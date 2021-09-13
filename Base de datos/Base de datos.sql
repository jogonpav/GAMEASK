-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.6.4-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para db_games
CREATE DATABASE IF NOT EXISTS `db_games` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `db_games`;

-- Volcando estructura para tabla db_games.categoria
CREATE TABLE IF NOT EXISTS `categoria` (
  `ID` int(2) unsigned NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(120) NOT NULL,
  `RONDA_ID` int(2) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_categoria_ronda` (`RONDA_ID`),
  CONSTRAINT `FK_categoria_ronda` FOREIGN KEY (`RONDA_ID`) REFERENCES `ronda` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_games.categoria: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` (`ID`, `NOMBRE`, `RONDA_ID`) VALUES
	(1, 'Cultura General', 1),
	(2, 'Deportes', 2),
	(3, 'Arte y Literatura', 3),
	(4, 'Historia', 4),
	(5, 'Ciencia', 5);
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;

-- Volcando estructura para tabla db_games.historico
CREATE TABLE IF NOT EXISTS `historico` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `JUGADOR` varchar(200) DEFAULT NULL,
  `RONDA_ALCANZADA` int(2) unsigned DEFAULT NULL,
  `PREMIO_OBTENIDO` int(6) unsigned DEFAULT NULL,
  `CLASIFICACION` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_games.historico: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `historico` DISABLE KEYS */;
/*!40000 ALTER TABLE `historico` ENABLE KEYS */;

-- Volcando estructura para tabla db_games.opcion
CREATE TABLE IF NOT EXISTS `opcion` (
  `ID` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `REPUESTA` varchar(200) NOT NULL,
  `ES_CORRECTO` tinyint(1) unsigned NOT NULL,
  `PREGUNTA_ID` int(4) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_opcion_pregunta` (`PREGUNTA_ID`),
  CONSTRAINT `FK_opcion_pregunta` FOREIGN KEY (`PREGUNTA_ID`) REFERENCES `pregunta` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_games.opcion: ~100 rows (aproximadamente)
/*!40000 ALTER TABLE `opcion` DISABLE KEYS */;
INSERT INTO `opcion` (`ID`, `REPUESTA`, `ES_CORRECTO`, `PREGUNTA_ID`) VALUES
	(1, 'Mexico', 0, 1),
	(2, 'El Vaticano', 0, 1),
	(3, 'Colombia', 0, 1),
	(4, 'China', 1, 1),
	(5, 'Bacatá en Colombia', 0, 2),
	(6, 'Burj Khalifa en Dubai', 1, 2),
	(7, 'Torre Colpatria en Colombia', 0, 2),
	(8, 'Empire State en EEUU', 0, 2),
	(9, 'Los Alpes', 0, 3),
	(10, 'Los Andes', 0, 3),
	(11, 'Everest', 1, 3),
	(12, 'Kilimanjaro', 0, 3),
	(13, 'La Ballena Azul', 1, 4),
	(14, 'El ratón', 0, 4),
	(15, 'El Rinoceronte', 0, 4),
	(16, 'La Jirafa', 0, 4),
	(17, 'El Yeso', 0, 5),
	(18, 'El Diamante', 1, 5),
	(19, 'El Oro', 0, 5),
	(20, 'El Agua', 0, 5),
	(21, 'Colombia', 0, 6),
	(22, 'Brazil', 1, 6),
	(23, 'EEUU', 0, 6),
	(24, 'Guatemala', 0, 6),
	(25, '11', 1, 7),
	(26, '20', 0, 7),
	(27, '6', 0, 7),
	(28, '15', 0, 7),
	(29, 'Atletico Nacional', 0, 8),
	(30, 'El kashima', 0, 8),
	(31, 'Real Madrid', 1, 8),
	(32, 'Paris Saint Germain ', 0, 8),
	(33, 'Tolima FC', 0, 9),
	(34, 'Atletico Nacional', 1, 9),
	(35, 'Millonarios', 0, 9),
	(36, 'Junior', 0, 9),
	(37, 'El Pibe Valderrama', 0, 10),
	(38, 'Oscar Cordoba', 0, 10),
	(39, 'Ivan René Valenciano', 0, 10),
	(40, 'José René Iguita', 1, 10),
	(41, 'Gabriel Garcia Marquez', 1, 11),
	(42, 'Fernando Vallejo', 0, 11),
	(43, 'Jorge Isaccs', 0, 11),
	(44, 'Hector Abad', 0, 11),
	(45, 'El nacimiento de Venus', 0, 12),
	(46, 'La joven de la Perla', 0, 12),
	(47, 'La Gioconda', 1, 12),
	(48, 'La infanta doña Margarita de Austria', 0, 12),
	(49, 'La Manuela', 0, 13),
	(50, 'Romeo y Julieta', 0, 13),
	(51, 'Ulises', 0, 13),
	(52, 'Cien años de soledad', 1, 13),
	(53, 'Don Quijote de la Mancha', 0, 14),
	(54, 'Sancho Panza', 0, 14),
	(55, 'Miguel de Cervantes', 1, 14),
	(56, 'William Shakespeare', 0, 14),
	(57, 'Romeo y Julieta', 1, 14),
	(58, 'Moby-Dick', 0, 14),
	(59, 'Matar un ruiseñor', 0, 14),
	(60, 'A Tale of Two Cities', 0, 14),
	(61, '1969', 1, 16),
	(62, '1970', 0, 16),
	(63, '1965', 0, 16),
	(64, '1968', 0, 16),
	(65, '1595', 0, 17),
	(66, '1692', 0, 17),
	(67, '1395', 0, 17),
	(68, '1492', 1, 17),
	(69, 'Austria', 1, 18),
	(70, 'Alemania', 0, 18),
	(71, 'Francia', 0, 18),
	(72, 'Italia', 0, 18),
	(73, 'Alejando el gran conquistador', 0, 19),
	(74, 'Alejandro el barbaro', 0, 19),
	(75, 'Alejandro Magno', 1, 19),
	(76, 'Alejandro el pacificador', 0, 19),
	(77, 'Colombia', 0, 20),
	(78, 'Venezuela', 1, 20),
	(79, 'Ecuador', 0, 20),
	(80, 'Peru', 0, 20),
	(81, '3x10^8 m/s', 1, 21),
	(82, '3x10^6 m/s', 0, 21),
	(83, '3x10^10 m/s', 0, 21),
	(84, '3x10^4 m/s', 0, 21),
	(85, '4,778 K', 0, 22),
	(86, '5,078 K', 0, 22),
	(87, '3,978 K', 0, 22),
	(88, '5,778 K', 1, 22),
	(89, 'El Cobre', 0, 23),
	(90, 'El Niquel', 0, 23),
	(91, 'El Bronce', 1, 23),
	(92, 'La Plata', 0, 23),
	(93, 'Albert Einstein', 1, 24),
	(94, 'Stephen Hawking', 0, 24),
	(95, 'Charles Darwin', 0, 24),
	(96, 'Peter Higgs', 0, 24),
	(97, 'Kelvin (K)', 0, 25),
	(98, 'Newton (N)', 0, 25),
	(99, 'Horsepower (HP)', 0, 25),
	(100, 'Julios (J)', 1, 25);
/*!40000 ALTER TABLE `opcion` ENABLE KEYS */;

-- Volcando estructura para tabla db_games.pregunta
CREATE TABLE IF NOT EXISTS `pregunta` (
  `ID` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `ENUNCIADO` varchar(1000) NOT NULL,
  `CATEGORIA_ID` int(3) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_pregunta_categoria` (`CATEGORIA_ID`),
  CONSTRAINT `FK_pregunta_categoria` FOREIGN KEY (`CATEGORIA_ID`) REFERENCES `categoria` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_games.pregunta: ~25 rows (aproximadamente)
/*!40000 ALTER TABLE `pregunta` DISABLE KEYS */;
INSERT INTO `pregunta` (`ID`, `ENUNCIADO`, `CATEGORIA_ID`) VALUES
	(1, '¿Cuál es el país con más habitantes del mundo?', 1),
	(2, '¿Cuál es el edificio más alto del mundo?', 1),
	(3, '¿Cuál es la montaña más alta del mundo?', 1),
	(4, '¿Cuál es el animal más grande de la Tierra?', 1),
	(5, '¿Cuál es el mineral más duro del planeta?', 1),
	(6, '¿Qué selección ha ganado más mundiales de fútbol?', 2),
	(7, '¿Cuántos jugadores hay en un equipo de fútbol?', 2),
	(8, 'El siguiente equipo es de españa', 2),
	(9, '¿Cuál equipo colombiano es de Medellin\'', 2),
	(10, '¿Jugador colombiano autor del Escorpion?', 2),
	(11, '¿Cuál escritor colombiano ha ganado el Premio Novel de literatura?', 3),
	(12, 'El Retrato de Lisa Gherardini es conocido ¿Cómo?', 3),
	(13, '¿Cuál de las siguientes Obras es de Gabriel Garcia Marquz', 3),
	(14, '¿Cuál es el autor de el ingenioso caballero Don Quijote de la Mancha?', 3),
	(15, '¿Es obra de William Shakespeare?', 3),
	(16, '¿Cuál es el año en que el hombre fue a la luna por primera vez?', 4),
	(17, '¿En cuál año se descubrió a America?', 4),
	(18, '¿Cuál era la nacionalidad de Adolfo Hitler?', 4),
	(19, '¿Alejandro III de Macedonia es también conocido cómo?', 4),
	(20, '¿En cuál pais nació Simon Bolivar?', 4),
	(21, '¿Cuál es la velocidad de la luz?', 5),
	(22, '¿Cuál es la temperatura de la superficie del sol?', 5),
	(23, '¿Cual de estos elementos químicos no pertenece a la tabla periodica?', 5),
	(24, '¿La teoría de la retavilidad fue propuesta por?', 5),
	(25, '¿Cuál es la medida de la energía?', 5);
/*!40000 ALTER TABLE `pregunta` ENABLE KEYS */;

-- Volcando estructura para tabla db_games.ronda
CREATE TABLE IF NOT EXISTS `ronda` (
  `ID` int(2) unsigned NOT NULL AUTO_INCREMENT,
  `Nivel` int(2) unsigned NOT NULL,
  `PREMIO` int(5) unsigned NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla db_games.ronda: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `ronda` DISABLE KEYS */;
INSERT INTO `ronda` (`ID`, `Nivel`, `PREMIO`) VALUES
	(1, 1, 500),
	(2, 2, 1000),
	(3, 3, 2000),
	(4, 4, 4000),
	(5, 5, 8500);
/*!40000 ALTER TABLE `ronda` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
