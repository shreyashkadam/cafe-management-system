package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class DBUtils {
	

	public static String current_username;
	public static int current_order;
    

 static Stage stage =new Stage();	
	static void onSubmitbtnClick() throws IOException {
        try {
//        	Parent submit_parent = FXMLLoader.load(getClass().getResource("abtpage.fxml"));
//       		Scene submit_scene = new Scene(submit_parent);
//       		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//       		app_stage.setScene(submit_scene);
//       		app_stage.show();
        	
        	
            Class currentClass = new Object() {
            }.getClass().getEnclosingClass();
            Parent root = FXMLLoader.load(currentClass.getResource("abtpage.fxml"));
            Scene sc = new Scene(root);
            stage.setScene(sc);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
	
	public static void logInUser(ActionEvent event, String username, String password) throws IOException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Cafe", "postgres","12345");
			//preparedStatement = connection.prepareStatement("SELECT cus_password, city FROM customers WHERE username = ?");
			preparedStatement = connection.prepareStatement("SELECT cus_password FROM customers WHERE username = ?");
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.isBeforeFirst()) {
				
				System.out.println("User not found in the database!");
			    Alert alert = new Alert(Alert.AlertType.ERROR);
			    alert.setContentText("Provided credentials are incorrect!");
			    alert.show();
			}else {
				while(resultSet.next()) {
					String retrievePassword = resultSet.getString("cus_password");
//					String retrieveCity = resultSet.getString("city");
					
					if(retrievePassword.equals(password)) {
						
						//System.out.println("ERRO FOUND");
						current_username = username;
						
						//a function here to insert into order table, current_username into username
						DBAction.insertUsernameToOrderTable(current_username);
						
						//a funtion to retrieve order_no from order table and store in global variable, current_order
						current_order = DBAction.retrieveOrderNo(current_username);
						
//						Parent submit_parent = FXMLLoader.load(java.lang.Class.getResource("abtpage.fxml"));
//			       		Scene submit_scene = new Scene(submit_parent);
//			       		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//			       		app_stage.setScene(submit_scene);
//			       		app_stage.show();
						
						onSubmitbtnClick();
						
					}else {
						System.out.println("Passwords did not match!");
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("The Provided Credentials are incorrect!");
						alert.show();
					}
					
//					connection.close();
//					preparedStatement.close();
					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Yes " + e.getMessage());
			
		}
	
	}
	
	static Connection con;
	
	 public static Connection getConnection() {

	      try {
	         //load the driver
	         Class.forName("org.postgresql.Driver");

	         //create the connection
	         con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Cafe","postgres","12345");

	      } catch (Exception e) {
	         e.printStackTrace();
	         System.out.println("Error");
	      }

	      return con;
	}
	 
	
	
	
}