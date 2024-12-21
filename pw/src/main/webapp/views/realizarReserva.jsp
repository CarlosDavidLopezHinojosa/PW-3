<%@ page import="java.util.List" %>
<%@ page import="web.model.business.DTOs.BonoDTO" %>
<%@ page import="web.model.business.Beans.CustomerBean" %>
<%@ page import="web.model.business.DTOs.PistaDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Realizar Reserva</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/realizarreserva.css">
    <link rel="icon" href="<%= request.getContextPath() + "/static/images/user.png" %>" type="image/x-icon">
    <script src="<%= request.getContextPath() %>/static/js/realizarreserva.js" defer></script>

    <%
        CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
        if(customerBean == null || customerBean.getRol().equals("CLIENTE")) {
            response.sendRedirect(request.getContextPath() + "/views/loginview.jsp");
        }
    %>

</head>
<body>
    <a href="<%= request.getContextPath() %>/controller/welcomeclientcontroller.jsp" class="back-button btn btn-secondary">Volver al Menú Principal</a>
    <form id="reservaForm" action="<%= request.getContextPath() %>/realizarReserva" method="post" onsubmit="return validarFormulario()">
    <h1>Realizar Reserva</h1>

        <label for="tipoReserva">Tipo de Reserva:</label>
        <select id="tipoReserva" name="tipoReserva" required onchange="actualizarCampos(); buscarPistasDisponibles();">
            <option value="Seleccione una opción" selected>Seleccione una opción</option>
            <option value="ADULTOS">ADULTOS</option>
            <option value="FAMILIAR">FAMILIAR</option>
            <option value="INFANTIL">INFANTIL</option>
        </select><br><br>

        <label for="diaYHora">Día y Hora de la Reserva:</label>
        <input type="datetime-local" id="diaYHora" name="diaYHora" required onchange="buscarPistasDisponibles()"><br><br>

        <label for="esBono">¿Es de Bono?:</label>
        <input type="checkbox" id="esBono" name="esBono" value="true" onchange="toggleBonoFields()"><br><br>

        <div id="bonoFields" style="display:none;">
            <label for="idBono">Seleccione un Bono:</label>
            <select id="idBono" name="idBono" onchange="buscarPistasDisponibles()">
                <option value="">Seleccione un bono</option>
            </select><br>
        </div>
        <br>
        <label for="duracion">Duración (minutos):</label>
        <input type="number" id="duracion" name="duracion" required onchange="buscarPistasDisponibles()"><br><br>

        <label for="numAdultos" id="numAdultosLabel" style="display:none;">Número de Adultos:</label>
        <input type="number" id="numAdultos" name="numAdultos" style="display:none;"><br><br>

        <label for="numNinos" id="numNinosLabel" style="display:none;">Número de Niños:</label>
        <input type="number" id="numNinos" name="numNinos" style="display:none;"><br><br>

        <label for="pista">Pista:</label>
        <select id="pista" name="pista" required onchange="actualizarPrecio()">
            <option value="">Seleccione una pista</option>
        </select><br><br>

        <input type="hidden" id="pistaTamano" name="pistaTamano">
        <input type="hidden" id="precio" name="precio">

        <input type="submit" value="Realizar Reserva">
    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            out.println("<p class='error-message'>" + mensaje + "</p>");
        }
    %>
    </form>

            <div id="hiddenPath" style="font-size: 0.1px; color: white;">
                <%= request.getContextPath() %>
            </div>
</body>
</html>