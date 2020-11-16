package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    static final String DATABASE_URL = DatabaseProperties.getProperties("DATABASE_URL");
    static final String JDBC_DRIVER = DatabaseProperties.getProperties("JDBC_DRIVER");
    static final String USER = DatabaseProperties.getProperties("USER");;
    static final String PASSWORD = DatabaseProperties.getProperties("PASSWORD");

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Creating connection to database...");
            this.connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Database Connection Creation Failed : " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }

        return instance;
    }
}