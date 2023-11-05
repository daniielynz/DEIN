DROP SCHEMA IF EXISTS `veterinaria` ;
CREATE SCHEMA IF NOT EXISTS `veterinaria` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci;

USE `veterinaria` ;

DROP TABLE IF EXISTS `veterinaria`.`Animal` ;

CREATE TABLE
    IF NOT EXISTS `veterinaria`.`Animal` (
        `codigo` INT NOT NULL AUTO_INCREMENT,
        `nombre` VARCHAR(250) NULL DEFAULT NULL,
        `especie` VARCHAR(250) NULL DEFAULT NULL,
        `raza` VARCHAR(250) NULL DEFAULT NULL,
        `sexo` VARCHAR(250) NULL DEFAULT NULL,
        `edad` INT NULL DEFAULT NULL,
        `peso` INT NULL DEFAULT NULL,
        `observaciones` VARCHAR(250) NULL DEFAULT NULL,
        `fecha_primera_cita` VARCHAR(250) NULL DEFAULT NULL,
        `foto` VARCHAR(250) NULL DEFAULT NULL,
        PRIMARY KEY (`codigo`)
    ) ENGINE = InnoDB DEFAULT CHARSET = latin1;