package model;

import java.io.Serializable;

public class LoginDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userID;
	private String userPassword;
	private String userRole;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public LoginDTO(String userID, String userPassword, String userRole) {
		super();
		this.userID = userID;
		this.userPassword = userPassword;
		this.userRole = userRole;
	}

	public LoginDTO() {
		super();
	}

	@Override
	public String toString() {
		return "LoginDTO [userID=" + userID + ", userPassword=" + userPassword + ", userRole=" + userRole + "]";
	}

}
