package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exception.NotFoundException;
import service.UserService;
import model.LoginDTO;

/**
 * Servlet implementation class Authenticate
 */
@WebServlet("/login")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Authenticate() {
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

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NotFoundException {

		String u = request.getParameter("username");
		String p = request.getParameter("password");
		LoginDTO user = new LoginDTO();
		user.setUserID(u);
		user.setUserPassword(p);

		UserService service;
		boolean result = false;
		try {
			service = new UserService();
			LoginDTO loggedInuser = service.authenticate(user);
			HttpSession session = null;

			if (loggedInuser != null) {
				session = request.getSession();
				session.setAttribute("profile", loggedInuser);
				result = true;
			}
			if (result == true) {
				if (loggedInuser.getUserRole().equals("admin")) {
					RequestDispatcher rd = request.getRequestDispatcher("/load?admin=student");
					rd.forward(request, response);
				} else if (loggedInuser.getUserRole().equals("student")) { //Need to change
					RequestDispatcher rd = request.getRequestDispatcher("/StudentCenter");
					rd.forward(request, response);
				} else if (loggedInuser.getUserRole().equals("lecturer")) {
					RequestDispatcher rd = request.getRequestDispatcher("/lecturerload");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("homePage/Login.jsp");
					rd.forward(request, response);
				}
				
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Cannot Login.. Try Again!!! ');");
				out.println("</script>");
				RequestDispatcher rd = request.getRequestDispatcher("homePage/Login.jsp");
				rd.forward(request, response);
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}
}
