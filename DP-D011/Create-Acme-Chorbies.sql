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
INSERT INTO `administrator` VALUES (12,0,'admin@gmail.com','administrator','0000','-',NULL,7);
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
INSERT INTO `banner` VALUES (9,0,'http://www.muypymes.com/wp-content/uploads/2011/09/coche_alquiler.jpg'),(10,0,'http://f.tqn.com/y/thaifood/1/T/i/k/padthai640.jpg'),(11,0,'https://s-media-cache-ak0.pinimg.com/originals/02/f6/21/02f6212576ebafe86058c80e94c12f3c.jpg');
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
INSERT INTO `configuration` VALUES (8,0,2,1,'12:00:00');
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
INSERT INTO `useraccount` VALUES (7,0,'21232f297a57a5a743894a0e4a801fc3','admin');
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
INSERT INTO `useraccount_authorities` VALUES (7,'ADMIN');
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

-- Dump completed on 2017-05-01 20:10:04
commit;