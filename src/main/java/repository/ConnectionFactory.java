package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static ConnectionFactory instance;
    private Connection connection;

    static final String DATABASE_URL = DatabasePropertiesReader.getProperties("DATABASE_URL");
    static final String JDBC_DRIVER = DatabasePropertiesReader.getProperties("JDBC_DRIVER");
    static final String USER = DatabasePropertiesReader.getProperties("USER");
    static final String PASSWORD = DatabasePropertiesReader.getProperties("PASSWORD");

    private ConnectionFactory() throws SQLException {
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

    public static ConnectionFactory getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnectionFactory();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConnectionFactory();
        }

        return instance;
    }
    public static void closeConnection(){
        try {
            instance.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}