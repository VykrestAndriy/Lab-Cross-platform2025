package Exercise.Models;

public class DisplayDevice {
    private String model;
    private int year;
    private double price;
    private int diagonal;
    private String manufacturer;

    public DisplayDevice(String model, int year, double price, int diagonal, String manufacturer) {
        this.model = model;
        this.year = year;
        this.price = price;
        this.diagonal = diagonal;
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public int getDiagonal() {
        return diagonal;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public String toString() {
        return "Модель: " + model + ", Рік: " + year + ", Ціна: " + price + " грн, Діагональ: " + diagonal + " дюймів, Виробник: " + manufacturer;
    }
}