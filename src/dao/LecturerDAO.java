package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import exception.DAOException;
import exception.NotFoundException;
import model.LecturerDTO;

public interface LecturerDAO {
	
	LecturerDTO findLecturerByID(String lecturerID) throws DAOException;

	ArrayList<LecturerDTO> findAllLecturer() throws DAOException;

	void insertLecturer(LecturerDTO lecturer) throws DAOException;

	void updateLecturer(LecturerDTO lecturer) throws DAOException;
	
	void deleteLecturer(LecturerDTO lecturer) throws DAOException, ClassNotFoundException, SQLException, NotFoundException;

	ArrayList<LecturerDTO> findLecturerByCriteria(LecturerDTO so) throws DAOException;
}
