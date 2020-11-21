package repository;

import view.CreateMenuHandler;
import view.DeleteMenuHandler;
import view.ReadMenuHandler;
import view.UpdateMenuHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepositoryImpl implements CustomerRepository {
    private static Connection connection;

    @Override
    public void create() {
        try {
            connection = DatabaseConnectionCreator.getInstance().getConnection();
            System.out.println("Creating customer...");

            String query = "INSERT customers (name , account, accountstatus) VALUES(?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE name=name , account=account, accountstatus=accountstatus";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, CreateMenuHandler.customerName);
            preparedStmt.setInt(2, CreateMenuHandler.customerAccount.getAccountValue());
            preparedStmt.setString(3, CreateMenuHandler.customerAccount.getAccountStatus().toString());
            preparedStmt.execute();

            System.out.println("Customer successfully created...");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    @Override
    public void read() {
        try {
            connection = DatabaseConnectionCreator.getInstance().getConnection();
            System.out.println("Reading data about customer...");

                String customerToReadSQL = "select customers.id, customers.name, specialties.specialty_name, " +
                        "customers.account, customers.accountstatus from customers \n" +
                        "left join customer_specialties on customers.id=customer_specialties.customer_id\n" +
                        "inner join specialties on customer_specialties.specialty_id=specialties.specialty_id WHERE " +
                        "customers.name = ?";
                PreparedStatement preparedStmt = connection.prepareStatement(customerToReadSQL,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                preparedStmt.setString(1, ReadMenuHandler.customerName);
                ResultSet resultSet = preparedStmt.executeQuery();
                resultSet.next();
                if (!resultSet.next()) {
                    System.out.println("Sorry, customer not found");
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
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update() {
        try {
            connection = DatabaseConnectionCreator.getInstance().getConnection();

            System.out.println("Updating customer...");
            String query = "UPDATE customers SET account=account+? WHERE name=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, UpdateMenuHandler.customerChangeAccountValue);
            preparedStmt.setString(2, UpdateMenuHandler.customerName);
            if (preparedStmt.executeUpdate() > 0)
                System.out.println("Customer successfully updated...");
            else System.out.println("Sorry, customer not found");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete() {
        try {
            connection = DatabaseConnectionCreator.getInstance().getConnection();
            System.out.println("Deleting customer...");

            String query = "DELETE FROM customers WHERE name=?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, DeleteMenuHandler.customerName);
            if (preparedStmt.executeUpdate() > 0)
                System.out.println("Customer successfully deleted...");
            else System.out.println("Sorry, customer not found");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
