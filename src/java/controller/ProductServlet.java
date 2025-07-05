package controller;

import dao.ProductDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CartItem;
import model.Product;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new ProductDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String category = request.getParameter("category");
        List<Product> products;
        
        if (category != null && !category.isEmpty()) {
            products = productDAO.getProductsByCategory(category);
        } else {
            products = productDAO.getAllProducts();
        }
        
        request.setAttribute("products", products);
        
        // Get cart count for display
        HttpSession session = request.getSession(false);
        List<CartItem> cart = (session != null) ? (List<CartItem>) session.getAttribute("cart") : null;
        int cartCount = (cart != null) ? cart.size() : 0;
        request.setAttribute("cartCount", cartCount);
        
        request.getRequestDispatcher("/order.jsp").forward(request, response);
    }
}