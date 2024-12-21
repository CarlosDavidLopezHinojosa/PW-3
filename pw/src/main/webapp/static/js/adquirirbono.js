function actualizarTamanoPista() {
  var tipoReserva = document.getElementById('tipoReserva').value
  var pistaTamano = document.getElementById('pistaTamano')
  pistaTamano.innerHTML = '' // Limpiar opciones

  if (tipoReserva === 'ADULTOS') {
    pistaTamano.innerHTML += "<option value='ADULTOS'>ADULTOS</option>"
  } else if (tipoReserva === 'FAMILIAR') {
    pistaTamano.innerHTML += "<option value='VS3'>3VS3</option>"
    pistaTamano.innerHTML += "<option value='MINIBASKET'>MINIBASKET</option>"
  } else if (tipoReserva === 'INFANTIL') {
    pistaTamano.innerHTML += "<option value='MINIBASKET'>MINIBASKET</option>"
  }

  pistaTamano.disabled = false // Habilitar selección de tamaño de pista
}

window.onload = function () {
  document
    .getElementById('tipoReserva')
    .addEventListener('change', actualizarTamanoPista)
  document.getElementById('pistaTamano').disabled = true // Deshabilitar inicialmente
}
