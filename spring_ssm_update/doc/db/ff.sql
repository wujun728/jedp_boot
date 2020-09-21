CREATE DATABASE  IF NOT EXISTS `ff` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ff`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: ff
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
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_Name` varchar(45) DEFAULT NULL,
  `event_number` varchar(45) DEFAULT NULL,
  `event_real_name` varchar(45) DEFAULT NULL,
  `event_address` varchar(45) DEFAULT NULL,
  `event_detail` varchar(45) DEFAULT NULL,
  `eventCreateTime` varchar(45) DEFAULT NULL,
  `eventEndTime` varchar(45) DEFAULT NULL,
  `syned` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  UNIQUE KEY `event_id_UNIQUE` (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='事件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'事件1','01','事件real','wuxi',NULL,'10','20','0'),(2,'事件2','02','事件real','shanghai',NULL,'20','30','0');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fireman`
--

DROP TABLE IF EXISTS `fireman`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fireman` (
  `fireman_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `fact_value` varchar(45) DEFAULT NULL,
  `mac_vaule` varchar(45) DEFAULT NULL,
  `avatar` varchar(45) DEFAULT NULL,
  `team` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`fireman_id`),
  UNIQUE KEY `fireman_id_UNIQUE` (`fireman_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消防员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fireman`
--

LOCK TABLES `fireman` WRITE;
/*!40000 ALTER TABLE `fireman` DISABLE KEYS */;
/*!40000 ALTER TABLE `fireman` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receive_event`
--

DROP TABLE IF EXISTS `receive_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receive_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dev_id` varchar(45) DEFAULT NULL COMMENT '设备id，ip地址',
  `left_air` varchar(45) DEFAULT NULL COMMENT '剩余气量',
  `left_time` varchar(45) DEFAULT NULL COMMENT '剩余时间',
  `create_time` varchar(45) DEFAULT NULL COMMENT '事件产生时间',
  `event_id` varchar(45) DEFAULT NULL COMMENT '事件ID',
  `evacuate_status` tinyint(2) DEFAULT NULL COMMENT '撤退状态:0.默认，3.发送了撤退命令，1.对讲机收到，2.对讲机回应',
  `blue` tinyint(2) DEFAULT NULL COMMENT '蓝牙状态;0默认，2.正常，3断开',
  `power` tinyint(2) DEFAULT NULL COMMENT '低电量报警,0正常，1低，ff初始',
  `hand` tinyint(2) DEFAULT NULL COMMENT '手动报警 0关，1开',
  `ariveMini` int(11) DEFAULT NULL COMMENT '到达任务点耗时，分钟',
  `arriveSec` int(11) DEFAULT NULL COMMENT '到达任务点,秒',
  `arivePa` varchar(45) DEFAULT NULL COMMENT '达到任务点压力',
  `event_type` varchar(45) DEFAULT NULL COMMENT '从什么事件接收到的',
  `end_time` varchar(45) DEFAULT NULL COMMENT '安全回来时间',
  `update_time` varchar(45) DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `receive_event_id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='收到消息事件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receive_event`
--

LOCK TABLES `receive_event` WRITE;
/*!40000 ALTER TABLE `receive_event` DISABLE KEYS */;
INSERT INTO `receive_event` VALUES (1,'11','100','20','10','1',0,0,0,0,20,10,'1000','test','12','24'),(2,'12','100','20','11','1',0,0,0,0,20,10,'1000','test','13','32');
/*!40000 ALTER TABLE `receive_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `team_id` int(11) NOT NULL AUTO_INCREMENT,
  `team_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`team_id`),
  UNIQUE KEY `team_id_UNIQUE` (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-20 17:30:17
