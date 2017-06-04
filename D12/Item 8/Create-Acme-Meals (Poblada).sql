start transaction;

drop database if exists `Acme-Meals`;
create database `Acme-Meals`;

use `Acme-Meals`;

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete
	on `Acme-Meals`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
	 on `Acme-Meals`.* to 'acme-manager'@'%';


-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Meals
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
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_cgls5lrufx91ufsyh467spwa3` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
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
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`),
  CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (96,0,'C/Sagasta nº23','manuto@gmail.com','Manuel','+34 965845789','Torres',84);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
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
  `manager_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3gkj79d7pfn63gjakb3r35j81` (`manager_id`),
  CONSTRAINT `FK_3gkj79d7pfn63gjakb3r35j81` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (112,0,'Pescado',101),(113,0,'Carne',101),(114,0,'Comida China',101),(115,0,'Comida Japonesa',102),(116,0,'Comida Rapida',102),(117,0,'Comida Vegana',103);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
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
  `moment` datetime DEFAULT NULL,
  `stars` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `restaurant_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_popwa81fkqoy50k6xv69qcgr` (`restaurant_id`),
  KEY `FK_jhvt6d9ap8gxv67ftrmshdfhj` (`user_id`),
  CONSTRAINT `FK_jhvt6d9ap8gxv67ftrmshdfhj` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_popwa81fkqoy50k6xv69qcgr` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (140,0,'2017-04-05 15:00:00',4,'Muy buen restaurante, tal y como me esperaba. El pescaito en su punto','Comentario del pescadito',106,97),(141,0,'2017-05-06 15:00:00',5,'Me encanta el aroma de la playa cuando fui a recoger mi pescadito frito','Comentario del pescadito',106,98),(142,0,'2017-03-05 15:00:00',3,'La comida estaba un poco cruda, pero aun asi estaba buena.','Comentario del brasas',107,97),(143,0,'2017-03-25 15:00:00',5,'Creo que ha sido una de mis mejoras experiencias en restaurantes de este tipo','Comentario del pescadito',107,98),(144,0,'2017-02-15 15:00:00',4,'Comida decente, quizas vuelva a pedir comia otro dia','Comentario del chino',108,99),(145,0,'2017-04-06 15:00:00',1,'Menuda basura de comida','Comentario basura',108,100);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
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
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `companyName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rq8eilj98ork1yxlo7xggp5c8` (`userAccount_id`),
  CONSTRAINT `FK_rq8eilj98ork1yxlo7xggp5c8` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `critic`
--

LOCK TABLES `critic` WRITE;
/*!40000 ALTER TABLE `critic` DISABLE KEYS */;
INSERT INTO `critic` VALUES (104,0,'C/Alcala nº6','Sazn@gmail.com','Salocin','+34 654699885','Znerol',92,'GastroCriticas SL'),(105,0,NULL,'maatunes@gmail.com','Marta',NULL,'Atunes',93,'Desafio Comida SL');
/*!40000 ALTER TABLE `critic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee`
--

DROP TABLE IF EXISTS `fee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fee` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `value` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee`
--

LOCK TABLES `fee` WRITE;
/*!40000 ALTER TABLE `fee` DISABLE KEYS */;
INSERT INTO `fee` VALUES (94,0,1);
/*!40000 ALTER TABLE `fee` ENABLE KEYS */;
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
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `vatNumber` varchar(255) DEFAULT NULL,
  `mealOrder_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rg5va6g1ewnl4ov2tihxxs27m` (`mealOrder_id`),
  CONSTRAINT `FK_rg5va6g1ewnl4ov2tihxxs27m` FOREIGN KEY (`mealOrder_id`) REFERENCES `mealorder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (166,0,'Se ha realizado tu pedido con la tarjeta ************5756','2017-04-25 18:30:00','Irene','Montoya','ES-78245688',157);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvv` int(11) NOT NULL,
  `expirationMonth` int(11) NOT NULL,
  `expirationYear` int(11) NOT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_84bmmxlq61tiaoc7dy7kdcghh` (`userAccount_id`),
  CONSTRAINT `FK_84bmmxlq61tiaoc7dy7kdcghh` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (101,0,'C/Pedro Jimenez nº2','Luci@gmail.com','Lucia','+34 922125654','Aire',89,'Visa',580,10,2019,'Lucia Aire','4079978752719950'),(102,0,NULL,'noAE@gmail.com','Nomis','+34 623545987','Aege',90,'MasterCard',998,12,2017,'Nomis Aege','5835894060330673'),(103,0,NULL,'neSO@gmail.com','Nebur',NULL,'Sotneirrab',91,'Discover',582,1,2019,'Nebur Sotneirrab','6011330960939283');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal`
--

DROP TABLE IF EXISTS `meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meal` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `erased` bit(1) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  `restaurant_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7xhbdhjxe386aejqcsg72tk2r` (`category_id`),
  KEY `FK_bokwd7n7vxdlf3ssh9i55i61a` (`restaurant_id`),
  CONSTRAINT `FK_bokwd7n7vxdlf3ssh9i55i61a` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`),
  CONSTRAINT `FK_7xhbdhjxe386aejqcsg72tk2r` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal`
--

LOCK TABLES `meal` WRITE;
/*!40000 ALTER TABLE `meal` DISABLE KEYS */;
INSERT INTO `meal` VALUES (151,0,'Surtido de gran variedad de los mejores y mas frescos pescaitos fritos.','\0',25.5,'Surtido variado',112,106),(152,0,'Estupenda dorada a la plancha acompañada con ensalada de col.','\0',17.5,'Dorada a la plancha',112,106),(153,0,'Filete de secreto ibérico en su punto con patatas.','\0',9.5,'Secreto ibérico',113,107),(154,0,'En este variado encontraras un total de30 piezas de sushi entre maki, uramaki, nigiri y oshi.','\0',30,'Sushi variado',115,109),(155,0,'Estupendo bocadillo de albondigas en salsa, ¿cúal salsa? ¡La que tú quieras!.','\0',7.5,'Bocadillo de albondigas',116,110),(156,0,'Fideos calentitos en todas las epocas del año.','\0',8,'Ramen',114,108);
/*!40000 ALTER TABLE `meal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mealorder`
--

DROP TABLE IF EXISTS `mealorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mealorder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `amount` double DEFAULT NULL,
  `deliveryAddress` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `pickUp` bit(1) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `restaurant_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dxergks7tq38pj9i2tgsk1smk` (`restaurant_id`),
  KEY `FK_m9i8k0s9sbq2qkg0pugpsoyee` (`user_id`),
  CONSTRAINT `FK_m9i8k0s9sbq2qkg0pugpsoyee` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_dxergks7tq38pj9i2tgsk1smk` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mealorder`
--

LOCK TABLES `mealorder` WRITE;
/*!40000 ALTER TABLE `mealorder` DISABLE KEYS */;
INSERT INTO `mealorder` VALUES (157,0,9.5,'C/ Sevilla nº19','2017-04-25 00:00:00','\0','PENDING',107,97),(158,0,43,NULL,'2017-05-12 00:00:00','','DRAFT',106,97),(159,0,14.5,NULL,'2017-04-26 00:00:00','','FINISHED',107,97),(160,0,42,'C/ Cordoba nº19','2017-04-26 00:00:00','\0','FINISHED',108,98);
/*!40000 ALTER TABLE `mealorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monthlybill`
--

DROP TABLE IF EXISTS `monthlybill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monthlybill` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `cost` double DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `paidMoment` datetime DEFAULT NULL,
  `manager_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_37n7imsk3bgursvaly1k8ocwf` (`manager_id`),
  CONSTRAINT `FK_37n7imsk3bgursvaly1k8ocwf` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monthlybill`
--

LOCK TABLES `monthlybill` WRITE;
/*!40000 ALTER TABLE `monthlybill` DISABLE KEYS */;
INSERT INTO `monthlybill` VALUES (137,0,20,'2017-05-05 15:00:00','2017-05-08 15:00:00',101),(138,0,50,'2017-04-05 15:00:00','2017-04-08 15:00:00',102),(139,0,25,'2017-03-05 15:00:00','2017-03-08 15:00:00',103);
/*!40000 ALTER TABLE `monthlybill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promote`
--

DROP TABLE IF EXISTS `promote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promote` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `beginning` date DEFAULT NULL,
  `ending` date DEFAULT NULL,
  `timesDisplayed` int(11) DEFAULT NULL,
  `totalDisplayed` int(11) DEFAULT NULL,
  `restaurant_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7ybspcq0txr1bbjgd36g3dmsk` (`restaurant_id`),
  CONSTRAINT `FK_7ybspcq0txr1bbjgd36g3dmsk` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promote`
--

LOCK TABLES `promote` WRITE;
/*!40000 ALTER TABLE `promote` DISABLE KEYS */;
INSERT INTO `promote` VALUES (148,0,'2016-02-25','2017-02-25',0,100,106),(149,0,'2017-04-30','2017-12-25',10,10,106),(150,0,'2017-04-25','2017-09-25',0,0,108);
/*!40000 ALTER TABLE `promote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quantity`
--

DROP TABLE IF EXISTS `quantity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quantity` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `meal_id` int(11) NOT NULL,
  `mealOrder_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8rwwuedvf6nyrnq0sc4ryxv4e` (`meal_id`),
  KEY `FK_3ekweusifrfiy8rqitjrrgnf3` (`mealOrder_id`),
  CONSTRAINT `FK_3ekweusifrfiy8rqitjrrgnf3` FOREIGN KEY (`mealOrder_id`) REFERENCES `mealorder` (`id`),
  CONSTRAINT `FK_8rwwuedvf6nyrnq0sc4ryxv4e` FOREIGN KEY (`meal_id`) REFERENCES `meal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quantity`
--

LOCK TABLES `quantity` WRITE;
/*!40000 ALTER TABLE `quantity` DISABLE KEYS */;
INSERT INTO `quantity` VALUES (161,0,1,153,157),(162,0,1,151,158),(163,0,1,152,158),(164,0,1,153,159),(165,0,2,154,160);
/*!40000 ALTER TABLE `quantity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relationdislike`
--

DROP TABLE IF EXISTS `relationdislike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relationdislike` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `review_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5qye6lb1812abflo2lk6xopld` (`review_id`),
  KEY `FK_c4cih26l12dnjoxtb39jy9btw` (`user_id`),
  CONSTRAINT `FK_c4cih26l12dnjoxtb39jy9btw` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_5qye6lb1812abflo2lk6xopld` FOREIGN KEY (`review_id`) REFERENCES `review` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relationdislike`
--

LOCK TABLES `relationdislike` WRITE;
/*!40000 ALTER TABLE `relationdislike` DISABLE KEYS */;
INSERT INTO `relationdislike` VALUES (131,0,123,97),(132,0,124,97),(133,0,122,98),(134,0,124,98),(135,0,124,99),(136,0,124,100);
/*!40000 ALTER TABLE `relationdislike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relationlike`
--

DROP TABLE IF EXISTS `relationlike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relationlike` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `review_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mxlou0cr09bx4nkawja4upoet` (`review_id`),
  KEY `FK_aw84qagp9dks4x568bugpiwux` (`user_id`),
  CONSTRAINT `FK_aw84qagp9dks4x568bugpiwux` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_mxlou0cr09bx4nkawja4upoet` FOREIGN KEY (`review_id`) REFERENCES `review` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relationlike`
--

LOCK TABLES `relationlike` WRITE;
/*!40000 ALTER TABLE `relationlike` DISABLE KEYS */;
INSERT INTO `relationlike` VALUES (125,0,121,97),(126,0,121,98),(127,0,121,99),(128,0,121,100),(129,0,122,97),(130,0,123,98);
/*!40000 ALTER TABLE `relationlike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `comment_id` int(11) NOT NULL,
  `reporter_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_619hbq1axno55kwvmf4rvw4rr` (`comment_id`),
  CONSTRAINT `FK_619hbq1axno55kwvmf4rvw4rr` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (146,0,'2017-04-07 15:00:00','Este comentario me parece inadecuado',145,97),(147,0,'2017-04-08 15:00:00','Este comentario me parece incorrecto',145,98);
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reporter`
--

DROP TABLE IF EXISTS `reporter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reporter` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvv` int(11) NOT NULL,
  `expirationMonth` int(11) NOT NULL,
  `expirationYear` int(11) NOT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_t0u5ulcrr22aump7f2f8j3abu` (`userAccount_id`),
  CONSTRAINT `FK_t0u5ulcrr22aump7f2f8j3abu` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reporter`
--

LOCK TABLES `reporter` WRITE;
/*!40000 ALTER TABLE `reporter` DISABLE KEYS */;
/*!40000 ALTER TABLE `reporter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `avgStars` double DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `costDelivery` double DEFAULT NULL,
  `deliveryService` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `erased` bit(1) DEFAULT NULL,
  `minimunAmount` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `manager_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9yotincsn1q3p91w0v3apsutx` (`name`,`address`,`city`,`manager_id`),
  KEY `FK_sufs79drcf0bq9xeu8mak51lr` (`manager_id`),
  CONSTRAINT `FK_sufs79drcf0bq9xeu8mak51lr` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES (106,0,'C/alcala nº6',4.5,'Cadiz',NULL,'\0','ElPesca1@gmail.com','\0',NULL,'El pescaito','+34 956123456','http://www.elpescaito.es/wp-content/uploads/2014/07/restaurante-andaluz-duque-de-sexto.jpg',101),(107,0,'C/Sagasta nº25',3.6,'Sevilla',2,'','Brasitas25@gmail.com','\0',15,'La brasa al punto','+34 688546231','https://pbs.twimg.com/profile_images/672480932138479616/0KfinFSQ.jpg',101),(108,0,'C/Alhambra nº2',2.3,'Granada',1,'','elchinofeliz@gmail.com','\0',10,'El panteon feliz','+34 956123456','http://www.emtstatic.com/2010/04/panteonfeliz.jpg',101),(109,0,'C/Aventura nº12',0,'Sevilla',NULL,'\0','makimaki@gmail.com','\0',NULL,'Japones Maki','+34 655474849','https://madridevoltion.files.wordpress.com/2012/06/maki-restaurante-japones-malasac3b1a.jpg',102),(110,0,'C/Sol nº42',0,'Malaga',3,'','waysub@gmail.com','\0',10,'SubWay','+34 905666321','http://ciplo.net/noticias/wp-content/uploads/2013/06/subway-restaurante.jpg',102),(111,0,'C/Arbol nº20',0,'Sevilla',NULL,'\0','vergelveggie@gmail.com','\0',NULL,'El Vergel','+34 905212321','http://s3.eestatic.com/2017/03/30/social/Acoso-Dieta_vegana-Restaurantes-La_Jungla_204740027_31771887_1024x576.jpg',103);
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
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
  `rate` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `critic_id` int(11) NOT NULL,
  `restaurant_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s9mb0jivt9dtxhtw0ho45gdgo` (`critic_id`),
  KEY `FK_iiqsrsepjjpp446drup1ur9uf` (`restaurant_id`),
  CONSTRAINT `FK_iiqsrsepjjpp446drup1ur9uf` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`),
  CONSTRAINT `FK_s9mb0jivt9dtxhtw0ho45gdgo` FOREIGN KEY (`critic_id`) REFERENCES `critic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (121,0,4,'Muy lejos, más allá de las montañas de palabras.','Critica al Pescadito',104,106),(122,0,5,'Muy lejos, más allá de las montañas de palabras, alejados de los países de las vocales y las consonantes.','Critica al Pescadito Gaditano',105,106),(123,0,3,'Muy lejos, más allá de las montañas.','Critica al restaurante de brasas',104,107),(124,0,2,'Muy lejos, más allá.','Critica al restaurante chino',105,108);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socialidentity`
--

DROP TABLE IF EXISTS `socialidentity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socialidentity` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `socialNetwork` varchar(255) DEFAULT NULL,
  `restaurant_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lwnyd54vgjlrg26p8c81d47ct` (`restaurant_id`),
  CONSTRAINT `FK_lwnyd54vgjlrg26p8c81d47ct` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socialidentity`
--

LOCK TABLES `socialidentity` WRITE;
/*!40000 ALTER TABLE `socialidentity` DISABLE KEYS */;
INSERT INTO `socialidentity` VALUES (118,0,'http://www.Twitter.com/PescaditoGaditano','PescaitoGaditano','https://pbs.twimg.com/profile_images/846376697020637184/VZF-4g8F.jpg','Twitter',106),(119,0,'http://www.facebook.com/PescaditoGaditano','PescaitoGadiFace','https://pbs.twimg.com/profile_images/846376697020637184/VZF-4g8F.jpg','Facebook',106),(120,0,'http://www.twitter.com/BrasasEnTwitter','BrasasEnTwitter','https://pbs.twimg.com/profile_images/344513261576185938/64a7e31fd8a24c1d74b6f5036749a55a.jpeg','Twitter',107);
/*!40000 ALTER TABLE `socialidentity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvv` int(11) NOT NULL,
  `expirationMonth` int(11) NOT NULL,
  `expirationYear` int(11) NOT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `banned` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o6s94d43co03sx067ili5760c` (`userAccount_id`),
  CONSTRAINT `FK_o6s94d43co03sx067ili5760c` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (97,0,NULL,'PCasto@gmail.com','Pedro','+34 672141455','Casto',85,'MasterCard',220,8,2020,'Irene Garcia Vazquez','5237494243102802','\0'),(98,0,'C/Barco Perdido nº42','MariaBAV@gmail.com','Maria',NULL,'Bienaventurada',86,'Visa',666,4,2019,'Pedro Almodovar','4735179919208401','\0'),(99,0,NULL,'IgnPal@gmail.com','Ignacio',NULL,'Palomo',87,'MasterCard',232,9,2020,'Montse Caballet','5901769379901779','\0'),(100,0,'C/Andalucía nº80','Valeste@gmail.com','Vanesa','+34 965922789','Celeste',88,'Amex',456,11,2021,'Chunk Corris','371773256613913','\0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
INSERT INTO `useraccount` VALUES (84,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(85,0,'24c9e15e52afc47c225b757e7bee1f9d','user1'),(86,0,'7e58d63b60197ceb55a1c487989a3720','user2'),(87,0,'92877af70a45fd6a2ed7fe81e1236b78','user3'),(88,0,'3f02ebe3d7929b091e3d8ccfde2f3bc6','user4'),(89,0,'c240642ddef994358c96da82c0361a58','manager1'),(90,0,'8df5127cd164b5bc2d2b78410a7eea0c','manager2'),(91,0,'2d3a5db4a2a9717b43698520a8de57d0','manager3'),(92,0,'165d185050bab438668aef6faeee3be5','critic1'),(93,0,'88fc227c1f12e1f11b8b1584991c88fe','critic2');
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
INSERT INTO `useraccount_authorities` VALUES (84,'ADMIN'),(85,'USER'),(86,'USER'),(87,'USER'),(88,'USER'),(89,'MANAGER'),(90,'MANAGER'),(91,'MANAGER'),(92,'CRITIC'),(93,'CRITIC');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vatnumber`
--

DROP TABLE IF EXISTS `vatnumber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vatnumber` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vatnumber`
--

LOCK TABLES `vatnumber` WRITE;
/*!40000 ALTER TABLE `vatnumber` DISABLE KEYS */;
INSERT INTO `vatnumber` VALUES (95,0,'ES-78245688');
/*!40000 ALTER TABLE `vatnumber` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-04 19:00:02

commit;
