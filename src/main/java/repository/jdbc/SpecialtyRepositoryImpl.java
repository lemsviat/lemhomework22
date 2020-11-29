package repository.jdbc;

import repository.ConnectionFactory;
import repository.SpecialtyRepository;
import view.CreateMenuHandler;
import view.CustomerView;
import view.SpecialtyView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialtyRepositoryImpl implements SpecialtyRepository {
    SpecialtyView specialtyView = new SpecialtyView();

    @Override
    public void create() {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            System.out.println("Creating specialty of the customer...");
            final String query = "INSERT specialties (specialty_name) VALUES(?) " +
                    "ON DUPLICATE KEY UPDATE specialty_name=specialty_name";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            String specialties = specialtyView.create().toString();
            preparedStmt.setString(1, specialties);
            preparedStmt.execute();
            System.out.println("Specialty successfully created...");

            //System.out.println("Selecting customerIdToJoin...");
            final String customerIdToJoinSQL = "SELECT id FROM customers WHERE customers.name = ?";
            PreparedStatement preparedStmt1 = connection.prepareStatement(customerIdToJoinSQL);
            preparedStmt1.setString(1, CustomerView.customerName);
            ResultSet resultSet1 = preparedStmt1.executeQuery();
            resultSet1.next();
            int customerIdToJoin = resultSet1.getInt(1);

            //System.out.println("Selecting specialtyIdToJoin...");
            final String specialtyIdToJoinSQL = "SELECT specialty_id FROM specialties WHERE specialty_name = ?";
            PreparedStatement preparedStmt2 = connection.prepareStatement(specialtyIdToJoinSQL);
            preparedStmt2.setString(1, specialties);
            ResultSet resultSet2 = preparedStmt2.executeQuery();
            resultSet2.next();
            int specialtyIdToJoin = resultSet2.getInt(1);

            final String queryJoinedTable1 = "INSERT customer_specialties (customer_id, specialty_id) VALUES(?, ?) " +
                    "ON DUPLICATE KEY UPDATE customer_id=customer_id,specialty_id=specialty_id";
            final String queryJoinedTable2 = "UPDATE customer_specialties SET customer_id=?, specialty_id=? ";
                    //"ON DUPLICATE KEY UPDATE customer_id=customer_id,specialty_id=specialty_id";

            final String checkDuplicateSQL = new StringBuilder()
                    .append("SELECT \n").append("    COUNT(customer_specialties.customer_id)\n")
                    .append("FROM\n").append("    customer_specialties\n")
                    .append("WHERE\n").append("    customer_id = ?").toString();
            PreparedStatement preparedStmtCheckDuplicate = connection.prepareStatement(checkDuplicateSQL);
            preparedStmtCheckDuplicate.setInt(1, customerIdToJoin);
            ResultSet resultSet3 = preparedStmtCheckDuplicate.executeQuery();
            resultSet3.next();
            int checkDuplicate = resultSet3.getInt(1);

            PreparedStatement preparedStmtJoinedTable;
            if (checkDuplicate > 0) {
                preparedStmtJoinedTable = connection.prepareStatement(queryJoinedTable2);
            } else {
                preparedStmtJoinedTable = connection.prepareStatement(queryJoinedTable1);
            }
            preparedStmtJoinedTable.setInt(1, customerIdToJoin);
            preparedStmtJoinedTable.setInt(2, specialtyIdToJoin);
            preparedStmtJoinedTable.execute();

            System.out.println("Joining the customer's data from table of customers and table of specialties has completed");

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
