<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.Reservas.ReservaDTO" %>
<%@ page import="web.model.business.Beans.CustomerBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ver Reservas</title>
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
    <h1>Ver Reservas</h1>
    
    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            out.println("<p>" + mensaje + "</p>");
        }
        
        List<ReservaDTO> reservas = (List<ReservaDTO>) request.getAttribute("reservas");
        if (reservas != null && !reservas.isEmpty()) {
            for (ReservaDTO reserva : reservas) {
                out.println("<div>");
                out.println("<p>ID: " + reserva.getIdReserva() + "</p>");
                out.println("<p>Fecha y Hora: " + reserva.getDiaYHora() + "</p>");
                out.println("<p>Pista: " + reserva.getIdPista() + "</p>");
                out.println("</div>");
            }
        } else {
            out.println("<p>No tienes reservas futuras.</p>");
        }
    %>
</body>
</html>
