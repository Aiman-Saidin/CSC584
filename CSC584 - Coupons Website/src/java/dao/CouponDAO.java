package dao;

import model.Coupon;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CouponDAO {
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String DB_URL = "jdbc:derby://localhost:1527/FoodMartDB";
    private static final String USER = "app";
    private static final String PASS = "app";
    
    public List<Coupon> getAllCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            
            String sql = "SELECT * FROM Coupon ORDER BY created_at DESC";
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Coupon coupon = new Coupon();
                coupon.setCouponId(rs.getInt("coupon_id"));
                coupon.setCode(rs.getString("code"));
                coupon.setDiscountType(rs.getString("discount_type"));
                coupon.setDiscountValue(rs.getDouble("discount_value"));
                coupon.setExpirationDate(rs.getDate("expiration_date"));
                coupon.setUsageLimit(rs.getInt("usage_limit"));
                coupon.setIsActive(rs.getBoolean("is_active"));
                coupon.setCampaignId(rs.getInt("campaign_id"));
                coupon.setProductId(rs.getInt("product_id"));
                coupon.setCreatedAt(rs.getTimestamp("created_at"));
                
                coupons.add(coupon);
            }
            
            rs.close();
            stmt.close();
            conn.close();
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
        return coupons;
    }
    
    public boolean createCoupon(Coupon coupon) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String sql = "INSERT INTO Coupon (code, discount_type, discount_value, " +
                         "expiration_date, usage_limit, is_active, campaign_id, product_id) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, coupon.getCode());
            pstmt.setString(2, coupon.getDiscountType());
            pstmt.setDouble(3, coupon.getDiscountValue());
            pstmt.setDate(4, coupon.getExpirationDate());
            
            if (coupon.getUsageLimit() != null) {
                pstmt.setInt(5, coupon.getUsageLimit());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }
            
            pstmt.setBoolean(6, coupon.isIsActive());
            
            if (coupon.getCampaignId() != null) {
                pstmt.setInt(7, coupon.getCampaignId());
            } else {
                pstmt.setNull(7, Types.INTEGER);
            }
            
            if (coupon.getProductId() != null) {
                pstmt.setInt(8, coupon.getProductId());
            } else {
                pstmt.setNull(8, Types.INTEGER);
            }
            
            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
            
            pstmt.close();
            conn.close();
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
    
    public boolean updateCoupon(Coupon coupon) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String sql = "UPDATE Coupon SET code=?, discount_type=?, discount_value=?, " +
                         "expiration_date=?, usage_limit=?, is_active=?, campaign_id=?, product_id=? " +
                         "WHERE coupon_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, coupon.getCode());
            pstmt.setString(2, coupon.getDiscountType());
            pstmt.setDouble(3, coupon.getDiscountValue());
            pstmt.setDate(4, coupon.getExpirationDate());
            
            if (coupon.getUsageLimit() != 0) {
                pstmt.setInt(5, coupon.getUsageLimit());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }
            
            pstmt.setBoolean(6, coupon.isIsActive());
            
            if (coupon.getCampaignId() != null) {
                pstmt.setInt(7, coupon.getCampaignId());
            } else {
                pstmt.setNull(7, Types.INTEGER);
            }
            
            if (coupon.getProductId() != null) {
                pstmt.setInt(8, coupon.getProductId());
            } else {
                pstmt.setNull(8, Types.INTEGER);
            }
            
            pstmt.setInt(9, coupon.getCouponId());
            
            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
            
            pstmt.close();
            conn.close();
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
    
    public Coupon getCouponById(int id) {
        Coupon coupon = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String sql = "SELECT * FROM Coupon WHERE coupon_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                coupon = new Coupon();
                coupon.setCouponId(rs.getInt("coupon_id"));
                coupon.setCode(rs.getString("code"));
                coupon.setDiscountType(rs.getString("discount_type"));
                coupon.setDiscountValue(rs.getDouble("discount_value"));
                coupon.setExpirationDate(rs.getDate("expiration_date"));
                coupon.setUsageLimit(rs.getInt("usage_limit"));
                coupon.setIsActive(rs.getBoolean("is_active"));
                coupon.setCampaignId(rs.getInt("campaign_id"));
                coupon.setProductId(rs.getInt("product_id"));
                coupon.setCreatedAt(rs.getTimestamp("created_at"));
            }
            
            rs.close();
            pstmt.close();
            conn.close();
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
        return coupon;
    }
    
    public boolean deleteCoupon(int couponId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "DELETE FROM Coupon WHERE coupon_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, couponId);

            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;

            pstmt.close();
            conn.close();
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
    
    public Coupon getCouponByCode(String code) {
        Coupon coupon = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT * FROM coupon WHERE code = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, code);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                coupon = new Coupon();
                coupon.setCouponId(rs.getInt("coupon_id"));
                coupon.setCode(rs.getString("code"));
                coupon.setDiscountType(rs.getString("discount_type"));
                coupon.setDiscountValue(rs.getDouble("discount_value"));
                coupon.setExpirationDate(rs.getDate("expiration_date"));
                coupon.setUsageLimit(rs.getInt("usage_limit"));
                coupon.setIsActive(rs.getBoolean("is_active"));
                coupon.setCampaignId(rs.getInt("campaign_id"));
                coupon.setProductId(rs.getInt("product_id"));
                coupon.setCreatedAt(rs.getTimestamp("created_at"));
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
    
    // Additional Validation
    public boolean isCouponValidForUser(int couponId, int userId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean isValid = false;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT c.* FROM Coupon c " +
                        "WHERE c.coupon_id = ? " +
                        "AND c.is_active = true " +
                        "AND (c.expiration_date >= CURRENT_DATE OR c.expiration_date IS NULL) " +
                        "AND (c.usage_limit IS NULL OR c.usage_limit > 0) " +
                        "AND (c.product_id IS NULL OR EXISTS (SELECT 1 FROM Order_Item oi JOIN \"Order\" o ON oi.order_id = o.order_id " +
                        "WHERE o.user_id = ? AND oi.product_id = c.product_id))";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, couponId);
            pstmt.setInt(2, userId);

            ResultSet rs = pstmt.executeQuery();
            isValid = rs.next();

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

        return isValid;
    }
    
    public List<Coupon> getRecentCoupons(int limit) {
        List<Coupon> coupons = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT * FROM Coupon ORDER BY created_at DESC FETCH FIRST ? ROWS ONLY";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, limit);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Coupon coupon = new Coupon();
                coupon.setCouponId(rs.getInt("coupon_id"));
                coupon.setCode(rs.getString("code"));
                coupon.setDiscountType(rs.getString("discount_type"));
                coupon.setDiscountValue(rs.getDouble("discount_value"));
                coupon.setExpirationDate(rs.getDate("expiration_date"));
                coupon.setUsageLimit(rs.getInt("usage_limit"));
                coupon.setIsActive(rs.getBoolean("is_active"));
                coupon.setCampaignId(rs.getInt("campaign_id"));
                coupon.setProductId(rs.getInt("product_id"));
                coupon.setCreatedAt(rs.getTimestamp("created_at"));

                coupons.add(coupon);
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
        return coupons;
    }

    public int getActiveCouponCount() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT COUNT(*) AS coupon_count FROM Coupon WHERE is_active = true AND expiration_date >= CURRENT_DATE";
            pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("coupon_count");
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
        return count;
    }
}