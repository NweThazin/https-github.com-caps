package controller;

import java.io.IOException;

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
 * Servlet implementation class EnrollmentProcess
 */
@WebServlet("/EnrollmentProcess")
public class EnrollmentProcess extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EnrollmentProcess() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		try {
			EnrollmentManager hm = new EnrollmentManager();
			EnrollmentDTO enrollment = new EnrollmentDTO();
			CourseDTO courseDTO = new CourseDTO();
			StudentDTO studentDTO = new StudentDTO();
			courseDTO.setCourseID(request.getParameter("courseID"));
			studentDTO.setStudentID(request.getParameter("studentID"));
			enrollment.setCourse(courseDTO);
			enrollment.setStudent(studentDTO);
			enrollment.setStudentGrade(request.getParameter("grade"));
			

			try {
				hm.updateEnrollment(enrollment);
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/enrollment");
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
