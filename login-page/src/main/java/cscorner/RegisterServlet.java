package cscorner;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RegisterServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Set the content type
        response.setContentType("text/html;charset=UTF-8");
        
        // Get the form parameters
        String username = request.getParameter("username");
        String email = request.getParameter("useremail");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("Confirmpassword");
        String mobileNo = request.getParameter("mobileNo");
        
        // Initialize writer
        PrintWriter out = response.getWriter();
        
        try {
            // Simple validation
            if (username == null || username.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                mobileNo == null || mobileNo.trim().isEmpty() ||
                confirmPassword == null || confirmPassword.trim().isEmpty()) {
                out.println("<html><body><h3>All fields are required!</h3></body></html>");
                return;
            }

            if (!password.equals(confirmPassword)) {
                out.println("<html><body><h3>Password and Confirm Password do not match!</h3></body></html>");
                return;
            }
            
            // Example email validation (regular expression)
            String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            if (!email.matches(emailPattern)) {
                out.println("<html><body><h3>Invalid email format!</h3></body></html>");
                return;
            }

            // Example mobile number validation (should be adjusted for your region)
            if (mobileNo.length() != 10) {
                out.println("<html><body><h3>Mobile Number should be 10 digits!</h3></body></html>");
                return;
            }

         
            out.println("<html><body>");
            out.println("<h3>Registration Successful!</h3>");
            out.println("<p>Username: " + username + "</p>");
            out.println("<p>Email: " + email + "</p>");
            out.println("<p>Mobile Number: " + mobileNo + "</p>");
            out.println("<p><a href='auth-login.html'>Go to Login Page</a></p>");
            out.println("</body></html>");

            // Optionally, redirect the user to the login page after successful registration
            // response.sendRedirect("login.html");

        } catch (Exception e) {
            out.println("<html><body><h3>Error: " + e.getMessage() + "</h3></body></html>");
        }
    }
}
