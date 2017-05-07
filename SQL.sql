-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema CAPS
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CAPS
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CAPS` ;
USE `CAPS` ;

-- -----------------------------------------------------
-- Table `CAPS`.`login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CAPS`.`login` (
  `userID` VARCHAR(15) NOT NULL,
  `userPassword` VARCHAR(45) NOT NULL,
  `userRole` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`userID`, `userPassword`, `userRole`));


-- -----------------------------------------------------
-- Table `CAPS`.`lecturer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CAPS`.`lecturer` (
  `lecturerID` VARCHAR(15) NOT NULL,
  `firstName` VARCHAR(45) NULL DEFAULT NULL,
  `secondName` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  PRIMARY KEY (`lecturerID`),
  CONSTRAINT `fk_lecturer_role1`
    FOREIGN KEY (`lecturerID`)
    REFERENCES `CAPS`.`login` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `CAPS`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CAPS`.`student` (
  `studentID` VARCHAR(15) NOT NULL,
  `firstName` VARCHAR(45) NULL DEFAULT NULL,
  `secondName` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `yearStudy` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `cGPA` DOUBLE NULL,
  PRIMARY KEY (`studentID`),
  CONSTRAINT `fk_student_login1`
    FOREIGN KEY (`studentID`)
    REFERENCES `CAPS`.`login` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `CAPS`.`course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CAPS`.`course` (
  `courseID` VARCHAR(15) NOT NULL,
  `lecturerID` VARCHAR(15) NOT NULL,
  `courseName` VARCHAR(45) NULL DEFAULT NULL,
  `startDate` DATE NULL DEFAULT NULL,
  `endDate` DATE NULL DEFAULT NULL,
  `courseFees` DOUBLE NULL DEFAULT NULL,
  `courseCredits` VARCHAR(15) NULL DEFAULT NULL,
  `comments` VARCHAR(100) NULL DEFAULT NULL,
  `courseSize` INT NULL,
  PRIMARY KEY (`courseID`, `lecturerID`),
  INDEX `efk_idx` (`lecturerID` ASC),
  CONSTRAINT `efk1`
    FOREIGN KEY (`lecturerID`)
    REFERENCES `CAPS`.`lecturer` (`lecturerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `CAPS`.`enrolment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CAPS`.`enrolment` (
  `course_courseID` VARCHAR(15) NOT NULL,
  `user_userid` VARCHAR(15) NOT NULL,
  `courseStart` DATE NULL,
  `enrolmentBy` DATETIME NULL,
  `studentGrade` VARCHAR(45) NULL,
  PRIMARY KEY (`course_courseID`, `user_userid`),
  INDEX `fk_course_has_user_user1_idx` (`user_userid` ASC),
  INDEX `fk_course_has_user_course1_idx` (`course_courseID` ASC),
  CONSTRAINT `fk_course_has_user_course1`
    FOREIGN KEY (`course_courseID`)
    REFERENCES `CAPS`.`course` (`courseID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_course_has_user_user1`
    FOREIGN KEY (`user_userid`)
    REFERENCES `CAPS`.`student` (`studentID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;





INSERT INTO `caps`.`login` (`userID`, `userPassword`, `userRole`) VALUES ('A001', 'password', 'admin');
INSERT INTO `caps`.`login` (`userID`, `userPassword`, `userRole`) VALUES ('E001', 'password', 'student');
INSERT INTO `caps`.`login` (`userID`, `userPassword`, `userRole`) VALUES ('E002', 'apple', 'student');
INSERT INTO `caps`.`login` (`userID`, `userPassword`, `userRole`) VALUES ('E003', 'pear', 'student');
INSERT INTO `caps`.`login` (`userID`, `userPassword`, `userRole`) VALUES ('E004', 'orange', 'student');
INSERT INTO `caps`.`login` (`userID`, `userPassword`, `userRole`) VALUES ('E005', 'grape', 'student');
INSERT INTO `caps`.`login` (`userID`, `userPassword`, `userRole`) VALUES ('L001', 'password', 'lecturer');
INSERT INTO `caps`.`login` (`userID`, `userPassword`, `userRole`) VALUES ('L002', 'coffee', 'lecturer');
INSERT INTO `caps`.`login` (`userID`, `userPassword`, `userRole`) VALUES ('L003', 'tea', 'lecturer');


INSERT INTO `caps`.`student` (`studentID`, `firstName`, `secondName`, `address`, `yearStudy`, `email`) VALUES ('E001', 'Cindy', 'Chiam', 'Yishun Street 81', '2', 'E0046672@u.nus.edu');
INSERT INTO `caps`.`student` (`studentID`, `firstName`, `secondName`, `address`, `yearStudy`, `email`) VALUES ('E002', 'Akshay', 'Mohan', 'Clementi Street 41', '2', 'chiams89@gmail.com');
INSERT INTO `caps`.`student` (`studentID`, `firstName`, `secondName`, `address`, `yearStudy`, `email`) VALUES ('E003', 'Imran', 'Careem', 'Bukit Panjang Street 88', '2', 'E0046672@u.nus.edu');
INSERT INTO `caps`.`student` (`studentID`, `firstName`, `secondName`, `address`, `yearStudy`, `email`) VALUES ('E004', 'Marco', 'Hu', 'Clementi Street 77', '1', 'chiams89@gmail.com');
INSERT INTO `caps`.`student` (`studentID`, `firstName`, `secondName`, `address`, `yearStudy`, `email`) VALUES ('E005', 'Nwe', 'Thazin', 'Toa Payoh Street 99', '1', 'E0046672@u.nus.edu');

INSERT INTO `caps`.`lecturer` (`lecturerID`, `firstName`, `secondName`, `phone`, `address`) VALUES ('L001', 'Suria', 'Priya', '62572921', '25 Heng Mui Keng Terrace');
INSERT INTO `caps`.`lecturer` (`lecturerID`, `firstName`, `secondName`, `phone`, `address`) VALUES ('L002', 'Derek', 'Kiong', '67692656', '25 Heng Mui Keng Terrace');
INSERT INTO `caps`.`lecturer` (`lecturerID`, `firstName`, `secondName`, `phone`, `address`) VALUES ('L003', 'Ester', 'Tan', '66251816', '25 Heng Mui Keng Terrace');

INSERT INTO `caps`.`course` (`courseID`, `lecturerID`, `courseName`, `startDate`, `endDate`, `courseFees`, `courseCredits`, `comments`, `courseSize`) VALUES ('OOAD15', 'L001', 'Object Oriented Analysis and Design', '2015-01-02', '2015-06-02', '9000', '6', 'Students will learn fundamentals of Object Oriented Analysis and Design ', '4');
INSERT INTO `caps`.`course` (`courseID`, `lecturerID`, `courseName`, `startDate`, `endDate`, `courseFees`, `courseCredits`, `comments`, `courseSize`) VALUES ('ASPNET15', 'L002', 'ASP.NET Programming', '2015-01-02', '2015-06-02', '10000', '8', 'Students will learn fundamentals of ASP.NET Programming ', '4');
INSERT INTO `caps`.`course` (`courseID`, `lecturerID`, `courseName`, `startDate`, `endDate`, `courseFees`, `courseCredits`, `comments`, `courseSize`) VALUES ('JAVA15', 'L003', 'JAVA Programming', '2015-01-02', '2015-06-02', '11000', '10', 'Students will learn fundamentals of JAVA Programming ', '4');
INSERT INTO `caps`.`course` (`courseID`, `lecturerID`, `courseName`, `startDate`, `endDate`, `courseFees`, `courseCredits`, `comments`, `courseSize`) VALUES ('SWENG15', 'L001', 'Software Engineering', '2015-01-02', '2015-06-02', '12000', '12', 'Students will learn fundamentals of Software Engineering ', '4');
INSERT INTO `caps`.`course` (`courseID`, `lecturerID`, `courseName`, `startDate`, `endDate`, `courseFees`, `courseCredits`, `comments`, `courseSize`) VALUES ('OOAD16', 'L002', 'Object Oriented Analysis and Design', '2016-01-02', '2016-06-02', '9000', '6', 'Students will learn fundamentals of Object Oriented Analysis and Design ', '4');
INSERT INTO `caps`.`course` (`courseID`, `lecturerID`, `courseName`, `startDate`, `endDate`, `courseFees`, `courseCredits`, `comments`, `courseSize`) VALUES ('ASPNET16', 'L003', 'ASP.NET Programming', '2016-01-02', '2016-06-02', '10000', '8', 'Students will learn fundamentals of ASP.NET Programming ', '4');
INSERT INTO `caps`.`course` (`courseID`, `lecturerID`, `courseName`, `startDate`, `endDate`, `courseFees`, `courseCredits`, `comments`, `courseSize`) VALUES ('JAVA16', 'L001', 'JAVA Programming', '2016-01-02', '2016-06-02', '11000', '10', 'Students will learn fundamentals of JAVA Programming ', '4');
INSERT INTO `caps`.`course` (`courseID`, `lecturerID`, `courseName`, `startDate`, `endDate`, `courseFees`, `courseCredits`, `comments`, `courseSize`) VALUES ('SWENG16', 'L002', 'Software Engineering', '2016-01-02', '2016-06-02', '12000', '12', 'Students will learn fundamentals of Software Engineering ', '4');


UPDATE `caps`.`course` SET `courseID`='ASPNET16', `startDate`='2016-01-02', `endDate`='2016-06-02' WHERE `courseID`='ASPNET15' and`lecturerID`='L002';
UPDATE `caps`.`course` SET `courseID`='JAVA16', `startDate`='2016-01-02', `endDate`='2016-06-02' WHERE `courseID`='JAVA15' and`lecturerID`='L003';
UPDATE `caps`.`course` SET `courseID`='OOAD16', `startDate`='2016-01-02', `endDate`='2016-06-02' WHERE `courseID`='OOAD15' and`lecturerID`='L001';
UPDATE `caps`.`course` SET `courseID`='SWENG16', `startDate`='2016-01-02', `endDate`='2016-06-02' WHERE `courseID`='SWENG15' and`lecturerID`='L001';
UPDATE `caps`.`course` SET `courseID`='ASPNET17', `startDate`='2017-01-02', `endDate`='2017-06-02' WHERE `courseID`='ASPNET16' and`lecturerID`='L003';
UPDATE `caps`.`course` SET `courseID`='JAVA17', `startDate`='2017-01-02', `endDate`='2017-06-02' WHERE `courseID`='JAVA16' and`lecturerID`='L001';
UPDATE `caps`.`course` SET `courseID`='OOAD17', `startDate`='2017-01-02', `endDate`='2017-06-02' WHERE `courseID`='OOAD16' and`lecturerID`='L002';
UPDATE `caps`.`course` SET `courseID`='SWENG17', `startDate`='2017-01-02', `endDate`='2017-06-02' WHERE `courseID`='SWENG16' and`lecturerID`='L002';


INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`, `studentGrade`) VALUES ('OOAD16', 'E001', '2016-01-02', '2015-12-02', 'A');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`, `studentGrade`) VALUES ('OOAD16', 'E002', '2016-01-02', '2015-12-02', 'B');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`, `studentGrade`) VALUES ('OOAD16', 'E003', '2016-01-02', '2015-12-02', 'C');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`, `studentGrade`) VALUES ('OOAD16', 'E004', '2016-01-02', '2015-12-02', 'D');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`) VALUES ('OOAD17', 'E001', '2017-01-02', '2016-12-02');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`) VALUES ('OOAD17', 'E002', '2017-01-02', '2016-12-02');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`) VALUES ('OOAD17', 'E003', '2017-01-02', '2016-12-02');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`, `studentGrade`) VALUES ('ASPNET16', 'E001', '2016-01-02', '2015-12-02', 'D');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`, `studentGrade`) VALUES ('ASPNET16', 'E002', '2016-01-02', '2015-12-02', 'C');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`, `studentGrade`) VALUES ('ASPNET16', 'E003', '2016-01-02', '2015-12-02', 'B');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`, `studentGrade`) VALUES ('ASPNET16', 'E004', '2016-01-02', '2015-12-02', 'A');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`) VALUES ('ASPNET17', 'E001', '2017-01-02', '2016-12-02');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`) VALUES ('ASPNET17', 'E002', '2017-01-02', '2016-12-02');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`) VALUES ('ASPNET17', 'E003', '2017-01-02', '2016-12-02');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`, `studentGrade`) VALUES ('JAVA16', 'E001', '2016-01-02', '2015-12-02', 'B');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`, `studentGrade`) VALUES ('JAVA16', 'E002', '2016-01-02', '2015-12-02', 'D');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`, `studentGrade`) VALUES ('JAVA16', 'E003', '2016-01-02', '2015-12-02', 'A');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`, `studentGrade`) VALUES ('JAVA16', 'E004', '2016-01-02', '2015-12-02', 'C');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`) VALUES ('JAVA17', 'E001', '2017-01-02', '2016-12-02');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`) VALUES ('JAVA17', 'E002', '2017-01-02', '2016-12-02');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`) VALUES ('JAVA17', 'E003', '2017-01-02', '2016-12-02');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`, `studentGrade`) VALUES ('SWENG16', 'E001', '2016-01-02', '2015-12-02', 'C');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`, `studentGrade`) VALUES ('SWENG16', 'E002', '2016-01-02', '2015-12-02', 'A');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`, `studentGrade`) VALUES ('SWENG16', 'E003', '2016-01-02', '2015-12-02', 'D');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`, `studentGrade`) VALUES ('SWENG16', 'E004', '2016-01-02', '2015-12-02', 'B');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`) VALUES ('SWENG17', 'E001', '2017-01-02', '2016-12-02');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`) VALUES ('SWENG17', 'E002', '2017-01-02', '2016-12-02');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `courseStart`, `enrolmentBy`) VALUES ('SWENG17', 'E003', '2017-01-02', '2016-12-02');


UPDATE `caps`.`enrolment` SET `studentGrade`='N/A' WHERE `course_courseID`='ASPNET17' and`user_userid`='E001';
UPDATE `caps`.`enrolment` SET `studentGrade`='N/A' WHERE `course_courseID`='ASPNET17' and`user_userid`='E002';
UPDATE `caps`.`enrolment` SET `studentGrade`='N/A' WHERE `course_courseID`='ASPNET17' and`user_userid`='E003';
UPDATE `caps`.`enrolment` SET `studentGrade`='N/A' WHERE `course_courseID`='JAVA17' and`user_userid`='E001';
UPDATE `caps`.`enrolment` SET `studentGrade`='N/A' WHERE `course_courseID`='JAVA17' and`user_userid`='E002';
UPDATE `caps`.`enrolment` SET `studentGrade`='N/A' WHERE `course_courseID`='JAVA17' and`user_userid`='E003';
UPDATE `caps`.`enrolment` SET `studentGrade`='N/A' WHERE `course_courseID`='OOAD17' and`user_userid`='E001';
UPDATE `caps`.`enrolment` SET `studentGrade`='N/A' WHERE `course_courseID`='OOAD17' and`user_userid`='E002';
UPDATE `caps`.`enrolment` SET `studentGrade`='N/A' WHERE `course_courseID`='OOAD17' and`user_userid`='E003';
UPDATE `caps`.`enrolment` SET `studentGrade`='N/A' WHERE `course_courseID`='SWENG17' and`user_userid`='E001';
UPDATE `caps`.`enrolment` SET `studentGrade`='N/A' WHERE `course_courseID`='SWENG17' and`user_userid`='E002';
UPDATE `caps`.`enrolment` SET `studentGrade`='N/A' WHERE `course_courseID`='SWENG17' and`user_userid`='E003';

