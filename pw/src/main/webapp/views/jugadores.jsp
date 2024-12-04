<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.JugadorDTO" %>
<%@ page import="web.model.data.DAOs.JugadorDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Jugadores</title>
</head>
<body>
    <h1>Lista de Jugadores</h1>
    <ul>
        <%
            JugadorDAO jugadorDAO = new JugadorDAO();
            List<JugadorDTO> jugadores = jugadorDAO.obtenerUsuarios();
            for (JugadorDTO jugador : jugadores) {
                out.println("<li>" + jugador + "</li>");
            }
        %>
    </ul>
</body>
</html>