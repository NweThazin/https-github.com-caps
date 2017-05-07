package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.DBConnection;
import exception.DAOException;
import exception.NotFoundException;
import model.LecturerDTO;

public class LecturerDAOImpl implements LecturerDAO {

	Connection conn = null;
	
	//Find Lecturer By ID
	@Override
	public LecturerDTO findLecturerByID(String lecturerID) throws DAOException {
		String selectSql = "SELECT * FROM lecturer WHERE lecturerID='" + lecturerID.trim() + "';";

		LecturerDTO oneLecturer = new LecturerDTO();
		try {
			
			conn = DBConnection.openConnection();
			Statement st = conn.createStatement();
			Logger.getLogger(getClass().getName()).log(Level.INFO, "Executing select: " + selectSql);
			ResultSet rs = st.executeQuery(selectSql);
			while (rs.next()) {
				oneLecturer.setLecturerID(rs.getString(1));
				oneLecturer.setFirstName(rs.getString(2));
				oneLecturer.setSecondName(rs.getString(3));
				oneLecturer.setPhone(rs.getString(4));
				oneLecturer.setAddress(rs.getString(5));
			}
			st.close();
		} catch (Exception e) {
			String error = "Error selecting lecturer. Nested Exception is: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			throw new DAOException(error);
		} finally {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
			}
		}
		System.out.println(oneLecturer.toString());
		return oneLecturer;
	}

	//Find All Lecturer
	@Override
	public ArrayList<LecturerDTO> findAllLecturer() throws DAOException {
		
		ArrayList<LecturerDTO> items = new ArrayList<LecturerDTO>();
		String selectSql = "SELECT * FROM lecturer;";

		try {
			conn = DBConnection.openConnection();
			
			Statement st = conn.createStatement();
			Logger.getLogger(getClass().getName()).log(Level.INFO, "Executing select: " + selectSql);
			ResultSet rs = st.executeQuery(selectSql);
			
			while (rs.next()) {
				LecturerDTO oneLecturer = new LecturerDTO();
				oneLecturer.setLecturerID(rs.getString("lecturerID"));
				oneLecturer.setFirstName(rs.getString("firstName"));
				oneLecturer.setSecondName(rs.getString("secondName"));
				oneLecturer.setPhone(rs.getString("phone"));
				oneLecturer.setAddress(rs.getString("address"));
				items.add(oneLecturer);
			}
			st.close();
		} catch (Exception e) {
			String error = "Error selecting lecturers. Nested Exception is: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			throw new DAOException(error);
		} finally {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
			}
		}
		return items;
	}

	//Search By Lecturer Criteria
	public ArrayList<LecturerDTO> findLecturerByCriteria(LecturerDTO so) throws DAOException {
		ArrayList<LecturerDTO> list = new ArrayList<LecturerDTO>();
		String selectSql = null;
		if (so.getFirstName().trim().equalsIgnoreCase("")) {

			selectSql = "SELECT * FROM lecturer WHERE secondName LIKE '" + so.getSecondName().trim() + "%';";
		} else {
			if (so.getSecondName().trim().equalsIgnoreCase("")) {
				selectSql = "SELECT * FROM lecturer WHERE firstName LIKE '" + so.getFirstName().trim() + "%';";
			} else {
				selectSql = "SELECT * FROM lecturer WHERE secondName LIKE '" + so.getSecondName().trim()
						+ "%' AND firstName LIKE '" + so.getFirstName() + "%';";
			}
		}

		try {
			conn = DBConnection.openConnection();
			Statement st = conn.createStatement();
			conn.setAutoCommit(false);
			Logger.getLogger(getClass().getName()).log(Level.INFO, "Executing select: " + selectSql);
			
			ResultSet rs = st.executeQuery(selectSql);
			while (rs.next()) {
				LecturerDTO oneLecturer = new LecturerDTO();
				oneLecturer.setLecturerID(rs.getString(1));
				oneLecturer.setFirstName(rs.getString(2));
				oneLecturer.setSecondName(rs.getString(3));
				oneLecturer.setPhone(rs.getString(4));
				oneLecturer.setAddress(rs.getString(5));
				System.out.println(oneLecturer.toString());
				list.add(oneLecturer);
			}
			st.close();
		} catch (Exception e) {
			String error = "Error selecting users. Nested Exception is: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			throw new DAOException(error);
		} finally {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
			}
		}
		return list;
	}

	//Add New Lecturer 
	@Override
	public void insertLecturer(LecturerDTO lecturer) throws DAOException {
		
		String insertUser = "INSERT INTO login VALUES('" + lecturer.getLecturerID() + "', '" + "password" + "', '"
				+ "lecturer" + "');";
		String insertSql = "INSERT INTO lecturer VALUES('" + lecturer.getLecturerID() + "', '" + lecturer.getFirstName()
				+ "', '" + lecturer.getSecondName() + "', '" + lecturer.getPhone() + "', '" + lecturer.getAddress()
				+ "');";

		try {
			conn = DBConnection.openConnection();
			Statement st = conn.createStatement();
			
			Logger.getLogger(getClass().getName()).log(Level.INFO, "Executing insert: " + insertUser);
			Logger.getLogger(getClass().getName()).log(Level.INFO, "Executing insert: " + insertSql);
			
			int res = st.executeUpdate(insertUser);
			int res2 = st.executeUpdate(insertSql);
			
			System.out.println(res+"	"+res2);
			
			st.close();
		} catch (Exception e) {
			
			String error = "Error selecting lecturers. Nested Exception is: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			throw new DAOException(error);
		} finally {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
			}
		}
	}

	//Update the Lecturer
	@Override
	public void updateLecturer(LecturerDTO lecturer) throws DAOException {
		
		String updateSql = "UPDATE lecturer SET lecturerID = '" + lecturer.getLecturerID() + "', firstName = '"
				+ lecturer.getFirstName() + "', secondName = '" + lecturer.getSecondName() + "', phone = '"
				+ lecturer.getPhone() + "', address = '" + lecturer.getAddress() + "' WHERE lecturerID = '"
				+ lecturer.getLecturerID() + "';";

		try {
			conn = DBConnection.openConnection();
			
			Statement st = conn.createStatement();
			Logger.getLogger(getClass().getName()).log(Level.INFO, "Executing update: " + updateSql);
			
			int res = st.executeUpdate(updateSql);
			if (res != 1) {
				throw new DAOException("Cannot insert the student..");
			}
			
			st.close();
		
		} catch (Exception e) {
			
			String error = "Error selecting lecturers. Nested Exception is: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			throw new DAOException(error);
		} finally {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
			}
		}

	}

	// Delete Lecturer from the Database
	@Override
	public void deleteLecturer(LecturerDTO lecturer)
			throws DAOException, ClassNotFoundException, SQLException, NotFoundException {
		
		String deleteSql = "DELETE FROM lecturer WHERE lecturerID='" + lecturer.getLecturerID().trim() + "';";
		String deleteSql1 = "DELETE FROM login WHERE userID='" + lecturer.getLecturerID().trim() + "';";
		
		Connection conn = DBConnection.openConnection();
		conn.setAutoCommit(false);
		try {

			Statement st = conn.createStatement();
			
			Logger.getLogger(getClass().getName()).log(Level.INFO, "Executing delete: " + deleteSql);
			Logger.getLogger(getClass().getName()).log(Level.INFO, "Executing delete: " + deleteSql1);
			
			int res1 = st.executeUpdate(deleteSql);
			int res2 = st.executeUpdate(deleteSql1);
			if ((res1 != 1) || (res2 !=1 )) {
				throw new DAOException("Cannot insert the student..");
			}
			
			conn.commit();
			
			st.close();
		} catch (Exception e) {
			
			String error = "Error deleting lecturers. Nested Exception is: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			throw new DAOException(error);
		} finally {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
