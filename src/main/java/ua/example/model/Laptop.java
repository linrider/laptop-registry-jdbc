package ua.example.model;

import java.time.LocalDate;

public class Laptop {
    private int id;
    private String model;
    private String manufacturer;
    private LocalDate prodDate;
    private int ramCapacity;
    private int ssdCapacity;
    private String cpu;

    public Laptop() {
    }

    public Laptop(String model, String manufacturer, LocalDate prodDate, int ramCapacity, int ssdCapacity, String cpu) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.prodDate = prodDate;
        this.ramCapacity = ramCapacity;
        this.ssdCapacity = ssdCapacity;
        this.cpu = cpu;
    }
    
    //Getters
    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public LocalDate getProdDate() {
        return prodDate;
    }

    public int getRamCapacity() {
        return ramCapacity;
    }

    public int getSsdCapacity() {
        return ssdCapacity;
    }

    public String getCpu() {
        return cpu;
    }
    
    
    //Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setModel(String model) {
        this.model = model;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setProdDate(LocalDate prodDate) {
        this.prodDate = prodDate;
    }

    public void setRamCapacity(int ramCapacity) {
        this.ramCapacity = ramCapacity;
    }

    public void setSsdCapacity(int ssdCapacity) {
        this.ssdCapacity = ssdCapacity;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    @Override
    public String toString() {
        return "Laptop [id=" + id + ", model=" + model + ", manufacturer=" + manufacturer + ", prodDate=" + prodDate
                + ", ramCapacity=" + ramCapacity + ", ssdCapacity=" + ssdCapacity + ", cpu=" + cpu + "]";
    }

}
