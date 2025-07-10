package controller;

import dao.CampaignDAO;
import dao.CouponDAO;
import dao.CouponUsageDAO;
import dao.ProductDAO;
import model.CartItem;
import model.Coupon;
import model.Product;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import model.Campaign;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private ProductDAO productDAO;
    private CouponDAO couponDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new ProductDAO();
        couponDAO = new CouponDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            session.setAttribute("couponMessage", "You need to be logged in to use coupons");
            response.sendRedirect("cart.jsp");
            return;
        }

        if ("add".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            Product product = productDAO.getProductById(productId);

            if (product != null) {
                CartItem item = cart.get(productId);
                if (item == null) {
                    item = new CartItem(product, 1);
                    cart.put(productId, item);
                } else {
                    item.setQuantity(item.getQuantity() + 1);
                }
            }

            // Handle AJAX response
            if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
                response.setContentType("application/json");
                response.getWriter().write("{\"success\": true, \"cartCount\": " + cart.size() + "}");
                return;
            }

            // For non-AJAX requests
            session.setAttribute("cart", cart);
            session.setAttribute("cartCount", cart.size());
            response.sendRedirect("products");
            return;
        } else if ("remove".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            cart.remove(productId);
        } else if ("update".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            
            if (quantity <= 0) {
                cart.remove(productId);
            } else {
                CartItem item = cart.get(productId);
                if (item != null) {
                    item.setQuantity(quantity);
                }
            }
        } else if ("applyCoupon".equals(action)) {
            String couponCode = request.getParameter("couponCode");
            Coupon coupon = couponDAO.getCouponByCode(couponCode);

            if (coupon != null) {
                // Check if coupon is active
                if (!coupon.isIsActive()) {
                    session.setAttribute("couponMessage", "This coupon is no longer active");
                    response.sendRedirect("cart.jsp");
                    return;
                }

                // Check if campaign is active (if coupon belongs to a campaign)
                if (coupon.getCampaignId() != null && coupon.getCampaignId() != 0) {
                    CampaignDAO campaignDAO = new CampaignDAO();
                    Campaign campaign = campaignDAO.getCampaignById(coupon.getCampaignId());
                    if (campaign != null && !campaign.isIsActive()) {
                        session.setAttribute("couponMessage", "The campaign associated with this coupon is not active");
                        response.sendRedirect("cart.jsp");
                        return;
                    }
                }

                // Check usage limit against total usages in COUPONUSAGE table
                if (coupon.getUsageLimit() > 0) {
                    CouponUsageDAO couponUsageDAO = new CouponUsageDAO();
                    int userUsageCount = couponUsageDAO.getCouponUsageCountForUser(coupon.getCouponId(), userId);

                    if (userUsageCount >= coupon.getUsageLimit()) {
                        session.setAttribute("couponMessage", "You have reached your personal usage limit for this coupon");
                        response.sendRedirect("cart.jsp");
                        return;
                    }
                }
                // Check if coupon is expired
                if (coupon.getExpirationDate() != null && 
                    coupon.getExpirationDate().before(new java.util.Date())) {
                    session.setAttribute("couponMessage", "This coupon has expired");
                    response.sendRedirect("cart.jsp");
                    return;
                }

                // Check if coupon is product-specific
                if (coupon.getProductId() != 0) {
                    boolean productInCart = false;
                    for (CartItem item : cart.values()) {
                        if (item.getProduct().getId() == coupon.getProductId()) {
                            productInCart = true;
                            break;
                        }
                    }

                    if (!productInCart) {
                        session.setAttribute("couponMessage", "This coupon is only valid for product ID: " + coupon.getProductId());
                        response.sendRedirect("cart.jsp");
                        return;
                    }
                }

                session.setAttribute("appliedCoupon", coupon);
                session.setAttribute("couponMessage", "Coupon applied successfully!");
            } else {
                session.setAttribute("couponMessage", "Invalid coupon code");
            }
} else if ("removeCoupon".equals(action)) {
            session.removeAttribute("appliedCoupon");
            session.setAttribute("couponMessage", "Coupon removed");
        }

        session.setAttribute("cart", cart);
        session.setAttribute("cartCount", cart.size());
        
        if (!isAjax) {
            response.sendRedirect("cart.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("cart.jsp");
    }
}