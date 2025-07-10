package controller;

import dao.*;
import model.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private OrderDAO orderDAO;
    private CouponDAO couponDAO;
    private CampaignDAO campaignDAO;
    private ProductDAO productDAO;
    private UserDAO userDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        orderDAO = new OrderDAO();
        couponDAO = new CouponDAO();
        campaignDAO = new CampaignDAO();
        productDAO = new ProductDAO();
        userDAO = new UserDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        int userId = (int) session.getAttribute("userId");
        boolean isAdmin = session.getAttribute("isAdmin") != null && (boolean) session.getAttribute("isAdmin");
        
        try {
            // For all users
            List<Order> recentOrders = orderDAO.getRecentOrders(10);
            request.setAttribute("recentOrders", recentOrders);
            
            // For admin users
            // In the doGet method, update the admin section to include coupon count:
            if (isAdmin) {
                // Sales statistics
                Map<String, Double> salesStats = orderDAO.getSalesStatistics();
                request.setAttribute("salesStats", salesStats);

                // Active campaigns
                List<Campaign> activeCampaigns = campaignDAO.getActiveCampaigns();
                request.setAttribute("activeCampaigns", activeCampaigns);

                // Recent coupons
                List<Coupon> recentCoupons = couponDAO.getRecentCoupons(5);
                request.setAttribute("recentCoupons", recentCoupons);

                // Active coupon count
                int activeCouponCount = couponDAO.getActiveCouponCount();
                request.setAttribute("activeCouponCount", activeCouponCount);

                // Popular products
                List<Product> popularProducts = productDAO.getPopularProducts(5);
                request.setAttribute("popularProducts", popularProducts);

                // User count
                int userCount = userDAO.getUserCount();
                request.setAttribute("userCount", userCount);
            }
            
            request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error loading dashboard data");
            request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
        }
    }
}