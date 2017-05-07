package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CourseDTO;
import model.LoginDTO;
import service.CourseManager;

/**
 * Servlet implementation class for Servlet: LoadCourseData
 *
 */
@WebServlet("/lecturerload")
 public class LoadCourseData extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LoadCourseData() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}   	  
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		
		CourseManager hm = new CourseManager();
		String userId = ((LoginDTO)request.getSession().getAttribute("profile")).getUserID();
		
		// Find all course taught by a LecturerID
		ArrayList<CourseDTO> data = hm.FindCourseByLecturerID(userId);
		request.setAttribute("courses", data);
		
		// good so far
		RequestDispatcher rd = request.getRequestDispatcher("/views/lecturer/CourseCRUDPage.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}