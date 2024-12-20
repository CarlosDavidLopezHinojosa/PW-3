<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="web.model.business.Beans.CustomerBean" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Datos</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/styles.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/update.css">

<%
    CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
    if (customerBean == null) {
        response.sendRedirect("../views/loginview.jsp");
        return;
    }
    boolean isAdmin = customerBean.getRol().name().equals("ADMIN");
    String iconPath = request.getContextPath() + "/static/images/" + (isAdmin ? "admin.png" : "user.png");
%>

<link rel="icon" href="<%= iconPath %>" type="image/x-icon">

</head>
<body>
    <a href="<%= request.getContextPath() + "/controller/welcome" + (isAdmin ? "admin" : "client") + "controller.jsp" %>" class="btn-back"><strong>VOLVER AL MENÚ PRINCIPAL</strong></a>
    <div class="container">
        <h2>Modificar Datos</h2>

        <form action="<%= request.getContextPath() %>/controller/updatecontroller.jsp" method="post">
            <label for="name">Nombre:</label>
            <input type="text" id="name" name="name" value="<%= customerBean.getNombre() %>" required><br>
            <label for="lastName">Apellidos:</label>
            <input type="text" id="lastName" name="lastName" value="<%= customerBean.getApellidos() %>" required><br>
            <button type="submit">Guardar Cambios</button>
        </form>
        <a href="<%= request.getContextPath() %>/views/changepassword.jsp" class="btn btn-secondary">Cambiar Contraseña</a>
    </div>
</body>
</html>