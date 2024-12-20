<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.PistaDTO" %>
<%@ page import="web.model.business.Beans.CustomerBean" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modificar Estado de Pista</title>
    <link rel="icon" href="<%= request.getContextPath() + "/static/images/admin.png" %>" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/modificarestadopista.css">

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
            <h1>Modificar Estado de Pista</h1>
            <%
                List<PistaDTO> pistas = (List<PistaDTO>) request.getAttribute("pistas");
                if (pistas == null || pistas.isEmpty()) {
            %>
                <p class="error-message">No hay pistas disponibles para modificar.</p>
            <%
                } else {
            %>
                <form id="modificarEstadoPistaForm" action="<%= request.getContextPath() %>/modificarEstadoPista" method="post">
                    <div class="form-group">
                        <label for="idPista">Pista:</label>
                        <select id="idPista" name="idPista" required>
                            <%
                                for (PistaDTO pista : pistas) {
                                    out.println("<option value='" + pista.getId() + "'>" + pista.getId() + " - " + pista.getNombre() + " - " + (pista.isDisponible() ? "Disponible" : "No Disponible") + "</option>");
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="nuevaDisponibilidad">Nueva Disponibilidad:</label>
                        <select id="nuevaDisponibilidad" name="nuevaDisponibilidad" required>
                            <option value="true">Disponible</option>
                            <option value="false">No Disponible</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <input type="submit" value="Modificar Disponibilidad" class="btn-submit">
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
