package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.LecturerManager;

import model.LecturerDTO;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/searchL")
public class SearchLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchLServlet() {
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
		String fn = request.getParameter("firstName");
		String sn = request.getParameter("secondName");
		if (fn.trim().equalsIgnoreCase("") && sn.trim().equalsIgnoreCase("")) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/LecturerCRUD.jsp?lect=lsearch");
			rd.forward(request, response);
		} else {
			LecturerDTO lso = new LecturerDTO();
			lso.setFirstName(fn);
			lso.setSecondName(sn);
			LecturerManager lm = new LecturerManager();
			ArrayList<LecturerDTO> lecturerlist = lm.findLecturerByCriteria(lso);
			request.setAttribute("lecturers", lecturerlist);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/LecturerCRUD.jsp?lect=lsearch");
			rd.forward(request, response);
		}

	}

}
