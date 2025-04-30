package com.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cscorner.DatabaseConnection;

public class UserDAO {

    // Method to insert user into the database
    public static boolean registerUser(String username, String email, String password, String mobileNo) {
        String sql = "INSERT INTO users (username, email, password, mobileNo) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password); // Store password as is, but better to hash it
            stmt.setString(4, mobileNo);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
