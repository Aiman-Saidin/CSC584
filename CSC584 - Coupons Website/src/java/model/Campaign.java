package model;

import java.io.Serializable;
import java.sql.Date;

public class Campaign implements Serializable {
    private int campaignId;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private boolean isActive;
    
    // Constructors, getters, and setters
    public Campaign() {}
    
    public Campaign(int campaignId, String name, String description, 
                   Date startDate, Date endDate, boolean isActive) {
        this.campaignId = campaignId;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }
    
    // Getters and setters for all fields
    public int getCampaignId() { return campaignId; }
    public void setCampaignId(int campaignId) { this.campaignId = campaignId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    
    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
    
    public boolean isIsActive() { return isActive; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }
}