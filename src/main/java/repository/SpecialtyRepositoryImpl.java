package repository;

import view.CreateMenuHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialtyRepositoryImpl implements SpecialtyRepository {
    private static Connection connection;

    @Override
    public void create() {
        try {
            connection = DatabaseConnectionCreator.getInstance().getConnection();
            System.out.println("Creating specialty of the customer...");
            String query = "INSERT specialties (specialty_name) VALUES(?) " +
                    "ON DUPLICATE KEY UPDATE specialty_name=specialty_name";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            String specialties=CreateMenuHandler.firstSpeciality.toString() + ", "
                    + CreateMenuHandler.secondSpeciality.toString() + ", " + CreateMenuHandler.thirdSpeciality.toString();
            preparedStmt.setString(1, specialties);
            preparedStmt.execute();
            System.out.println("Specialty successfully created...");

            //System.out.println("Selecting customerIdToJoin...");
            String customerIdToJoinSQL="SELECT id FROM customers WHERE customers.name = ?";
            PreparedStatement preparedStmt1 = connection.prepareStatement(customerIdToJoinSQL);
            preparedStmt1.setString(1,CreateMenuHandler.customerName);
            ResultSet resultSet1 = preparedStmt1.executeQuery();
            resultSet1.next();
            int customerIdToJoin=resultSet1.getInt(1);

            //System.out.println("Selecting specialtyIdToJoin...");
            String specialtyIdToJoinSQL="SELECT specialty_id FROM specialties WHERE specialty_name = ?";
            PreparedStatement preparedStmt2 = connection.prepareStatement(specialtyIdToJoinSQL);
            preparedStmt2.setString(1,specialties);
            ResultSet resultSet2 = preparedStmt2.executeQuery();
            resultSet2.next();
            int specialtyIdToJoin=resultSet2.getInt(1);

            String queryJoinedTable = "INSERT customer_specialties (customer_id, specialty_id) VALUES(?, ?) " +
                    "ON DUPLICATE KEY UPDATE customer_id=customer_id,specialty_id=specialty_id";
            PreparedStatement preparedStmtJoinedTable = connection.prepareStatement(queryJoinedTable);
            preparedStmtJoinedTable.setInt(1, customerIdToJoin);
            preparedStmtJoinedTable.setInt(2, specialtyIdToJoin);
            preparedStmtJoinedTable.execute();
            System.out.println("Joining the customer's data from table of customers and table of specialties has completed");

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

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
