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
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/styles.css"> <!-- Estilo opcional -->
    <link rel="icon" href="<%= request.getContextPath() + "/static/images/basketball.png" %>" type="image/x-icon">
</head>
<body>
    <div class="container">
        <h1>¡Hasta pronto!</h1>
        <p>Tu sesión se ha cerrado correctamente.</p>
        <p>¿Deseas iniciar sesión de nuevo?</p>
        <a href="../views/loginview.jsp" class="btn btn-primary">Iniciar Sesión</a>
        <a href="../index.jsp" class="btn btn-primary"> Volver al inicio </a>
    </div>
</body>
</html>
