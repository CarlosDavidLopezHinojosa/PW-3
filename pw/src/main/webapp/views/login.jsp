<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/styles.css"> <!-- Enlace al CSS -->
</head>
<body>
    <div class="container">
        <h2>Iniciar Sesión</h2>
        <form action="<%= request.getContextPath() %>/LoginServlet" method="post">
            <label for="email">Correo:</label>
            <input type="email" id="email" name="email" required><br><br>
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required><br><br>
            <button type="submit" class="btn btn-primary">Ingresar</button>
        </form>
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
                out.println("<p style='color:red;'>" + error + "</p>");
            }
        %>
    </div>
</body>
</html>