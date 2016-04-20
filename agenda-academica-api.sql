-- Production
USE `sql5109263`;

-- Development
CREATE DATABASE `agenda-academica-api`;
USE `agenda-academica-api`;

CREATE TABLE IF NOT EXISTS `evento` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codigoUsuario` INT NOT NULL,
  `codigoDisciplina` INT,
  `codigoTurma` INT,
  `codigoCurso` INT,
  `codigoUnidade` INT ,
  `codigoUniversidade` INT NOT NULL,
  `titulo` VARCHAR(45) ,
  `descricao` TEXT ,
  `dataInicioEvento` DATETIME ,
  `dataFimEvento` DATETIME ,
  PRIMARY KEY (`codigo`))
ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS `Unidade` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codigoUniversidade` INT ,
  `nome` VARCHAR(45) ,
  PRIMARY KEY (`codigo`))
ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS `Curso` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codigoUnidade` INT ,
  `nome` VARCHAR(45) ,
  PRIMARY KEY (`codigo`))
ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS `InstituicaoDeEnsino` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codigoUsuario` INT ,
  `nome` VARCHAR(45) ,
   PRIMARY KEY (`codigo`))
ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS `Turma` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codigoCurso` INT ,
  `nome` VARCHAR(45) ,
  PRIMARY KEY (`codigo`))
ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS `Usuario` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(75) ,
  `login` VARCHAR(75) ,
  `email` VARCHAR(50) ,
  `celular` VARCHAR(20) ,
  `senha` VARCHAR(128) , -- Crypt: SHA512
  PRIMARY KEY (`codigo`))
ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS `Disciplina` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(75) ,
  `codigoTurma` INT,
  `horarioInicio` DATETIME ,
  `horarioFim` DATETIME ,
  `diaDaSemana` VARCHAR(25) ,
  `sala` VARCHAR(100) ,
  PRIMARY KEY (`codigo`))
ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS `Representante` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(75) ,
  `codigoTurma` VARCHAR(75) ,
  `email` VARCHAR(50) ,
  PRIMARY KEY (`codigo`))
ENGINE = MyISAM;
