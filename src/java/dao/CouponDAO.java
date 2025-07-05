package dao;

import model.Coupon;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CouponDAO {
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String DB_URL = "jdbc:derby://localhost:1527/FoodMartDB";
    private static final String USER = "app";
    private static final String PASS = "app";
    
    // In-memory storage for simplicity (replace with database in production)
    private static final Map<String, Coupon> coupons = new HashMap<>();
    
    static {
        // Initialize with some sample coupons
        coupons.put("SAVE10", new Coupon("SAVE10", 10, "PERCENT"));
        coupons.put("FIVEOFF", new Coupon("FIVEOFF", 5, "FIXED"));
        coupons.put("WELCOME20", new Coupon("WELCOME20", 20, "PERCENT"));
    }
    
    public Coupon getCouponByCode(String code) {
        // For production, replace with database query
        return coupons.get(code);
    }
    
    // Database version (uncomment if you want to use database instead)
    /*
    public Coupon getCouponByCode(String code) {
        Coupon coupon = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String sql = "SELECT * FROM coupons WHERE code = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, code);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                coupon = new Coupon();
                coupon.setCode(rs.getString("code"));
                coupon.setDiscountValue(rs.getDouble("discount_value"));
                coupon.setDiscountType(rs.getString("discount_type"));
            }
            
            rs.close();
            pstmt.close();
            conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
        
        return coupon;
    }
    */
}