<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.BonoDTO" %>
<%@ page import="web.model.business.DTOs.PistaDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Realizar Reserva</title>
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
    <h1>Realizar Reserva</h1>
    <form id="reservaForm" action="<%= request.getContextPath() %>/realizarReserva" method="post" onsubmit="return validarFormulario()">
        <label for="tipoReserva">Tipo de Reserva:</label>
        <select id="tipoReserva" name="tipoReserva" required onchange="actualizarCampos(); buscarPistasDisponibles();">
            <option value="Seleccione una opción" selected>Seleccione una opción</option>
            <option value="ADULTOS">ADULTOS</option>
            <option value="FAMILIAR">FAMILIAR</option>
            <option value="INFANTIL">INFANTIL</option>
        </select><br><br>

        <label for="diaYHora">Día y Hora de la Reserva:</label>
        <input type="datetime-local" id="diaYHora" name="diaYHora" required onchange="buscarPistasDisponibles()"><br><br>

        <label for="esBono">¿Es de Bono?:</label>
        <input type="checkbox" id="esBono" name="esBono" value="true" onchange="toggleBonoFields()"><br><br>

        <div id="bonoFields" style="display:none;">
            <label for="idBono">Seleccione un Bono:</label>
            <select id="idBono" name="idBono" onchange="buscarPistasDisponibles()">
                <option value="">Seleccione un bono</option>
            </select><br><br>
        </div>

        <label for="duracion">Duración (minutos):</label>
        <input type="number" id="duracion" name="duracion" required onchange="buscarPistasDisponibles()"><br><br>

        <label for="numAdultos" id="numAdultosLabel" style="display:none;">Número de Adultos:</label>
        <input type="number" id="numAdultos" name="numAdultos" style="display:none;"><br><br>

        <label for="numNinos" id="numNinosLabel" style="display:none;">Número de Niños:</label>
        <input type="number" id="numNinos" name="numNinos" style="display:none;"><br><br>

        <label for="pista">Pista:</label>
        <select id="pista" name="pista" required onchange="actualizarPrecio()">
            <option value="">Seleccione una pista</option>
        </select><br><br>

        <input type="hidden" id="pistaTamano" name="pistaTamano">
        <input type="hidden" id="precio" name="precio">

        <input type="submit" value="Realizar Reserva">
    </form>

    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            out.println("<p style='color:red;'>" + mensaje + "</p>");
        }
    %>

    <script>
        function toggleBonoFields() {
            var bonoFields = document.getElementById("bonoFields");
            if (document.getElementById("esBono").checked) {
                bonoFields.style.display = "block";
                buscarBonosDisponibles();
            } else {
                bonoFields.style.display = "none";
                document.getElementById("idBono").value = "";
                buscarPistasDisponibles();
            }
        }

        function actualizarCampos() {
            var tipoReserva = document.getElementById("tipoReserva").value;
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
        }

        function buscarPistasDisponibles() {
            var tipoReserva = document.getElementById("tipoReserva").value;
            var diaYHora = document.getElementById("diaYHora").value;
            var duracion = document.getElementById("duracion").value;
            var idBono = document.getElementById("idBono").value;
            var esBono = document.getElementById("esBono").checked;

            if (tipoReserva && diaYHora && duracion) {
                var xhr = new XMLHttpRequest();
                var url = "<%= request.getContextPath() %>/realizarReserva?action=buscarPistasDisponibles&tipoReserva=" + tipoReserva + "&diaYHora=" + diaYHora + "&duracion=" + duracion;
                if (esBono && idBono) {
                    url += "&idBono=" + idBono;
                }
                xhr.open("GET", url, true);
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var pistaSelect = document.getElementById("pista");
                        pistaSelect.innerHTML = xhr.responseText;
                    }
                };
                xhr.send();
            }
        }

        function buscarBonosDisponibles() {
            var tipoReserva = document.getElementById("tipoReserva").value;

            if (tipoReserva) {
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "<%= request.getContextPath() %>/realizarReserva?action=buscarBonosDisponibles&tipoReserva=" + tipoReserva, true);
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var bonoSelect = document.getElementById("idBono");
                        bonoSelect.innerHTML = xhr.responseText;
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
            document.getElementById("esBono").addEventListener('change', toggleBonoFields);
            document.getElementById("tipoReserva").addEventListener('change', actualizarCampos);
        });
    </script>
</body>
</html>
