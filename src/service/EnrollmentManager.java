package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DAOFactory;
import dao.EnrollmentDAO;
import exception.DAOException;
import exception.NotFoundException;
import model.EnrollmentDTO;

public class EnrollmentManager {

	private EnrollmentDAO enrollDAO;
	
	public EnrollmentManager() {
		enrollDAO = DAOFactory.getEnrollmentDAO();
	}
	
	public int EnrollmentMapping(ArrayList<EnrollmentDTO> enrollList) {
		for (EnrollmentDTO enroll : enrollList) {
			try {
				enrollDAO.ObjectMapping(enroll);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		}
		return 1;
	}

	public int createEnrollment(EnrollmentDTO enroll) throws ClassNotFoundException, SQLException {
		try {
			System.out.println(enroll);
			return enrollDAO.enrollmentCourse(enroll);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<EnrollmentDTO> findAllCourses() {
		ArrayList<EnrollmentDTO> currentList = new ArrayList<EnrollmentDTO>();
		try {
			currentList = enrollDAO.findAllEnrollment();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return currentList;
	}

	public int updateEnrollment(EnrollmentDTO enroll) throws ClassNotFoundException, SQLException {
		try {
			return enrollDAO.updateEnrollment(enroll);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int removeEnrollment(EnrollmentDTO enroll) throws ClassNotFoundException, SQLException {
		try {
			return enrollDAO.deleteEnrollment(enroll);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// when user click check button show the lists of the enrollments
	public ArrayList<EnrollmentDTO> findEnrollmentsByCourseID(EnrollmentDTO enroll)
			throws ClassNotFoundException, SQLException {
		try {
			// no Mapping//return enrollDAO.findEnrollmentByCourse(false, enroll.getCourse().getCourseID());
			return enrollDAO.findEnrollmentByCourse(enroll.getCourse().getCourseID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<EnrollmentDTO> searchEnrollment(EnrollmentDTO enroll) {
		try {
			return enrollDAO.findEnrollmentByCriteria(enroll);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean checkCourseSize(EnrollmentDTO enroll)
	{
		try{
			return enrollDAO.checkCourseSize(enroll);
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
