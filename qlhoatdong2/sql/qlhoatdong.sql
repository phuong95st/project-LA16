-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2017 at 08:42 AM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `qlhoatdong`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_checkin_stu`
--

CREATE TABLE IF NOT EXISTS `tbl_checkin_stu` (
  `id` bigint(20) NOT NULL,
  `sche_stu_id` int(11) DEFAULT NULL,
  `week_id` int(11) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `content` text COLLATE utf8_unicode_ci,
  `other` text COLLATE utf8_unicode_ci
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_checkin_stu`
--

INSERT INTO `tbl_checkin_stu` (`id`, `sche_stu_id`, `week_id`, `status`, `content`, `other`) VALUES
(1, 4, 22, b'0', '', ''),
(2, 4, 23, b'0', '', ''),
(3, 4, 24, b'0', '', ''),
(4, 4, 25, b'0', '', ''),
(5, 4, 26, b'0', '', ''),
(6, 4, 27, b'0', '', ''),
(7, 4, 28, b'0', '', ''),
(8, 4, 29, b'0', '', ''),
(9, 4, 30, b'0', '', ''),
(10, 4, 31, b'0', '', ''),
(11, 4, 32, b'0', '', ''),
(12, 4, 33, b'0', '', ''),
(13, 4, 34, b'1', '<ul><li>Xây dựng giao diện trò chơi</li><li>Thêm chức năng chơi online</li></ul>', '<p>Nội dung báo cáo tuần tới:</p><ul><li>Sửa lại giao diện trò chơi</li><li>Hoàn thiện chức năng chơi online</li></ul>'),
(14, 4, 35, b'0', '', ''),
(15, 4, 36, b'0', '<p>Bỏ môn</p>', '<p><strong>Bỏ môn</strong></p>'),
(16, 4, 37, b'0', '', ''),
(17, 4, 38, b'0', '', ''),
(18, 4, 39, b'0', '', ''),
(19, 4, 40, b'0', '', ''),
(39, 9, 22, b'0', '', ''),
(40, 9, 23, b'0', '', ''),
(41, 9, 24, b'0', '', ''),
(42, 9, 25, b'0', '', ''),
(43, 9, 26, b'0', '', ''),
(44, 9, 27, b'0', '', ''),
(45, 9, 28, b'0', '', ''),
(46, 9, 29, b'0', '', ''),
(47, 9, 30, b'0', '', ''),
(48, 9, 31, b'0', '', ''),
(49, 9, 32, b'0', '', ''),
(50, 9, 33, b'0', '', ''),
(51, 9, 34, b'0', '', ''),
(52, 9, 35, b'0', '', ''),
(53, 9, 36, b'0', '', ''),
(54, 9, 37, b'0', '', ''),
(55, 9, 38, b'0', '', ''),
(56, 9, 39, b'0', '', ''),
(57, 9, 40, b'0', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_hocky`
--

CREATE TABLE IF NOT EXISTS `tbl_hocky` (
  `hoc_ky_id` int(11) NOT NULL,
  `name` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `nam_hoc` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_hocky`
--

INSERT INTO `tbl_hocky` (`hoc_ky_id`, `name`, `nam_hoc`) VALUES
(1, '20162', '2016-2017'),
(2, '20161', '2016-2017'),
(3, '20163', '2016-2017'),
(4, '20171', '2017-2018'),
(5, '20172', '2017-2018'),
(6, '20173', '2017-2018');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_onl`
--

CREATE TABLE IF NOT EXISTS `tbl_onl` (
  `id` bigint(20) NOT NULL,
  `time_start` time DEFAULT NULL,
  `time_end` time DEFAULT NULL,
  `ca_truc` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `week_id` int(11) NOT NULL,
  `date_of_week` int(11) NOT NULL,
  `is_hol` bit(1) NOT NULL,
  `is_late` bit(1) NOT NULL,
  `late_min` int(11) NOT NULL,
  `reason` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_onl`
--

INSERT INTO `tbl_onl` (`id`, `time_start`, `time_end`, `ca_truc`, `user_id`, `week_id`, `date_of_week`, `is_hol`, `is_late`, `late_min`, `reason`) VALUES
(1, '14:00:00', '17:30:00', 2, 1, 34, 3, b'1', b'0', 0, ''),
(2, '08:30:00', '11:00:00', 1, 1, 34, 6, b'0', b'1', 139, 'Tôi có việc riêng'),
(3, '09:30:00', '12:00:00', 1, 1, 35, 2, b'0', b'0', 0, ''),
(4, '08:30:00', '11:30:00', 1, 1, 37, 3, b'1', b'0', 0, ''),
(6, '14:00:00', '17:30:00', 2, 1, 37, 6, b'1', b'0', 0, ''),
(7, '14:00:00', '16:30:00', 2, 3, 37, 4, b'1', b'0', 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_position`
--

CREATE TABLE IF NOT EXISTS `tbl_position` (
  `phong` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_position`
--

INSERT INTO `tbl_position` (`phong`, `latitude`, `longitude`) VALUES
('B1-502', 21.004462907735693, 105.84693078431835),
('D3-101', 21.006010075604635, 105.84853294874512),
('D3-201', 21.006010075604635, 105.84853294874512),
('D3-301', 21.006010075604635, 105.84853294874512),
('D3-401', 21.006010075604635, 105.84853294874512),
('D35-101', 21.005974621378623, 105.8483048957557),
('D35-201', 21.005974621378623, 105.8483048957557),
('D35-301', 21.005974621378623, 105.8483048957557),
('D35-401', 21.005974621378623, 105.8483048957557),
('D5-101', 21.006014568428554, 105.8485311373011),
('D5-102', 21.004970599916767, 105.8450707980486),
('D5-103', 21.00502503686204, 105.84476590397595),
('D5-104', 21.005097375470847, 105.84504330298381),
('D5-105', 21.004970599916767, 105.8450707980486),
('D5-201', 21.006014568428554, 105.8485311373011),
('D5-202', 21.004970599916767, 105.8450707980486),
('D5-203', 21.00502503686204, 105.84476590397595),
('D5-204', 21.005097375470847, 105.84504330298381),
('D5-205', 21.004970599916767, 105.8450707980486),
('D5-301', 21.006014568428554, 105.8485311373011),
('D5-302', 21.004970599916767, 105.8450707980486),
('D5-303', 21.00502503686204, 105.84476590397595),
('D5-304', 21.005097375470847, 105.84504330298381),
('D5-305', 21.004970599916767, 105.8450707980486),
('D9-101', 21.006019199878917, 105.84849842089912),
('D9-102', 21.004970599916767, 105.8450707980486),
('D9-103', 21.00411998750689, 105.84432900400589),
('D9-104', 21.00411049092282, 105.8443281614276),
('D9-105', 21.004123467251183, 105.84433541263559),
('D9-106', 21.004287600536834, 105.84402649673245),
('D9-107', 21.004290077555005, 105.8440285468657),
('D9-201', 21.006019199878917, 105.84849842089912),
('D9-202', 21.004970599916767, 105.8450707980486),
('D9-203', 21.00411998750689, 105.84432900400589),
('D9-204', 21.00411049092282, 105.8443281614276),
('D9-205', 21.004123467251183, 105.84433541263559),
('D9-206', 21.004287600536834, 105.84402649673245),
('D9-207', 21.004290077555005, 105.8440285468657),
('D9-301', 21.006019199878917, 105.84849842089912),
('D9-302', 21.004970599916767, 105.8450707980486),
('D9-303', 21.00411998750689, 105.84432900400589),
('D9-304', 21.00411049092282, 105.8443281614276),
('D9-305', 21.004123467251183, 105.84433541263559),
('D9-306', 21.004287600536834, 105.84402649673245),
('D9-307', 21.004290077555005, 105.8440285468657),
('D9-401', 21.006019199878917, 105.84849842089912),
('D9-402', 21.004970599916767, 105.8450707980486),
('D9-403', 21.00411998750689, 105.84432900400589),
('D9-404', 21.00411049092282, 105.8443281614276),
('D9-405', 21.004123467251183, 105.84433541263559),
('D9-406', 21.004287600536834, 105.84402649673245),
('D9-407', 21.004290077555005, 105.8440285468657),
('T-103', 21.0036173, 105.8488423),
('T-203', 21.0036173, 105.8488423),
('T-303', 21.0036173, 105.8488423),
('T-403', 21.0036173, 105.8488423),
('T-503', 21.0036173, 105.8488423);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sche_stu`
--

CREATE TABLE IF NOT EXISTS `tbl_sche_stu` (
  `id` bigint(20) NOT NULL,
  `time_start` time DEFAULT NULL,
  `time_end` time DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `date_of_week` int(11) NOT NULL,
  `week_start` int(11) NOT NULL,
  `week_end` int(11) NOT NULL,
  `student_name` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `topic` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phong` varchar(15) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_sche_stu`
--

INSERT INTO `tbl_sche_stu` (`id`, `time_start`, `time_end`, `type`, `user_id`, `date_of_week`, `week_start`, `week_end`, `student_name`, `topic`, `phong`) VALUES
(4, '09:00:00', '11:00:00', 1, 1, 3, 22, 40, 'Vũ Hữu Quang', 'Lập trình game trên HĐH Android', 'B1-502'),
(9, '10:30:00', '15:30:00', 4, 1, 2, 22, 40, 'Nguyễn Hữu Phương', 'Xây dựng hệ thống Quản lý hoạt động của nhân viên trong bộ môn KTMT', 'B1-502');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_teach`
--

CREATE TABLE IF NOT EXISTS `tbl_teach` (
  `teach_id` bigint(20) NOT NULL,
  `time_start` time DEFAULT NULL,
  `time_end` time DEFAULT NULL,
  `code_class` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `code_subject` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `phong` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_of_week` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `week_start` int(11) DEFAULT NULL,
  `week_end` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_teach`
--

INSERT INTO `tbl_teach` (`teach_id`, `time_start`, `time_end`, `code_class`, `code_subject`, `name`, `user_id`, `phong`, `date_of_week`, `week_start`, `week_end`) VALUES
(7, '10:05:00', '11:30:00', '546779', 'IT4568', 'Kỹ năng lập báo cáo kỹ thuật', 1, 'T-403', '4', 22, 45),
(8, '06:45:00', '10:05:00', '587678', 'IT4568', 'Thiết kế và xây dựng phần mềm', 1, 'T-403', '5', 22, 45),
(9, '10:45:00', '11:45:00', '567909', 'IT4578', 'vhytujby', 3, 'T-403', '7', 22, 45);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_teach_week`
--

CREATE TABLE IF NOT EXISTS `tbl_teach_week` (
  `id` bigint(20) NOT NULL,
  `teach_id` bigint(20) DEFAULT NULL,
  `week_id` int(11) DEFAULT NULL,
  `is_late` bit(1) DEFAULT NULL,
  `is_hol` bit(1) DEFAULT NULL,
  `late_min` int(11) DEFAULT NULL,
  `reason` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_teach_week`
--

INSERT INTO `tbl_teach_week` (`id`, `teach_id`, `week_id`, `is_late`, `is_hol`, `late_min`, `reason`) VALUES
(16, 7, 22, b'0', b'1', 0, ''),
(17, 7, 23, b'0', b'1', 0, ''),
(18, 7, 24, b'0', b'1', 0, ''),
(19, 7, 25, b'0', b'1', 0, ''),
(20, 7, 26, b'0', b'1', 0, ''),
(21, 7, 27, b'0', b'1', 0, ''),
(22, 7, 28, b'0', b'1', 0, ''),
(23, 7, 29, b'0', b'1', 0, ''),
(24, 7, 30, b'0', b'1', 0, ''),
(25, 7, 31, b'0', b'1', 0, ''),
(26, 7, 32, b'0', b'1', 0, ''),
(27, 7, 33, b'0', b'1', 0, ''),
(28, 7, 34, b'0', b'1', 0, ''),
(29, 7, 35, b'0', b'1', 0, ''),
(30, 7, 36, b'0', b'1', 0, ''),
(31, 7, 37, b'0', b'1', 0, ''),
(32, 7, 38, b'0', b'1', 0, ''),
(33, 7, 39, b'0', b'1', 0, ''),
(34, 7, 40, b'0', b'1', 0, ''),
(35, 7, 41, b'0', b'1', 0, ''),
(36, 7, 42, b'0', b'1', 0, ''),
(37, 7, 43, b'0', b'1', 0, ''),
(38, 7, 44, b'0', b'1', 0, ''),
(39, 7, 45, b'0', b'1', 0, ''),
(40, 8, 22, b'0', b'1', 0, ''),
(41, 8, 23, b'0', b'1', 0, ''),
(42, 8, 24, b'0', b'1', 0, ''),
(43, 8, 25, b'0', b'1', 0, ''),
(44, 8, 26, b'0', b'1', 0, ''),
(45, 8, 27, b'0', b'1', 0, ''),
(46, 8, 28, b'0', b'1', 0, ''),
(47, 8, 29, b'0', b'1', 0, ''),
(48, 8, 30, b'0', b'1', 0, ''),
(49, 8, 31, b'0', b'1', 0, ''),
(50, 8, 32, b'0', b'1', 0, ''),
(51, 8, 33, b'0', b'1', 0, ''),
(52, 8, 34, b'0', b'1', 0, ''),
(53, 8, 35, b'0', b'1', 0, ''),
(54, 8, 36, b'0', b'1', 0, ''),
(55, 8, 37, b'0', b'1', 0, ''),
(56, 8, 38, b'0', b'1', 0, ''),
(57, 8, 39, b'0', b'1', 0, ''),
(58, 8, 40, b'0', b'1', 0, ''),
(59, 8, 41, b'0', b'1', 0, ''),
(60, 8, 42, b'0', b'1', 0, ''),
(61, 8, 43, b'0', b'1', 0, ''),
(62, 8, 44, b'0', b'1', 0, ''),
(63, 8, 45, b'0', b'1', 0, ''),
(64, 9, 22, b'0', b'1', 0, ''),
(65, 9, 23, b'0', b'1', 0, ''),
(66, 9, 24, b'0', b'1', 0, ''),
(67, 9, 25, b'0', b'1', 0, ''),
(68, 9, 26, b'0', b'1', 0, ''),
(69, 9, 27, b'0', b'1', 0, ''),
(70, 9, 28, b'0', b'1', 0, ''),
(71, 9, 29, b'0', b'1', 0, ''),
(72, 9, 30, b'0', b'1', 0, ''),
(73, 9, 31, b'0', b'1', 0, ''),
(74, 9, 32, b'0', b'1', 0, ''),
(75, 9, 33, b'0', b'1', 0, ''),
(76, 9, 34, b'0', b'1', 0, ''),
(77, 9, 35, b'0', b'1', 0, ''),
(78, 9, 36, b'0', b'1', 0, ''),
(79, 9, 37, b'0', b'1', 0, ''),
(80, 9, 38, b'0', b'1', 0, ''),
(81, 9, 39, b'0', b'1', 0, ''),
(82, 9, 40, b'0', b'1', 0, ''),
(83, 9, 41, b'0', b'1', 0, ''),
(84, 9, 42, b'0', b'1', 0, ''),
(85, 9, 43, b'0', b'1', 0, ''),
(86, 9, 44, b'0', b'1', 0, ''),
(87, 9, 45, b'0', b'1', 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_tick`
--

CREATE TABLE IF NOT EXISTS `tbl_tick` (
  `id` int(11) NOT NULL,
  `time_tick` timestamp NULL DEFAULT NULL,
  `is_late` bit(1) DEFAULT NULL,
  `late_min` int(11) DEFAULT NULL,
  `reason` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `action` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE IF NOT EXISTS `tbl_user` (
  `user_id` int(11) NOT NULL,
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
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `birthday` date NOT NULL,
  `sex` bit(1) NOT NULL,
  `que_quan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dan_toc` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `dia_chi_hien_tai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tieu_su_cong_tac` text COLLATE utf8_unicode_ci NOT NULL,
  `cmt` varchar(15) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`user_id`, `email`, `pass`, `phone`, `fax`, `office`, `list_subject`, `research`, `released_engine`, `released_book`, `other`, `role`, `name`, `image`, `title`, `birthday`, `sex`, `que_quan`, `dan_toc`, `dia_chi_hien_tai`, `tieu_su_cong_tac`, `cmt`) VALUES
(1, 'anhbq@soict.hust.edu.vn', 'cd72a9a744f55e4a8d8e9de606f461f9', '0438696125', '', 'Phòng 502 nhà B1', '<p>&ndash; Điện tửe số và vi mạch số<br />&ndash; Kỹ thuật Vi xử lý<br />&ndash; Thiết bị ngoại vi và kỹ thuật ghép nối<br />&ndash; Hệ thống thông tin công nghiệp</p>', '', '', '', '', b'0', ' Bùi Quốc Anh	', 'images/bui-quoc-anh.jpg', 'Giảng viên chính', '1960-06-23', b'1', 'Hà Nội', 'Kinh', 'Số 34, Trường Chinh, Hà Nội', '<p>- 1976-1978: Học tại đại học Tổng hợp</p>', '015465689555'),
(2, 'phuongtran95st@gmail.com', '44e8afc352df1fb30fd76afb18f56c6b', '01665577452', NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'1', 'Nguyễn Hữu Phương', '', '', '1975-10-19', b'1', 'Hoài Đức - Hà Nội', 'Kinh', 'P.834, Tòa HH2C, Chung cư Linh Đàm, Hà Nội', '', '017316655'),
(3, 'haha.tsn@gmail.com', '4ea02a3fe6d0717111421489ba1f4d10', '01667970895', '', 'Phòng 502 nhà B1', '', '', '', '', '', b'0', 'Vũ Phương Thảo', 'images\\thaovu.jpg', 'Giảng viên', '1995-07-08', b'0', 'Thanh Hóa', 'Kinh', 'Cầu Giấy - Hà Nội', '', '123456789');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_week`
--

CREATE TABLE IF NOT EXISTS `tbl_week` (
  `week_id` int(11) NOT NULL,
  `week_count` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `hoc_ky_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_week`
--

INSERT INTO `tbl_week` (`week_id`, `week_count`, `start_date`, `end_date`, `hoc_ky_id`) VALUES
(1, 1, '2016-08-22', '2016-08-28', 2),
(2, 2, '2016-08-29', '2016-09-04', 2),
(3, 3, '2016-09-05', '2016-09-11', 2),
(4, 4, '2016-09-12', '2016-09-18', 2),
(5, 5, '2016-09-19', '2016-09-25', 2),
(6, 6, '2016-09-26', '2016-10-02', 2),
(7, 7, '2016-10-03', '2016-10-09', 2),
(8, 8, '2016-10-10', '2016-10-16', 2),
(9, 9, '2016-10-17', '2016-10-23', 2),
(10, 10, '2016-10-24', '2016-10-30', 2),
(11, 11, '2016-10-31', '2016-11-06', 2),
(12, 12, '2016-11-07', '2016-11-13', 2),
(13, 13, '2016-11-14', '2016-11-20', 2),
(14, 14, '2016-11-21', '2016-11-27', 2),
(15, 15, '2016-11-28', '2016-12-04', 2),
(16, 16, '2016-12-05', '2016-12-11', 2),
(17, 17, '2016-12-12', '2016-12-18', 2),
(18, 18, '2016-12-19', '2016-12-25', 2),
(19, 19, '2016-12-26', '2017-01-01', 2),
(20, 20, '2017-01-02', '2017-01-08', 2),
(21, 21, '2017-01-09', '2017-01-15', 2),
(22, 22, '2017-01-16', '2017-01-22', 1),
(23, 23, '2017-01-23', '2017-01-29', 1),
(24, 24, '2017-01-30', '2017-02-05', 1),
(25, 25, '2017-02-06', '2017-02-12', 1),
(26, 26, '2017-02-13', '2017-02-19', 1),
(27, 27, '2017-02-20', '2017-02-26', 1),
(28, 28, '2017-02-27', '2017-03-05', 1),
(29, 29, '2017-03-06', '2017-03-12', 1),
(30, 30, '2017-03-13', '2017-03-19', 1),
(31, 31, '2017-03-20', '2017-03-26', 1),
(32, 31, '2017-03-27', '2017-04-02', 1),
(33, 33, '2017-04-03', '2017-04-09', 1),
(34, 34, '2017-04-10', '2017-04-16', 1),
(35, 35, '2017-04-17', '2017-04-23', 1),
(36, 36, '2017-04-24', '2017-04-30', 1),
(37, 37, '2017-05-01', '2017-05-07', 1),
(38, 38, '2017-05-08', '2017-05-14', 1),
(39, 39, '2017-05-15', '2017-05-21', 1),
(40, 40, '2017-05-22', '2017-05-28', 1),
(41, 41, '2017-05-29', '2017-06-04', 1),
(42, 42, '2017-06-05', '2017-06-11', 1),
(43, 43, '2017-06-12', '2017-06-18', 1),
(44, 44, '2017-06-19', '2017-06-25', 1),
(45, 45, '2017-06-26', '2017-07-02', 1),
(46, 46, '2017-07-03', '2017-07-09', 3),
(47, 47, '2017-07-10', '2017-07-16', 3),
(48, 48, '2017-07-17', '2017-07-23', 3),
(49, 49, '2017-07-24', '2017-07-30', 3),
(50, 50, '2017-07-31', '2017-08-06', 3),
(51, 1, '2017-08-07', '2017-08-13', 4),
(52, 2, '2017-08-14', '2017-08-20', 4),
(53, 3, '2017-08-21', '2017-08-27', 4),
(54, 4, '2017-08-28', '2017-09-03', 4),
(55, 5, '2017-09-04', '2017-09-10', 4),
(56, 6, '2017-09-11', '2017-09-17', 4),
(57, 7, '2017-09-18', '2017-09-24', 4),
(58, 8, '2017-09-25', '2017-10-01', 4),
(59, 9, '2017-10-02', '2017-10-08', 4),
(60, 10, '2017-10-09', '2017-10-15', 4),
(61, 11, '2017-10-16', '2017-10-22', 4),
(62, 12, '2017-10-23', '2017-10-29', 4),
(63, 13, '2017-10-30', '2017-11-05', 4),
(64, 14, '2017-11-06', '2017-11-12', 4),
(65, 15, '2017-11-13', '2017-11-19', 4),
(66, 16, '2017-11-20', '2017-11-26', 4),
(67, 17, '2017-11-27', '2017-12-03', 4),
(68, 18, '2017-12-04', '2017-12-10', 4),
(69, 19, '2017-12-11', '2017-12-17', 4),
(70, 20, '2017-12-18', '2017-12-24', 4),
(71, 21, '2017-12-25', '2017-12-31', 4),
(72, 22, '2018-01-01', '2018-01-07', 4),
(73, 23, '2018-01-08', '2018-01-14', 5),
(74, 24, '2018-01-15', '2018-01-21', 5),
(75, 25, '2018-01-22', '2018-01-28', 5),
(76, 26, '2018-01-29', '2018-02-04', 5),
(77, 27, '2018-02-05', '2018-02-11', 5),
(78, 28, '2018-02-12', '2018-02-18', 5),
(79, 29, '2018-02-19', '2018-02-25', 5),
(80, 30, '2018-02-26', '2018-03-04', 5),
(81, 31, '2018-03-05', '2018-03-11', 5),
(82, 32, '2018-03-12', '2018-03-18', 5),
(83, 33, '2018-03-19', '2018-03-25', 5),
(84, 34, '2018-03-26', '2018-04-01', 5),
(85, 35, '2018-04-02', '2018-04-08', 5),
(86, 36, '2018-04-09', '2018-04-15', 5),
(87, 37, '2018-04-16', '2018-04-22', 5),
(88, 38, '2018-04-23', '2018-04-29', 5),
(89, 39, '2018-04-30', '2018-05-06', 5),
(90, 40, '2018-05-07', '2018-05-13', 5),
(91, 41, '2018-05-14', '2018-05-20', 5),
(92, 42, '2018-05-21', '2018-05-27', 5),
(93, 43, '2018-05-28', '2018-06-03', 5),
(94, 44, '2018-06-04', '2018-06-10', 5),
(95, 45, '2018-06-11', '2018-06-17', 5),
(96, 46, '2018-06-18', '2018-06-24', 6),
(97, 47, '2018-06-25', '2018-07-01', 6),
(98, 48, '2018-07-02', '2018-07-08', 6),
(99, 49, '2018-07-09', '2018-07-15', 6),
(100, 50, '2018-07-16', '2018-07-22', 6),
(101, 51, '2018-07-23', '2018-07-29', 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_checkin_stu`
--
ALTER TABLE `tbl_checkin_stu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_hocky`
--
ALTER TABLE `tbl_hocky`
  ADD PRIMARY KEY (`hoc_ky_id`);

--
-- Indexes for table `tbl_onl`
--
ALTER TABLE `tbl_onl`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_position`
--
ALTER TABLE `tbl_position`
  ADD PRIMARY KEY (`phong`);

--
-- Indexes for table `tbl_sche_stu`
--
ALTER TABLE `tbl_sche_stu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_teach`
--
ALTER TABLE `tbl_teach`
  ADD PRIMARY KEY (`teach_id`);

--
-- Indexes for table `tbl_teach_week`
--
ALTER TABLE `tbl_teach_week`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_tick`
--
ALTER TABLE `tbl_tick`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `tbl_week`
--
ALTER TABLE `tbl_week`
  ADD PRIMARY KEY (`week_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_checkin_stu`
--
ALTER TABLE `tbl_checkin_stu`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=58;
--
-- AUTO_INCREMENT for table `tbl_hocky`
--
ALTER TABLE `tbl_hocky`
  MODIFY `hoc_ky_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `tbl_onl`
--
ALTER TABLE `tbl_onl`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `tbl_sche_stu`
--
ALTER TABLE `tbl_sche_stu`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `tbl_teach`
--
ALTER TABLE `tbl_teach`
  MODIFY `teach_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `tbl_teach_week`
--
ALTER TABLE `tbl_teach_week`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=88;
--
-- AUTO_INCREMENT for table `tbl_tick`
--
ALTER TABLE `tbl_tick`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `tbl_week`
--
ALTER TABLE `tbl_week`
  MODIFY `week_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=102;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
