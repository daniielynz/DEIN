DROP SCHEMA IF EXISTS `supermercado` ;
CREATE SCHEMA IF NOT EXISTS `supermercado` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci;
USE `supermercado` ;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;

CREATE TABLE `seccion` (
  `codigo` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB;

INSERT INTO `seccion` (`codigo`, `nombre`) VALUES
(1,'FRUTERIA'),
(2,'CHARCUTERIA'),
(3,'CARNICERIA'),
(4,'COSMETICOS'),
(5,'PESCADERIA'),
(6,'PANADERIA');

CREATE TABLE `producto` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `precio` float DEFAULT 0,
  `stock` int DEFAULT 0,
  `seccion` int NOT NULL,
  PRIMARY KEY (`codigo`),
  CONSTRAINT `FK_producto_seccion` FOREIGN KEY (`seccion`) REFERENCES `seccion` (`codigo`)
) ENGINE=InnoDB;

INSERT INTO `producto` (`nombre`, `precio`, `stock`, `seccion`) VALUES
	('LIMONES',1.89,10,1),
	('NARANJAS',1.69,20,1),
	('PLATANOS',1.75,120,1),
	('CHORIZO',3.25,25,2),
	('JAMON',4.21,32,2),
	('POLLO',5.99,5,3),
	('CHULETAS',25.99,6,3),
	('GEL',25.99,24,4),
	('CHAMPU',25.99,16,4),
	('SALMON',12.05,23,5),
	('SARDINAS',10.05,80,5),
	('MERLUZA',32.05,13,5),
	('DORADA',22.05,9,5),
	('PALMERA',2.2,35,6),
	('PAN',2.1,45,6),
	('DONUTS',1.00,12,6);

COMMIT;