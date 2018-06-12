-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               8.0.11 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for fant_cric
CREATE DATABASE IF NOT EXISTS `fant_cric` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `fant_cric`;

-- Dumping structure for table fant_cric.city
CREATE TABLE IF NOT EXISTS `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `country_id` int(11) NOT NULL DEFAULT '2',
  PRIMARY KEY (`id`),
  KEY `FK_city_country` (`country_id`),
  CONSTRAINT `FK_city_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table fant_cric.city: ~31 rows (approximately)
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
REPLACE INTO `city` (`id`, `name`, `country_id`) VALUES
	(2, 'Ahmedabad', 2),
	(3, 'Delhi', 2),
	(4, 'Sharjah', 4),
	(5, 'Rajkot', 2),
	(6, 'Cape Town', 3),
	(7, 'Centurion', 3),
	(8, 'Johannesburg', 3),
	(9, 'Indore', 2),
	(10, 'Mumbai', 2),
	(11, 'Dubai', 4),
	(12, 'Kimberley', 3),
	(13, 'Kanpur', 2),
	(14, 'Chennai', 2),
	(15, 'Visakhapatnam', 2),
	(16, 'Jaipur', 2),
	(17, 'East London', 3),
	(18, 'Durban', 3),
	(19, 'Bengaluru', 2),
	(20, 'Chandigarh', 2),
	(21, 'Raipur', 2),
	(22, 'Bloemfontein', 3),
	(23, 'Dharamsala', 2),
	(24, 'Kolkata', 2),
	(25, 'Hyderabad', 2),
	(26, 'Abu Dhabi', 4),
	(27, 'Ranchi', 2),
	(28, 'Kochi', 2),
	(29, 'Cuttack', 2),
	(30, 'Nagpur', 2),
	(31, 'Pune', 2),
	(32, 'Port Elizabeth', 3);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;

-- Dumping structure for table fant_cric.country
CREATE TABLE IF NOT EXISTS `country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table fant_cric.country: ~3 rows (approximately)
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
REPLACE INTO `country` (`id`, `name`) VALUES
	(2, 'India'),
	(3, 'South Africa'),
	(4, 'United Arab Emirates');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;

-- Dumping structure for table fant_cric.league
CREATE TABLE IF NOT EXISTS `league` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '0',
  `tournament_id` int(11) NOT NULL,
  `points` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_league_tournament` (`tournament_id`),
  CONSTRAINT `FK_league_tournament` FOREIGN KEY (`tournament_id`) REFERENCES `tournament` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table fant_cric.league: ~27 rows (approximately)
/*!40000 ALTER TABLE `league` DISABLE KEYS */;
REPLACE INTO `league` (`id`, `name`, `tournament_id`, `points`) VALUES
	(1, 'te_league', 1, 0),
	(2, 'test_another', 1, 0),
	(3, 'test_league', 1, 0),
	(4, 'vcnxnb', 1, 0),
	(5, 'new league', 1, 0),
	(6, 'new league', 1, 0),
	(8, 'final league', 1, 0),
	(9, 'final league', 1, 0),
	(10, 'final league', 1, 0),
	(11, 't_league', 1, 0),
	(12, 'te_league', 1, 0),
	(13, 'te_league', 1, 0),
	(14, 'te_league', 1, 0),
	(15, 'te_league', 1, 0),
	(16, 'te_league', 1, 0),
	(17, 'te_leagueig', 1, 0),
	(18, 'te_leagueig', 1, 0),
	(19, 'tsdfdge_leagueig', 1, 0),
	(20, 'My League', 1, 0),
	(21, 'My League', 1, 0),
	(22, 'my test league', 1, 0),
	(23, 'my test league', 1, 0),
	(24, 'my test league', 1, 0),
	(26, 'my test league', 1, 0),
	(27, 'my test league', 1, 0),
	(28, 'my test league', 1, 0),
	(29, 'my test league', 1, 0);
/*!40000 ALTER TABLE `league` ENABLE KEYS */;

-- Dumping structure for table fant_cric.league_squad
CREATE TABLE IF NOT EXISTS `league_squad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `league_id` int(11) NOT NULL DEFAULT '0',
  `squad_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `league_id_squad_id` (`league_id`,`squad_id`),
  KEY `FK_league_squad_squad` (`squad_id`),
  CONSTRAINT `FK_league_squad_league` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`),
  CONSTRAINT `FK_league_squad_squad` FOREIGN KEY (`squad_id`) REFERENCES `squad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table fant_cric.league_squad: ~9 rows (approximately)
/*!40000 ALTER TABLE `league_squad` DISABLE KEYS */;
REPLACE INTO `league_squad` (`id`, `league_id`, `squad_id`) VALUES
	(22, 1, 17),
	(27, 1, 18),
	(21, 1, 19),
	(23, 1, 21),
	(28, 1, 22),
	(29, 1, 23),
	(24, 2, 17),
	(25, 3, 17),
	(26, 4, 17);
/*!40000 ALTER TABLE `league_squad` ENABLE KEYS */;

-- Dumping structure for table fant_cric.lockin
CREATE TABLE IF NOT EXISTS `lockin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `match_id` int(11) NOT NULL DEFAULT '0',
  `squad_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  `power_type` varchar(32) DEFAULT NULL,
  `batting_points` int(11) DEFAULT '0',
  `bowling_points` int(11) DEFAULT '0',
  `fielding_points` int(11) DEFAULT '0',
  `bonus_points` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `league_member_id_player_id_match_id` (`squad_id`,`player_id`,`match_id`),
  KEY `FK_member_squad_player` (`player_id`),
  KEY `FK_lockin_match` (`match_id`),
  CONSTRAINT `FK_lockin_match` FOREIGN KEY (`match_id`) REFERENCES `match` (`id`),
  CONSTRAINT `lockin_ibfk_2` FOREIGN KEY (`squad_id`) REFERENCES `squad` (`id`),
  CONSTRAINT `lockin_ibfk_3` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table fant_cric.lockin: ~154 rows (approximately)
/*!40000 ALTER TABLE `lockin` DISABLE KEYS */;
REPLACE INTO `lockin` (`id`, `match_id`, `squad_id`, `player_id`, `power_type`, `batting_points`, `bowling_points`, `fielding_points`, `bonus_points`) VALUES
	(1, 4, 17, 377, NULL, 26, 111, 0, 0),
	(2, 4, 17, 385, NULL, 70, 0, 10, 0),
	(3, 4, 17, 464, NULL, 138, 31, 0, 30),
	(4, 4, 17, 466, NULL, 5, 0, 0, 5),
	(5, 4, 17, 361, NULL, 15, 0, 10, 0),
	(6, 4, 17, 362, NULL, 0, 29, 10, 0),
	(7, 4, 17, 375, NULL, 0, 24, 10, 0),
	(8, 4, 17, 475, 'BATTING', 4, 0, 0, 5),
	(9, 4, 17, 378, NULL, 74, 0, 10, 0),
	(10, 4, 17, 465, 'BOWLING', -6, 116, 0, 5),
	(11, 4, 17, 482, NULL, 21, 71, 0, 5),
	(12, 4, 18, 378, NULL, 74, 0, 10, 0),
	(13, 4, 18, 385, NULL, 70, 0, 10, 0),
	(14, 4, 18, 377, NULL, 26, 111, 0, 0),
	(15, 4, 18, 362, NULL, 0, 29, 10, 0),
	(16, 4, 18, 482, NULL, 21, 71, 0, 5),
	(17, 4, 18, 475, 'BATTING', 4, 0, 0, 5),
	(18, 4, 18, 375, NULL, 0, 24, 10, 0),
	(19, 4, 18, 464, NULL, 138, 31, 0, 30),
	(20, 4, 18, 465, 'BOWLING', -6, 116, 0, 5),
	(21, 4, 18, 361, NULL, 15, 0, 10, 0),
	(22, 4, 18, 466, NULL, 5, 0, 0, 5),
	(23, 4, 19, 385, NULL, 70, 0, 10, 0),
	(24, 4, 19, 482, NULL, 21, 71, 0, 5),
	(25, 4, 19, 464, NULL, 138, 31, 0, 30),
	(26, 4, 19, 466, NULL, 5, 0, 0, 5),
	(27, 4, 19, 362, NULL, 0, 29, 10, 0),
	(28, 4, 19, 361, NULL, 15, 0, 10, 0),
	(29, 4, 19, 375, NULL, 0, 24, 10, 0),
	(30, 4, 19, 377, NULL, 26, 111, 0, 0),
	(31, 4, 19, 465, 'BOWLING', -6, 116, 0, 5),
	(32, 4, 19, 475, 'BATTING', 4, 0, 0, 5),
	(33, 4, 19, 378, NULL, 74, 0, 10, 0),
	(34, 4, 21, 362, NULL, 0, 29, 10, 0),
	(35, 4, 21, 464, NULL, 138, 31, 0, 30),
	(36, 4, 21, 379, NULL, 0, 0, 0, 0),
	(37, 4, 21, 475, 'BATTING', 4, 0, 0, 5),
	(38, 4, 21, 482, NULL, 21, 71, 0, 5),
	(39, 4, 21, 361, NULL, 15, 0, 10, 0),
	(40, 4, 21, 375, NULL, 0, 24, 10, 0),
	(41, 4, 21, 465, 'BOWLING', -6, 116, 0, 5),
	(42, 4, 21, 378, NULL, 74, 0, 10, 0),
	(43, 4, 21, 385, NULL, 70, 0, 10, 0),
	(44, 4, 21, 466, NULL, 5, 0, 0, 5),
	(45, 4, 22, 465, 'BOWLING', -6, 116, 0, 5),
	(46, 4, 22, 361, NULL, 15, 0, 10, 0),
	(47, 4, 22, 464, NULL, 138, 31, 0, 30),
	(48, 4, 22, 375, NULL, 0, 24, 10, 0),
	(49, 4, 22, 385, NULL, 70, 0, 10, 0),
	(50, 4, 22, 466, NULL, 5, 0, 0, 5),
	(51, 4, 22, 377, NULL, 26, 111, 0, 0),
	(52, 4, 22, 482, NULL, 21, 71, 0, 5),
	(53, 4, 22, 378, NULL, 74, 0, 10, 0),
	(54, 4, 22, 362, NULL, 0, 29, 10, 0),
	(55, 4, 22, 475, 'BATTING', 4, 0, 0, 5),
	(56, 4, 23, 465, 'BOWLING', -6, 116, 0, 5),
	(57, 4, 23, 482, NULL, 21, 71, 0, 5),
	(58, 4, 23, 378, NULL, 74, 0, 10, 0),
	(59, 4, 23, 464, NULL, 138, 31, 0, 30),
	(60, 4, 23, 362, NULL, 0, 29, 10, 0),
	(61, 4, 23, 375, NULL, 0, 24, 10, 0),
	(62, 4, 23, 475, 'BATTING', 4, 0, 0, 5),
	(63, 4, 23, 466, NULL, 5, 0, 0, 5),
	(64, 4, 23, 377, NULL, 26, 111, 0, 0),
	(65, 4, 23, 385, NULL, 70, 0, 10, 0),
	(66, 4, 23, 361, NULL, 15, 0, 10, 0),
	(67, 4, 24, 362, NULL, 0, 29, 10, 0),
	(68, 4, 24, 378, NULL, 74, 0, 10, 0),
	(69, 4, 24, 475, 'BATTING', 4, 0, 0, 5),
	(70, 4, 24, 482, NULL, 21, 71, 0, 5),
	(71, 4, 24, 385, NULL, 70, 0, 10, 0),
	(72, 4, 24, 465, 'BOWLING', -6, 116, 0, 5),
	(73, 4, 24, 377, NULL, 26, 111, 0, 0),
	(74, 4, 24, 464, NULL, 138, 31, 0, 30),
	(75, 4, 24, 361, NULL, 15, 0, 10, 0),
	(76, 4, 24, 466, NULL, 5, 0, 0, 5),
	(77, 4, 24, 375, NULL, 0, 24, 10, 0),
	(78, 5, 17, 362, NULL, NULL, NULL, NULL, NULL),
	(79, 5, 17, 482, NULL, NULL, NULL, NULL, NULL),
	(80, 5, 17, 465, 'BOWLING', NULL, NULL, NULL, NULL),
	(81, 5, 17, 353, NULL, NULL, NULL, NULL, NULL),
	(82, 5, 17, 385, NULL, NULL, NULL, NULL, NULL),
	(83, 5, 17, 375, NULL, NULL, NULL, NULL, NULL),
	(84, 5, 17, 378, NULL, NULL, NULL, NULL, NULL),
	(85, 5, 17, 475, 'BATTING', NULL, NULL, NULL, NULL),
	(86, 5, 17, 377, NULL, NULL, NULL, NULL, NULL),
	(87, 5, 17, 361, NULL, NULL, NULL, NULL, NULL),
	(88, 5, 17, 464, NULL, NULL, NULL, NULL, NULL),
	(89, 5, 18, 385, NULL, NULL, NULL, NULL, NULL),
	(90, 5, 18, 475, 'BATTING', NULL, NULL, NULL, NULL),
	(91, 5, 18, 465, 'BOWLING', NULL, NULL, NULL, NULL),
	(92, 5, 18, 362, NULL, NULL, NULL, NULL, NULL),
	(93, 5, 18, 482, NULL, NULL, NULL, NULL, NULL),
	(94, 5, 18, 361, NULL, NULL, NULL, NULL, NULL),
	(95, 5, 18, 464, NULL, NULL, NULL, NULL, NULL),
	(96, 5, 18, 375, NULL, NULL, NULL, NULL, NULL),
	(97, 5, 18, 466, NULL, NULL, NULL, NULL, NULL),
	(98, 5, 18, 378, NULL, NULL, NULL, NULL, NULL),
	(99, 5, 18, 377, NULL, NULL, NULL, NULL, NULL),
	(100, 5, 19, 375, NULL, NULL, NULL, NULL, NULL),
	(101, 5, 19, 482, NULL, NULL, NULL, NULL, NULL),
	(102, 5, 19, 466, NULL, NULL, NULL, NULL, NULL),
	(103, 5, 19, 361, NULL, NULL, NULL, NULL, NULL),
	(104, 5, 19, 362, NULL, NULL, NULL, NULL, NULL),
	(105, 5, 19, 378, NULL, NULL, NULL, NULL, NULL),
	(106, 5, 19, 464, NULL, NULL, NULL, NULL, NULL),
	(107, 5, 19, 377, NULL, NULL, NULL, NULL, NULL),
	(108, 5, 19, 475, 'BATTING', NULL, NULL, NULL, NULL),
	(109, 5, 19, 385, NULL, NULL, NULL, NULL, NULL),
	(110, 5, 19, 465, 'BOWLING', NULL, NULL, NULL, NULL),
	(111, 5, 21, 375, NULL, NULL, NULL, NULL, NULL),
	(112, 5, 21, 385, NULL, NULL, NULL, NULL, NULL),
	(113, 5, 21, 362, NULL, NULL, NULL, NULL, NULL),
	(114, 5, 21, 475, 'BATTING', NULL, NULL, NULL, NULL),
	(115, 5, 21, 378, NULL, NULL, NULL, NULL, NULL),
	(116, 5, 21, 466, NULL, NULL, NULL, NULL, NULL),
	(117, 5, 21, 482, NULL, NULL, NULL, NULL, NULL),
	(118, 5, 21, 464, NULL, NULL, NULL, NULL, NULL),
	(119, 5, 21, 465, 'BOWLING', NULL, NULL, NULL, NULL),
	(120, 5, 21, 379, NULL, NULL, NULL, NULL, NULL),
	(121, 5, 21, 361, NULL, NULL, NULL, NULL, NULL),
	(122, 5, 22, 377, NULL, NULL, NULL, NULL, NULL),
	(123, 5, 22, 378, NULL, NULL, NULL, NULL, NULL),
	(124, 5, 22, 466, NULL, NULL, NULL, NULL, NULL),
	(125, 5, 22, 465, 'BOWLING', NULL, NULL, NULL, NULL),
	(126, 5, 22, 385, NULL, NULL, NULL, NULL, NULL),
	(127, 5, 22, 361, NULL, NULL, NULL, NULL, NULL),
	(128, 5, 22, 464, NULL, NULL, NULL, NULL, NULL),
	(129, 5, 22, 482, NULL, NULL, NULL, NULL, NULL),
	(130, 5, 22, 362, NULL, NULL, NULL, NULL, NULL),
	(131, 5, 22, 375, NULL, NULL, NULL, NULL, NULL),
	(132, 5, 22, 475, 'BATTING', NULL, NULL, NULL, NULL),
	(133, 5, 23, 377, NULL, NULL, NULL, NULL, NULL),
	(134, 5, 23, 466, NULL, NULL, NULL, NULL, NULL),
	(135, 5, 23, 482, NULL, NULL, NULL, NULL, NULL),
	(136, 5, 23, 465, 'BOWLING', NULL, NULL, NULL, NULL),
	(137, 5, 23, 385, NULL, NULL, NULL, NULL, NULL),
	(138, 5, 23, 475, 'BATTING', NULL, NULL, NULL, NULL),
	(139, 5, 23, 464, NULL, NULL, NULL, NULL, NULL),
	(140, 5, 23, 362, NULL, NULL, NULL, NULL, NULL),
	(141, 5, 23, 375, NULL, NULL, NULL, NULL, NULL),
	(142, 5, 23, 361, NULL, NULL, NULL, NULL, NULL),
	(143, 5, 23, 378, NULL, NULL, NULL, NULL, NULL),
	(144, 5, 24, 378, NULL, NULL, NULL, NULL, NULL),
	(145, 5, 24, 375, NULL, NULL, NULL, NULL, NULL),
	(146, 5, 24, 465, 'BOWLING', NULL, NULL, NULL, NULL),
	(147, 5, 24, 475, 'BATTING', NULL, NULL, NULL, NULL),
	(148, 5, 24, 377, NULL, NULL, NULL, NULL, NULL),
	(149, 5, 24, 482, NULL, NULL, NULL, NULL, NULL),
	(150, 5, 24, 361, NULL, NULL, NULL, NULL, NULL),
	(151, 5, 24, 362, NULL, NULL, NULL, NULL, NULL),
	(152, 5, 24, 464, NULL, NULL, NULL, NULL, NULL),
	(153, 5, 24, 385, NULL, NULL, NULL, NULL, NULL),
	(154, 5, 24, 466, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `lockin` ENABLE KEYS */;

-- Dumping structure for table fant_cric.match
CREATE TABLE IF NOT EXISTS `match` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scheduled_start` datetime NOT NULL,
  `locked_in` bit(1) NOT NULL DEFAULT b'0',
  `points_updated` tinyint(1) NOT NULL DEFAULT '0',
  `score_updated` bit(1) NOT NULL DEFAULT b'0',
  `venue_id` int(11) NOT NULL,
  `seq_num` int(11) DEFAULT NULL,
  `team1_id` int(11) NOT NULL,
  `team2_id` int(11) NOT NULL,
  `tournament_id` int(11) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `result` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `seq_num_tournament_id` (`seq_num`,`tournament_id`),
  KEY `FK_match_team` (`team1_id`),
  KEY `FK_match_team_2` (`team2_id`),
  KEY `FK_match_tournament` (`tournament_id`),
  KEY `FK_match_venue` (`venue_id`),
  CONSTRAINT `FK_match_team` FOREIGN KEY (`team1_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FK_match_team_2` FOREIGN KEY (`team2_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FK_match_tournament` FOREIGN KEY (`tournament_id`) REFERENCES `tournament` (`id`),
  CONSTRAINT `FK_match_venue` FOREIGN KEY (`venue_id`) REFERENCES `venue` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table fant_cric.match: ~60 rows (approximately)
/*!40000 ALTER TABLE `match` DISABLE KEYS */;
REPLACE INTO `match` (`id`, `scheduled_start`, `locked_in`, `points_updated`, `score_updated`, `venue_id`, `seq_num`, `team1_id`, `team2_id`, `tournament_id`, `description`, `result`) VALUES
	(4, '2018-04-07 20:00:00', b'1', 1, b'1', 14, 1, 5, 7, 1, '1st match - Mumbai Indians v Chennai Super Kings', 'TEAM2_WIN'),
	(5, '2018-04-08 16:00:00', b'0', 0, b'0', 20, 2, 4, 6, 1, '2nd match - Kings XI Punjab v Delhi Daredevils', NULL),
	(6, '2018-04-08 20:00:00', b'0', 0, b'0', 24, 3, 8, 15, 1, '3rd match - Kolkata Knight Riders v Royal Challengers Bangalore', NULL),
	(7, '2018-04-09 20:00:00', b'0', 0, b'0', 27, 4, 3, 9, 1, '4th match - Sunrisers Hyderabad v Rajasthan Royals', NULL),
	(8, '2018-04-10 20:00:00', b'0', 0, b'0', 21, 5, 7, 8, 1, '5th match - Chennai Super Kings v Kolkata Knight Riders', NULL),
	(9, '2018-04-11 20:00:00', b'0', 0, b'0', 9, 6, 9, 6, 1, '6th match - Rajasthan Royals v Delhi Daredevils', NULL),
	(10, '2018-04-12 20:00:00', b'0', 0, b'0', 27, 7, 3, 5, 1, '7th match - Sunrisers Hyderabad v Mumbai Indians', NULL),
	(11, '2018-04-13 20:00:00', b'0', 0, b'0', 11, 8, 15, 4, 1, '8th match - Royal Challengers Bangalore v Kings XI Punjab', NULL),
	(12, '2018-04-14 16:00:00', b'0', 0, b'0', 14, 9, 5, 6, 1, '9th match - Mumbai Indians v Delhi Daredevils', NULL),
	(13, '2018-04-14 20:00:00', b'0', 0, b'0', 24, 10, 8, 3, 1, '10th match - Kolkata Knight Riders v Sunrisers Hyderabad', NULL),
	(14, '2018-04-15 16:00:00', b'0', 0, b'0', 11, 11, 15, 9, 1, '11th match - Royal Challengers Bangalore v Rajasthan Royals', NULL),
	(15, '2018-04-15 20:00:00', b'0', 0, b'0', 20, 12, 4, 7, 1, '12th match - Kings XI Punjab v Chennai Super Kings', NULL),
	(16, '2018-04-16 20:00:00', b'0', 0, b'0', 24, 13, 8, 6, 1, '13th match - Kolkata Knight Riders v Delhi Daredevils', NULL),
	(17, '2018-04-17 20:00:00', b'0', 0, b'0', 14, 14, 5, 15, 1, '14th match - Mumbai Indians v Royal Challengers Bangalore', NULL),
	(18, '2018-04-18 20:00:00', b'0', 0, b'0', 9, 15, 9, 8, 1, '15th match - Rajasthan Royals v Kolkata Knight Riders', NULL),
	(19, '2018-04-19 20:00:00', b'0', 0, b'0', 20, 16, 4, 3, 1, '16th match - Kings XI Punjab v Sunrisers Hyderabad', NULL),
	(20, '2018-04-20 20:00:00', b'0', 0, b'0', 18, 17, 7, 9, 1, '17th match - Chennai Super Kings v Rajasthan Royals', NULL),
	(21, '2018-04-21 16:00:00', b'0', 0, b'0', 24, 18, 8, 4, 1, '18th match - Kolkata Knight Riders v Kings XI Punjab', NULL),
	(22, '2018-04-21 20:00:00', b'0', 0, b'0', 11, 19, 15, 6, 1, '19th match - Royal Challengers Bangalore v Delhi Daredevils', NULL),
	(23, '2018-04-22 16:00:00', b'0', 0, b'0', 27, 20, 3, 7, 1, '20th match - Sunrisers Hyderabad v Chennai Super Kings', NULL),
	(24, '2018-04-22 20:00:00', b'0', 0, b'0', 9, 21, 9, 5, 1, '21st match - Rajasthan Royals v Mumbai Indians', NULL),
	(25, '2018-04-23 20:00:00', b'0', 0, b'0', 7, 22, 6, 4, 1, '22nd match - Delhi Daredevils v Kings XI Punjab', NULL),
	(26, '2018-04-24 20:00:00', b'0', 0, b'0', 14, 23, 5, 3, 1, '23rd match - Mumbai Indians v Sunrisers Hyderabad', NULL),
	(27, '2018-04-25 20:00:00', b'0', 0, b'0', 11, 24, 15, 7, 1, '24th match - Royal Challengers Bangalore v Chennai Super Kings', NULL),
	(28, '2018-04-26 20:00:00', b'0', 0, b'0', 27, 25, 3, 4, 1, '25th match - Sunrisers Hyderabad v Kings XI Punjab', NULL),
	(29, '2018-04-27 20:00:00', b'0', 0, b'0', 7, 26, 6, 8, 1, '26th match - Delhi Daredevils v Kolkata Knight Riders', NULL),
	(30, '2018-04-28 20:00:00', b'0', 0, b'0', 18, 27, 7, 5, 1, '27th match - Chennai Super Kings v Mumbai Indians', NULL),
	(31, '2018-04-29 16:00:00', b'0', 0, b'0', 9, 28, 9, 3, 1, '28th match - Rajasthan Royals v Sunrisers Hyderabad', NULL),
	(32, '2018-04-29 20:00:00', b'0', 0, b'0', 11, 29, 15, 8, 1, '29th match - Royal Challengers Bangalore v Kolkata Knight Riders', NULL),
	(33, '2018-04-30 20:00:00', b'0', 0, b'0', 18, 30, 7, 6, 1, '30th match - Chennai Super Kings v Delhi Daredevils', NULL),
	(34, '2018-05-01 20:00:00', b'0', 0, b'0', 11, 31, 15, 5, 1, '31st match - Royal Challengers Bangalore v Mumbai Indians', NULL),
	(35, '2018-05-02 20:00:00', b'0', 0, b'0', 7, 32, 6, 9, 1, '32nd match - Delhi Daredevils v Rajasthan Royals', NULL),
	(36, '2018-05-03 20:00:00', b'0', 0, b'0', 24, 33, 8, 7, 1, '33rd match - Kolkata Knight Riders v Chennai Super Kings', NULL),
	(37, '2018-05-04 20:00:00', b'0', 0, b'0', 23, 34, 4, 5, 1, '34th match - Kings XI Punjab v Mumbai Indians', NULL),
	(38, '2018-05-05 16:00:00', b'0', 0, b'0', 18, 35, 7, 15, 1, '35th match - Chennai Super Kings v Royal Challengers Bangalore', NULL),
	(39, '2018-05-05 20:00:00', b'0', 0, b'0', 27, 36, 3, 6, 1, '36th match - Sunrisers Hyderabad v Delhi Daredevils', NULL),
	(40, '2018-05-06 16:00:00', b'0', 0, b'0', 14, 37, 5, 8, 1, '37th match - Mumbai Indians v Kolkata Knight Riders', NULL),
	(41, '2018-05-06 20:00:00', b'0', 0, b'0', 23, 38, 4, 9, 1, '38th match - Kings XI Punjab v Rajasthan Royals', NULL),
	(42, '2018-05-07 20:00:00', b'0', 0, b'0', 27, 39, 3, 15, 1, '39th match - Sunrisers Hyderabad v Royal Challengers Bangalore', NULL),
	(43, '2018-05-08 20:00:00', b'0', 0, b'0', 9, 40, 9, 4, 1, '40th match - Rajasthan Royals v Kings XI Punjab', NULL),
	(44, '2018-05-09 20:00:00', b'0', 0, b'0', 24, 41, 8, 5, 1, '41st match - Kolkata Knight Riders v Mumbai Indians', NULL),
	(45, '2018-05-10 20:00:00', b'0', 0, b'0', 7, 42, 6, 3, 1, '42nd match - Delhi Daredevils v Sunrisers Hyderabad', NULL),
	(46, '2018-05-11 20:00:00', b'0', 0, b'0', 9, 43, 9, 7, 1, '43rd match - Rajasthan Royals v Chennai Super Kings', NULL),
	(47, '2018-05-12 16:00:00', b'0', 0, b'0', 23, 44, 4, 8, 1, '44th match - Kings XI Punjab v Kolkata Knight Riders', NULL),
	(48, '2018-05-12 20:00:00', b'0', 0, b'0', 7, 45, 6, 15, 1, '45th match - Delhi Daredevils v Royal Challengers Bangalore', NULL),
	(49, '2018-05-13 16:00:00', b'0', 0, b'0', 18, 46, 7, 3, 1, '46th match - Chennai Super Kings v Sunrisers Hyderabad', NULL),
	(50, '2018-05-13 20:00:00', b'0', 0, b'0', 14, 47, 5, 9, 1, '47th match - Mumbai Indians v Rajasthan Royals', NULL),
	(51, '2018-05-14 20:00:00', b'0', 0, b'0', 23, 48, 4, 15, 1, '48th match - Kings XI Punjab v Royal Challengers Bangalore', NULL),
	(52, '2018-05-15 20:00:00', b'0', 0, b'0', 24, 49, 8, 9, 1, '49th match - Kolkata Knight Riders v Rajasthan Royals', NULL),
	(53, '2018-05-16 20:00:00', b'0', 0, b'0', 14, 50, 5, 4, 1, '50th match - Mumbai Indians v Kings XI Punjab', NULL),
	(54, '2018-05-17 20:00:00', b'0', 0, b'0', 11, 51, 15, 3, 1, '51st match - Royal Challengers Bangalore v Sunrisers Hyderabad', NULL),
	(55, '2018-05-18 20:00:00', b'0', 0, b'0', 7, 52, 6, 7, 1, '52nd match - Delhi Daredevils v Chennai Super Kings', NULL),
	(56, '2018-05-19 16:00:00', b'0', 0, b'0', 9, 53, 9, 15, 1, '53rd match - Rajasthan Royals v Royal Challengers Bangalore', NULL),
	(57, '2018-05-19 20:00:00', b'0', 0, b'0', 27, 54, 3, 8, 1, '54th match - Sunrisers Hyderabad v Kolkata Knight Riders', NULL),
	(58, '2018-05-20 16:00:00', b'0', 0, b'0', 7, 55, 6, 5, 1, '55th match - Delhi Daredevils v Mumbai Indians', NULL),
	(59, '2018-05-20 20:00:00', b'0', 0, b'0', 18, 56, 7, 4, 1, '56th match - Chennai Super Kings v Kings XI Punjab', NULL),
	(60, '2018-05-22 19:00:00', b'0', 0, b'0', 14, 57, 3, 7, 1, 'Qualifier 1 - Sunrisers Hyderabad v Chennai Super Kings', NULL),
	(61, '2018-05-23 19:00:00', b'0', 0, b'0', 24, 58, 8, 9, 1, 'Eliminator - Kolkata Knight Riders v Rajasthan Royals', NULL),
	(62, '2018-05-25 19:00:00', b'0', 0, b'0', 24, 59, 8, 3, 1, 'Qualifier 2 - Kolkata Knight Riders v Sunrisers Hyderabad', NULL),
	(63, '2018-05-27 19:00:00', b'0', 0, b'0', 14, 60, 7, 3, 1, 'Final - Chennai Super Kings v Sunrisers Hyderabad', NULL);
/*!40000 ALTER TABLE `match` ENABLE KEYS */;

-- Dumping structure for table fant_cric.match_perf
CREATE TABLE IF NOT EXISTS `match_perf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `match_id` int(11) NOT NULL DEFAULT '0',
  `player_id` int(11) NOT NULL DEFAULT '0',
  `team_num` int(11) NOT NULL DEFAULT '0',
  `batting_points` int(11) DEFAULT '0',
  `bowling_points` int(11) DEFAULT '0',
  `fielding_points` int(11) DEFAULT '0',
  `bonus_points` int(11) DEFAULT '0',
  `dismissal` varchar(50) NOT NULL DEFAULT '0',
  `mom` bit(1) DEFAULT b'0',
  `bat_pos` int(11) DEFAULT '0',
  `runs_scored` int(11) DEFAULT '0',
  `balls_faced` int(11) DEFAULT '0',
  `fours_hit` int(11) DEFAULT '0',
  `sixes_hit` int(11) DEFAULT '0',
  `balls_bowled` int(11) DEFAULT '0',
  `dots_bowled` int(11) DEFAULT '0',
  `maidens_bowled` int(11) DEFAULT '0',
  `runs_given` int(11) DEFAULT '0',
  `wickets_taken` int(11) DEFAULT '0',
  `fours_given` int(11) DEFAULT '0',
  `sixes_given` int(11) DEFAULT '0',
  `wides_bowled` int(11) DEFAULT '0',
  `no_balls_bowled` int(11) DEFAULT '0',
  `catches` int(11) DEFAULT '0',
  `run_outs` int(11) DEFAULT '0',
  `stumpings` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `match_id_player_id` (`match_id`,`player_id`),
  KEY `FK_match_score_player` (`player_id`),
  CONSTRAINT `FK_match_details_match` FOREIGN KEY (`match_id`) REFERENCES `match` (`id`),
  CONSTRAINT `FK_match_score_player` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table fant_cric.match_perf: ~22 rows (approximately)
/*!40000 ALTER TABLE `match_perf` DISABLE KEYS */;
REPLACE INTO `match_perf` (`id`, `match_id`, `player_id`, `team_num`, `batting_points`, `bowling_points`, `fielding_points`, `bonus_points`, `dismissal`, `mom`, `bat_pos`, `runs_scored`, `balls_faced`, `fours_hit`, `sixes_hit`, `balls_bowled`, `dots_bowled`, `maidens_bowled`, `runs_given`, `wickets_taken`, `fours_given`, `sixes_given`, `wides_bowled`, `no_balls_bowled`, `catches`, `run_outs`, `stumpings`) VALUES
	(133, 4, 361, 1, 15, 0, 10, 0, 'CAUGHT', NULL, 1, 15, 18, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL),
	(134, 4, 369, 1, -7, 0, 10, 0, 'LBW', NULL, 2, 0, 2, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL),
	(135, 4, 367, 1, 62, 0, 10, 0, 'CAUGHT', NULL, 3, 40, 29, 4, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
	(136, 4, 385, 1, 70, 0, 10, 0, 'CAUGHT', NULL, 4, 43, 29, 6, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL),
	(137, 4, 377, 1, 26, 111, 0, 0, 'NOT_OUT', NULL, 5, 22, 20, 2, 0, 24, 12, 0, 24, 3, 1, 1, 3, 0, NULL, NULL, NULL),
	(138, 4, 378, 1, 74, 0, 10, 0, 'NOT_OUT', NULL, 6, 41, 22, 5, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL),
	(139, 4, 379, 1, 0, 0, 0, 0, 'DNB', NULL, 2147483647, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(140, 4, 362, 1, 0, 29, 10, 0, 'DNB', NULL, 2147483647, NULL, NULL, NULL, NULL, 24, 10, 0, 37, 1, 0, 4, 0, 1, 1, NULL, NULL),
	(141, 4, 371, 1, 0, 17, 0, 0, 'DNB', NULL, 2147483647, NULL, NULL, NULL, NULL, 24, 5, 0, 44, 1, 4, 2, 1, 0, NULL, NULL, NULL),
	(142, 4, 375, 1, 0, 24, 10, 0, 'DNB', NULL, 2147483647, NULL, NULL, NULL, NULL, 23, 8, 0, 39, 1, 6, 1, 0, 0, 1, NULL, NULL),
	(143, 4, 372, 1, 0, 112, 0, 0, 'DNB', NULL, 2147483647, NULL, NULL, NULL, NULL, 24, 11, 0, 23, 3, 0, 2, 0, 0, NULL, NULL, NULL),
	(144, 4, 482, 2, 21, 71, 0, 5, 'CAUGHT', NULL, 1, 16, 14, 1, 1, 24, 12, 0, 29, 2, 1, 2, 1, 0, NULL, NULL, NULL),
	(145, 4, 476, 2, 29, 0, 10, 5, 'LBW', NULL, 2, 22, 19, 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL),
	(146, 4, 475, 2, 2, 0, 0, 5, 'CAUGHT', NULL, 3, 4, 6, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(147, 4, 485, 2, 31, 0, 0, 5, 'NOT_OUT', NULL, 4, 24, 22, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(148, 4, 466, 2, 5, 0, 0, 5, 'LBW', NULL, 5, 5, 5, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(149, 4, 470, 2, 12, 3, 0, 5, 'CAUGHT', NULL, 6, 12, 13, 1, 0, 6, 3, 0, 9, 0, 2, 0, 0, 0, NULL, NULL, NULL),
	(150, 4, 464, 2, 138, 31, 0, 30, 'CAUGHT', b'1', 7, 68, 30, 3, 7, 24, 9, 0, 25, 0, 3, 0, 0, 0, NULL, NULL, NULL),
	(151, 4, 465, 2, -6, 58, 0, 5, 'STUMPED', NULL, 8, 0, 1, 0, 0, 18, 12, 0, 14, 1, 2, 0, 0, 0, NULL, NULL, NULL),
	(152, 4, 468, 2, 12, 11, 10, 5, 'CAUGHT', NULL, 9, 8, 5, 1, 0, 12, 3, 0, 14, 0, 1, 0, 0, 0, 1, NULL, NULL),
	(153, 4, 484, 2, -1, -7, 10, 5, 'CAUGHT', NULL, 10, 1, 3, 0, 0, 24, 6, 0, 49, 0, 6, 2, 1, 0, 1, NULL, NULL),
	(154, 4, 469, 2, 2, 19, 0, 5, 'NOT_OUT', NULL, 11, 2, 2, 0, 0, 12, 4, 0, 23, 1, 3, 1, 0, 0, NULL, NULL, NULL);
/*!40000 ALTER TABLE `match_perf` ENABLE KEYS */;

-- Dumping structure for table fant_cric.player
CREATE TABLE IF NOT EXISTS `player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL DEFAULT '0',
  `ext_id` int(11) DEFAULT '0',
  `team_id` int(11) NOT NULL DEFAULT '0',
  `skill` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ext_id` (`ext_id`),
  KEY `FK_player_team` (`team_id`),
  CONSTRAINT `FK_player_team` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=513 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table fant_cric.player: ~198 rows (approximately)
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
REPLACE INTO `player` (`id`, `name`, `ext_id`, `team_id`, `skill`) VALUES
	(315, 'Dinesh Karthik', 30045, 8, 'KEEPER'),
	(316, 'Piyush Chawla', 32966, 8, 'ALL_ROUNDER'),
	(317, 'Tom Curran', 550235, 8, 'ALL_ROUNDER'),
	(318, 'Cameron Delport', 322810, 8, 'ALL_ROUNDER'),
	(319, 'Ishank Jaggi', 279554, 8, 'BATSMAN'),
	(320, 'Mitchell Johnson', 6033, 8, 'BOWLER'),
	(321, 'Kuldeep Yadav', 559235, 8, 'BOWLER'),
	(322, 'Chris Lynn', 326637, 8, 'BATSMAN'),
	(323, 'Sunil Narine', 230558, 8, 'BOWLER'),
	(324, 'Prasidh Krishna', 917159, 8, 'BOWLER'),
	(325, 'Nitish Rana', 604527, 8, 'BATSMAN'),
	(326, 'Andre Russell', 276298, 8, 'ALL_ROUNDER'),
	(327, 'Javon Searles', 230554, 8, 'BOWLER'),
	(328, 'Shivam Mavi', 1079848, 8, 'ALL_ROUNDER'),
	(329, 'Shubman Gill', 1070173, 8, 'BATSMAN'),
	(330, 'Rinku Singh', 723105, 8, 'BATSMAN'),
	(331, 'Robin Uthappa', 35582, 8, 'BATSMAN'),
	(332, 'Vinay Kumar', 35731, 8, 'BOWLER'),
	(333, 'Apoorv Wankhade', 505773, 8, 'BATSMAN'),
	(334, 'Kamlesh Nagarkoti', 1070188, 8, 'BOWLER'),
	(335, 'Mitchell Starc', 311592, 8, 'BOWLER'),
	(336, 'Virat Kohli', 253802, 15, 'BATSMAN'),
	(337, 'Moeen Ali', 8917, 15, 'ALL_ROUNDER'),
	(338, 'Corey Anderson', 277662, 15, 'ALL_ROUNDER'),
	(339, 'Murugan Ashwin', 528067, 15, 'BOWLER'),
	(340, 'Yuzvendra Chahal', 430246, 15, 'BOWLER'),
	(341, 'Aniket Choudhary', 527299, 15, 'BOWLER'),
	(342, 'Colin de Grandhomme', 55395, 15, 'ALL_ROUNDER'),
	(343, 'Quinton de Kock', 379143, 15, 'KEEPER'),
	(344, 'Pavan Deshpande', 422104, 15, 'ALL_ROUNDER'),
	(345, 'AB de Villiers', 44936, 15, 'KEEPER'),
	(346, 'Aniruddha Joshi', 420644, 15, 'BOWLER'),
	(347, 'Sarfaraz Khan', 642525, 15, 'BATSMAN'),
	(348, 'Kulwant Khejroliya', 1083033, 15, 'BOWLER'),
	(349, 'Brendon McCullum', 37737, 15, 'KEEPER'),
	(350, 'Mandeep Singh', 398506, 15, 'ALL_ROUNDER'),
	(351, 'Mohammed Siraj', 940973, 15, 'BOWLER'),
	(352, 'Pawan Negi', 530773, 15, 'BOWLER'),
	(353, 'Parthiv Patel', 32242, 15, 'KEEPER'),
	(354, 'Navdeep Saini', 700167, 15, 'BOWLER'),
	(355, 'Tim Southee', 232364, 15, 'BOWLER'),
	(356, 'Manan Vohra', 532856, 15, 'BATSMAN'),
	(357, 'Washington Sundar', 719715, 15, 'BATSMAN'),
	(358, 'Chris Woakes', 247235, 15, 'BOWLER'),
	(359, 'Umesh Yadav', 376116, 15, 'BOWLER'),
	(360, 'Nathan Coulter-Nile', 261354, 15, 'BOWLER'),
	(361, 'Rohit Sharma', 34102, 5, 'BATSMAN'),
	(362, 'Jasprit Bumrah', 625383, 5, 'BOWLER'),
	(363, 'Rahul Chahar', 1064812, 5, 'BOWLER'),
	(364, 'Ben Cutting', 230371, 5, 'ALL_ROUNDER'),
	(365, 'Akila Dananjaya', 574178, 5, 'ALL_ROUNDER'),
	(366, 'Jean-Paul Duminy', 44932, 5, 'ALL_ROUNDER'),
	(367, 'Ishan Kishan', 720471, 5, 'BATSMAN'),
	(368, 'Siddhesh Lad', 422342, 5, 'BATSMAN'),
	(369, 'Evin Lewis', 431901, 5, 'BATSMAN'),
	(370, 'Sharad Lumba', 726989, 5, 'BATSMAN'),
	(371, 'Mitchell McClenaghan', 319439, 5, 'BOWLER'),
	(372, 'Mayank Markande', 1081442, 5, 'BOWLER'),
	(373, 'Adam Milne', 450860, 5, 'BOWLER'),
	(374, 'Mohsin Khan', 1132005, 5, 'BOWLER'),
	(375, 'Mustafizur Rahman', 330902, 5, 'BOWLER'),
	(376, 'MD Nidheesh', 822703, 5, 'BOWLER'),
	(377, 'Hardik Pandya', 625371, 5, 'ALL_ROUNDER'),
	(378, 'Krunal Pandya', 471342, 5, 'ALL_ROUNDER'),
	(379, 'Kieron Pollard', 230559, 5, 'ALL_ROUNDER'),
	(380, 'Anukul Roy', 1079839, 5, 'ALL_ROUNDER'),
	(381, 'Pradeep Sangwan', 279545, 5, 'BOWLER'),
	(382, 'Tajinder Singh', 1079860, 5, 'ALL_ROUNDER'),
	(383, 'Aditya Tare', 333904, 5, 'KEEPER'),
	(384, 'Saurabh Tiwary', 35390, 5, 'BATSMAN'),
	(385, 'Suryakumar Yadav', 446507, 5, 'BATSMAN'),
	(386, 'Jason Behrendorff', 272477, 5, 'BOWLER'),
	(387, 'Pat Cummins', 489889, 5, 'BOWLER'),
	(388, 'Ajinkya Rahane', 277916, 9, 'BATSMAN'),
	(389, 'Ankit Sharma', 422992, 9, 'BOWLER'),
	(390, 'Anureet Singh', 376324, 9, 'BOWLER'),
	(391, 'Jofra Archer', 669855, 9, 'ALL_ROUNDER'),
	(392, 'Stuart Binny', 27223, 9, 'ALL_ROUNDER'),
	(393, 'Aryaman Birla', 1126227, 9, 'BATSMAN'),
	(394, 'Jos Buttler', 308967, 9, 'KEEPER'),
	(395, 'Prashant Chopra', 500135, 9, 'BATSMAN'),
	(396, 'Shreyas Gopal', 344580, 9, 'ALL_ROUNDER'),
	(397, 'Krishnappa Gowtham', 424377, 9, 'ALL_ROUNDER'),
	(398, 'Heinrich Klaasen', 436757, 9, 'KEEPER'),
	(399, 'Dhawal Kulkarni', 277955, 9, 'BOWLER'),
	(400, 'Ben Laughlin', 315623, 9, 'BOWLER'),
	(401, 'Mahipal Lomror', 853265, 9, 'ALL_ROUNDER'),
	(402, 'Sudhesan Midhun', 1131619, 9, 'BOWLER'),
	(403, 'Sanju Samson', 425943, 9, 'KEEPER'),
	(404, 'Jatin Saxena', 34349, 9, 'BATSMAN'),
	(405, 'D\'Arcy Short', 308798, 9, 'BATSMAN'),
	(406, 'Ish Sodhi', 559066, 9, 'BOWLER'),
	(407, 'Ben Stokes', 311158, 9, 'ALL_ROUNDER'),
	(408, 'Rahul Tripathi', 446763, 9, 'BATSMAN'),
	(409, 'Jaydev Unadkat', 390484, 9, 'BOWLER'),
	(410, 'Dushmantha Chameera', 552152, 9, 'BOWLER'),
	(411, 'Steven Smith', 267192, 9, 'BATSMAN'),
	(412, 'Zahir Khan', 712219, 9, 'BOWLER'),
	(413, 'Ravichandran Ashwin', 26421, 4, 'ALL_ROUNDER'),
	(414, 'Akshdeep Nath', 500360, 4, 'BATSMAN'),
	(415, 'Mayank Agarwal', 398438, 4, 'BATSMAN'),
	(416, 'Mayank Dagar', 942367, 4, 'BOWLER'),
	(417, 'Ben Dwarshuis', 679567, 4, 'BOWLER'),
	(418, 'Aaron Finch', 5334, 4, 'BATSMAN'),
	(419, 'Chris Gayle', 51880, 4, 'ALL_ROUNDER'),
	(420, 'Manzoor Dar', 1080203, 4, 'BATSMAN'),
	(421, 'David Miller', 321777, 4, 'BATSMAN'),
	(422, 'Mujeeb Ur Rahman', 974109, 4, 'BOWLER'),
	(423, 'Karun Nair', 398439, 4, 'BATSMAN'),
	(424, 'Axar Patel', 554691, 4, 'ALL_ROUNDER'),
	(425, 'Lokesh Rahul', 422108, 4, 'BATSMAN'),
	(426, 'Ankit Rajpoot', 591650, 4, 'BOWLER'),
	(427, 'Pardeep Sahu', 34155, 4, 'ALL_ROUNDER'),
	(428, 'Mohit Sharma', 537119, 4, 'BOWLER'),
	(429, 'Barinder Sran', 537126, 4, 'BOWLER'),
	(430, 'Marcus Stoinis', 325012, 4, 'BATSMAN'),
	(431, 'Manoj Tiwary', 35565, 4, 'BATSMAN'),
	(432, 'Andrew Tye', 459508, 4, 'BOWLER'),
	(433, 'Yuvraj Singh', 36084, 4, 'BATSMAN'),
	(434, 'Shreyas Iyer', 642519, 6, 'BATSMAN'),
	(435, 'Abhishek Sharma', 1070183, 6, 'BOWLER'),
	(436, 'Avesh Khan', 694211, 6, 'BOWLER'),
	(437, 'Trent Boult', 277912, 6, 'BOWLER'),
	(438, 'Daniel Christian', 4864, 6, 'ALL_ROUNDER'),
	(439, 'Junior Dala', 545467, 6, 'BOWLER'),
	(440, 'Gautam Gambhir', 28763, 6, 'BATSMAN'),
	(441, 'Sayan Ghosh', 736413, 6, 'BOWLER'),
	(442, 'Gurkeerat Singh Mann', 537124, 6, 'BATSMAN'),
	(443, 'Sandeep Lamichhane', 960361, 6, 'BOWLER'),
	(444, 'Manjot Kalra', 1079842, 6, 'BATSMAN'),
	(445, 'Glenn Maxwell', 325026, 6, 'ALL_ROUNDER'),
	(446, 'Amit Mishra', 31107, 6, 'BOWLER'),
	(447, 'Mohammed Shami', 481896, 6, 'BOWLER'),
	(448, 'Colin Munro', 232359, 6, 'BATSMAN'),
	(449, 'Shahbaz Nadeem', 31872, 6, 'BOWLER'),
	(450, 'Naman Ojha', 32102, 6, 'KEEPER'),
	(451, 'Rishabh Pant', 931581, 6, 'KEEPER'),
	(452, 'Harshal Patel', 390481, 6, 'BOWLER'),
	(453, 'Liam Plunkett', 19264, 6, 'BOWLER'),
	(454, 'Jason Roy', 298438, 6, 'BATSMAN'),
	(455, 'Vijay Shankar', 477021, 6, 'ALL_ROUNDER'),
	(456, 'Prithvi Shaw', 1070168, 6, 'BATSMAN'),
	(457, 'Rahul Tewatia', 423838, 6, 'BOWLER'),
	(458, 'Jayant Yadav', 447587, 6, 'BOWLER'),
	(459, 'Chris Morris', 439952, 6, 'ALL_ROUNDER'),
	(460, 'Kagiso Rabada', 550215, 6, 'BOWLER'),
	(461, 'KM Asif', 1083030, 7, 'BOWLER'),
	(462, 'Sam Billings', 297628, 7, 'KEEPER'),
	(463, 'Chaitanya Bishnoi', 628217, 7, 'ALL_ROUNDER'),
	(464, 'Dwayne Bravo', 51439, 7, 'ALL_ROUNDER'),
	(465, 'Deepak Chahar', 447261, 7, 'BOWLER'),
	(466, 'MS Dhoni', 28081, 7, 'KEEPER'),
	(467, 'Faf du Plessis', 44828, 7, 'BATSMAN'),
	(468, 'Harbhajan Singh', 29264, 7, 'BOWLER'),
	(469, 'Imran Tahir', 40618, 7, 'BOWLER'),
	(470, 'Ravindra Jadeja', 234675, 7, 'ALL_ROUNDER'),
	(471, 'Narayan Jagadeesan', 1048813, 7, 'BATSMAN'),
	(472, 'Kshitiz Sharma', 625380, 7, 'BATSMAN'),
	(473, 'Monu Kumar', 694209, 7, 'ALL_ROUNDER'),
	(474, 'Lungi Ngidi', 542023, 7, 'BOWLER'),
	(475, 'Suresh Raina', 33335, 7, 'BATSMAN'),
	(476, 'Ambati Rayudu', 33141, 7, 'BATSMAN'),
	(477, 'Kanishk Seth', 849737, 7, 'BOWLER'),
	(478, 'Karn Sharma', 30288, 7, 'BOWLER'),
	(479, 'Dhruv Shorey', 590327, 7, 'BATSMAN'),
	(480, 'Shardul Thakur', 475281, 7, 'BOWLER'),
	(481, 'Murali Vijay', 237095, 7, 'BATSMAN'),
	(482, 'Shane Watson', 8180, 7, 'ALL_ROUNDER'),
	(483, 'David Willey', 308251, 7, 'ALL_ROUNDER'),
	(484, 'Mark Wood', 351588, 7, 'BOWLER'),
	(485, 'Kedar Jadhav', 290716, 7, 'ALL_ROUNDER'),
	(486, 'Mitchell Santner', 502714, 7, 'ALL_ROUNDER'),
	(487, 'Kane Williamson', 277906, 3, 'BATSMAN'),
	(488, 'Tanmay Agarwal', 792725, 3, 'BATSMAN'),
	(489, 'Khaleel Ahmed', 942645, 3, 'BOWLER'),
	(490, 'Basil Thampi', 732291, 3, 'BOWLER'),
	(491, 'Ricky Bhui', 642531, 3, 'BATSMAN'),
	(492, 'Bipul Sharma', 35928, 3, 'ALL_ROUNDER'),
	(493, 'Carlos Brathwaite', 457249, 3, 'ALL_ROUNDER'),
	(494, 'Shikhar Dhawan', 28235, 3, 'BATSMAN'),
	(495, 'Shreevats Goswami', 302579, 3, 'KEEPER'),
	(496, 'Alex Hales', 249866, 3, 'BATSMAN'),
	(497, 'Mehdi Hasan', 477050, 3, 'BOWLER'),
	(498, 'Deepak Hooda', 497121, 3, 'ALL_ROUNDER'),
	(499, 'Chris Jordan', 288992, 3, 'BOWLER'),
	(500, 'Siddarth Kaul', 326017, 3, 'BOWLER'),
	(501, 'Bhuvneshwar Kumar', 326016, 3, 'BOWLER'),
	(502, 'Mohammad Nabi', 25913, 3, 'ALL_ROUNDER'),
	(503, 'T Natarajan', 802575, 3, 'BOWLER'),
	(504, 'Manish Pandey', 290630, 3, 'BATSMAN'),
	(505, 'Yusuf Pathan', 32498, 3, 'ALL_ROUNDER'),
	(506, 'Rashid Khan', 793463, 3, 'BOWLER'),
	(507, 'Sachin Baby', 432783, 3, 'BATSMAN'),
	(508, 'Wriddhiman Saha', 279810, 3, 'KEEPER'),
	(509, 'Sandeep Sharma', 438362, 3, 'BOWLER'),
	(510, 'Shakib Al Hasan', 56143, 3, 'ALL_ROUNDER'),
	(511, 'Billy Stanlake', 533042, 3, 'BOWLER'),
	(512, 'David Warner', 219889, 3, 'BATSMAN');
/*!40000 ALTER TABLE `player` ENABLE KEYS */;

-- Dumping structure for table fant_cric.player_conflict_res
CREATE TABLE IF NOT EXISTS `player_conflict_res` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `match_id` int(11) NOT NULL,
  `seq_num` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `match_id_seq_num` (`match_id`,`seq_num`),
  KEY `player_conflict_res_player_id_fk` (`player_id`),
  CONSTRAINT `player_conflict_res_match_id_fk` FOREIGN KEY (`match_id`) REFERENCES `match` (`id`),
  CONSTRAINT `player_conflict_res_player_id_fk` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table fant_cric.player_conflict_res: ~0 rows (approximately)
/*!40000 ALTER TABLE `player_conflict_res` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_conflict_res` ENABLE KEYS */;

-- Dumping structure for table fant_cric.squad
CREATE TABLE IF NOT EXISTS `squad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL DEFAULT '0',
  `tournament_id` int(11) NOT NULL DEFAULT '0',
  `subs_left` int(11) NOT NULL DEFAULT '0',
  `points` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_squad_user` (`user_id`),
  KEY `FK_squad_tournament` (`tournament_id`),
  CONSTRAINT `FK_squad_tournament` FOREIGN KEY (`tournament_id`) REFERENCES `tournament` (`id`),
  CONSTRAINT `FK_squad_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table fant_cric.squad: ~7 rows (approximately)
/*!40000 ALTER TABLE `squad` DISABLE KEYS */;
REPLACE INTO `squad` (`id`, `name`, `user_id`, `tournament_id`, `subs_left`, `points`) VALUES
	(17, 'low', 19, 1, 80, 829),
	(18, 'test squad', 19, 1, 80, 829),
	(19, 'test squad', 19, 1, 80, 829),
	(21, 'high', 19, 1, 80, 692),
	(22, 'test squad', 19, 1, 80, 829),
	(23, 'test squad', 19, 1, 80, 829),
	(24, 'test squad', 19, 1, 80, 829);
/*!40000 ALTER TABLE `squad` ENABLE KEYS */;

-- Dumping structure for table fant_cric.squad_player
CREATE TABLE IF NOT EXISTS `squad_player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `squad_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  `power_type` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `squad_id_player_id` (`squad_id`,`player_id`),
  KEY `FK_member_squad_player` (`player_id`),
  CONSTRAINT `squad_player_ibfk_1` FOREIGN KEY (`squad_id`) REFERENCES `squad` (`id`),
  CONSTRAINT `squad_player_ibfk_2` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table fant_cric.squad_player: ~77 rows (approximately)
/*!40000 ALTER TABLE `squad_player` DISABLE KEYS */;
REPLACE INTO `squad_player` (`id`, `squad_id`, `player_id`, `power_type`) VALUES
	(1, 17, 353, NULL),
	(2, 17, 375, NULL),
	(3, 17, 361, NULL),
	(4, 17, 377, NULL),
	(5, 17, 378, NULL),
	(6, 17, 385, NULL),
	(7, 17, 475, 'BATTING'),
	(8, 17, 465, 'BOWLING'),
	(9, 17, 464, NULL),
	(10, 17, 482, NULL),
	(11, 17, 362, NULL),
	(12, 18, 465, 'BOWLING'),
	(13, 18, 466, NULL),
	(14, 18, 375, NULL),
	(15, 18, 361, NULL),
	(16, 18, 377, NULL),
	(17, 18, 378, NULL),
	(18, 18, 385, NULL),
	(19, 18, 464, NULL),
	(20, 18, 475, 'BATTING'),
	(21, 18, 482, NULL),
	(22, 18, 362, NULL),
	(23, 19, 466, NULL),
	(24, 19, 375, NULL),
	(25, 19, 361, NULL),
	(26, 19, 377, NULL),
	(27, 19, 378, NULL),
	(28, 19, 385, NULL),
	(29, 19, 464, NULL),
	(30, 19, 482, NULL),
	(31, 19, 465, 'BOWLING'),
	(32, 19, 475, 'BATTING'),
	(33, 19, 362, NULL),
	(45, 22, 475, 'BATTING'),
	(46, 22, 466, NULL),
	(47, 22, 375, NULL),
	(48, 22, 361, NULL),
	(49, 22, 377, NULL),
	(50, 22, 378, NULL),
	(51, 22, 385, NULL),
	(52, 22, 465, 'BOWLING'),
	(53, 22, 464, NULL),
	(54, 22, 482, NULL),
	(55, 22, 362, NULL),
	(56, 23, 466, NULL),
	(57, 23, 375, NULL),
	(58, 23, 361, NULL),
	(59, 23, 377, NULL),
	(60, 23, 378, NULL),
	(61, 23, 385, NULL),
	(62, 23, 475, 'BATTING'),
	(63, 23, 464, NULL),
	(64, 23, 482, NULL),
	(65, 23, 465, 'BOWLING'),
	(66, 23, 362, NULL),
	(100, 21, 466, NULL),
	(101, 21, 375, NULL),
	(102, 21, 379, NULL),
	(103, 21, 361, NULL),
	(104, 21, 475, 'BATTING'),
	(105, 21, 465, 'BOWLING'),
	(106, 21, 378, NULL),
	(107, 21, 385, NULL),
	(108, 21, 464, NULL),
	(109, 21, 482, NULL),
	(110, 21, 362, NULL),
	(111, 24, 466, NULL),
	(112, 24, 475, 'BATTING'),
	(113, 24, 375, NULL),
	(114, 24, 361, NULL),
	(115, 24, 377, NULL),
	(116, 24, 378, NULL),
	(117, 24, 385, NULL),
	(118, 24, 464, NULL),
	(119, 24, 482, NULL),
	(120, 24, 362, NULL),
	(121, 24, 465, 'BOWLING');
/*!40000 ALTER TABLE `squad_player` ENABLE KEYS */;

-- Dumping structure for table fant_cric.team
CREATE TABLE IF NOT EXISTS `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT '0',
  `short_name` varchar(8) NOT NULL DEFAULT '0',
  `ext_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ext_id` (`ext_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table fant_cric.team: ~8 rows (approximately)
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
REPLACE INTO `team` (`id`, `name`, `short_name`, `ext_id`) VALUES
	(3, 'Sunrisers Hyderabad', 'SRH', 628333),
	(4, 'Kings XI Punjab', 'KXIP', 335973),
	(5, 'Mumbai Indians', 'MI', 335978),
	(6, 'Delhi Daredevils', 'DD', 335975),
	(7, 'Chennai Super Kings', 'CSK', 335974),
	(8, 'Kolkata Knight Riders', 'KKR', 335971),
	(9, 'Rajasthan Royals', 'RR', 335977),
	(15, 'Royal Challengers Bangalore', 'RCB', 335970);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;

-- Dumping structure for table fant_cric.tournament
CREATE TABLE IF NOT EXISTS `tournament` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT '0',
  `total_subs` smallint(6) NOT NULL DEFAULT '0',
  `free_subs` int(11) NOT NULL,
  `unlimited_subs` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table fant_cric.tournament: ~1 rows (approximately)
/*!40000 ALTER TABLE `tournament` DISABLE KEYS */;
REPLACE INTO `tournament` (`id`, `name`, `total_subs`, `free_subs`, `unlimited_subs`) VALUES
	(1, 'test_tournament', 80, 1, b'0');
/*!40000 ALTER TABLE `tournament` ENABLE KEYS */;

-- Dumping structure for table fant_cric.tournament_player
CREATE TABLE IF NOT EXISTS `tournament_player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) NOT NULL DEFAULT '0',
  `tournament_team_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `team_id_tournament_id` (`player_id`,`tournament_team_id`),
  KEY `FK_tournament_team_tournament` (`tournament_team_id`),
  CONSTRAINT `tournament_player_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `team` (`id`),
  CONSTRAINT `tournament_player_ibfk_2` FOREIGN KEY (`tournament_team_id`) REFERENCES `tournament` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table fant_cric.tournament_player: ~0 rows (approximately)
/*!40000 ALTER TABLE `tournament_player` DISABLE KEYS */;
/*!40000 ALTER TABLE `tournament_player` ENABLE KEYS */;

-- Dumping structure for table fant_cric.tournament_team
CREATE TABLE IF NOT EXISTS `tournament_team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `team_id` int(11) NOT NULL DEFAULT '0',
  `tournament_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `team_id_tournament_id` (`team_id`,`tournament_id`),
  KEY `FK_tournament_team_tournament` (`tournament_id`),
  CONSTRAINT `FK_tournament_team_team` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FK_tournament_team_tournament` FOREIGN KEY (`tournament_id`) REFERENCES `tournament` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table fant_cric.tournament_team: ~8 rows (approximately)
/*!40000 ALTER TABLE `tournament_team` DISABLE KEYS */;
REPLACE INTO `tournament_team` (`id`, `team_id`, `tournament_id`) VALUES
	(10, 3, 1),
	(3, 4, 1),
	(5, 5, 1),
	(2, 6, 1),
	(1, 7, 1),
	(4, 8, 1),
	(6, 9, 1),
	(8, 15, 1);
/*!40000 ALTER TABLE `tournament_team` ENABLE KEYS */;

-- Dumping structure for table fant_cric.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '0',
  `password` varchar(128) NOT NULL DEFAULT '0',
  `role` varchar(64) NOT NULL DEFAULT '0',
  `email` varchar(64) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table fant_cric.user: ~9 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`id`, `name`, `password`, `role`, `email`) VALUES
	(19, 'User', '$2a$10$6vV3HaGD3.A5I7Wocc1tYOvNdbOHugXOREmsW51moduVUgXpAidW2', 'ROLE_USER', 'user@user.com'),
	(20, 'Admin', '$2a$10$iDlCY51LHChMuI3j/4Ph.uzWp9kS00LnvwwFRwAOMR52UDLUnJnpe', 'ROLE_ADMIN', 'admin@admin.com'),
	(21, 'test', '$2a$10$SZBYgmG7LsaStvJ.gtbO2OHoCcOAblegNGm6oRnIAYOmIeOt0Ocke', 'ROLE_USER', 'test@test.com'),
	(22, 'test', '$2a$10$mVUhotuW/BLmbuZmefhr5u70hi8QtxCif9cVMdkkkWxIlllleErsm', 'ROLE_USER', 'test1@test.com'),
	(23, 'test', '$2a$10$VtnXLdn9G0JZV0nol5ankucFp7eRONSqzBlLIpU7G9oIAWBfC/Vu6', 'ROLE_USER', 'test2@test.com'),
	(30, 'VIVEK VIJAYAKUMAR', '$2a$10$nYbCud4ojq6qGs.3m0LKzuC5651U.Y8gB9hUj25Y.NKCJlj4In4a.', 'ROLE_USER', 'vivek.1612@gmail.com'),
	(31, 'VIVEK VIJAYAKUMAR', '$2a$10$MURYEAWteri8RyS7xsQ9ZepVxFU6CWLirxnryLkIfOYut0WopKMZq', 'ROLE_USER', 'vivek.16123@gmail.com'),
	(32, 'VIVEK VIJAYAKUMAR', '$2a$10$xdXcLwWHnSGHV6Yg7O3KgeDU5YRnWxG.68WW1HcuJAxvMru1bRBrG', 'ROLE_USER', 'vivek.16121@gmail.com'),
	(33, 'VIVEK VIJAYAKUMAR', '$2a$10$5yvfSUzil5dXr4n979fkQ.EZk6x/yTxg3F5/UKd2AbGGImQMXdrgi', 'ROLE_USER', 'vivek.1612sd@gmail.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table fant_cric.venue
CREATE TABLE IF NOT EXISTS `venue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL DEFAULT '0',
  `city_id` int(11) DEFAULT '0',
  `ext_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ext_id` (`ext_id`),
  KEY `FK_venue_city` (`city_id`),
  CONSTRAINT `FK_venue_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table fant_cric.venue: ~33 rows (approximately)
/*!40000 ALTER TABLE `venue` DISABLE KEYS */;
REPLACE INTO `venue` (`id`, `name`, `city_id`, `ext_id`) VALUES
	(2, 'Kingsmead', 18, 59089),
	(3, 'Brabourne Stadium', 10, 58317),
	(4, 'Vidarbha Cricket Association Stadium', 30, 375326),
	(5, 'Green Park', 13, 58204),
	(6, 'Dubai International Cricket Stadium', 11, 392627),
	(7, 'Feroz Shah Kotla', 3, 58040),
	(8, 'SuperSport Park', 7, 59079),
	(9, 'Sawai Mansingh Stadium', 16, 58162),
	(10, 'St George\'s Park', 32, 59159),
	(11, 'M.Chinnaswamy Stadium', 19, 57897),
	(12, 'Shaheed Veer Narayan Singh International Stadium', 21, 601879),
	(13, 'Sharjah Cricket Stadium', 4, 59392),
	(14, 'Wankhede Stadium', 10, 58324),
	(15, 'JSCA International Stadium Complex', 27, 485865),
	(16, 'Diamond Oval', 12, 59135),
	(17, 'Saurashtra Cricket Association Stadium', 5, 377285),
	(18, 'Maharashtra Cricket Association Stadium', 31, 545380),
	(19, 'Dr. Y.S. Rajasekhara Reddy ACA-VDCA Cricket Stadium', 15, 58547),
	(20, 'Punjab Cricket Association IS Bindra Stadium', 20, 57991),
	(21, 'MA Chidambaram Stadium', 14, 58008),
	(22, 'Sheikh Zayed Stadium', 26, 59396),
	(23, 'Holkar Cricket Stadium', 9, 58150),
	(24, 'Eden Gardens', 24, 57980),
	(25, 'Newlands', 6, 59068),
	(26, 'Buffalo Park', 17, 59098),
	(27, 'Rajiv Gandhi International Stadium', 25, 58142),
	(28, 'Nehru Stadium', 28, 58230),
	(29, 'The Wanderers Stadium', 8, 59120),
	(30, 'Sardar Patel (Gujarat) Stadium', 2, 57851),
	(31, 'Himachal Pradesh Cricket Association Stadium', 23, 58056),
	(32, 'Mangaung Oval', 22, 59042),
	(33, 'Dr DY Patil Sports Academy', 10, 343050),
	(34, 'Barabati Stadium', 29, 58027);
/*!40000 ALTER TABLE `venue` ENABLE KEYS */;

-- Dumping structure for trigger fant_cric.match_after_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `match_after_update` AFTER UPDATE ON `match` FOR EACH ROW BEGIN
	if (NEW.points_updated <> OLD.points_updated && NEW.points_updated=1) then
		update league l1, 
		(
			select l.id as lid, sum(s.points) as totalPoints from league l 
			join league_squad ls on ls.league_id = l.id
			join squad s on ls.squad_id = s.id
			group by l.id
		) as p
		set l1.points = p.totalPoints where l1.id=p.lid;		
	end if;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
