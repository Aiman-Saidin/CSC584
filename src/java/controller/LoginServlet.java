package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // ✅ DEBUG: Check what you got from the form
        System.out.println("---- LoginServlet Debug ----");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        try {
            // ✅ DEBUG: Load Derby driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            // ✅ Use Derby, not MySQL
            Connection con = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/FoodMartDB;create=true", "app", "app");

            System.out.println("Connected to DB!"); // ✅ Connection success

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);

            System.out.println("Query ready, executing..."); // ✅ Before query runs

            ResultSet rs = ps.executeQuery();

            System.out.println("Query executed, checking results...");

            if(rs.next()) {
                System.out.println("✅ User found! Login success.");
                int userId = rs.getInt("id"); 
                HttpSession session = request.getSession();
                session.setAttribute("userId", userId); 
                session.setAttribute("username", username);
                response.sendRedirect("index.jsp");
            } else {
                System.out.println("❌ User not found! Redirecting back to login.");
                response.sendRedirect("login.jsp?error");
            }
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
