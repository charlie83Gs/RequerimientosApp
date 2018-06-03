DELIMITER //

CREATE PROCEDURE create_user(IN pPassword VARCHAR(100),IN pCorreo VARCHAR(100), IN pTelefono INT, IN pTipo INT)
	BEGIN
		INSERT INTO Usuario(passwordDigest,correo,telefono,tipoUsuario)
		VALUES(pPassword,pCorreo,pTelefono,pTipo);
	END//

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
CREATE PROCEDURE mostrarTareas()
	SELECT id,nombre from tarea
DELIMITER;

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

