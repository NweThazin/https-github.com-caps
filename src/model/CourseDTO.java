package model;

import java.io.Serializable;
import java.util.Date;

public class CourseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String courseID;
	private LecturerDTO lecturer;
	private String courseName;
	private Date startDate;
	private Date endDate;
	private double courseFees;
	private int courseCredits;
	private String comments;
	private int courseSize;

	// auto-generated constructors
	public CourseDTO() {
		super();
	}
	public CourseDTO(String courseID, LecturerDTO lecturer, String coureName, Date startDate, Date endDate,
			double courseFees, int courseCredits, String comments, int courseSize) {
		super();
		this.courseID = courseID;
		this.lecturer = lecturer;
		this.courseName = coureName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.courseFees = courseFees;
		this.courseCredits = courseCredits;
		this.comments = comments;
		this.courseSize = courseSize;
	}

	// auto-generated getter and setter
	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public LecturerDTO getLecturer() {
		return lecturer;
	}

	public void setLecturer(LecturerDTO lecturer) {
		this.lecturer = lecturer;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getCourseFees() {
		return courseFees;
	}

	public void setCourseFees(double courseFees) {
		this.courseFees = courseFees;
	}

	public int getCourseCredits() {
		return courseCredits;
	}

	public void setCourseCredits(int courseCredits) {
		this.courseCredits = courseCredits;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getCourseSize() {
		return courseSize;
	}

	public void setCourseSize(int courseSize) {
		this.courseSize = courseSize;
	}

	@Override
	public String toString() {
		return "CourseDTO [courseID=" + courseID + ", lecturer=" + lecturer + ", coureName=" + courseName
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", courseFees=" + courseFees
				+ ", courseCredits=" + courseCredits + ", comments=" + comments + ", courseSize=" + courseSize + "]";
	}

}
