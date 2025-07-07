package controller;

import dao.CampaignDAO;
import dao.CouponDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Campaign;
import model.Coupon;

@WebServlet("/CouponManager")
public class CouponManagerServlet extends HttpServlet {
    private CouponDAO couponDAO;
    private CampaignDAO campaignDAO;
    private ProductDAO productDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        couponDAO = new CouponDAO();
        campaignDAO = new CampaignDAO();
        productDAO = new ProductDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // Get all coupons and campaigns
            List<Coupon> coupons = couponDAO.getAllCoupons();
            List<Campaign> campaigns = campaignDAO.getAllCampaigns();
            
            // Set attributes for JSP
            request.setAttribute("coupons", coupons);
            request.setAttribute("campaigns", campaigns);
            
            // Check for messages from other servlets
            String message = (String) request.getSession().getAttribute("message");
            if (message != null) {
                request.setAttribute("message", message);
                request.setAttribute("isError", request.getSession().getAttribute("isError"));
                request.getSession().removeAttribute("message");
                request.getSession().removeAttribute("isError");
            }
            
            // Forward to JSP
            request.getRequestDispatcher("/CouponManager.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error loading coupon manager: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}