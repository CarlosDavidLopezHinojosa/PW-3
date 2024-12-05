<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrarse</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/styles.css"> <!-- Enlace al CSS -->
    <script src="<%= request.getContextPath() %>/static/js/register.js"></script>
</head>
<body>
    <div class="container">
        <h2>Registro</h2>
        <form action="<%= request.getContextPath() %>/Registro" method="post" onsubmit="return validarFormulario()">
            <label for="name">Nombre:</label>
            <input type="text" id="name" name="name" required><br><br>
            <label for="lastName">Apellidos:</label>
            <input type="text" id="lastName" name="lastName" required><br><br>
            <label for="email">Correo:</label>
            <input type="email" id="email" name="email" required><br><br>
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required><br><br>
            <label for="fechaNacimiento">Fecha de Nacimiento:</label>
            <input type="date" id="fechaNacimiento" name="fechaNacimiento" required><br><br>
            <button type="submit" class="btn btn-secondary">Registrarse</button>
        </form>
        <p id="error" style="color:red;"></p>
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
                out.println("<p style='color:red;'>" + error + "</p>");
            }
        %>
    </div>
</body>
</html>