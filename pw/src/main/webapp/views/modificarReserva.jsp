<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.Reservas.ReservaDTO" %>
<%@ page import="web.model.business.DTOs.PistaDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modificar Reserva</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css">
    <style>
        .back-button {
            position: absolute;
            top: 10px;
            right: 10px;
        }
    </style>
</head>
<body>
    <a href="<%= request.getContextPath() %>/controller/welcomeclientcontroller.jsp" class="back-button btn btn-secondary">Volver al Menú Principal</a>
    <h1>Modificar Reserva</h1>
    
    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            out.println("<p>" + mensaje + "</p>");
        }
        
        List<ReservaDTO> reservas = (List<ReservaDTO>) request.getAttribute("reservas");
        if (reservas != null) {
            if (reservas.isEmpty()) {
                out.println("<p>El usuario no tiene reservas futuras.</p>");
            } else {
                out.println("<h2>Reservas Futuras:</h2>");
                out.println("<form id='modificarReservaForm' action='" + request.getContextPath() + "/modificarReserva' method='post' onsubmit='return validarFormulario()'>");
                out.println("<ul>");
                for (ReservaDTO reserva : reservas) {
                    out.println("<li>");
                    out.println("<input type='radio' name='idReserva' value='" + reserva.getIdReserva() + "' required>");
                    out.println("ID: " + reserva.getIdReserva() + ", Fecha y Hora: " + reserva.getDiaYHora() + ", Pista: " + reserva.getIdPista() + ", Tipo: " + reserva.getTipoReserva() + ", Adultos: " + reserva.getNumAdultos() + ", Niños: " + reserva.getNumNinos() + ", Duración: " + reserva.getDuracion() + " minutos, Bono: " + (reserva.getIdBono() != -1 ? "Sí" : "No"));
                    out.println("</li>");
                }
                out.println("</ul>");
                out.println("<label for='nuevaFecha'>Nueva Fecha y Hora:</label>");
                out.println("<input type='datetime-local' id='nuevaFecha' name='nuevaFecha' required><br><br>");

                out.println("<input type='hidden' id='tipoReserva' name='tipoReserva'>");

                out.println("<label for='duracion'>Duración (minutos):</label>");
                out.println("<input type='number' id='duracion' name='duracion' required onchange='buscarPistasDisponibles()'><br><br>");

                out.println("<label for='numAdultos' id='numAdultosLabel'>Número de Adultos:</label>");
                out.println("<input type='number' id='numAdultos' name='numAdultos' required><br><br>");

                out.println("<label for='numNinos' id='numNinosLabel'>Número de Niños:</label>");
                out.println("<input type='number' id='numNinos' name='numNinos' required><br><br>");

                out.println("<label for='pista'>Pista:</label>");
                out.println("<select id='pista' name='pista' required onchange='actualizarPrecio()'>");
                out.println("<option value=''>Seleccione una pista</option>");
                out.println("</select><br><br>");

                out.println("<input type='hidden' id='pistaTamano' name='pistaTamano'>");
                out.println("<input type='hidden' id='precio' name='precio'>");

                out.println("<input type='submit' name='confirmar' value='Modificar Reserva'>");
                out.println("</form>");
            }
        }
    %>

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
