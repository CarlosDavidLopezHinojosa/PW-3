<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.Reservas.ReservaDTO" %>
<%@ page import="web.model.business.Beans.CustomerBean" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ver Reservas</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/verreservas.css">
    <link rel="icon" href="<%= request.getContextPath() + "/static/images/user.png" %>" type="image/x-icon">
</head>
<body>
    <div class="header">
        <a href="<%= request.getContextPath() %>/controller/welcomeclientcontroller.jsp" class="back-button btn btn-secondary">Volver al Menú Principal</a>
    </div>
    <div class="container">
        <h1>Ver Reservas</h1>
        <% 
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje != null) {
        %>
            <p class="alert alert-info"><%= mensaje %></p>
        <% 
            }

            List<ReservaDTO> reservas = (List<ReservaDTO>) request.getAttribute("reservas");
            if (reservas != null && !reservas.isEmpty()) {
        %>
            <div class="reservas-list">
                <% 
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                for (ReservaDTO reserva : reservas) { %>
                    <div class="reserva-item">
                        <p><strong>ID:</strong> <%= reserva.getIdReserva() %></p>
                        <p><strong>Fecha y Hora:</strong> <%= reserva.getDiaYHora().format(formatter) %></p>
                        <p><strong>Pista:</strong> <%= reserva.getIdPista() %></p>
                    </div>
                <% } %>
            </div>
        <% 
            } else { 
        %>
            <p class="alert alert-info">No se encontraron reservas.</p>
        <% 
            } 
        %>
    </div>
</body>
</html>