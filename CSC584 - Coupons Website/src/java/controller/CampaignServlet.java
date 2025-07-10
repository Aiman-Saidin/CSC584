package controller;

import dao.CampaignDAO;
import model.Campaign;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/campaign")
public class CampaignServlet extends HttpServlet {
    private CampaignDAO campaignDAO;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    
    @Override
    public void init() throws ServletException {
        super.init();
        campaignDAO = new CampaignDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String message = "";
        boolean isError = false;
        
        try {
        if ("delete".equals(action)) {
            // Handle delete action
            int campaignId = Integer.parseInt(request.getParameter("campaign_id"));
            
            if (campaignDAO.deleteCampaign(campaignId)) {
                message = "Campaign deleted successfully!";
            } else {
                message = "Failed to delete campaign!";
                isError = true;
            }
        } 
        else if ("create".equals(action) || "update".equals(action)) {
                Campaign campaign = new Campaign();

                // Handle campaign ID for updates
                if ("update".equals(action)) {
   
                    campaign.setCampaignId(Integer.parseInt(request.getParameter("campaign_id")));

                }
                
                campaign.setName(request.getParameter("name"));
                campaign.setDescription(request.getParameter("description"));
                
                // Parse dates with validation
                String startDateStr = request.getParameter("start_date");
                String endDateStr = request.getParameter("end_date");
                
                if (startDateStr == null || startDateStr.isEmpty() || 
                    endDateStr == null || endDateStr.isEmpty()) {
                    throw new IllegalArgumentException("Start date and end date are required");
                }
                
                try {
                    // Parse and convert to sql.Date
                    java.util.Date parsedStartDate = DATE_FORMAT.parse(startDateStr);
                    java.util.Date parsedEndDate = DATE_FORMAT.parse(endDateStr);
                    
                    campaign.setStartDate(new Date(parsedStartDate.getTime()));
                    campaign.setEndDate(new Date(parsedEndDate.getTime()));
                    
                    // Validate date range
                    if (parsedStartDate.after(parsedEndDate)) {
                        throw new IllegalArgumentException("End date must be after start date");
                    }
                    
                } catch (ParseException e) {
                    throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD format");
                }
                
                campaign.setIsActive(request.getParameter("is_active") != null);
                
                // Save or update campaign
                boolean success;
                if ("create".equals(action)) {
                    success = campaignDAO.createCampaign(campaign);
                    message = success ? "Campaign created successfully!" : "Failed to create campaign!";
                } else {
                    success = campaignDAO.updateCampaign(campaign);
                    message = success ? "Campaign updated successfully!" : "Failed to update campaign!";
                }
                
                if (!success) {
                    isError = true;
                }
            }
        } catch (IllegalArgumentException e) {
            message = "Error: " + e.getMessage();
            isError = true;
        } catch (Exception e) {
            message = "An unexpected error occurred: " + e.getMessage();
            isError = true;
            e.printStackTrace();
        }
        
        request.getSession().setAttribute("message", message);
        request.getSession().setAttribute("isError", isError);

        response.sendRedirect("CouponManager");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("get".equals(action)) {
            try {
                int campaignId = Integer.parseInt(request.getParameter("campaign_id"));
                Campaign campaign = campaignDAO.getCampaignById(campaignId);

                if (campaign != null) {
                    // Convert to JSON response
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    String json = String.format(
                        "{\"campaignId\":%d,\"name\":\"%s\",\"description\":\"%s\"," +
                        "\"startDate\":\"%s\",\"endDate\":\"%s\",\"isActive\":%b}",
                        campaign.getCampaignId(),
                        escapeJson(campaign.getName()),
                        escapeJson(campaign.getDescription()),
                        campaign.getStartDate(),
                        campaign.getEndDate(),
                        campaign.isIsActive()
                    );

                    response.getWriter().write(json);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Campaign not found");
                }
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private String escapeJson(String input) {
        return input.replace("\"", "\\\"")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }
    
    
}