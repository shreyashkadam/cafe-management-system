package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;

public class menupageController {
	  

    @FXML
    private Button button_aboutus;

    @FXML
    private Button button_cart;

    @FXML
    private Button button_logout;
	
//	 Connection conn = null;
//	    PreparedStatement pst = null;
//	    ResultSet rs = null;
		
	    @FXML
		public void onclick_cart(ActionEvent event) throws IOException {
//			TODO Autogenerated
			Parent submit_parent = FXMLLoader.load(getClass().getResource("cartpage.fxml"));
			Scene submit_scene = new Scene(submit_parent);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(submit_scene);
			app_stage.show();
	    
	}
	    
	    @FXML
		public void onclick_logout(ActionEvent event) throws IOException {
//			TODO Autogenerated
			Parent submit_parent = FXMLLoader.load(getClass().getResource("loginpage.fxml"));
			Scene submit_scene = new Scene(submit_parent);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(submit_scene);
			app_stage.show();
	    
	}
	    
	    @FXML
		public void onclick_aboutus(ActionEvent event) throws IOException {
//			TODO Autogenerated
			Parent submit_parent = FXMLLoader.load(getClass().getResource("abtpage.fxml"));
			Scene submit_scene = new Scene(submit_parent);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(submit_scene);
			app_stage.show();
	    
	}

}
