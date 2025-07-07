package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Coupon implements Serializable {
    private int couponId;
    private String code;
    private String discountType;
    private double discountValue;
    private Date expirationDate;
    private Integer usageLimit;
    private boolean isActive;
    private Integer campaignId;
    private Integer productId;
    private Timestamp createdAt;
    
    // Constructors
    public Coupon() {}
    
    public Coupon(int couponId, String code, String discountType, double discountValue,
                Date expirationDate, Integer usageLimit, boolean isActive,
                Integer campaignId, Integer productId, Timestamp createdAt) {
        this.couponId = couponId;
        this.code = code;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.expirationDate = expirationDate;
        this.usageLimit = usageLimit;
        this.isActive = isActive;
        this.campaignId = campaignId;
        this.productId = productId;
        this.createdAt = createdAt;
    }
    
    // Getters and setters for all fields
    public int getCouponId() { return couponId; }
    public void setCouponId(int couponId) { this.couponId = couponId; }
    
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    public String getDiscountType() { return discountType; }
    public void setDiscountType(String discountType) { this.discountType = discountType; }
    
    public double getDiscountValue() { return discountValue; }
    public void setDiscountValue(double discountValue) { this.discountValue = discountValue; }
    
    public Date getExpirationDate() { return expirationDate; }
    public void setExpirationDate(Date expirationDate) { this.expirationDate = expirationDate; }
    
    public Integer getUsageLimit() { return usageLimit; }
    public void setUsageLimit(Integer usageLimit) { this.usageLimit = usageLimit; }
    
    public boolean isIsActive() { return isActive; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }
    
    public Integer getCampaignId() { return campaignId; }
    public void setCampaignId(Integer campaignId) { this.campaignId = campaignId; }
    
    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }
    
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}