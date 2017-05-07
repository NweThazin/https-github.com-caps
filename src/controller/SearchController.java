package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CourseDTO;
import model.EnrollmentDTO;
import model.StudentDTO;
import service.EnrollmentManager;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {

		EnrollmentManager em = new EnrollmentManager();
		EnrollmentDTO enroll = new EnrollmentDTO();
		CourseDTO courseDTO = new CourseDTO();
		StudentDTO studentDTO = new StudentDTO();
		courseDTO.setCourseID(request.getParameter("courseID"));
		studentDTO.setStudentID(request.getParameter("studentID"));
		enroll.setCourse(courseDTO);
		enroll.setStudent(studentDTO);
		//enroll.setCourseID(request.getParameter("courseID"));
		//enroll.setStudentID(request.getParameter("studentID"));
		ArrayList<EnrollmentDTO> data = em.searchEnrollment(enroll);
		request.setAttribute("enrolls", data);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/EnrollmentCRUD.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
