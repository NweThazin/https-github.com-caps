package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import exception.DAOException;
import exception.NotFoundException;
import model.StudentDTO;

public interface StudentDAO {

	public StudentDTO findStudentByID(String studentID) throws DAOException;

	public ArrayList<StudentDTO> findAllStudent() throws DAOException;

	public ArrayList<StudentDTO> findStudentByCriteria(StudentDTO studentDTO) throws DAOException;
	
	public int addStudent(StudentDTO stu) throws DAOException;

	public int updateStudent(StudentDTO stu) throws DAOException;
	
	public int removeStudent(String studentID) throws DAOException, ClassNotFoundException, SQLException, NotFoundException;
	
}
