<%@ page import="web.model.business.Beans.CustomerBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenido</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/styles.css"> <!-- Enlace al CSS -->
</head>
<body>
    <div class="container">
        <h2>
            Bienvenido, 
            <%
                CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
                if (customerBean != null && customerBean.getNombre() != null) {
            %>
                <%= customerBean.getNombre() %>!
            <%
                } else {
            %>
                Invitado!
            <%
                }
            %>
        </h2>
        <p>Has iniciado sesión correctamente.</p>
        <div>
            <a href="<%= request.getContextPath() %>/controller/logout.jsp">Desconectar</a>
            <a href="<%= request.getContextPath() %>/views/updateview.jsp">Modificar Datos</a>
        </div>
        <p>
            Fecha actual: 
            <%= request.getAttribute("fechaActual") != null ? request.getAttribute("fechaActual") : "No disponible" %>
        </p>
        <p>
            Fecha de inscripción: 
            <%= request.getAttribute("fechaInscripcion") != null ? request.getAttribute("fechaInscripcion") : "No disponible" %>
        </p>

        <%
            String proximaReserva = (String) request.getAttribute("proximaReserva");
        %>
        <p>
            Fecha de la próxima reserva: 
            <%= proximaReserva != null ? proximaReserva : "No tienes reservas próximas." %>
        </p>
        
        <h3>Funcionalidades</h3>
        <div class="actions">
            <a href="<%= request.getContextPath() %>/views/realizarReserva.jsp" class="btn btn-primary">Realizar Reserva</a>
            <a href="<%= request.getContextPath() %>/views/modificarReserva.jsp" class="btn btn-primary">Modificar Reserva</a>
            <a href="<%= request.getContextPath() %>/views/cancelarReserva.jsp" class="btn btn-primary">Cancelar Reserva</a>
            <a href="<%= request.getContextPath() %>/views/verReservas.jsp" class="btn btn-primary">Consultar Reservas</a>
            <a href="<%= request.getContextPath() %>/views/adquirirBono.jsp" class="btn btn-primary">Adquirir Bono</a>
            <a href="<%= request.getContextPath() %>/views/buscarPistaDisponible.jsp" class="btn btn-primary">Buscar Pista Disponible</a>
            <a href="<%= request.getContextPath() %>/views/jugadores.jsp" class="btn btn-primary">Ver Jugadores</a>
            <a href="<%= request.getContextPath() %>/views/verMateriales.jsp" class="btn btn-primary">Ver Materiales</a>
            <a href="<%= request.getContextPath() %>/views/verBonos.jsp" class="btn btn-primary">Ver Bonos</a>
            <a href="<%= request.getContextPath() %>/views/verPistas.jsp" class="btn btn-primary">Ver Pistas</a>
        </div>
    </div>
</body>
</html>
