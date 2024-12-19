document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('eliminarReservaForm')
  form.addEventListener('submit', (event) => {
    const checkbox = document.getElementById('confirmDelete')
    if (!checkbox.checked) {
      alert('Debe confirmar que desea eliminar la reserva.')
      event.preventDefault() // Evita el envío del formulario si no se cumple la validación
    }
  })
})
