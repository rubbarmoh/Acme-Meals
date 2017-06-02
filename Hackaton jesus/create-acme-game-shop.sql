start transaction;

create database `Acme-Game-Shop`;

use 'Acme-Game-Shop';


create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';


grant select, insert, update, delete 
	on `Acme-Game-Shop`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Acme-Game-Shop`.* to 'acme-manager'@'%';


-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Game-Shop
-- ------------------------------------------------------
-- Server version	5.5.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5w0f1bikb2adecvdifuxbep9d` (`creditCard_id`),
  KEY `FK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_cgls5lrufx91ufsyh467spwa3` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_5w0f1bikb2adecvdifuxbep9d` FOREIGN KEY (`creditCard_id`) REFERENCES `creditcard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gl4ryvfr1pd7798c3kuo22hvb` (`creditCard_id`),
  KEY `FK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`),
  CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_gl4ryvfr1pd7798c3kuo22hvb` FOREIGN KEY (`creditCard_id`) REFERENCES `creditcard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (79,0,'admin@email.com','administrator','0000','-',NULL,69);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
INSERT INTO `banner` VALUES (114,0,'https://cdn.areajugones.es/wp-content/uploads/2017/01/tekken7coleccionista-1024x614.jpg'),(115,0,'https://media.redadn.es/imagenes/the-legend-of-zelda-breath-of-the-wild-nintendo-switch-wii-u_289641.jpg'),(116,0,'http://cdn.akamai.steamstatic.com/steam/apps/730/header.jpg?t=1490209724');
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_foei036ov74bv692o5lh5oi66` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (89,0,'shooter'),(90,0,'strategy'),(91,0,'multiplayer'),(92,0,'single player'),(93,0,'sport');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_game`
--

DROP TABLE IF EXISTS `category_game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_game` (
  `categories_id` int(11) NOT NULL,
  `games_id` int(11) NOT NULL,
  KEY `FK_sapbvj90srsvexsp1rk3xve0u` (`games_id`),
  KEY `FK_iebrrgl906513hqvr17veple0` (`categories_id`),
  CONSTRAINT `FK_iebrrgl906513hqvr17veple0` FOREIGN KEY (`categories_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FK_sapbvj90srsvexsp1rk3xve0u` FOREIGN KEY (`games_id`) REFERENCES `game` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_game`
--

LOCK TABLES `category_game` WRITE;
/*!40000 ALTER TABLE `category_game` DISABLE KEYS */;
INSERT INTO `category_game` VALUES (90,94),(91,96),(92,94),(93,95);
/*!40000 ALTER TABLE `category_game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_3vq4up2ft9jn57roytt0nopyd` (`score`),
  KEY `FK_ga0pnb7dqgnu7oy42rr99u9sr` (`customer_id`),
  KEY `FK_soxuobq6o64th8irfdgk8f10q` (`game_id`),
  CONSTRAINT `FK_soxuobq6o64th8irfdgk8f10q` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`),
  CONSTRAINT `FK_ga0pnb7dqgnu7oy42rr99u9sr` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (104,0,'El juego me gusta mucho','2017-03-04 19:00:00',9,'Buenas',80,94),(105,0,'No esta mal','2017-03-02 19:00:00',5,'Hola',80,95),(106,0,'No me ha gustado nada de nada','2017-03-01 18:32:00',0,'Saludos',81,96);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditcard`
--

DROP TABLE IF EXISTS `creditcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creditcard` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `expirationMonth` int(11) DEFAULT NULL,
  `expirationYear` int(11) DEFAULT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_k0yy0i1pnw0d0cmkwtp1sdghk` (`expirationMonth`,`expirationYear`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditcard`
--

LOCK TABLES `creditcard` WRITE;
/*!40000 ALTER TABLE `creditcard` DISABLE KEYS */;
INSERT INTO `creditcard` VALUES (86,0,'VISA',356,8,2019,'Developer1','5760651824445570'),(87,0,'MASTERCARD',425,3,2020,'Developer2','5732718459670965'),(88,0,'DISCOVER',678,8,2030,'Customer1','4539144561950377');
/*!40000 ALTER TABLE `creditcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `critic`
--

DROP TABLE IF EXISTS `critic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `critic` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  `magazine` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_c1s5sx9ambn10wa059givano5` (`creditCard_id`),
  KEY `FK_rq8eilj98ork1yxlo7xggp5c8` (`userAccount_id`),
  CONSTRAINT `FK_rq8eilj98ork1yxlo7xggp5c8` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_c1s5sx9ambn10wa059givano5` FOREIGN KEY (`creditCard_id`) REFERENCES `creditcard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `critic`
--

LOCK TABLES `critic` WRITE;
/*!40000 ALTER TABLE `critic` DISABLE KEYS */;
INSERT INTO `critic` VALUES (122,0,'critic1@email.com','Axel','1111','Springer',NULL,70,'Hobby Consolas'),(123,0,'critic2@email.com',' Steve','2222','Jarratt',NULL,71,'Edge'),(124,0,'critic3@email.com','Andy','3333','McNamara',NULL,72,'Game Informer');
/*!40000 ALTER TABLE `critic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  `birthdate` datetime DEFAULT NULL,
  `shoppingCart_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qv2l4kfmx51um4ry4wlfu52l9` (`shoppingCart_id`),
  KEY `FK_2c2mhcwiahfypfbl0gy9fx6r4` (`creditCard_id`),
  KEY `FK_pwmktpkay2yx7v00mrwmuscl8` (`userAccount_id`),
  CONSTRAINT `FK_pwmktpkay2yx7v00mrwmuscl8` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_2c2mhcwiahfypfbl0gy9fx6r4` FOREIGN KEY (`creditCard_id`) REFERENCES `creditcard` (`id`),
  CONSTRAINT `FK_qv2l4kfmx51um4ry4wlfu52l9` FOREIGN KEY (`shoppingCart_id`) REFERENCES `shoppingcart` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (80,1,'customer1@email.com','NameCustomer1','1234','SurnameCustomer1',88,73,'1980-06-10 00:00:00',111),(81,1,'customer2@email.com','NameCustomer2','1232','SurnameCustomer2',NULL,74,'1990-06-10 00:00:00',112),(82,1,'customer3@email.com','NameCustomer3','1233','SurnameCustomer3',NULL,75,'2010-06-10 00:00:00',113);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `developer`
--

DROP TABLE IF EXISTS `developer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `developer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_km8s9beykwsup92b6gnum7a09` (`creditCard_id`),
  KEY `FK_h964g94t51e468qk3039iy7y8` (`userAccount_id`),
  CONSTRAINT `FK_h964g94t51e468qk3039iy7y8` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_km8s9beykwsup92b6gnum7a09` FOREIGN KEY (`creditCard_id`) REFERENCES `creditcard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `developer`
--

LOCK TABLES `developer` WRITE;
/*!40000 ALTER TABLE `developer` DISABLE KEYS */;
INSERT INTO `developer` VALUES (83,1,'developer1@email.com','NameDeveloper1','954845024','SurnameDeveloper1',86,76,'Ubisoft'),(84,1,'developer2@email.com','NameDeveloper2','498347120','SurnameDeveloper2',87,77,'Blizzard'),(85,0,'developer3@email.com','NameDeveloper3','1233','SurnameDeveloper3',NULL,78,'Nintendo');
/*!40000 ALTER TABLE `developer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `percentage` int(11) DEFAULT NULL,
  `used` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6unf0wlkrwqx56fu0rfsiin9q` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (117,0,'U46-FEO-GU8',10,''),(118,0,'HRJ-732-J9W',25,'\0'),(119,0,'J9W-1FK-NKK',50,''),(120,0,'EJ9-HT9-H8F',50,'\0'),(121,0,'KN0-13F-JBE',75,'\0');
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `sellsNumber` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `developer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sqbqgqga5l97q8okk26wao7a0` (`title`),
  KEY `UK_2ytpirodl31a1hx9cj3peueg4` (`sellsNumber`),
  KEY `FK_18co2gi9frscjpumbndwdl12j` (`developer_id`),
  CONSTRAINT `FK_18co2gi9frscjpumbndwdl12j` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` VALUES (94,0,16,'Juega en el papel de Julio César y experimenta las 50 puñaladas que recibió...¡¡en 3D!!','http://www.hobbyconsolas.com/sites/hobbyconsolas.com/public/styles/main_element/public/media/image/2013/09/245606-analisis-total-war-rome-ii.jpg?itok=LtN-6MBi',45,10,'Rome total war II',83),(95,0,16,'¡Grindea como un esclavo y mira como en 3 meses te reiniciamos todo lo conseguido!','http://www.3djuegos.com/juegos/2884/diablo_3/fotos/ficha/diablo_3-1953745.jpg',40,10,'Diablo III',84),(96,0,3,'¡Vuelven las carreras mas locas con MarioKart 8','http://static.hellofriki.com/wp-content/uploads/2014/11/mario_kart_8_tournament.jpg',35,3,'MarioKart 8',84);
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',3);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messageemail`
--

DROP TABLE IF EXISTS `messageemail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messageemail` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `deletedForRecipient` bit(1) DEFAULT NULL,
  `deletedForSender` bit(1) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `recipient_id` int(11) DEFAULT NULL,
  `sender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messageemail`
--

LOCK TABLES `messageemail` WRITE;
/*!40000 ALTER TABLE `messageemail` DISABLE KEYS */;
INSERT INTO `messageemail` VALUES (130,0,'\0','\0','2017-02-10 00:00:00','Duda','¿Es compatible con w7?',83,80),(131,1,'','\0','2017-02-11 00:00:00','Re:Duda','Si, pero solo los domingos',80,83),(132,0,'\0','\0','2017-04-08 00:00:00','MarioKart 8','Podriamos comprarnos este juego',81,80),(133,0,'\0','\0','2017-04-08 12:00:00','Re: MarioKart 8','¡Vale!',80,81),(134,0,'\0','\0','2017-04-08 13:00:00','Re:Re: MarioKart 8','Jugaré con Bowser',81,80),(32768,1,'\0','\0','2017-02-10 00:00:00','Duda','¿Es compatible con w7?',83,80),(65536,1,'\0','\0','2017-06-01 23:59:00','Re: Re: MarioKart 8','miau*',81,80);
/*!40000 ALTER TABLE `messageemail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messageemail_attachments`
--

DROP TABLE IF EXISTS `messageemail_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messageemail_attachments` (
  `MessageEmail_id` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  KEY `FK_dwt4w4gjqvpr88e83emwuio6u` (`MessageEmail_id`),
  CONSTRAINT `FK_dwt4w4gjqvpr88e83emwuio6u` FOREIGN KEY (`MessageEmail_id`) REFERENCES `messageemail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messageemail_attachments`
--

LOCK TABLES `messageemail_attachments` WRITE;
/*!40000 ALTER TABLE `messageemail_attachments` DISABLE KEYS */;
/*!40000 ALTER TABLE `messageemail_attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderedgames`
--

DROP TABLE IF EXISTS `orderedgames`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderedgames` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `price` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `receipt_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hxk6bypcl6s8bn9ucqhbmn5q6` (`receipt_id`),
  CONSTRAINT `FK_hxk6bypcl6s8bn9ucqhbmn5q6` FOREIGN KEY (`receipt_id`) REFERENCES `receipt` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderedgames`
--

LOCK TABLES `orderedgames` WRITE;
/*!40000 ALTER TABLE `orderedgames` DISABLE KEYS */;
INSERT INTO `orderedgames` VALUES (100,0,44.9,'Warhammer',97),(101,0,20.5,'World of Warcraft',97),(102,0,10.9,'Age of Empire III',98),(103,0,11.9,'Crash Bandicoot',99);
/*!40000 ALTER TABLE `orderedgames` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receipt` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_a9pg9geytwape2frah75245ou` (`customer_id`),
  CONSTRAINT `FK_a9pg9geytwape2frah75245ou` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
INSERT INTO `receipt` VALUES (97,0,'2017-04-06 16:00:00',80),(98,0,'2016-09-11 12:45:00',80),(99,0,'2016-12-10 19:22:00',81);
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `draft` bit(1) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `critic_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_d8lyak4he5eevt9o9cgo151c7` (`score`),
  KEY `FK_s9mb0jivt9dtxhtw0ho45gdgo` (`critic_id`),
  KEY `FK_769lumehxnkfwduv6tmjlumwi` (`game_id`),
  CONSTRAINT `FK_769lumehxnkfwduv6tmjlumwi` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`),
  CONSTRAINT `FK_s9mb0jivt9dtxhtw0ho45gdgo` FOREIGN KEY (`critic_id`) REFERENCES `critic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (125,0,'Este juego parece que ha sido realizado por un par de lúmpenes.','','2017-04-27 12:30:00',1,'Hastiado',122,94),(126,0,'Tras deleitarme con el uso intrínseco de este juego, he comprobado que el contenido es un tanto pobre y soez.','\0','2017-05-02 14:07:00',3,'Deplorable',122,94),(127,0,'El nombre es fastuoso, sin embargo el juego no lo es.','\0','2017-05-10 10:46:00',7,'Nombre fastuoso',122,95),(128,0,'Es como si estuvieras dentro de la propia batalla. Además me gusta su estilo barroco en las texturas.','\0','2017-05-09 18:22:00',9,'Gran experiencia',123,94),(129,0,'No tiene mucha variedad y se te hace repetitivo.','','2017-05-10 09:02:00',3,'Repetitivo',123,95);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sense`
--

DROP TABLE IF EXISTS `sense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sense` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `likeGame` bit(1) DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gy4f7ue375urss1fouk9u59vw` (`customer_id`),
  KEY `FK_scl7op3lsi1wqg27blc1ngh5c` (`game_id`),
  CONSTRAINT `FK_scl7op3lsi1wqg27blc1ngh5c` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`),
  CONSTRAINT `FK_gy4f7ue375urss1fouk9u59vw` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sense`
--

LOCK TABLES `sense` WRITE;
/*!40000 ALTER TABLE `sense` DISABLE KEYS */;
INSERT INTO `sense` VALUES (107,0,'',80,94),(108,0,'',80,95),(109,0,'\0',81,96),(110,0,'',81,94);
/*!40000 ALTER TABLE `sense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingcart`
--

DROP TABLE IF EXISTS `shoppingcart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingcart` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingcart`
--

LOCK TABLES `shoppingcart` WRITE;
/*!40000 ALTER TABLE `shoppingcart` DISABLE KEYS */;
INSERT INTO `shoppingcart` VALUES (111,0),(112,0),(113,0);
/*!40000 ALTER TABLE `shoppingcart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingcart_game`
--

DROP TABLE IF EXISTS `shoppingcart_game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingcart_game` (
  `ShoppingCart_id` int(11) NOT NULL,
  `games_id` int(11) NOT NULL,
  KEY `FK_lejdw24ppx2d8sw8v536a9ilc` (`games_id`),
  KEY `FK_tlnp2bi4wml7x1fvdneaex8hf` (`ShoppingCart_id`),
  CONSTRAINT `FK_tlnp2bi4wml7x1fvdneaex8hf` FOREIGN KEY (`ShoppingCart_id`) REFERENCES `shoppingcart` (`id`),
  CONSTRAINT `FK_lejdw24ppx2d8sw8v536a9ilc` FOREIGN KEY (`games_id`) REFERENCES `game` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingcart_game`
--

LOCK TABLES `shoppingcart_game` WRITE;
/*!40000 ALTER TABLE `shoppingcart_game` DISABLE KEYS */;
/*!40000 ALTER TABLE `shoppingcart_game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (68,0,'1b3231655cebb7a1f783eddf27d254ca','super'),(69,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(70,0,'165d185050bab438668aef6faeee3be5','critic1'),(71,0,'88fc227c1f12e1f11b8b1584991c88fe','critic2'),(72,0,'8a253092f1d177ba06d104b69a159d93','critic3'),(73,0,'ffbc4675f864e0e9aab8bdf7a0437010','customer1'),(74,0,'5ce4d191fd14ac85a1469fb8c29b7a7b','customer2'),(75,0,'033f7f6121501ae98285ad77f216d5e7','customer3'),(76,0,'e857f9b3fa03593ff7787a6ba9ecd5c1','developer1'),(77,0,'19edea09031e5ea3c360597a5f9430b4','developer2'),(78,0,'24ec8cfaaf0ad6e5fdb9cf76dd6f7890','developer3');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (68,'ADMIN'),(68,'CUSTOMER'),(68,'DEVELOPER'),(68,'CRITIC'),(69,'ADMIN'),(70,'CRITIC'),(71,'CRITIC'),(72,'CRITIC'),(73,'CUSTOMER'),(74,'CUSTOMER'),(75,'CUSTOMER'),(76,'DEVELOPER'),(77,'DEVELOPER'),(78,'DEVELOPER');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-02  0:32:08

commit;