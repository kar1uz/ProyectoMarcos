-- data.sql
-- Datos para la tabla `administradores`
INSERT INTO administradores (id_admin, email, password_hash, nombre, apellido, fecha_creacion, activo) VALUES
(1,'admin@tecnonet.com','$2a$10$qyvczj9c2sTqPZRBdTkr1uuhZkqPMBN4ZAQYQJhBJJxI/R/gN/GYu','Patrick Estefano','Del Aguila Rodriguez','2025-06-16 23:30:08',1);

-- Datos para la tabla `contactos`
-- INSERT INTO contactos (id_contacto, id_usuario, nombre_remitente, apellido_remitente, email_remitente, telefono_remitente, asunto, mensaje, asesor_genero, fecha_envio) VALUES
-- (1,1,'Carlos Hernesto','Casas Torres','CarlosCasas@gmail.com','987456321','consulta','Necesito que me den información sobre los planes','MASCULINO','2025-06-17 06:35:58'),
-- (2,1,'Carlos Hernesto','Casas Torres','CarlosCasas@gmail.com','987456321','consulta','Cuanto tiempo se demoran en habilitar el plan','FEMENINO','2025-06-17 06:37:42'),
-- (3,2,'Miguel Angel','Tenorio Mesa','migueltenorio25@gmail.com','945678123','consulta','Cuanto tiempo se demoran en responder las consultas','FEMENINO','2025-06-17 19:58:08');

-- Datos para la tabla `planes`
-- INSERT INTO planes (id_plan, nombre_plan, velocidad_descarga_mbps, velocidad_carga_mbps, wifi_incluido, mes_gratis_promocion, puertos_ethernet, precio_mensual, descripcion, fecha_creacion, activo) VALUES
-- (1,'Plan Home Eco',50,25,1,0,1000,55.00,'Ideal para uso básico del hogar, navegación y redes sociales.','2025-06-17 16:55:49',1),
-- (2,'Plan Home Lite',100,50,1,0,1000,110.00,'Perfecto para hogares con streaming moderado y trabajo remoto ligero.','2025-06-17 16:56:41',1),
-- (3,'Plan Home Prime',200,95,1,1,1000,210.00,'Diseñado para usuarios exigentes, gaming, streaming 4K y múltiples dispositivos.','2025-06-17 16:57:21',1);

-- Datos para la tabla `usuarios`
-- INSERT INTO usuarios (id_usuario, nombre, apellido, email, password_hash, fecha_registro, activo, tipo_usuario) VALUES
-- (1,'Carlos Hernesto','Casas Torres','CarlosCasas@gmail.com','$2a$10$ETpxthB5ljNzR9vCWjBl9exlECB3UD6nVzQxCFuHcoS2bxRQTGEmi','2025-06-17 04:35:58',1,'USUARIO'),
-- (2,'Miguel Angel','Tenorio Mesa','migueltenorio25@gmail.com','$2a$10$dZU86tRfIg51oS2nRvoLHuBBwr85cy.FswdvEpai9QB1XIn05Jnwm','2025-06-17 06:41:14',1,'USUARIO'),
-- (3,'Camila Valentina','Mendez Sosa','camivalen74@gmail.com','$2a$10$pDWHohmRsMLdRM1YI0CY8OvZMzX5eQtfL.7WygTiNph4iKTI7dPje','2025-06-17 06:56:02',1,'USUARIO');

-- Datos para la tabla `contratos` (¡Importante: inserta contratos DESPUÉS de usuarios y planes, por las FKs!)
-- INSERT INTO contratos (id_contrato, id_usuario, id_plan, fecha_contratacion, fecha_inicio_servicio, fecha_fin_contrato, estado_contrato, direccion_instalacion, numero_telefono_contacto, metodo_pago, costo_instalacion, observaciones) VALUES
-- (1,1,1,'2025-06-17 18:44:33','2025-06-18','2026-06-18','PENDIENTE_INSTALACION','Avenida Javier Prado Este, 123, San Isidro, Lima','987456321','Transferencia Bancaria',70.00,'La entrada al edificio es por la calle trasera.'),
-- (2,3,3,'2025-06-17 18:57:14','2025-06-20','2026-06-20','PENDIENTE_INSTALACION','Jirón de la Unión, 1011, Cercado de Lima','912345678','Tarjeta de Debito',70.00,'Existe un perro grande en la propiedad, por favor llamar antes de llegar.'),
-- (3,2,2,'2025-06-17 20:03:44','2025-06-23','2026-06-23','PENDIENTE_INSTALACION','Avenida Alfredo Benavides, 789, Miraflores, Lima','945678123','Tarjeta de Credito',70.00,'Se solicita que no se ingrese con calzado a su domicilio, favor de llevar cubre zapatos.');