package com.example.demo;

public class RegistrationClass {

    static Database database;

    public RegistrationClass() throws InterruptedException {
        database = Database.getDatabase();
    }

    public boolean signUpClient(Client client) {
        return database.addClient(client);
    }

    public boolean signUpDriver(Driver driver) {
        return database.addPendingDriver(driver);
    }

    public void signUpAdmin(Admin admin) {
        database.addAdmin(admin);
    }
}
