<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Material</title>
    <link rel="icon" href="<%= request.getContextPath() + "/static/images/admin.png" %>" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/crearmaterial.css">
</head>
<body class="background">
    <a href="<%= request.getContextPath() %>/controller/welcomeadmincontroller.jsp" class="btn btn-secondary back-button">Volver al Menú Principal</a>

    <div class="container">
        <h1>Crear Material</h1>
        <form id="materialForm" action="<%= request.getContextPath() %>/crearMaterial" method="post">
            <label for="tipoMaterial">Tipo de Material:</label>
            <select id="tipoMaterial" name="tipoMaterial" class="input-field" required>
                <option value="PELOTAS">PELOTAS</option>
                <option value="CANASTAS">CANASTAS</option>
                <option value="CONOS">CONOS</option>
            </select>

            <label for="usoExterior">¿Uso Exterior?:</label>
            <input type="checkbox" id="usoExterior" name="usoExterior" value="true" class="checkbox">

            <label for="estadoMaterial">Estado del Material:</label>
            <select id="estadoMaterial" name="estadoMaterial" class="input-field" required>
                <option value="DISPONIBLE">DISPONIBLE</option>
                <option value="RESERVADO">RESERVADO</option>
                <option value="MAL_ESTADO">MAL_ESTADO</option>
            </select>

            <input type="submit" value="Crear Material" class="btn btn-primary">
        </form>

        <% 
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje != null) {
        %>
            <div class="success-message"><%= mensaje %></div>
        <% 
            }
        %>
    </div>
</body>
</html>
