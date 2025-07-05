package controller;

import dao.CouponDAO;
import model.Coupon;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/applyCoupon")
public class CouponServlet extends HttpServlet {
    private CouponDAO couponDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        couponDAO = new CouponDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String couponCode = request.getParameter("couponCode");
        HttpSession session = request.getSession();
        
        Coupon coupon = couponDAO.getCouponByCode(couponCode);
        
        if (coupon != null) {
            session.setAttribute("appliedCoupon", coupon);
            session.setAttribute("couponMessage", "Coupon applied successfully!");
        } else {
            session.setAttribute("couponMessage", "Invalid coupon code!");
        }
        
        response.sendRedirect("cart.jsp");
    }
}