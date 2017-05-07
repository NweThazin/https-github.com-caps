package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.NotFoundException;
import model.StudentDTO;
import service.StudentManager;

/**
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentController() {
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
		} catch (NumberFormatException | ClassNotFoundException | SQLException | NotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException {
		try {
			doProcess(request, response);
		} catch (ClassNotFoundException | SQLException | NotFoundException e) {
			e.printStackTrace();
		}
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException, NumberFormatException, ClassNotFoundException, SQLException, NotFoundException {

		StudentManager sm = new StudentManager();
		int flag = 0;
		
		if (request.getParameter("action") != null && request.getParameter("action").equals("delete")) {
			
			flag = sm.removeStudent(request.getParameter("studentID"));

		} else {
			String ins = (String) request.getParameter("ins");
			StudentDTO stu = new StudentDTO();
			stu.setStudentID(request.getParameter("studentID"));
			stu.setStudentFirstName(request.getParameter("FirstName"));
			stu.setStudentSecondName(request.getParameter("SecondName"));
			stu.setStudentAddress(request.getParameter("Address"));
			stu.setStudentYearStudy(request.getParameter("YearofStudy"));
			stu.setStudentEmail(request.getParameter("Email"));
			String gpa = request.getParameter("CGPA");
			if (gpa != "" && gpa != null) {
				stu.setStudentCGPA(Double.valueOf(gpa));
			}
			try {
				if (ins.equalsIgnoreCase("true")) {
					flag = sm.addStudent(stu);
				} else {
					flag = sm.updateStudent(stu);
				}
			} catch (ClassNotFoundException | SQLException | NotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			if (flag == 1) {
				RequestDispatcher rd = request.getRequestDispatcher("/load?admin=student");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
