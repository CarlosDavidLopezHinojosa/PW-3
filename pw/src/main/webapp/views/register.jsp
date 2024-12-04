<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrarse</title>
    <link rel="stylesheet" href="../static/css/styles.css"> <!-- Enlace al CSS -->
</head>
<body>
    <div class="container">
        <h2>Registro</h2>
        <form action="RegisterServlet" method="post">
            <label for="name">Nombre:</label>
            <input type="text" id="name" name="name" required><br><br>
            <label for="email">Correo:</label>
            <input type="email" id="email" name="email" required><br><br>
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required><br><br>
            <button type="submit" class="btn btn-secondary">Registrarse</button>
        </form>
    </div>
</body>
</html>
