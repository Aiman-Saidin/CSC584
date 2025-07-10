package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Get form data
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        // ✅ DEBUG: Print form data to NetBeans output
        System.out.println("---- RegisterServlet Debug ----");
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);

        // ✅ CORRECT Derby connection string
        String jdbcURL = "jdbc:derby://localhost:1527/FoodMartDB;create=true";
        String dbUser = "app"; 
        String dbPassword = "app"; 

        try {
            // ✅ CORRECT Derby driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Insert user data
            String sql = "INSERT INTO users (name, username, password, email, address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, username);
            statement.setString(3, password); // ⚠️ Reminder: hash in real apps!
            statement.setString(4, email);
            statement.setString(5, address);

            int rowsInserted = statement.executeUpdate();
            System.out.println("Rows inserted: " + rowsInserted);

            if (rowsInserted > 0) {
                System.out.println("✅ Insert successful!");
                response.sendRedirect("login.jsp?success=1");
            } else {
                System.out.println("❌ Insert failed: No rows inserted");
                response.sendRedirect("register.jsp?error=insertfail");
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("register.jsp?error=exception");
        }
    }
}
