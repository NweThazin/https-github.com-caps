package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.DBConnection;
import exception.DAOException;
import exception.NotFoundException;
import model.StudentDTO;

public class StudentDAOImpl implements StudentDAO {

	Connection conn = null;

	// Find Student By ID
	@Override
	public StudentDTO findStudentByID(String studentID) 
			throws DAOException {
		StudentDTO stu = new StudentDTO();
		try {
			conn = DBConnection.openConnection();
			String selectQuery = "SELECT * FROM student WHERE studentID = '" + studentID + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(selectQuery);
			while (rs.next()) {
				stu.setStudentID(rs.getString("studentID"));
				stu.setStudentFirstName(rs.getString("firstName"));
				stu.setStudentSecondName(rs.getString("secondName"));
				stu.setStudentAddress(rs.getString("address"));
				stu.setStudentYearStudy(rs.getString("yearStudy"));
				stu.setStudentEmail(rs.getString("email"));
				stu.setStudentCGPA(rs.getDouble("cGPA"));
			}
			return stu;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	// Retrieve all student lists from the student table
	@Override
	public ArrayList<StudentDTO> findAllStudent()
			throws DAOException {

		try {
			conn = DBConnection.openConnection();

			ArrayList<StudentDTO> items = new ArrayList<StudentDTO>();
			String selectQuery = "SELECT * FROM student;";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(selectQuery);

			while (rs.next()) {
				StudentDTO stu = new StudentDTO();
				stu.setStudentID(rs.getString("studentID"));
				stu.setStudentFirstName(rs.getString("firstName"));
				stu.setStudentSecondName(rs.getString("secondName"));
				stu.setStudentAddress(rs.getString("address"));
				stu.setStudentYearStudy(rs.getString("yearStudy"));
				stu.setStudentEmail(rs.getString("email"));
				stu.setStudentCGPA(rs.getDouble("cGPA"));
				items.add(stu);
			}
			return items;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public ArrayList<StudentDTO> findStudentByCriteria(StudentDTO so) throws DAOException {
		// TODO Auto-generated method stub

		ArrayList<StudentDTO> items = new ArrayList<StudentDTO>();
		String selectSql = null;
		if (so.getStudentFirstName().trim().equalsIgnoreCase("")) {
			selectSql = "SELECT * FROM student WHERE secondName LIKE '" + so.getStudentSecondName().trim() + "%';";
		} else {
			if (so.getStudentSecondName().trim().equalsIgnoreCase("")) {
				selectSql = "SELECT * FROM student WHERE firstName LIKE '" + so.getStudentFirstName().trim() + "%';";
			} else {
				selectSql = "SELECT * FROM student WHERE secondName LIKE '" + so.getStudentSecondName().trim()
						+ "%' AND firstName LIKE '" + so.getStudentFirstName() + "%';";
			}
		}
		try {
			conn = DBConnection.openConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(selectSql);

			while (rs.next()) {
				StudentDTO stu = new StudentDTO();
				stu.setStudentID(rs.getString("studentID"));
				stu.setStudentFirstName(rs.getString("firstName"));
				stu.setStudentSecondName(rs.getString("secondName"));
				stu.setStudentAddress(rs.getString("address"));
				stu.setStudentYearStudy(rs.getString("yearStudy"));
				stu.setStudentEmail(rs.getString("email"));
				stu.setStudentCGPA(rs.getDouble("cGPA"));
				items.add(stu);
			}
			return items;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	// Add New Student to database methods
	@Override
	public int addStudent(StudentDTO stu) 
			throws DAOException {

		try {
			conn = DBConnection.openConnection();
			conn.setAutoCommit(false);

			PreparedStatement prepLogin = conn.prepareStatement("INSERT INTO login VALUES(?,?,?)");
			prepLogin.setString(1, stu.getStudentID());
			prepLogin.setString(2, "password");
			prepLogin.setString(3, "student");

			PreparedStatement prepStudent = conn.prepareStatement("INSERT INTO student VALUES(?,?,?,?,?,?,?);");
			prepStudent.setString(1, stu.getStudentID());
			prepStudent.setString(2, stu.getStudentFirstName());
			prepStudent.setString(3, stu.getStudentSecondName());
			prepStudent.setString(4, stu.getStudentAddress());
			prepStudent.setString(5, stu.getStudentYearStudy());
			prepStudent.setString(6, stu.getStudentEmail());
			prepStudent.setDouble(7, 0);

			int p1 = prepLogin.executeUpdate();
			int p = prepStudent.executeUpdate();

			if (p1 != 1 || p != 1) {
				throw new NotFoundException("Cannot insert the student..");
			}
			conn.commit();
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("Cannot Insert the data into the table");
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Delete the student record from the student table
	@Override
	public int removeStudent(String studentID)
			throws DAOException, ClassNotFoundException, SQLException, NotFoundException {

		int res = 0;
		try {
			conn = DBConnection.openConnection();
			conn.setAutoCommit(false);
			Statement st = conn.createStatement();

			String strCourse = "DELETE FROM enrolment WHERE user_userid=\"" + studentID+"\";";
			System.out.println(strCourse);
			res = st.executeUpdate(strCourse);
			if (res != 1) {
				//throw new NotFoundException("Cannot remove the students from the list");
			}
			String strStudent = "DELETE FROM student WHERE studentID=\"" + studentID+"\";";
			System.out.println(strCourse);
			res = st.executeUpdate(strStudent);
			if (res != 1) {
				//throw new NotFoundException("Cannot remove the students from the list");
			}	
			String strLogin = "DELETE FROM login WHERE userID=\"" + studentID+"\";";
			System.out.println(strCourse);
			res = st.executeUpdate(strLogin);
			if (res != 1) {
				//throw new NotFoundException("Cannot remove the students from the list");
			}
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			throw new NotFoundException("Cannot remove the student from the db");
		} finally {
			conn.commit();
			conn.close();
		}
		return res;
	}

	// Update the student information
	@Override
	public int updateStudent(StudentDTO stu)
			throws DAOException {

		int result = 0;
		PreparedStatement prep = null;
		try {
			conn = DBConnection.openConnection();
			conn.setAutoCommit(false);

			prep = conn.prepareStatement(
					"UPDATE student SET firstName=?,secondName=?,address=?,yearStudy=?,email=?,cGPA=? WHERE studentID=?;");

			prep.setString(1, stu.getStudentFirstName());
			prep.setString(2, stu.getStudentSecondName());
			prep.setString(3, stu.getStudentAddress());
			prep.setString(4, stu.getStudentYearStudy());
			prep.setString(5, stu.getStudentEmail());
			prep.setDouble(6, stu.getStudentCGPA());
			prep.setString(7, stu.getStudentID());
			result = prep.executeUpdate();
			if (result != 1) {
				throw new DAOException("Cannot update the students from the list");
			}	
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
				throw new DAOException("Cannot Update the Student");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
				prep.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
