<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.Reservas.ReservaDTO" %>
<%@ page import="web.model.business.Beans.CustomerBean" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cancelar Reserva</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/styles.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/cancelarreserva.css">
    <link rel="icon" href="<%= request.getContextPath() + "/static/images/user.png" %>" type="image/x-icon">

    <%
        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
        if(customerBean == null || customerBean.getRol().equals("ADMIN")) {
            response.sendRedirect("../views/loginview.jsp");
        }
    %>
</head>
<body>
    <div class="header">
        <a href="<%= request.getContextPath() %>/controller/welcomeclientcontroller.jsp" class="back-button btn btn-secondary">Volver al Menú Principal</a>
    </div>
    
    <div class="form-container">
        <% 
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje != null) {
        %>
            <p class="alert alert-info"><%= mensaje %></p>
        <% 
            }

            List<ReservaDTO> reservas = (List<ReservaDTO>) request.getAttribute("reservas");
            if (reservas == null || reservas.isEmpty()) {
        %>
            <p class="alert alert-info">No se encontraron reservas futuras para el usuario.</p>
        <% 
            } else { 
        %>
            
            <form action="<%= request.getContextPath() %>/cancelarReserva" method="post">
            <h2>Reservas Futuras</h2>
                <ul class="reservas-list">
                    <% 
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    for (ReservaDTO reserva : reservas) { %>
                        <li>
                            <input type="radio" name="idReserva" value="<%= reserva.getIdReserva() %>" required>
                            <%= String.format(
                                    "ID: %d, Fecha y Hora: %s, Pista: %d",
                                    reserva.getIdReserva(), reserva.getDiaYHora().format(formatter), reserva.getIdPista()
                            ) %>
                        </li>
                    <% } %>
                </ul>
                <button type="submit" class="btn btn-primary">Cancelar Reserva</button>
            </form>
        <% } %>
    </div>
</body>
</html>