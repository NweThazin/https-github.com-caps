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
import model.LoginDTO;
import service.CourseManager;
import service.EnrollmentManager;

/**
 * Servlet implementation class LoadEnrollmentData
 */
@WebServlet("/enrollment")
public class LoadEnrollmentData extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	   static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadEnrollmentData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<ArrayList<EnrollmentDTO>> outer = new ArrayList<ArrayList<EnrollmentDTO>>();
		EnrollmentManager em = new EnrollmentManager();
		CourseManager hm = new CourseManager();
		String userId = ((LoginDTO) request.getSession().getAttribute("profile")).getUserID();
		//Firstly, Find the all course of logged in lecturerid
		ArrayList<CourseDTO> data = hm.FindCourseByLecturerID(userId);
		
		for (CourseDTO courseDTO : data) {
			
			EnrollmentDTO enroll = new EnrollmentDTO();
			enroll.setCourse(courseDTO);
			ArrayList<EnrollmentDTO> getEnr;
			try {
				getEnr = em.findEnrollmentsByCourseID(enroll);
				outer.add(getEnr);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("enrList", outer);
		RequestDispatcher rd = request.getRequestDispatcher("/views/lecturer/Enrollment.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
