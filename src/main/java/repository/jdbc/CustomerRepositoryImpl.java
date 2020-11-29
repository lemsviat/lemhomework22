package repository.jdbc;

import repository.CustomerRepository;
import repository.ConnectionFactory;
import view.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepositoryImpl implements CustomerRepository {

    public static final String CREATE_CUSTOMER_QUERY = "INSERT customers (name , account, accountstatus) VALUES(?, ?, ?) " +
            "ON DUPLICATE KEY UPDATE name=name, account=account, accountstatus=accountstatus";
    public static final String READ_CUSTOMER_QUERY = "select customers.id, customers.name, specialties.specialty_name, " +
            "customers.account, customers.accountstatus from customers \n" +
            "left join customer_specialties on customers.id=customer_specialties.customer_id\n" +
            "inner join specialties on customer_specialties.specialty_id=specialties.specialty_id WHERE " +
            "customers.name = ? order by id";
    public static final String UPDATE_CUSTOMER_QUERY = "UPDATE customers SET account=account+? WHERE name=?";
    public static final String DELETE_CUSTOMER_QUERY = "DELETE FROM customers WHERE name=?";

    @Override
    public void create() {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            System.out.println("Creating customer...");
           /* final String query = "INSERT customers (name , account, accountstatus) VALUES(?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE name=name , account=account, accountstatus=accountstatus";*/
            PreparedStatement preparedStmt = connection.prepareStatement(CREATE_CUSTOMER_QUERY);
            preparedStmt.setString(1, CustomerView.customerName);
            preparedStmt.setInt(2, CustomerView.customerAccount.getAccountValue());
            preparedStmt.setString(3, CustomerView.customerAccount.getAccountStatus().toString());
            preparedStmt.execute();

            System.out.println("Customer successfully created...");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void read() {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            System.out.println("Reading data about customer...");
           /* final String customerToReadSQL = "select customers.id, customers.name, specialties.specialty_name, " +
                    "customers.account, customers.accountstatus from customers \n" +
                    "left join customer_specialties on customers.id=customer_specialties.customer_id\n" +
                    "inner join specialties on customer_specialties.specialty_id=specialties.specialty_id WHERE " +
                    "customers.name = ? order by id";*/
            PreparedStatement preparedStmt = connection.prepareStatement(READ_CUSTOMER_QUERY,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            preparedStmt.setString(1, CustomerView.customerName);
            ResultSet resultSet = preparedStmt.executeQuery();
            //resultSet.next();
            if (!resultSet.next()) {
                System.out.println("Sorry, customer with name <" + CustomerView.customerName + ">, " +
                        " not found");
            } else {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    String specialty = resultSet.getString(3);
                    int account = resultSet.getInt(4);
                    String accountStatus = resultSet.getString(5);

                    System.out.println("id: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Specialty: " + specialty);
                    System.out.println("Account: " + account);
                    System.out.println("AccountStatus: " + accountStatus);
                    System.out.println("===========================");
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update() {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            System.out.println("Updating customer...");
           // final String query = "UPDATE customers SET account=account+? WHERE name=?";
            PreparedStatement preparedStmt = connection.prepareStatement(UPDATE_CUSTOMER_QUERY);
            preparedStmt.setInt(1, CustomerView.customerChangeAccountValue);
            preparedStmt.setString(2, CustomerView.customerName);
            if (preparedStmt.executeUpdate() > 0)
                System.out.println("Customer successfully updated...");
            else System.out.println("Sorry, customer not found");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete() {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            System.out.println("Deleting customer...");

            //final String query = "DELETE FROM customers WHERE name=?";
            PreparedStatement preparedStmt = connection.prepareStatement(DELETE_CUSTOMER_QUERY);
            preparedStmt.setString(1, CustomerView.customerName);
            if (preparedStmt.executeUpdate() > 0)
                System.out.println("Customer successfully deleted...");
            else System.out.println("Sorry, customer not found");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
