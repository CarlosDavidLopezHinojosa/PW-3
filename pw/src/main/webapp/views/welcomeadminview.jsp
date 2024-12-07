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
        <table>
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
                %>
                    <tr>
                        <td><%= cliente.getNombre() %></td>
                        <td><%= cliente.getApellidos() %></td>
                        <td><%= cliente.getFechaInscripcion() %></td>
                        <td><%= reservasCompletadas.get(cliente.getId()) %></td>
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
</body>
</html>