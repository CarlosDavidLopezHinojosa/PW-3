<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.MaterialDTO" %>
<%@ page import="web.model.business.DTOs.PistaDTO" %>

<!DOCTYPE html>
<html>
<head>
    <title>Asociar Material a Pista</title>
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
    <h1>Asociar Material a Pista</h1>
    <form id="asociarMaterialPistaForm" action="<%= request.getContextPath() %>/asociarMaterialPista" method="post">
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
        </select><br><br>

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
        </select><br><br>

        <input type="submit" value="Asociar Material">
    </form>

    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            out.println("<p style='color:red;'>" + mensaje + "</p>");
        }
    %>
</body>
</html>