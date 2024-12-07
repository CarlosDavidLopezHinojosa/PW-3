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
    </div>
</body>
</html>