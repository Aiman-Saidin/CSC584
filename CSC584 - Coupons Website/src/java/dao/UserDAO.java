package dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String DB_URL = "jdbc:derby://localhost:1527/FoodMartDB";
    private static final String USER = "app";
    private static final String PASS = "app";
    
    public int getUserCount() {
        Connection conn = null;
        Statement stmt = null;
        int count = 0;
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            
            String sql = "SELECT COUNT(*) AS user_count FROM users";
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                count = rs.getInt("user_count");
            }
            
            rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return count;
    }
    
    // Add other user-related methods as needed
}