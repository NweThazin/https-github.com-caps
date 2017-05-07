package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.NotFoundException;
import model.LecturerDTO;
import service.LecturerManager;

/**
 * Servlet implementation class LecturerController
 */
@WebServlet("/LecturerController")
public class LecturerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LecturerController() {
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
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws NotFoundException {

		LecturerManager lm = new LecturerManager();
		LecturerDTO lecturer = new LecturerDTO();

		try {
			if (request.getParameter("action") != null && request.getParameter("action").equals("delete")) {

				String lecturerID = request.getParameter("lecturerID");
				lecturer = lm.findLecturer(lecturerID);// Find the lecturer id
				System.out.println(lecturer.toString());
				try {
					lm.deleteLecturer(lecturer);
				} catch (ClassNotFoundException | SQLException | NotFoundException e) {
					PrintWriter out = response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Cannot delete.S/He still has courses');");
				//	out.println("location='index.jsp';");
					out.println("</script>");
					e.printStackTrace();
				}
			} else {
				lecturer.setLecturerID(request.getParameter("lecturerID"));
				lecturer.setFirstName(request.getParameter("firstName"));
				lecturer.setSecondName(request.getParameter("secondName"));
				lecturer.setPhone(request.getParameter("phone"));
				lecturer.setAddress(request.getParameter("address"));
				String ins = (String) request.getParameter("ins");

				Logger.getLogger(getClass().getName()).log(Level.INFO, "Insert Flag: " + ins);

				if (ins.equalsIgnoreCase("true")) {
					lm.insertLecturer(lecturer);
				} else {
					lm.updateLecturer(lecturer);
				}
			}
			RequestDispatcher rd = request.getRequestDispatcher("/load?admin=lecturer");
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("Error..");
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
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

}
