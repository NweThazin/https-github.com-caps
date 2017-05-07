package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import model.LoginDTO;
import dao.DAOFactory;
import dao.UserDao;
import exception.NotFoundException;

public class UserService {
	private UserDao udao;
	private Connection conn;

	public UserService() throws NotFoundException {
		super();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/caps", "root", "password");
			this.udao = DAOFactory.getUserDao();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new NotFoundException("Driver Fault");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NotFoundException("SQL Fault");
		}
		udao=DAOFactory.getUserDao();
		
	}
	//call from controller
	public LoginDTO authenticate(LoginDTO u) throws NotFoundException {
		try {							//DAO
			ArrayList<LoginDTO> list = this.udao.searchMatching(this.conn, u);
			if (list.size() > 0)
				return list.get(0);
			else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<LoginDTO> findUsers() throws NotFoundException {
		try {
			return (this.udao.loadAll(this.conn));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NotFoundException("Error in load all");
		}
	}
	
	@SuppressWarnings("finally")
	public LoginDTO findUsersDetails(String userId, String password) throws NotFoundException {
		LoginDTO userObj = new LoginDTO();
		try {
			userObj = udao.getUserDetails(conn, userId, password);
		} finally
		{
			return userObj;
		}
	}
	
}
