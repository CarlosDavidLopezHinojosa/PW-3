<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.Reservas.ReservaDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modificar Reserva</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css">
    <style>
        .back-button {
            position: absolute;
            top: 10px;
            right: 10px;
        }
    </style>
</head>
<body>
    <a href="<%= request.getContextPath() %>/controller/welcomeclientcontroller.jsp" class="back-button btn btn-secondary">Volver al Menú Principal</a>
    <h1>Modificar Reserva</h1>
    
    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            out.println("<p>" + mensaje + "</p>");
        }
        
        List<ReservaDTO> reservas = (List<ReservaDTO>) request.getAttribute("reservas");
        if (reservas != null) {
            if (reservas.isEmpty()) {
                out.println("<p>El usuario no tiene reservas futuras.</p>");
            } else {
                out.println("<h2>Reservas Futuras:</h2>");
                out.println("<form action='" + request.getContextPath() + "/modificarReserva' method='post'>");
                out.println("<ul>");
                for (ReservaDTO reserva : reservas) {
                    out.println("<li>");
                    out.println("<input type='radio' name='idReserva' value='" + reserva.getIdReserva() + "' required>");
                    out.println("ID: " + reserva.getIdReserva() + ", Fecha y Hora: " + reserva.getDiaYHora() + ", Pista: " + reserva.getIdPista());
                    out.println("</li>");
                }
                out.println("</ul>");
                out.println("<label for='nuevaFecha'>Nueva Fecha y Hora:</label>");
                out.println("<input type='datetime-local' id='nuevaFecha' name='nuevaFecha' required><br><br>");
                out.println("<input type='submit' name='confirmar' value='Modificar Reserva'>");
                out.println("</form>");
            }
        }
    %>
</body>
</html>