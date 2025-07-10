package model;

import java.sql.Timestamp;
import java.util.List;

public class Order {
    private int orderId;
    private int userId;
    private double totalAmount;
    private Double discountApplied;
    private double finalAmount;
    private Timestamp orderDate;
    private List<OrderItem> items;

    // Constructors
    public Order() {}

    public Order(int userId, double totalAmount, Double discountApplied, 
                double finalAmount, Timestamp orderDate) {
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.discountApplied = discountApplied;
        this.finalAmount = finalAmount;
        this.orderDate = orderDate;
    }

    // Getters and setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    
    public int getUserId() { return userId; }  // Changed from getCustomerId
    public void setUserId(int userId) { this.userId = userId; }  // Changed from setCustomerId
    
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    
    public Double getDiscountApplied() { return discountApplied; }
    public void setDiscountApplied(Double discountApplied) { this.discountApplied = discountApplied; }
    
    public double getFinalAmount() { return finalAmount; }
    public void setFinalAmount(double finalAmount) { this.finalAmount = finalAmount; }
    
    public Timestamp getOrderDate() { return orderDate; }
    public void setOrderDate(Timestamp orderDate) { this.orderDate = orderDate; }
    
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
}