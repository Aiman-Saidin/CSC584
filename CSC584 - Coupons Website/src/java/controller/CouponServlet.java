package controller;

import dao.CouponDAO;
import model.Coupon;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/coupon")
public class CouponServlet extends HttpServlet {
    private CouponDAO couponDAO;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    
    @Override
    public void init() throws ServletException {
        super.init();
        couponDAO = new CouponDAO();
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
            int couponId = Integer.parseInt(request.getParameter("coupon_id"));
            if (couponDAO.deleteCoupon(couponId)) {
                message = "Coupon deleted successfully!";
            } else {
                message = "Failed to delete coupon!";
                isError = true;
            }
        } 
        else if ("create".equals(action) || "update".equals(action)) {
                Coupon coupon = new Coupon();
                
                // Handle coupon ID for updates
                if ("update".equals(action)) {
                    String couponId = request.getParameter("coupon_id");
                    if (couponId == null || couponId.isEmpty()) {
                        throw new IllegalArgumentException("Coupon ID is required for update");
                    }
                    coupon.setCouponId(Integer.parseInt(couponId));
                }
                
                // Validate and set coupon code
                String code = request.getParameter("code");
                if (code == null || code.isEmpty()) {
                    throw new IllegalArgumentException("Coupon code is required");
                }
                coupon.setCode(code);
                
                // Validate and set discount type
                String discountType = request.getParameter("discount_type");
                if (discountType == null || !(discountType.equals("PERCENT") || discountType.equals("FIXED"))) {
                    throw new IllegalArgumentException("Invalid discount type");
                }
                coupon.setDiscountType(discountType);
                
                // Validate and set discount value
                String discountValueStr = request.getParameter("discount_value");
                if (discountValueStr == null || discountValueStr.isEmpty()) {
                    throw new IllegalArgumentException("Discount value is required");
                }
                try {
                    double discountValue = Double.parseDouble(discountValueStr);
                    if (discountValue <= 0) {
                        throw new IllegalArgumentException("Discount value must be positive");
                    }
                    if (discountType.equals("PERCENT") && discountValue > 100) {
                        throw new IllegalArgumentException("Percentage discount cannot exceed 100%");
                    }
                    coupon.setDiscountValue(discountValue);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid discount value format");
                }
                
                // Validate and set expiration date
                String expirationDateStr = request.getParameter("expiration_date");
                if (expirationDateStr == null || expirationDateStr.isEmpty()) {
                    throw new IllegalArgumentException("Expiration date is required");
                }
                try {
                    java.util.Date parsedDate = DATE_FORMAT.parse(expirationDateStr);
                    coupon.setExpirationDate(new Date(parsedDate.getTime()));
                } catch (ParseException e) {
                    throw new IllegalArgumentException("Invalid expiration date format. Use YYYY-MM-DD");
                }
                
                // Handle optional usage limit
                String usageLimitStr = request.getParameter("usage_limit");
                if (usageLimitStr != null && !usageLimitStr.isEmpty()) {
                    try {
                        int usageLimit = Integer.parseInt(usageLimitStr);
                        if (usageLimit <= 0) {
                            throw new IllegalArgumentException("Usage limit must be positive");
                        }
                        coupon.setUsageLimit(usageLimit);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid usage limit format");
                    }
                }
                
                // Handle is_active checkbox
                coupon.setIsActive(request.getParameter("is_active") != null);
                
                // Handle optional campaign_id
                String campaignIdStr = request.getParameter("campaign_id");
                if (campaignIdStr != null && !campaignIdStr.isEmpty()) {
                    try {
                        coupon.setCampaignId(Integer.parseInt(campaignIdStr));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid campaign ID");
                    }
                }
                
                // Handle optional product_id
                String productIdStr = request.getParameter("product_id");
                if (productIdStr != null && !productIdStr.isEmpty()) {
                    try {
                        coupon.setProductId(Integer.parseInt(productIdStr));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid product ID");
                    }
                }
                
                // Save or update coupon
                boolean success;
                if ("create".equals(action)) {
                    success = couponDAO.createCoupon(coupon);
                    message = success ? "Coupon created successfully!" : "Failed to create coupon!";
                } else {
                    success = couponDAO.updateCoupon(coupon);
                    message = success ? "Coupon updated successfully!" : "Failed to update coupon!";
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
                int couponId = Integer.parseInt(request.getParameter("coupon_id"));
                Coupon coupon = couponDAO.getCouponById(couponId);

                if (coupon != null) {
                    // Convert to JSON response
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    String json = String.format(
                        "{\"couponId\":%d,\"code\":\"%s\",\"discountType\":\"%s\"," +
                        "\"discountValue\":%.2f,\"expirationDate\":\"%s\",\"usageLimit\":%s," +
                        "\"isActive\":%b,\"campaignId\":%s,\"productId\":%s}",
                        coupon.getCouponId(),
                        escapeJson(coupon.getCode()),
                        escapeJson(coupon.getDiscountType()),
                        coupon.getDiscountValue(),
                        coupon.getExpirationDate(),
                        coupon.getUsageLimit() != null ? coupon.getUsageLimit().toString() : "null",
                        coupon.isIsActive(),
                        coupon.getCampaignId() != null ? coupon.getCampaignId().toString() : "null",
                        coupon.getProductId() != null ? coupon.getProductId().toString() : "null"
                    );

                    response.getWriter().write(json);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Coupon not found");
                }
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private String escapeJson(String input) {
        if (input == null) return "";
        return input.replace("\"", "\\\"")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }
}