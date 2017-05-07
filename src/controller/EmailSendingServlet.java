package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import email.EmailUtility;
import model.StudentDTO;
import service.StudentManager;

/**
 * A servlet that takes message details from user and send it as a new e-mail
 * through an SMTP server.
 *
 * @author www.codejava.net
 *
 */
@WebServlet("/email")
public class EmailSendingServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String host;
	private String port;
	private String user;
	private String pass;

	public void init() {
		// SMTP Setting from the web.xml
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get the email from the student by ID
		StudentManager sm = new StudentManager();
		String studentID = request.getParameter("studentID");
		String courseID = request.getParameter("courseID");
		StudentDTO stu = new StudentDTO();
		stu = sm.findStudentByID(studentID);
		String recipient = stu.getStudentEmail();
		String subject = "Your enrollment to course";
		String content = "Hello! Dear " + stu.getStudentFirstName() + " " + stu.getStudentSecondName()
				+ " You have successfully enrolled in " + courseID;
		String resultMessage = "";

		try {
			EmailUtility.sendEmail(host, port, user, pass, recipient, subject, content);
			resultMessage = "The e-mail was sent successfully";
			//To Show the email successful message
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Enrollment is successful. A confirmation email has been sent. ');");
			//For Student
			if (request.getParameter("flag").equals("student")) {
				out.println("location='/Team04CAPS/StudentCenter';");
				out.println("</script>");
			} else {
				out.println("location='/Team04CAPS/load?admin=enroll';");
				out.println("</script>");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		} finally {
			/*
			 * request.setAttribute("Message", resultMessage);
			 * getServletContext().getRequestDispatcher("/load?admin=enroll").
			 * forward( request, response);
			 */
		}
	}
}
