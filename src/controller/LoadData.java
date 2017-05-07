package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import exception.DAOException;
import exception.NotFoundException;
import model.CourseDTO;
import model.EnrollmentDTO;
import model.LecturerDTO;
import model.StudentDTO;
import service.CourseManager;
import service.EnrollmentManager;
import service.LecturerManager;
import service.StudentManager;

/**
 * Servlet implementation class for Servlet: LoadData
 *
 */
@WebServlet("/load")
public class LoadData extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LoadData() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ClassNotFoundException | DAOException | SQLException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, DAOException, SQLException, NotFoundException {

		if (request.getParameter("admin").equals("student")) {

			try {
				StudentManager sm = new StudentManager();
				ArrayList<StudentDTO> data = sm.findAllStudent();
				request.setAttribute("students", data);
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/StudentCRUD.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (request.getParameter("admin").equals("enroll")) {

			try {
				EnrollmentManager em = new EnrollmentManager();
				ArrayList<EnrollmentDTO> data = em.findAllCourses();
				request.setAttribute("enrolls", data);
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/EnrollmentCRUD.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (request.getParameter("admin").equals("lecturer")) {

			try {
				LecturerManager lm = new LecturerManager();
				ArrayList<LecturerDTO> data = lm.findAllLecturer();
				request.setAttribute("lecturers", data);
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/LecturerCRUD.jsp?lect=lload");
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (request.getParameter("admin").equals("course")) {

			try {
				CourseManager cm = new CourseManager();
				ArrayList<CourseDTO> data = cm.findAllCourse();
				request.setAttribute("courses", data);
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/CourseCRUD.jsp?check=nav");
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}