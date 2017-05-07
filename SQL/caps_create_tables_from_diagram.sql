SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

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
