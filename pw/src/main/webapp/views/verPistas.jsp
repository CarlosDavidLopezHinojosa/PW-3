<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.PistaDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ver Pistas</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css">
    <style>
        .back-button {
            position: absolute;
            top: 10px;
            right: 10px;
        }
    </style>
    <script>
        function mostrarDetalles(id) {
            var detalles = document.getElementById("detalles-" + id);
            if (detalles.style.display === "none") {
                detalles.style.display = "block";
            } else {
                detalles.style.display = "none";
            }
        }
    </script>
</head>
<body>
    <a href="<%= request.getContextPath() %>/controller/welcomeclientcontroller.jsp" class="back-button btn btn-secondary">Volver al Menú Principal</a>
    <h1>Ver Pistas</h1>
    
    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            out.println("<p>" + mensaje + "</p>");
        }
        
        List<PistaDTO> pistas = (List<PistaDTO>) request.getAttribute("pistas");
        if (pistas != null && !pistas.isEmpty()) {
            for (PistaDTO pista : pistas) {
                out.println("<button onclick='mostrarDetalles(" + pista.getId() + ")'>Nombre: " + pista.getNombre() + ", ID: " + pista.getId() + "</button>");
                out.println("<div id='detalles-" + pista.getId() + "' style='display:none;'>");
                out.println("<p>ID: " + pista.getId() + "</p>");
                out.println("<p>Nombre: " + pista.getNombre() + "</p>");
                out.println("<p>Disponible: " + (pista.isDisponible() ? "Sí" : "No") + "</p>");
                out.println("<p>Exterior: " + (pista.isEsExterior() ? "Sí" : "No") + "</p>");
                out.println("<p>Tamaño: " + pista.getTamano() + "</p>");
                out.println("<p>Máximo de Jugadores: " + pista.getMaxJugadores() + "</p>");
                out.println("</div>");
            }
        } else {
            out.println("<p>No hay pistas disponibles.</p>");
        }
    %>
</body>
</html>