package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static application.DBUtils.current_order;
import static application.cartpageController.ipcold;

public class cartpageController {
	
	public static int ipcold;
	public static int ipcapp;
	public static int iplatt;
	public static int ipmoch;
	public static int iphotc;
	
	public static String retrieveColdQty = "";
	public static String retrieveCappQty = "";
	public static String retrieveLattQty = "";
	public static String retrieveMochQty = "";
	public static String retrieveHotcQty = "";


  
	@FXML
	private Button button_aboutus;
	 
    @FXML
    private Button button_logout;

    @FXML
    private Button button_billnew;
	
	
    @FXML
    private Button button_placeorder;


    @FXML
    private TextField txf_capp;

    @FXML
    private TextField txf_cold;

    @FXML
    private TextField txf_hotc;

    @FXML
    private TextField txf_latt;

    @FXML
    private TextField txf_moch;


    
    @FXML
	public void onclick_placeorder(ActionEvent event) throws IOException, SQLException {
//		TODO Autogenerated
		try {
  
	    Parent submit_parent = FXMLLoader.load(getClass().getResource("billpage.fxml"));
		Scene submit_scene = new Scene(submit_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(submit_scene);
		app_stage.show();
	
		int icold = convertNullToZero(txf_cold.getText());
		int icapp = convertNullToZero(txf_capp.getText());
		int ilatt = convertNullToZero(txf_latt.getText());
		int imoch = convertNullToZero(txf_moch.getText());
		int ihotc = convertNullToZero(txf_hotc.getText());
		
		ipcold = icold;
		ipcapp = icapp;
		iplatt = ilatt;
		ipmoch = imoch;
		iphotc = ihotc;
		
		DBAction.AddToOrderContent(icold, icapp, ilatt, imoch, ihotc, current_order);
		
		//change getColdQty to getQty by adding third parameter for item_no
//		retrieveColdqty = getColdQty(current_order, ipcold);
		
		//
		retrieveColdQty = getQty(current_order, ipcold, 1);
		retrieveCappQty = getQty(current_order, ipcapp, 2);
		retrieveLattQty = getQty(current_order, iplatt, 3);
		retrieveMochQty = getQty(current_order, ipmoch, 4);
		retrieveHotcQty = getQty(current_order, iphotc, 5);
		
        }catch(Exception e) {
			e.printStackTrace();
		}

    }
    
    @FXML
   	public void onclick_billnew(ActionEvent event) throws IOException {
//   		TODO Autogenerated
//   		Parent submit_parent = FXMLLoader.load(getClass().getResource("billpage.fxml"));
//   		Scene submit_scene = new Scene(submit_parent);
//   		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//   		app_stage.setScene(submit_scene);
//   		app_stage.show();
    	
    	System.out.println("Place order first!");
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("First Place the order from Menu");
		alert.show();
       }
    
    @FXML
   	public void onclick_aboutus(ActionEvent event) throws IOException {
//   		TODO Autogenerated
   		Parent submit_parent = FXMLLoader.load(getClass().getResource("abtpage.fxml"));
   		Scene submit_scene = new Scene(submit_parent);
   		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
   		app_stage.setScene(submit_scene);
   		app_stage.show();
       }
    
    @FXML
   	public void onclick_logout(ActionEvent event) throws IOException {
//   		TODO Autogenerated
   		Parent submit_parent = FXMLLoader.load(getClass().getResource("loginpage.fxml"));
   		Scene submit_scene = new Scene(submit_parent);
   		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
   		app_stage.setScene(submit_scene);
   		app_stage.show();
       }
    
    public int convertNullToZero(String nvar) {
		//System.out.println(nvar);
		//System.out.println("er");
		if(nvar.isEmpty()) {
			//System.out.println("err");
			return 0;
		} else {
			//int validVariable = Integer.parseInt(nvar);
			int validVariable = Integer.valueOf(nvar);
			return validVariable;
		}
	}
    
    public static String getQty(int current_orderno, int qty, int item_num) {
    	String retreive = "";
    	String query = "SELECT qty FROM order_content WHERE order_no = ? AND item_no = ?";
    	
    	try {
        	Connection conn = DBUtils.getConnection();
        	PreparedStatement pstmt = conn.prepareStatement(query);
        	pstmt.setLong(1, current_orderno);
        	pstmt.setLong(2, item_num);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			
			while(resultSet.next()) {
				retreive = resultSet.getString("qty");
			}
			
			
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   	
      return retreive;
	}
    
    public static String getPrice(int item_num) {
    	String retreive = "";
    	String query = "SELECT item_cost FROM items WHERE item_no = ?";
    	
    	try {
        	Connection conn = DBUtils.getConnection();
        	PreparedStatement pstmt = conn.prepareStatement(query);
        	pstmt.setLong(1, item_num);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			
			while(resultSet.next()) {
				retreive = resultSet.getString("item_cost");
			}
			
			
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   	
      return retreive;
	}
    
   
    

}

    
    
    
    
    
    
