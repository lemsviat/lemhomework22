package repository;

import controller.CreateMenuHandler;
import controller.DeleteMenuHandler;
import controller.ReadMenuHandler;
import controller.UpdateMenuHandler;

import java.sql.*;

public class MySQLBase {
    static final String DATABASE_URL = "jdbc:mysql://localhost/lem-study?useUnicode=true&serverTimezone=UTC";
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String USER = "root";
    static final String PASSWORD = "Dev911Base!@#";

    public static void createTable() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JDBC_DRIVER);

            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            System.out.println("Creating table in selected database...");
            statement = connection.createStatement();

            String SQL = "CREATE TABLE customers " +
                    "(id INTEGER not NULL," +
                    " name VARCHAR(50), " +
                    " specialty VARCHAR (50), " +
                    " account INTEGER not NULL, " +
                    " PRIMARY KEY (id))";

            statement.executeUpdate(SQL);
            System.out.println("Table successfully created...");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void createCustomer() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            //System.out.println("Registering JDBC driver...");
            //Class.forName(JDBC_DRIVER);

            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            System.out.println("Creating customer...");
            //statement = connection.createStatement();
            //String SQL = "INSERT customers (name , specialty, account, accountstatus) VALUES ('Petro','Java, Spring',1000,'ACTIVE')";
            //statement.executeUpdate(SQL);

            String query = "INSERT customers (name , specialty, account, accountstatus)VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, CreateMenuHandler.customerName);
            preparedStmt.setString(2, CreateMenuHandler.firstSpeciality.toString() + ", "
                    + CreateMenuHandler.secondSpeciality.toString() + ", " + CreateMenuHandler.thirdSpeciality.toString());
            preparedStmt.setInt(3, CreateMenuHandler.customerAccount.getAccountValue());
            preparedStmt.setString(4, CreateMenuHandler.customerAccount.getAccountStatus().toString());
            preparedStmt.execute();

            System.out.println("Customer successfully created...");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        //System.out.println("I created customer in MySQL database");
    }

    public static void readCustomer() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            System.out.println("Reading data about customer...");

            String query = "SELECT * FROM customers WHERE name=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, ReadMenuHandler.customerName);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    String specialty = resultSet.getString(3);
                    int account = resultSet.getInt(4);
                    String accountstatus = resultSet.getString(5);

                    System.out.println("id: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Specialty: " + specialty);
                    System.out.println("Account: " + account);
                    System.out.println("AccountStatus: " + accountstatus);
                    System.out.println("===========================");
                }
            //System.out.println("Sorry, customer not found");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


    public static void updateCustomer() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            System.out.println("Updating customer...");

            String query = "UPDATE customers SET account=account+? WHERE name=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, UpdateMenuHandler.customerChangeAccountValue);
            preparedStmt.setString(2, UpdateMenuHandler.customerName);
            if (preparedStmt.executeUpdate() > 0)
                System.out.println("Customer successfully updated...");
            else System.out.println("Sorry, customer not found");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


    public static void deleteCustomer() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            System.out.println("Deleting customer...");

            String query = "DELETE FROM customers WHERE name=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, DeleteMenuHandler.customerName);
            if (preparedStmt.executeUpdate() > 0)
                System.out.println("Customer successfully deleted...");
            else System.out.println("Sorry, customer not found");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
