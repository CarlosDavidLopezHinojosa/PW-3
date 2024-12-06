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
SELECT * FROM Bono WHERE idUser = 2;

-- 7. Obtener el número de reservas por tipo de pista
SELECT pistaTamano, COUNT(*) AS numReservas FROM Reserva GROUP BY pistaTamano;

-- 8. Obtener el número de jugadores inscritos por mes
SELECT DATE_FORMAT(fechaInscripcion, '%Y-%m') AS mes, COUNT(*) AS numJugadores
FROM Jugador
GROUP BY mes;

-- 9. Obtener el total de ingresos por reservas
SELECT SUM(precio - descuento) AS totalIngresos FROM Reserva;

-- 10. Obtener el material asociado a una pista específica (por ejemplo, con id 1)
SELECT * FROM Material WHERE idPista = 1;

-- 11. Obtener todos los usuarios con rol de Admin
SELECT * FROM Jugador WHERE rol = 'Admin';

-- 12. Obtener el número de usuarios con rol de Admin
SELECT COUNT(*) AS numAdmins FROM Jugador WHERE rol = 'Admin';

-- 13. Obtener todas las reservas realizadas por usuarios con rol de Admin
SELECT r.* FROM Reserva r
JOIN Jugador j ON r.idUsuario = j.id
WHERE j.rol = 'Admin';

-- 14. Obtener todos los bonos de usuarios con rol de Admin
SELECT b.* FROM Bono b
JOIN Jugador j ON b.idUser = j.id
WHERE j.rol = 'Admin';

-- 15. Obtener el total de ingresos por reservas realizadas por usuarios con rol de Admin
SELECT SUM(r.precio - r.descuento) AS totalIngresosAdmin FROM Reserva r
JOIN Jugador j ON r.idUsuario = j.id
WHERE j.rol = 'Admin';

