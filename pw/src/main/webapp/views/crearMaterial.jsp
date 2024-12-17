<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Crear Material</title>
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
    <a href="<%= request.getContextPath() %>/controller/welcomeadmincontroller.jsp" class="back-button btn btn-secondary">Volver al Menú Principal</a>
    <h1>Crear Material</h1>
    <form id="materialForm" action="<%= request.getContextPath() %>/crearMaterial" method="post">
        <label for="tipoMaterial">Tipo de Material:</label>
        <select id="tipoMaterial" name="tipoMaterial" required>
            <option value="PELOTAS">PELOTAS</option>
            <option value="CANASTAS">CANASTAS</option>
            <option value="CONOS">CONOS</option>
        </select><br><br>

        <label for="usoExterior">¿Uso Exterior?:</label>
        <input type="checkbox" id="usoExterior" name="usoExterior" value="true"><br><br>

        <label for="estadoMaterial">Estado del Material:</label>
        <select id="estadoMaterial" name="estadoMaterial" required>
            <option value="DISPONIBLE">DISPONIBLE</option>
            <option value="RESERVADO">RESERVADO</option>
            <option value="MAL_ESTADO">MAL_ESTADO</option>
        </select><br><br>

        <input type="submit" value="Crear Material">
    </form>

    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            out.println("<p style='color:red;'>" + mensaje + "</p>");
        }
    %>
</body>
</html>