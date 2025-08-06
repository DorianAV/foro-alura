-- Tabla Perfil
CREATE TABLE Perfil
(
    id     INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL
);

-- Tabla Usuario
CREATE TABLE Usuario
(
    id                INT PRIMARY KEY AUTO_INCREMENT,
    nombre            VARCHAR(100) NOT NULL,
    correo_electronico VARCHAR(150) NOT NULL UNIQUE,
    contrasena        VARCHAR(255) NOT NULL,
    perfiles          INT,
    FOREIGN KEY (perfiles) REFERENCES Perfil (id)
);

-- Tabla Curso
CREATE TABLE Curso
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    nombre    VARCHAR(100) NOT NULL,
    categoria VARCHAR(100)
);

-- Tabla Tópico
CREATE TABLE Topico
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    titulo        VARCHAR(200) NOT NULL,
    mensaje       TEXT         NOT NULL,
    fecha_creacion DATETIME,
    status        VARCHAR(50),
    autor         INT,
    curso         INT,
    FOREIGN KEY (autor) REFERENCES Usuario (id),
    FOREIGN KEY (curso) REFERENCES Curso (id)
);

-- Tabla Respuesta
CREATE TABLE Respuesta
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    mensaje       TEXT NOT NULL,
    topico        INT,
    fecha_creacion DATETIME ,
    autor         INT,
    solucion      BOOLEAN  DEFAULT FALSE,
    FOREIGN KEY (topico) REFERENCES Topico (id),
    FOREIGN KEY (autor) REFERENCES Usuario (id)
);
