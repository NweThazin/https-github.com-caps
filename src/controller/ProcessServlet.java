package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CourseDTO;
import model.EnrollmentDTO;
import service.EnrollmentManager;

/**
 * Servlet implementation class for Servlet: ProcessServlet
 *
 */
@WebServlet("/process")
public class ProcessServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ProcessServlet() {
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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ParseException {

		CourseDTO course = new CourseDTO();
		EnrollmentManager enrol = new EnrollmentManager();
		course.setCourseName(request.getParameter("name"));

		// DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String courseId = request.getParameter("courseID");

		// Find the vacancy information of a course with CourseID
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setCourseID(courseId);
		EnrollmentDTO enroll = new EnrollmentDTO();
		enroll.setCourse(courseDTO);
		ArrayList<EnrollmentDTO> enrDet = new ArrayList<EnrollmentDTO>();
		int arrySize = 0;
		try {
			enrDet = enrol.findEnrollmentsByCourseID(enroll);
			arrySize = enrDet.size();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		// ArrayList<EnrollmentDTO> enrDet =
		// enrol.findEnrolmentByCourseId(courseId);
		// Mapping is necessary here
		enrol.EnrollmentMapping(enrDet);
		request.setAttribute("course", course);
		request.setAttribute("enrold", enrDet);
		request.setAttribute("arrySize", arrySize);
		RequestDispatcher rd = request.getRequestDispatcher("/views/lecturer/EnrollmentDetailsPage.jsp");
		
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}