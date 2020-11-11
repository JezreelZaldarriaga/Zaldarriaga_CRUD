package zaldarriaga.crudapp;

import java.sql.*;

public class Model {
    static String value = "";
    public static void delete_data(String x){
        try {
            String url = "jdbc:derby://localhost:1527/employee"; 
            Connection conn = DriverManager.getConnection(url,"root","1234"); 
            Statement st = conn.createStatement(); 
            st.executeUpdate("DELETE from Record WHERE id='"+x+"'");
            conn.close();
        } catch (Exception e) {
            System.out.print("record not updated in db");
        }
    }
    public static void update_data(String x, String y){
        try {
            String url = "jdbc:derby://localhost:1527/employee"; 
            Connection conn = DriverManager.getConnection(url,"root","1234"); 
            Statement st = conn.createStatement(); 
            st.executeUpdate("update record set cash='"+x+"' where id='"+y+"'");
            conn.close();
        } catch (Exception e) {
            System.out.print("record not updated in db");
        }
    }
    public static String check_balance(String x){
        try {
            String url = "jdbc:derby://localhost:1527/employee"; 
            Connection conn = DriverManager.getConnection(url,"root","1234"); 
            Statement st = conn.createStatement(); 
            String query ="SELECT id, cash FROM record WHERE id='"+x+"'";
            ResultSet rs = st.executeQuery(query);
                while(rs.next()){
                    String replace = rs.getString("cash");
                    value =replace;
                }
            conn.close();
        } catch (Exception e) {
            System.out.print("User not registered db");
            value = "0";
        }
        return value;
    }
    
    
    //=======================================================
    
    public static String check_user(String x, String y){
            
        try {
            String url = "jdbc:derby://localhost:1527/employee"; 
            Connection conn = DriverManager.getConnection(url,"root","1234"); 
            Statement st = conn.createStatement();
            String query ="SELECT id FROM record WHERE id='"+x+"'";
            ResultSet rs = st.executeQuery(query);
                rs.next();
                String z =rs.getString("id");
                value = z;
                conn.close();
        } catch (Exception e) {
            try {
                String url = "jdbc:derby://localhost:1527/employee"; 
                Connection conn = DriverManager.getConnection(url,"root","1234");
                Statement st = conn.createStatement();
                st.executeUpdate("INSERT INTO record "+ "VALUES('"+x+"','"+y+"','0')");
                value = null;
            } catch (Exception ae) {
                value = null;
            }
            
        }
        return value;
    }
    
    
    public static int add(String num1, String num2){
        int a = Integer.parseInt(num1);
        int b = Integer.parseInt(num2);
        return a+b;
    }
    
    public static int subtract(String num1, String num2){
        int a = Integer.parseInt(num1);
        int b = Integer.parseInt(num2);
        return a-b;
    }
}
