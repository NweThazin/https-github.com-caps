package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CourseDTO;
import model.LecturerDTO;
import service.CourseManager;

/**
 * Servlet implementation class CourseController
 */
@WebServlet("/CourseController")
public class CourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseController() {
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
			doProcess(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		CourseManager cm = new CourseManager();
		CourseDTO course = new CourseDTO();
		LecturerDTO lecturer = new LecturerDTO();

		try {
			if (request.getParameter("action") != null && request.getParameter("action").equals("delete")) {
				course.setCourseID(request.getParameter("courseID"));
				cm.deleteCourse(course);
				
			} else {

				course.setCourseID(request.getParameter("courseID"));
				lecturer.setLecturerID(request.getParameter("lecturerID"));
				course.setLecturer(lecturer);
				course.setCourseName(request.getParameter("courseName"));
				course.setStartDate(sdf.parse(request.getParameter("startDate")));
				course.setEndDate(sdf.parse(request.getParameter("endDate")));
				course.setCourseFees(Double.parseDouble(request.getParameter("courseFees")));
				course.setCourseCredits(Integer.parseInt(request.getParameter("courseCredits")));
				course.setComments(request.getParameter("comments"));
				course.setCourseSize(Integer.parseInt(request.getParameter("courseSize")));
				String ins = (String) request.getParameter("ins");

				Logger.getLogger(getClass().getName()).log(Level.INFO, "Insert Flag: " + ins);
				System.out.println("MESSAGE");

				if (ins.equalsIgnoreCase("true")) {
					cm.insertCourse(course);
				} else {
					cm.updateCourse(course);
				}
			}
			RequestDispatcher rd = request.getRequestDispatcher("/load?admin=course");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			doProcess(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
