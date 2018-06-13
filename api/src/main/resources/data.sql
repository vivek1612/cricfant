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


-- Dumping database structure for cricfant
CREATE DATABASE IF NOT EXISTS `cricfant` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `cricfant`;

-- Dumping structure for table cricfant.city
CREATE TABLE IF NOT EXISTS `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `country_id` int(11) NOT NULL DEFAULT '2',
  PRIMARY KEY (`id`),
  KEY `FK_city_country` (`country_id`),
  CONSTRAINT `FK_city_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table cricfant.city: ~31 rows (approximately)
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

-- Dumping structure for table cricfant.country
CREATE TABLE IF NOT EXISTS `country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table cricfant.country: ~3 rows (approximately)
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
REPLACE INTO `country` (`id`, `name`) VALUES
	(2, 'India'),
	(3, 'South Africa'),
	(4, 'United Arab Emirates');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;

-- Dumping structure for table cricfant.league
CREATE TABLE IF NOT EXISTS `league` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '0',
  `tournament_id` int(11) NOT NULL,
  `admin_id` int(11) DEFAULT NULL,
  `type` varchar(50) NOT NULL DEFAULT 'PUBLIC',
  `points` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_league_tournament` (`tournament_id`),
  KEY `FK_league_user` (`admin_id`),
  CONSTRAINT `FK_league_tournament` FOREIGN KEY (`tournament_id`) REFERENCES `tournament` (`id`),
  CONSTRAINT `FK_league_user` FOREIGN KEY (`admin_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table cricfant.league: ~32 rows (approximately)
/*!40000 ALTER TABLE `league` DISABLE KEYS */;
REPLACE INTO `league` (`id`, `name`, `tournament_id`, `admin_id`, `type`, `points`) VALUES
	(1, 'te_league', 1, NULL, 'PUBLIC', 514),
	(2, 'test_another', 1, NULL, 'PUBLIC', 0),
	(3, 'test_league', 1, NULL, 'PUBLIC', 0),
	(4, 'vcnxnb', 1, NULL, 'PUBLIC', 0),
	(5, 'new league', 1, NULL, 'PUBLIC', 0),
	(6, 'new league', 1, NULL, 'PUBLIC', 0),
	(8, 'final league', 1, NULL, 'PUBLIC', 0),
	(9, 'final league', 1, NULL, 'PUBLIC', 0),
	(10, 'final league', 1, NULL, 'PUBLIC', 0),
	(11, 't_league', 1, NULL, 'PUBLIC', 0),
	(12, 'te_league', 1, NULL, 'PUBLIC', 0),
	(13, 'te_league', 1, NULL, 'PUBLIC', 0),
	(14, 'te_league', 1, NULL, 'PUBLIC', 0),
	(15, 'te_league', 1, NULL, 'PUBLIC', 0),
	(16, 'te_league', 1, NULL, 'PUBLIC', 0),
	(17, 'te_leagueig', 1, NULL, 'PUBLIC', 0),
	(18, 'te_leagueig', 1, NULL, 'PUBLIC', 0),
	(19, 'tsdfdge_leagueig', 1, NULL, 'PUBLIC', 0),
	(20, 'My League', 1, NULL, 'PUBLIC', 0),
	(21, 'My League', 1, NULL, 'PUBLIC', 0),
	(22, 'my test league', 1, NULL, 'PUBLIC', 0),
	(23, 'my test league', 1, NULL, 'PUBLIC', 0),
	(24, 'my test league', 1, NULL, 'PUBLIC', 0),
	(26, 'my test league', 1, NULL, 'PUBLIC', 0),
	(27, 'my test league', 1, NULL, 'PUBLIC', 0),
	(28, 'my test league', 1, NULL, 'PUBLIC', 0),
	(29, 'my test league', 1, NULL, 'PUBLIC', 0),
	(31, 'test_league', 1, NULL, 'PUBLIC', 0),
	(32, 'test_league', 1, NULL, 'PUBLIC', 0),
	(33, 'test_league', 1, 19, 'PRIVATE', 0),
	(34, 'test_league', 1, 19, 'PRIVATE', 0),
	(35, 'test_league', 1, 19, 'PRIVATE', 0);
/*!40000 ALTER TABLE `league` ENABLE KEYS */;

-- Dumping structure for table cricfant.league_squad
CREATE TABLE IF NOT EXISTS `league_squad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `league_id` int(11) NOT NULL DEFAULT '0',
  `squad_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `league_id_squad_id` (`league_id`,`squad_id`),
  KEY `FK_league_squad_squad` (`squad_id`),
  CONSTRAINT `FK_league_squad_league` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`),
  CONSTRAINT `FK_league_squad_squad` FOREIGN KEY (`squad_id`) REFERENCES `squad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table cricfant.league_squad: ~1 rows (approximately)
/*!40000 ALTER TABLE `league_squad` DISABLE KEYS */;
REPLACE INTO `league_squad` (`id`, `league_id`, `squad_id`) VALUES
	(1, 1, 37);
/*!40000 ALTER TABLE `league_squad` ENABLE KEYS */;

-- Dumping structure for table cricfant.lockin
CREATE TABLE IF NOT EXISTS `lockin` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `match_id` int(11) NOT NULL DEFAULT '0',
  `squad_id` int(11) NOT NULL,
  `tournament_team_player_id` int(11) NOT NULL,
  `power_type` varchar(32) DEFAULT NULL,
  `batting_points` int(11) DEFAULT '0',
  `bowling_points` int(11) DEFAULT '0',
  `fielding_points` int(11) DEFAULT '0',
  `bonus_points` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `league_member_id_player_id_match_id` (`squad_id`,`tournament_team_player_id`,`match_id`),
  KEY `FK_member_squad_player` (`tournament_team_player_id`),
  KEY `FK_lockin_match` (`match_id`),
  CONSTRAINT `FK_lockin_match` FOREIGN KEY (`match_id`) REFERENCES `match` (`id`),
  CONSTRAINT `FK_lockin_tournament_team_player` FOREIGN KEY (`tournament_team_player_id`) REFERENCES `tournament_team_player` (`id`),
  CONSTRAINT `lockin_ibfk_2` FOREIGN KEY (`squad_id`) REFERENCES `squad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table cricfant.lockin: ~33 rows (approximately)
/*!40000 ALTER TABLE `lockin` DISABLE KEYS */;
REPLACE INTO `lockin` (`id`, `match_id`, `squad_id`, `tournament_team_player_id`, `power_type`, `batting_points`, `bowling_points`, `fielding_points`, `bonus_points`) VALUES
	(1, 4, 36, 7, NULL, -6, 58, 0, 5),
	(2, 4, 36, 112, 'BOWLING', 0, 48, 10, 0),
	(3, 4, 36, 114, NULL, 26, 111, 0, 0),
	(4, 4, 36, 10, NULL, 12, 11, 10, 5),
	(5, 4, 36, 17, NULL, 2, 0, 0, 5),
	(6, 4, 36, 20, NULL, NULL, NULL, NULL, NULL),
	(7, 4, 36, 115, NULL, 74, 0, 10, 0),
	(8, 4, 36, 98, 'BATTING', 30, 0, 10, 0),
	(9, 4, 36, 8, NULL, 5, 0, 0, 5),
	(10, 4, 36, 18, NULL, 29, 0, 10, 5),
	(11, 4, 36, 99, NULL, 0, 29, 10, 0),
	(12, 4, 38, 17, NULL, 2, 0, 0, 5),
	(13, 4, 38, 98, 'BATTING', 30, 0, 10, 0),
	(14, 4, 38, 10, NULL, 12, 11, 10, 5),
	(15, 4, 38, 8, NULL, 5, 0, 0, 5),
	(16, 4, 38, 99, NULL, 0, 29, 10, 0),
	(17, 4, 38, 114, NULL, 26, 111, 0, 0),
	(18, 4, 38, 7, NULL, -6, 58, 0, 5),
	(19, 4, 38, 20, NULL, NULL, NULL, NULL, NULL),
	(20, 4, 38, 112, 'BOWLING', 0, 48, 10, 0),
	(21, 4, 38, 115, NULL, 74, 0, 10, 0),
	(22, 4, 38, 18, NULL, 29, 0, 10, 5),
	(23, 4, 37, 8, NULL, 5, 0, 0, 5),
	(24, 4, 37, 99, NULL, 0, 29, 10, 0),
	(25, 4, 37, 18, NULL, 29, 0, 10, 5),
	(26, 4, 37, 20, NULL, NULL, NULL, NULL, NULL),
	(27, 4, 37, 112, 'BOWLING', 0, 48, 10, 0),
	(28, 4, 37, 114, NULL, 26, 111, 0, 0),
	(29, 4, 37, 98, 'BATTING', 30, 0, 10, 0),
	(30, 4, 37, 7, NULL, -6, 58, 0, 5),
	(31, 4, 37, 115, NULL, 74, 0, 10, 0),
	(32, 4, 37, 17, NULL, 2, 0, 0, 5),
	(33, 4, 37, 10, NULL, 12, 11, 10, 5);
/*!40000 ALTER TABLE `lockin` ENABLE KEYS */;

-- Dumping structure for table cricfant.match
CREATE TABLE IF NOT EXISTS `match` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scheduled_start` datetime NOT NULL,
  `locked_in` bit(1) NOT NULL DEFAULT b'0',
  `points_updated` bit(1) NOT NULL DEFAULT b'0',
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

-- Dumping data for table cricfant.match: ~60 rows (approximately)
/*!40000 ALTER TABLE `match` DISABLE KEYS */;
REPLACE INTO `match` (`id`, `scheduled_start`, `locked_in`, `points_updated`, `score_updated`, `venue_id`, `seq_num`, `team1_id`, `team2_id`, `tournament_id`, `description`, `result`) VALUES
	(4, '2018-04-07 20:00:00', b'1', b'1', b'1', 14, 1, 5, 7, 1, '1st match - Mumbai Indians v Chennai Super Kings', 'TEAM2_WIN'),
	(5, '2018-04-08 16:00:00', b'0', b'0', b'0', 20, 2, 4, 6, 1, '2nd match - Kings XI Punjab v Delhi Daredevils', NULL),
	(6, '2018-04-08 20:00:00', b'0', b'0', b'0', 24, 3, 8, 15, 1, '3rd match - Kolkata Knight Riders v Royal Challengers Bangalore', NULL),
	(7, '2018-04-09 20:00:00', b'0', b'0', b'0', 27, 4, 3, 9, 1, '4th match - Sunrisers Hyderabad v Rajasthan Royals', NULL),
	(8, '2018-04-10 20:00:00', b'0', b'0', b'0', 21, 5, 7, 8, 1, '5th match - Chennai Super Kings v Kolkata Knight Riders', NULL),
	(9, '2018-04-11 20:00:00', b'0', b'0', b'0', 9, 6, 9, 6, 1, '6th match - Rajasthan Royals v Delhi Daredevils', NULL),
	(10, '2018-04-12 20:00:00', b'0', b'0', b'0', 27, 7, 3, 5, 1, '7th match - Sunrisers Hyderabad v Mumbai Indians', NULL),
	(11, '2018-04-13 20:00:00', b'0', b'0', b'0', 11, 8, 15, 4, 1, '8th match - Royal Challengers Bangalore v Kings XI Punjab', NULL),
	(12, '2018-04-14 16:00:00', b'0', b'0', b'0', 14, 9, 5, 6, 1, '9th match - Mumbai Indians v Delhi Daredevils', NULL),
	(13, '2018-04-14 20:00:00', b'0', b'0', b'0', 24, 10, 8, 3, 1, '10th match - Kolkata Knight Riders v Sunrisers Hyderabad', NULL),
	(14, '2018-04-15 16:00:00', b'0', b'0', b'0', 11, 11, 15, 9, 1, '11th match - Royal Challengers Bangalore v Rajasthan Royals', NULL),
	(15, '2018-04-15 20:00:00', b'0', b'0', b'0', 20, 12, 4, 7, 1, '12th match - Kings XI Punjab v Chennai Super Kings', NULL),
	(16, '2018-04-16 20:00:00', b'0', b'0', b'0', 24, 13, 8, 6, 1, '13th match - Kolkata Knight Riders v Delhi Daredevils', NULL),
	(17, '2018-04-17 20:00:00', b'0', b'0', b'0', 14, 14, 5, 15, 1, '14th match - Mumbai Indians v Royal Challengers Bangalore', NULL),
	(18, '2018-04-18 20:00:00', b'0', b'0', b'0', 9, 15, 9, 8, 1, '15th match - Rajasthan Royals v Kolkata Knight Riders', NULL),
	(19, '2018-04-19 20:00:00', b'0', b'0', b'0', 20, 16, 4, 3, 1, '16th match - Kings XI Punjab v Sunrisers Hyderabad', NULL),
	(20, '2018-04-20 20:00:00', b'0', b'0', b'0', 18, 17, 7, 9, 1, '17th match - Chennai Super Kings v Rajasthan Royals', NULL),
	(21, '2018-04-21 16:00:00', b'0', b'0', b'0', 24, 18, 8, 4, 1, '18th match - Kolkata Knight Riders v Kings XI Punjab', NULL),
	(22, '2018-04-21 20:00:00', b'0', b'0', b'0', 11, 19, 15, 6, 1, '19th match - Royal Challengers Bangalore v Delhi Daredevils', NULL),
	(23, '2018-04-22 16:00:00', b'0', b'0', b'0', 27, 20, 3, 7, 1, '20th match - Sunrisers Hyderabad v Chennai Super Kings', NULL),
	(24, '2018-04-22 20:00:00', b'0', b'0', b'0', 9, 21, 9, 5, 1, '21st match - Rajasthan Royals v Mumbai Indians', NULL),
	(25, '2018-04-23 20:00:00', b'0', b'0', b'0', 7, 22, 6, 4, 1, '22nd match - Delhi Daredevils v Kings XI Punjab', NULL),
	(26, '2018-04-24 20:00:00', b'0', b'0', b'0', 14, 23, 5, 3, 1, '23rd match - Mumbai Indians v Sunrisers Hyderabad', NULL),
	(27, '2018-04-25 20:00:00', b'0', b'0', b'0', 11, 24, 15, 7, 1, '24th match - Royal Challengers Bangalore v Chennai Super Kings', NULL),
	(28, '2018-04-26 20:00:00', b'0', b'0', b'0', 27, 25, 3, 4, 1, '25th match - Sunrisers Hyderabad v Kings XI Punjab', NULL),
	(29, '2018-04-27 20:00:00', b'0', b'0', b'0', 7, 26, 6, 8, 1, '26th match - Delhi Daredevils v Kolkata Knight Riders', NULL),
	(30, '2018-04-28 20:00:00', b'0', b'0', b'0', 18, 27, 7, 5, 1, '27th match - Chennai Super Kings v Mumbai Indians', NULL),
	(31, '2018-04-29 16:00:00', b'0', b'0', b'0', 9, 28, 9, 3, 1, '28th match - Rajasthan Royals v Sunrisers Hyderabad', NULL),
	(32, '2018-04-29 20:00:00', b'0', b'0', b'0', 11, 29, 15, 8, 1, '29th match - Royal Challengers Bangalore v Kolkata Knight Riders', NULL),
	(33, '2018-04-30 20:00:00', b'0', b'0', b'0', 18, 30, 7, 6, 1, '30th match - Chennai Super Kings v Delhi Daredevils', NULL),
	(34, '2018-05-01 20:00:00', b'0', b'0', b'0', 11, 31, 15, 5, 1, '31st match - Royal Challengers Bangalore v Mumbai Indians', NULL),
	(35, '2018-05-02 20:00:00', b'0', b'0', b'0', 7, 32, 6, 9, 1, '32nd match - Delhi Daredevils v Rajasthan Royals', NULL),
	(36, '2018-05-03 20:00:00', b'0', b'0', b'0', 24, 33, 8, 7, 1, '33rd match - Kolkata Knight Riders v Chennai Super Kings', NULL),
	(37, '2018-05-04 20:00:00', b'0', b'0', b'0', 23, 34, 4, 5, 1, '34th match - Kings XI Punjab v Mumbai Indians', NULL),
	(38, '2018-05-05 16:00:00', b'0', b'0', b'0', 18, 35, 7, 15, 1, '35th match - Chennai Super Kings v Royal Challengers Bangalore', NULL),
	(39, '2018-05-05 20:00:00', b'0', b'0', b'0', 27, 36, 3, 6, 1, '36th match - Sunrisers Hyderabad v Delhi Daredevils', NULL),
	(40, '2018-05-06 16:00:00', b'0', b'0', b'0', 14, 37, 5, 8, 1, '37th match - Mumbai Indians v Kolkata Knight Riders', NULL),
	(41, '2018-05-06 20:00:00', b'0', b'0', b'0', 23, 38, 4, 9, 1, '38th match - Kings XI Punjab v Rajasthan Royals', NULL),
	(42, '2018-05-07 20:00:00', b'0', b'0', b'0', 27, 39, 3, 15, 1, '39th match - Sunrisers Hyderabad v Royal Challengers Bangalore', NULL),
	(43, '2018-05-08 20:00:00', b'0', b'0', b'0', 9, 40, 9, 4, 1, '40th match - Rajasthan Royals v Kings XI Punjab', NULL),
	(44, '2018-05-09 20:00:00', b'0', b'0', b'0', 24, 41, 8, 5, 1, '41st match - Kolkata Knight Riders v Mumbai Indians', NULL),
	(45, '2018-05-10 20:00:00', b'0', b'0', b'0', 7, 42, 6, 3, 1, '42nd match - Delhi Daredevils v Sunrisers Hyderabad', NULL),
	(46, '2018-05-11 20:00:00', b'0', b'0', b'0', 9, 43, 9, 7, 1, '43rd match - Rajasthan Royals v Chennai Super Kings', NULL),
	(47, '2018-05-12 16:00:00', b'0', b'0', b'0', 23, 44, 4, 8, 1, '44th match - Kings XI Punjab v Kolkata Knight Riders', NULL),
	(48, '2018-05-12 20:00:00', b'0', b'0', b'0', 7, 45, 6, 15, 1, '45th match - Delhi Daredevils v Royal Challengers Bangalore', NULL),
	(49, '2018-05-13 16:00:00', b'0', b'0', b'0', 18, 46, 7, 3, 1, '46th match - Chennai Super Kings v Sunrisers Hyderabad', NULL),
	(50, '2018-05-13 20:00:00', b'0', b'0', b'0', 14, 47, 5, 9, 1, '47th match - Mumbai Indians v Rajasthan Royals', NULL),
	(51, '2018-05-14 20:00:00', b'0', b'0', b'0', 23, 48, 4, 15, 1, '48th match - Kings XI Punjab v Royal Challengers Bangalore', NULL),
	(52, '2018-05-15 20:00:00', b'0', b'0', b'0', 24, 49, 8, 9, 1, '49th match - Kolkata Knight Riders v Rajasthan Royals', NULL),
	(53, '2018-05-16 20:00:00', b'0', b'0', b'0', 14, 50, 5, 4, 1, '50th match - Mumbai Indians v Kings XI Punjab', NULL),
	(54, '2018-05-17 20:00:00', b'0', b'0', b'0', 11, 51, 15, 3, 1, '51st match - Royal Challengers Bangalore v Sunrisers Hyderabad', NULL),
	(55, '2018-05-18 20:00:00', b'0', b'0', b'0', 7, 52, 6, 7, 1, '52nd match - Delhi Daredevils v Chennai Super Kings', NULL),
	(56, '2018-05-19 16:00:00', b'0', b'0', b'0', 9, 53, 9, 15, 1, '53rd match - Rajasthan Royals v Royal Challengers Bangalore', NULL),
	(57, '2018-05-19 20:00:00', b'0', b'0', b'0', 27, 54, 3, 8, 1, '54th match - Sunrisers Hyderabad v Kolkata Knight Riders', NULL),
	(58, '2018-05-20 16:00:00', b'0', b'0', b'0', 7, 55, 6, 5, 1, '55th match - Delhi Daredevils v Mumbai Indians', NULL),
	(59, '2018-05-20 20:00:00', b'0', b'0', b'0', 18, 56, 7, 4, 1, '56th match - Chennai Super Kings v Kings XI Punjab', NULL),
	(60, '2018-05-22 19:00:00', b'0', b'0', b'0', 14, 57, 3, 7, 1, 'Qualifier 1 - Sunrisers Hyderabad v Chennai Super Kings', NULL),
	(61, '2018-05-23 19:00:00', b'0', b'0', b'0', 24, 58, 8, 9, 1, 'Eliminator - Kolkata Knight Riders v Rajasthan Royals', NULL),
	(62, '2018-05-25 19:00:00', b'0', b'0', b'0', 24, 59, 8, 3, 1, 'Qualifier 2 - Kolkata Knight Riders v Sunrisers Hyderabad', NULL),
	(63, '2018-05-27 19:00:00', b'0', b'0', b'0', 14, 60, 7, 3, 1, 'Final - Chennai Super Kings v Sunrisers Hyderabad', NULL);
/*!40000 ALTER TABLE `match` ENABLE KEYS */;

-- Dumping structure for table cricfant.match_performance
CREATE TABLE IF NOT EXISTS `match_performance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `match_id` int(11) NOT NULL DEFAULT '0',
  `tournament_team_player_id` int(11) NOT NULL DEFAULT '0',
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
  UNIQUE KEY `match_id_player_id` (`match_id`,`tournament_team_player_id`),
  KEY `FK_match_score_player` (`tournament_team_player_id`),
  CONSTRAINT `FK_match_details_match` FOREIGN KEY (`match_id`) REFERENCES `match` (`id`),
  CONSTRAINT `FK_match_score_player` FOREIGN KEY (`tournament_team_player_id`) REFERENCES `tournament_team_player` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table cricfant.match_performance: ~0 rows (approximately)
/*!40000 ALTER TABLE `match_performance` DISABLE KEYS */;
REPLACE INTO `match_performance` (`id`, `match_id`, `tournament_team_player_id`, `team_num`, `batting_points`, `bowling_points`, `fielding_points`, `bonus_points`, `dismissal`, `mom`, `bat_pos`, `runs_scored`, `balls_faced`, `fours_hit`, `sixes_hit`, `balls_bowled`, `dots_bowled`, `maidens_bowled`, `runs_given`, `wickets_taken`, `fours_given`, `sixes_given`, `wides_bowled`, `no_balls_bowled`, `catches`, `run_outs`, `stumpings`) VALUES
	(23, 4, 98, 1, 15, 0, 10, 0, '2', NULL, 1, 15, 18, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL),
	(24, 4, 106, 1, -7, 0, 10, 0, '3', NULL, 2, 0, 2, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL),
	(25, 4, 104, 1, 62, 0, 10, 0, '2', NULL, 3, 40, 29, 4, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
	(26, 4, 122, 1, 70, 0, 10, 0, '2', NULL, 4, 43, 29, 6, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL),
	(27, 4, 114, 1, 26, 111, 0, 0, '0', NULL, 5, 22, 20, 2, 0, 24, 12, 0, 24, 3, 1, 1, 3, 0, NULL, NULL, NULL),
	(28, 4, 115, 1, 74, 0, 10, 0, '0', NULL, 6, 41, 22, 5, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL),
	(29, 4, 116, 1, 0, 0, 0, 0, '12', NULL, 2147483647, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(30, 4, 99, 1, 0, 29, 10, 0, '12', NULL, 2147483647, NULL, NULL, NULL, NULL, 24, 10, 0, 37, 1, 0, 4, 0, 1, 1, NULL, NULL),
	(31, 4, 108, 1, 0, 17, 0, 0, '12', NULL, 2147483647, NULL, NULL, NULL, NULL, 24, 5, 0, 44, 1, 4, 2, 1, 0, NULL, NULL, NULL),
	(32, 4, 112, 1, 0, 24, 10, 0, '12', NULL, 2147483647, NULL, NULL, NULL, NULL, 23, 8, 0, 39, 1, 6, 1, 0, 0, 1, NULL, NULL),
	(33, 4, 109, 1, 0, 112, 0, 0, '12', NULL, 2147483647, NULL, NULL, NULL, NULL, 24, 11, 0, 23, 3, 0, 2, 0, 0, NULL, NULL, NULL),
	(34, 4, 24, 2, 21, 71, 0, 5, '2', NULL, 1, 16, 14, 1, 1, 24, 12, 0, 29, 2, 1, 2, 1, 0, NULL, NULL, NULL),
	(35, 4, 18, 2, 29, 0, 10, 5, '3', NULL, 2, 22, 19, 4, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL),
	(36, 4, 17, 2, 2, 0, 0, 5, '2', NULL, 3, 4, 6, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(37, 4, 27, 2, 31, 0, 0, 5, '0', NULL, 4, 24, 22, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(38, 4, 8, 2, 5, 0, 0, 5, '3', NULL, 5, 5, 5, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(39, 4, 12, 2, 12, 3, 0, 5, '2', NULL, 6, 12, 13, 1, 0, 6, 3, 0, 9, 0, 2, 0, 0, 0, NULL, NULL, NULL),
	(40, 4, 6, 2, 138, 31, 0, 30, '2', b'1', 7, 68, 30, 3, 7, 24, 9, 0, 25, 0, 3, 0, 0, 0, NULL, NULL, NULL),
	(41, 4, 7, 2, -6, 58, 0, 5, '5', NULL, 8, 0, 1, 0, 0, 18, 12, 0, 14, 1, 2, 0, 0, 0, NULL, NULL, NULL),
	(42, 4, 10, 2, 12, 11, 10, 5, '2', NULL, 9, 8, 5, 1, 0, 12, 3, 0, 14, 0, 1, 0, 0, 0, 1, NULL, NULL),
	(43, 4, 26, 2, -1, -7, 10, 5, '2', NULL, 10, 1, 3, 0, 0, 24, 6, 0, 49, 0, 6, 2, 1, 0, 1, NULL, NULL),
	(44, 4, 11, 2, 2, 19, 0, 5, '0', NULL, 11, 2, 2, 0, 0, 12, 4, 0, 23, 1, 3, 1, 0, 0, NULL, NULL, NULL);
/*!40000 ALTER TABLE `match_performance` ENABLE KEYS */;

-- Dumping structure for table cricfant.player
CREATE TABLE IF NOT EXISTS `player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL DEFAULT '0',
  `ext_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ext_id` (`ext_id`)
) ENGINE=InnoDB AUTO_INCREMENT=513 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table cricfant.player: ~198 rows (approximately)
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
REPLACE INTO `player` (`id`, `name`, `ext_id`) VALUES
	(315, 'Dinesh Karthik', 30045),
	(316, 'Piyush Chawla', 32966),
	(317, 'Tom Curran', 550235),
	(318, 'Cameron Delport', 322810),
	(319, 'Ishank Jaggi', 279554),
	(320, 'Mitchell Johnson', 6033),
	(321, 'Kuldeep Yadav', 559235),
	(322, 'Chris Lynn', 326637),
	(323, 'Sunil Narine', 230558),
	(324, 'Prasidh Krishna', 917159),
	(325, 'Nitish Rana', 604527),
	(326, 'Andre Russell', 276298),
	(327, 'Javon Searles', 230554),
	(328, 'Shivam Mavi', 1079848),
	(329, 'Shubman Gill', 1070173),
	(330, 'Rinku Singh', 723105),
	(331, 'Robin Uthappa', 35582),
	(332, 'Vinay Kumar', 35731),
	(333, 'Apoorv Wankhade', 505773),
	(334, 'Kamlesh Nagarkoti', 1070188),
	(335, 'Mitchell Starc', 311592),
	(336, 'Virat Kohli', 253802),
	(337, 'Moeen Ali', 8917),
	(338, 'Corey Anderson', 277662),
	(339, 'Murugan Ashwin', 528067),
	(340, 'Yuzvendra Chahal', 430246),
	(341, 'Aniket Choudhary', 527299),
	(342, 'Colin de Grandhomme', 55395),
	(343, 'Quinton de Kock', 379143),
	(344, 'Pavan Deshpande', 422104),
	(345, 'AB de Villiers', 44936),
	(346, 'Aniruddha Joshi', 420644),
	(347, 'Sarfaraz Khan', 642525),
	(348, 'Kulwant Khejroliya', 1083033),
	(349, 'Brendon McCullum', 37737),
	(350, 'Mandeep Singh', 398506),
	(351, 'Mohammed Siraj', 940973),
	(352, 'Pawan Negi', 530773),
	(353, 'Parthiv Patel', 32242),
	(354, 'Navdeep Saini', 700167),
	(355, 'Tim Southee', 232364),
	(356, 'Manan Vohra', 532856),
	(357, 'Washington Sundar', 719715),
	(358, 'Chris Woakes', 247235),
	(359, 'Umesh Yadav', 376116),
	(360, 'Nathan Coulter-Nile', 261354),
	(361, 'Rohit Sharma', 34102),
	(362, 'Jasprit Bumrah', 625383),
	(363, 'Rahul Chahar', 1064812),
	(364, 'Ben Cutting', 230371),
	(365, 'Akila Dananjaya', 574178),
	(366, 'Jean-Paul Duminy', 44932),
	(367, 'Ishan Kishan', 720471),
	(368, 'Siddhesh Lad', 422342),
	(369, 'Evin Lewis', 431901),
	(370, 'Sharad Lumba', 726989),
	(371, 'Mitchell McClenaghan', 319439),
	(372, 'Mayank Markande', 1081442),
	(373, 'Adam Milne', 450860),
	(374, 'Mohsin Khan', 1132005),
	(375, 'Mustafizur Rahman', 330902),
	(376, 'MD Nidheesh', 822703),
	(377, 'Hardik Pandya', 625371),
	(378, 'Krunal Pandya', 471342),
	(379, 'Kieron Pollard', 230559),
	(380, 'Anukul Roy', 1079839),
	(381, 'Pradeep Sangwan', 279545),
	(382, 'Tajinder Singh', 1079860),
	(383, 'Aditya Tare', 333904),
	(384, 'Saurabh Tiwary', 35390),
	(385, 'Suryakumar Yadav', 446507),
	(386, 'Jason Behrendorff', 272477),
	(387, 'Pat Cummins', 489889),
	(388, 'Ajinkya Rahane', 277916),
	(389, 'Ankit Sharma', 422992),
	(390, 'Anureet Singh', 376324),
	(391, 'Jofra Archer', 669855),
	(392, 'Stuart Binny', 27223),
	(393, 'Aryaman Birla', 1126227),
	(394, 'Jos Buttler', 308967),
	(395, 'Prashant Chopra', 500135),
	(396, 'Shreyas Gopal', 344580),
	(397, 'Krishnappa Gowtham', 424377),
	(398, 'Heinrich Klaasen', 436757),
	(399, 'Dhawal Kulkarni', 277955),
	(400, 'Ben Laughlin', 315623),
	(401, 'Mahipal Lomror', 853265),
	(402, 'Sudhesan Midhun', 1131619),
	(403, 'Sanju Samson', 425943),
	(404, 'Jatin Saxena', 34349),
	(405, 'D\'Arcy Short', 308798),
	(406, 'Ish Sodhi', 559066),
	(407, 'Ben Stokes', 311158),
	(408, 'Rahul Tripathi', 446763),
	(409, 'Jaydev Unadkat', 390484),
	(410, 'Dushmantha Chameera', 552152),
	(411, 'Steven Smith', 267192),
	(412, 'Zahir Khan', 712219),
	(413, 'Ravichandran Ashwin', 26421),
	(414, 'Akshdeep Nath', 500360),
	(415, 'Mayank Agarwal', 398438),
	(416, 'Mayank Dagar', 942367),
	(417, 'Ben Dwarshuis', 679567),
	(418, 'Aaron Finch', 5334),
	(419, 'Chris Gayle', 51880),
	(420, 'Manzoor Dar', 1080203),
	(421, 'David Miller', 321777),
	(422, 'Mujeeb Ur Rahman', 974109),
	(423, 'Karun Nair', 398439),
	(424, 'Axar Patel', 554691),
	(425, 'Lokesh Rahul', 422108),
	(426, 'Ankit Rajpoot', 591650),
	(427, 'Pardeep Sahu', 34155),
	(428, 'Mohit Sharma', 537119),
	(429, 'Barinder Sran', 537126),
	(430, 'Marcus Stoinis', 325012),
	(431, 'Manoj Tiwary', 35565),
	(432, 'Andrew Tye', 459508),
	(433, 'Yuvraj Singh', 36084),
	(434, 'Shreyas Iyer', 642519),
	(435, 'Abhishek Sharma', 1070183),
	(436, 'Avesh Khan', 694211),
	(437, 'Trent Boult', 277912),
	(438, 'Daniel Christian', 4864),
	(439, 'Junior Dala', 545467),
	(440, 'Gautam Gambhir', 28763),
	(441, 'Sayan Ghosh', 736413),
	(442, 'Gurkeerat Singh Mann', 537124),
	(443, 'Sandeep Lamichhane', 960361),
	(444, 'Manjot Kalra', 1079842),
	(445, 'Glenn Maxwell', 325026),
	(446, 'Amit Mishra', 31107),
	(447, 'Mohammed Shami', 481896),
	(448, 'Colin Munro', 232359),
	(449, 'Shahbaz Nadeem', 31872),
	(450, 'Naman Ojha', 32102),
	(451, 'Rishabh Pant', 931581),
	(452, 'Harshal Patel', 390481),
	(453, 'Liam Plunkett', 19264),
	(454, 'Jason Roy', 298438),
	(455, 'Vijay Shankar', 477021),
	(456, 'Prithvi Shaw', 1070168),
	(457, 'Rahul Tewatia', 423838),
	(458, 'Jayant Yadav', 447587),
	(459, 'Chris Morris', 439952),
	(460, 'Kagiso Rabada', 550215),
	(461, 'KM Asif', 1083030),
	(462, 'Sam Billings', 297628),
	(463, 'Chaitanya Bishnoi', 628217),
	(464, 'Dwayne Bravo', 51439),
	(465, 'Deepak Chahar', 447261),
	(466, 'MS Dhoni', 28081),
	(467, 'Faf du Plessis', 44828),
	(468, 'Harbhajan Singh', 29264),
	(469, 'Imran Tahir', 40618),
	(470, 'Ravindra Jadeja', 234675),
	(471, 'Narayan Jagadeesan', 1048813),
	(472, 'Kshitiz Sharma', 625380),
	(473, 'Monu Kumar', 694209),
	(474, 'Lungi Ngidi', 542023),
	(475, 'Suresh Raina', 33335),
	(476, 'Ambati Rayudu', 33141),
	(477, 'Kanishk Seth', 849737),
	(478, 'Karn Sharma', 30288),
	(479, 'Dhruv Shorey', 590327),
	(480, 'Shardul Thakur', 475281),
	(481, 'Murali Vijay', 237095),
	(482, 'Shane Watson', 8180),
	(483, 'David Willey', 308251),
	(484, 'Mark Wood', 351588),
	(485, 'Kedar Jadhav', 290716),
	(486, 'Mitchell Santner', 502714),
	(487, 'Kane Williamson', 277906),
	(488, 'Tanmay Agarwal', 792725),
	(489, 'Khaleel Ahmed', 942645),
	(490, 'Basil Thampi', 732291),
	(491, 'Ricky Bhui', 642531),
	(492, 'Bipul Sharma', 35928),
	(493, 'Carlos Brathwaite', 457249),
	(494, 'Shikhar Dhawan', 28235),
	(495, 'Shreevats Goswami', 302579),
	(496, 'Alex Hales', 249866),
	(497, 'Mehdi Hasan', 477050),
	(498, 'Deepak Hooda', 497121),
	(499, 'Chris Jordan', 288992),
	(500, 'Siddarth Kaul', 326017),
	(501, 'Bhuvneshwar Kumar', 326016),
	(502, 'Mohammad Nabi', 25913),
	(503, 'T Natarajan', 802575),
	(504, 'Manish Pandey', 290630),
	(505, 'Yusuf Pathan', 32498),
	(506, 'Rashid Khan', 793463),
	(507, 'Sachin Baby', 432783),
	(508, 'Wriddhiman Saha', 279810),
	(509, 'Sandeep Sharma', 438362),
	(510, 'Shakib Al Hasan', 56143),
	(511, 'Billy Stanlake', 533042),
	(512, 'David Warner', 219889);
/*!40000 ALTER TABLE `player` ENABLE KEYS */;

-- Dumping structure for table cricfant.player_conflict_res
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

-- Dumping data for table cricfant.player_conflict_res: ~0 rows (approximately)
/*!40000 ALTER TABLE `player_conflict_res` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_conflict_res` ENABLE KEYS */;

-- Dumping structure for table cricfant.squad
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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table cricfant.squad: ~4 rows (approximately)
/*!40000 ALTER TABLE `squad` DISABLE KEYS */;
REPLACE INTO `squad` (`id`, `name`, `user_id`, `tournament_id`, `subs_left`, `points`) VALUES
	(36, 'test', 19, 1, 80, 514),
	(37, 'test', 19, 1, 80, 514),
	(38, 'test', 19, 1, 80, 514),
	(40, 'test', 19, 1, 80, 0);
/*!40000 ALTER TABLE `squad` ENABLE KEYS */;

-- Dumping structure for table cricfant.squad_player
CREATE TABLE IF NOT EXISTS `squad_player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `squad_id` int(11) NOT NULL,
  `tournament_team_player_id` int(11) NOT NULL,
  `power_type` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `squad_id_player_id` (`squad_id`,`tournament_team_player_id`),
  KEY `FK_member_squad_player` (`tournament_team_player_id`),
  CONSTRAINT `squad_player_ibfk_1` FOREIGN KEY (`squad_id`) REFERENCES `squad` (`id`),
  CONSTRAINT `squad_player_ibfk_2` FOREIGN KEY (`tournament_team_player_id`) REFERENCES `tournament_team_player` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table cricfant.squad_player: ~33 rows (approximately)
/*!40000 ALTER TABLE `squad_player` DISABLE KEYS */;
REPLACE INTO `squad_player` (`id`, `squad_id`, `tournament_team_player_id`, `power_type`) VALUES
	(1, 36, 17, NULL),
	(2, 36, 18, NULL),
	(3, 36, 98, 'BATTING'),
	(4, 36, 7, NULL),
	(5, 36, 99, NULL),
	(6, 36, 112, 'BOWLING'),
	(7, 36, 115, NULL),
	(8, 36, 8, NULL),
	(9, 36, 10, NULL),
	(10, 36, 114, NULL),
	(11, 36, 20, NULL),
	(12, 37, 17, NULL),
	(13, 37, 18, NULL),
	(14, 37, 98, 'BATTING'),
	(15, 37, 7, NULL),
	(16, 37, 99, NULL),
	(17, 37, 112, 'BOWLING'),
	(18, 37, 115, NULL),
	(19, 37, 8, NULL),
	(20, 37, 10, NULL),
	(21, 37, 114, NULL),
	(22, 37, 20, NULL),
	(23, 38, 17, NULL),
	(24, 38, 18, NULL),
	(25, 38, 98, 'BATTING'),
	(26, 38, 7, NULL),
	(27, 38, 99, NULL),
	(28, 38, 112, 'BOWLING'),
	(29, 38, 115, NULL),
	(30, 38, 8, NULL),
	(31, 38, 10, NULL),
	(32, 38, 114, NULL),
	(33, 38, 20, NULL);
/*!40000 ALTER TABLE `squad_player` ENABLE KEYS */;

-- Dumping structure for table cricfant.team
CREATE TABLE IF NOT EXISTS `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT '0',
  `short_name` varchar(8) NOT NULL DEFAULT '0',
  `ext_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ext_id` (`ext_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table cricfant.team: ~8 rows (approximately)
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

-- Dumping structure for table cricfant.tournament
CREATE TABLE IF NOT EXISTS `tournament` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT '0',
  `total_subs` int(11) NOT NULL DEFAULT '0',
  `free_subs` int(11) NOT NULL,
  `unlimited_subs` bit(1) NOT NULL DEFAULT b'0',
  `squad_budget` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table cricfant.tournament: ~0 rows (approximately)
/*!40000 ALTER TABLE `tournament` DISABLE KEYS */;
REPLACE INTO `tournament` (`id`, `name`, `total_subs`, `free_subs`, `unlimited_subs`, `squad_budget`) VALUES
	(1, 'test_tournament', 80, 1, b'0', 1000000);
/*!40000 ALTER TABLE `tournament` ENABLE KEYS */;

-- Dumping structure for table cricfant.tournament_team
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

-- Dumping data for table cricfant.tournament_team: ~8 rows (approximately)
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

-- Dumping structure for table cricfant.tournament_team_player
CREATE TABLE IF NOT EXISTS `tournament_team_player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) NOT NULL DEFAULT '0',
  `tournament_team_id` int(11) NOT NULL DEFAULT '0',
  `skill` varchar(50) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `team_id_tournament_id` (`player_id`,`tournament_team_id`),
  KEY `FK_tournament_team_tournament` (`tournament_team_id`),
  CONSTRAINT `tournament_team_player_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`),
  CONSTRAINT `tournament_team_player_ibfk_2` FOREIGN KEY (`tournament_team_id`) REFERENCES `tournament_team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=258 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- Dumping data for table cricfant.tournament_team_player: ~198 rows (approximately)
/*!40000 ALTER TABLE `tournament_team_player` DISABLE KEYS */;
REPLACE INTO `tournament_team_player` (`id`, `player_id`, `tournament_team_id`, `skill`, `price`) VALUES
	(3, 461, 1, 'BOWLER', 80000),
	(4, 462, 1, 'KEEPER', 80000),
	(5, 463, 1, 'ALL_ROUNDER', 80000),
	(6, 464, 1, 'ALL_ROUNDER', 80000),
	(7, 465, 1, 'BOWLER', 80000),
	(8, 466, 1, 'KEEPER', 80000),
	(9, 467, 1, 'BATSMAN', 80000),
	(10, 468, 1, 'BOWLER', 80000),
	(11, 469, 1, 'BOWLER', 80000),
	(12, 470, 1, 'ALL_ROUNDER', 80000),
	(13, 471, 1, 'BATSMAN', 80000),
	(14, 472, 1, 'BATSMAN', 80000),
	(15, 473, 1, 'ALL_ROUNDER', 80000),
	(16, 474, 1, 'BOWLER', 80000),
	(17, 475, 1, 'BATSMAN', 80000),
	(18, 476, 1, 'BATSMAN', 80000),
	(19, 477, 1, 'BOWLER', 80000),
	(20, 478, 1, 'BOWLER', 80000),
	(21, 479, 1, 'BATSMAN', 80000),
	(22, 480, 1, 'BOWLER', 80000),
	(23, 481, 1, 'BATSMAN', 80000),
	(24, 482, 1, 'ALL_ROUNDER', 80000),
	(25, 483, 1, 'ALL_ROUNDER', 80000),
	(26, 484, 1, 'BOWLER', 80000),
	(27, 485, 1, 'ALL_ROUNDER', 80000),
	(28, 486, 1, 'ALL_ROUNDER', 80000),
	(29, 434, 2, 'BATSMAN', 80000),
	(30, 435, 2, 'BOWLER', 80000),
	(31, 436, 2, 'BOWLER', 80000),
	(32, 437, 2, 'BOWLER', 80000),
	(33, 438, 2, 'ALL_ROUNDER', 80000),
	(34, 439, 2, 'BOWLER', 80000),
	(35, 440, 2, 'BATSMAN', 80000),
	(36, 441, 2, 'BOWLER', 80000),
	(37, 442, 2, 'BATSMAN', 80000),
	(38, 443, 2, 'BOWLER', 80000),
	(39, 444, 2, 'BATSMAN', 80000),
	(40, 445, 2, 'ALL_ROUNDER', 80000),
	(41, 446, 2, 'BOWLER', 80000),
	(42, 447, 2, 'BOWLER', 80000),
	(43, 448, 2, 'BATSMAN', 80000),
	(44, 449, 2, 'BOWLER', 80000),
	(45, 450, 2, 'KEEPER', 80000),
	(46, 451, 2, 'KEEPER', 80000),
	(47, 452, 2, 'BOWLER', 80000),
	(48, 453, 2, 'BOWLER', 80000),
	(49, 454, 2, 'BATSMAN', 80000),
	(50, 455, 2, 'ALL_ROUNDER', 80000),
	(51, 456, 2, 'BATSMAN', 80000),
	(52, 457, 2, 'BOWLER', 80000),
	(53, 458, 2, 'BOWLER', 80000),
	(54, 459, 2, 'ALL_ROUNDER', 80000),
	(55, 460, 2, 'BOWLER', 80000),
	(56, 413, 3, 'ALL_ROUNDER', 80000),
	(57, 414, 3, 'BATSMAN', 80000),
	(58, 415, 3, 'BATSMAN', 80000),
	(59, 416, 3, 'BOWLER', 80000),
	(60, 417, 3, 'BOWLER', 80000),
	(61, 418, 3, 'BATSMAN', 80000),
	(62, 419, 3, 'ALL_ROUNDER', 80000),
	(63, 420, 3, 'BATSMAN', 80000),
	(64, 421, 3, 'BATSMAN', 80000),
	(65, 422, 3, 'BOWLER', 80000),
	(66, 423, 3, 'BATSMAN', 80000),
	(67, 424, 3, 'ALL_ROUNDER', 80000),
	(68, 425, 3, 'BATSMAN', 80000),
	(69, 426, 3, 'BOWLER', 80000),
	(70, 427, 3, 'ALL_ROUNDER', 80000),
	(71, 428, 3, 'BOWLER', 80000),
	(72, 429, 3, 'BOWLER', 80000),
	(73, 430, 3, 'BATSMAN', 80000),
	(74, 431, 3, 'BATSMAN', 80000),
	(75, 432, 3, 'BOWLER', 80000),
	(76, 433, 3, 'BATSMAN', 80000),
	(77, 315, 4, 'KEEPER', 80000),
	(78, 316, 4, 'ALL_ROUNDER', 80000),
	(79, 317, 4, 'ALL_ROUNDER', 80000),
	(80, 318, 4, 'ALL_ROUNDER', 80000),
	(81, 319, 4, 'BATSMAN', 80000),
	(82, 320, 4, 'BOWLER', 80000),
	(83, 321, 4, 'BOWLER', 80000),
	(84, 322, 4, 'BATSMAN', 80000),
	(85, 323, 4, 'BOWLER', 80000),
	(86, 324, 4, 'BOWLER', 80000),
	(87, 325, 4, 'BATSMAN', 80000),
	(88, 326, 4, 'ALL_ROUNDER', 80000),
	(89, 327, 4, 'BOWLER', 80000),
	(90, 328, 4, 'ALL_ROUNDER', 80000),
	(91, 329, 4, 'BATSMAN', 80000),
	(92, 330, 4, 'BATSMAN', 80000),
	(93, 331, 4, 'BATSMAN', 80000),
	(94, 332, 4, 'BOWLER', 80000),
	(95, 333, 4, 'BATSMAN', 80000),
	(96, 334, 4, 'BOWLER', 80000),
	(97, 335, 4, 'BOWLER', 80000),
	(98, 361, 5, 'BATSMAN', 80000),
	(99, 362, 5, 'BOWLER', 80000),
	(100, 363, 5, 'BOWLER', 80000),
	(101, 364, 5, 'ALL_ROUNDER', 80000),
	(102, 365, 5, 'ALL_ROUNDER', 80000),
	(103, 366, 5, 'ALL_ROUNDER', 80000),
	(104, 367, 5, 'BATSMAN', 80000),
	(105, 368, 5, 'BATSMAN', 80000),
	(106, 369, 5, 'BATSMAN', 80000),
	(107, 370, 5, 'BATSMAN', 80000),
	(108, 371, 5, 'BOWLER', 80000),
	(109, 372, 5, 'BOWLER', 80000),
	(110, 373, 5, 'BOWLER', 80000),
	(111, 374, 5, 'BOWLER', 80000),
	(112, 375, 5, 'BOWLER', 80000),
	(113, 376, 5, 'BOWLER', 80000),
	(114, 377, 5, 'ALL_ROUNDER', 80000),
	(115, 378, 5, 'ALL_ROUNDER', 80000),
	(116, 379, 5, 'ALL_ROUNDER', 80000),
	(117, 380, 5, 'ALL_ROUNDER', 80000),
	(118, 381, 5, 'BOWLER', 80000),
	(119, 382, 5, 'ALL_ROUNDER', 80000),
	(120, 383, 5, 'KEEPER', 80000),
	(121, 384, 5, 'BATSMAN', 80000),
	(122, 385, 5, 'BATSMAN', 80000),
	(123, 386, 5, 'BOWLER', 80000),
	(124, 387, 5, 'BOWLER', 80000),
	(125, 388, 6, 'BATSMAN', 80000),
	(126, 389, 6, 'BOWLER', 80000),
	(127, 390, 6, 'BOWLER', 80000),
	(128, 391, 6, 'ALL_ROUNDER', 80000),
	(129, 392, 6, 'ALL_ROUNDER', 80000),
	(130, 393, 6, 'BATSMAN', 80000),
	(131, 394, 6, 'KEEPER', 80000),
	(132, 395, 6, 'BATSMAN', 80000),
	(133, 396, 6, 'ALL_ROUNDER', 80000),
	(134, 397, 6, 'ALL_ROUNDER', 80000),
	(135, 398, 6, 'KEEPER', 80000),
	(136, 399, 6, 'BOWLER', 80000),
	(137, 400, 6, 'BOWLER', 80000),
	(138, 401, 6, 'ALL_ROUNDER', 80000),
	(139, 402, 6, 'BOWLER', 80000),
	(140, 403, 6, 'KEEPER', 80000),
	(141, 404, 6, 'BATSMAN', 80000),
	(142, 405, 6, 'BATSMAN', 80000),
	(143, 406, 6, 'BOWLER', 80000),
	(144, 407, 6, 'ALL_ROUNDER', 80000),
	(145, 408, 6, 'BATSMAN', 80000),
	(146, 409, 6, 'BOWLER', 80000),
	(147, 410, 6, 'BOWLER', 80000),
	(148, 411, 6, 'BATSMAN', 80000),
	(149, 412, 6, 'BOWLER', 80000),
	(150, 336, 8, 'BATSMAN', 80000),
	(151, 337, 8, 'ALL_ROUNDER', 80000),
	(152, 338, 8, 'ALL_ROUNDER', 80000),
	(153, 339, 8, 'BOWLER', 80000),
	(154, 340, 8, 'BOWLER', 80000),
	(155, 341, 8, 'BOWLER', 80000),
	(156, 342, 8, 'ALL_ROUNDER', 80000),
	(157, 343, 8, 'KEEPER', 80000),
	(158, 344, 8, 'ALL_ROUNDER', 80000),
	(159, 345, 8, 'KEEPER', 80000),
	(160, 346, 8, 'BOWLER', 80000),
	(161, 347, 8, 'BATSMAN', 80000),
	(162, 348, 8, 'BOWLER', 80000),
	(163, 349, 8, 'KEEPER', 80000),
	(164, 350, 8, 'ALL_ROUNDER', 80000),
	(165, 351, 8, 'BOWLER', 80000),
	(166, 352, 8, 'BOWLER', 80000),
	(167, 353, 8, 'KEEPER', 80000),
	(168, 354, 8, 'BOWLER', 80000),
	(169, 355, 8, 'BOWLER', 80000),
	(170, 356, 8, 'BATSMAN', 80000),
	(171, 357, 8, 'BATSMAN', 80000),
	(172, 358, 8, 'BOWLER', 80000),
	(173, 359, 8, 'BOWLER', 80000),
	(174, 360, 8, 'BOWLER', 80000),
	(175, 487, 10, 'BATSMAN', 80000),
	(176, 488, 10, 'BATSMAN', 80000),
	(177, 489, 10, 'BOWLER', 80000),
	(178, 490, 10, 'BOWLER', 80000),
	(179, 491, 10, 'BATSMAN', 80000),
	(180, 492, 10, 'ALL_ROUNDER', 80000),
	(181, 493, 10, 'ALL_ROUNDER', 80000),
	(182, 494, 10, 'BATSMAN', 80000),
	(183, 495, 10, 'KEEPER', 80000),
	(184, 496, 10, 'BATSMAN', 80000),
	(185, 497, 10, 'BOWLER', 80000),
	(186, 498, 10, 'ALL_ROUNDER', 80000),
	(187, 499, 10, 'BOWLER', 80000),
	(188, 500, 10, 'BOWLER', 80000),
	(189, 501, 10, 'BOWLER', 80000),
	(190, 502, 10, 'ALL_ROUNDER', 80000),
	(191, 503, 10, 'BOWLER', 80000),
	(192, 504, 10, 'BATSMAN', 80000),
	(193, 505, 10, 'ALL_ROUNDER', 80000),
	(194, 506, 10, 'BOWLER', 80000),
	(195, 507, 10, 'BATSMAN', 80000),
	(196, 508, 10, 'KEEPER', 80000),
	(197, 509, 10, 'BOWLER', 80000),
	(198, 510, 10, 'ALL_ROUNDER', 80000),
	(199, 511, 10, 'BOWLER', 80000),
	(200, 512, 10, 'BATSMAN', 80000);
/*!40000 ALTER TABLE `tournament_team_player` ENABLE KEYS */;

-- Dumping structure for table cricfant.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '0',
  `password` varchar(128) NOT NULL DEFAULT '0',
  `role` varchar(64) NOT NULL DEFAULT '0',
  `email` varchar(64) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table cricfant.user: ~10 rows (approximately)
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
	(33, 'VIVEK VIJAYAKUMAR', '$2a$10$5yvfSUzil5dXr4n979fkQ.EZk6x/yTxg3F5/UKd2AbGGImQMXdrgi', 'ROLE_USER', 'vivek.1612sd@gmail.com'),
	(34, 'test', '$2a$10$wKTUYG8tmxXsH2VZbqqgHe0oeWF.HhVgctGZI3e4artw3Ff2hjL1G', 'ROLE_USER', 'test4@test.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table cricfant.venue
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

-- Dumping data for table cricfant.venue: ~33 rows (approximately)
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

-- Dumping structure for trigger cricfant.match_after_update
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
