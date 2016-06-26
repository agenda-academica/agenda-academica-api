-- Production
USE `sql5109263`;

-- Development
CREATE DATABASE `agenda-academica-api`;
USE `agenda-academica-api`;

-----
-- Creates
-----
DROP TABLE IF EXISTS `Curso`;
CREATE TABLE `Curso` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `idUniversidade` INT NOT NULL,
  `idUnidade` INT NOT NULL,
  `abreviacao` VARCHAR(50) NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `outrasInformacoes` TEXT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Disciplina`;
CREATE TABLE IF NOT EXISTS `Disciplina` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `idUniversidade` INT NOT NULL,
  `idUnidade` INT NOT NULL,
  `idCurso` INT NOT NULL,
  `idTurma` INT NOT NULL,
  `abreviacao` VARCHAR(50) NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `horaInicio` TIME NOT NULL,
  `horaFim` TIME NOT NULL,
  `diaSemana` TINYINT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Evento`;
CREATE TABLE `Evento` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `codigoUsuario` int(11) NOT NULL,
  `codigoDisciplina` int(11) DEFAULT NULL,
  `codigoTurma` int(11) DEFAULT NULL,
  `codigoCurso` int(11) DEFAULT NULL,
  `codigoUnidade` int(11) DEFAULT NULL,
  `codigoUniversidade` int(11) NOT NULL,
  `titulo` varchar(45) DEFAULT NULL,
  `descricao` text,
  `dataInicioEvento` datetime DEFAULT NULL,
  `dataFimEvento` datetime DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Universidade`;
CREATE TABLE `Universidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `nome` VARCHAR(200) NOT NULL,
  `abreviacao` VARCHAR(50) NOT NULL,
  `site` VARCHAR(150) NULL DEFAULT NULL,
  `logo` VARCHAR(150) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Representante`;
CREATE TABLE `Representante` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(75) DEFAULT NULL,
  `codigoTurma` varchar(75) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Turma`;
CREATE TABLE `Turma` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `idUniversidade` INT NOT NULL,
  `idUnidade` INT NOT NULL,
  `idCurso` INT NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NULL,
  `site` VARCHAR(100) NULL,
  `outrasInformacoes` TEXT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Unidade`;
CREATE TABLE `Unidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `idUniversidade` INT NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `endereco` TEXT NOT NULL,
  `outrasInformacoes` TEXT NULL,
  `unidadeSede` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Usuario`;
CREATE TABLE `Usuario` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(75) DEFAULT NULL,
  `login` varchar(75) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `celular` varchar(20) DEFAULT NULL,
  `senha` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
