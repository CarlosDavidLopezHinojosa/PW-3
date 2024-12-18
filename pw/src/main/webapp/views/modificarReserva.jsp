<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.Reservas.ReservaDTO" %>
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
        List<ReservaDTO> reservas = (List<ReservaDTO>) request.getAttribute("reservas");
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            out.println("<p style='color:red;'>" + mensaje + "</p>");
        }
    %>

    <form id="seleccionarReservaForm" action="<%= request.getContextPath() %>/modificarReserva" method="post">
        <label for="idReserva">Seleccione una Reserva:</label>
        <select id="idReserva" name="idReserva" required onchange="actualizarCampos(); buscarPistasDisponibles();">
            <option value="" selected>Seleccione una reserva</option>
            <% for (ReservaDTO reserva : reservas) { %>
            <option value="<%= reserva.getIdReserva() %>" data-diaYHora="<%= reserva.getDiaYHora().toString().replace(' ', 'T') %>" data-idPista="<%= reserva.getIdPista() %>" data-tipoReserva="<%= reserva.getTipoReserva() %>">
                Pista: <%= reserva.getIdPista() %> - Fecha y Hora: <%= reserva.getDiaYHora().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) %>
            </option>
            <% } %>
        </select><br><br>
    </form>

    <form id="modificarReservaForm" action="<%= request.getContextPath() %>/modificarReserva" method="post" onsubmit="return validarFormulario()">
        <input type="hidden" name="idReserva" id="idReservaHidden">
        <label for="tipoReserva">Tipo de Reserva:</label>
        <input type="text" id="tipoReserva" name="tipoReserva" readonly><br><br>

        <label for="diaYHora">Día y Hora de la Reserva:</label>
        <input type="datetime-local" id="diaYHora" name="nuevaFecha" required onchange="buscarPistasDisponibles()"><br><br>

        <label for="pista">Pista:</label>
        <select id="pista" name="pista" required>
            <option value=""></option>
        </select><br><br>

        <input type="submit" name="confirmar" value="Modificar Reserva">
    </form>

    <script>
        function actualizarCampos() {
            var selectReserva = document.getElementById("idReserva");
            var selectedOption = selectReserva.options[selectReserva.selectedIndex];

            if (selectedOption.value) {
                document.getElementById("idReservaHidden").value = selectedOption.value;
                document.getElementById("tipoReserva").value = selectedOption.getAttribute("data-tipoReserva");
                document.getElementById("diaYHora").value = selectedOption.getAttribute("data-diaYHora");
                buscarPistasDisponibles();
            }
        }

        function buscarPistasDisponibles() {
            var tipoReserva = document.getElementById("tipoReserva").value;
            var diaYHora = document.getElementById("diaYHora").value;
            var pistaSelect = document.getElementById("pista");
            var idPistaActual = document.getElementById("idReservaHidden").value;

            if (tipoReserva && diaYHora) {
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "<%= request.getContextPath() %>/modificarReserva?action=buscarPistasDisponibles&tipoReserva=" + tipoReserva + "&diaYHora=" + diaYHora, true);
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        pistaSelect.innerHTML = xhr.responseText;
                    }
                };
                xhr.send();
            }
        }
    </script>
</body>
</html>