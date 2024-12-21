<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.PistaDTO" %>
<%@ page import="web.model.business.Beans.CustomerBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Buscar Pista Disponible</title>
        <link rel="icon" href="<%= request.getContextPath() + "/static/images/user.png" %>" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/buscarpistadisponible.css">

    <%
        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
        if(customerBean == null || customerBean.getRol().equals("ADMIN")) {
            response.sendRedirect(request.getContextPath() + "/views/loginview.jsp");
        }
    %>
</head>
<body>
    <div class="header">
        <a href="<%= request.getContextPath() %>/controller/welcomeclientcontroller.jsp" class="back-button btn btn-secondary">Volver al Menú Principal</a>
    </div>
    <div class="form-container">
        <form action="<%= request.getContextPath() %>/buscarPistaDisponible" method="post">
        <h1>Buscar Pista Disponible</h1>
            <div class="form-group">
                <label for="fechaHora">Fecha y Hora:</label>
                <input type="datetime-local" id="fechaHora" name="fechaHora" value="<%= request.getParameter("fechaHora") != null ? request.getParameter("fechaHora") : "" %>" required>
            </div>
            <div class="form-group">
                <label for="tipoPista">Tipo de Pista (opcional):</label>
                <select id="tipoPista" name="tipoPista">
                    <option value="">Cualquiera</option>
                    <option value="INTERIOR" <%= "INTERIOR".equals(request.getParameter("tipoPista")) ? "selected" : "" %>>Interior</option>
                    <option value="EXTERIOR" <%= "EXTERIOR".equals(request.getParameter("tipoPista")) ? "selected" : "" %>>Exterior</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Buscar</button>
        </form>
        <% 
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje != null) {
        %>
            <p class="alert alert-info"><%= mensaje %></p>
        <% 
            }

            List<PistaDTO> pistas = (List<PistaDTO>) request.getAttribute("pistas");
            if (pistas != null && !pistas.isEmpty()) {
        %>
            <div class="pistas-container">
                <h2>Pistas Disponibles:</h2>
                <div class="pistas-list">
                    <% for (PistaDTO pista : pistas) { %>
                        <div class="pista-item">
                            <p><strong>Nombre:</strong> <%= pista.getNombre() %></p>
                            <p><strong>Tamaño:</strong> <%= pista.getTamano() %></p>
                            <p><strong>Tipo:</strong> <%= pista.isEsExterior() ? "Exterior" : "Interior" %></p>
                        </div>
                    <% } %>
                </div>
            </div>
        <% 
            } 
        %>
    </div>
</body>
</html>