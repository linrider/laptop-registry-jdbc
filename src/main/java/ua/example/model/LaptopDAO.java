package ua.example.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LaptopDAO {
    private final String URL = "jdbc:mysql://localhost:3306/laptop_registry";
    private final String USERNAME = "root";
    private final String PASSWORD = "123";
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public LaptopDAO() {
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void addLaptop(Laptop laptop) {
        String sqlQuery = "INSERT INTO laptops (model, manufacturer, prod_date, ram_capacity, ssd_capacity, cpu_name) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, laptop.getModel());
            statement.setString(2, laptop.getManufacturer());
            statement.setString(3, laptop.getProdDate().format(FORMATTER));
            statement.setInt(4, laptop.getRamCapacity());
            statement.setInt(5, laptop.getSsdCapacity());
            statement.setString(6, laptop.getCpu());
            int isDone = statement.executeUpdate();
            if (isDone > 0) {
                System.out.println("New laptop was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Faild db connection");
        }
    }

    public Laptop findById(int id) {
        String sqlQuery = "SELECT * FROM laptops WHERE laptop_id = ?";
        Laptop laptop = new Laptop();

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                laptop.setId(resultSet.getInt(1));
                laptop.setModel(resultSet.getString(2));
                laptop.setManufacturer(resultSet.getString(3));
                laptop.setProdDate(LocalDate.parse(resultSet.getString(4), FORMATTER));
                laptop.setRamCapacity(resultSet.getInt(5));
                laptop.setRamCapacity(resultSet.getInt(6));
                laptop.setCpu(resultSet.getString(7));
            } else {
                System.out.println("Resultset is empty");
            }
        } catch (SQLException e) {
            System.out.println("Faild db connection");
        }

        return laptop;
    }
}
