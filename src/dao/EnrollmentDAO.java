package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import exception.DAOException;
import exception.NotFoundException;
import model.EnrollmentDTO;

public interface EnrollmentDAO {
	
	// create a enrollment, enroll contains student and course objects
	
	public int StudentMapping(EnrollmentDTO enroll) throws DAOException;

	public int CourseMapping(EnrollmentDTO enroll) throws DAOException;
	
	public int ObjectMapping(EnrollmentDTO enroll) throws DAOException;
	
	public int enrollmentCourse(EnrollmentDTO enroll) throws NotFoundException;

	public int updateEnrollment(EnrollmentDTO enroll) throws SQLException, NotFoundException;
	
	public int deleteEnrollment(EnrollmentDTO enroll) throws SQLException, NotFoundException;
	
	// Because Create, Update and Delete works on database with String not Object
	// Only Retrieving do Mapping

	
	public ArrayList<EnrollmentDTO> findAllEnrollment() throws NotFoundException;		// By default, Mapping
	
	public ArrayList<EnrollmentDTO> findEnrollmentByCourse(String courseID) throws NotFoundException;	// By default, Mapping
	
	public ArrayList<EnrollmentDTO> findEnrollmentByStudent(String studentID) throws NotFoundException;		// By default, Mapping
	
	public ArrayList<EnrollmentDTO> findEnrollmentByCriteria(EnrollmentDTO enroll) throws NotFoundException;
	
	public boolean checkCourseSize(EnrollmentDTO enroll);
	
/*	public ArrayList<EnrollmentDTO> findAllEnrollment(boolean Mapping) throws NotFoundException;	// Without Mapping
	
	public ArrayList<EnrollmentDTO> findEnrollmentByCourse(boolean Mapping, String courseID) throws NotFoundException;	// Without Mapping
	
	public ArrayList<EnrollmentDTO> findEnrollmentByStudent(boolean Mapping, String studentID) throws NotFoundException;	// Without Mapping
*/	
}
