package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.DBConnection;
import exception.DAOException;
import model.CourseDTO;
import model.LecturerDTO;

public class CourseDAOImpl implements CourseDAO {

	Connection conn = null;

	HashMap<String, LecturerDTO> LecturerMapping = new HashMap<String, LecturerDTO>();

	@Override
	public int ObjectMapping(CourseDTO course) throws DAOException {
		String lecturerID = course.getLecturer().getLecturerID();
		LecturerDTO lecturerDTO = LecturerMapping.get(lecturerID);
		if (lecturerDTO != null) {
			course.setLecturer(lecturerDTO);
			return 1;
		}
		LecturerDAO lecturerDAO = DAOFactory.getLecturerDAO();
		lecturerDTO = lecturerDAO.findLecturerByID(lecturerID);
		if (lecturerDTO != null) {
			LecturerMapping.put(lecturerID, lecturerDTO);
			course.setLecturer(lecturerDTO);
			return 1;
		}
		return 0;
	}

	@Override
	public CourseDTO findCourseByID(String courseID) throws DAOException {
		String selectSql = "SELECT * FROM course WHERE courseID='" + courseID.trim() + "';";

		LecturerDTO lecturer = new LecturerDTO();
		CourseDTO oneCourse = new CourseDTO();
		Connection conn = null;
		try {
			conn = DBConnection.openConnection();
			Statement st = conn.createStatement();
			Logger.getLogger(getClass().getName()).log(Level.INFO, "Executing select: " + selectSql);
			ResultSet rs = st.executeQuery(selectSql);
			while (rs.next()) {

				oneCourse.setCourseID(rs.getString(1));
				lecturer.setLecturerID(rs.getString(2));
				oneCourse.setLecturer(lecturer);
				oneCourse.setCourseName(rs.getString(3));
				oneCourse.setStartDate(rs.getDate(4));
				oneCourse.setEndDate(rs.getDate(5));
				oneCourse.setCourseFees(rs.getDouble(6));
				oneCourse.setCourseCredits(rs.getInt(7));
				oneCourse.setComments(rs.getString(8));
				oneCourse.setCourseSize(rs.getInt(9));
			}
			st.close();
		} catch (Exception e) {
			String error = "Error selecting lecturer. Nested Exception is: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			throw new DAOException(error);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
		// System.out.println(oneCourse.toString());
		return oneCourse;
	}

	@Override
	public ArrayList<CourseDTO> findAllCourse() throws DAOException {

		try {
			conn = DBConnection.openConnection();

			ArrayList<CourseDTO> items = new ArrayList<CourseDTO>();
			String selectSql = "SELECT * FROM course;";
			Statement st = conn.createStatement();
			Logger.getLogger(getClass().getName()).log(Level.INFO, "Executing select: " + selectSql);

			ResultSet rs = st.executeQuery(selectSql);
			while (rs.next()) {

				CourseDTO oneCourse = new CourseDTO();
				LecturerDTO lecturer = new LecturerDTO();
				oneCourse.setCourseID(rs.getString(1));
				lecturer.setLecturerID(rs.getString(2));
				oneCourse.setLecturer(lecturer);
				oneCourse.setCourseName(rs.getString(3));
				oneCourse.setStartDate(rs.getDate(4));
				oneCourse.setEndDate(rs.getDate(5));
				oneCourse.setCourseFees(rs.getDouble(6));
				oneCourse.setCourseCredits(rs.getInt(7));
				oneCourse.setComments(rs.getString(8));
				oneCourse.setCourseSize(rs.getInt(9));
				items.add(oneCourse);
			}
			st.close();
			return items;

		} catch (Exception e) {
			String error = "Error selecting lecturers. Nested Exception is: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			throw new DAOException(error);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Grade Course
	@Override
	public ArrayList<CourseDTO> findCourseByCriteria(CourseDTO so) throws DAOException {

		ArrayList<CourseDTO> list = new ArrayList<CourseDTO>();

		String selectSql = null;
		if (so.getLecturer().getLecturerID().trim().equalsIgnoreCase("")) {
			selectSql = "SELECT * FROM course WHERE courseName LIKE '" + so.getCourseName().trim() + "%';";
		} else {
			if (so.getCourseName().trim().equalsIgnoreCase("")) {
				selectSql = "SELECT * FROM course WHERE lecturerID LIKE '" + so.getLecturer().getLecturerID().trim()
						+ "%';";
			} else {
				selectSql = "SELECT * FROM course WHERE lecturerID LIKE '" + so.getLecturer().getLecturerID().trim()
						+ "%' AND courseName LIKE '" + so.getCourseName() + "%';";
			}
		}
		try {
			conn = DBConnection.openConnection();
			Statement st = conn.createStatement();
			Logger.getLogger(getClass().getName()).log(Level.INFO, "Executing select: " + selectSql);
			ResultSet rs = st.executeQuery(selectSql);
			while (rs.next()) {

				LecturerDTO lecturer = new LecturerDTO();
				CourseDTO oneCourse = new CourseDTO();
				oneCourse.setCourseID(rs.getString(1));
				lecturer.setLecturerID(rs.getString(2));
				oneCourse.setLecturer(lecturer);
				oneCourse.setCourseName(rs.getString(3));
				oneCourse.setStartDate(rs.getDate(4));
				oneCourse.setEndDate(rs.getDate(5));
				oneCourse.setCourseFees(rs.getDouble(6));
				oneCourse.setCourseCredits(Integer.parseInt(rs.getString(7)));
				oneCourse.setComments(rs.getString(8));
				oneCourse.setCourseSize(rs.getInt(9));
				list.add(oneCourse);
			}
			st.close();
		} catch (Exception e) {
			String error = "Error selecting users. Nested Exception is: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			throw new DAOException(error);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
		return list;
	}

	@Override
	public void insertCourse(CourseDTO course) throws DAOException {

		java.sql.Date startDate = new java.sql.Date(course.getStartDate().getTime());
		java.sql.Date endDate = new java.sql.Date(course.getEndDate().getTime());

		String insertSql = "INSERT INTO course VALUES('" + course.getCourseID() + "', '"
				+ course.getLecturer().getLecturerID() + "', '" + course.getCourseName() + "', '" + startDate + "', '"
				+ endDate + "', '" + course.getCourseFees() + "', '" + course.getCourseCredits() + "', '"
				+ course.getComments() + "', '" + course.getCourseSize() + "');";
		try {
			conn = DBConnection.openConnection();
			Statement st = conn.createStatement();

			Logger.getLogger(getClass().getName()).log(Level.INFO, "Executing insert: " + insertSql);
			int res2 = st.executeUpdate(insertSql);
			// System.out.println(res2);
			st.close();

		} catch (Exception e) {
			String error = "Error selecting lecturers. Nested Exception is: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			throw new DAOException(error);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void updateCourse(CourseDTO course) throws DAOException {
		java.sql.Date startDate = new java.sql.Date(course.getStartDate().getTime());
		java.sql.Date endDate = new java.sql.Date(course.getEndDate().getTime());

		String updateSql = "UPDATE course SET courseID = '" + course.getCourseID() + "', lecturerID = '"
				+ course.getLecturer().getLecturerID() + "', courseName = '" + course.getCourseName()
				+ "', startDate = '" + startDate + "', endDate = '" + endDate + "',courseFees = '"
				+ course.getCourseFees() + "', courseCredits = '" + course.getCourseCredits() + "', comments = '"
				+ course.getComments() + "',courseSize = '" + course.getCourseSize() + "' WHERE courseID = '"
				+ course.getCourseID() + "';";
		try {
			conn = DBConnection.openConnection();
			Statement st = conn.createStatement();
			Logger.getLogger(getClass().getName()).log(Level.INFO, "Executing update: " + updateSql);
			int res = st.executeUpdate(updateSql);
			// System.out.println(res);
			st.close();
		} catch (Exception e) {

			String error = "Error selecting lecturers. Nested Exception is: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			throw new DAOException(error);

		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteCourse(CourseDTO course) throws DAOException {

		String deleteSql = "DELETE FROM course WHERE courseID='" + course.getCourseID().trim() + "';";
		String deleteSql1 = "DELETE FROM enrolment WHERE course_courseID='" + course.getCourseID().trim() + "';";

		try {
			conn = DBConnection.openConnection();
			Statement st = conn.createStatement();

			Logger.getLogger(getClass().getName()).log(Level.INFO, "Executing delete: " + deleteSql);
			Logger.getLogger(getClass().getName()).log(Level.INFO, "Executing delete: " + deleteSql1);

			int rows1 = st.executeUpdate(deleteSql);
			int rows = st.executeUpdate(deleteSql1);
			// System.out.println(rows1 + " " + rows);

			st.close();
		} catch (Exception e) {
			String error = "Error deleting lecturers. Nested Exception is: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			throw new DAOException(error);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}

	// Jane Search
	@Override
	public ArrayList<CourseDTO> SearchCourse(CourseDTO so) throws DAOException {

		java.sql.Date startDate = new java.sql.Date(so.getStartDate().getTime());
		String sd = startDate.toString();
		ArrayList<CourseDTO> list = new ArrayList<CourseDTO>();
		String selectSql = null;
		if (so.getCourseID().trim().equalsIgnoreCase("")) {
			selectSql = "SELECT * FROM course WHERE courseName LIKE '" + so.getCourseName().trim()
					+ "%' AND startDate>='" + startDate + "';";
		} else {
			if (so.getCourseName().trim().equalsIgnoreCase("")) {
				selectSql = "SELECT * FROM course WHERE courseID LIKE'" + so.getCourseID().trim()
						+ "%' AND startDate>='" + startDate + "';";
			} else {
				if (sd.trim().equalsIgnoreCase("")) {
					selectSql = "SELECT * FROM course WHERE courseName LIKE '" + so.getCourseName().trim()
							+ "%' AND courseID LIKE '" + so.getCourseID().trim() + "%';";
				} else {
					selectSql = "SELECT * FROM course WHERE courseName LIKE '" + so.getCourseName().trim()
							+ "%' AND courseID LIKE '" + so.getCourseID().trim() + "%' AND startDate>='" + startDate
							+ "';d";
				}
			}

		}
		try {
			conn = DBConnection.openConnection();
			conn.setAutoCommit(false);
			Statement st = conn.createStatement();
			Logger.getLogger(getClass().getName()).log(Level.INFO, "Executing select: " + selectSql);
			ResultSet rs = st.executeQuery(selectSql);
			while (rs.next()) {
				CourseDTO oneCourse = new CourseDTO();
				oneCourse.setCourseID(rs.getString(1));
				LecturerDTO lecturer = new LecturerDTO();
				lecturer.setLecturerID(rs.getString(2));
				oneCourse.setLecturer(lecturer);
				oneCourse.setCourseName(rs.getString(3));
				oneCourse.setStartDate(rs.getDate(4));
				oneCourse.setEndDate(rs.getDate(5));
				oneCourse.setCourseFees(rs.getDouble(6));
				oneCourse.setCourseCredits(rs.getInt(7));
				oneCourse.setComments(rs.getString(8));
				oneCourse.setCourseSize(rs.getInt(9));
				list.add(oneCourse);
			}
			conn.commit();
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

	/*
	 * // Find all courses by Lecturer ID
	 * 
	 * @Override public ArrayList<CourseDTO> findCourseByLecturer(String
	 * lecturerID) throws DAOException {
	 * 
	 * ArrayList<CourseDTO> list = new ArrayList<CourseDTO>();
	 * 
	 * String selectSql = "SELECT * FROM course WHERE lecturerID LIKE '" +
	 * lecturerID + "%';"; try { conn = DBConnection.openConnection(); Statement
	 * st = conn.createStatement();
	 * Logger.getLogger(getClass().getName()).log(Level.INFO,
	 * "Executing select: " + selectSql); ResultSet rs =
	 * st.executeQuery(selectSql); while (rs.next()) {
	 * 
	 * CourseDTO oneCourse = new CourseDTO(); LecturerDTO lecturer = new
	 * LecturerDTO(); oneCourse.setCourseID(rs.getString(1));
	 * oneCourse.setLecturer(lecturer);
	 * oneCourse.setCourseName(rs.getString(3));
	 * oneCourse.setStartDate(rs.getDate(4));
	 * oneCourse.setEndDate(rs.getDate(5));
	 * oneCourse.setCourseFees(rs.getDouble(6));
	 * oneCourse.setCourseCredits(Integer.parseInt(rs.getString(7)));
	 * oneCourse.setComments(rs.getString(8));
	 * oneCourse.setCourseSize(rs.getInt(9)); list.add(oneCourse); } st.close();
	 * } catch (Exception e) { String error =
	 * "Error selecting users. Nested Exception is: " + e;
	 * Logger.getLogger(getClass().getName()).log(Level.SEVERE, error); throw
	 * new DAOException(error); } finally { try { conn.close(); } catch
	 * (Exception e) { } } return list; }
	 */
	/*
	 * public ArrayList<CourseDTO> findAllCoursesByLecturerID(String lecturerID)
	 * throws DAOException { ArrayList<CourseDTO> items = new
	 * ArrayList<CourseDTO>(); String selectSql =
	 * "SELECT cr.* FROM courses cr join lecturer lec on cr.lecturerID = lec.lecturerID where lec.lecturerID = '"
	 * + lecturerID + "' ORDER BY courseID;"; try { conn =
	 * DBConnection.openConnection(); Statement st = conn.createStatement();
	 * Logger.getLogger(getClass().getName()).log(Level.INFO,
	 * "Executing select: " + selectSql); ResultSet rs =
	 * st.executeQuery(selectSql); while (rs.next()) { CourseDTO oneCourse = new
	 * CourseDTO(); oneCourse.setCourseID(rs.getString("courseID"));
	 * oneCourse.setCourseName(rs.getString("courseName"));
	 * oneCourse.setComments(rs.getString("comments"));
	 * oneCourse.setCourseSize(rs.getInt("courseSize")); SimpleDateFormat format
	 * = new SimpleDateFormat("yyyy-MM-dd");
	 * oneCourse.setStartDate(format.parse(rs.getString("startDate")));
	 * items.add(oneCourse); } st.close(); } catch (Exception e) { String error
	 * = "Error selecting courses. Nested Exception is: " + e;
	 * Logger.getLogger(getClass().getName()).log(Level.SEVERE, error); throw
	 * new DAOException(error); } finally { try { conn.rollback(); conn.close();
	 * } catch (Exception e) { } } return items; }
	 */
}
