package main.com.mokujin.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    String url = "jdbc:postgresql://localhost:5432/restaurant";
    String user = "mokujin";
    String password = "1111";

    public EmployeeDao() {
        loadDriver();
    }

    public Employee load(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createEmployee(resultSet);
            } else {
                throw new RuntimeException("Can't find employee with id " + id);
            }
        } catch (SQLException e) {
            LOGGER.info("Exception occurred while connecting to database: " + url + e);
            throw new RuntimeException(e);
        }
    }

    public List<Employee> getAll() {
        List<Employee> result = new ArrayList<>();
        LOGGER.info("Connection to database...");
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            LOGGER.info("Successfully connected to database");
            String sql = "SELECT * FROM employee";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Employee employee = createEmployee(resultSet);
                result.add(employee);
            }
        } catch (SQLException e) {
            LOGGER.info("Exception occurred while connecting to database: " + url + e);
            throw new RuntimeException(e);
        }
        return result;
    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setName(resultSet.getString("name"));
        employee.setBirthday(resultSet.getString("dob"));
        employee.setPhone(resultSet.getString("phone"));
        employee.setPositionID(resultSet.getInt("position_id"));
        employee.setSalary(resultSet.getDouble("salary"));
        return employee;
    }


    private void loadDriver() {
        try {
            LOGGER.info("Loading JDBS driver: org.postgresql.Driver");
            Class.forName("org.postgresql.Driver");
            LOGGER.info("Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot find driver: org.postgresql.Driver");
            throw new RuntimeException(e);
        }
    }
}
