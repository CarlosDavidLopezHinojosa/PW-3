<%@ page import="web.model.business.DTOs.JugadorDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenido Administrador</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/admin.css">
    <link rel="icon" href="<%= request.getContextPath() + "/static/images/admin.png" %>" type="image/x-icon">
</head>
<body>
    <div class="main-container">
        <!-- Encabezado -->
        <header class="header">
            <h1>Bienvenido Administrador</h1>
            <p>Has iniciado sesión correctamente.</p>
            <div class="header-links">
                <a href="<%= request.getContextPath() %>/controller/logout.jsp" class="btn btn-danger">Cerrar Sesión</a>
                <a href="<%= request.getContextPath() %>/views/updateview.jsp" class="btn btn-secondary">Modificar Datos</a>
            </div>
        </header>

        <!-- Listado de Clientes -->
        <section class="section">
            <h2>Listado de Clientes</h2>
            <div class="table-container">
                <table class="styled-table">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Apellidos</th>
                            <th>Fecha de Inscripción</th>
                            <th>Número de Reservas Completadas</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<JugadorDTO> clientes = (List<JugadorDTO>) request.getAttribute("clientes");
                            Map<Integer, Integer> reservasCompletadas = (Map<Integer, Integer>) request.getAttribute("reservasCompletadas");
                            if (clientes != null && reservasCompletadas != null) {
                                for (JugadorDTO cliente : clientes) {
                                    int clienteId = cliente.getId();
                        %>
                            <tr>
                                <td><%= cliente.getNombre() %></td>
                                <td><%= cliente.getApellidos() %></td>
                                <td><%= cliente.getFechaInscripcion() %></td>
                                <td><%= reservasCompletadas.getOrDefault(clienteId, 0) %></td>
                            </tr>
                        <%
                                }
                            } else {
                        %>
                            <tr>
                                <td colspan="4">No hay datos disponibles.</td>
                            </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </section>

        <!-- Funcionalidades -->
        <section class="section">
            <h2>Funcionalidades</h2>
            <div class="actions-grid">
                <a href="<%= request.getContextPath() %>/views/crearMaterial.jsp" class="btn btn-primary">Dar material de alta</a>
                <a href="<%= request.getContextPath() %>/views/crearPista.jsp" class="btn btn-primary">Dar pista de alta</a>
                <a href="<%= request.getContextPath() %>/asociarMaterialPista" class="btn btn-primary">Asociar material a pista</a>
                <a href="<%= request.getContextPath() %>/modificarEstadoMaterial" class="btn btn-primary">Modificar estado de material</a>
                <a href="<%= request.getContextPath() %>/modificarEstadoPista" class="btn btn-primary">Modificar estado de pista</a>
                <a href="<%= request.getContextPath() %>/eliminarReservaAdmin" class="btn btn-primary">Eliminar reservas no realizadas</a>
            </div>
        </section>
    </div>
</body>
</html>
