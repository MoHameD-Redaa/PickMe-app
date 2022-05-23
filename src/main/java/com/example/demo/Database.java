package com.example.demo;

import java.time.LocalDate;
import java.util.*;

public class Database {
    ArrayList<Client> clients;
    ArrayList<Driver> drivers;
    ArrayList<Driver> pendingDrivers;
    ArrayList<Admin> admins;
    ArrayList<Ride> AllRides;
    ArrayList<String> discountAreas;
    ArrayList<LocalDate> holidays;
    private static Database database;

    private Database() {
        clients = new ArrayList<Client>();
        drivers = new ArrayList<Driver>();
        pendingDrivers = new ArrayList<>();
        discountAreas = new ArrayList<>();
        admins = new ArrayList<>();
        // To create the default admin once we create a database
        admins.add(new Admin());

        AllRides = new ArrayList<>();

        // Adding Holidays.
        holidays = new ArrayList<LocalDate>();

        holidays.add(LocalDate.of(2022, 1, 7));
        holidays.add(LocalDate.of(2022, 1, 25));
        holidays.add(LocalDate.of(2022, 5, 1));
        holidays.add(LocalDate.of(2022, 5, 2));
        holidays.add(LocalDate.of(2022, 5, 3));
        holidays.add(LocalDate.of(2022, 5, 13));
        holidays.add(LocalDate.of(2022, 7, 20));
        holidays.add(LocalDate.of(2022, 7, 23));
        holidays.add(LocalDate.of(2022, 8, 10));
        holidays.add(LocalDate.of(2022, 10, 6));
        holidays.add(LocalDate.of(2022, 10, 19));
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public Admin getAdmin(String username) {

        for (int i = 0; i < admins.size(); i++) {

            if (admins.get(i).getUsername().equals(username)) {

                return admins.get(i);
            }
        }
        return null;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }


    public Client getClient(String username) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getUsername().equals(username)) {
                return clients.get(i);
            }
        }
        return null;
    }

    public Driver getDriver(String username) {
        for (int i = 0; i < drivers.size(); i++) {
            if (drivers.get(i).getUsername().equals(username)) {
                return drivers.get(i);
            }
        }
        return null;
    }

    public static Database getDatabase() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    public boolean addClient(Client client) {
        clients.add(client);
        return true;
    }

    public void addDriver(Driver driver) {
        driver.setActive(true);
        drivers.add(driver);
    }

    public boolean addPendingDriver(Driver driver) {
        pendingDrivers.add(driver);
        pendingDrivers.size();
        return true;
    }

    public void removeDriver(int idx) {

        if (idx > -1 && idx < pendingDrivers.size())
            pendingDrivers.remove(idx);
    }


    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(ArrayList<Driver> drivers) {
        this.drivers = drivers;
    }


    public void setPendingDrivers(ArrayList<Driver> pendingDrivers) {
        this.pendingDrivers = pendingDrivers;
    }

    public ArrayList<Driver> getPendingDrivers() {
        return pendingDrivers;
    }

    public void addDiscountArea(String area) {
        discountAreas.add(area);
    }

    public ArrayList<String> getDiscountAreas() {
        return discountAreas;
    }

    public ArrayList<LocalDate> getHolidays() {
        return holidays;
    }

    //@haridy
    public void saveRide(Ride r) {
        AllRides.add(r);
    }

    public ArrayList<Ride> getAllRides() {
        return AllRides;
    }
}
