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

		SELECT COUNT(id), id ,telefono, tipoUsuario 
		INTO qEncontrado, pId, pTelefono, pTipo
		FROM Usuario
		WHERE correo = pCorreo;

		IF(qEncontrado = 0) THEN
			SET pTipo = 0;
		END IF;

	END//

DELIMITER ;
