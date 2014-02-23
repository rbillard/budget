-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Sam 01 Février 2014 à 20:29
-- Version du serveur: 5.6.12-log
-- Version de PHP: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `budget`
--
CREATE DATABASE IF NOT EXISTS `budget` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `budget`;

-- --------------------------------------------------------

--
-- Structure de la table `l_period_budget`
--

CREATE TABLE IF NOT EXISTS `l_period_budget` (
  `amount` decimal(19,2) DEFAULT NULL,
  `T_PERIOD_ID` bigint(20) NOT NULL DEFAULT '0',
  `T_BUDGET_ID` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`T_BUDGET_ID`,`T_PERIOD_ID`),
  KEY `FK_7w0kr36t1lwqkaa7far1e43ea` (`T_PERIOD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `l_period_budget`
--

INSERT INTO `l_period_budget` (`amount`, `T_PERIOD_ID`, `T_BUDGET_ID`) VALUES
('150.00', 1, 1),
('300.00', 1, 2),
('100.00', 1, 3),
('80.00', 1, 13);

-- --------------------------------------------------------

--
-- Structure de la table `t_budget`
--

CREATE TABLE IF NOT EXISTS `t_budget` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) NOT NULL,
  `color` char(7) NOT NULL,
  `T_USER_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jdgkjdgsjg5dkjg5djs6fjkd5` (`T_USER_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;

--
-- Contenu de la table `t_budget`
--

INSERT INTO `t_budget` (`id`, `label`, `color`, `T_USER_ID`) VALUES
(1, 'Nourriture', '#FFFFFF', 1),
(2, 'Loisirs', '#FF00FF', 1),
(3, 'Essence', '#FF0000', 1),
(13, 'Abonnement', '#FFFF00', 1);

-- --------------------------------------------------------

--
-- Structure de la table `t_operation`
--

CREATE TABLE IF NOT EXISTS `t_operation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` decimal(19,2) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `label` varchar(255) NOT NULL,
  `T_BUDGET_ID` bigint(20) NOT NULL,
  `T_PERIOD_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mql94xv969ok744ldtx8ku9n0` (`T_BUDGET_ID`,`T_PERIOD_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=65 ;

--
-- Contenu de la table `t_operation`
--

INSERT INTO `t_operation` (`id`, `amount`, `date`, `label`, `T_BUDGET_ID`, `T_PERIOD_ID`) VALUES
(59, '120.00', '2014-02-03 23:00:00', 'Carrefour', 1, 1),
(61, '45.94', '2014-02-01 20:27:41', 'Essence Carrefour', 3, 1),
(62, '15.99', '2014-04-14 22:00:00', 'Free mobile', 13, 1),
(63, '34.99', '2014-04-28 22:00:00', 'Free internet', 13, 1),
(64, '10.00', '2014-02-13 23:00:00', 'Cinéma', 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `t_period`
--

CREATE TABLE IF NOT EXISTS `t_period` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `endDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `label` varchar(255) NOT NULL,
  `startDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `T_USER_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_oh1t92uaipj8i65pgf7g2f1ex` (`T_USER_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;

--
-- Contenu de la table `t_period`
--

INSERT INTO `t_period` (`id`, `endDate`, `label`, `startDate`, `T_USER_ID`) VALUES
(1, '2014-01-30 23:00:00', 'Janvier 2014', '2013-12-31 23:00:00', 1),
(2, '2014-03-30 22:00:00', 'Mars 2014', '2014-02-28 23:00:00', 1),
(3, '2014-04-29 22:00:00', 'Avril 2014', '2014-03-31 22:00:00', 1),
(4, '2014-06-29 22:00:00', 'Juin 2014', '2014-05-31 22:00:00', 1),
(10, '2014-02-27 23:00:00', 'Février 2014', '2014-01-31 23:00:00', 1),
(11, '2014-05-30 22:00:00', 'Mai 2014', '2014-04-30 22:00:00', 1),
(12, '2014-07-30 22:00:00', 'Juillet 2014', '2014-06-30 22:00:00', 1);

-- --------------------------------------------------------

--
-- Structure de la table `t_user`
--

CREATE TABLE IF NOT EXISTS `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `t_user`
--

INSERT INTO `t_user` (`id`, `login`, `password`) VALUES
(1, 'rbillard', '$2a$10$o1PPyly8IBQDZB12k4Jlb.qHhGGi5LKh2S3u3SbILeC7aQZgSpFqy');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `l_period_budget`
--
ALTER TABLE `l_period_budget`
  ADD CONSTRAINT `FK_7w0kr36t1lwqkaa7far1e43ea` FOREIGN KEY (`T_PERIOD_ID`) REFERENCES `t_period` (`id`),
  ADD CONSTRAINT `FK_ne7q8d0o5xo8s4dgsk6xkgy8x` FOREIGN KEY (`T_BUDGET_ID`) REFERENCES `t_budget` (`id`);

--
-- Contraintes pour la table `t_budget`
--
ALTER TABLE `t_budget`
  ADD CONSTRAINT `FK_jdgkjdgsjg5dkjg5djs6fjkd5` FOREIGN KEY (`T_USER_ID`) REFERENCES `t_user` (`id`);

--
-- Contraintes pour la table `t_operation`
--
ALTER TABLE `t_operation`
  ADD CONSTRAINT `FK_mql94xv969ok744ldtx8ku9n0` FOREIGN KEY (`T_BUDGET_ID`, `T_PERIOD_ID`) REFERENCES `l_period_budget` (`T_BUDGET_ID`, `T_PERIOD_ID`);

--
-- Contraintes pour la table `t_period`
--
ALTER TABLE `t_period`
  ADD CONSTRAINT `FK_oh1t92uaipj8i65pgf7g2f1ex` FOREIGN KEY (`T_USER_ID`) REFERENCES `t_user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
