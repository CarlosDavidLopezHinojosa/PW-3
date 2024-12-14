<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.PistaDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Buscar Pista Disponible</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css">
</head>
<body>
    <h1>Buscar Pista Disponible</h1>
    <form action="<%= request.getContextPath() %>/buscarPistaDisponible" method="post">
        <label for="fecha">Fecha:</label>
        <input type="date" id="fecha" name="fecha" value="<%= request.getParameter("fecha") != null ? request.getParameter("fecha") : "" %>" required><br><br>

        <label for="tipoPista">Tipo de Pista (opcional):</label>
        <select id="tipoPista" name="tipoPista">
            <option value="">Cualquiera</option>
            <option value="INTERIOR" <%= "INTERIOR".equals(request.getParameter("tipoPista")) ? "selected" : "" %>>Interior</option>
            <option value="EXTERIOR" <%= "EXTERIOR".equals(request.getParameter("tipoPista")) ? "selected" : "" %>>Exterior</option>
        </select><br><br>
        
        <input type="submit" value="Buscar">
    </form>
    
    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            out.println("<p>" + mensaje + "</p>");
        }
        
        List<PistaDTO> pistas = (List<PistaDTO>) request.getAttribute("pistas");
        if (pistas != null) {
            out.println("<h2>Pistas Disponibles:</h2>");
            out.println("<ul>");
            for (PistaDTO pista : pistas) {
                out.println("<li>" + pista.getNombre() + " - " + pista.getTamano() + " - " + (pista.isEsExterior() ? "Exterior" : "Interior") + "</li>");
            }
            out.println("</ul>");
        }
    %>
</body>
</html>