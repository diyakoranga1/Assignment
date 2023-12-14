package com.sis.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {
    
    private static final String URL = "jdbc:mysql://localhost:3306/SISDB";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "SIYA";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            
            throw new RuntimeException("Error loading JDBC driver");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    
    
}
