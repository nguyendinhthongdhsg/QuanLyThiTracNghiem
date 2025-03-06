-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlythitracnghiem
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answers` (
  `awID` int NOT NULL,
  `qID` int NOT NULL,
  `awContent` text NOT NULL,
  `awPictures` text,
  `isRight` tinyint NOT NULL,
  `awStatus` tinyint NOT NULL,
  PRIMARY KEY (`qID`,`awID`),
  KEY `qID_idx` (`qID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Các lựa chọn của câu hỏi';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (1,4,'10',NULL,1,1),(2,4,'15',NULL,0,1),(3,4,'',NULL,0,1),(1,16,'bắc',NULL,0,0),(2,16,'trung',NULL,1,0),(3,16,'nam',NULL,0,0),(1,17,'lớp cá',NULL,1,0),(2,17,'lớp thú',NULL,0,0),(1,18,'không biết',NULL,0,0),(2,18,'chào thua',NULL,0,0),(3,18,'các đáp án trên',NULL,1,0),(1,50,'Sử dụng JDBC',NULL,1,1),(2,50,'Sử dụng ODBC',NULL,0,1),(3,50,'Sử dụng Hibernate',NULL,0,1),(4,50,'Sử dụng JPA',NULL,0,1),(1,51,'Một ngôn ngữ lập trình hướng đối tượng',NULL,1,1),(2,51,'Một hệ điều hành',NULL,0,1),(3,51,'Một trình duyệt web',NULL,0,1),(4,51,'Một cơ sở dữ liệu',NULL,0,1),(1,52,'`==` so sánh tham chiếu, `equals()` so sánh giá trị',NULL,1,1),(2,52,'`==` và `equals()` đều so sánh giá trị',NULL,0,1),(3,52,'`==` so sánh giá trị, `equals()` so sánh tham chiếu',NULL,0,1),(4,52,'`==` so sánh kiểu dữ liệu, `equals()` so sánh đối tượng',NULL,0,1),(1,53,'SELECT * FROM bảng',NULL,1,1),(2,53,'SELECT bảng FROM *',NULL,0,1),(3,53,'SELECT FROM bảng WHERE điều kiện',NULL,0,1),(4,53,'SELECT bảng WHERE điều kiện',NULL,0,1),(1,54,'Tất cả các đối tượng',NULL,1,1),(2,54,'Chỉ các đối tượng của lớp con của `ArrayList`',NULL,0,1),(3,54,'Chỉ các đối tượng số nguyên',NULL,0,1),(4,54,'Chỉ các đối tượng kiểu dữ liệu nguyên thủy',NULL,0,1),(1,55,'new TênLớp()',NULL,1,1),(2,55,'create TênLớp()',NULL,0,1),(3,55,'Tạo đối tượng mà không cần từ khóa',NULL,0,1),(4,55,'class TênLớp()',NULL,0,1),(1,56,'Thông qua Garbage Collection',NULL,1,1),(2,56,'Thông qua việc gọi `delete`',NULL,0,1),(3,56,'Thông qua việc sử dụng con trỏ',NULL,0,1),(4,56,'Thông qua việc tạo nhiều đối tượng',NULL,0,1),(1,57,'`finalize()` được gọi trước khi Garbage Collection, còn `dispose()` được gọi khi hủy JFrame',NULL,1,1),(2,57,'`finalize()` là phương thức dùng để giải phóng bộ nhớ',NULL,0,1),(3,57,'Cả hai phương thức đều có chức năng giống nhau',NULL,0,1),(4,57,'`dispose()` chỉ dùng cho GUI, còn `finalize()` dùng cho mọi đối tượng',NULL,0,1),(1,58,'Sử dụng cấu trúc try-catch',NULL,1,1),(2,58,'Sử dụng cấu trúc if-else',NULL,0,1),(3,58,'Sử dụng cấu trúc switch',NULL,0,1),(4,58,'Sử dụng `throw` để ném ngoại lệ',NULL,0,1),(1,59,'`ArrayList` sử dụng mảng động, `LinkedList` sử dụng danh sách liên kết',NULL,1,1),(2,59,'`ArrayList` và `LinkedList` đều sử dụng danh sách liên kết',NULL,0,1),(3,59,'`ArrayList` chỉ cho phép thêm phần tử vào cuối, còn `LinkedList` cho phép thêm ở bất kỳ đâu',NULL,0,1),(4,59,'`ArrayList` có thời gian truy xuất nhanh hơn, còn `LinkedList` có thời gian thêm/xóa phần tử nhanh hơn',NULL,0,1);
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exams`
--

DROP TABLE IF EXISTS `exams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exams` (
  `testCode` varchar(20) NOT NULL,
  `exOrder` varchar(1) NOT NULL,
  `exCode` varchar(20) NOT NULL,
  `ex_quesIDs` text NOT NULL,
  `examId` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`examId`),
  KEY `testCode_idx` (`testCode`),
  KEY `exCode_idx` (`exCode`),
  CONSTRAINT `fk_exams_test` FOREIGN KEY (`testCode`) REFERENCES `test` (`testCode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Bài thi theo cấu trúc Test';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exams`
--

LOCK TABLES `exams` WRITE;
/*!40000 ALTER TABLE `exams` DISABLE KEYS */;
INSERT INTO `exams` VALUES ('TEST','2','TEST1','50,51,52,53,54,55,56,57,58,59',1),('3','2','TEST2','50,51,52,53,54,55,56,57,58,59',2),('2','2','TEST3','50,51,52,53,54,55,56,57,58,59',3);
/*!40000 ALTER TABLE `exams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logs` (
  `logID` int NOT NULL,
  `logContent` text NOT NULL,
  `logUserID` int NOT NULL,
  `logExID` int NOT NULL,
  `logDate` datetime NOT NULL,
  PRIMARY KEY (`logID`),
  KEY `logUserID_idx` (`logUserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Lưu vết thao tác của người thi';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `qID` int NOT NULL AUTO_INCREMENT,
  `qContent` text NOT NULL,
  `qPictures` varchar(255) DEFAULT NULL,
  `qTopicID` int NOT NULL,
  `qLevel` varchar(10) NOT NULL,
  `qStatus` tinyint NOT NULL,
  PRIMARY KEY (`qID`),
  KEY `qTopicID_idx` (`qTopicID`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Câu hỏi';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (3,'đây là gì','C:\\Users\\Administrator\\Pictures\\nike-shox-tl.png',1,'Dễ',1),(4,'4 +  6 = ?','',1,'Dễ',1),(5,'định lý pytago là gì ?','',1,'Trung bình',1),(9,'đây là gì','C:\\Users\\Administrator\\Pictures\\register.png',1,'Trung bình',1),(10,'đây là gì ?','C:\\Users\\Administrator\\Pictures\\Ảnh1.png',1,'Trung bình',1),(11,'thúy kiều là nhân vật của tác phẩm văn học nào ?','C:\\Users\\Administrator\\Pictures\\register.png',2,'Trung bình',1),(12,'Tô hoài mất năm bao nhiêu ?','C:\\Users\\Administrator\\Pictures\\nike-shox-tl.png',2,'Trung bình',1),(14,'bình thuận nằm vùng nào ?','C:\\Users\\Administrator\\Pictures\\Ảnh1.png',8,'Trung bình',1),(16,'bình thuận nằm vùng nào ?','C:\\Users\\Administrator\\Pictures\\Ảnh1.png',8,'Trung bình',1),(17,'cá thuộc lớp động vật nào ?',NULL,4,'Dễ',1),(18,'trận bạch đằng đánh năm bao nhiêu ?',NULL,9,'Trung bình',1),(50,'Làm thế nào để kết nối Java với MySQL?',NULL,1,'Dễ',1),(51,'Java là gì?',NULL,1,'Trung bình',1),(52,'Sự khác biệt giữa `==` và `equals()` trong Java là gì?',NULL,1,'Khó',1),(53,'Hãy mô tả cách sử dụng câu lệnh SELECT trong SQL.',NULL,2,'Trung bình',1),(54,'Một `ArrayList` trong Java có thể chứa các loại đối tượng nào?',NULL,2,'Dễ',1),(55,'Để tạo một đối tượng trong Java, ta sử dụng cú pháp nào?',NULL,1,'Trung bình',1),(56,'Quản lý bộ nhớ trong Java được thực hiện như thế nào?',NULL,3,'Khó',1),(57,'Hãy giải thích sự khác biệt giữa phương thức `finalize()` và phương thức `dispose()` trong Java.',NULL,3,'Trung bình',1),(58,'Làm thế nào để xử lý ngoại lệ trong Java?',NULL,1,'Dễ',1),(59,'Nêu một số sự khác biệt giữa `ArrayList` và `LinkedList` trong Java?',NULL,2,'Trung bình',1);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result` (
  `rs_num` tinyint NOT NULL,
  `userID` int NOT NULL,
  `exCode` varchar(20) NOT NULL,
  `rs_anwsers` longtext NOT NULL,
  `rs_mark` decimal(10,0) NOT NULL,
  `rs_date` datetime NOT NULL,
  PRIMARY KEY (`rs_num`,`userID`,`exCode`),
  KEY `userID_idx` (`userID`),
  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Kết quả thi của users';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` VALUES (1,1,'TEST1','{\"50\": \"A\", \"51\": \"B\", \"52\": \"C\", \"53\": \"D\", \"54\": \"A\", \"55\": \"B\", \"56\": \"C\", \"57\": \"D\", \"58\": \"A\", \"59\": \"B\"}',8,'2024-02-25 10:30:00'),(2,2,'TEST1','{\"50\": \"C\", \"51\": \"D\", \"52\": \"A\", \"53\": \"B\", \"54\": \"C\", \"55\": \"D\", \"56\": \"A\", \"57\": \"B\", \"58\": \"C\", \"59\": \"D\"}',7,'2024-02-25 11:00:00'),(3,1,'TEST1','{\"50\": \"B\", \"51\": \"A\", \"52\": \"D\", \"53\": \"C\", \"54\": \"B\", \"55\": \"A\", \"56\": \"D\", \"57\": \"C\", \"58\": \"B\", \"59\": \"A\"}',3,'2024-02-25 12:15:00'),(4,2,'TEST1','{\"50\": \"D\", \"51\": \"C\", \"52\": \"B\", \"53\": \"A\", \"54\": \"D\", \"55\": \"C\", \"56\": \"B\", \"57\": \"A\", \"58\": \"D\", \"59\": \"C\"}',6,'2024-02-25 14:00:00'),(5,1,'TEST1','{\"50\": \"A\", \"51\": \"B\", \"52\": \"C\", \"53\": \"D\", \"54\": \"A\", \"55\": \"B\", \"56\": \"C\", \"57\": \"D\", \"58\": \"A\", \"59\": \"B\"}',4,'2024-02-25 15:45:00'),(6,3,'TEST1','{\"50\": \"A\", \"51\": \"B\", \"52\": \"C\", \"53\": \"D\", \"54\": \"A\", \"55\": \"B\", \"56\": \"C\", \"57\": \"D\", \"58\": \"A\", \"59\": \"B\"}',5,'2024-02-25 15:45:00'),(7,2,'TEST2','{\"50\": \"A\", \"51\": \"B\", \"52\": \"C\", \"53\": \"D\", \"54\": \"A\", \"55\": \"B\", \"56\": \"C\", \"57\": \"D\", \"58\": \"A\", \"59\": \"B\"}',6,'2024-02-25 15:45:00'),(8,3,'TEST3','{\"50\": \"A\", \"51\": \"B\", \"52\": \"C\", \"53\": \"D\", \"54\": \"A\", \"55\": \"B\", \"56\": \"C\", \"57\": \"D\", \"58\": \"A\", \"59\": \"B\"}',7,'2024-02-25 15:45:00'),(9,2,'TEST3','{\"50\": \"A\", \"51\": \"B\", \"52\": \"C\", \"53\": \"D\", \"54\": \"A\", \"55\": \"B\", \"56\": \"C\", \"57\": \"D\", \"58\": \"A\", \"59\": \"B\"}',7,'2024-02-25 15:45:00'),(10,3,'TEST2','{\"50\": \"A\", \"51\": \"B\", \"52\": \"C\", \"53\": \"D\", \"54\": \"A\", \"55\": \"B\", \"56\": \"C\", \"57\": \"D\", \"58\": \"A\", \"59\": \"B\"}',7,'2024-02-25 15:45:00');
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test` (
  `testID` int NOT NULL AUTO_INCREMENT,
  `testCode` varchar(20) NOT NULL,
  `testTitle` text NOT NULL,
  `testTime` int NOT NULL,
  `tpID` int NOT NULL,
  `num_easy` int NOT NULL,
  `num_medium` int NOT NULL,
  `num_diff` int NOT NULL,
  `test_limit` tinyint NOT NULL,
  `testDate` date NOT NULL,
  `testStatus` int NOT NULL,
  PRIMARY KEY (`testID`),
  KEY `tpID_idx` (`tpID`),
  KEY `testCode_idx` (`testCode`),
  CONSTRAINT `tpID` FOREIGN KEY (`tpID`) REFERENCES `questions` (`qTopicID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Cấu trúc bài thi';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (1,'1','Toán',20,1,10,5,5,5,'2025-02-15',0),(2,'2','ToánTB',20,2,5,5,10,5,'2025-02-15',0),(3,'3','toán',30,1,10,10,10,15,'2025-02-15',0),(4,'TEST','KIEM TRA',30,1,4,4,2,5,'2025-02-20',1);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topics`
--

DROP TABLE IF EXISTS `topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topics` (
  `tpID` int NOT NULL AUTO_INCREMENT,
  `tpTitle` text NOT NULL,
  `tpParent` int NOT NULL,
  `tpStatus` tinyint NOT NULL,
  PRIMARY KEY (`tpID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Các chủ đề / bài học			';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topics`
--

LOCK TABLES `topics` WRITE;
/*!40000 ALTER TABLE `topics` DISABLE KEYS */;
INSERT INTO `topics` VALUES (1,'Toán',0,1),(2,'Văn',861422,1),(3,'Anh',841623,1),(4,'sinh',312412,1),(5,'lý',861403,1),(7,'hóa',641303,1),(8,'địa',841632,1),(9,'sử',841203,1);
/*!40000 ALTER TABLE `topics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(40) NOT NULL,
  `userEmail` varchar(255) DEFAULT NULL,
  `userPassword` varchar(40) NOT NULL,
  `userFullName` varchar(40) NOT NULL,
  `isAdmin` tinyint NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Người dùng';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'anhhuy2004','nguyenanhhuy2004@gmail.com','20112004','nguyen anh huy',0),(2,'admin','admin@gmail.com','admin','admin',1),(3,'anhthu2004','anhthu1120@gmail.com','11112004','huynh ngoc anh thu',0),(4,'dinhthong2004','dinhthong2004@gmail.com','dinhthong2004','dinhthong2004',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-06 13:35:06
