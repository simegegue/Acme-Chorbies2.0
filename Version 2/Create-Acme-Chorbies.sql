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
-- Host: localhost    Database: Acme-Chorbies
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
INSERT INTO `administrator` VALUES (269,0,'admin@gmail.com','administrator','0000','-',NULL,245);
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
INSERT INTO `banner` VALUES (257,0,'http://www.muypymes.com/wp-content/uploads/2011/09/coche_alquiler.jpg'),(258,0,'http://f.tqn.com/y/thaifood/1/T/i/k/padthai640.jpg'),(259,0,'https://s-media-cache-ak0.pinimg.com/originals/02/f6/21/02f6212576ebafe86058c80e94c12f3c.jpg');
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
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
  `copy` bit(1) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `sender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_l58p5o8qwiecaote4fmuddbhw` (`copy`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chirp`
--

LOCK TABLES `chirp` WRITE;
/*!40000 ALTER TABLE `chirp` DISABLE KEYS */;
INSERT INTO `chirp` VALUES (279,0,'\0','2017-02-10 00:00:00','Chirigota1','Me gustan los pitisues...',273),(280,0,'\0','2017-02-11 00:00:00','Chirigota1','Y las milhojas de crema...',274),(281,0,'\0','2017-02-12 00:00:00','Chirigota1','Los cuernos y las  canastillas...',273),(282,0,'\0','2017-02-13 00:00:00','Chirigota1','Y las palmeras de yema...',274),(283,0,'\0','2017-02-14 00:00:00','Chirigota1','Esos palitos de nata...',273),(284,0,'\0','2017-02-15 00:00:00','Chirigota1','Me como 10 y no me harto...',274),(285,0,'\0','2017-02-16 00:00:00','Chirigota1','Y esas merengas que tienen...',273),(286,0,'','2017-02-10 00:00:00','Chirigota1','1Me gustan los pitisues...',273),(287,0,'','2017-02-11 00:00:00','Chirigota1','Y las milhojas de crema...',274),(288,0,'','2017-02-12 00:00:00','Chirigota1','Los cuernos y las  canastillas...',273),(289,0,'','2017-02-13 00:00:00','Chirigota1','Y las palmeras de yema...',274),(290,0,'','2017-02-14 00:00:00','Chirigota1','Esos palitos de nata...',273),(291,0,'','2017-02-15 00:00:00','Chirigota1','Me como 10 y no me harto...',274),(292,0,'','2017-02-16 00:00:00','Chirigota1','Y esas merengas que tienen...',273);
/*!40000 ALTER TABLE `chirp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chirp_actor`
--

DROP TABLE IF EXISTS `chirp_actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chirp_actor` (
  `reciveChirps_id` int(11) NOT NULL,
  `recipients_id` int(11) NOT NULL,
  KEY `FK_qxc39wnk6derqxso8s1dcks6g` (`reciveChirps_id`),
  CONSTRAINT `FK_qxc39wnk6derqxso8s1dcks6g` FOREIGN KEY (`reciveChirps_id`) REFERENCES `chirp` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chirp_actor`
--

LOCK TABLES `chirp_actor` WRITE;
/*!40000 ALTER TABLE `chirp_actor` DISABLE KEYS */;
INSERT INTO `chirp_actor` VALUES (279,274),(280,273),(281,274),(282,273),(283,274),(284,273),(285,274),(286,274),(287,273),(288,274),(289,273),(290,274),(291,273),(292,274);
/*!40000 ALTER TABLE `chirp_actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chirp_attachments`
--

DROP TABLE IF EXISTS `chirp_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chirp_attachments` (
  `Chirp_id` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  KEY `FK_19xst1rktyonkumt1r20fe0gh` (`Chirp_id`),
  CONSTRAINT `FK_19xst1rktyonkumt1r20fe0gh` FOREIGN KEY (`Chirp_id`) REFERENCES `chirp` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chirp_attachments`
--

LOCK TABLES `chirp_attachments` WRITE;
/*!40000 ALTER TABLE `chirp_attachments` DISABLE KEYS */;
/*!40000 ALTER TABLE `chirp_attachments` ENABLE KEYS */;
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
  `creditCard_id` int(11) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `birthDate` datetime DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  `searchTemplate_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_ovkyoevssjuf41lisig22x0b8` (`birthDate`),
  KEY `FK_5louq4cq51fl6wldjwd5ivlw2` (`searchTemplate_id`),
  KEY `FK_ooohivehsja6eu3cxe201pe7v` (`creditCard_id`),
  KEY `FK_qrvmwkp25xc5exr6m3jgaxu4x` (`userAccount_id`),
  CONSTRAINT `FK_qrvmwkp25xc5exr6m3jgaxu4x` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_5louq4cq51fl6wldjwd5ivlw2` FOREIGN KEY (`searchTemplate_id`) REFERENCES `searchtemplate` (`id`),
  CONSTRAINT `FK_ooohivehsja6eu3cxe201pe7v` FOREIGN KEY (`creditCard_id`) REFERENCES `creditcard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chorbi`
--

LOCK TABLES `chorbi` WRITE;
/*!40000 ALTER TABLE `chorbi` DISABLE KEYS */;
INSERT INTO `chorbi` VALUES (273,0,'amador@gmail.com','Amador','1234','Rivas',NULL,246,0,'1980-06-10 00:00:00','Madrid','España','','','¡Tengo el salami en oferta!','Man','2017-02-03 12:00:00','https://laqueseavecinafiu.files.wordpress.com/2012/04/105445.jpg','Love',266),(274,0,'maite@gmail.com','Maite','2345','Figueroa',NULL,247,0,'1978-02-17 00:00:00','Madrid','','','','Hola, hola, hola. Soy Maite Figueroa, deja tu mensaje y te llamo en media hora','Woman','2017-02-03 12:00:00','http://s04.s3c.es/imag/_v0/250x200/a/5/e/eva-isanta.jpg','Friendship',NULL),(275,0,'estelaReynolds@gmail.com','Francisca','3456','Pacheco',260,248,2,'1967-11-28 00:00:00','Malaga','','','Andalucia','¡Yo soy Estela Reynolds!, a mí Fernando Esteso me chupó un pezón','Woman','2017-02-03 12:00:00','http://www.formulatv.com/images/noticias/38000/38083/1_db269c5dbd.jpg','Love',268),(276,0,'enriquePastor@gmail.com','Enrique','4567','Pastor',261,249,6,'1957-01-02 00:00:00','Madrid','España','','','Enrique Pastor, concejal de juventud y tiempo libre','Man','2017-02-03 12:00:00','http://3.bp.blogspot.com/-I_M4LJ8RXSs/UWJs45emcUI/AAAAAAAAACw/sfoEJl0w9_w/s1600/enrique2.jpg','Activities',NULL),(277,0,'antonioRecio@gmail.com','Antonio','5678','Recio',262,250,0,'1954-04-08 00:00:00','Dos Hermanas','España','Sevilla','','¡Mayorista, no limpio pescado!','Man','2017-02-03 12:00:00','http://listas.eleconomista.es/system/items/000/012/141/medium/antoniore.jpg?1447028500','Love',NULL),(278,0,'chusa@gmail.com','La chusa','6789','La chusa',NULL,251,0,'1983-06-10 00:00:00','Cádiz','','','Andalucia','¡Te pinsho como no me llames ¿eh?!','Woman','2017-04-02 12:00:00','http://www.namespedia.com/image/Chusa_4.jpg','Activities',267);
/*!40000 ALTER TABLE `chorbi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chorbi_event`
--

DROP TABLE IF EXISTS `chorbi_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chorbi_event` (
  `chorbies_id` int(11) NOT NULL,
  `events_id` int(11) NOT NULL,
  KEY `FK_pqd2lm74tro8qsyrbb6r8my9r` (`events_id`),
  KEY `FK_ixaexsrjsqyebv32wuxeaxb15` (`chorbies_id`),
  CONSTRAINT `FK_ixaexsrjsqyebv32wuxeaxb15` FOREIGN KEY (`chorbies_id`) REFERENCES `chorbi` (`id`),
  CONSTRAINT `FK_pqd2lm74tro8qsyrbb6r8my9r` FOREIGN KEY (`events_id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chorbi_event`
--

LOCK TABLES `chorbi_event` WRITE;
/*!40000 ALTER TABLE `chorbi_event` DISABLE KEYS */;
INSERT INTO `chorbi_event` VALUES (273,297),(273,298),(273,299),(274,297),(275,297),(276,298),(277,297),(277,299);
/*!40000 ALTER TABLE `chorbi_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `feeChorbi` double DEFAULT NULL,
  `feeManager` double DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
INSERT INTO `configuration` VALUES (256,0,2,1,'12:00:00');
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
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
INSERT INTO `creditcard` VALUES (260,0,'VISA',356,8,2019,'Francisca Pacheco','5760651824445570'),(261,0,'MASTERCARD',425,3,2020,'Enrique Pastor','5732718459670965'),(262,0,'DISCOVER',678,8,2030,'Antonio Recio','4539144561950377'),(263,0,'DISCOVER',678,8,2030,'Manager1','4539144561950377'),(264,0,'DISCOVER',678,8,2030,'Manager2','4539144561950377'),(265,0,'DISCOVER',678,8,2030,'Manager3','4539144561950377');
/*!40000 ALTER TABLE `creditcard` ENABLE KEYS */;
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
  `availableSeats` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `seats` int(11) DEFAULT NULL,
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
INSERT INTO `event` VALUES (297,0,95,'No te pierdes esta fantastica fiesta universitaria donde la reina de la noche es una vaca.','2017-05-21 23:00:00','http://www.promicsa.com/uploads/78525eba8a1beed.jpg',100,'Fiesta universitaria',270),(298,0,248,'Este año todos correran con tacones o zancos.','2017-02-25 10:00:00','https://www.clubrunning.es/cartel/cartel_b86cfff512.png',250,'Maraton de Sevilla',270),(299,0,0,'Regalamos una escapada romántica a los dos primeros que acepten.','2017-05-23 09:00:00','https://reservas.zenithoteles.com/Content/images/001/Productos/Prod_27_10_solocontrato.jpg',2,'Escapada romántica',270),(300,0,2,'¡Te invito a un té para que nos conozcamos!','2017-08-10 17:00:00','http://www.quelapaseslindo.com.ar/wp-content/uploads/busco-novia.jpg',2,'Se busca novia',271);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
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
  `creditCard_id` int(11) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `vat` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_giivdifqwawxhepiwjes4redt` (`creditCard_id`),
  KEY `FK_84bmmxlq61tiaoc7dy7kdcghh` (`userAccount_id`),
  CONSTRAINT `FK_84bmmxlq61tiaoc7dy7kdcghh` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_giivdifqwawxhepiwjes4redt` FOREIGN KEY (`creditCard_id`) REFERENCES `creditcard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (270,0,'manager1@gmail.com','manager1','0000','surnameManager1',263,253,0,'Company1','ES-SVQ'),(271,0,'manager2@gmail.com','manager2','0002','surnameManager2',264,254,1,'Company2','EU-SC'),(272,0,'manager3@gmail.com','manager3','0003','surnameManager3',265,255,2,'Company3','UE-NY');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
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
  `genre` varchar(255) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  `updateMoment` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `searchtemplate`
--

LOCK TABLES `searchtemplate` WRITE;
/*!40000 ALTER TABLE `searchtemplate` DISABLE KEYS */;
INSERT INTO `searchtemplate` VALUES (266,0,NULL,'Madrid','España','','','','','Activities','2017-04-04 12:00:00'),(267,0,30,'Madrid','','','','man','rubio','Activities','2017-02-26 14:00:00'),(268,0,24,'Malaga','','','Andalucia','man','dinero','Friendship','2017-02-26 16:00:00');
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
  `results_id` int(11) NOT NULL,
  KEY `FK_l2rnobjnvi4dk30erfk44708j` (`results_id`),
  KEY `FK_8qqojnmpd0r5ur1bo34ymb973` (`SearchTemplate_id`),
  CONSTRAINT `FK_8qqojnmpd0r5ur1bo34ymb973` FOREIGN KEY (`SearchTemplate_id`) REFERENCES `searchtemplate` (`id`),
  CONSTRAINT `FK_l2rnobjnvi4dk30erfk44708j` FOREIGN KEY (`results_id`) REFERENCES `chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `searchtemplate_chorbi`
--

LOCK TABLES `searchtemplate_chorbi` WRITE;
/*!40000 ALTER TABLE `searchtemplate_chorbi` DISABLE KEYS */;
INSERT INTO `searchtemplate_chorbi` VALUES (266,275),(267,273),(267,275);
/*!40000 ALTER TABLE `searchtemplate_chorbi` ENABLE KEYS */;
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
  `comment` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `stars` int(11) DEFAULT NULL,
  `recipient_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g3jwau6808jekg3a2kbjejtuu` (`recipient_id`),
  KEY `FK_3lboc12qeufjac9qg9rlerg8i` (`sender_id`),
  CONSTRAINT `FK_3lboc12qeufjac9qg9rlerg8i` FOREIGN KEY (`sender_id`) REFERENCES `chorbi` (`id`),
  CONSTRAINT `FK_g3jwau6808jekg3a2kbjejtuu` FOREIGN KEY (`recipient_id`) REFERENCES `chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sense`
--

LOCK TABLES `sense` WRITE;
/*!40000 ALTER TABLE `sense` DISABLE KEYS */;
INSERT INTO `sense` VALUES (293,0,NULL,'2017-03-29 12:13:00',2,276,274),(294,0,'¡Merengue, merengue!','2017-04-01 16:37:00',1,275,273),(295,0,'Yo te quiero mucho Cuqui, vuelve conmigo.','2017-04-01 16:45:00',3,274,273),(296,0,NULL,'2017-04-02 10:56:00',2,274,277);
/*!40000 ALTER TABLE `sense` ENABLE KEYS */;
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
INSERT INTO `useraccount` VALUES (245,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(246,0,'3daa859a294cdefb20a84840240a76f5','chorbi1'),(247,0,'0c8746de81268518ff83490301db8652','chorbi2'),(248,0,'a2da05a88eead7e64593826cafc6a7a7','chorbi3'),(249,0,'a09dd233386632e297a7f4f461989563','chorbi4'),(250,0,'7e062f6f2a4c0f7ec5abacef2917e033','chorbi5'),(251,0,'0b41c51bd4b755c5b639498f55058fd3','chorbi6'),(252,0,'1b3231655cebb7a1f783eddf27d254ca','super'),(253,0,'c240642ddef994358c96da82c0361a58','manager1'),(254,0,'8df5127cd164b5bc2d2b78410a7eea0c','manager2'),(255,0,'2d3a5db4a2a9717b43698520a8de57d0','manager3');
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
INSERT INTO `useraccount_authorities` VALUES (245,'ADMIN'),(246,'CHORBI'),(247,'CHORBI'),(248,'CHORBI'),(249,'CHORBI'),(250,'BANNED'),(251,'BANNED'),(252,'ADMIN'),(252,'CHORBI'),(253,'MANAGER'),(254,'MANAGER'),(255,'MANAGER');
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

-- Dump completed on 2017-05-04 22:32:15
commit;