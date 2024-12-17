<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.MaterialDTO" %>

<!DOCTYPE html>
<html>
<head>
    <title>Modificar Estado de Material</title>
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
    <a href="<%= request.getContextPath() %>/controller/welcomeadmincontroller.jsp" class="back-button btn btn-secondary">Volver al Menú Principal</a>
    <h1>Modificar Estado de Material</h1>
    <%
        List<MaterialDTO> materiales = (List<MaterialDTO>) request.getAttribute("materiales");
        if (materiales == null || materiales.isEmpty()) {
    %>
        <p style="color:red;">No hay materiales disponibles para modificar.</p>
    <%
        } else {
    %>
        <form id="modificarEstadoMaterialForm" action="<%= request.getContextPath() %>/modificarEstadoMaterial" method="post">
            <label for="materialId">Material:</label>
            <select id="materialId" name="materialId" required>
                <%
                    for (MaterialDTO material : materiales) {
                        out.println("<option value='" + material.getId() + "'>" + material.getId() + " - " + material.getTipo() + " - " + material.getEstado() + "</option>");
                    }
                %>
            </select><br><br>

            <label for="nuevoEstado">Nuevo Estado:</label>
            <select id="nuevoEstado" name="nuevoEstado" required>
                <option value="DISPONIBLE">Disponible</option>
                <option value="RESERVADO">Reservado</option>
                <option value="MAL_ESTADO">Mal Estado</option>
            </select><br><br>

            <input type="submit" value="Modificar Estado">
        </form>
    <%
        }
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            out.println("<p style='color:red;'>" + mensaje + "</p>");
        }
    %>
</body>
</html>