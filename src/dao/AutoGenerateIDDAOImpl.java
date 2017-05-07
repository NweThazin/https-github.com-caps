package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.DBConnection;
import exception.NotFoundException;

public class AutoGenerateIDDAOImpl implements AutoGenerateIDDAO {

	Connection conn = null;
	String strID = null;
	
	// get next ID
	private String nextID(String ID) {
		int increment = 1;
		String result = "";
		for (int i = ID.length()-1; i>=0; i--) {
			char ch = ID.charAt(i); // String to char
			int nextValue = (int)ch + increment; // get ASCII code and add increment
			char nextch = (char)nextValue; // convert back to char
			increment = 0; // clear increment
			// case of '9', 'Z', 'z' -> '0', 'A', 'a' and increment = 1
			switch (ch) {
				case '9' : nextch = '0'; increment = 1; break;
				case 'Z' : nextch = 'A'; increment = 1; break;
				case 'z' : nextch = 'a'; increment = 1; break;
			}
			result = String.valueOf(nextch) + result;
		}
		return result;
	}
	
	//General method
	public String generateID(String paraStr) {
		return nextID(paraStr);
	}
	
	//Generate Student ID
	@Override
	public String generateStudentID() throws ClassNotFoundException, SQLException, NotFoundException {

		try {
			conn = DBConnection.openConnection();
			// get max studentID
			String strSelect = "SELECT * FROM student ORDER BY studentID DESC LIMIT 1;";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(strSelect);
			while (rs.next()) {
				strID = rs.getString("studentID");
			}
			conn.close();
			state.close();
			return generateID(strID);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return "";
	}
	//Generate Course ID
	@Override
	public String generateCourseID() throws ClassNotFoundException, SQLException, NotFoundException {

		try {
			conn = DBConnection.openConnection();
			// get max courseID
			String strSelect = "SELECT * FROM course ORDER BY courseID DESC LIMIT 1 ";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(strSelect);
			while (rs.next()) {
				strID = rs.getString("courseID");
			}
			conn.close();
			state.close();
			return generateID(strID);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return "";
	}
	//Generate Lecturer ID
	@Override
	public String generateLecturerID() throws ClassNotFoundException, SQLException, NotFoundException {

		try {
			conn = DBConnection.openConnection();
			// get max lecturerID
			String strSelect = "SELECT * FROM lecturer ORDER BY lecturerID DESC LIMIT 1;";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(strSelect);
			while (rs.next()) {
				strID = rs.getString("lecturerID");
			}
			conn.close();
			state.close();
			return generateID(strID);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return "";
	}
}
