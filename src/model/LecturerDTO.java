package model;

import java.io.Serializable;

public class LecturerDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String lecturerID;
	private String firstName;
	private String secondName;
	private String phone;
	private String address;

	public String getLecturerID() {
		return lecturerID;
	}

	public void setLecturerID(String lecturerID) {
		this.lecturerID = lecturerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LecturerDTO(String lecturerID, String firstName, String secondName, String phone, String address) {
		super();
		this.lecturerID = lecturerID;
		this.firstName = firstName;
		this.secondName = secondName;
		this.phone = phone;
		this.address = address;
	}

	public LecturerDTO() {
		super();
	}

	@Override
	public String toString() {
		return "LecturerDTO [lecturerID=" + lecturerID + ", firstName=" + firstName + ", secondName=" + secondName
				+ ", phone=" + phone + ", address=" + address + "]";
	}
}
