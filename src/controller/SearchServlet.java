package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.StudentDTO;
import service.StudentManager;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
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

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Search By Student First Name
		String fName = request.getParameter("firstName");

		// If the course name is "" nothing show
		if (fName.trim().equalsIgnoreCase("")) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/lecturer/SearchPage.jsp");
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			StudentDTO studentDTO = new StudentDTO();
			StudentManager hm = new StudentManager();
			studentDTO.setStudentFirstName(fName);
			studentDTO.setStudentSecondName("");
			ArrayList<StudentDTO> studentlist = hm.findStudentByCriteria(studentDTO);
			request.setAttribute("searchlist", studentlist);
			RequestDispatcher rd = request.getRequestDispatcher("/views/lecturer/SearchPage.jsp");
			try {
				rd.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
