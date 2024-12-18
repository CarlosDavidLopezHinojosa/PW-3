<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrarse</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/styles.css"> <!-- Enlace al CSS global -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/register.css"> <!-- Enlace al CSS específico -->
</head>
<body>
    <div class="container">
        <h2>Registro</h2>
        <form action="<%= request.getContextPath() %>/controller/registercontroller.jsp" method="post" id="registerForm">
            <label for="name">Nombre:</label>
            <input type="text" id="name" name="name" required>

            <label for="lastName">Apellidos:</label>
            <input type="text" id="lastName" name="lastName" required>

            <label for="email">Correo:</label>
            <input type="email" id="email" name="email" required>

            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required minlength="8">

            <label for="confirmPassword">Confirmar Contraseña:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required minlength="8">

            <label for="fechaNacimiento">Fecha de Nacimiento:</label>
            <input type="date" id="fechaNacimiento" name="fechaNacimiento" required>

            <button type="submit" class="btn">Registrarse</button>
        </form>

        <p id="error" class="error-message">
            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
                    out.println(error);
                }
            %>
        </p>
    </div>
</body>
</html>
