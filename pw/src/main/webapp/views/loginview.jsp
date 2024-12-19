<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/styles.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/login.css">
    <link rel="icon" href="<%= request.getContextPath() + "/static/images/basketball.png" %>" type="image/x-icon">
</head>
<body>
    <div class="container">
        <h2>Iniciar Sesión</h2>
        <form action="<%= request.getContextPath() %>/controller/logincontroller.jsp" method="post" onsubmit="return validateForm()">
            <!-- Correo -->
            <label for="email">Correo:</label>
            <input type="email" id="email" name="email" required>
            <p id="emailError" class="error"></p>
            
            <!-- Contraseña -->
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" minlength="6" required>
            <p id="passwordError" class="error"></p>
            
            <!-- Recuérdame -->
            <label>
                <input type="checkbox" name="rememberMe"> Recuérdame
            </label><br><br>
            
            <!-- Botón de envío -->
            <button type="submit" class="btn btn-primary">Ingresar</button>
        </form>
        
        <!-- Mensaje de error del servidor -->
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
            <p class="error" role="alert"><%= org.apache.commons.text.StringEscapeUtils.escapeHtml4(error) %></p>
        <%
            }
        %>
        
        <!-- Opciones adicionales -->
        <%-- <p>
            <a href="<%= request.getContextPath() %>/views/forgot-password.jsp" style="color: #007bff;">¿Olvidaste tu contraseña?</a>
        </p> --%>
        <p>
            <a href="<%= request.getContextPath() %>/views/registerview.jsp" style="color: #007bff;">¿No tienes una cuenta? Regístrate</a>
        </p>
    </div>

    <script src="<%= request.getContextPath() %>/static/js/login.js"></script> <!-- Enlace al JS -->
</body>
</html>
