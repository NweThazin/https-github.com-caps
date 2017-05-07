package model;

import java.io.Serializable;
import java.util.Date;


public class EnrollmentDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private CourseDTO course;
	private StudentDTO student;
	private Date courseStart;
	private Date enrolmentBy;
	private String studentGrade;

	public EnrollmentDTO(CourseDTO course, StudentDTO student, Date courseStart, Date enrolmentBy,
			String studentGrade) {
		super();
		this.course = course;
		this.student = student;
		this.courseStart = courseStart;
		this.enrolmentBy = enrolmentBy;
		this.studentGrade = studentGrade;
	}

	public EnrollmentDTO() {
		super();
	}

	public CourseDTO getCourse() {
		return course;
	}

	public void setCourse(CourseDTO course) {
		this.course = course;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}

	public Date getCourseStart() {
		return courseStart;
	}

	public void setCourseStart(Date courseStart) {
		this.courseStart = courseStart;
	}

	public Date getEnrolmentBy() {
		return enrolmentBy;
	}

	public void setEnrolmentBy(Date enrolmentBy) {
		this.enrolmentBy = enrolmentBy;
	}

	public String getStudentGrade() {
		return studentGrade;
	}

	public void setStudentGrade(String studentGrade) {
		this.studentGrade = studentGrade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "EnrollmentDTO [course=" + course + ", student=" + student + ", courseStart=" + courseStart
				+ ", enrolmentBy=" + enrolmentBy + ", studentGrade=" + studentGrade + "]";
	}

}
