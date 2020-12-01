package repository.jdbc;

import repository.AccountRepository;
import repository.ConnectionFactory;
import view.AccountView;
import view.CustomerView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepositoryImpl implements AccountRepository {
    public static final String CREATE_ACCOUNT_QUERY = "INSERT `lem-study`.accounts(account_value, account_status) VALUES(?, ?) " +
            "ON DUPLICATE KEY UPDATE account_value=account_value, account_status=account_status";

    public static final String FIND_CUSTOMER_NAME_BY_ID_QUERY = "SELECT id FROM `lem-study`.customers WHERE customers.name = ?";

    public static final String FIND_ACCOUNT_BY_ID_QUERY = "SELECT MAX(account_id) FROM `lem-study`.accounts WHERE account_value = ?";

    public static final String INSERT_INTO_JOINED_TABLE_QUERY = "INSERT `lem-study`.customer_accounts (customer_id, account_id) VALUES(?, ?) ";

    @Override
    public void create() {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            System.out.println("Creating account...");
            PreparedStatement preparedStmt = connection.prepareStatement(CREATE_ACCOUNT_QUERY);
            preparedStmt.setLong(1, AccountView.customerAccount.getAccountValue());
            preparedStmt.setString(2, AccountView.customerAccount.getAccountStatus().toString());
            preparedStmt.execute();

            PreparedStatement preparedStmt1 = connection.prepareStatement(FIND_CUSTOMER_NAME_BY_ID_QUERY);
            preparedStmt1.setString(1, CustomerView.customerName);
            ResultSet resultSet1 = preparedStmt1.executeQuery();
            resultSet1.next();
            int customerIdToJoin = resultSet1.getInt(1);

            //System.out.println("Selecting accountIdToJoin...");
            PreparedStatement preparedStmt2 = connection.prepareStatement(FIND_ACCOUNT_BY_ID_QUERY);
            preparedStmt2.setLong(1, AccountView.customerAccount.getAccountValue());
            ResultSet resultSet2 = preparedStmt2.executeQuery();
            resultSet2.next();
            int specialtyIdToJoin = resultSet2.getInt(1);

            PreparedStatement preparedStmtJoinedTable;

            preparedStmtJoinedTable = connection.prepareStatement(INSERT_INTO_JOINED_TABLE_QUERY);
            preparedStmtJoinedTable.setInt(1, customerIdToJoin);
            preparedStmtJoinedTable.setInt(2, specialtyIdToJoin);

            preparedStmtJoinedTable.execute();

            System.out.println("Joining the customer's data from table of customers and table of accounts has completed");


            System.out.println("Account successfully created...");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void read() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
