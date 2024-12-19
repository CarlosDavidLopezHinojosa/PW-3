<%@ page import="web.model.business.Beans.CustomerBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenido</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/cliente.css">
    <link rel="icon" href="<%= request.getContextPath() + "/static/images/user.png" %>" type="image/x-icon">
</head>
<body>
    <div class="main-container">
        <!-- Encabezado -->
        <header class="header">
            <h1>Bienvenido, 
                <%
                    CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
                    if (customerBean != null && customerBean.getNombre() != null) {
                %>
                    <%= customerBean.getNombre() %>!
                <%
                    } else {
                        response.sendRedirect("../views/loginview.jsp");
                    }
                %>

            </h1>
            <p class="subtitle">Has iniciado sesión correctamente.</p>
        </header>

        <!-- Información del usuario -->
        <section class="user-info">
            <p><strong>Fecha actual:</strong> 
                <%= request.getAttribute("fechaActual") != null ? request.getAttribute("fechaActual") : "No disponible" %>
            </p>
            <p><strong>Fecha de inscripción:</strong> 
                <%= request.getAttribute("fechaInscripcion") != null ? request.getAttribute("fechaInscripcion") : "No disponible" %>
            </p>
            <p><strong>Fecha de la próxima reserva:</strong> 
                <%= request.getAttribute("proximaReserva") != null ? request.getAttribute("proximaReserva") : "No tienes reservas próximas." %>
            </p>
        </section>

        <!-- Opciones principales -->
        <div class="actions-container">
            <h2>Gestión de Reservas</h2>
            <div class="actions-grid">
                <a href="<%= request.getContextPath() %>/views/realizarReserva.jsp" class="btn btn-primary">Realizar Reserva</a>
                <a href="<%= request.getContextPath() %>/modificarReserva" class="btn btn-primary">Modificar Reserva</a>
                <a href="<%= request.getContextPath() %>/cancelarReserva" class="btn btn-primary">Cancelar Reserva</a>
                <a href="<%= request.getContextPath() %>/verReservas" class="btn btn-primary">Consultar Reservas</a>
            </div>

            <h2>Otras Funcionalidades</h2>
            <div class="actions-grid">
                <a href="<%= request.getContextPath() %>/views/adquirirBono.jsp" class="btn btn-secondary">Adquirir Bono</a>
                <a href="<%= request.getContextPath() %>/views/buscarPistaDisponible.jsp" class="btn btn-secondary">Buscar Pista Disponible</a>
                <a href="<%= request.getContextPath() %>/jugadores" class="btn btn-secondary">Ver Jugadores</a>
                <a href="<%= request.getContextPath() %>/verMateriales" class="btn btn-secondary">Ver Materiales</a>
                <a href="<%= request.getContextPath() %>/verBonos" class="btn btn-secondary">Ver Bonos</a>
                <a href="<%= request.getContextPath() %>/verPistas" class="btn btn-secondary">Ver Pistas</a>
            </div>
        </div>

        <!-- Enlaces secundarios -->
        <footer class="footer">
            <a href="<%= request.getContextPath() %>/controller/logout.jsp" class="footer-link">Cerrar Sesión</a>
            <a href="<%= request.getContextPath() %>/views/updateview.jsp" class="footer-link">Modificar Datos</a>
        </footer>
    </div>
</body>
</html>