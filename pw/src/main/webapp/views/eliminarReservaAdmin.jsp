<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.Reservas.ReservaDTO" %>
<%@ page import="web.model.business.Beans.CustomerBean" %>
<!DOCTYPE html>
<html>
<head>
    <title>Eliminar Reserva</title>
    <link rel="icon" href="<%= request.getContextPath() + "/static/images/admin.png" %>" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/eliminarreserva.css">
    <script src="<%= request.getContextPath() %>/static/js/eliminar-reserva.js" defer></script>

    <%
        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
        if(customerBean == null || customerBean.getRol().equals("CLIENTE")) {
            response.sendRedirect("../views/loginview.jsp");
        }
    %>

</head>
<body>
    <div class="page-container">
        <a href="<%= request.getContextPath() %>/controller/welcomeadmincontroller.jsp" class="back-button btn btn-secondary">Volver al Menú Principal</a>
        <div class="form-container">
            <h1>Eliminar Reserva</h1>
            <%
                List<ReservaDTO> reservasFuturas = (List<ReservaDTO>) request.getAttribute("reservasFuturas");
                if (reservasFuturas == null || reservasFuturas.isEmpty()) {
            %>
                <p class="error-message">No hay reservas futuras disponibles para eliminar.</p>
            <%
                } else {
            %>
                <form id="eliminarReservaForm" action="<%= request.getContextPath() %>/eliminarReservaAdmin" method="post">
                    <div class="form-group">
                        <label for="reservaId">Reserva:</label>
                        <select id="reservaId" name="reservaId" required>
                            <%
                                for (ReservaDTO reserva : reservasFuturas) {
                                    out.println("<option value='" + reserva.getIdReserva() + "'>" + reserva.getIdReserva() + " - Usuario: " + reserva.getIdUsuario() + " - Día: " + reserva.getDiaYHora().toLocalDate() + " - Pista: " + reserva.getIdPista() + "</option>");
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="confirmDelete">Estoy seguro de que deseo eliminar la reserva</label><input type="checkbox" id="confirmDelete" name="confirmDelete" required>
                        
                    </div>

                    <div class="form-group">
                        <input type="submit" value="Eliminar Reserva" class="btn-submit">
                    </div>
                </form>
            <%
                }
                String mensaje = (String) request.getAttribute("mensaje");
                String error = (String) request.getAttribute("error");
                if (mensaje != null) {
                    out.println("<p class='success-message'>" + mensaje + "</p>");
                }

                if(error != null){
                    out.println("<p class='error-message'>" + error + "</p>");
                }
            %>
        </div>
    </div>
</body>
</html>
