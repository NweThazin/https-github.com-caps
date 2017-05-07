package dao;

import java.sql.SQLException;

import exception.NotFoundException;

public interface AutoGenerateIDDAO {

	public String generateStudentID() throws ClassNotFoundException, SQLException, NotFoundException;
	
	public String generateCourseID() throws ClassNotFoundException, SQLException, NotFoundException;
	
	public String generateLecturerID() throws ClassNotFoundException, SQLException, NotFoundException;
}
