<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Invalida la sesión
    session.invalidate();
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sesión Finalizada</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/logout.css"> <!-- Estilo en un archivo separado -->
    <link rel="icon" href="<%= request.getContextPath() %>/static/images/basketball.png" type="image/x-icon">
</head>
<body>
    <div class="container">
        <h1><strong>¡Hasta pronto!</strong></h1>
        <p>Tu sesión se ha cerrado correctamente.</p>
        <p>¿Deseas iniciar sesión de nuevo?</p>
        <a href="../views/loginview.jsp" class="btn"><strong>Iniciar Sesión</strong></a>
        <a href="../index.jsp" class="btn"><strong>Volver al inicio</strong></a>
    </div>
</body>
</html>
