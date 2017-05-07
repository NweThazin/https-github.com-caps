package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CourseDTO;
import model.EnrollmentDTO;
import model.StudentDTO;
import service.CourseManager;
import service.EnrollmentManager;

/**
 * Servlet implementation class EnrollmentController
 */
@WebServlet("/EnrollmentController")
public class EnrollmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EnrollmentController() {
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
			try {
				doProcess(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} catch (ParseException e) {
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
			try {
				doProcess(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, ClassNotFoundException, SQLException, ServletException, IOException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		EnrollmentManager em = new EnrollmentManager();
		CourseManager cm = new CourseManager();
		EnrollmentDTO e = new EnrollmentDTO();
		StudentDTO stu = new StudentDTO();
		CourseDTO course = new CourseDTO();
		int flag = 0;

		if (request.getParameter("action") != null && request.getParameter("action").equals("delete")) {

			stu.setStudentID(request.getParameter("studentID"));
			course.setCourseID(request.getParameter("courseID"));
			e.setStudent(stu);
			e.setCourse(course);
			flag = em.removeEnrollment(e);
			try {
				if (flag == 1) {

					RequestDispatcher rd = request.getRequestDispatcher("/load?admin=enroll");
					rd.forward(request, response);
				}
			} catch (Exception ee) {
				ee.printStackTrace();
			}

		} else {
			try {
				stu.setStudentID(request.getParameter("studentID"));
				course.setCourseID(request.getParameter("courseID"));
				
				CourseDTO c = new CourseDTO();
				c = cm.findCourse(request.getParameter("courseID"));
				
				e.setStudent(stu);
				e.setCourse(course);
				e.setCourseStart(c.getStartDate());
				Date date = new Date();
				String str = sdf.format(date);
				e.setEnrolmentBy(sdf.parse(str));
				e.setStudentGrade("N/A");
				if (em.checkCourseSize(e)) {
					flag = em.createEnrollment(e);
				}
				try {
					if (flag == 1) {
						RequestDispatcher rd = request.getRequestDispatcher("/email?studentID="
								+ request.getParameter("studentID") + "&courseID=" + request.getParameter("courseID")+"&flag=admin");
						rd.forward(request, response);
					} else {
						PrintWriter out = response.getWriter();
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Cannot enroll. Class is full');");
						out.println("location='views/admin/AddEnrollment.jsp';");
						out.println("</script>");
					}
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
	}
}
