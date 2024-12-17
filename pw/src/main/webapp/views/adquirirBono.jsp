<%@ page import="web.model.business.Beans.CustomerBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Adquirir Bono</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css">
    <style>
        .back-button {
            position: absolute;
            top: 10px;
            right: 10px;
        }
    </style>
    <script>
        function actualizarTamanoPista() {
            var tipoReserva = document.getElementById("tipoReserva").value;
            var pistaTamano = document.getElementById("pistaTamano");
            pistaTamano.innerHTML = ""; // Limpiar opciones

            if (tipoReserva === "ADULTOS") {
                pistaTamano.innerHTML += "<option value='ADULTOS'>ADULTOS</option>";
            } else if (tipoReserva === "FAMILIAR") {
                pistaTamano.innerHTML += "<option value='VS3'>3VS3</option>";
                pistaTamano.innerHTML += "<option value='MINIBASKET'>MINIBASKET</option>";
            } else if (tipoReserva === "INFANTIL") {
                pistaTamano.innerHTML += "<option value='MINIBASKET'>MINIBASKET</option>";
            }

            pistaTamano.disabled = false; // Habilitar selección de tamaño de pista
        }

        window.onload = function() {
            document.getElementById("tipoReserva").addEventListener("change", actualizarTamanoPista);
            document.getElementById("pistaTamano").disabled = true; // Deshabilitar inicialmente
        }
    </script>
</head>
<body>
    <a href="<%= request.getContextPath() %>/controller/welcomeclientcontroller.jsp" class="back-button btn btn-secondary">Volver al Menú Principal</a>
    <h1>Adquirir Bono</h1>
    <form action="<%= request.getContextPath() %>/adquirirBono" method="post">
        <label for="sesiones">Número de Sesiones:</label>
        <input type="number" id="sesiones" name="sesiones" required><br><br>
        
        <label for="tipoReserva">Tipo de Reserva:</label>
        <select id="tipoReserva" name="tipoReserva" required>
            <option value="">Seleccione un tipo de reserva</option>
            <option value="ADULTOS">ADULTOS</option>
            <option value="FAMILIAR">FAMILIAR</option>
            <option value="INFANTIL">INFANTIL</option>
        </select><br><br>
        
        <label for="pistaTamano">Tamaño de la Pista:</label>
        <select id="pistaTamano" name="pistaTamano" required>
            <!-- Las opciones se actualizarán dinámicamente -->
        </select><br><br>
        
        <input type="submit" id="submitBtn" value="Adquirir">
    </form>

    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            out.println("<p>" + mensaje + "</p>");
        }
    %>
</body>
</html>
