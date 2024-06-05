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
            statement.setString(4, String.valueOf(laptop.getRamCapacity()));
            statement.setString(5, String.valueOf(laptop.getSsdCapacity()));
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
                PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                laptop.setId(Integer.parseInt("laptop_id"));
                laptop.setModel(resultSet.getString("model"));
                laptop.setManufacturer(resultSet.getString("manufaturer"));
                laptop.setProdDate(LocalDate.parse(resultSet.getString("prod_date"), FORMATTER));
                laptop.setRamCapacity(Integer.parseInt(resultSet.getString("ram_capacity")));
                laptop.setRamCapacity(Integer.parseInt(resultSet.getString("ssd_capacity")));
                laptop.setCpu(resultSet.getString("cpu_name"));
            }

        } catch (SQLException e) {
            System.out.println("Faild db connection");
        }

        return laptop;
    }
}
