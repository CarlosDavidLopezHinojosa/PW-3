function toggleBonoFields() {
  var bonoFields = document.getElementById('bonoFields')
  if (document.getElementById('esBono').checked) {
    bonoFields.style.display = 'block'
    buscarBonosDisponibles()
  } else {
    bonoFields.style.display = 'none'
    document.getElementById('idBono').value = ''
    buscarPistasDisponibles()
  }
}

function actualizarCampos() {
  var tipoReserva = document.getElementById('tipoReserva').value
  var numAdultosField = document.getElementById('numAdultos')
  var numAdultosLabel = document.getElementById('numAdultosLabel')
  var numNinosField = document.getElementById('numNinos')
  var numNinosLabel = document.getElementById('numNinosLabel')

  if (tipoReserva === 'ADULTOS') {
    numAdultosField.style.display = 'block'
    numAdultosLabel.style.display = 'block'
    numNinosField.style.display = 'none'
    numNinosLabel.style.display = 'none'
    numNinosField.value = 0
  } else if (tipoReserva === 'INFANTIL') {
    numAdultosField.style.display = 'none'
    numAdultosLabel.style.display = 'none'
    numAdultosField.value = 0
    numNinosField.style.display = 'block'
    numNinosLabel.style.display = 'block'
  } else if (tipoReserva === 'FAMILIAR') {
    numAdultosField.style.display = 'block'
    numAdultosLabel.style.display = 'block'
    numNinosField.style.display = 'block'
    numNinosLabel.style.display = 'block'
  } else {
    numAdultosField.style.display = 'none'
    numAdultosLabel.style.display = 'none'
    numNinosField.style.display = 'none'
    numNinosLabel.style.display = 'none'
  }
}

function buscarPistasDisponibles() {
  var tipoReserva = document.getElementById('tipoReserva').value
  var diaYHora = document.getElementById('diaYHora').value
  var duracion = document.getElementById('duracion').value
  var idBono = document.getElementById('idBono').value
  var esBono = document.getElementById('esBono').checked

  if (tipoReserva && diaYHora && duracion) {
    var xhr = new XMLHttpRequest()
    var url =
      '/realizarReserva?action=buscarPistasDisponibles&tipoReserva=' +
      tipoReserva +
      '&diaYHora=' +
      diaYHora +
      '&duracion=' +
      duracion
    if (esBono && idBono) {
      url += '&idBono=' + idBono
    }
    xhr.open('GET', url, true)
    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status === 200) {
        var pistaSelect = document.getElementById('pista')
        pistaSelect.innerHTML = xhr.responseText
      }
    }
    xhr.send()
  }
}

function buscarBonosDisponibles() {
  var tipoReserva = document.getElementById('tipoReserva').value

  if (tipoReserva) {
    var xhr = new XMLHttpRequest()
    xhr.open(
      'GET',
      '/realizarReserva?action=buscarBonosDisponibles&tipoReserva=' +
        tipoReserva,
      true
    )
    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status === 200) {
        var bonoSelect = document.getElementById('idBono')
        bonoSelect.innerHTML = xhr.responseText
      }
    }
    xhr.send()
  }
}

function actualizarPrecio() {
  var duracion = document.getElementById('duracion').value
  var pistaTamano = document
    .getElementById('pista')
    .selectedOptions[0].getAttribute('data-tamano')
  var precio = 0

  if (pistaTamano === 'MINIBASKET') {
    precio = 0.1 * duracion
  } else if (pistaTamano === 'VS3') {
    precio = 0.13 * duracion
  } else if (pistaTamano === 'ADULTOS') {
    precio = 0.15 * duracion
  }

  document.getElementById('pistaTamano').value = pistaTamano
  document.getElementById('precio').value = precio.toFixed(2)
}

function validarFormulario() {
  var numAdultos = parseInt(document.getElementById('numAdultos').value) || 0
  var numNinos = parseInt(document.getElementById('numNinos').value) || 0
  var maxJugadores = parseInt(
    document
      .getElementById('pista')
      .selectedOptions[0].getAttribute('data-max-jugadores')
  )

  if (numAdultos + numNinos > maxJugadores) {
    alert(
      'El número total de participantes supera el límite permitido para esta pista.'
    )
    return false
  }

  return true
}

// Inicializar campos al cargar la página
document.addEventListener('DOMContentLoaded', function () {
  document.getElementById('esBono').addEventListener('change', toggleBonoFields)
  document
    .getElementById('tipoReserva')
    .addEventListener('change', actualizarCampos)
})
