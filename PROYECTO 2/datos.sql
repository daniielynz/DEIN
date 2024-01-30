-- MariaDB dump 10.19  Distrib 10.6.12-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: libros
-- ------------------------------------------------------
-- Server version	10.6.12-MariaDB-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Alumno`
--

DROP TABLE IF EXISTS `Alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Alumno` (
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(150) DEFAULT NULL,
  `apellido1` varchar(150) DEFAULT NULL,
  `apellido2` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Alumno`
--

/*!40000 ALTER TABLE `Alumno` DISABLE KEYS */;
INSERT INTO `Alumno` VALUES 
('23456789X', 'Laura', 'Martínez', 'Fernández'),
('34567890Y', 'Carlos', 'Gómez', 'López'),
('45678901Z', 'Elena', 'Fernández', 'González'),
('56789012A', 'Hugo', 'Sánchez', 'Rodríguez'),
('67890123B', 'Sofía', 'Jiménez', 'Díaz'),
('78901234C', 'Miguel', 'Ruiz', 'Vargas'),
('89012345D', 'Isabel', 'Hernández', 'Martínez'),
('90123456E', 'Javier', 'López', 'Mendoza'),
('01234567F', 'Ana', 'García', 'Pérez'),
('12345678W', 'Diego', 'Ramírez', 'Hernández'),
('56842103X', 'Carmen', 'Pérez', 'García'),
('56842165H', 'Manuel', 'López', 'Herrera'), 
('56842166J', 'María', 'Fernández', 'González'),
('56842167K', 'Pablo', 'Hernández', 'Martínez'), 
('56842168L', 'Luis', 'Jiménez', 'Díaz'), 
('56842169M', 'Daniel', 'Sanz', 'Gómez'), 
('56842170N', 'Iker', 'Torres', 'Serrano'), 
('56842171P', 'Adriana', 'Gutiérrez', 'Mendoza'), 
('56842172Q', 'Irene', 'Ruiz', 'Vargas'), 
('56842173R', 'Alejandro', 'Serrano', 'Martínez'); 


/*!40000 ALTER TABLE `Alumno` ENABLE KEYS */;

--
-- Table structure for table `Historico_prestamo`
--

DROP TABLE IF EXISTS `Historico_prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Historico_prestamo` (
  `id_prestamo` int(11) NOT NULL AUTO_INCREMENT,
  `dni_alumno` varchar(9) NOT NULL,
  `codigo_libro` int(11) NOT NULL,
  `fecha_prestamo` datetime DEFAULT NULL,
  `fecha_devolucion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_prestamo`),
  KEY `FK_Historico_prestamo_Alumno` (`dni_alumno`),
  KEY `FK_Historico_prestamo_Libro` (`codigo_libro`),
  CONSTRAINT `FK_Historico_prestamo_Alumno` FOREIGN KEY (`dni_alumno`) REFERENCES `Alumno` (`dni`),
  CONSTRAINT `FK_Historico_prestamo_Libro` FOREIGN KEY (`codigo_libro`) REFERENCES `Libro` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `Historico_prestamo` (`dni_alumno`, `codigo_libro`, `fecha_prestamo`, `fecha_devolucion`) VALUES 
('12345678W', 1, '2024-02-20 10:30:00', '2024-03-05 14:45:00'),
('23456789X', 2, '2024-02-21 11:15:00', '2024-03-06 16:20:00'),
('34567890Y', 3, '2024-02-22 09:45:00', '2024-03-07 12:10:00'),
('45678901Z', 4, '2024-02-23 13:20:00', '2024-03-08 15:30:00'),
('56789012A', 5, '2024-02-24 14:50:00', '2024-03-09 17:15:00'),
('67890123B', 6, '2024-02-25 10:05:00', '2024-03-10 11:05:00'),
('78901234C', 7, '2024-02-26 16:30:00', '2024-03-11 16:40:00'),
('89012345D', 8, '2024-02-27 08:40:00', '2024-03-12 08:55:00'),
('90123456E', 9, '2024-02-28 12:10:00', '2024-03-13 13:25:00'),
('01234567F', 10, '2024-02-29 17:30:00', '2024-03-14 17:00:00');

--
-- Dumping data for table `Historico_prestamo`
--

/*!40000 ALTER TABLE `Historico_prestamo` DISABLE KEYS */;
/*!40000 ALTER TABLE `Historico_prestamo` ENABLE KEYS */;

--
-- Table structure for table `Libro`
--

DROP TABLE IF EXISTS `Libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Libro` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(150) DEFAULT NULL,
  `autor` varchar(200) DEFAULT NULL,
  `editorial` varchar(150) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  `baja` int(11) DEFAULT 0,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Libro`
--

/*!40000 ALTER TABLE `Libro` DISABLE KEYS */;
INSERT INTO `Libro` VALUES 
(6, 'Crimen y castigo', 'Fyodor Dostoevsky', 'Editorial Ejemplo', 'Nuevo', 0),
(7, 'En busca del tiempo perdido', 'Marcel Proust', 'Otra Editorial', 'Usado seminuevo', 1),
(8, 'Ulises', 'James Joyce', 'Editorial Literaria', 'Usado estropeado', 0),
(9, 'Don Quijote de la Mancha', 'Miguel de Cervantes', 'Editorial Clásica', 'Restaurado', 1),
(10, 'Matar a un ruiseñor', 'Harper Lee', 'Editorial Harper', 'Estropeado', 1),
(11, 'Los miserables', 'Victor Hugo', 'Editorial Hugo', 'Nuevo', 0),
(12, 'El Gran Gatsby', 'F. Scott Fitzgerald', 'Editorial Gatsby', 'Usado seminuevo', 1),
(13, 'El guardián entre el centeno', 'J.D. Salinger', 'Editorial Salinger', 'Usado estropeado', 0),
(14, 'La Odisea', 'Homero', 'Editorial Épica', 'Restaurado', 1),
(15, 'Cien años de soledad', 'Gabriel Garcia Marquez', 'Editorial Sudamericana', 'Estropeado', 1),
(16, 'Mujer en punto cero', 'Nawal El Saadawi', 'Editorial Feminista', 'Nuevo', 0),
(17, 'El Hobbit', 'J.R.R Tolkien', 'Editorial Fantástica', 'Usado seminuevo', 1),
(18, 'Anna Karenina', 'Leo Tolstoy', 'Editorial Rusa', 'Usado estropeado', 0),
(19, 'Rayuela', 'Julio Cortázar', 'Editorial Latinoamericana', 'Restaurado', 1),
(20, 'Matar a un ruiseñor', 'Harper Lee', 'Editorial Harper', 'Estropeado', 1),
(21, 'La divina comedia', 'Dante Alighieri', 'Editorial Renacentista', 'Nuevo', 0),
(22, 'El nombre del viento', 'Patrick Rothfuss', 'Editorial Épica', 'Usado seminuevo', 1),
(23, 'El retrato de Dorian Gray', 'Oscar Wilde', 'Editorial Literaria', 'Usado estropeado', 0),
(24, 'La metamorfosis', 'Franz Kafka', 'Editorial Surrealista', 'Restaurado', 1),
(25, 'Harry Potter y la piedra filosofal', 'J.K. Rowling', 'Editorial Mágica', 'Estropeado', 1);

/*!40000 ALTER TABLE `Libro` ENABLE KEYS */;

--
-- Table structure for table `Prestamo`
--

DROP TABLE IF EXISTS `Prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Prestamo` (
  `id_prestamo` int(11) NOT NULL AUTO_INCREMENT,
  `dni_alumno` varchar(9) NOT NULL,
  `codigo_libro` int(11) NOT NULL,
  `fecha_prestamo` datetime DEFAULT NULL,
  PRIMARY KEY (`id_prestamo`),
  KEY `FK_Prestamo_Libro` (`codigo_libro`),
  KEY `FK_Prestamo_Alumno` (`dni_alumno`),
  CONSTRAINT `FK_Prestamo_Alumno` FOREIGN KEY (`dni_alumno`) REFERENCES `Alumno` (`dni`),
  CONSTRAINT `FK_Prestamo_Libro` FOREIGN KEY (`codigo_libro`) REFERENCES `Libro` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO `Prestamo` (`dni_alumno`, `codigo_libro`, `fecha_prestamo`) VALUES 
('23456789X', 2, '2024-02-01 10:45:00'),
('34567890Y', 3, '2024-02-02 15:20:00'),
('45678901Z', 4, '2024-02-03 09:10:00'),
('56789012A', 5, '2024-02-04 12:30:00'),
('67890123B', 6, '2024-02-05 14:15:00'),
('78901234C', 7, '2024-02-06 11:05:00'),
('89012345D', 8, '2024-02-07 16:40:00'),
('90123456E', 9, '2024-02-08 08:55:00'),
('01234567F', 10, '2024-02-09 13:25:00'),
('12345678W', 11, '2024-02-10 17:00:00');
--
-- Dumping data for table `Prestamo`
--

/*!40000 ALTER TABLE `Prestamo` DISABLE KEYS */;
/*!40000 ALTER TABLE `Prestamo` ENABLE KEYS */;

--
-- Dumping routines for database 'libros'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-30 12:07:01
