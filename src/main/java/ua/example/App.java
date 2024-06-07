package ua.example;

import java.time.LocalDate;
import ua.example.model.Laptop;
import ua.example.model.LaptopDAO;

public class App {
    public static void main(String[] args) {
        LaptopDAO laptopDAO = new LaptopDAO();
        laptopDAO.addLaptop(
                new Laptop("Vostro", "Lenovo", LocalDate.of(2022, 12, 19), 16, 256, "Intel Core I7-11G"));
        // System.out.println(laptopDAO.findById(2));
    }
}
