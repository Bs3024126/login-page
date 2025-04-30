package com.registration;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class UserRegistrationServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Method to handle POST requests from the registration form
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Step 1: Get data from the HTML registration form
        String username = request.getParameter("username");
        String email = request.getParameter("useremail");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String mobileNo = request.getParameter("mobileNo");

        // Step 2: Check if passwords match
        if (!password.equals(confirmPassword)) {
            response.getWriter().write("Passwords do not match.");
            return;
        }

        // Step 3: Call UserDAO to insert user data into the database
        boolean isRegistered = UserDAO.registerUser(username, email, password, mobileNo);

        // Step 4: Send response back to the user
        if (isRegistered) {
            response.getWriter().write("Registration successful.");
        } else {
            response.getWriter().write("Registration failed. Please try again.");
        }
    }
}
