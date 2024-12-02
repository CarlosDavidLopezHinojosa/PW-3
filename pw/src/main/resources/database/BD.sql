CREATE TABLE Pista (
    nombre VARCHAR(255),
    id INT AUTO_INCREMENT PRIMARY KEY,
    disponible BOOLEAN,
    esExterior BOOLEAN,
    tamano ENUM('MINIBASKET', 'ADULTOS', 'VS3'),
    maxJugadores INT
);

CREATE TABLE Material (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('PELOTAS', 'CANASTAS', 'CONOS'),
    usoExterior BOOLEAN,
    estado ENUM('DISPONIBLE', 'RESERVADO', 'MAL_ESTADO'),
    idPista INT,
    FOREIGN KEY (idPista) REFERENCES Pista(id)
);


CREATE TABLE Jugador (
    nombre VARCHAR(255),
    apellidos VARCHAR(255),
    id INT AUTO_INCREMENT PRIMARY KEY,
    fechaNacimiento DATE,
    fechaInscripcion DATE,
    email VARCHAR(255)
);

CREATE TABLE Reserva (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idUsuario INT,
    diaYHora DATETIME,
    idBono INT NULL,
    nSesionBono INT,
    duracion INT,
    idPista INT,
    precio DECIMAL(5,2),
    descuento DECIMAL(5,2),
    pistaTamano ENUM('MINIBASKET', 'ADULTOS', 'VS3'),
    
    tipo ENUM('ADULTOS','FAMILIAR','INFANTIL'),
    numAdultos INT,
    numNinos INT,
    FOREIGN KEY (idBono) REFERENCES Bono(id),
    FOREIGN KEY (idUsuario) REFERENCES Jugador(id),
    FOREIGN KEY (idPista) REFERENCES Pista(id)
);

CREATE TABLE Bono (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sesiones INT,
    idUser INT,
    tipoReserva ENUM('ADULTOS','FAMILIAR','INFANTIL'),
    tamanoPista ENUM('MINIBASKET', 'ADULTOS', 'VS3'),
    FOREIGN KEY (idUser) REFERENCES Jugador(id)
);