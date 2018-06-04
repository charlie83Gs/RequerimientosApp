
CALL create_user('carlos','1056ww','carlosgomezsoza@gmail.com',128,1);
CALL create_user('jose','123456','joseaguirre@gmail.com',129,2);

CALL create_user('1056ww','carlosgomezsoza@gmail.com','Carlos',128,1);

--algunos datos de prueba
insert into operacion(nombre)
values("encofrado de columana");
insert into operacion(nombre)
values("Tapisado de pared");
insert into operacion(nombre)
values("Construir infraestructura");
insert into proyecto(nombre,descripcion)
values("Remodelacion tec","trabajos para reparar, mejorar y ampliar la infraestructura del tec");
insert into muestreo(idOperacion, idProyecto,cantidadObservaciones,rangominutos, horaInicio,horaFinalizacion)
Values(2,1,30,600,current_timestamp(), '2019-06-04 09:55:42');
insert into muestreo(idOperacion, idProyecto,cantidadObservaciones,rangominutos, horaInicio,horaFinalizacion)
Values(3,1,40,500,current_timestamp(), '2019-06-04 09:55:42');
insert into analista_muestreo(idMuestreo, idUsuario)
values(1,1);
insert into analista_muestreo(idMuestreo, idUsuario)
values(2,1);