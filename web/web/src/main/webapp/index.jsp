<%@ page import="java.sql.Connection, java.sql.SQLException, java.util.List" %>
<%@ page import="data.common.DBConnection" %>
<%@ page import="business.DTOs.PistaDTO" %>
<%@ page import="data.DAOs.PistaDAO" %>

<!DOCTYPE html>
<html>
<head>
    <title>Database Results</title>
</head>
<body>
    <h1>Database Results</h1>
    <%
        List<PistaDTO> pistasDisponibles = PistaDAO.listarPistasDisponibles();
        if (pistasDisponibles != null && !pistasDisponibles.isEmpty()) {
            for (PistaDTO pista : pistasDisponibles) {
            out.println("<p>" + pista.toString() + "</p>");
            }
        } else {
            out.println("<p>No hay pistas disponibles.</p>");
        }
    %>
</body>
</html>