<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Crear Pista</title>
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
    <h1>Crear Pista</h1>
    <form id="pistaForm" action="<%= request.getContextPath() %>/crearPista" method="post">
        <label for="nombrePista">Nombre de la Pista:</label>
        <input type="text" id="nombrePista" name="nombrePista" required><br><br>

        <label for="disponible">¿Disponible?:</label>
        <input type="checkbox" id="disponible" name="disponible" value="true"><br><br>

        <label for="esExterior">¿Es Exterior?:</label>
        <input type="checkbox" id="esExterior" name="esExterior" value="true"><br><br>

        <label for="tamanoPista">Tamaño de la Pista:</label>
        <select id="tamanoPista" name="tamanoPista" required>
            <option value="MINIBASKET">MINIBASKET</option>
            <option value="ADULTOS">ADULTOS</option>
            <option value="VS3">VS3</option>
        </select><br><br>

        <label for="maxJugadores">Número Máximo de Jugadores:</label>
        <input type="number" id="maxJugadores" name="maxJugadores" required><br><br>

        <input type="submit" value="Crear Pista">
    </form>

    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            out.println("<p style='color:red;'>" + mensaje + "</p>");
        }
    %>
</body>
</html>