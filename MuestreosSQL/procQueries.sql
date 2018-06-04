
CALL create_user('carlos','1056ww','carlosgomezsoza@gmail.com',128,1);
CALL create_user('jose','123456','joseaguirre@gmail.com',129,2);
CALL create_user('Katherina','12345','bfkathe@gmail.com',130,2);

CALL agregarTarea('Colocando panel',1);
CALL agregarTarea('Taladrando',1);
CALL agregarTarea('Cortando madera',1);
CALL agregarTarea('Clavando',1);
CALL agregarTarea('Colocando llaves',1);
CALL agregarTarea('Aplomando Columna',1);

CALL agregarTarea('Tranportándose',2);
CALL agregarTarea('Hablando',2);
CALL agregarTarea('Esperando',2);
CALL agregarTarea('Ocioso',2);
CALL agregarTarea('Ausente',2);
CALL agregarTarea('Observando',2);
 
CALL agregarTarea('Transportando panel',3);
CALL agregarTarea('Transportando varilla',3);
CALL agregarTarea('Transportando escalera',3);
CALL agregarTarea('Midiendo',3);
CALL agregarTarea('Acomodando herramientas',3);
CALL agregarTarea('Verificando nivel',3);

CALL agregarOperacion('Columnas/encofrado');
CALL agregarOperacion('Colado');
CALL agregarOperacion('Vigas');
CALL agregarOperacion('Columnas/desencofrado');
CALL agregarOperacion('Vigas encofrado');

CALL agregarProyecto('Proyecto 1','Construccion escuela');
CALL agregarProyecto('Proyecto 2','Construccion asilo');
CALL agregarProyecto('Proyecto 3','Construccion edificio');
CALL agregarProyecto('Proyecto 3','Construccion gimnasio');

CALL create_trabajador('Vegonia','Operario');
CALL create_trabajador('Hondureño','Ayudante');
CALL create_trabajador('Beto','Operario');
CALL create_trabajador('Randy','Ayudante');