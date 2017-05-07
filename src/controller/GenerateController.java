package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CourseManager;
import service.LecturerManager;
import service.StudentManager;

/**
 * Servlet implementation class GenerateController
 */
@WebServlet("/GenerateController")
public class GenerateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerateController() {
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

		//This is auto generate student ID
		if (request.getParameter("autoGenerate") != null && request.getParameter("autoGenerate").equals("student")) {
			try {
				StudentManager stu = new StudentManager();
				request.setAttribute("studentID", stu.getStudentID());
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/AddStudent.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}//AutoGenerate the Lecturer ID
		} else if (request.getParameter("autoGenerate") != null
				&& request.getParameter("autoGenerate").equals("lecturer")) {
			try {
				LecturerManager lm = new LecturerManager();
				request.setAttribute("lecturerID", lm.getLecturerID());
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/AddLecturer.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}//Auto Generate the Course ID
		} else if (request.getParameter("autoGenerate") != null
				&& request.getParameter("autoGenerate").equals("course")) {
			try {
				CourseManager cm = new CourseManager();
				LecturerManager lm = new LecturerManager();
				//request.setAttribute("courseID", cm.getCourseID());
				
				//CourseDTO c=cm.findCourse(request.getParameter("courseID"));
				
				//To bind the lists of lecturer in drop down box
				request.setAttribute("list",lm.getLecturerLists());
				
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/AddCourse.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
