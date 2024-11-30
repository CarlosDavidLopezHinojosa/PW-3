-- 1. Obtener todos los jugadores
SELECT * FROM Jugador;

-- 2. Obtener todas las pistas disponibles
SELECT * FROM Pista WHERE disponible = TRUE;

-- 3. Obtener todos los materiales en buen estado
SELECT * FROM Material WHERE estado = 'DISPONIBLE';

-- 4. Obtener todas las reservas de un jugador específico (por ejemplo, con id 1)
SELECT * FROM Reserva WHERE idUsuario = 1;

-- 5. Obtener todas las reservas para una fecha específica (por ejemplo, 2023-04-01)
SELECT * FROM Reserva WHERE DATE(diaYHora) = '2023-04-01';

-- 6. Obtener todos los bonos de un jugador específico (por ejemplo, con id 2)
SELECT * FROM Bono WHERE idUsuario = 2;

-- 7. Obtener el número de reservas por tipo de pista
SELECT pistaTamano, COUNT(*) AS numReservas FROM Reserva GROUP BY pistaTamano;

-- 8. Obtener el número de jugadores inscritos por mes
SELECT DATE_FORMAT(fechaInscripcion, '%Y-%m') AS mes, COUNT(*) AS numJugadores
FROM Jugador
GROUP BY mes;

-- 9. Obtener el total de ingresos por reservas
SELECT SUM(precio - descuento) AS totalIngresos FROM Reserva;

-- 10. Obtener el material asociado a una pista específica (por ejemplo, con id 1)
SELECT * FROM Material WHERE pistaId = 1;