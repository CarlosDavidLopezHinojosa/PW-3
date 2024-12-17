// Rutas de los videos
const videos = [
  'static/videos/guy_running_towards_net.mp4',
  'static/videos/man_bouncing_ball.mp4',
  'static/videos/beach_floor.mp4',
]

let currentVideoIndex = 0
const video1 = document.getElementById('video1')
const video2 = document.getElementById('video2')

// Inicializamos el primer video
video1.src = videos[currentVideoIndex]
video1.classList.add('active')

video1.addEventListener('ended', playNextVideo)

// Función para alternar videos
function playNextVideo() {
  currentVideoIndex = (currentVideoIndex + 1) % videos.length

  // Determinar qué video está activo y cuál no
  if (video1.classList.contains('active')) {
    video2.src = videos[currentVideoIndex]
    video2.load()
    video2.play()

    // Transición suave
    video1.classList.remove('active')
    video2.classList.add('active')
  } else {
    video1.src = videos[currentVideoIndex]
    video1.load()
    video1.play()

    // Transición suave
    video2.classList.remove('active')
    video1.classList.add('active')
  }
}
