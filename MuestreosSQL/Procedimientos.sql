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
