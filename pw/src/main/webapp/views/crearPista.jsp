<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Pista</title>
    <link rel="icon" href="<%= request.getContextPath() + "/static/images/admin.png" %>" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/crearpista.css">
</head>
<body>
    <!-- Botón de regreso -->
    <a href="<%= request.getContextPath() %>/controller/welcomeadmincontroller.jsp" class="back-button btn btn-secondary">Volver al Menú Principal</a>

    <!-- Contenedor del formulario -->
    <div class="form-container">
        <h1>Crear Pista</h1>
        <form id="pistaForm" action="<%= request.getContextPath() %>/crearPista" method="post">
            <!-- Nombre de la Pista -->
            <label for="nombrePista">Nombre de la Pista:</label>
            <input type="text" id="nombrePista" name="nombrePista" required>

            <!-- Disponible -->
            <div class="checkbox-group">
                <label for="disponible">¿Disponible?</label>
                <input type="checkbox" id="disponible" name="disponible" value="true">
            </div>

            <!-- Es Exterior -->
            <div class="checkbox-group">
                <label for="esExterior">¿Es Exterior?</label>
                <input type="checkbox" id="esExterior" name="esExterior" value="true">
            </div>


            <!-- Tamaño de la Pista -->
            <label for="tamanoPista">Tamaño de la Pista:</label>
            <select id="tamanoPista" name="tamanoPista" required>
                <option value="MINIBASKET">MINIBASKET</option>
                <option value="ADULTOS">ADULTOS</option>
                <option value="VS3">VS3</option>
            </select>

            <!-- Número Máximo de Jugadores -->
            <label for="maxJugadores">Número Máximo de Jugadores:</label>
            <input type="number" id="maxJugadores" name="maxJugadores" required>

            <!-- Botón de Enviar -->
            <input type="submit" value="Crear Pista">
        </form>

        <!-- Mensajes -->
        <%
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje != null) {
                out.println("<div class='success-message'>" + mensaje + "</div>");
            }
        %>
    </div>
</body>
</html>
