<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.BonoDTO" %>
<%@ page import="web.model.business.Beans.CustomerBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ver Bonos</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css">
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
    <h1>Ver Bonos</h1>
    
    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            out.println("<p>" + mensaje + "</p>");
        }
        
        List<BonoDTO> bonos = (List<BonoDTO>) request.getAttribute("bonos");
        if (bonos != null && !bonos.isEmpty()) {
            for (BonoDTO bono : bonos) {
                out.println("<button onclick='mostrarDetalles(" + bono.getId() + ")'>ID: " + bono.getId() + ", Sesiones: " + bono.getSesiones() + ", Tipo: " + bono.getTipoReserva() + "</button>");
                out.println("<div id='detalles-" + bono.getId() + "' style='display:none;'>");
                out.println("<p>Id Bono: " + bono.getId() + "</p>");
                out.println("<p>Numero Sesiones: " + bono.getSesiones() + "</p>");
                out.println("<p>Tipo Reserva: " + bono.getTipoReserva() + "</p>");
                out.println("<p>ID Usuario: " + bono.getIdUser() + "</p>");
                out.println("<p>Tamaño Pista: " + bono.getPistaTamano() + "</p>");
                out.println("</div>");
            }
        } else {
            out.println("<p>No tienes bonos disponibles.</p>");
        }
    %>
</body>
</html>