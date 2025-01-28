-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Mar 28 Janvier 2025 à 10:53
-- Version du serveur :  5.7.11
-- Version de PHP :  5.6.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `mesi_h_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `commandes`
--

CREATE TABLE `commandes` (
  `id` int(11) NOT NULL,
  `utilisateur_id` int(11) NOT NULL,
  `voiture_id` int(11) NOT NULL,
  `date_commande` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `prix` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `commandes`
--

INSERT INTO `commandes` (`id`, `utilisateur_id`, `voiture_id`, `date_commande`, `prix`) VALUES
(1, 1, 1, '2025-01-09 23:00:00', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `date_inscription` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id`, `nom`, `email`, `mot_de_passe`, `date_inscription`) VALUES
(1, 'Jean Dupont', 'jean.dupont@example.com', 'motdepasse123', '2025-01-12 15:13:37');

-- --------------------------------------------------------

--
-- Structure de la table `voitures`
--

CREATE TABLE `voitures` (
  `id` int(11) NOT NULL,
  `marque` varchar(50) NOT NULL,
  `modele` varchar(50) NOT NULL,
  `annee` int(11) NOT NULL,
  `description` text,
  `chemin_image` varchar(255) NOT NULL,
  `prix` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `voitures`
--

INSERT INTO `voitures` (`id`, `marque`, `modele`, `annee`, `description`, `chemin_image`, `prix`) VALUES
(1, 'Alfa Romeo', 'Spider', 1966, 'Un roadster classique avec un design emblématique.', 'images/Alfa_Romeo_Spider_1966.jpg', '25000.00'),
(2, 'Aston Martin', 'DB5', 1964, 'La voiture de James Bond, alliant luxe et performance.', 'images\\Aston_Martin_DB5_1964.jpg', '250000.00'),
(3, 'Buick', 'Riviera', 1965, 'Une voiture avec un style audacieux et un confort exceptionnel.', 'images\\Buick_Riviera_1965.jpg', '20000.00'),
(4, 'Chevrolet', 'Camaro', 1969, 'Une muscle car emblématique, parfaite pour la performance.', 'images\\Chevrolet_Camaro_1969.jpg', '30000.00'),
(5, 'Chevrolet', 'Corvette', 1963, 'La Corvette, symbole de l\'excellence américaine en matière d\'automobile.', 'images\\Chevrolet_Corvette_1963.jpg', '50000.00'),
(6, 'Datsun', '240Z', 1970, 'Une voiture sportive populaire, appréciée pour son agilité.', 'images\\Datsun_240Z_1970.jpg', '25000.00'),
(7, 'Dodge', 'Charger', 1970, 'Une muscle car au design agressif et à la puissance impressionnante.', 'images\\Dodge_Charger_1970.jpg', '30000.00'),
(8, 'Fiat', '500', 1965, 'Une petite voiture avec beaucoup de charme et d\'élégance.', 'images\\Fiat_500_1965.jpg', '15000.00'),
(9, 'Ford', 'Bronco', 1970, 'Un SUV classique, parfait pour l\'aventure.', 'images\\Ford_Bronco_1970.jpg', '28000.00'),
(10, 'Ford', 'Mustang', 1965, 'Un symbole de la culture automobile américaine, alliant style et performance.', 'images\\Ford_Mustang_1965.jpg', '35000.00'),
(11, 'Jaguar', 'E-Type', 1961, 'Considérée comme l\'une des plus belles voitures de tous les temps.', 'images\\Jaguar_E-Type_1961.jpg', '60000.00'),
(12, 'Lamborghini', 'Miura', 1966, 'Une supercar iconique, alliant design et performance.', 'images\\Lamborghini_Miura_1966.jpg', '80000.00'),
(13, 'Mercedes-Benz', '300SL', 1955, 'Une combinaison parfaite de luxe et d\'ingénierie.', 'images\\Mercedes-Benz_300SL_1955.jpg', '120000.00'),
(14, 'MGB', 'Roadster', 1965, 'Un roadster britannique classique, apprécié pour son style.', 'images\\MGB_Roadster_1965.jpg', '22000.00'),
(15, 'Mini', 'Cooper', 1965, 'Une petite voiture avec un grand caractère et une maniabilité exceptionnelle.', 'images\\Mini_Cooper_1965.jpg', '18000.00'),
(16, 'Nissan', 'Skyline GT-R', 1999, 'Une voiture de sport japonaise, célèbre pour ses performances.', 'images\\Nissan_Skyline_GT-R_1999.jpg', '70000.00'),
(17, 'Pontiac', 'GTO', 1966, 'Une muscle car classique, connue pour sa puissance.', 'images\\Pontiac_GTO_1966.jpg', '40000.00'),
(18, 'Porsche', '911', 1973, 'Une voiture de sport emblématique, synonyme de performance.', 'images\\Porsche_911_1973.jpg', '90000.00'),
(19, 'Toyota', '2000GT', 1967, 'Une voiture sportive rare, reconnue pour son design et sa performance.', 'images\\Toyota_2000GT_1967.jpg', '60000.00'),
(20, 'Volkswagen', 'Beetle', 1970, 'La coccinelle, une voiture légendaire avec un charme intemporel.', 'images\\Volkswagen_Beetle_1970.jpg', '12000.00');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `utilisateur_id` (`utilisateur_id`),
  ADD KEY `voiture_id` (`voiture_id`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Index pour la table `voitures`
--
ALTER TABLE `voitures`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `commandes`
--
ALTER TABLE `commandes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `voitures`
--
ALTER TABLE `voitures`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD CONSTRAINT `commandes_ibfk_1` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateurs` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
