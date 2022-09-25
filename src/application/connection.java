package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {

	private final String url = "jdbc:postgresql://localhost:5432/Cafe";
	private final String user = "postgres";
	private final String password = "12345";
	
	private void connect() {
		try(Connection connection =DriverManager.getConnection(url, user, password);){
			if(connection != null) {
				System.out.println("Connected to PostgreSQL server successfully!");
			}else {
				System.out.println("Failed to connect to the PostgreSQL server");
			}
	} catch (SQLException e) {
		e.printStackTrace();
		
	}
}
	public static void main(String[] args) {
		
		connection sqlConnect = new connection();
		sqlConnect.connect();
	}
	
}


