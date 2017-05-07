package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CourseDTO;
import model.EnrollmentDTO;
import service.EnrollmentManager;

/**
 * Servlet implementation class CheckEnrollmentLists
 */
@WebServlet("/check")
public class CheckEnrollmentLists extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckEnrollmentLists() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {

		try {
			EnrollmentManager em = new EnrollmentManager();
			EnrollmentDTO enroll = new EnrollmentDTO();
			CourseDTO course = new CourseDTO();
			
			course.setCourseID(request.getParameter("courseID"));
			enroll.setCourse(course);
			ArrayList<EnrollmentDTO> data = em.findEnrollmentsByCourseID(enroll);
			request.setAttribute("enrolls", data);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/EnrollmentCRUD.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
