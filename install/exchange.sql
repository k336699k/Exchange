-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.22-log - MySQL Community Server (GPL)
-- ОС Сервера:                   Win64
-- HeidiSQL Версия:              8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных exchange
CREATE DATABASE IF NOT EXISTS `exchange` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `exchange`;


-- Дамп структуры для таблица exchange.metals
CREATE TABLE IF NOT EXISTS `metals` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT '0',
  `quantity` varchar(50) DEFAULT '0',
  `price` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы exchange.metals: ~4 rows (приблизительно)
/*!40000 ALTER TABLE `metals` DISABLE KEYS */;
INSERT INTO `metals` (`id`, `title`, `quantity`, `price`) VALUES
	(1, 'Ариатура 10', '10 т', 10000),
	(2, 'Арматура 20', '20 т', 20000),
	(3, 'Швеллер 12', '5 т', 5000),
	(4, 'Труба профильная', '2 т', 2000);
/*!40000 ALTER TABLE `metals` ENABLE KEYS */;


-- Дамп структуры для таблица exchange.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы exchange.roles: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `name`) VALUES
	(1, 'Продавец'),
	(2, 'Покупатель');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;


-- Дамп структуры для таблица exchange.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) DEFAULT '0',
  `last_name` varchar(50) DEFAULT '0',
  `login` varchar(50) DEFAULT '0',
  `password` varchar(50) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы exchange.users: ~4 rows (приблизительно)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `first_name`, `last_name`, `login`, `password`) VALUES
	(1, 'Константин', 'Шаплыко', 'k336699k@mail.ru', '123456'),
	(2, 'Виктория', 'Холодинская', 'h_v_e@mail.ru', '67565'),
	(3, 'Алексей', 'Петров', 'petrov@tut.by', '3423245'),
	(4, 'Илья', 'Русый', 'rus@yandex.ru', '664567457'),
	(10, 'Никита', 'Батюшков', 'nikita@tut.by', '43498');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
