package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EnrollmentDTO;
import service.CentralServiceForStudent;

/**
 * Servlet implementation class StudentTakenCourses
 */
@WebServlet("/StudentTakenCourses")
public class StudentTakenCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentTakenCourses() {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {

		try {
			CentralServiceForStudent ss = new CentralServiceForStudent();
			
			ArrayList<EnrollmentDTO> data = ss.findEnrollmentByStudent(request.getParameter("studentID"));
			double gpa = ss.getStudentGPA(request.getParameter("studentID"));
			request.setAttribute("grades", data);
			request.setAttribute("GPA", gpa);
			
			RequestDispatcher rd = request.getRequestDispatcher("views/lecturer/ViewStudentPerformance.jsp");
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
