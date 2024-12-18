<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.InputStream" %>
<%@ page import="web.model.data.common.DBConnection" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reserva tu Cancha - Inicio</title>
    <link rel="stylesheet" href="static/css/styles.css">
</head>
<body>
    <%
        // Recuperar la ruta de configuración
        String file = application.getInitParameter("config");
        InputStream myIO = application.getResourceAsStream(file);
        DBConnection.setConfig(myIO);
    %>

    <!-- Dos videos superpuestos para la transición -->
   <div id="video-container">
        <video id="video1" class="background-video active" autoplay muted>
            <source src="static/videos/man_bouncing_and_dunking.mp4" type="video/mp4">
        </video>
        <video id="video2" class="background-video" muted>
            <source src="static/videos/guy_running_towards_net.mp4" type="video/mp4">
        </video>
        <video id="video3" class="background-video" muted>
            <source src="static/videos/beach_floor.mp4" type="video/mp4">
        </video>
    </div>

    <!-- Contenedor principal -->
    <div class="container">
        <header class="header">
            <h1>Reserva tu Cancha de Baloncesto</h1>
            <p>Inicia sesión o regístrate para gestionar tus reservas.</p>
        </header>
        <div class="actions">
            <a href="views/loginview.jsp" class="btn btn-primary">Iniciar Sesión</a>
            <a href="views/registerview.jsp" class="btn btn-secondary">Registrarse</a>
        </div>
    </div>

    <!-- Script para gestionar la transición de videos -->
    <script src="static/js/video-transition.js"></script>
</body>
</html>
