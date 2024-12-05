function validarEdad() {
  const fechaNacimiento = new Date(
    document.getElementById('fechaNacimiento').value
  )
  const hoy = new Date()
  const edad = hoy.getFullYear() - fechaNacimiento.getFullYear()
  const mes = hoy.getMonth() - fechaNacimiento.getMonth()
  const dia = hoy.getDate() - fechaNacimiento.getDate()

  if (mes < 0 || (mes === 0 && dia < 0)) {
    edad--
  }

  if (edad < 18) {
    document.getElementById('error').innerText =
      'Debes ser mayor de edad para registrarte.'
    return false
  }

  return true
}

function validarContrasena() {
  const password = document.getElementById('password').value
  const regex =
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/

  if (!regex.test(password)) {
    document.getElementById('error').innerText =
      'La contraseña debe tener al menos 8 caracteres, incluir una letra mayúscula, una letra minúscula, un número y un carácter especial.'
    return false
  }

  return true
}

function validarFormulario() {
  return validarEdad() && validarContrasena()
}
