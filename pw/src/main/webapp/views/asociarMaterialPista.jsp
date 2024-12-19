<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.MaterialDTO" %>
<%@ page import="web.model.business.DTOs.PistaDTO" %>

<!DOCTYPE html>
<html>
<head>
    <title>Asociar Material a Pista</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/asociarmaterialpista.css">
</head>
<body>
    <div class="page-container">
        <a href="<%= request.getContextPath() %>/controller/welcomeadmincontroller.jsp" class="back-button btn btn-secondary">Volver al Menú Principal</a>
        <div class="form-container">
            <h1>Asociar Material a Pista</h1>
            <form id="asociarMaterialPistaForm" action="<%= request.getContextPath() %>/asociarMaterialPista" method="post">
                <div class="form-group">
                    <label for="materialId">Material:</label>
                    <select id="materialId" name="materialId" required>
                        <%
                            List<MaterialDTO> materiales = (List<MaterialDTO>) request.getAttribute("materiales");
                            if (materiales != null) {
                                for (MaterialDTO material : materiales) {
                                    out.println("<option value='" + material.getId() + "'>" + material.getId() + " - " + material.getTipo() + " - " + material.getEstado() + "</option>");
                                }
                            }
                        %>
                    </select>
                </div>

                <div class="form-group">
                    <label for="pistaId">Pista:</label>
                    <select id="pistaId" name="pistaId" required>
                        <%
                            List<PistaDTO> pistas = (List<PistaDTO>) request.getAttribute("pistas");
                            if (pistas != null) {
                                for (PistaDTO pista : pistas) {
                                    out.println("<option value='" + pista.getId() + "'>" + pista.getNombre() + " - " + (pista.isDisponible() ? "Disponible" : "No Disponible") + "</option>");
                                }
                            }
                        %>
                    </select>
                </div>

                <div class="form-group">
                    <input type="submit" value="Asociar Material" class="btn-submit">
                </div>
            </form>

            <%
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
