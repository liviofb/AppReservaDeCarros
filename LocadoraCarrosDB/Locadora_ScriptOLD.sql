-- MySQL Script generated by MySQL Workbench
-- Fri Dec 17 00:16:33 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema dbLocadora
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dbLocadora
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dbLocadora` DEFAULT CHARACTER SET utf8 ;
USE `dbLocadora` ;

-- -----------------------------------------------------
-- Table `dbLocadora`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbLocadora`.`cliente` (
  `idcliente` INT NOT NULL AUTO_INCREMENT,
  `nome_cliente` VARCHAR(75) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  `email_cliente` VARCHAR(75) NOT NULL,
  `rua` VARCHAR(75) NOT NULL,
  `bairro` VARCHAR(75) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `uf` VARCHAR(2) NOT NULL,
  `cep` VARCHAR(9) NOT NULL,
  `celular` VARCHAR(11) NOT NULL,
  PRIMARY KEY (`idcliente`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE,
  UNIQUE INDEX `email_cliente_UNIQUE` (`email_cliente` ASC) VISIBLE,
  UNIQUE INDEX `telefone_UNIQUE` (`celular` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbLocadora`.`agencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbLocadora`.`agencia` (
  `idagencia` INT NOT NULL AUTO_INCREMENT,
  `rua` VARCHAR(45) NOT NULL,
  `numero` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `uf` VARCHAR(2) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `celular` VARCHAR(11) NOT NULL,
  PRIMARY KEY (`idagencia`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `relefone_UNIQUE` (`celular` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbLocadora`.`veiculo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbLocadora`.`veiculo` (
  `idveiculo` INT NOT NULL AUTO_INCREMENT,
  `placa` VARCHAR(8) NOT NULL,
  `modelo` VARCHAR(75) NOT NULL,
  `cor` VARCHAR(75) NOT NULL,
  `ano` VARCHAR(8) NOT NULL,
  `valor_dia` FLOAT NOT NULL,
  `km` INT NOT NULL,
  `combustivel` VARCHAR(45) NOT NULL,
  `agencia_idagencia` INT NOT NULL,
  PRIMARY KEY (`idveiculo`),
  UNIQUE INDEX `placa_UNIQUE` (`placa` ASC) VISIBLE,
  INDEX `fk_veiculo_agencia1_idx` (`agencia_idagencia` ASC) VISIBLE,
  CONSTRAINT `fk_veiculo_agencia1`
    FOREIGN KEY (`agencia_idagencia`)
    REFERENCES `dbLocadora`.`agencia` (`idagencia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbLocadora`.`reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbLocadora`.`reserva` (
  `idreserva` INT NOT NULL AUTO_INCREMENT,
  `data_retirada` VARCHAR(45) NOT NULL,
  `data_entrega` VARCHAR(45) NOT NULL,
  `valor` FLOAT NOT NULL,
  `cliente_idcliente` INT NOT NULL,
  `agencia_idagencia` INT NOT NULL,
  `veiculo_idveiculo` INT NOT NULL,
  PRIMARY KEY (`idreserva`),
  INDEX `fk_reserva_cliente1_idx` (`cliente_idcliente` ASC) VISIBLE,
  INDEX `fk_reserva_agencia1_idx` (`agencia_idagencia` ASC) VISIBLE,
  INDEX `fk_reserva_veiculo1_idx` (`veiculo_idveiculo` ASC) VISIBLE,
  CONSTRAINT `fk_reserva_cliente1`
    FOREIGN KEY (`cliente_idcliente`)
    REFERENCES `dbLocadora`.`cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_agencia1`
    FOREIGN KEY (`agencia_idagencia`)
    REFERENCES `dbLocadora`.`agencia` (`idagencia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_veiculo1`
    FOREIGN KEY (`veiculo_idveiculo`)
    REFERENCES `dbLocadora`.`veiculo` (`idveiculo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

insert into reserva values(default, "20/01/2022", "30/01/2022", 300, 1,1,1);
insert into reserva values(default, "05/02/2022", "13/03/2022", 500, 1,1,1);
insert into reserva values(default, "06/03/2022", "22/06/2022", 900, 1,1,1);
insert into reserva values(default, "31/01/2022", "12/07/2022", 2000, 1,1,1);

select reserva.idreserva, reserva.data_retirada, reserva.data_entrega,
reserva.valor, cliente.nome_cliente, agencia.idagencia, veiculo.idveiculo 
from reserva
inner join cliente on reserva.cliente_idcliente = cliente.idcliente
inner join agencia on reserva.agencia_idagencia = agencia.idagencia
inner join veiculo on reserva.veiculo_idveiculo = veiculo.idveiculo;

select * from reserva;
select * from veiculo;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;