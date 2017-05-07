package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CourseDTO;
import service.CourseManager;


/**
 * Servlet implementation class SearchCServlet
 */
@WebServlet("/searchC")
public class SearchCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchCServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			doProcess(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
	
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date sd=sdf.parse(request.getParameter("startDate"));
		String s=sd.toString();
		String i=request.getParameter("courseID");
		String cn = request.getParameter("courseName");
		if (cn.trim().equalsIgnoreCase("")&&i.trim().equalsIgnoreCase("")&&s.trim().equalsIgnoreCase("")) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/CourseCRUD.jsp?check=search");
			rd.forward(request, response);
		} else {
			CourseDTO cso = new CourseDTO();
			cso.setCourseName(cn);
			cso.setCourseID(i);
			cso.setStartDate(sd);
			CourseManager cm = new CourseManager();
			ArrayList<CourseDTO> courselist =cm.searchCourse(cso);
			request.setAttribute("courses", courselist);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/CourseCRUD.jsp?check=search");
			rd.forward(request, response);
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			doProcess(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
