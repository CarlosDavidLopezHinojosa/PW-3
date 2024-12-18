document.addEventListener('DOMContentLoaded', (event) => {
  // Cargar el correo electrónico guardado si existe
  const savedEmail = getCookie('email')
  if (savedEmail) {
    document.getElementById('email').value = savedEmail
    document.getElementById('rememberMe').checked = true
  }
})

function validateForm() {
  let isValid = true

  // Validar correo
  const email = document.getElementById('email')
  const emailError = document.getElementById('emailError')
  emailError.textContent = ''
  if (!email.value.includes('@')) {
    emailError.textContent = 'Por favor, ingrese un correo válido.'
    isValid = false
  }

  // Validar contraseña
  const password = document.getElementById('password')
  const passwordError = document.getElementById('passwordError')
  passwordError.textContent = ''
  if (password.value.length < 6) {
    passwordError.textContent =
      'La contraseña debe tener al menos 6 caracteres.'
    isValid = false
  }

  // Manejar la opción "Recuérdame"
  const rememberMe = document.getElementById('rememberMe')
  if (rememberMe.checked) {
    setCookie('email', email.value, 30) // Guardar el correo por 30 días
  } else {
    setCookie('email', '', -1) // Borrar la cookie si "Recuérdame" no está marcado
  }

  return isValid // Evita el envío del formulario si hay errores
}

function setCookie(name, value, days) {
  const date = new Date()
  date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000)
  const expires = 'expires=' + date.toUTCString()
  document.cookie = name + '=' + value + ';' + expires + ';path=/'
}

function getCookie(name) {
  const nameEQ = name + '='
  const ca = document.cookie.split(';')
  for (let i = 0; i < ca.length; i++) {
    let c = ca[i]
    while (c.charAt(0) == ' ') c = c.substring(1, c.length)
    if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length)
  }
  return null
}