-- Production
USE `sql5109263`;

-- Development
CREATE DATABASE `agenda-academica-api`;
USE `agenda-academica-api`;

-----
-- Drops
-----
DROP TABLE `Curso`;
DROP TABLE `Disciplina`;
DROP TABLE `Evento`;
DROP TABLE `Universidade`;
DROP TABLE `Representante`;
DROP TABLE `Turma`;
DROP TABLE `Unidade`;
DROP TABLE `Usuario`;

-----
-- Creates
-----
CREATE TABLE `Curso` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `codigoUnidade` int(11) DEFAULT NULL,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `Disciplina` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `codigoTurma` int(11) DEFAULT NULL,
  `nome` varchar(75) DEFAULT NULL,
  `horarioInicio` datetime DEFAULT NULL,
  `horarioFim` datetime DEFAULT NULL,
  `diaDaSemana` varchar(25) DEFAULT NULL,
  `sala` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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

CREATE TABLE `Universidade` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `codigoUsuario` int(11) NOT NULL,
  `nome` varchar(200) NOT NULL,
  `abreviacao` varchar(45) DEFAULT NULL,
  `site` varchar(100) DEFAULT NULL,
  `logo` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `Representante` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(75) DEFAULT NULL,
  `codigoTurma` varchar(75) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `Turma` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `codigoCurso` int(11) DEFAULT NULL,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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

CREATE TABLE `Usuario` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(75) DEFAULT NULL,
  `login` varchar(75) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `celular` varchar(20) DEFAULT NULL,
  `senha` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
