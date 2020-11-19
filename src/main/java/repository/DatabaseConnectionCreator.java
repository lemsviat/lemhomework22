package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionCreator {

    private static DatabaseConnectionCreator instance;
    private Connection connection;

    static final String DATABASE_URL = DatabasePropertiesReader.getProperties("DATABASE_URL");
    static final String JDBC_DRIVER = DatabasePropertiesReader.getProperties("JDBC_DRIVER");
    static final String USER = DatabasePropertiesReader.getProperties("USER");
    static final String PASSWORD = DatabasePropertiesReader.getProperties("PASSWORD");

    private DatabaseConnectionCreator() throws SQLException {
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

    public static DatabaseConnectionCreator getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnectionCreator();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnectionCreator();
        }

        return instance;
    }
}