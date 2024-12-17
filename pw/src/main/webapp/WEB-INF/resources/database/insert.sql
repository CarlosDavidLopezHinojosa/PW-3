-- Inserciones en la tabla Pista
INSERT INTO Pista (nombre, disponible, esExterior, tamano, maxJugadores) VALUES
('Pista 1', TRUE, TRUE, 'ADULTOS', 10),
('Pista 2', TRUE, FALSE, 'MINIBASKET', 6),
('Pista 3', FALSE, TRUE, 'VS3', 8);

-- Inserciones en la tabla Material
INSERT INTO Material (tipo, usoExterior, estado, idPista) VALUES
('PELOTAS', TRUE, 'DISPONIBLE', 1),
('CANASTAS', FALSE, 'RESERVADO', 2),
('CONOS', TRUE, 'MAL_ESTADO', 3);

-- Inserciones en la tabla Jugador
INSERT INTO Jugador (nombre, apellidos, fechaNacimiento, fechaInscripcion, email, password, rol) VALUES
('Juan', 'Pérez', '1990-01-01', '2023-01-01', 'juan.perez@example.com', 'password123', 'CLIENTE'),
('María', 'García', '1985-05-05', '2023-02-01', 'maria.garcia@example.com', 'password123', 'CLIENTE'),
('Carlos', 'López', '2000-10-10', '2023-03-01', 'carlos.lopez@example.com', 'password123', 'ADMIN');

-- Inserciones en la tabla Bono
INSERT INTO Bono (sesiones, idUser, tipoReserva, tamanoPista) VALUES
(10, 1, 'ADULTOS', 'ADULTOS'),
(5, 2, 'FAMILIAR', 'MINIBASKET'),
(8, 3, 'INFANTIL', 'VS3');

-- Inserciones en la tabla Reserva
INSERT INTO Reserva (idUsuario, diaYHora, idBono, nSesionBono, duracion, idPista, precio, descuento, pistaTamano, tipo, numAdultos, numNinos) VALUES
(1, '2026-04-01 10:00:00', 1, 1, 60, 1, 20.00, 5.00, 'ADULTOS', 'ADULTOS', 2, 0),
(2, '2023-04-02 11:00:00', 2, 1, 90, 2, 15.00, 3.00, 'MINIBASKET', 'FAMILIAR', 2, 2),
(3, '2023-04-03 12:00:00', 3, 1, 120, 3, 25.00, 7.00, 'VS3', 'INFANTIL', 1, 3);