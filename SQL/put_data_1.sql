-- login
INSERT INTO `caps`.`login` (`userID`, `userPassword`, `userRole`) VALUES ('E0041234', 'password', 'student');
INSERT INTO `caps`.`login` (`userID`, `userPassword`, `userRole`) VALUES ('L001', 'password', 'lecturer');
-- lecturer
INSERT INTO `caps`.`lecturer` (`lecturerID`, `firstName`, `secondName`, `phone`, `address`) VALUES ('L001', 'Suria', 'Suria', '0000', 'NUS');
-- student
INSERT INTO `caps`.`student` (`studentID`, `firstName`, `secondName`, `address`, `yearStudy`, `email`) VALUES ('E0041234', 'Stu', 'Dent', 'NUS', '1', 'Stu@u.nus.edu.sg');
-- course
INSERT INTO `caps`.`course` (`courseID`, `lecturerID`, `courseName`, `startDate`, `endDate`, `courseFees`, `courseCredits`, `comments`, `courseSize`) VALUES ('C0001', 'L001', 'OOAD', '2016-01-01', '2016-12-12', '1000', '6', 'OOAD', '100');
INSERT INTO `caps`.`course` (`courseID`, `lecturerID`, `courseName`, `startDate`, `endDate`, `courseFees`, `courseCredits`, `comments`, `courseSize`) VALUES ('C0002', 'L001', 'PROG', '2016-01-01', '2016-12-12', '1000', '8', 'PROG', '50');
INSERT INTO `caps`.`course` (`courseID`, `lecturerID`, `courseName`, `startDate`, `endDate`, `courseFees`, `courseCredits`, `comments`, `courseSize`) VALUES ('C0003', 'L001', 'JAVA', '2016-01-01', '2016-12-12', '1000', '8', 'JAVA', '50');
-- enrollment
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `studentGrade`) VALUES ('C0001', 'E0041234', 'B');
INSERT INTO `caps`.`enrolment` (`course_courseID`, `user_userid`, `studentGrade`) VALUES ('C0003', 'E0041234', 'A');
