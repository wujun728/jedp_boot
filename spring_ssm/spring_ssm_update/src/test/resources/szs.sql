-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: szs
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
-- Table structure for table `childcol`
--

DROP TABLE IF EXISTS `childcol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `childcol` (
  `ch_id` int(11) NOT NULL AUTO_INCREMENT,
  `childcol_en` varchar(45) DEFAULT NULL,
  `childcol_cn` varchar(45) DEFAULT NULL,
  `spcolumn_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ch_id`),
  UNIQUE KEY `id_UNIQUE` (`ch_id`),
  KEY `ch_sp_idx` (`spcolumn_id`),
  CONSTRAINT `ch_sp` FOREIGN KEY (`spcolumn_id`) REFERENCES `spcolumn` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `childcol`
--

LOCK TABLES `childcol` WRITE;
/*!40000 ALTER TABLE `childcol` DISABLE KEYS */;
INSERT INTO `childcol` VALUES (1,'pastMemory','曾经的记忆',2),(2,'oldPhoto','山梁项老照片',2);
/*!40000 ALTER TABLE `childcol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `content`
--

DROP TABLE IF EXISTS `content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `content` (
  `content_id` int(11) NOT NULL AUTO_INCREMENT,
  `content_title` varchar(45) DEFAULT NULL COMMENT '标题',
  `img_path` varchar(45) DEFAULT NULL COMMENT '图片路径',
  `content_spcol` varchar(45) DEFAULT NULL COMMENT '父路径',
  `content_chcol` varchar(45) DEFAULT NULL COMMENT '子路径',
  `video_path` varchar(45) DEFAULT NULL COMMENT '视频路径',
  PRIMARY KEY (`content_id`),
  UNIQUE KEY `content_id_UNIQUE` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content`
--

LOCK TABLES `content` WRITE;
/*!40000 ALTER TABLE `content` DISABLE KEYS */;
/*!40000 ALTER TABLE `content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spcolumn`
--

DROP TABLE IF EXISTS `spcolumn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spcolumn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `spcolumn_en` varchar(45) DEFAULT NULL,
  `spcolumn_cn` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spcolumn`
--

LOCK TABLES `spcolumn` WRITE;
/*!40000 ALTER TABLE `spcolumn` DISABLE KEYS */;
INSERT INTO `spcolumn` VALUES (1,'artTrain','艺术培训'),(2,'mountainMemory','记忆石嘴山'),(3,'achieve','工业重镇陆港新区成就展'),(4,'mainSchool','五七干校');
/*!40000 ALTER TABLE `spcolumn` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-05 17:51:33
