package cscorner;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Handles GET requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // This redirects to the login page (optional)
        response.sendRedirect("auth-login.html");
    }

    // Handles POST requests for login
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set content type to HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve the username and password entered by the user
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if username or password is empty
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            out.println("Username and password must not be empty.");
            return;
        }

        // Query to check if the username and password match
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        // Establish connection and validate the user
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            // Set parameters for the prepared statement
            ps.setString(1, username);
            ps.setString(2, password);

            // Execute the query
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // User exists and login is successful
                    response.sendRedirect("index.jsp");  // Redirect to dashboard or home page
                } else {
                    // User does not exist or invalid credentials
                    response.sendRedirect("auth-login.html?error=Invalid credentials");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(out);
            out.println("Database error: " + e.getMessage());
        }
    }
}
