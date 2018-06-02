DROP DATABASE IF EXISTS requemuestreos;
CREATE DATABASE requemuestreos;

USE requemuestreos;

DROP TABLE IF EXISTS Proyecto;
CREATE TABLE Proyecto(
	id INT  AUTO_INCREMENT PRIMARY KEY,
	nombre  VARCHAR(100),
	descripcion  VARCHAR(150)
);

DROP TABLE IF EXISTS Operacion;
CREATE TABLE Operacion(
	id INT  AUTO_INCREMENT PRIMARY KEY,
	nombre  VARCHAR(100)
);

DROP TABLE IF EXISTS Operacion_proyecto;
CREATE TABLE Operacion_proyecto(
	id INT  AUTO_INCREMENT PRIMARY KEY,
	idOperacion INT NOT NULL,
	idProyecto  INT NOT NULL,
	FOREIGN KEY(idOperacion)REFERENCES Operacion(id),
	FOREIGN KEY(idProyecto)REFERENCES Proyecto(id)
);




DROP TABLE IF EXISTS Tarea;
CREATE TABLE Tarea(
	id INT AUTO_INCREMENT PRIMARY KEY,
	nombre  VARCHAR(100),
	tipo int
);


DROP TABLE IF EXISTS Operacion_tarea;
CREATE TABLE Operacion_tarea(
	id INT  AUTO_INCREMENT PRIMARY KEY,
	idOperacion INT NOT NULL,
	idTarea  INT NOT NULL,
	FOREIGN KEY(idOperacion)REFERENCES Operacion(id),
	FOREIGN KEY(idTarea)REFERENCES tarea(id)
);

DROP TABLE IF EXISTS Muestreo;
CREATE TABLE Muestreo(
	id INT  AUTO_INCREMENT PRIMARY KEY,
	idOperacion INT NOT NULL,
	idProyecto  INT NOT NULL,
	cantidadObservaciones DECIMAL(10,0),
	rangominutos DECIMAL(10,0),
	horaInicio	DATETIME,
	horaFinalizacion DATETIME,
	FOREIGN KEY(idOperacion)REFERENCES Operacion(id),
	FOREIGN KEY(idProyecto)REFERENCES Proyecto(id)
);

DROP TABLE IF EXISTS HoraRestringida;
CREATE TABLE HoraRestringida(
	id INT  AUTO_INCREMENT PRIMARY KEY,
	idMuestreo INT NOT NULL,
	hora TIME,
	duracionMinutos INT,
	FOREIGN KEY(idMuestreo)REFERENCES Muestreo(id)
);

DROP TABLE IF EXISTS HoraMuestrear;
CREATE TABLE HoraMuestrear(
	id INT AUTO_INCREMENT PRIMARY KEY,
	idMuestreo INT NOT NULL,
	hora TIME,
	FOREIGN KEY(idMuestreo)REFERENCES Muestreo(id)
);


DROP TABLE IF EXISTS Trabajador;
CREATE TABLE Trabajador(
	id INT AUTO_INCREMENT PRIMARY KEY,
	apodo  VARCHAR(50),
	puesto  VARCHAR(50)
);

DROP TABLE IF EXISTS Trabajadores_muestreo;
CREATE TABLE Trabajadores_muestreo(
	id INT  AUTO_INCREMENT PRIMARY KEY,
	idMuestreo INT NOT NULL,
	idTrabajador  INT NOT NULL,
	FOREIGN KEY(idMuestreo)REFERENCES Muestreo(id),
	FOREIGN KEY(idTrabajador)REFERENCES Trabajador(id)
);

DROP TABLE IF EXISTS Observacion;
CREATE TABLE Observacion(
	id INT AUTO_INCREMENT PRIMARY KEY,
	idMuestreo INT NOT NULL,
	idTrabajador  INT NOT NULL,
	idTarea  INT NOT NULL,
	fecha DATETIME,
	comentario VARCHAR(1024),
	FOREIGN KEY(idMuestreo)REFERENCES Muestreo(id),
	FOREIGN KEY(idTrabajador)REFERENCES Trabajador(id)
);

DROP TABLE IF EXISTS Usuario;
CREATE TABLE Usuario(
	id INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100),
	passwordDigest VARCHAR(100),
	telefono DECIMAL(12,0),
	correo VARCHAR(100),
	tipoUsuario int

);

ALTER TABLE Usuario ADD UNIQUE (correo);

DROP TABLE IF EXISTS Analista_muestreo;
CREATE TABLE Analista_muestreo(
	id INT  AUTO_INCREMENT PRIMARY KEY,
	idMuestreo INT NOT NULL,
	idUsuario INT NOT NULL,
	FOREIGN KEY(idMuestreo)REFERENCES Muestreo(id),
	FOREIGN KEY(idUsuario)REFERENCES Usuario(id)
);