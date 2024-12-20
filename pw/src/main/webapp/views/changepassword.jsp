<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="web.model.business.Beans.CustomerBean" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cambiar Contraseña</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/styles.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/changepassword.css">

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
        <h2>Cambiar Contraseña</h2>
        <form id="changePasswordForm" action="<%= request.getContextPath() %>/controller/changepasswordcontroller.jsp" method="post" onsubmit="return validatePassword()">
            <label for="currentPassword">Contraseña Actual:</label>
            <input type="password" id="currentPassword" name="currentPassword" required><br>
            <label for="newPassword">Nueva Contraseña:</label>
            <input type="password" id="newPassword" name="newPassword" required><br>
            <label for="confirmPassword">Confirmar Nueva Contraseña:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required><br>
            <button type="submit">Cambiar Contraseña</button>

            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
                  out.println("<p class='error-message'>" + error + "</p>");
                }
            %>

        </form>
        <p>
            <a href="<%= request.getContextPath() %>/views/updateview.jsp" style="color: #007bff;">Volver atrás</a>
        </p>
    </div>
    <script src="<%= request.getContextPath() %>/static/js/validatepassword.js"></script>
</body>
</html>