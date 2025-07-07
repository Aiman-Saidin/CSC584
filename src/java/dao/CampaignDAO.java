package dao;

import model.Campaign;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CampaignDAO {
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String DB_URL = "jdbc:derby://localhost:1527/FoodMartDB";
    private static final String USER = "app";
    private static final String PASS = "app";
    
    public List<Campaign> getAllCampaigns() {
        List<Campaign> campaigns = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            
            String sql = "SELECT * FROM Campaign ORDER BY start_date DESC";
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Campaign campaign = new Campaign();
                campaign.setCampaignId(rs.getInt("campaign_id"));
                campaign.setName(rs.getString("name"));
                campaign.setDescription(rs.getString("description"));
                campaign.setStartDate(rs.getDate("start_date"));
                campaign.setEndDate(rs.getDate("end_date"));
                campaign.setIsActive(rs.getBoolean("is_active"));
                
                campaigns.add(campaign);
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
        return campaigns;
    }
    
    public boolean createCampaign(Campaign campaign) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String sql = "INSERT INTO Campaign (name, description, start_date, end_date, is_active) " +
                         "VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, campaign.getName());
            pstmt.setString(2, campaign.getDescription());
            pstmt.setDate(3, campaign.getStartDate());
            pstmt.setDate(4, campaign.getEndDate());
            pstmt.setBoolean(5, campaign.isIsActive());
            
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
    
    public boolean updateCampaign(Campaign campaign) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String sql = "UPDATE Campaign SET name=?, description=?, start_date=?, " +
                         "end_date=?, is_active=? WHERE campaign_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, campaign.getName());
            pstmt.setString(2, campaign.getDescription());
            pstmt.setDate(3, campaign.getStartDate());
            pstmt.setDate(4, campaign.getEndDate());
            pstmt.setBoolean(5, campaign.isIsActive());
            pstmt.setInt(6, campaign.getCampaignId());
            
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
    
    public Campaign getCampaignById(int id) {
        Campaign campaign = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String sql = "SELECT * FROM Campaign WHERE campaign_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                campaign = new Campaign();
                campaign.setCampaignId(rs.getInt("campaign_id"));
                campaign.setName(rs.getString("name"));
                campaign.setDescription(rs.getString("description"));
                campaign.setStartDate(rs.getDate("start_date"));
                campaign.setEndDate(rs.getDate("end_date"));
                campaign.setIsActive(rs.getBoolean("is_active"));
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
        return campaign;
    }
    
    public boolean deleteCampaign(int campaignId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "DELETE FROM Campaign WHERE campaign_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, campaignId);

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
}