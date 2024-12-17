<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.PistaDTO" %>

<!DOCTYPE html>
<html>
<head>
    <title>Modificar Estado de Pista</title>
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
    <a href="<%= request.getContextPath() %>/controller/welcomeadmincontroller.jsp" class="back-button btn btn-secondary">Volver al Menú Principal</a>
    <h1>Modificar Estado de Pista</h1>
    <%
        List<PistaDTO> pistas = (List<PistaDTO>) request.getAttribute("pistas");
        if (pistas == null || pistas.isEmpty()) {
    %>
        <p style="color:red;">No hay pistas disponibles para modificar.</p>
    <%
        } else {
    %>
        <form id="modificarEstadoPistaForm" action="<%= request.getContextPath() %>/modificarEstadoPista" method="post">
            <label for="idPista">Pista:</label>
            <select id="idPista" name="idPista" required>
                <%
                    for (PistaDTO pista : pistas) {
                        out.println("<option value='" + pista.getId() + "'>" + pista.getId() + " - " + pista.getNombre() + " - " + (pista.isDisponible() ? "Disponible" : "No Disponible") + "</option>");
                    }
                %>
            </select><br><br>

            <label for="nuevaDisponibilidad">Nueva Disponibilidad:</label>
            <select id="nuevaDisponibilidad" name="nuevaDisponibilidad" required>
                <option value="true">Disponible</option>
                <option value="false">No Disponible</option>
            </select><br><br>

            <input type="submit" value="Modificar Disponibilidad">
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