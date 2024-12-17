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
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/styles.css"> <!-- Enlace al CSS -->
</head>
<body>
    <div class="container">
        <h2>Bienvenido Administrador</h2>
        <p>Has iniciado sesión correctamente.</p>
        <div>
            <a href="<%= request.getContextPath() %>/controller/logout.jsp">Desconectar</a>
            <a href="<%= request.getContextPath() %>/views/updateview.jsp">Modificar Datos</a>
        </div>
        <h3>Listado de Clientes</h3>
        <div style="text-align: center;">
            <table style="margin: 0 auto;">
                <thead>
                    <tr>
                        <th style="padding-right: 20px;">Nombre</th>
                        <th style="padding-right: 20px;">Apellidos</th>
                        <th style="padding-right: 20px;">Fecha de Inscripción</th>
                        <th style="padding-right: 20px;">Número de Reservas Completadas</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<JugadorDTO> clientes = (List<JugadorDTO>) request.getAttribute("clientes");
                        Map<Integer, Integer> reservasCompletadas = (Map<Integer, Integer>) request.getAttribute("reservasCompletadas");
                        if (clientes != null && reservasCompletadas != null) {
                            for (JugadorDTO cliente : clientes) {
                                // Trabajamos directamente con cliente.getId(), que ya es un int
                                int clienteId = cliente.getId();
                    %>
                        <tr>
                            <td style="padding-right: 20px;"><%= cliente.getNombre() %></td>
                            <td style="padding-right: 20px;"><%= cliente.getApellidos() %></td>
                            <td style="padding-right: 20px;"><%= cliente.getFechaInscripcion() %></td>
                            <td style="padding-right: 20px;"><%= reservasCompletadas.getOrDefault(clienteId, 0) %></td>
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
        <h3>Funcionalidades</h3>
        <div class="actions">
            <a href="<%= request.getContextPath() %>/views/crearMaterial.jsp" class="btn btn-primary">Dar material de alta</a>
            <a href="<%= request.getContextPath() %>/views/crearPista.jsp" class="btn btn-primary">Dar pista de alta</a>
            <a href="<%= request.getContextPath() %>/asociarMaterialPista" class="btn btn-primary">Asociar material a pista</a>
            <a href="<%= request.getContextPath() %>/modificarEstadoMaterial" class="btn btn-primary">Modificar estado de material</a>
            <a href="<%= request.getContextPath() %>/modificarEstadoPista" class="btn btn-primary">Modificar estado de pista</a>
            <a href="<%= request.getContextPath() %>/views/eliminarReservas.jsp" class="btn btn-primary">Eliminar reservas no realizadas</a>
        </div>
    </div>
</body>
</html>
