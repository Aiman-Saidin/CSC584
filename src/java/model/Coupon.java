package model;

import java.io.Serializable;

public class Coupon implements Serializable {
    private String code;
    private double discountValue;
    private String discountType; // "PERCENT" or "FIXED"
    
    public Coupon() {}
    
    public Coupon(String code, double discountValue, String discountType) {
        this.code = code;
        this.discountValue = discountValue;
        this.discountType = discountType;
    }
    
    // Getters and Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    public double getDiscountValue() { return discountValue; }
    public void setDiscountValue(double discountValue) { this.discountValue = discountValue; }
    
    public String getDiscountType() { return discountType; }
    public void setDiscountType(String discountType) { this.discountType = discountType; }
    
    public double applyDiscount(double total) {
        if ("PERCENT".equals(discountType)) {
            return total * (1 - (discountValue / 100));
        } else if ("FIXED".equals(discountType)) {
            return Math.max(0, total - discountValue);
        }
        return total;
    }
}