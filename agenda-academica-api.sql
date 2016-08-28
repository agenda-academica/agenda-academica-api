-- -- --
-- Production
-- -- --
USE `sql5109263`;

-- -- --
-- Development
-- -- --
CREATE DATABASE `agenda-academica-api`;
USE `agenda-academica-api`;

-- -- --
-- Creates
-- -- --
DROP TABLE IF EXISTS `Usuario`;
CREATE TABLE IF NOT EXISTS `Usuario` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(75) DEFAULT NULL,
  `login` varchar(75) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `celular` varchar(20) DEFAULT NULL,
  `senha` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Curso`;
CREATE TABLE IF NOT EXISTS `Curso` (
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
CREATE TABLE IF NOT EXISTS IF NOT EXISTS `Disciplina` (
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
CREATE TABLE IF NOT EXISTS `Evento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `idUniversidade` INT NOT NULL,
  `idUnidade` INT NOT NULL,
  `idCurso` INT NOT NULL,
  `idTurma` INT NOT NULL,
  `idDisciplina` INT NOT NULL,
  `tipo` ENUM('Prova', 'Trabalho', 'Outros') NOT NULL,
  `titulo` VARCHAR(100) NOT NULL,
  `descricao` TEXT NULL,
  `dataInicio` DATE NOT NULL,
  `dataFim` DATE NOT NULL,
  `horaInicio` TIME NOT NULL,
  `horaFim` TIME NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Universidade`;
CREATE TABLE IF NOT EXISTS `Universidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `nome` VARCHAR(200) NOT NULL,
  `abreviacao` VARCHAR(50) NOT NULL,
  `site` VARCHAR(150) NULL DEFAULT NULL,
  `logo` VARCHAR(150) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Representante`;
CREATE TABLE IF NOT EXISTS `Representante` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(75) DEFAULT NULL,
  `codigoTurma` varchar(75) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Turma`;
CREATE TABLE IF NOT EXISTS `Turma` (
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
CREATE TABLE IF NOT EXISTS `Unidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `idUniversidade` INT NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `endereco` TEXT NOT NULL,
  `outrasInformacoes` TEXT NULL,
  `unidadeSede` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `PeriodoLetivo`;
CREATE TABLE IF NOT EXISTS `agenda-academica-api`.`PeriodoLetivo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `idUniversidade` INT NOT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  `dataInicio` DATETIME NOT NULL,
  `dataFim` DATETIME NOT NULL,
  `cor` VARCHAR(19) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- -- --
-- Inserts
-- -- --

INSERT INTO `Usuario`
(`idUsuario`, `nome`, `abreviacao`, `site`) VALUES
(1, 'Gabriel Ramos Takeda', 'gabrielrtakeda', 'gabrielrtakeda@gmail.com', '11979999994', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2');

INSERT INTO `Universidade`
(`idUsuario`, `nome`, `abreviacao`, `site`) VALUES
(1 , 'Universidade São Judas Tadeu'                      , 'USJT'      , 'http://www.usjt.br'),
(1 , 'Faculdade de Informática e Administração Paulista' , 'FIAP'      , 'http://www.fiap.com.br'),
(1 , 'Faculdade Paulista Ciências Aplicadas'             , 'FPCA'      , 'http://www.fpca.com.br'),
(1 , 'Faculdade Paulista de Pesquisa e Ensino Superior'  , 'FAPPES'    , 'http://www.fappes.com.br'),
(1 , 'Faculdade Paulista de Serviço Social'              , 'FAPSS'     , 'http://www.fapss.com.br'),
(1 , 'Faculdade Paulus de Tecnologia e Comunicação'      , 'FAPCOM'    , 'http://www.fapcom.com.br'),
(1 , 'Faculdade Práxis'                                  , 'FIPEP'     , 'http://www.fipep.com.br'),
(1 , 'Faculdade Santa Marcelina'                         , 'FASM'      , 'http://www.fasm.com.br'),
(1 , 'Universidade Presbiteriana Mackenzie'              , 'Mackenzie' , 'http://www.mackenzie.com.br');

INSERT INTO .`Unidade`
(`idUsuario`, `idUniversidade`, `nome`, `endereco`, `outrasInformacoes`, `unidadeSede`) VALUES
(1 , 1 , 'Lasanha Valley'      , 'Rua Bolognous Avenue, 10'   , 'Take a ride!'        , 1),
(1 , 2 , 'lol'                 , 'Rua LOL, 6969'              , 'asdasd'              , 1),
(1 , 3 , 'Butantã'             , 'Rua Vital Brasil, 1000'     , 'null'                , 0),
(1 , 4 , 'Aclimação'           , 'Bairro Aclimação'           , NULL                  , 1),
(1 , 5 , 'Higienópolis'        , 'Rua da Consolação'          , NULL                  , 1),
(1 , 6 , 'Alphaville'          , 'Bairro Alphaville'          , NULL                  , 0),
(1 , 7 , 'Suspicious Mountain' , 'Rua Suspicious Road, 66'    , 'To Hell m/'          , 1),
(1 , 8 , 'São Paulo'           , 'Av. Prof. Almeida Prado'    , NULL                  , 0),
(1 , 9 , 'Sampa'               , 'Rua Sampa Mêmo'             , NULL                  , 0),
(1 , 1 , 'Paulista'            , 'Av. Paulista, 1000'         , NULL                  , 0),
(1 , 2 , 'Harry Potter'        , 'Rua Acathania'              , NULL                  , 0),
(1 , 3 , 'Alguma unidade'      , 'Rua Alguma Coisa, 123'      , NULL                  , 0),
(1 , 4 , 'São Paulo'           , 'Av. dos Bandeirantes, 2000' , 'Hello Informations.' , 1);
