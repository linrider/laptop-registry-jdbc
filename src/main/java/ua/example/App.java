package ua.example;

import ua.example.model.LaptopDAO;

public class App {
    public static void main(String[] args) {
        LaptopDAO laptopDAO = new LaptopDAO();
        // laptopDAO.addLaptop(
        //         new Laptop("G6", "Hewlett Packard", LocalDate.of(2019, 3, 21), 16, 512, "Intel Core I5-11G"));
        System.out.println(laptopDAO.findById(2));
    }
}
