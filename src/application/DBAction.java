package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAction {
	
	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public DBAction(int quantity) {
		super();
		this.quantity = quantity;
	}
	
	
//	public static int convertNullToZero(String nvar) {
//		System.out.println(nvar);
//		System.out.println("er")
//		if(nvar == null) {
//			System.out.println("err");
//			return 0;
//		} else {
//			//int validVariable = Integer.parseInt(nvar);
//			int validVariable = Integer.valueOf(nvar);
//			return validVariable;
//		}
//	}
	
	public static void insertUsernameToOrderTable(String usern) throws SQLException
    {
        try{
        	 String query = "INSERT INTO orders(username) VALUES(?)";
             Connection conn = DBUtils.getConnection();
             PreparedStatement p = conn.prepareStatement(query);
                
             p.setString(1, usern);
             
             p.executeUpdate();
             System.out.println("Sucessfully created.");
             
             conn.close();
             p.close();
        }catch(Exception e)
        {
            e.printStackTrace();
            //System.out.println("Error here");
        }
       
    }
	
	public static int retrieveOrderNo(String usern) throws SQLException {
		int currentOrder = 0;
		
		try {
			//String query = "SELECT order_no FROM orders WHERE username = ? ORDER BY order_no DESC LIMIT 1";
			Connection conn = DBUtils.getConnection();
			String query = "SELECT order_no FROM orders WHERE username = '" + usern + "' ORDER BY order_no DESC LIMIT 1";
			Statement p = conn.createStatement();
			ResultSet resultSet = p.executeQuery(query);
			
			while(resultSet.next()) {
				currentOrder = resultSet.getInt("order_no");
			}
			
			conn.close();
            p.close();
			
		}catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error here");
        }
		
		return currentOrder;
	}
	
//	public static void AddToOrderContent(int cold, int capp, int latt, int moch, int hotc, int current_order) throws SQLException {
//	      
//
//
//	   }

	public static void AddToOrderContent(int icold, int icapp, int ilatt, int imoch, int ihotc, int current_order) {
		// TODO Auto-generated method stub
		try {
	         Connection conn = DBUtils.getConnection();
	         Statement statement = conn.createStatement();

	         if(icold == 0) {
	            //error can occur in current_order and item_no variable placement - solved
	            //add qty column in order_content table - solved
	         } else {
	            statement.executeUpdate("INSERT INTO order_content(order_no, item_no, qty) VALUES(" + current_order + ", 1, " + icold + ")");
	         }

	         if(icapp == 0) {
	         } else {
	            statement.executeUpdate("INSERT INTO order_content(order_no, item_no, qty) VALUES(" + current_order + ", 2, " + icapp + ")");
	         }

	         if(ilatt == 0) {
	            //error can occur in current_order and item_no variable placement
	            //add qty column in order_content table
	         } else {
	            statement.executeUpdate("INSERT INTO order_content(order_no, item_no, qty) VALUES(" + current_order + ", 3, " + ilatt + ")");
	         }

	         if(imoch == 0) {
	            //error can occur in current_order and item_no variable placement
	            //add qty column in order_content table
	         } else {
	            statement.executeUpdate("INSERT INTO order_content(order_no, item_no, qty) VALUES(" + current_order + ", 4, " + imoch + ")");
	         }

	         if(ihotc == 0) {
	            //error can occur in current_order and item_no variable placement
	            //add qty column in order_content table
	         } else {
	            statement.executeUpdate("INSERT INTO order_content(order_no, item_no, qty) VALUES(" + current_order + ", 5, " + ihotc + ")");
	         }

	         conn.close();
	         statement.close();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
		
	}
	
//    public static String getColdQty(int current_orderno, int qty) {
//    	String retreive = null;
//    	
//    	try {
//        	Connection conn = DBUtils.getConnection();
//			Statement statement = conn.createStatement();
//			
//			String query = "SELECT qty FROM order_content WHERE order_no = " + current_orderno + " AND item_no = 1";
//			
//			
//			ResultSet resultSet = statement.executeQuery(query);
//			
//			while(resultSet.next()) {
//				retreive = resultSet.getString("qty");
//			}
//			
//			} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	
//        
//        return retreive;
//	}
        
        

}
