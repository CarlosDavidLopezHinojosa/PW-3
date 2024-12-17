<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.Reservas.ReservaDTO" %>

<!DOCTYPE html>
<html>
<head>
    <title>Eliminar Reserva</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css">
    <style>
        .back-button {
            position: absolute;
            top: 10px;
            right: 10px;
        }
    </style>
    <script>
        function validateForm() {
            var checkbox = document.getElementById("confirmDelete");
            if (!checkbox.checked) {
                alert("Debe confirmar que desea eliminar la reserva.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <a href="<%= request.getContextPath() %>/controller/welcomeadmincontroller.jsp" class="back-button btn btn-secondary">Volver al Menú Principal</a>
    <h1>Eliminar Reserva</h1>
    <%
        List<ReservaDTO> reservasFuturas = (List<ReservaDTO>) request.getAttribute("reservasFuturas");
        if (reservasFuturas == null || reservasFuturas.isEmpty()) {
    %>
        <p style="color:red;">No hay reservas futuras disponibles para eliminar.</p>
    <%
        } else {
    %>
        <form id="eliminarReservaForm" action="<%= request.getContextPath() %>/eliminarReservaAdmin" method="post" onsubmit="return validateForm();">
            <label for="reservaId">Reserva:</label>
            <select id="reservaId" name="reservaId" required>
                <%
                    for (ReservaDTO reserva : reservasFuturas) {
                        out.println("<option value='" + reserva.getIdReserva() + "'>" + reserva.getIdReserva() + " - Usuario: " + reserva.getIdUsuario() + " - Día: " + reserva.getDiaYHora().toLocalDate() + " - Pista: " + reserva.getIdPista() + "</option>");
                    }
                %>
            </select><br><br>

            <input type="checkbox" id="confirmDelete" name="confirmDelete" required>
            <label for="confirmDelete">Estoy seguro de que deseo eliminar la reserva</label><br><br>

            <input type="submit" value="Eliminar Reserva">
        </form>
    <%
        }
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            out.println("<p style='color:red;'>" + mensaje + "</p>");
        }
    %>
</body>
</html>
