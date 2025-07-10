package dao;

import model.Order;
import model.OrderItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String DB_URL = "jdbc:derby://localhost:1527/FoodMartDB";
    private static final String USER = "app";
    private static final String PASS = "app";
    
    public int createOrder(Order order, List<OrderItem> items) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int orderId = -1;
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false); // Start transaction
            
            // 1. Create the order
            String orderSql = "INSERT INTO \"Order\" (user_id, total_amount, discount_applied, final_amount) " +
                             "VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, order.getUserId());
            pstmt.setDouble(2, order.getTotalAmount());
            
            if (order.getDiscountApplied() != null) {
                pstmt.setDouble(3, order.getDiscountApplied());
            } else {
                pstmt.setNull(3, Types.DOUBLE);
            }
            
            pstmt.setDouble(4, order.getFinalAmount());
            pstmt.executeUpdate();
            
            // Get the generated order ID
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
            
            // 2. Create order items
            if (orderId != -1 && items != null && !items.isEmpty()) {
                String itemSql = "INSERT INTO Order_Item (order_id, product_id, quantity, price) " +
                               "VALUES (?, ?, ?, ?)";
                pstmt = conn.prepareStatement(itemSql);
                
                for (OrderItem item : items) {
                    pstmt.setInt(1, orderId);
                    pstmt.setInt(2, item.getProductId());
                    pstmt.setInt(3, item.getQuantity());
                    pstmt.setDouble(4, item.getPrice());
                    pstmt.addBatch();
                }
                
                pstmt.executeBatch();
            }
            
            conn.commit(); // Commit transaction
        } catch (SQLException se) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
        return orderId;
    }
    
    public List<Order> getOrdersByCustomer(int userId) {
        List<Order> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String sql = "SELECT * FROM \"Order\" WHERE user_id = ? ORDER BY order_date DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setDiscountApplied(rs.getDouble("discount_applied"));
                order.setFinalAmount(rs.getDouble("final_amount"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                
                orders.add(order);
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
        
        return orders;
    }
    
    public Order getOrderById(int orderId) {
        Order order = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            String sql = "SELECT * FROM \"Order\" WHERE order_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setDiscountApplied(rs.getDouble("discount_applied"));
                order.setFinalAmount(rs.getDouble("final_amount"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                
                // Get order items
                order.setItems(getOrderItems(conn, orderId));
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
        
        return order;
    }
    
    private List<OrderItem> getOrderItems(Connection conn, int orderId) throws SQLException {
        List<OrderItem> items = new ArrayList<>();
        PreparedStatement pstmt = null;
        
        try {
            String sql = "SELECT * FROM Order_Item WHERE order_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setItemId(rs.getInt("item_id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                
                items.add(item);
            }
            
            rs.close();
        } finally {
            if (pstmt != null) pstmt.close();
        }
        
        return items;
    }
}