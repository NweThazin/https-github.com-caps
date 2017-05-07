package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import exception.DAOException;
import exception.NotFoundException;
import model.CourseDTO;

public interface CourseDAO {

	int ObjectMapping(CourseDTO course) throws DAOException;

	CourseDTO findCourseByID(String courseID) throws DAOException;

	ArrayList<CourseDTO> findAllCourse() throws DAOException;

	ArrayList<CourseDTO> findCourseByCriteria(CourseDTO sear) throws DAOException;

	void insertCourse(CourseDTO course) throws DAOException;

	void updateCourse(CourseDTO course) throws DAOException;

	void deleteCourse(CourseDTO course) throws DAOException, ClassNotFoundException, SQLException, NotFoundException;
	
	ArrayList<CourseDTO> SearchCourse(CourseDTO course) throws DAOException;
}