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
        <select id="tipoReserva" name="tipoReserva" required onchange="actualizarCampos(); buscarPistasDisponibles()">
            <option value="Seleccione una opción" selected>Seleccione una opción</option>
            <option value="ADULTOS">ADULTOS</option>
            <option value="FAMILIAR">FAMILIAR</option>
            <option value="INFANTIL">INFANTIL</option>
        </select><br><br>

        <label for="diaYHora">Día y Hora de la Reserva:</label>
        <input type="datetime-local" id="diaYHora" name="diaYHora" required onchange="buscarPistasDisponibles()"><br><br>

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
            var numAdultosField = document.getElementById("numAdultos");
            var numAdultosLabel = document.getElementById("numAdultosLabel");
            var numNinosField = document.getElementById("numNinos");
            var numNinosLabel = document.getElementById("numNinosLabel");

            if (tipoReserva === "ADULTOS") {
                numAdultosField.style.display = "block";
                numAdultosLabel.style.display = "block";
                numNinosField.style.display = "none";
                numNinosLabel.style.display = "none";
                numNinosField.value = 0;
            } else if (tipoReserva === "INFANTIL") {
                numAdultosField.style.display = "none";
                numAdultosLabel.style.display = "none";
                numAdultosField.value = 0;
                numNinosField.style.display = "block";
                numNinosLabel.style.display = "block";
            } else if (tipoReserva === "FAMILIAR") {
                numAdultosField.style.display = "block";
                numAdultosLabel.style.display = "block";
                numNinosField.style.display = "block";
                numNinosLabel.style.display = "block";
            } else {
                numAdultosField.style.display = "none";
                numAdultosLabel.style.display = "none";
                numNinosField.style.display = "none";
                numNinosLabel.style.display = "none";
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

        function buscarPistasDisponibles() {
            var tipoReserva = document.getElementById("tipoReserva").value;
            var diaYHora = document.getElementById("diaYHora").value;

            if (tipoReserva && diaYHora) {
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "<%= request.getContextPath() %>/realizarReserva?action=buscarPistasDisponibles&tipoReserva=" + tipoReserva + "&diaYHora=" + diaYHora, true);
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var pistaSelect = document.getElementById("pista");
                        pistaSelect.innerHTML = xhr.responseText;
                    }
                };
                xhr.send();
            }
        }

        function actualizarPrecio() {
            var duracion = document.getElementById("duracion").value;
            var pistaTamano = document.getElementById("pista").selectedOptions[0].getAttribute("data-tamano");
            var precio = 0;

            if (pistaTamano === "MINIBASKET") {
                precio = 0.1 * duracion;
            } else if (pistaTamano === "VS3") {
                precio = 0.13 * duracion;
            } else if (pistaTamano === "ADULTOS") {
                precio = 0.15 * duracion;
            }

            document.getElementById("pistaTamano").value = pistaTamano;
            document.getElementById("precio").value = precio.toFixed(2);
        }

        // Inicializar campos al cargar la página
        actualizarCampos();
    </script>
</body>
</html>
