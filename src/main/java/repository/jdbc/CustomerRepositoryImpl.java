package repository.jdbc;

import repository.CustomerRepository;
import repository.ConnectionFactory;
import view.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepositoryImpl implements CustomerRepository {

    public static final String CREATE_CUSTOMER_QUERY = "INSERT customers (name) VALUES(?) " +
            "ON DUPLICATE KEY UPDATE name=name";

    public static final String READ_CUSTOMER_QUERY = "select customers.id, customers.name, specialties.specialty_name, " +
            "accounts.account_value,  accounts.account_status from customers" +
            " left join customer_specialties on customers.id=customer_specialties.customer_id" +
            " inner join specialties  on customer_specialties.specialty_id=specialties.specialty_id" +
            " left join customer_accounts on customers.id=customer_accounts.customer_id" +
            " inner join accounts  on customer_accounts.account_id=accounts.account_id" +
            " where customers.name =? order by id";

    public static final String FIND_ACCOUNT_BY_CUSTOMER_QUERY = "select accounts.account_id from customers" +
            " left join customer_specialties on customers.id=customer_specialties.customer_id" +
            " inner join specialties  on customer_specialties.specialty_id=specialties.specialty_id" +
            " left join customer_accounts on customers.id=customer_accounts.customer_id" +
            " inner join accounts  on customer_accounts.account_id=accounts.account_id" +
            " where customers.name =? order by id";

    public static final String UPDATE_CUSTOMER_QUERY = "UPDATE accounts SET account_value=account_value+? WHERE account_id=?";
    public static final String DELETE_CUSTOMER_QUERY = "DELETE FROM customers WHERE name=?";

    @Override
    public void create() {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            System.out.println("Creating customer...");
            PreparedStatement preparedStmt = connection.prepareStatement(CREATE_CUSTOMER_QUERY);
            preparedStmt.setString(1, CustomerView.customerName);
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
            PreparedStatement preparedStmt = connection.prepareStatement(READ_CUSTOMER_QUERY,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            preparedStmt.setString(1, CustomerView.customerName);
            ResultSet resultSet = preparedStmt.executeQuery();
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
    Long accountId;
    @Override
    public void update() {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            System.out.println("Updating customer...");

            PreparedStatement preparedStmt = connection.prepareStatement(FIND_ACCOUNT_BY_CUSTOMER_QUERY,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            preparedStmt.setString(1, CustomerView.customerName);
            ResultSet resultSet = preparedStmt.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Sorry, customer with name <" + CustomerView.customerName + ">, " +
                        " not found");
            } else {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    accountId = resultSet.getLong(1);
                }

                PreparedStatement preparedStmtUpdate = connection.prepareStatement(UPDATE_CUSTOMER_QUERY);
                preparedStmtUpdate.setLong(1, CustomerView.customerChangeAccountValue);
                preparedStmtUpdate.setLong(2, accountId);
                if (preparedStmtUpdate.executeUpdate() > 0)
                    System.out.println("Customer successfully updated...");
                else System.out.println("Sorry, customer not found");
            }
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
