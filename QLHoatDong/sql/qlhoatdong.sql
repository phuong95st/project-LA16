-- MySQL dump 10.13  Distrib 5.7.16, for Win64 (x86_64)
--
-- Host: localhost    Database: qlhoatdong
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `tbl_event`
--

DROP TABLE IF EXISTS `tbl_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) DEFAULT NULL,
  `time_start` timestamp NULL DEFAULT NULL,
  `time_end` timestamp NULL DEFAULT NULL,
  `place` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` text COLLATE utf8_unicode_ci,
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `FK_event_type_idx` (`type_id`),
  CONSTRAINT `FK_event_type` FOREIGN KEY (`type_id`) REFERENCES `tbl_type_event` (`type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_event`
--

LOCK TABLES `tbl_event` WRITE;
/*!40000 ALTER TABLE `tbl_event` DISABLE KEYS */;
INSERT INTO `tbl_event` VALUES (1,1,'2017-03-20 00:15:00','2017-03-20 10:00:00','Nhà B1 ĐHBK HN','Tham quan chùa Bái Đính','Du xuân đầu năm 2017');
/*!40000 ALTER TABLE `tbl_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_hol_sche`
--

DROP TABLE IF EXISTS `tbl_hol_sche`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_hol_sche` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `start_date` timestamp NULL DEFAULT NULL,
  `end_date` timestamp NULL DEFAULT NULL,
  `reason` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `phep` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lichnghi_user_idx` (`user_id`),
  CONSTRAINT `FK_lichnghi_user` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_hol_sche`
--

LOCK TABLES `tbl_hol_sche` WRITE;
/*!40000 ALTER TABLE `tbl_hol_sche` DISABLE KEYS */;
INSERT INTO `tbl_hol_sche` VALUES (2,3,'2017-03-07 01:00:00','2017-03-07 10:00:00','Nghỉ không phép',1,'','\0'),(3,3,'2017-03-08 01:00:00','2017-03-08 10:00:00','Nghỉ không phép',1,'','\0');
/*!40000 ALTER TABLE `tbl_hol_sche` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_late`
--

DROP TABLE IF EXISTS `tbl_late`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_late` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `time_login` timestamp NULL DEFAULT NULL,
  `reason` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `range_min` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_late_user_idx` (`user_id`),
  CONSTRAINT `FK_late_user` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_late`
--

LOCK TABLES `tbl_late` WRITE;
/*!40000 ALTER TABLE `tbl_late` DISABLE KEYS */;
INSERT INTO `tbl_late` VALUES (7,'2017-03-10 04:30:27','Nhận bàn giao tài khoản muộn',1,210);
/*!40000 ALTER TABLE `tbl_late` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_meeting`
--

DROP TABLE IF EXISTS `tbl_meeting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_meeting` (
  `meeting_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `count_user` int(11) DEFAULT NULL,
  `time_start` timestamp NULL DEFAULT NULL,
  `time_long` int(11) DEFAULT NULL,
  `content` text COLLATE utf8_unicode_ci,
  `create_name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`meeting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_meeting`
--

LOCK TABLES `tbl_meeting` WRITE;
/*!40000 ALTER TABLE `tbl_meeting` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_meeting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_onl_cal`
--

DROP TABLE IF EXISTS `tbl_onl_cal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_onl_cal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ca_truc` int(11) DEFAULT NULL,
  `time_start` timestamp NULL DEFAULT NULL,
  `time_end` timestamp NULL DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lichtruc_user_idx` (`user_id`),
  CONSTRAINT `FK_lichtruc_user` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_onl_cal`
--

LOCK TABLES `tbl_onl_cal` WRITE;
/*!40000 ALTER TABLE `tbl_onl_cal` DISABLE KEYS */;
INSERT INTO `tbl_onl_cal` VALUES (1,1,'2017-03-09 01:00:00','2017-03-09 05:00:00',1),(2,2,'2017-03-10 05:00:00','2017-03-10 08:00:00',1);
/*!40000 ALTER TABLE `tbl_onl_cal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_she_stu`
--

DROP TABLE IF EXISTS `tbl_she_stu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_she_stu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time_start` timestamp NULL DEFAULT NULL,
  `time_end` timestamp NULL DEFAULT NULL,
  `type_stu` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_stu_user_idx` (`user_id`),
  CONSTRAINT `FK_stu_user` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_she_stu`
--

LOCK TABLES `tbl_she_stu` WRITE;
/*!40000 ALTER TABLE `tbl_she_stu` DISABLE KEYS */;
INSERT INTO `tbl_she_stu` VALUES (1,'2017-03-07 03:30:00','2017-03-07 08:00:00',0,1),(2,'2017-03-09 03:30:00','2017-03-09 08:00:00',3,1);
/*!40000 ALTER TABLE `tbl_she_stu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_tea_cal`
--

DROP TABLE IF EXISTS `tbl_tea_cal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_tea_cal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `time_start` timestamp NULL DEFAULT NULL,
  `time_end` timestamp NULL DEFAULT NULL,
  `week` int(11) DEFAULT NULL,
  `code_class` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `code_subject` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `phong` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lichgiang_user_idx` (`user_id`),
  CONSTRAINT `FK_lichgiang_user` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_tea_cal`
--

LOCK TABLES `tbl_tea_cal` WRITE;
/*!40000 ALTER TABLE `tbl_tea_cal` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_tea_cal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_thamdu`
--

DROP TABLE IF EXISTS `tbl_thamdu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_thamdu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `meeting_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_thamdu_user_idx` (`user_id`),
  KEY `FK_thamdu_hop_idx` (`meeting_id`),
  CONSTRAINT `FK_thamdu_hop` FOREIGN KEY (`meeting_id`) REFERENCES `tbl_meeting` (`meeting_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_thamdu_user` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_thamdu`
--

LOCK TABLES `tbl_thamdu` WRITE;
/*!40000 ALTER TABLE `tbl_thamdu` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_thamdu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_thamgia`
--

DROP TABLE IF EXISTS `tbl_thamgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_thamgia` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `event_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_thamgia_user_idx` (`user_id`),
  KEY `FK_thamgia_event_idx` (`event_id`),
  CONSTRAINT `FK_thamgia_event` FOREIGN KEY (`event_id`) REFERENCES `tbl_event` (`event_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_thamgia_user` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_thamgia`
--

LOCK TABLES `tbl_thamgia` WRITE;
/*!40000 ALTER TABLE `tbl_thamgia` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_thamgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_type_event`
--

DROP TABLE IF EXISTS `tbl_type_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_type_event` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_type_event`
--

LOCK TABLES `tbl_type_event` WRITE;
/*!40000 ALTER TABLE `tbl_type_event` DISABLE KEYS */;
INSERT INTO `tbl_type_event` VALUES (1,'Du lịch');
/*!40000 ALTER TABLE `tbl_type_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` bit(1) DEFAULT NULL,
  `pass` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_login` timestamp NULL DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES (1,'','44e8afc352df1fb30fd76afb18f56c6b','phuongtran95st@gmail.com','01665577452','2017-03-10 04:30:27','Nguyễn Hữu Phương');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-10 18:40:54
