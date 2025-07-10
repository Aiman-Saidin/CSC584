package controller;

import dao.CouponDAO;
import dao.CouponUsageDAO;
import dao.OrderDAO;
import model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private OrderDAO orderDAO;
    private CouponDAO couponDAO;
    private CouponUsageDAO couponUsageDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        orderDAO = new OrderDAO();
        couponDAO = new CouponDAO();
        couponUsageDAO = new CouponUsageDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        try {
            // 1. Get cart and user info
            Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
            Integer userId = (Integer) session.getAttribute("userId");
            String username = (String) session.getAttribute("username");
            
            
            if (cart == null || cart.isEmpty()) {
                session.setAttribute("checkoutMessage", "Your cart is empty");
                response.sendRedirect("products");
                return;
            }
            
            if (username == null) {
                session.setAttribute("checkoutMessage", "You must be logged in to checkout");
                response.sendRedirect("cart.jsp");
                return;
            }
            
            // 2. Calculate totals
            double subtotal = calculateSubtotal(cart);
            Coupon appliedCoupon = (Coupon) session.getAttribute("appliedCoupon");
            Double discount = calculateDiscount(cart, appliedCoupon);
            double total = subtotal - (discount != null ? discount : 0);
            
            // 3. Create order
            Order order = new Order();
            order.setUserId(userId);
            order.setTotalAmount(subtotal);
            order.setDiscountApplied(discount);
            order.setFinalAmount(total);
            order.setOrderDate(new Timestamp(System.currentTimeMillis()));
            
            // Convert cart items to order items
            List<OrderItem> orderItems = convertCartToOrderItems(cart);
            
            // 4. Save order to database
            int orderId = orderDAO.createOrder(order, orderItems);
            
            if (orderId == -1) {
                throw new Exception("Failed to create order");
            }
            
            // 5. Record coupon usage if applied
            if (appliedCoupon != null) {
                boolean usageRecorded = couponUsageDAO.recordCouponUsage(
                    appliedCoupon.getCouponId(), 
                    userId, 
                    orderId
                );
                
                if (!usageRecorded) {
                    System.out.println("Failed to record coupon usage");
                }
                
                /* Update coupon usage count
                if (appliedCoupon.getUsageLimit() != null) {
                    couponDAO.updateCouponUsage(appliedCoupon.getCouponId());
                }*/
            }
            
            // 6. Clear cart and coupon from session
            session.removeAttribute("cart");
            session.removeAttribute("appliedCoupon");
            session.removeAttribute("couponMessage");
            session.setAttribute("cartCount", 0);
            
            // 7. Set success message and redirect to index.jsp
            session.setAttribute("checkoutSuccess", "Your Order was placed successfully!");
            response.sendRedirect("index.jsp");
            
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("checkoutMessage", "Error processing order: " + e.getMessage());
            response.sendRedirect("cart.jsp");
        }
    }
    
    private double calculateSubtotal(Map<Integer, CartItem> cart) {
        return cart.values().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }
    
    private Double calculateDiscount(Map<Integer, CartItem> cart, Coupon coupon) {
        if (coupon == null) return null;
        
        double applicableAmount = 0;
        
        // Apply to specific product only if productId is neither null nor 0
        if (coupon.getProductId() != null && coupon.getProductId() != 0) {
            for (CartItem item : cart.values()) {
                if (item.getProduct().getId() == coupon.getProductId()) {
                    applicableAmount += item.getTotalPrice();
                }
            }
        } else {
            // Apply to all products (when PRODUCT_ID is null or 0)
            applicableAmount = calculateSubtotal(cart);
        }
        
        if ("PERCENT".equals(coupon.getDiscountType())) {
            return applicableAmount * coupon.getDiscountValue() / 100;
        } else {
            return Math.min(applicableAmount, coupon.getDiscountValue());
        }
    }
    
    private List<OrderItem> convertCartToOrderItems(Map<Integer, CartItem> cart) {
        List<OrderItem> orderItems = new ArrayList<>();
        
        for (CartItem cartItem : cart.values()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartItem.getProduct().getId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            
            orderItems.add(orderItem);
        }
        
        return orderItems;
    }
}