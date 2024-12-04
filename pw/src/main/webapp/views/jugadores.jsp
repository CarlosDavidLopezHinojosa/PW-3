<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.JugadorDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Jugadores</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/jugadores.css">
</head>
<body>
    <h1>Lista de Jugadores</h1>
    <ul>
        <%
            List<JugadorDTO> jugadores = (List<JugadorDTO>) request.getAttribute("jugadores");
            for (JugadorDTO jugador : jugadores) {
                out.println("<li>" + jugador.getNombre() + " (" + jugador.getEmail() + ")</li>");
            }
        %>
    </ul>
</body>
</html>