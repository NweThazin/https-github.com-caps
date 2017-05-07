package model;

import java.io.Serializable;

public class StudentDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String studentID;
	private String studentFirstName;
	private String studentSecondName;
	private String studentAddress;
	private String studentYearStudy;
	private String studentEmail;
	private double studentCGPA;

	// auto-generated constructors
	public StudentDTO() {
		super();
	}
	public StudentDTO(String studentID, String studentFirstName, String studentSecondName, String studentAddress,
			String studentYearStudy, String studentEmail, double studentCGPA) {
		super();
		this.studentID = studentID;
		this.studentFirstName = studentFirstName;
		this.studentSecondName = studentSecondName;
		this.studentAddress = studentAddress;
		this.studentYearStudy = studentYearStudy;
		this.studentEmail = studentEmail;
		this.studentCGPA = studentCGPA;
	}
	
	// auto-generated getter and setter
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getStudentFirstName() {
		return studentFirstName;
	}
	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}
	public String getStudentSecondName() {
		return studentSecondName;
	}
	public void setStudentSecondName(String studentSecondName) {
		this.studentSecondName = studentSecondName;
	}
	public String getStudentAddress() {
		return studentAddress;
	}
	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}
	public String getStudentYearStudy() {
		return studentYearStudy;
	}
	public void setStudentYearStudy(String studentYearStudy) {
		this.studentYearStudy = studentYearStudy;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public double getStudentCGPA() {
		return studentCGPA;
	}
	public void setStudentCGPA(double studentCGPA) {
		this.studentCGPA = studentCGPA;
	}
	
	@Override
	public String toString() {
		return "StudentDTO [studentID=" + studentID + ", studentFirstName=" + studentFirstName + ", studentSecondName="
				+ studentSecondName + ", studentAddress=" + studentAddress + ", studentYearStudy=" + studentYearStudy
				+ ", studentEmail=" + studentEmail + ", studentCGPA=" + studentCGPA + "]";
	}

	//Getter and Setter methods for student

}
