DELIMITER //


CREATE PROCEDURE create_user(IN pPassword VARCHAR(100),IN pCorreo VARCHAR(100),IN pNombre VARCHAR(100), IN pTelefono INT, IN pTipo INT)
	BEGIN
		INSERT INTO Usuario(passwordDigest,correo,nombre,telefono,tipoUsuario)
		VALUES(pPassword,pCorreo,pNombre,pTelefono,pTipo);
	END//

DELIMITER ;

DELIMITER //

CREATE PROCEDURE create_trabajador(IN pApodo VARCHAR(50),IN pPuesto VARCHAR(50))
	BEGIN
		INSERT INTO trabajador(apodo, puesto)
		VALUES(pApodo, pPuesto);
	END//

DELIMITER ;

DELIMITER //
CREATE PROCEDURE editarTrabajadorApodo(IN ID_OP INT,IN pApodo VARCHAR(50))
	UPDATE trabajador SET apodo = pApodo WHERE id=ID_OP; //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE editarTrabajadorPuesto(IN ID_OP INT,IN pPuesto VARCHAR(50))
	UPDATE trabajador SET puesto = pPuesto WHERE id=ID_OP; //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE eliminarTrabajador(IN ID_OP INT)
	DELETE FROM trabajador WHERE id=ID_OP; //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE getIdTrabajador(IN pApodo VARCHAR(50), OUT ID_OP INT)
	SELECT id 
	into ID_OP
	FROM trabajador WHERE apodo = pApodo; //
DELIMITER ;


DELIMITER //

CREATE PROCEDURE validate_login(IN pCorreo VARCHAR(100), IN pPassword VARCHAR(100), 
								OUT pTelefono INT,OUT pTipo INT, Out pId INT)
	BEGIN
		DECLARE qEncontrado INT;
		DECLARE qPassword Varchar(100);

		SELECT COUNT(id), id ,telefono, tipoUsuario, passwordDigest 
		INTO qEncontrado, pId, pTelefono, pTipo, qPassword
		FROM Usuario
		WHERE correo = pCorreo;
		
		IF(qEncontrado = 0) THEN
			SET pTipo = 0;
		END IF;
		IF(!(qPassword LIKE pPassword)) THEN
			SET pTipo = 0;
		END IF;

	END//

DELIMITER ;

DELIMITER //
CREATE PROCEDURE agregarTarea(IN NOMBRE VARCHAR(100),IN TIPO VARCHAR(150))
	BEGIN
		DECLARE TIPO_TAREA INT;
		CASE TIPO
			WHEN "Productiva" THEN SET TIPO_TAREA=1;
			WHEN "No Productiva" THEN SET TIPO_TAREA=2;
			ELSE SET TIPO_TAREA=3;
		END CASE;
    INSERT INTO tarea (nombre,tipo) VALUES (NOMBRE,TIPO_TAREA);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE editarTarea(IN IDT INT,IN NOMBRET VARCHAR(100),IN TIPOT VARCHAR(150))
BEGIN
	DECLARE TIPO_TAREA INT;
		CASE TIPOT
			WHEN "Productiva" THEN SET TIPO_TAREA=1;
			WHEN "No Productiva" THEN SET TIPO_TAREA=2;
			ELSE SET TIPO_TAREA=3;
		END CASE;
	UPDATE tarea SET nombre=NOMBRET,tipo=TIPO_TAREA WHERE id=IDT;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE eliminarTarea (IN ID_Tarea INT)
	delete from tarea where id=ID_Tarea;
    
DELIMITER ;

DELIMITER //
CREATE PROCEDURE agregarOperacion(IN NOMBRE VARCHAR(100))
	INSERT INTO operacion (nombre) VALUES (NOMBRE); //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE asociar_tarea_operacion(IN ID_OP INT, IN ID_TAR INT)
	INSERT INTO operacion_tarea (idOperacion,idTarea) VALUES (ID_OP,ID_TAR); //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE editarOperacion(IN ID_OP INT,IN NOMBRE_OP VARCHAR(100))
	UPDATE operacion SET nombre=NOMBRE_OP WHERE id=ID_OP; //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE eliminarOperacion(IN ID_OP INT)
	DELETE FROM operacion WHERE id=ID_OP; //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE agregarProyecto(IN NOMBRE_PRO VARCHAR(100),IN DESCRIPCION_PRO VARCHAR(150))
	INSERT INTO proyecto (nombre,descripcion) VALUES (NOMBRE_PRO,DESCRIPCION_PRO); //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE generarMuestreo(IN ID_OP INT,IN ID_PRO INT,IN CANT_OBS INT,IN RANGO_MIN INT,
					IN HORA_INI TIME,IN HORA_FIN TIME)
INSERT INTO muestreo (idOperacion,idProyecto,cantidadObservaciones,rangominutos,horaInicio,horaFinalizacion)
			VALUES (ID_OP,ID_PRO,CANT_OBS,RANGO_MIN,HORA_INI,HORA_FIN); //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE agregarHoraRestringida(IN ID_MUESTREO INT,IN HORA TIME,IN DURACION INT)
INSERT INTO horarestringida (idMuestreo,hora,duracionMinutos) VALUES (ID_MUESTREO,HORA,DURACION); //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE asociar_operacion_proyecto(IN ID_OP INT,IN ID_PRO INT)
	INSERT INTO operacion_proyecto (idOperacion,idProyecto) VALUES (ID_OP,ID_PRO); //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE editarProyecto(IN ID_PRO INT,IN NOMBRE_PRO VARCHAR(100),IN DESCRIPCION_PRO VARCHAR(150))
	UPDATE proyecto SET nombre=NOMBRE_PRO,descripcion=DESCRIPCION_PRO WHERE id=ID_PRO; //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE descripcionPro(IN ID_PRO INT)
	SELECT descripcion FROM proyecto WHERE id=ID_PRO; //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE eliminarProyecto(IN ID_PRO INT)
	DELETE FROM proyecto WHERE id=ID_PRO; //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE asociar_analista_muestreo(IN ID_MUESTREO INT,IN ID_USUARIO INT)
	INSERT INTO analista_muestreo (idMuestreo,idUsuario) VALUES (ID_MUESTREO,ID_USUARIO); //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE asociar_trabajador_muestreo(IN ID_MUESTREO INT,IN ID_TRABAJADOR INT)
	INSERT INTO trabajadores_muestreo (idMuestreo,idTrabajador) VALUES(ID_MUESTREO,ID_TRABAJADOR); //
DELIMITER ;
    



