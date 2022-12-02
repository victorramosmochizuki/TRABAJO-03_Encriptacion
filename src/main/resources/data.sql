DROP TABLE IF EXISTS COLLABORATOR_TYPE;

CREATE TABLE COLLABORATOR_TYPE(
id SERIAL PRIMARY KEY NOT NULL,
position VARCHAR(50) NOT NULL,
description VARCHAR(10000) NOT NULL,
area_type VARCHAR(50) NOT NULL,
active BOOLEAN NOT NULL,
stage INTEGER NOT NULL
);

INSERT INTO COLLABORATOR_TYPE (position,description,area_type,active,stage)
values  ('Tesorero','Encargado del Area de Tesoreria de Valle Grande','Administracion','true', '1'),
        ('Jefe de area','Encargado del Jefe de Area de Valle Grande','Administracion','true', '2'),
        ('Tesorero','Encargado del Area de Tesoreria de Valle Grande','Administracion','true', '3'),
        ('Secretaria','Encargado de la Secretaria academica de Valle Grande','Academico','true', '4'),
        ('Director','Encargado la firma de resolucion','Administracion','true', '5'),
        ('Secretaria','Encargado de la Secretaria academica de Valle Grande','Academico','true', '6'),
        ('Jefe Minedu','Encargado de emitir los titulos','Academico','true', '7');
select* from COLLABORATOR_TYPE;