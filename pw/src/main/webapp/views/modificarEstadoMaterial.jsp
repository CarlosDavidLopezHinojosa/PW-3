<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.MaterialDTO" %>

<!DOCTYPE html>
<html>
<head>
    <title>Modificar Estado de Material</title>
    <link rel="icon" href="<%= request.getContextPath() + "/static/images/admin.png" %>" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/modificarestadomaterial.css">
</head>
<body>
    <div class="page-container">
        <a href="<%= request.getContextPath() %>/controller/welcomeadmincontroller.jsp" class="back-button btn btn-secondary">Volver al Menú Principal</a>
        <div class="form-container">
            <h1>Modificar Estado de Material</h1>
            <%
                List<MaterialDTO> materiales = (List<MaterialDTO>) request.getAttribute("materiales");
                if (materiales == null || materiales.isEmpty()) {
            %>
                <p class="error-message">No hay materiales disponibles para modificar.</p>
            <%
                } else {
            %>
                <form id="modificarEstadoMaterialForm" action="<%= request.getContextPath() %>/modificarEstadoMaterial" method="post">
                    <div class="form-group">
                        <label for="materialId">Material:</label>
                        <select id="materialId" name="materialId" required>
                            <%
                                for (MaterialDTO material : materiales) {
                                    out.println("<option value='" + material.getId() + "'>" + material.getId() + " - " + material.getTipo() + " - " + material.getEstado() + "</option>");
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="nuevoEstado">Nuevo Estado:</label>
                        <select id="nuevoEstado" name="nuevoEstado" required>
                            <option value="DISPONIBLE">Disponible</option>
                            <option value="RESERVADO">Reservado</option>
                            <option value="MAL_ESTADO">Mal Estado</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <input type="submit" value="Modificar Estado" class="btn-submit">
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
