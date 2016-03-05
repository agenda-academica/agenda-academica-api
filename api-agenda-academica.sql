USE `sql5109263`;

CREATE TABLE IF NOT EXISTS `Anexo` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codigoConteudo` INT NOT NULL,
  `codigoTrabalho` INT NOT NULL,
  `codigoProva` INT NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `caminhoArquivo` VARCHAR(100) NOT NULL,
  `tipoArquivo` VARCHAR(20) NOT NULL,
  `descricaoArquivo` TEXT NOT NULL,
  `tamanhoArquivo` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS `AnoLetivo` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codigoInstituicaoDeEnsino` INT NOT NULL,
  `anoLetivo` VARCHAR(20) NOT NULL,
  `descricao` TEXT NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS `Aula` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codigoMateria` INT NOT NULL,
  `assunto` VARCHAR(45) NOT NULL,
  `data` TIMESTAMP NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS `Conteudo` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codigoAula` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` TEXT NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS `Curso` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codigoAnoLetivo` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` TEXT NOT NULL,
  `areaDoConhecimento` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS `InstituicaoDeEnsino` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codigoUsuario` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `site` VARCHAR(80) NOT NULL,
  `descricao` TEXT NOT NULL,
  `telefone` VARCHAR(20) NOT NULL,
  `unidade` VARCHAR(50) NOT NULL,
  `isProfessor` TINYINT(1) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS `Materia` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codigoCurso` INT NOT NULL,
  `codigoTurma` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` TEXT NOT NULL,
  `diaDaSemana` VARCHAR(10) NOT NULL,
  `sala` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS `Nota` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codigoProva` INT NOT NULL,
  `codigoTrabalho` INT NOT NULL,
  `codigoUsuario` INT NOT NULL,
  `descricao` TEXT NOT NULL,
  `nota` INT NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS `Prova` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codigoAula` INT NOT NULL,
  `dataDeEntrega` TIMESTAMP NOT NULL,
  `descricao` TEXT NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS `Trabalho` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codigoAula` INT NOT NULL,
  `dataDeEntrega` TIMESTAMP NOT NULL,
  `descricao` TEXT NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS `Turma` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `codigoCurso` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` TEXT NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = MyISAM;

CREATE TABLE IF NOT EXISTS `Usuario` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `celular` VARCHAR(20) NOT NULL,
  `senha` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = MyISAM;
