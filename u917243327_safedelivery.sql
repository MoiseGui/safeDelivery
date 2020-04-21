-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 21 avr. 2020 à 19:09
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP : 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `u917243327_safedelivery`
--

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

CREATE TABLE `adresse` (
  `id` int(11) NOT NULL,
  `detail` text NOT NULL,
  `id_zone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `adresse`
--

INSERT INTO `adresse` (`id`, `detail`, `id_zone`) VALUES
(2, 'hello', 10),
(3, 'hellome', 11),
(4, 'pas d\'adresse', 12);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `adresse` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `adresse`) VALUES
(19, 3),
(21, 4),
(22, 4);

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `total` double NOT NULL,
  `etat` varchar(45) NOT NULL DEFAULT 'En attente',
  `id_livreur` int(11) DEFAULT NULL,
  `dateCommande` datetime NOT NULL DEFAULT current_timestamp(),
  `dateLivraison` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id`, `id_client`, `total`, `etat`, `id_livreur`, `dateCommande`, `dateLivraison`) VALUES
(19, 19, 80, 'En attente', NULL, '2020-04-21 17:01:29', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `commande_item`
--

CREATE TABLE `commande_item` (
  `id_plat` int(11) NOT NULL,
  `id_commande` int(11) NOT NULL,
  `qte` int(11) NOT NULL,
  `etat` varchar(50) NOT NULL DEFAULT 'En attente'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `commande_item`
--

INSERT INTO `commande_item` (`id_plat`, `id_commande`, `qte`, `etat`) VALUES
(6, 19, 2, 'En attente');

-- --------------------------------------------------------

--
-- Structure de la table `livreur`
--

CREATE TABLE `livreur` (
  `id` int(11) NOT NULL,
  `busy` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `livreur`
--

INSERT INTO `livreur` (`id`, `busy`) VALUES
(19, 1),
(20, 1);

-- --------------------------------------------------------

--
-- Structure de la table `menu`
--

CREATE TABLE `menu` (
  `id_plat` int(11) NOT NULL,
  `id_restaurant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `menu`
--

INSERT INTO `menu` (`id_plat`, `id_restaurant`) VALUES
(1, 1),
(2, 2),
(4, 3),
(5, 3),
(6, 1),
(7, 1);

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

CREATE TABLE `panier` (
  `id` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `id_plat` int(11) NOT NULL,
  `qte` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `peut_livrer`
--

CREATE TABLE `peut_livrer` (
  `id_zone` int(11) NOT NULL,
  `id_livreur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `plat`
--

CREATE TABLE `plat` (
  `id` int(11) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `prix` double NOT NULL,
  `description` text NOT NULL,
  `image` varchar(100) NOT NULL,
  `deleted` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `plat`
--

INSERT INTO `plat` (`id`, `nom`, `prix`, `description`, `image`, `deleted`) VALUES
(1, 'Tacos Poulet', 30, 'Tacos au poulet', '', 0),
(2, 'Pasticcio', 28, 'C\'est du pasticcio', '', 0),
(4, 'Pizza Margarita', 20, 'La plus simple des pizza', '', 0),
(5, 'Pizza Viande hachée', 35, 'De le pizza et de la viande hachée', '', 0),
(6, 'Tanjia', 40, 'Tanjia fait maison', '', 0),
(7, 'Tacos Mixte', 40, '', '', 0);

-- --------------------------------------------------------

--
-- Structure de la table `restaurant`
--

CREATE TABLE `restaurant` (
  `id` int(11) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `adresse` int(11) NOT NULL,
  `id_restaurateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `restaurant`
--

INSERT INTO `restaurant` (`id`, `nom`, `adresse`, `id_restaurateur`) VALUES
(1, 'Tacos City', 4, 25),
(2, 'Cozamia', 2, 21),
(3, 'Pizza Hut', 2, 24);

-- --------------------------------------------------------

--
-- Structure de la table `restaurateur`
--

CREATE TABLE `restaurateur` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `restaurateur`
--

INSERT INTO `restaurateur` (`id`) VALUES
(21),
(24),
(25);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `pass` varchar(200) NOT NULL,
  `tel` varchar(45) NOT NULL,
  `categorie` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `pass`, `tel`, `categorie`, `enable`) VALUES
(19, 'acaf', 'yassir', 'yassir@yassir.com', 'DD1A9FC4AB9EEF00E556E3BA44911F637C4706F48C55BC06EB6023B07F8B299E', '0622334455', 0, 0),
(20, 'toto', 'toto', 'toto@toto.toto', '9A45271EFEF868A31EBBD528C407C678C33D8982871D92DA3A766C1283C12F69', '0622885734', 0, 0),
(21, 'zozo', 'zizi', 'zizi@gmail.com', '3CB5475B9FAE09996351E450C72D6A511C527A7C490468B5CAEC8825B177AC9F', '0622885643', 0, 0),
(22, 'Madame ezaghab', 'chaimaa', 'chaimaa@gmail.com', '0999A6992FC68052E7A745BD02C37EF1791CFF78E7A05E4CC8ECB03FD556BEC9', '0612345678', 0, 0),
(23, 'kmdslgkdsn', 'nmdfijm', 'user@gmail.com', '85E451106FB409544D0EDCA70F030BBE905ED7B7D1DE93CA20E5390A0A7F3FD5', '0699558855', 0, 0),
(24, 'Kodjo', 'Qmadou', 'amadou@gmail.com', '8BB0CF6EB9B17D0F7D22B456F121257DC1254E1F01665370476383EA776DF414', '0648374635', 0, 0),
(25, 'Gui', 'Moïse', 'contact@moisegui.com', '942C2F60936067DC7747D58246C66A3E9658D0E89EDA302EB3F801FB8B152B2F', '0699883590', 2, 0);

-- --------------------------------------------------------

--
-- Structure de la table `ville`
--

CREATE TABLE `ville` (
  `id` int(11) NOT NULL,
  `nom` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ville`
--

INSERT INTO `ville` (`id`, `nom`) VALUES
(1, 'konoha'),
(2, 'rabat'),
(3, 'paris'),
(4, 'newYork'),
(5, 'h1'),
(7, 'h2'),
(8, 'h3'),
(9, 'h5'),
(10, 'h7'),
(11, 'h99'),
(12, 'pas de ville');

-- --------------------------------------------------------

--
-- Structure de la table `zone`
--

CREATE TABLE `zone` (
  `id` int(11) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `id_ville` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `zone`
--

INSERT INTO `zone` (`id`, `nom`, `id_ville`) VALUES
(1, 'zone1', 1),
(3, 'zone2', 2),
(4, 'zone5', 8),
(5, 'zone6', 9),
(7, 'zone10022', 10),
(8, 'zone1002ozozoz', 10),
(9, 'hihuhuhuahaha', 10),
(10, 'zone51', 10),
(11, 'zone52', 11),
(12, 'pas de zone', 12);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkIdx_52` (`id_zone`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkIdx_26` (`id`),
  ADD KEY `fkIdx_57` (`adresse`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `livreur`
--
ALTER TABLE `livreur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkIdx_33` (`id`);

--
-- Index pour la table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id_plat`,`id_restaurant`),
  ADD KEY `fkIdx_88` (`id_restaurant`),
  ADD KEY `fkIdx_91` (`id_plat`);

--
-- Index pour la table `panier`
--
ALTER TABLE `panier`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `peut_livrer`
--
ALTER TABLE `peut_livrer`
  ADD PRIMARY KEY (`id_zone`,`id_livreur`),
  ADD KEY `fkIdx_61` (`id_livreur`),
  ADD KEY `fkIdx_64` (`id_zone`);

--
-- Index pour la table `plat`
--
ALTER TABLE `plat`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `restaurant`
--
ALTER TABLE `restaurant`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkIdx_72` (`adresse`),
  ADD KEY `fkIdx_84` (`id_restaurateur`);

--
-- Index pour la table `restaurateur`
--
ALTER TABLE `restaurateur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkIdx_29` (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `ville`
--
ALTER TABLE `ville`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `zone`
--
ALTER TABLE `zone`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkIdx_49` (`id_ville`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `adresse`
--
ALTER TABLE `adresse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `panier`
--
ALTER TABLE `panier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- AUTO_INCREMENT pour la table `plat`
--
ALTER TABLE `plat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `restaurant`
--
ALTER TABLE `restaurant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT pour la table `ville`
--
ALTER TABLE `ville`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `zone`
--
ALTER TABLE `zone`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD CONSTRAINT `FK_52` FOREIGN KEY (`id_zone`) REFERENCES `zone` (`id`);

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FK_26` FOREIGN KEY (`id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_57` FOREIGN KEY (`adresse`) REFERENCES `adresse` (`id`);

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FK_129` FOREIGN KEY (`id_livreur`) REFERENCES `livreur` (`id`),
  ADD CONSTRAINT `FK_95` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `livreur`
--
ALTER TABLE `livreur`
  ADD CONSTRAINT `FK_33` FOREIGN KEY (`id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `FK_88` FOREIGN KEY (`id_restaurant`) REFERENCES `restaurant` (`id`),
  ADD CONSTRAINT `FK_91` FOREIGN KEY (`id_plat`) REFERENCES `plat` (`id`);

--
-- Contraintes pour la table `peut_livrer`
--
ALTER TABLE `peut_livrer`
  ADD CONSTRAINT `FK_61` FOREIGN KEY (`id_livreur`) REFERENCES `livreur` (`id`),
  ADD CONSTRAINT `FK_64` FOREIGN KEY (`id_zone`) REFERENCES `zone` (`id`);

--
-- Contraintes pour la table `restaurant`
--
ALTER TABLE `restaurant`
  ADD CONSTRAINT `FK_72` FOREIGN KEY (`adresse`) REFERENCES `adresse` (`id`),
  ADD CONSTRAINT `FK_84` FOREIGN KEY (`id_restaurateur`) REFERENCES `restaurateur` (`id`);

--
-- Contraintes pour la table `restaurateur`
--
ALTER TABLE `restaurateur`
  ADD CONSTRAINT `FK_29` FOREIGN KEY (`id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `zone`
--
ALTER TABLE `zone`
  ADD CONSTRAINT `FK_49` FOREIGN KEY (`id_ville`) REFERENCES `ville` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
