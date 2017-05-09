start transaction;

drop database if exists `Acme-Chorbies`;
create database `Acme-Chorbies`;

use 'Acme-Chorbies';

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';

create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete 
	on `Acme-Chorbies`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Acme-Chorbies`.* to 'acme-manager'@'%';

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: acme-chorbies
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
INSERT INTO `administrator` VALUES (79,0,'manuto@gmail.com','Manuel','+34 965845789','Torres',58);
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
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
INSERT INTO `banner` VALUES (67,0,'https://c1.staticflickr.com/3/2813/33640539192_f498ce27f2.jpg'),(68,0,'https://c1.staticflickr.com/4/3837/33667275001_890d00ddd7.jpg'),(69,0,'https://c1.staticflickr.com/4/3856/33667279331_a4ec3acb2e_b.jpg');
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cachetime`
--

DROP TABLE IF EXISTS `cachetime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cachetime` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cachetime`
--

LOCK TABLES `cachetime` WRITE;
/*!40000 ALTER TABLE `cachetime` DISABLE KEYS */;
INSERT INTO `cachetime` VALUES (70,0,'12:00:00');
/*!40000 ALTER TABLE `cachetime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chirp`
--

DROP TABLE IF EXISTS `chirp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chirp` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `deleteRecipient` bit(1) NOT NULL,
  `deleteSender` bit(1) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `recipient_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_20arsrvo5x8fqqrj7o5tomu6f` (`recipient_id`),
  CONSTRAINT `FK_20arsrvo5x8fqqrj7o5tomu6f` FOREIGN KEY (`recipient_id`) REFERENCES `chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chirp`
--

LOCK TABLES `chirp` WRITE;
/*!40000 ALTER TABLE `chirp` DISABLE KEYS */;
INSERT INTO `chirp` VALUES (98,0,'\0','\0','2017-02-05 17:44:00','subject1','text1',86,85),(99,0,'\0','\0','2017-03-05 17:44:00','subject2','text2',88,85),(100,0,'\0','\0','2017-01-05 17:44:00','subject3','text3',88,86),(101,0,'\0','\0','2017-01-05 10:45:00','subject4','text4',86,85),(102,0,'\0','\0','2017-01-05 10:45:00','subject4','text4',85,90);
/*!40000 ALTER TABLE `chirp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chirp_attachment`
--

DROP TABLE IF EXISTS `chirp_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chirp_attachment` (
  `Chirp_id` int(11) NOT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  KEY `FK_dd8t51u0hqax65l7gq4p6gsn3` (`Chirp_id`),
  CONSTRAINT `FK_dd8t51u0hqax65l7gq4p6gsn3` FOREIGN KEY (`Chirp_id`) REFERENCES `chirp` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chirp_attachment`
--

LOCK TABLES `chirp_attachment` WRITE;
/*!40000 ALTER TABLE `chirp_attachment` DISABLE KEYS */;
INSERT INTO `chirp_attachment` VALUES (98,'http://www.google.es'),(99,'http://www.google.es');
/*!40000 ALTER TABLE `chirp_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chorbi`
--

DROP TABLE IF EXISTS `chorbi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chorbi` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `avgStars` double DEFAULT NULL,
  `banned` bit(1) DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `expirationMonth` int(11) DEFAULT NULL,
  `expirationYear` int(11) DEFAULT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `feeAmount` double DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `genre_id` int(11) NOT NULL,
  `kindRelationship_id` int(11) NOT NULL,
  `searchTemplate_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5louq4cq51fl6wldjwd5ivlw2` (`searchTemplate_id`),
  UNIQUE KEY `UK_qrvmwkp25xc5exr6m3jgaxu4x` (`userAccount_id`),
  KEY `FK_26bw2hbx9ok56nvixdljxo6ga` (`genre_id`),
  KEY `FK_gxmf3h253pkfjjmdju64d6tuo` (`kindRelationship_id`),
  CONSTRAINT `FK_qrvmwkp25xc5exr6m3jgaxu4x` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_26bw2hbx9ok56nvixdljxo6ga` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`),
  CONSTRAINT `FK_5louq4cq51fl6wldjwd5ivlw2` FOREIGN KEY (`searchTemplate_id`) REFERENCES `searchtemplate` (`id`),
  CONSTRAINT `FK_gxmf3h253pkfjjmdju64d6tuo` FOREIGN KEY (`kindRelationship_id`) REFERENCES `kindrelationship` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chorbi`
--

LOCK TABLES `chorbi` WRITE;
/*!40000 ALTER TABLE `chorbi` DISABLE KEYS */;
INSERT INTO `chorbi` VALUES (85,0,'juava@gmail.com','Juan','+34 966845789','Vazquez',59,2,'\0','1995-02-06','Sevilla','España','Sevilla','Andalucia','Visa',580,10,2019,'Juan Vazquez','4079978752719950','description1',5,'http://2.bp.blogspot.com/-H6MLqMZhViM/VD7jpYbzemI/AAAAAAAABN0/C1eyjkI-Y4U/s1600/visitante%2Bmisterioso.jpg',73,76,80),(86,0,'nigriba@gmail.com','Nica','+34 966995789','Griego Barco',60,3,'\0','1992-02-06','Moscu','Rusia','Moscu','Moscu','MasterCard',998,12,2017,'Nica Griego Barco','348336595595090','description2',23,'http://photos1.blogger.com/blogger/1942/3777/1600/Anonimo.jpg',74,77,81),(87,0,'marova@gmail.com','Manolo','+34 967995789','Romero Vazquez',61,0,'\0','1988-02-05','Madrid','España','Madrid','Madrid','Discover',582,1,2019,'Manolo Romero Vazquez','6011330960939283','description3',5,'http://www.aspirehire.co.uk/aspirehire-co-uk/_img/profile.svg',73,78,82),(88,0,'irgava@gmail.com','Irene','+34 968895789','Garcia Vazquez',62,2.5,'\0','1980-02-04','Sevilla','España','Sevilla','Andalucia','MasterCard',220,8,2020,'Irene Garcia Vazquez','5237494243102802','description4',10,'http://www.aspirehire.co.uk/aspirehire-co-uk/_img/profile.svg',74,77,83),(89,0,'pavegra@gmail.com','Paco','+34 967996789','Velmez Gracia',63,1,'\0','1975-08-03','Sevilla','España','Sevilla','Andalucia',NULL,NULL,NULL,NULL,NULL,NULL,'description5',20,'http://www.aspirehire.co.uk/aspirehire-co-uk/_img/profile.svg',73,77,84);
/*!40000 ALTER TABLE `chorbi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `seatsOffered` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `manager_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cm6wrj8rjwiu1ftcjtq0l6wuo` (`manager_id`),
  CONSTRAINT `FK_cm6wrj8rjwiu1ftcjtq0l6wuo` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (93,0,'Evento de cine superchulo organizado por el mejor del cine, Peeeedro','2017-05-20 18:00:00','http://www.eventosregionxv.cl/wp-content/uploads/2015/03/ciclo-de-cine-Almodovar-Arica.jpg',50,'Evento de cine',90),(94,0,'Evento de pelis de risa','2016-10-08 22:30:00','https://www.findelahistoria.com/web/wp-content/uploads/2015/01/banne.bmp',30,'Evento de pelis',90),(95,0,'Karaok3','2017-05-22 18:00:00','http://2.bp.blogspot.com/_W-1r3mo-nXE/TRPCDxIdVPI/AAAAAAAAAXQ/5iWUFbc5jfU/s1600/Karaoke%2BFront-1.jpg',2,'Evento de karaoke',91),(96,0,'Lecciones de canto para que chorbies aprendan juntos y echen unos jajas','2016-11-04 12:30:00','http://caracas.afvenezuela.org/wp-content/uploads/2016/01/ganadores-canta-en-frances.jpg',20,'Evento de lecciones de canto',91),(97,0,'Evento grandioso de chunk, no te arrepentiras ;)','2017-06-22 15:00:00','https://i.ytimg.com/vi/7ZJaa2jOwCo/hqdefault.jpg',999,'Evento de Chunk',92);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
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
  `chorbiValue` double DEFAULT NULL,
  `managerValue` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee`
--

LOCK TABLES `fee` WRITE;
/*!40000 ALTER TABLE `fee` DISABLE KEYS */;
INSERT INTO `fee` VALUES (71,0,1,1);
/*!40000 ALTER TABLE `fee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6eoi7r1ljqg0tgtackcxhpwxg` (`value`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (72,0,'none'),(73,0,'man'),(74,0,'woman');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
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
-- Table structure for table `kindrelationship`
--

DROP TABLE IF EXISTS `kindrelationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kindrelationship` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kindrelationship`
--

LOCK TABLES `kindrelationship` WRITE;
/*!40000 ALTER TABLE `kindrelationship` DISABLE KEYS */;
INSERT INTO `kindrelationship` VALUES (75,0,'none'),(76,0,'activities'),(77,0,'friendship'),(78,0,'love');
/*!40000 ALTER TABLE `kindrelationship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mailer`
--

DROP TABLE IF EXISTS `mailer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mailer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_923h0ll5kypnj0rrd4cpuyjsu` (`userAccount_id`),
  CONSTRAINT `FK_923h0ll5kypnj0rrd4cpuyjsu` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mailer`
--

LOCK TABLES `mailer` WRITE;
/*!40000 ALTER TABLE `mailer` DISABLE KEYS */;
/*!40000 ALTER TABLE `mailer` ENABLE KEYS */;
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
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `company` varchar(255) DEFAULT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `expirationMonth` int(11) DEFAULT NULL,
  `expirationYear` int(11) DEFAULT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `feeAmount` double DEFAULT NULL,
  `vat` varchar(255) DEFAULT NULL,
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
INSERT INTO `manager` VALUES (90,0,'pAlm@gmail.com','Pedro','+34 966122122','Almodovar',64,'Pedro\'s Company','Visa',666,4,2019,'Pedro Almodovar','4735179919208401',20,'ES-78787878'),(91,0,'mCab@gmail.com','Montse','+34 900122122','Caballet',65,'El canto de Montse','MasterCard',232,9,2020,'Montse Caballet','5901769379901779',15,'ES-23232323'),(92,0,'RealNorris@gmail.com','Chunk','+34 666111666','Corris',66,'Club Chunk','Amex',456,11,2021,'Chunk Corris','371773256613913',100,'ES-66611666');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relationevent`
--

DROP TABLE IF EXISTS `relationevent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relationevent` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `chorbi_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ohpraqej6hptfblgnd5h2y1gs` (`chorbi_id`),
  KEY `FK_klyk8gdo4x44w4uiwuh4ee7n` (`event_id`),
  CONSTRAINT `FK_klyk8gdo4x44w4uiwuh4ee7n` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  CONSTRAINT `FK_ohpraqej6hptfblgnd5h2y1gs` FOREIGN KEY (`chorbi_id`) REFERENCES `chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relationevent`
--

LOCK TABLES `relationevent` WRITE;
/*!40000 ALTER TABLE `relationevent` DISABLE KEYS */;
INSERT INTO `relationevent` VALUES (103,0,85,93),(104,0,86,94),(105,0,87,95),(106,0,88,96),(107,0,89,97),(108,0,85,95);
/*!40000 ALTER TABLE `relationevent` ENABLE KEYS */;
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
  `comment` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `stars` int(11) DEFAULT NULL,
  `likeRecipient_id` int(11) NOT NULL,
  `likeSender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ph7kqessbmytcwaagchn7p01a` (`likeRecipient_id`),
  KEY `FK_h3m08tdtc2dbryq7kgm66s3ag` (`likeSender_id`),
  CONSTRAINT `FK_h3m08tdtc2dbryq7kgm66s3ag` FOREIGN KEY (`likeSender_id`) REFERENCES `chorbi` (`id`),
  CONSTRAINT `FK_ph7kqessbmytcwaagchn7p01a` FOREIGN KEY (`likeRecipient_id`) REFERENCES `chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relationlike`
--

LOCK TABLES `relationlike` WRITE;
/*!40000 ALTER TABLE `relationlike` DISABLE KEYS */;
INSERT INTO `relationlike` VALUES (109,0,'Me pareces muy interesante','2017-03-31 12:45:00',3,86,85),(110,0,NULL,'2017-03-31 14:55:00',2,88,85),(111,0,'Me pareces muy interesante','2017-03-30 15:55:00',3,88,86),(112,0,'Tienes mucha carisma','2017-02-16 12:15:00',1,89,87),(113,0,NULL,'2017-03-14 22:13:00',2,85,86),(114,0,'Podriamos conectar :^)','2017-01-16 19:45:00',3,86,88);
/*!40000 ALTER TABLE `relationlike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `searchtemplate`
--

DROP TABLE IF EXISTS `searchtemplate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `searchtemplate` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `lastTimeSearched` datetime DEFAULT NULL,
  `genre_id` int(11) DEFAULT NULL,
  `kindRelationship_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jbll5vcngm57qji2jm39kyvsr` (`genre_id`),
  KEY `FK_j8qcxj55yqmgigifjqvei8yv2` (`kindRelationship_id`),
  CONSTRAINT `FK_j8qcxj55yqmgigifjqvei8yv2` FOREIGN KEY (`kindRelationship_id`) REFERENCES `kindrelationship` (`id`),
  CONSTRAINT `FK_jbll5vcngm57qji2jm39kyvsr` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `searchtemplate`
--

LOCK TABLES `searchtemplate` WRITE;
/*!40000 ALTER TABLE `searchtemplate` DISABLE KEYS */;
INSERT INTO `searchtemplate` VALUES (80,0,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-31 14:55:00',NULL,NULL),(81,0,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-31 14:55:00',NULL,NULL),(82,0,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-31 14:55:00',NULL,NULL),(83,0,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-31 14:55:00',NULL,NULL),(84,0,NULL,NULL,NULL,NULL,NULL,NULL,'2017-03-31 14:55:00',NULL,NULL);
/*!40000 ALTER TABLE `searchtemplate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `searchtemplate_chorbi`
--

DROP TABLE IF EXISTS `searchtemplate_chorbi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `searchtemplate_chorbi` (
  `SearchTemplate_id` int(11) NOT NULL,
  `chorbies_id` int(11) NOT NULL,
  KEY `FK_3t03s45pt13r22kb7y510060k` (`chorbies_id`),
  KEY `FK_8qqojnmpd0r5ur1bo34ymb973` (`SearchTemplate_id`),
  CONSTRAINT `FK_8qqojnmpd0r5ur1bo34ymb973` FOREIGN KEY (`SearchTemplate_id`) REFERENCES `searchtemplate` (`id`),
  CONSTRAINT `FK_3t03s45pt13r22kb7y510060k` FOREIGN KEY (`chorbies_id`) REFERENCES `chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `searchtemplate_chorbi`
--

LOCK TABLES `searchtemplate_chorbi` WRITE;
/*!40000 ALTER TABLE `searchtemplate_chorbi` DISABLE KEYS */;
/*!40000 ALTER TABLE `searchtemplate_chorbi` ENABLE KEYS */;
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
INSERT INTO `useraccount` VALUES (58,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(59,0,'3daa859a294cdefb20a84840240a76f5','chorbi1'),(60,0,'0c8746de81268518ff83490301db8652','chorbi2'),(61,0,'a2da05a88eead7e64593826cafc6a7a7','chorbi3'),(62,0,'a09dd233386632e297a7f4f461989563','chorbi4'),(63,0,'7e062f6f2a4c0f7ec5abacef2917e033','chorbi5'),(64,0,'c240642ddef994358c96da82c0361a58','manager1'),(65,0,'8df5127cd164b5bc2d2b78410a7eea0c','manager2'),(66,0,'2d3a5db4a2a9717b43698520a8de57d0','manager3');
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
INSERT INTO `useraccount_authorities` VALUES (58,'ADMIN'),(59,'CHORBI'),(60,'CHORBI'),(61,'CHORBI'),(62,'CHORBI'),(63,'CHORBI'),(64,'MANAGER'),(65,'MANAGER'),(66,'MANAGER');
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

-- Dump completed on 2017-05-09 11:15:41
