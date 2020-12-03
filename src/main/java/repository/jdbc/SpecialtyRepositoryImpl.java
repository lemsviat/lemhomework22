package repository.jdbc;

import model.Specialty;
import repository.ConnectionFactory;
import repository.SpecialtyRepository;
import view.CustomerView;
import view.SpecialtyView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class SpecialtyRepositoryImpl implements SpecialtyRepository {

    public static final String CREATE_SPECIALTY_QUERY = "INSERT specialties (specialty_name) VALUES(?) " +
            "ON DUPLICATE KEY UPDATE specialty_name=specialty_name";
    public static final String FIND_CUSTOMER_NAME_BY_ID_QUERY = "SELECT id FROM customers WHERE customers.name = ?";
    public static final String FIND_SPECIALTY_NAME_BY_ID_QUERY = "SELECT specialty_id FROM specialties WHERE specialty_name = ?";
    public static final String INSERT_INTO_JOINED_TABLE_QUERY = "INSERT customer_specialties (customer_id, specialty_id) VALUES(?, ?) " +
            "ON DUPLICATE KEY UPDATE customer_id=?,specialty_id=specialty_id";
    public static final String UPDATE_INTO_JOINED_TABLE_QUERY = "UPDATE customer_specialties SET customer_id=?, specialty_id=? " +
            "WHERE customer_id=?";

    public static final String FIND_DUPLICATE_CUSTOMER_ID_IN_JOINED_TABLE_QUERY = "SELECT COUNT(customer_specialties.customer_id) " +
            "FROM customer_specialties WHERE customer_id = ?";

    SpecialtyView specialtyView = new SpecialtyView();
    public static Set<Specialty> specialtySet = new LinkedHashSet<>();

    public void create() {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            System.out.println("Creating specialty of the customer...");
            PreparedStatement preparedStmt = connection.prepareStatement(CREATE_SPECIALTY_QUERY);
            specialtySet = specialtyView.create();
            String specialties = specialtySet.toString();
            preparedStmt.setString(1, specialties);
            preparedStmt.execute();
            System.out.println("Specialty successfully created...");

            //System.out.println("Selecting customerIdToJoin...");
            PreparedStatement preparedStmt1 = connection.prepareStatement(FIND_CUSTOMER_NAME_BY_ID_QUERY);
            preparedStmt1.setString(1, CustomerView.customerName);
            ResultSet resultSet1 = preparedStmt1.executeQuery();
            resultSet1.next();
            int customerIdToJoin = resultSet1.getInt(1);

            //System.out.println("Selecting specialtyIdToJoin...");
            PreparedStatement preparedStmt2 = connection.prepareStatement(FIND_SPECIALTY_NAME_BY_ID_QUERY);
            preparedStmt2.setString(1, specialties);
            ResultSet resultSet2 = preparedStmt2.executeQuery();
            resultSet2.next();
            int specialtyIdToJoin = resultSet2.getInt(1);

            PreparedStatement preparedStmtCheckDuplicate =
                    connection.prepareStatement(FIND_DUPLICATE_CUSTOMER_ID_IN_JOINED_TABLE_QUERY);
            preparedStmtCheckDuplicate.setInt(1, customerIdToJoin);
            ResultSet resultSet3 = preparedStmtCheckDuplicate.executeQuery();
            resultSet3.next();
            int checkDuplicate = resultSet3.getInt(1);

            PreparedStatement preparedStmtJoinedTable;
            if (checkDuplicate > 0) {
                preparedStmtJoinedTable = connection.prepareStatement(UPDATE_INTO_JOINED_TABLE_QUERY);
            } else {
                preparedStmtJoinedTable = connection.prepareStatement(INSERT_INTO_JOINED_TABLE_QUERY);
            }
            preparedStmtJoinedTable.setInt(1, customerIdToJoin);
            preparedStmtJoinedTable.setInt(2, specialtyIdToJoin);
            preparedStmtJoinedTable.setInt(3, customerIdToJoin);

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
