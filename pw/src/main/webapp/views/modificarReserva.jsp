<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.Reservas.ReservaDTO" %>
<%@ page import="web.model.business.DTOs.PistaDTO" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modificar Reserva</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/styles.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/modificarreserva.css">
    <link rel="icon" href="<%= request.getContextPath() + "/static/images/user.png" %>" type="image/x-icon">
</head>
<body>
    <div class="header">
        <a href="<%= request.getContextPath() %>/controller/welcomeclientcontroller.jsp" class="back-button btn btn-secondary">Volver al Menú Principal</a>
    </div>
    <div class="form-container">
        <% 
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje != null) {
        %>
            <p class="alert alert-info"><%= mensaje %></p>
        <% 
            }

            List<ReservaDTO> reservas = (List<ReservaDTO>) request.getAttribute("reservas");
            if (reservas == null || reservas.isEmpty()) {
        %>
            <div class="alert alert-info">No tienes reservas futuras.</div>
        <% 
            } else { 
        %>
            
            <form id="modificarReservaForm" action="<%= request.getContextPath() %>/modificarReserva" method="post" onsubmit="return validarFormulario()">
                <div class="reservas-info">
                <h2>Reservas Futuras</h2>
                    <ul class="reservas-list">
                        <% 
                        
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        for (ReservaDTO reserva : reservas) { %>
                            <li>
                                <input type="radio" name="idReserva" value="<%= reserva.getIdReserva() %>" required>
                                <%= String.format(
                                        "ID: %d, Fecha y Hora: %s, Pista: %d, Tipo: %s, Adultos: %d, Niños: %d, Duración: %d minutos, Bono: %s",
                                        reserva.getIdReserva(), reserva.getDiaYHora().format(formatter) , reserva.getIdPista(),
                                        reserva.getTipoReserva(), reserva.getNumAdultos(), reserva.getNumNinos(),
                                        reserva.getDuracion(), reserva.getIdBono() != -1 ? "Sí" : "No"
                                ) %>
                            </li>
                        <% } %>
                    </ul>
                </div>
                <div class="form-group">
                    <label for="nuevaFecha">Nueva Fecha y Hora:</label>
                    <input type="datetime-local" id="nuevaFecha" name="nuevaFecha" required>
                </div>
                <input type="hidden" id="tipoReserva" name="tipoReserva">

                <div class="form-group">
                    <label for="duracion">Duración (minutos):</label>
                    <input type="number" id="duracion" name="duracion" required onchange="buscarPistasDisponibles()">
                </div>

                <div class="form-group">
                    <label for="numAdultos">Número de Adultos:</label>
                    <input type="number" id="numAdultos" name="numAdultos" required>
                </div>

                <div class="form-group">
                    <label for="numNinos">Número de Niños:</label>
                    <input type="number" id="numNinos" name="numNinos" required>
                </div>

                <div class="form-group">
                    <label for="pista">Pista:</label>
                    <select id="pista" name="pista" required onchange="actualizarPrecio()">
                        <option value="">Seleccione una pista</option>
                    </select>
                </div>
                <input type="hidden" id="pistaTamano" name="pistaTamano">
                <input type="hidden" id="precio" name="precio">
                <button type="submit" class="btn btn-primary">Modificar Reserva</button>
            </form>
        <% } %>
    </div>
    
    <script>
        function actualizarCampos() {
            var reservaSeleccionada = document.querySelector('input[name="idReserva"]:checked').parentElement.textContent;
            var tipoReserva = reservaSeleccionada.match(/Tipo: (\w+)/)[1];
            var idBono = reservaSeleccionada.match(/Bono: (\w+)/)[1] === "Sí" ? true : false;
            document.getElementById("tipoReserva").value = tipoReserva;

            var numAdultosField = document.getElementById("numAdultos");
            var numAdultosLabel = document.getElementById("numAdultosLabel");
            var numNinosField = document.getElementById("numNinos");
            var numNinosLabel = document.getElementById("numNinosLabel");

            if (tipoReserva === "ADULTOS") {
                numAdultosField.style.display = "block";
                numAdultosLabel.style.display = "block";
                numNinosField.style.display = "none";
                numNinosLabel.style.display = "none";
                numNinosField.value = 0;
            } else if (tipoReserva === "INFANTIL") {
                numAdultosField.style.display = "none";
                numAdultosLabel.style.display = "none";
                numAdultosField.value = 0;
                numNinosField.style.display = "block";
                numNinosLabel.style.display = "block";
            } else if (tipoReserva === "FAMILIAR") {
                numAdultosField.style.display = "block";
                numAdultosLabel.style.display = "block";
                numNinosField.style.display = "block";
                numNinosLabel.style.display = "block";
            } else {
                numAdultosField.style.display = "none";
                numAdultosLabel.style.display = "none";
                numNinosField.style.display = "none";
                numNinosLabel.style.display = "none";
            }

            if (idBono) {
                document.getElementById("pista").disabled = true;
            } else {
                document.getElementById("pista").disabled = false;
            }
        }

        function buscarPistasDisponibles() {
            var tipoReserva = document.getElementById("tipoReserva").value;
            var diaYHora = document.getElementById("nuevaFecha").value;
            var duracion = document.getElementById("duracion").value;

            if (tipoReserva && diaYHora && duracion) {
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "<%= request.getContextPath() %>/realizarReserva?action=buscarPistasDisponibles&tipoReserva=" + tipoReserva + "&diaYHora=" + diaYHora + "&duracion=" + duracion, true);
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var pistaSelect = document.getElementById("pista");
                        pistaSelect.innerHTML = xhr.responseText;
                    }
                };
                xhr.send();
            }
        }

        function actualizarPrecio() {
            var duracion = document.getElementById("duracion").value;
            var pistaTamano = document.getElementById("pista").selectedOptions[0].getAttribute("data-tamano");
            var precio = 0;

            if (pistaTamano === "MINIBASKET") {
                precio = 0.1 * duracion;
            } else if (pistaTamano === "VS3") {
                precio = 0.13 * duracion;
            } else if (pistaTamano === "ADULTOS") {
                precio = 0.15 * duracion;
            }

            document.getElementById("pistaTamano").value = pistaTamano;
            document.getElementById("precio").value = precio.toFixed(2);
        }

        function validarFormulario() {
            var numAdultos = parseInt(document.getElementById("numAdultos").value) || 0;
            var numNinos = parseInt(document.getElementById("numNinos").value) || 0;
            var maxJugadores = parseInt(document.getElementById("pista").selectedOptions[0].getAttribute("data-max-jugadores"));

            if (numAdultos + numNinos > maxJugadores) {
                alert("El número total de participantes supera el límite permitido para esta pista.");
                return false;
            }

            return true;
        }

        // Inicializar campos al cargar la página
        document.addEventListener('DOMContentLoaded', function() {
            document.querySelectorAll('input[name="idReserva"]').forEach(function(radio) {
                radio.addEventListener('change', actualizarCampos);
            });
        });
    </script>
</body>
</html>