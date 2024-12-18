// Variables de control
let currentVideoIndex = 0
const videos = [
  document.getElementById('video1'),
  document.getElementById('video2'),
  document.getElementById('video3'),
]
const videoChangeInterval = [14000, 11000, 12000] // Duración de cada video
let transitionDuration = 500 // Duración de la transición en ms

// Función para inicializar el primer video
function initializeVideo() {
  videos[currentVideoIndex].classList.add('active')
  videos[currentVideoIndex].play()

  // Comenzar la transición cíclica entre videos
  setTimeout(playNextVideo, videoChangeInterval[currentVideoIndex])
}

// Función para reproducir el siguiente video
function playNextVideo() {
  // Video actual
  const currentVideo = videos[currentVideoIndex]

  // Calcular el siguiente índice del video
  currentVideoIndex = (currentVideoIndex + 1) % videos.length
  const nextVideo = videos[currentVideoIndex]

  // Iniciar transición: apagar el video actual
  currentVideo.classList.remove('active')
  setTimeout(() => {
    currentVideo.pause()
    currentVideo.currentTime = 0 // Reiniciar video actual

    // Encender el siguiente video
    nextVideo.classList.add('active')
    nextVideo.play()

    // Programar la próxima transición
    setTimeout(playNextVideo, videoChangeInterval[currentVideoIndex])
  }, transitionDuration) // Esperar a que termine la transición CSS
}

// Inicializar el video
initializeVideo()
