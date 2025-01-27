package configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/abc";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Private constructor to prevent instantiation
    private DBConnection() {
        // Private constructor to prevent instantiation outside
    }

    // Static method to get a new database connection
    public static Connection getNewConnection() {
        Connection connection = null;
        try {
        	 // Load MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Establish connection
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Static method to close database connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
