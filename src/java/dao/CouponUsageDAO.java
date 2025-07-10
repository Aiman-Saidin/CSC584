package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class CouponUsageDAO {
        private static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
        private static final String DB_URL = "jdbc:derby://localhost:1527/FoodMartDB";
        private static final String USER = "app";
        private static final String PASS = "app";

        public boolean recordCouponUsage(int couponId, int userId, int orderId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "INSERT INTO CouponUsage (coupon_id, user_id, order_id) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, couponId);
            pstmt.setInt(2, userId);
            pstmt.setInt(3, orderId);

            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return success;
    }
    
        public int getCouponUsageCountForUser(int couponId, int userId) {
            Connection conn = null;
            PreparedStatement pstmt = null;
            int usageCount = 0;

            try {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);

                String sql = "SELECT COUNT(*) AS usage_count FROM CouponUsage WHERE coupon_id = ? AND user_id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, couponId);
                pstmt.setInt(2, userId);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    usageCount = rs.getInt("usage_count");
                }

                rs.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

            return usageCount;
        }
    
    public boolean hasUserUsedCoupon(int userId, int couponId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean hasUsed = false;
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String sql = "SELECT 1 FROM CouponUsage WHERE user_id = ? AND coupon_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, couponId);
            
            ResultSet rs = pstmt.executeQuery();
            hasUsed = rs.next();
            
            rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return hasUsed;
    }
}