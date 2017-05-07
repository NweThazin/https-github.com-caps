package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import connection.DBConnection;
import exception.DAOException;
import exception.NotFoundException;
import model.CourseDTO;
import model.EnrollmentDTO;
import model.StudentDTO;

public class EnrollmentDAOImpl implements EnrollmentDAO {

	Connection conn = null;

	// -- About Efficiency
	// Consider we have n students and m courses, n~10,000 and m~1000
	// So the number of all Enrollments can be n*m, and Mapping all enrollment
	// may takes O((n+m)*n*m), quite slow
	// Here we use HashMap to accelerate the process of mapping.
	// Actually, findAllEnrollment is useless in real environment LOL.
	// While findEnrollment by Student or Course, number of Enrollment will be
	// no more than 100, thus no HashMap is needed
	HashMap<String, CourseDTO> CourseMapping = new HashMap<String, CourseDTO>();
	HashMap<String, StudentDTO> StudentMapping = new HashMap<String, StudentDTO>();

	@Override
	public int CourseMapping(EnrollmentDTO enroll) throws DAOException {
		String courseID = enroll.getCourse().getCourseID();
		CourseDTO courseDTO = CourseMapping.get(courseID);
		if (courseDTO != null) {
			enroll.setCourse(courseDTO);
			return 1;
		}
		CourseDAO courseDAO = DAOFactory.getCourseDAO();
		courseDTO = courseDAO.findCourseByID(courseID);
		if (courseDTO != null) {
			CourseMapping.put(courseID, courseDTO);
			enroll.setCourse(courseDTO);
			return 1;
		}
		return 0;
	}

	@Override
	public int StudentMapping(EnrollmentDTO enroll) throws DAOException {
		String StudentID = enroll.getStudent().getStudentID();
		StudentDTO StudentDTO = StudentMapping.get(StudentID);
		if (StudentDTO != null) {
			enroll.setStudent(StudentDTO);
			return 1;
		}
		StudentDAO StudentDAO = DAOFactory.getStudentDAO();
		StudentDTO = StudentDAO.findStudentByID(StudentID);
		if (StudentDTO != null) {
			StudentMapping.put(StudentID, StudentDTO);
			enroll.setStudent(StudentDTO);
			return 1;
		}
		return 0;
	}

	@Override
	public int ObjectMapping(EnrollmentDTO enroll) throws DAOException {
		// TODO Auto-generated method stub
		int res1 = CourseMapping(enroll);
		int res2 = StudentMapping(enroll);
		return res1 * res2;
	}

	// Show All Enrollment Lists
	@Override
	public ArrayList<EnrollmentDTO> findAllEnrollment() throws NotFoundException {
		try {
			String selectQuery = "SELECT * FROM enrolment;";

			conn = DBConnection.openConnection();
			ArrayList<EnrollmentDTO> items = new ArrayList<EnrollmentDTO>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(selectQuery);

			while (rs.next()) {

				EnrollmentDTO enroll = new EnrollmentDTO();
				CourseDTO c = new CourseDTO();
				StudentDTO s = new StudentDTO();
				c.setCourseID(rs.getString("course_courseID"));
				s.setStudentID(rs.getString("user_userid"));
				enroll.setCourse(c);
				enroll.setStudent(s);
				enroll.setCourseStart(rs.getDate("courseStart"));
				enroll.setEnrolmentBy(rs.getDate("enrolmentBy"));
				enroll.setStudentGrade(rs.getString("studentGrade"));
				items.add(enroll);
			}
			conn.close();
			return items;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<EnrollmentDTO> findEnrollmentByCourse(String courseID) throws NotFoundException {
		try {
																									//Change
			String selectQuery = "SELECT * FROM enrolment where course_courseID = '" + courseID + "';";
			conn = DBConnection.openConnection();
			ArrayList<EnrollmentDTO> items = new ArrayList<EnrollmentDTO>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(selectQuery);

			while (rs.next()) {
				
				EnrollmentDTO enroll = new EnrollmentDTO();
				CourseDTO c = new CourseDTO();
				StudentDTO s = new StudentDTO();
				c.setCourseID(courseID);
				s.setStudentID(rs.getString("user_userid"));
				enroll.setCourse(c);
				enroll.setStudent(s);
				enroll.setCourseStart(rs.getDate("courseStart"));
				enroll.setEnrolmentBy(rs.getDate("enrolmentBy"));
				enroll.setStudentGrade(rs.getString("studentGrade"));
				items.add(enroll);
			}
			conn.close();
			return items;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<EnrollmentDTO> findEnrollmentByStudent(String studentID) throws NotFoundException {
		try {

			String selectQuery = "SELECT * FROM enrolment where user_userid = '" + studentID + "';";
			conn = DBConnection.openConnection();
			ArrayList<EnrollmentDTO> items = new ArrayList<EnrollmentDTO>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(selectQuery);
			while (rs.next()) {

				EnrollmentDTO enroll = new EnrollmentDTO();
				CourseDTO c = new CourseDTO();
				StudentDTO s = new StudentDTO();
				// known student StudentDTO s = new StudentDTO();
				c.setCourseID(rs.getString("course_courseID"));
				s.setStudentID(studentID);
				enroll.setCourse(c);
				// known student
				enroll.setStudent(s);
				enroll.setCourseStart(rs.getDate("courseStart"));
				enroll.setEnrolmentBy(rs.getDate("enrolmentBy"));
				enroll.setStudentGrade(rs.getString("studentGrade"));
				items.add(enroll);
			}
			conn.close();
			return items;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<EnrollmentDTO> findEnrollmentByCriteria(EnrollmentDTO enroll) throws NotFoundException {

		try {

			String selectQuery = "SELECT * FROM enrolment WHERE course_courseID=\"" + enroll.getCourse().getCourseID()
					+ "\" OR user_userid=\"" + enroll.getStudent().getStudentID() + "\";";

			conn = DBConnection.openConnection();
			ArrayList<EnrollmentDTO> items = new ArrayList<EnrollmentDTO>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(selectQuery);
			while (rs.next()) {

				EnrollmentDTO edto = new EnrollmentDTO();
				CourseDTO c = new CourseDTO();
				StudentDTO s = new StudentDTO();
				c.setCourseID(rs.getString("course_courseID"));
				s.setStudentID(rs.getString("user_userid"));
				edto.setCourse(c);
				edto.setStudent(s);
				edto.setCourseStart(rs.getDate("courseStart"));
				edto.setEnrolmentBy(rs.getDate("enrolmentBy"));
				edto.setStudentGrade(rs.getString("studentGrade"));
				items.add(edto);
			}
			return items;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// Make New Enrollment
	@Override
	public int enrollmentCourse(EnrollmentDTO enroll) throws NotFoundException {
		System.out.println(enroll.toString());
		try {
			java.sql.Date sqlDate = new java.sql.Date(enroll.getCourseStart().getTime());
			java.sql.Date sqlEnrollBy = new java.sql.Date(enroll.getEnrolmentBy().getTime());
			conn = DBConnection.openConnection();
			conn.setAutoCommit(false);

			PreparedStatement prep3 = conn.prepareStatement("INSERT INTO enrolment VALUES(?,?,?,?,?)");
			prep3.setString(1, enroll.getCourse().getCourseID());
			prep3.setString(2, enroll.getStudent().getStudentID());
			prep3.setDate(3, sqlDate);
			prep3.setDate(4, sqlEnrollBy);
			prep3.setString(5, enroll.getStudentGrade());
			int p3 = prep3.executeUpdate();
			
			System.out.println(prep3.toString());
			if (p3 != 1) {
				throw new NotFoundException("Cannot insert the student..");
			}
			conn.commit();
			conn.close();
			return p3;
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("Cannot Insert the data into the table");
		}
	}

	@Override
	public int updateEnrollment(EnrollmentDTO enroll) throws SQLException, NotFoundException {

		int result = 0;
		try {
			PreparedStatement prep = null;
			conn = DBConnection.openConnection();
			conn.setAutoCommit(false);
			//java.sql.Date sqlEnrollBy = new java.sql.Date(enroll.getEnrolmentBy().getTime());
			//java.sql.Date courseStart = new java.sql.Date(enroll.getCourseStart().getTime());
			prep = conn.prepareStatement(
					"UPDATE enrolment SET studentGrade=? WHERE course_courseID=? AND user_userid=?;");

			//prep.setDate(1, courseStart);
			//prep.setDate(2, sqlEnrollBy);
			prep.setString(1, enroll.getStudentGrade());
			prep.setString(2, enroll.getCourse().getCourseID());
			prep.setString(3, enroll.getStudent().getStudentID());
			result = prep.executeUpdate();

			conn.commit();
			return result;
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return result;
	}

	// Delete the enrollments
	@Override
	public int deleteEnrollment(EnrollmentDTO enroll) throws SQLException, NotFoundException {

		int res = 0;
		try {
			conn = DBConnection.openConnection();
			conn.setAutoCommit(false);

			PreparedStatement prep = conn
					.prepareStatement("DELETE FROM enrolment WHERE course_courseID=? AND user_userid=?;");
			prep.setString(1, enroll.getCourse().getCourseID());
			prep.setString(2, enroll.getStudent().getStudentID());
			res = prep.executeUpdate();
			if (res != 1) {
				throw new NotFoundException("Cannot remove the students from the list");
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

	@Override
	public boolean checkCourseSize(EnrollmentDTO enroll) {
		boolean flag = false;
		int cSize = 0, tCount = 0;
		String selectCourse = "SELECT courseSize FROM course WHERE courseID=\"" + enroll.getCourse().getCourseID()
				+ "\"";
		String selectEnrol = "SELECT COUNT(*) AS total FROM enrolment GROUP BY course_courseID HAVING course_courseID=\""
				+ enroll.getCourse().getCourseID() + "\"";
		try {
			conn = DBConnection.openConnection();
			//ArrayList<EnrollmentDTO> items = new ArrayList<EnrollmentDTO>();

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(selectCourse);
			while (rs.next()) {
				cSize = Integer.parseInt(rs.getString("courseSize"));
			}
			rs.close();
			st.close();

			st = conn.createStatement();
			ResultSet rs1 = st.executeQuery(selectEnrol);
			while (rs1.next()) {
				tCount = Integer.parseInt(rs1.getString("total"));
			}
			rs1.close();
			st.close();
			if (tCount < cSize) {
				flag = true;
				return flag;
			}
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

}
