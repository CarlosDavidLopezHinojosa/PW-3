<%@ page import="web.model.business.Beans.CustomerBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Datos</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/styles.css"> <!-- Enlace al CSS -->
</head>
<body>
    <div class="container">
        <h2>Modificar Datos</h2>
        <%
            CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
            if (customerBean == null) {
                response.sendRedirect("../views/loginview.jsp");
                return;
            }
        %>
        <form action="<%= request.getContextPath() %>/controller/updatecontroller.jsp" method="post">
            <label for="name">Nombre:</label>
            <input type="text" id="name" name="name" value="<%= customerBean.getNombre() %>" required><br><br>
            <label for="lastName">Apellidos:</label>
            <input type="text" id="lastName" name="lastName" value="<%= customerBean.getApellidos() %>" required><br><br>
            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
        </form>
    </div>
</body>
</html>