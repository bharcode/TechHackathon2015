-- MySQL dump 10.13  Distrib 5.6.24, for osx10.10 (x86_64)
--
-- Host: localhost    Database: hackathon
-- ------------------------------------------------------
-- Server version	5.6.24

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
-- Table structure for table `apiDetails`
--

CREATE DATABASE IF NOT EXISTS `hackathon`;

use `hackathon`;

DROP TABLE IF EXISTS `apiDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apiDetails` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `jarname` varchar(128) DEFAULT NULL,
  `className` varchar(256) DEFAULT NULL,
  `methodName` varchar(128) DEFAULT NULL,
  `inputParams` text,
  `outputParams` varchar(128) DEFAULT NULL,
  `downloadLink` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apiDetails`
--

LOCK TABLES `apiDetails` WRITE;
/*!40000 ALTER TABLE `apiDetails` DISABLE KEYS */;
INSERT INTO `apiDetails` VALUES (1,'CardAuth.jar','com.americanexpress.gdas.service.NameService','getName','com.americanexpress.gdas.pojo.GdasRequest','com.americanexpress.gdas.pojo.GdasNameResponse','http://bglc02m969efh01:8080/downloadfile/getName-1'),(2,'BasicMath.jar','com.aexp.hackathon.string.Sum','sum','java.lang.String, java.lang.String','java.lang.String','http://bglc02m969efh01:8080/downloadfile/sum-2'),(3,'CardAuth.jar','com.americanexpress.gdas.service.EmailService','getEmail','com.americanexpress.gdas.pojo.GdasRequest','com.americanexpress.gdas.pojo.GdasEmailResponse','http://bglc02m969efh01:8080/downloadfile/getEmail-3'),(4,'BasicMath.jar','com.aexp.hackathon.string.Product','product','java.lang.String, java.lang.String','java.lang.String','http://bglc02m969efh01:8080/downloadfile/product-4'),(15,'gdas-client.jar','com.americanexpress.gdas.service.EmailService','getEmail','com.americanexpress.gdas.pojo.GdasRequest','com.americanexpress.gdas.pojo.GdasEmailResponse','http://bglc02m969efh01:8080/downloadfile/getEmail-15'),(16,'gdas-client.jar','com.americanexpress.gdas.service.NameService','getName','com.americanexpress.gdas.pojo.GdasRequest','com.americanexpress.gdas.pojo.GdasNameResponse','http://bglc02m969efh01:8080/downloadfile/getName-16');
/*!40000 ALTER TABLE `apiDetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-04 15:53:32
