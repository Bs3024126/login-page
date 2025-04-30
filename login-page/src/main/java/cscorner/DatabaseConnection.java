
package cscorner;

import java.sql.*;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/login";
        String username = "hi";  
        String password = "name";  

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // For MySQL 8+
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found", e);
        }
    }
}
