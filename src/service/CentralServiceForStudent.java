package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import dao.CourseDAO;
import dao.DAOFactory;
import dao.EnrollmentDAO;
import exception.DAOException;
import exception.NotFoundException;
import model.CourseDTO;
import model.EnrollmentDTO;
import model.StudentDTO;

public class CentralServiceForStudent {
	private EnrollmentDAO enrollmentDAO;
	private CourseDAO courseDAO;
	private double studentGPA;
	
	// Constructor
	public CentralServiceForStudent() {
		enrollmentDAO = DAOFactory.getEnrollmentDAO();
		courseDAO = DAOFactory.getCourseDAO();
		studentGPA = -1.0;
	}
	
	public ArrayList<EnrollmentDTO> findEnrollmentByStudent(String studentID) {
		try {
			ArrayList<EnrollmentDTO> result = enrollmentDAO.findEnrollmentByStudent(studentID);
			for (EnrollmentDTO enroll : result) {
				enrollmentDAO.ObjectMapping(enroll);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// From StudentEnrollCourseManager
	
	// findCourseStudentNotEnrolled
	public ArrayList<CourseDTO> removeCourseStudentEnrolled(String studentID, ArrayList<CourseDTO> courseList) {
		ArrayList<CourseDTO> result = new ArrayList<CourseDTO>();
		HashSet<String> EnrolledCourseIDSet = new HashSet<String>();
		try {
			// no Mapping
			//for (EnrollmentDTO enrollment : enrollmentDAO.findEnrollmentByStudent(false, studentID)) {
			for (EnrollmentDTO enrollment : enrollmentDAO.findEnrollmentByStudent(studentID)) {
				EnrolledCourseIDSet.add(enrollment.getCourse().getCourseID());
			}
		} catch (NotFoundException e) {
			// NotFound is actually OK which means currently no enrollment
		}
		for (CourseDTO course : courseList) {
			if (!EnrolledCourseIDSet.contains(course.getCourseID()))
				result.add(course);
		}
		return result;
	}
	
	// get number of Students enrolled for certain class
	public int getVacancyOfCourse(String courseID) {
		int result = 0;
		 try {
			// not Mapping
			//result = enrollmentDAO.findEnrollmentByCourse(false, courseID).size();
			 result = enrollmentDAO.findEnrollmentByCourse(courseID).size();
		} catch (NotFoundException e) {
			// Not Found is not a big problem dude
		}
		return result;
	}
	
	// call Enrollment Manager to make a enroll 
	public int EnrollCourse(String studentID, String courseID) {
		int flag = 0;

		try {
			// No need to Mapping studentDTO, only give the studentID to write the database
			// Because we pass CourseStart and CourseSize in this method, so no need to Mapping courseDTO neither
			// System.out.println("CentralService");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			EnrollmentManager em = new EnrollmentManager();
			EnrollmentDTO e = new EnrollmentDTO();
			StudentDTO student = new StudentDTO();
			CourseDTO course = new CourseDTO();
			student.setStudentID(studentID);
			//course.setCourseID(courseID);
			course = courseDAO.findCourseByID(courseID);
			Date date = new Date();
			String EnrollBy = sdf.format(date);
			
			e.setStudent(student);
			//e.setCourse(course);
			e.setCourse(course);
			e.setCourseStart(course.getStartDate());
			e.setEnrolmentBy(sdf.parse(EnrollBy));
			e.setStudentGrade("N/A");
			//flag = em.updateEnrollment(e);
			System.out.println(e.toString());
			flag = em.createEnrollment(e);
		} catch (Exception er) {
			er.printStackTrace();
		}
		try
		{
			if (flag == 1) {
				// Success information. Because we enable auto-email, so let's say processing...
			}
		} catch(Exception ee)
		{
			ee.printStackTrace();
		}
		return flag;
	}

	
	
	// From GradesManager
	// get GPA
	// public double getStudentGPA() { return studentGPA; }
	public double getStudentGPA(String studentID) {
		if (studentGPA < 0)
			try {
				studentGPA = calculateGPA(studentID);
			} catch (NotFoundException e) {
				// NotFound is OK means students currently didn't finish class
				//e.printStackTrace();
				studentGPA = 0.0;
			}
		return studentGPA;
	}
	
	// new version by using object relation mapping
	private double calculateGPA(String studentID) throws NotFoundException {
		//calculate gpa
		//System.out.println("access to calcluateGPA");
		double tot = 0.0; double sum = 0.0;
		ArrayList<EnrollmentDTO> enrollments = enrollmentDAO.findEnrollmentByStudent(studentID);
		for (EnrollmentDTO sg : enrollments) {
			// Mapping enrollment with CourseDTO
			try {
				enrollmentDAO.ObjectMapping(sg);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			double credit = 0.0; double points = 0.0;
			String grades = sg.getStudentGrade();
			credit = sg.getCourse().getCourseCredits();
			switch (grades) {
				case "A": points = 5.0; break;
				case "B": points = 4.0; break;
				case "C": points = 3.0; break;
				case "D": points = 2.0; break;
				case "E": points = 1.0; break;
				case "F": points = 0.0; break;
				default : points = 0.0; credit = 0.0; break;
			}
			tot = tot + credit;
			sum = sum + credit * points;
		}
		//System.out.println(tot);
		//System.out.println(sum);
		return Double.parseDouble(String.format("%.2f", (sum / tot)));
	}

}
