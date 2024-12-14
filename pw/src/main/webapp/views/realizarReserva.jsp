<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Realizar Reserva</title>
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
    <h1>Realizar Reserva</h1>
    <form id="reservaForm" action="<%= request.getContextPath() %>/realizarReserva" method="post" onsubmit="return validarFormulario()">
        <label for="tipoReserva">Tipo de Reserva:</label>
        <select id="tipoReserva" name="tipoReserva" required onchange="actualizarCampos()">
            <option value="Seleccione una opción" selected>Seleccione una opción</option>
            <option value="ADULTOS">ADULTOS</option>
            <option value="FAMILIAR">FAMILIAR</option>
            <option value="INFANTIL">INFANTIL</option>
        </select><br><br>

        <label for="diaYHora">Día y Hora de la Reserva:</label>
        <input type="datetime-local" id="diaYHora" name="diaYHora" required><br><br>

        <label for="esBono">¿Es de Bono?:</label>
        <input type="checkbox" id="esBono" name="esBono" value="true"><br><br>

        <div id="bonoFields" style="display:none;">
            <label for="idBono">ID del Bono:</label>
            <input type="number" id="idBono" name="idBono"><br><br>

            <label for="nSesionBono">Número de Sesión del Bono:</label>
            <input type="number" id="nSesionBono" name="nSesionBono"><br><br>
        </div>

        <label for="duracion">Duración (minutos):</label>
        <input type="number" id="duracion" name="duracion" required><br><br>

        <label for="idPista">ID de la Pista:</label>
        <input type="number" id="idPista" name="idPista" required><br><br>

        <label for="precio">Precio:</label>
        <input type="number" step="0.01" id="precio" name="precio" required><br><br>

        <div id="numAdultosField">
            <label for="numAdultos">Número de Adultos:</label>
            <input type="number" id="numAdultos" name="numAdultos" required><br><br>
        </div>

        <div id="numNinosField">
            <label for="numNinos">Número de Niños:</label>
            <input type="number" id="numNinos" name="numNinos" value="0" required><br><br>
        </div>

        <div id="pistaTamanoField">
            <label for="pistaTamano">Tamaño de la Pista:</label>
            <select id="pistaTamano" name="pistaTamano">
                <option value="MINIBASKET">MINIBASKET</option>
                <option value="3VS3">3VS3</option>
                <option value="ADULTOS">ADULTOS</option>
            </select><br><br>
        </div>

        <label for="usarCodigoDescuento">¿Usar Código de Descuento?:</label>
        <input type="checkbox" id="usarCodigoDescuento" name="usarCodigoDescuento" onchange="toggleCodigoDescuento()"><br><br>

        <div id="codigoDescuentoField" style="display:none;">
            <label for="codigoDescuento">Código de Descuento:</label>
            <input type="text" id="codigoDescuento" name="codigoDescuento"><br><br>
        </div>

        <input type="submit" value="Realizar Reserva">
    </form>

    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            out.println("<p style='color:red;'>" + mensaje + "</p>");
        }
    %>

    <script>
        document.getElementById("esBono").addEventListener("change", function() {
            var bonoFields = document.getElementById("bonoFields");
            if (this.checked) {
                bonoFields.style.display = "block";
            } else {
                bonoFields.style.display = "none";
            }
        });

        function actualizarCampos() {
            var tipoReserva = document.getElementById("tipoReserva").value;
            var numAdultosField = document.getElementById("numAdultosField");
            var numNinosField = document.getElementById("numNinosField");
            var pistaTamanoField = document.getElementById("pistaTamanoField");
            var pistaTamanoSelect = document.getElementById("pistaTamano");

            if (tipoReserva === "ADULTOS") {
                numAdultosField.style.display = "block";
                numNinosField.style.display = "none";
                numNinosField.value = 0;
                pistaTamanoField.style.display = "none";
                pistaTamanoSelect.value = "ADULTOS";
            } else if (tipoReserva === "INFANTIL") {
                numAdultosField.style.display = "none";
                numAdultosField.value = 0;
                numNinosField.style.display = "block";
                pistaTamanoField.style.display = "none";
                pistaTamanoSelect.value = "MINIBASKET";
            } else if (tipoReserva === "FAMILIAR") {
                numAdultosField.style.display = "block";
                numNinosField.style.display = "block";
                pistaTamanoField.style.display = "block";
                // Limpiar las opciones de tamaño de pista
                pistaTamanoSelect.innerHTML = "";
                // Añadir opciones válidas para FAMILIAR
                var option1 = document.createElement("option");
                option1.value = "MINIBASKET";
                option1.text = "MINIBASKET";
                pistaTamanoSelect.add(option1);
                var option2 = document.createElement("option");
                option2.value = "3VS3";
                option2.text = "3VS3";
                pistaTamanoSelect.add(option2);
            }
        }

        function toggleCodigoDescuento() {
            var usarCodigoDescuento = document.getElementById("usarCodigoDescuento").checked;
            var codigoDescuentoField = document.getElementById("codigoDescuentoField");
            if (usarCodigoDescuento) {
                codigoDescuentoField.style.display = "block";
            } else {
                codigoDescuentoField.style.display = "none";
                document.getElementById("codigoDescuento").value = "";
            }
        }

        function validarFormulario() {
            var usarCodigoDescuento = document.getElementById("usarCodigoDescuento").checked;
            var codigoDescuento = document.getElementById("codigoDescuento").value;

            if (usarCodigoDescuento) {
                if (codigoDescuento === "") {
                    // Tratar como si la casilla estuviese desmarcada
                    usarCodigoDescuento = false;
                } else if (codigoDescuento !== "DESCUENTO") {
                    alert("El código de descuento no es correcto.");
                    return false;
                } else {
                    alert("Código de descuento introducido correctamente.");
                }
            }

            return true;
        }       

        // Inicializar campos al cargar la página
        actualizarCampos();
    </script>
</body>
</html>