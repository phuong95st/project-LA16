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
-- Table structure for table `tbl_checkin_stu`
--

DROP TABLE IF EXISTS `tbl_checkin_stu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_checkin_stu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `week_id` int(11) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `content` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_checkin_stu`
--

LOCK TABLES `tbl_checkin_stu` WRITE;
/*!40000 ALTER TABLE `tbl_checkin_stu` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_checkin_stu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_onl`
--

DROP TABLE IF EXISTS `tbl_onl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_onl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `time_start` timestamp NULL DEFAULT NULL,
  `time_end` timestamp NULL DEFAULT NULL,
  `ca_truc` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `is_hol` bit(1) DEFAULT NULL,
  `is_late` bit(1) DEFAULT NULL,
  `late_min` int(11) DEFAULT NULL,
  `reason` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_onl`
--

LOCK TABLES `tbl_onl` WRITE;
/*!40000 ALTER TABLE `tbl_onl` DISABLE KEYS */;
INSERT INTO `tbl_onl` VALUES (1,'2017-03-15 07:00:00','2017-03-15 10:30:00',2,1,'','\0',0,NULL),(2,'2017-03-15 01:30:00','2017-03-15 04:00:00',1,1,'','\0',0,'');
/*!40000 ALTER TABLE `tbl_onl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_position`
--

DROP TABLE IF EXISTS `tbl_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_position` (
  `phong` varchar(15) CHARACTER SET utf8 NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  PRIMARY KEY (`phong`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_position`
--

LOCK TABLES `tbl_position` WRITE;
/*!40000 ALTER TABLE `tbl_position` DISABLE KEYS */;
INSERT INTO `tbl_position` VALUES ('T-403',21.00032601,105.8484327);
/*!40000 ALTER TABLE `tbl_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_sche_stu`
--

DROP TABLE IF EXISTS `tbl_sche_stu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_sche_stu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time_start` timestamp NULL DEFAULT NULL,
  `time_end` timestamp NULL DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_sche_stu`
--

LOCK TABLES `tbl_sche_stu` WRITE;
/*!40000 ALTER TABLE `tbl_sche_stu` DISABLE KEYS */;
INSERT INTO `tbl_sche_stu` VALUES (1,'2017-03-15 03:30:00','2017-03-15 08:00:00',1,1),(2,'2017-03-15 03:30:00','2017-03-15 08:00:00',2,1),(3,'2017-03-15 03:30:00','2017-03-15 08:00:00',3,1),(4,'2017-03-15 03:30:00','2017-03-15 08:00:00',4,1);
/*!40000 ALTER TABLE `tbl_sche_stu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_student`
--

DROP TABLE IF EXISTS `tbl_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `topic` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_student`
--

LOCK TABLES `tbl_student` WRITE;
/*!40000 ALTER TABLE `tbl_student` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_teach`
--

DROP TABLE IF EXISTS `tbl_teach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_teach` (
  `teach_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `time_start` timestamp NULL DEFAULT NULL,
  `time_end` timestamp NULL DEFAULT NULL,
  `week_id` int(11) DEFAULT NULL,
  `code_class` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `code_subject` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `is_hol` bit(1) DEFAULT NULL,
  `phong` varchar(15) CHARACTER SET utf8 DEFAULT NULL,
  `is_late` bit(1) DEFAULT NULL,
  `late_min` int(11) DEFAULT NULL,
  `reason` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`teach_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_teach`
--

LOCK TABLES `tbl_teach` WRITE;
/*!40000 ALTER TABLE `tbl_teach` DISABLE KEYS */;
INSERT INTO `tbl_teach` VALUES (1,'2017-03-15 08:00:00','2017-03-15 11:30:00',30,'325643','IT1246','Thiết kế và xây dựng phần mềm',1,'\0','T-403','',193,NULL);
/*!40000 ALTER TABLE `tbl_teach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_tick`
--

DROP TABLE IF EXISTS `tbl_tick`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_tick` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time_tick` timestamp NULL DEFAULT NULL,
  `is_late` bit(1) DEFAULT NULL,
  `late_min` int(11) DEFAULT NULL,
  `reason` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `action` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_tick`
--

LOCK TABLES `tbl_tick` WRITE;
/*!40000 ALTER TABLE `tbl_tick` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_tick` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(55) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pass` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fax` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `office` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `list_subject` text COLLATE utf8_unicode_ci,
  `research` text COLLATE utf8_unicode_ci,
  `released_engine` text COLLATE utf8_unicode_ci,
  `released_book` text COLLATE utf8_unicode_ci,
  `other` text COLLATE utf8_unicode_ci,
  `role` bit(1) DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES (1,'anhbq@soict.hust.edu.vn','44e8afc352df1fb30fd76afb18f56c6b','3869 6125',NULL,'Phòng 502 nhà B1',' -          Điện tử số và vi mạch số',NULL,NULL,NULL,'','\0',' Bùi QUốc Anh	');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_week`
--

DROP TABLE IF EXISTS `tbl_week`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_week` (
  `week_id` int(11) NOT NULL AUTO_INCREMENT,
  `week_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`week_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_week`
--

LOCK TABLES `tbl_week` WRITE;
/*!40000 ALTER TABLE `tbl_week` DISABLE KEYS */;
INSERT INTO `tbl_week` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),(12,12),(13,13),(14,14),(15,15),(16,16),(17,17),(18,18),(19,19),(20,20),(21,21),(22,22),(23,23),(24,24),(25,25),(26,26),(27,27),(28,28),(29,29),(30,30),(31,31),(32,31),(33,33),(34,34),(35,35),(36,36),(37,37),(38,38),(39,39),(40,40);
/*!40000 ALTER TABLE `tbl_week` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-15 18:19:58
