package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.NotFoundException;

public class DBConnection {

	public static Connection openConnection() throws ClassNotFoundException, SQLException, NotFoundException {

		Connection conn = null;
		try {
			Class.forName(MYSQLConstant.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new NotFoundException("Cannot load the driver");
		}
		try {
			conn = DriverManager.getConnection(MYSQLConstant.dbUrl, MYSQLConstant.dbUserName, MYSQLConstant.dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
