DROP SCHEMA IF EXISTS `aeropuertos` ;

CREATE SCHEMA IF NOT EXISTS `aeropuertos` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci;

USE `aeropuertos` ;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";

SET AUTOCOMMIT = 0;

START TRANSACTION;


----------------------------------
-- Tabla `direcciones`
----------------------------------

CREATE TABLE  `direcciones` (
	`id` int NOT NULL AUTO_INCREMENT,
	`pais` varchar(50) NOT NULL,
	`ciudad` varchar(50) NOT NULL,
	`calle` varchar(50) NOT NULL,
	`numero` int NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE =  InnoDB AUTO_INCREMENT = 10 DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;

INSERT INTO `direcciones` ( `id`, `pais`, `ciudad`, `calle`, `numero` )
VALUES	(1, 'España', 'Bilbao', 'Loiu', 1), 
		(2, 'España','Madrid','Avenida Libertad',1 ),
        (3,'España','El Prat de Llobregat','Calle maravillosa',2),
        (4,'Andorra', 'Andorra','Calle aeropuerto',3),
        (5, 'España','Vitoria', 'Avenida Foronda', 5 ),
        (6, 'España','Madrid', 'Prueba', 5);


------------------------
-- Tabla `aeropuertos`
-------------------------

CREATE TABLE  `aeropuertos` (
	`id` int NOT NULL AUTO_INCREMENT,
	`nombre` varchar(50) NOT NULL,
	`anio_inauguracion` int NOT NULL,
	`capacidad` int NOT NULL,
	`id_direccion` int NOT NULL,
	`imagen` blob NULL,
	PRIMARY KEY (`id`),
    CONSTRAINT `aeropuertos_direcciones` FOREIGN KEY (`id_direccion`) REFERENCES `direcciones` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10 DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;

INSERT INTO `aeropuertos` ( `id`, `nombre`, `anio_inauguracion`, `capacidad`, `id_direccion` )
VALUES 	(1, 'Loiu', 1950, 59058047, 1),
		(2, 'Adolfo Suárez Madrid-Barajas', 1920, 6173403, 2),
		(4, 'El Prat', 1925, 52686314, 3),
		(3, 'Aeropuerto de Andorra', 1980, 300000, 4 ),
		(5, 'Foronda', 1977, 174022, 5),
		(6, 'Base Aérea de Torrejón', 1990, 500, 6);
  
  
---------------------------------
-- Tabla `aeropuertos_privados`
----------------------------------

CREATE TABLE `aeropuertos_privados` (
	`id_aeropuerto` int NOT NULL,
	`numero_socios` int NOT NULL,
	PRIMARY KEY (`id_aeropuerto`),
	CONSTRAINT `aeropuertos_privados_aeropuertos` FOREIGN KEY (`id_aeropuerto`) REFERENCES `aeropuertos` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;

INSERT INTO `aeropuertos_privados` (`id_aeropuerto`, `numero_socios` )
VALUES 	(3, 15),
		(6, 2);


----------------------------------
-- Tabla `aeropuertos_publicos`
----------------------------------

CREATE TABLE `aeropuertos_publicos` (
	`id_aeropuerto` int NOT NULL,
	`financiacion` decimal(20,2) NOT NULL,
	`num_trabajadores` int NOT NULL,
	PRIMARY KEY (`id_aeropuerto`),
	CONSTRAINT `aeropuertos_publicos_aeropuertos` FOREIGN KEY (`id_aeropuerto`) REFERENCES `aeropuertos` (`id`) ON DELETE CASCADE
) ENGINE =  InnoDB DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;

INSERT INTO `aeropuertos_publicos` ( `id_aeropuerto`, `financiacion`, `num_trabajadores` )
VALUES 	(1, '100000000.50', 200),
		(2, '2000000000.00', 2500), 
        (4, '1500000000.50', 2400), 
        (5, '50000000.00', 100);


----------------------------------
-- Tabla `aviones`
----------------------------------
CREATE TABLE `aviones` (
	`id` int NOT NULL AUTO_INCREMENT,
	`modelo` varchar(60) NOT NULL,
	`numero_asientos` int NOT NULL,
	`velocidad_maxima` int NOT NULL,
	`activado` int NOT NULL,
	`id_aeropuerto` int NOT NULL,
	PRIMARY KEY (`id`),
	KEY `id_aeropuerto` (`id_aeropuerto`),
	CONSTRAINT `aviones_aeropuertos` FOREIGN KEY (`id_aeropuerto`) REFERENCES `aeropuertos` (`id`)
) ENGINE =  InnoDB AUTO_INCREMENT = 20 DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;

INSERT INTO `aviones` ( `id`, `modelo`, `numero_asientos`, `velocidad_maxima`, `activado`, `id_aeropuerto`)
VALUES 	(1, 'Boeing1', 300, 500, 1, 2), 
		(2, 'Falcon1', 250, 400, 1, 2), 
        (3, 'Air force1', 200, 300, 1, 2), 
        (4, 'Airbus1', 60, 450, 0, 2), 
        (5, 'Boeing2', 300, 500, 1, 4), 
        (6, 'Falcon2', 250, 400, 1, 4), 
        (7, 'Air force2', 200, 300, 1, 4), 
        (8, 'Airbus2', 60, 450, 0, 4), 
        (9, 'Boeing3', 300, 500, 1, 3), 
        (10, 'Falcon3', 250, 400, 1, 3), 
        (11, 'Air force4', 200, 300, 1, 1), 
        (12, 'Airbus4', 60, 450, 0, 1), 
        (13, 'Boeing5', 300, 500, 1, 5), 
        (14, 'Falcon5', 250, 400, 1, 5), 
        (15, 'Air force6', 200, 300, 1, 6);


----------------------------------
-- Tabla `usuarios`
----------------------------------

CREATE TABLE `usuarios` (
	`usuario` varchar(50) NOT NULL,
	`password` varchar(50) NOT NULL,
	PRIMARY KEY (`usuario`)
) ENGINE = InnoDB  DEFAULT CHARACTER SET = latin1 COLLATE = latin1_spanish_ci;

INSERT INTO `usuarios` (`usuario`, `password`) VALUES ('admin', 'admin');

COMMIT;