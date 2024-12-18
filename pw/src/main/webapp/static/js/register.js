document
  .getElementById('registerForm')
  .addEventListener('submit', function (event) {
    const password = document.getElementById('password').value
    const confirmPassword = document.getElementById('confirmPassword').value

    if (password !== confirmPassword) {
      event.preventDefault() // Detener el envío del formulario
      alert('Las contraseñas no coinciden.')
      return false
    }

    // Validación adicional del formato del correo si es necesario
    const email = document.getElementById('email').value
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      event.preventDefault()
      alert('Por favor, ingresa un correo válido.')
      return false
    }

    // Validación adicional puede ir aquí
  })
