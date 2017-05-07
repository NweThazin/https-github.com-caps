package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.NotFoundException;
import model.CourseDTO;
import model.EnrollmentDTO;
import model.LoginDTO;
import model.StudentDTO;
import service.CentralServiceForStudent;
import service.CourseManager;
import service.EnrollmentManager;
import service.StudentManager;

/**
 * Servlet implementation class ServletForStudent
 */
@WebServlet("/StudentCenter")
public class ServletForStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletForStudent() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		processCenter(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		processCenter(request, response);
	}

	private void processCenter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String studentID = ((LoginDTO)
		// request.getSession().getAttribute("profile")).getUserID();
		String action = (String) request.getParameter("action");
		if (action == null)
			action = "";
		// System.out.println(action);
		switch (action) {
		// MyProfile
		case "Student_MyInformation":
			doMyInformation(request, response);
			break;
		case "1":
			doMyInformation(request, response);
			break;
		// View Grades&GPA
		case "Student_Course_Grades":
			doCourse_Grades(request, response);
			break;
		case "2":
			doCourse_Grades(request, response);
			break;
		// View Undergoing Courses, optional
		// case "Student_ViewMyCourses" : doViewMyCourses(request, response);
		// break;
		// case "3" : doEnrollCourses(request, response); break;
		// View Courses can Enroll
		case "Student_EnrollCourses":
			doEnrollCourses(request, response);
			break;
		case "4":
			doEnrollCourses(request, response);
			break;
		// Enroll Selection
		case "Student_EnrollSelects":
			doEnrollSelects(request, response);
			break;
		// case "5" : doEnrollSelects(request, response); break;
		case "Processing_Enrollment":
			doProcessingEnrollment(request, response);
			break;
		case "6":
			doProcessingEnrollment(request, response);
			break;
		default:
			doMyInformation(request, response);
			break;
		}
	}

	private void doMyInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String studentID = ((LoginDTO) request.getSession().getAttribute("profile")).getUserID();
		StudentManager sm = new StudentManager();
		// process post from EditProfile
		if ((request.getParameter("ins") != null) && (request.getParameter("ins").equals("false"))) {
			// System.out.println("update");
			StudentDTO stu = new StudentDTO();
			stu.setStudentID(request.getParameter("studentID"));
			stu.setStudentFirstName(request.getParameter("FirstName"));
			stu.setStudentSecondName(request.getParameter("SecondName"));
			stu.setStudentAddress(request.getParameter("Address"));
			stu.setStudentYearStudy(request.getParameter("YearofStudy"));
			stu.setStudentEmail(request.getParameter("Email"));
			try {
				sm.updateStudent(stu);
			} catch (ClassNotFoundException | SQLException | NotFoundException e) {
				e.printStackTrace();
			}
		}

		// Display Student Profile
		// System.out.println("display");
		StudentDTO student = sm.findStudentByID(studentID);
		// System.out.println(student.toString());
		request.setAttribute("students", student);
		// System.out.println("Dispatch");
		RequestDispatcher rd = request.getRequestDispatcher("views/student/MyProfile.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doCourse_Grades(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CentralServiceForStudent ss = new CentralServiceForStudent();
		// later change all fixed studentID with session.getParameter("userID");
		String studentID = ((LoginDTO) request.getSession().getAttribute("profile")).getUserID();
		ArrayList<EnrollmentDTO> data = new ArrayList<EnrollmentDTO>();
		data = ss.findEnrollmentByStudent(studentID);
		double gpa = ss.getStudentGPA(studentID);

		/*
		 * System.out.println(data.toString()); System.out.println(data.size());
		 * // for debugging System.out.println(gpa);
		 */

		request.setAttribute("grades", data);
		request.setAttribute("GPA", gpa);

		RequestDispatcher rd = request.getRequestDispatcher("views/student/Course&Grades.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void doEnrollCourses(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CentralServiceForStudent secm = new CentralServiceForStudent();
		CourseManager cm = new CourseManager();
		String studentID = ((LoginDTO) request.getSession().getAttribute("profile")).getUserID();
		ArrayList<CourseDTO> data = secm.removeCourseStudentEnrolled(studentID, cm.findAllCourse());
		request.setAttribute("courses", data);

		RequestDispatcher rd = request.getRequestDispatcher("views/student/CourseList.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void doEnrollSelects(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String studentID = ((LoginDTO) request.getSession().getAttribute("profile")).getUserID();
		String courseID = request.getParameter("courseID");
		CentralServiceForStudent secm = new CentralServiceForStudent();
		CourseManager cm = new CourseManager();

		CourseDTO course = cm.findCourse(courseID);
		int num = secm.getVacancyOfCourse(courseID);

		request.setAttribute("studentID", studentID);
		request.setAttribute("course", course);
		request.setAttribute("num", num);

		RequestDispatcher rd = request.getRequestDispatcher("views/student/CourseEnroll.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doProcessingEnrollment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CentralServiceForStudent csfs = new CentralServiceForStudent();
		EnrollmentManager em = new EnrollmentManager();
		// String studentID =
		// (String)request.getSession().getAttribute("studentID");
		// String studentID = ((LoginDTO)
		// request.getSession().getAttribute("profile")).getUserID();
		// String courseID =
		// (String)request.getSession().getAttribute("courseID");
		String studentID = request.getParameter("studentID");
		String courseID = request.getParameter("courseID");
		System.out.println("S=" + studentID);
		System.out.println("C=" + courseID);
		// Check Course Size...
		EnrollmentDTO e = new EnrollmentDTO();
		CourseDTO c = new CourseDTO();
		c.setCourseID(courseID);
		e.setCourse(c);
		int res = 0;
		if (em.checkCourseSize(e)) {
			res = csfs.EnrollCourse(studentID, courseID);
		}
		// for email
		if (res == 1) {
			RequestDispatcher rd = request.getRequestDispatcher("/email?studentID=" + request.getParameter("studentID")
					+ "&courseID=" + request.getParameter("courseID") + "&flag=student");
			rd.forward(request, response);
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Cannot enroll. Class is full');");
			out.println("location='/Team04CAPS/StudentCenter';");
			out.println("</script>");
		}

		// RequestDispatcher rd =
		// request.getRequestDispatcher("views/student/ProcessingYourEnrollment.jsp");
		/*
		 * try { rd.forward(request, response); } catch (ServletException e) {
		 * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
		 */
	}
}
