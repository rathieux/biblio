-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le : jeu. 02 juil. 2026 à 07:41
-- Version du serveur : 8.0.44
-- Version de PHP : 8.3.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `biblio`
--

-- --------------------------------------------------------

--
-- Structure de la table `auteur`
--

CREATE TABLE `auteur` (
  `id` int NOT NULL,
  `nationalite` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `auteur`
--

INSERT INTO `auteur` (`id`, `nationalite`, `nom`, `prenom`) VALUES
(1, 'Britannique', 'Tolkien', 'J.R.R.'),
(2, 'Britannique', 'Rowling', 'J.K.'),
(3, 'Britannique', 'Orwell', 'George'),
(4, 'Française', 'Saint-Exupéry', 'Antoine de'),
(5, 'Américaine', 'Herbert', 'Frank'),
(6, 'Américaine', 'Asimov', 'Isaac'),
(7, 'Française', 'Hugo', 'Victor'),
(8, 'Française', 'Camus', 'Albert');

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

CREATE TABLE `avis` (
  `id` int NOT NULL,
  `commentaire` varchar(500) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `note` int NOT NULL,
  `livre_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `avis`
--

INSERT INTO `avis` (`id`, `commentaire`, `date`, `note`, `livre_id`) VALUES
(1, 'surkoté', '2026-08-14', 2, 1),
(2, 'Très bon.', '2026-06-30', 4, 2),
(3, 'Un classique incontournable.', '2026-06-29', 5, 3),
(4, 'Très poétique.', '2026-06-28', 4, 4),
(5, 'Chef-d\'œuvre de science-fiction.', '2026-06-27', 5, 5),
(6, 'Très intéressant.', '2026-06-26', 4, 6),
(7, 'Magnifique.', '2026-06-25', 5, 7),
(8, 'Pas mal.', '2026-06-24', 3, 8),
(9, 'Je n\'ai pas accroché.', '2026-06-23', 2, 2),
(12, 'Ouais\n', '2026-07-05', 5, 1),
(13, 'dezedds', '2026-07-16', 3, 3);

-- --------------------------------------------------------

--
-- Structure de la table `collection`
--

CREATE TABLE `collection` (
  `id` int NOT NULL,
  `nom` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `collection`
--

INSERT INTO `collection` (`id`, `nom`) VALUES
(1, 'Classiques'),
(2, 'Fantasy'),
(3, 'Science-Fiction'),
(4, 'Policier'),
(5, 'Jeunesse');

-- --------------------------------------------------------

--
-- Structure de la table `editeur`
--

CREATE TABLE `editeur` (
  `id` int NOT NULL,
  `nom` varchar(100) NOT NULL,
  `pays` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `editeur`
--

INSERT INTO `editeur` (`id`, `nom`, `pays`) VALUES
(1, 'Gallimard', 'France'),
(2, 'Hachette', 'France'),
(3, 'Pocket', 'France'),
(4, 'Albin Michel', 'France'),
(5, 'Penguin Books', 'Royaume-Uni');

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

CREATE TABLE `livre` (
  `id` int NOT NULL,
  `annee` int DEFAULT NULL,
  `resume` varchar(255) DEFAULT NULL,
  `titre` varchar(255) NOT NULL,
  `auteur_id` int NOT NULL,
  `collection_id` int DEFAULT NULL,
  `editeur_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`id`, `annee`, `resume`, `titre`, `auteur_id`, `collection_id`, `editeur_id`) VALUES
(1, 1954, 'Une quête pour détruire un anneau.', 'Le Seigneur des Anneaux', 1, 2, 1),
(2, 1997, 'Le premier tome des aventures de Harry Potter.', 'Harry Potter à l\'école des sorciers', 2, 5, 2),
(3, 1949, 'Une dystopie où Big Brother surveille tout.', '1984', 3, 1, 5),
(4, 1943, 'Le voyage d\'un jeune prince.', 'Le Petit Prince', 4, 5, 1),
(5, 1965, 'Une lutte pour le contrôle d\'Arrakis.', 'Dune', 5, 3, 3),
(6, 1951, 'L\'histoire de la Fondation.', 'Fondation', 6, 3, 3),
(7, 1862, 'Le destin de Jean Valjean.', 'Les Misérables', 7, 1, 1),
(8, 1942, 'L\'histoire de Meursault.', 'L\'Étranger', 8, 1, 4),
(10, 4, 'azdscs', 'aaaa', 2, 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `password`, `username`) VALUES
(1, '$2y$10$etqbOquE181VDQ7MPTLKBe6gED0mM0ACU8cDt5hQbTwMf.7ntC1VO', 'r');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `auteur`
--
ALTER TABLE `auteur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `avis`
--
ALTER TABLE `avis`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkuy2rlix4tlqhrmueiyijbxtr` (`livre_id`);

--
-- Index pour la table `collection`
--
ALTER TABLE `collection`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `editeur`
--
ALTER TABLE `editeur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh0pb6pxv3ubtgo1s3ev4gebgj` (`auteur_id`),
  ADD KEY `FKtdie3rsbf0cer3n22mhp4to53` (`collection_id`),
  ADD KEY `FKgowgjbkkxnvjykexh6nlmjjd7` (`editeur_id`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKkq7nt5wyq9v9lpcpgxag2f24a` (`username`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `auteur`
--
ALTER TABLE `auteur`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `avis`
--
ALTER TABLE `avis`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `collection`
--
ALTER TABLE `collection`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `editeur`
--
ALTER TABLE `editeur`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `livre`
--
ALTER TABLE `livre`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `avis`
--
ALTER TABLE `avis`
  ADD CONSTRAINT `FKkuy2rlix4tlqhrmueiyijbxtr` FOREIGN KEY (`livre_id`) REFERENCES `livre` (`id`);

--
-- Contraintes pour la table `livre`
--
ALTER TABLE `livre`
  ADD CONSTRAINT `FKgowgjbkkxnvjykexh6nlmjjd7` FOREIGN KEY (`editeur_id`) REFERENCES `editeur` (`id`),
  ADD CONSTRAINT `FKh0pb6pxv3ubtgo1s3ev4gebgj` FOREIGN KEY (`auteur_id`) REFERENCES `auteur` (`id`),
  ADD CONSTRAINT `FKtdie3rsbf0cer3n22mhp4to53` FOREIGN KEY (`collection_id`) REFERENCES `collection` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
