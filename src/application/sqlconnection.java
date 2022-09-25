package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


class newuser {

    public static void newuserDatabase(String username,String cus_password, String cus_name, String phone, String cus_city) {

        String url = "jdbc:postgresql://localhost:5432/Cafe";
        String user = "postgres";
        String password = "12345";

        
        String usern = username;
        String passw = cus_password;
        String customername = cus_name;
        String phoneno = phone;
        String city = cus_city;
       
        // query
        String query = "INSERT INTO customers(username, cus_password, customer_name, phone_no, city) VALUES(?,?,?,?,?)";

        try 
       
        (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement p = conn.prepareStatement(query)) {
            //p.setString(1, customer_id);
        	p.setString(1, usern);
        	p.setString(2, passw);
            p.setString(3, customername);
            p.setString(4, phoneno);
            p.setString(5, city);
           
            
            p.executeUpdate();
            System.out.println("Sucessfully created.");
            
            conn.close();
            p.close();

        }
    
        catch (Exception e) {
        	System.out.println("ERROR CONNECTION");
        	System.out.println(e);
        	    }

    }
}

