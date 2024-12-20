<%@ page import="web.model.business.Beans.CustomerBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Adquirir Bono</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/styles.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/adquirirbono.css">
</head>
<body>
    <div class="header">
        <a href="<%= request.getContextPath() %>/controller/welcomeclientcontroller.jsp" class="back-button btn btn-secondary">Volver al Menú Principal</a>
    </div>
    <div class="form-container">
        

        <form action="<%= request.getContextPath() %>/adquirirBono" method="post">
            <div class="form-group">
            <h1>Adquirir Bono</h1>
                <label for="tipoReserva">Tipo de Reserva:</label>
                <select id="tipoReserva" name="tipoReserva" required>
                    <option value="">Seleccione el tipo de reserva</option>
                    <option value="ADULTOS">ADULTOS</option>
                    <option value="FAMILIAR">FAMILIAR</option>
                    <option value="INFANTIL">INFANTIL</option>
                </select>
            </div>
            <div class="form-group">
                <label for="pistaTamano">Tamaño de la Pista:</label>
                <select id="pistaTamano" name="pistaTamano" required>
                    <option value="">Seleccione el tamaño de la pista</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Adquirir Bono</button>

        <% 
            String mensaje = (String) request.getAttribute("mensaje");
            String error = (String) request.getAttribute("error");
            if (mensaje != null) {
                out.println("<p class='alert success-info'>" + mensaje + "</p>");
            }

            if (error != null) {
                out.println("<p class='alert alert-info'>" + error + "</p>");
            }
        %>
            <p class="alert alert-info"><%= mensaje %></p>
        <% 
            }
        %>
        </form>


    </div>
    <script src="<%= request.getContextPath() %>/static/js/adquirirbono.js"></script>
</body>
</html>