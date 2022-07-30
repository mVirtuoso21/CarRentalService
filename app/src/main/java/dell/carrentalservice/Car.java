package dell.carrentalservice;

public class Car {

    private String make, model, year, number, price;
    private boolean rented;

    public Car (String make, String model, String year, String number, String price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.number = number;
        this.price = price;
        this.rented = false;
    }

    public Car (String make, String model, String year, String number, String price, String rented) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.number = number;
        this.price = price;
        this.rented = Boolean.valueOf(rented);
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

    public boolean isRented() { return rented; }

    public void setRented(boolean rented) { this.rented = rented; }

    public String toString () {
        return this.make + " " + this.model + " " + this.year + "\nNumber: " + this.number + "\nPrice: $" + this.price + "/24hours";
    }
}