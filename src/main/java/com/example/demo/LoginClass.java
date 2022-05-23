package com.example.demo;

public class LoginClass {

    static Database database;

    public LoginClass() {
        database = Database.getDatabase();
    }

    public Admin loginAsAdmin(String username, String password) throws InterruptedException {
        boolean valid = AppSystem.isValidAdmin(username, password);
        if (!valid) {
            //System.out.println("Wrong username or password");
            return null;
        } else {
            //System.out.println("\t\tWelcome " + username + "\n");
            Admin admin = database.getAdmin(username);
            return admin;
        }
    }

    public Client loginAsClient(String username, String password) throws InterruptedException {
        boolean valid = AppSystem.isValidClient(username, password);
        if (!valid) {
            //System.out.println("Wrong username or password");
            return null;
        } else {
            //System.out.println("\t\tWelcome " + username + "\n");
            Client client = database.getClient(username);
            return client;
        }
    }


    public Driver loginAsDriver(String username, String password) throws InterruptedException {
        boolean valid = AppSystem.isValidDriver(username, password);
        if (!valid) {
            //System.out.println("Wrong username or password");
            return null;
        } else {
            //System.out.println("\t\tWelcome " + username + "\n");
            Driver driver = database.getDriver(username);
            return driver;
        }
    }
}
