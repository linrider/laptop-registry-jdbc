package ua.example.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

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

    public Laptop findLaptopById(int id) {
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
                laptop.setSsdCapacity(resultSet.getInt(6));
                laptop.setCpu(resultSet.getString(7));
            } else {
                System.out.println("Resultset is empty");
            }
        } catch (SQLException e) {
            System.out.println("Faild db connection");
        }

        return laptop;
    }

    public List<Laptop> findAllLaptops() {
        String sqlQuery = "SELECT * FROM laptops";
        List<Laptop> laptops = new ArrayList<>();

        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                Laptop laptop = new Laptop();

                laptop.setId(resultSet.getInt(1));
                laptop.setModel(resultSet.getString(2));
                laptop.setManufacturer(resultSet.getString(3));
                laptop.setProdDate(LocalDate.parse(resultSet.getString(4), FORMATTER));
                laptop.setRamCapacity(resultSet.getInt(5));
                laptop.setSsdCapacity(resultSet.getInt(6));
                laptop.setCpu(resultSet.getString(7));

                laptops.add(laptop);
            }
        } catch (SQLException e) {
            System.out.println("Faild db connection");
        }
        return laptops;
    }

    public void deletLaptopbyId(int id) {
        String sqlQuery = "DELETE FROM laptops WHERE laptop_id = ?";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Faild db connection");
        }
    }

    public void deleteAllLaptops() {
        String sqlQuery = "DELETE FROM laptops";

        try (Connection connection = getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlQuery);

        } catch (SQLException e) {
            System.out.println("Faild db connection");
        }
    }

    public void updateLaptopById(int id, Laptop laptop) {
        String sqlQuery = "UPDATE laptops SET model = ?, manufacturer = ?, prod_date = ?, ram_capacity = ?," +
                "ssd_capacity = ?, cpu_name = ? WHERE laptop_id = ?";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, laptop.getModel());
            preparedStatement.setString(2, laptop.getManufacturer());
            preparedStatement.setString(3, laptop.getProdDate().format(FORMATTER));
            preparedStatement.setInt(4, laptop.getRamCapacity());
            preparedStatement.setInt(5, laptop.getSsdCapacity());
            preparedStatement.setString(6, laptop.getCpu());
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Faild db connection");
        }
    }

    public List<Laptop> findLaptopByModel(String model) {
        String sqlQuery = "SELECT * FROM laptops WHERE model = ?";
        List<Laptop> laptops = new ArrayList<>();

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, model);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Laptop laptop = new Laptop();
                laptop.setId(resultSet.getInt(1));
                laptop.setModel(resultSet.getString(2));
                laptop.setManufacturer(resultSet.getString(3));
                laptop.setProdDate(LocalDate.parse(resultSet.getString(4), FORMATTER));
                laptop.setRamCapacity(resultSet.getInt(5));
                laptop.setSsdCapacity(resultSet.getInt(6));
                laptop.setCpu(resultSet.getString(7));

                laptops.add(laptop);
            }
        } catch (SQLException e) {
            System.out.println("Faild db connection");
        }

        return laptops;
    }

    public List<Laptop> findLaptopByProdDate(String prodDate) {
        String sqlQuery = "SELECT * FROM laptops WHERE prod_date = ?";
        List<Laptop> laptops = new ArrayList<>();

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, prodDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Laptop laptop = new Laptop();
                laptop.setId(resultSet.getInt(1));
                laptop.setModel(resultSet.getString(2));
                laptop.setManufacturer(resultSet.getString(3));
                laptop.setProdDate(LocalDate.parse(resultSet.getString(4), FORMATTER));
                laptop.setRamCapacity(resultSet.getInt(5));
                laptop.setSsdCapacity(resultSet.getInt(6));
                laptop.setCpu(resultSet.getString(7));

                laptops.add(laptop);
            }
        } catch (SQLException e) {
            System.out.println("Faild db connection");
        }

        return laptops;
    }

    public List<Laptop> findLaptopByRamAnadSsd(int ram, int ssd) {
        String sqlQuery = "SELECT * FROM laptops WHERE ram_capacity = ? AND ssd_capacity = ?";
        List<Laptop> laptops = new ArrayList<>();

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, ram);
            preparedStatement.setInt(2, ssd);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Laptop laptop = new Laptop();
                laptop.setId(resultSet.getInt(1));
                laptop.setModel(resultSet.getString(2));
                laptop.setManufacturer(resultSet.getString(3));
                laptop.setProdDate(LocalDate.parse(resultSet.getString(4), FORMATTER));
                laptop.setRamCapacity(resultSet.getInt(5));
                laptop.setSsdCapacity(resultSet.getInt(6));
                laptop.setCpu(resultSet.getString(7));

                laptops.add(laptop);
            }
        } catch (SQLException e) {
            System.out.println("Faild db connection");
        }

        return laptops;
    }

    public List<Laptop> findLaptopByCpu(String cpu) {
        String sqlQuery = "SELECT * FROM laptops WHERE cpu_name = ?";
        List<Laptop> laptops = new ArrayList<>();

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, cpu);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Laptop laptop = new Laptop();
                laptop.setId(resultSet.getInt(1));
                laptop.setModel(resultSet.getString(2));
                laptop.setManufacturer(resultSet.getString(3));
                laptop.setProdDate(LocalDate.parse(resultSet.getString(4), FORMATTER));
                laptop.setRamCapacity(resultSet.getInt(5));
                laptop.setSsdCapacity(resultSet.getInt(6));
                laptop.setCpu(resultSet.getString(7));

                laptops.add(laptop);
            }
        } catch (SQLException e) {
            System.out.println("Faild db connection");
        }

        return laptops;
    }
}
