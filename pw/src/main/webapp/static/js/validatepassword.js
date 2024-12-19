function validatePassword() {
  const newPassword = document.getElementById('newPassword').value
  const confirmPassword = document.getElementById('confirmPassword').value
  let errorMessage = document.getElementById('error-message')

  let passwordAreTheSame = newPassword === confirmPassword
  let passwordLengthIsCorrect = newPassword.length >= 8

  if ((!passwordAreTheSame || !passwordLengthIsCorrect) && !errorMessage) {
    errorMessage = document.createElement('p')
    errorMessage.id = 'error-message'
    errorMessage.className = 'error-message'
    document.getElementById('changePasswordForm').appendChild(errorMessage)
  }

  if (!passwordAreTheSame) {
    errorMessage.innerHTML = 'Las contraseñas no coinciden'
    errorMessage.style.display = 'block'
    return false
  }

  if (!passwordLengthIsCorrect) {
    errorMessage.innerHTML = 'La contraseña debe tener al menos 8 caracteres'
    errorMessage.style.display = 'block'
    return false
  }
  //errorMessage.style.display = 'none'
  return true
}

window.addEventListener('blur', () => {
  const errorMessage = document.getElementById('error-message')
  if (errorMessage) {
    errorMessage.style.display = 'none'
  }
})
