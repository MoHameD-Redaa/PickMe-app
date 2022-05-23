package com.example.demo;

public class Offer {
    double avgRating;
    double price;
    String driverName;

    public Offer(double avgRating, double price, String driverName) {
        this.avgRating = avgRating;
        this.price = price;
        this.driverName = driverName;
    }

    public double getAvgRating() {
        return avgRating;
    }
    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getDriverName() {
        return driverName;
    }
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void printOffer()
    {
        String offer = "Driver's name: " + driverName + "\n";
        offer += "Average Rating: " + avgRating + "\n";
        offer += "Price: " + price;
        System.out.println(offer);
    }


}
