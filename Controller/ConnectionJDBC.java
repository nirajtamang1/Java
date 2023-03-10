package Controller;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
public class ConnectionJDBC {
	
	public static Connection connection = null;
	public static Connection getConnection() {
		//Connecting with database of course management system
		try {
			//Connection the mysql driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Connection to the localhost system of xampp
			String url = "jdbc:mysql://localhost:3306/coursemanagementsystem";
			String username = "root";
			String password = "";
			connection = DriverManager.getConnection(url,username, password);
			}
			catch(Exception e) {
				throw new RuntimeException(e);
			}
		return connection;	
	}
}
