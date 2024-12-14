<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.MaterialDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Materiales</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css">
</head>
<body>
    <h1>Lista de Materiales</h1>
    <ul>
        <%
            List<MaterialDTO> materiales = (List<MaterialDTO>) request.getAttribute("materiales");
            for (MaterialDTO material : materiales) {
                out.println("<li>" + material.getId() + " - " + material.getTipo() + " - " + material.isUsoExterior() + " - " + material.getEstado() + " - " + material.getIdPista() + "</li>");
            }
        %>
    </ul>
</body>
</html>