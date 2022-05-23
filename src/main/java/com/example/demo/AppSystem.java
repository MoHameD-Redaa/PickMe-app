package com.example.demo;

import EventPackage.ADDOFFEREVENT;

import java.time.LocalDate;
import java.util.*;

public class AppSystem {
    static Database database = Database.getDatabase();
    static Client curClient = null;
    static Driver curDriver = null;
    static Admin curAdmin = null;

    public static boolean isValidAdmin(String username, String password)
    {
        ArrayList<Admin> admins = database.getAdmins();

        for (int i = 0; i < admins.size(); i++)
        {
            String curUsername = admins.get(i).getUsername();
            String curPassword = admins.get(i).getPassword();
            if (curUsername.equals(username) && curPassword.equals(password)) {
                curAdmin = admins.get(i);
                return true;
            }
        }
        return false;
    }


    public static boolean isValidClient(String username, String password)
    {
        ArrayList<Client> clients = database.getClients();
        for (int i = 0; i < clients.size(); i++)
        {
            String curUsername = clients.get(i).getUsername();
            String curPassword = clients.get(i).getPassword();
            if (curUsername.equals(username) && curPassword.equals(password)) {
                curClient = clients.get(i);
                return true;
            }
        }
        return false;
    }

    public static boolean isValidDriver(String username, String password)
    {
        ArrayList<Driver> drivers = database.getDrivers();
        for (int i = 0; i < drivers.size(); i++)
        {
            String curUsername = drivers.get(i).getUsername();
            String curPassword = drivers.get(i).getPassword();
            if (curUsername.equals(username) && curPassword.equals(password) && drivers.get(i).getIsActive()) {
                curDriver = drivers.get(i);
                return true;
            }
        }
        return false;
    }

    public static boolean setDriverStatus(String username, boolean flag)
    {
        ArrayList<Driver> drivers = database.getDrivers();
        for (int i = 0; i < drivers.size(); i++)
        {
            String curUsername = drivers.get(i).getUsername();
            if (curUsername.equals(username)) {
                drivers.get(i).setDriving(flag);
                return true;
            }
        }
        return false;
    }


	/*
	public static boolean findClient(String username)
	{
		for (int i = 0; i < clients.size())
	}*/

    public static void notifyDrivers(String username, String area)
    {
        ArrayList<Driver> drivers = database.getDrivers();

        for (int i = 0; i < drivers.size(); i++)
        {
            for (int j = 0; j < drivers.get(i).getFavouriteArea().size(); j++)
            {
                String curArea = drivers.get(i).getFavouriteArea().get(j);
                if (curArea.equals(area))
                {
                    String notification = "You have a pending ride at " + area + ".\n";
                    notification += "Client's name is " + username;
                    //System.out.println();
                    drivers.get(i).addNotification(notification);
                }
            }
        }

    }

    public static void notifyClient(String username, Offer offer)
    {
        ArrayList<Client> clients = database.getClients();
        /*
         *Mohamed Haridy Add it
         */
        for (int i = 0; i < clients.size(); i++)
        {
            if (clients.get(i).getUsername().equals(username))
            {
                clients.get(i).addOffer(offer);
                clients.get(i).getRide().AddEvent(new ADDOFFEREVENT(offer.driverName, offer.getPrice()));
                return;
            }
        }
    }

    public static void addRide(Ride ride)
    {
        String username = ride.getOffer().getDriverName();
        ArrayList<Driver> drivers = database.getDrivers();
        for (int i = 0; i < drivers.size(); i++)
        {
            String curUsername = drivers.get(i).getUsername();
            if (curUsername.equals(username)) {
                drivers.get(i).addRide(ride);
                drivers.get(i).getNotifications().clear();
            }
        }
    }

    // Function to check if this is a holiday or not.
    public static boolean isHoliday(LocalDate date)
    {
        ArrayList<LocalDate> holidays = database.getHolidays();
        return holidays.contains(date);
    }

    // Function to check if this is a discount area.
    public static boolean isDiscountArea(String area)
    {
        ArrayList<String> discountAreas = database.getDiscountAreas();
        return discountAreas.contains(area);
    }

    public static double checkPrice(Client client)
    {
        double totalDiscount = 1.0;

        //If this the first ride, make 5% discount.
        if (client.getIsFirstRide())
        {
            client.setFirstRide(false);
            totalDiscount -= 0.05;
            System.out.println("This is your first ride. We have 5% discount for you");
        }

        Ride ride = client.getRide();

        // If number of passengers is 2, make 5% discount.
        if (ride.getCntPassengers() >= 2)
        {
            totalDiscount -= 0.05;
            System.out.println("The number of passengers is "+ ride.getCntPassengers() + ". We have 5% discount for you");
        }

        // If today is a public holiday, apply 5% discount.
        if (AppSystem.isHoliday(ride.getDate()))
        {
            totalDiscount -= 0.05;
            System.out.println("Today is a public holiday. We have 5% discount for you");
        }

        // If today is client's birthday, apply 10% discount.
        if (ride.getDate().equals(client.getBirthday()))
        {
            totalDiscount -= 0.1;
            System.out.println("Today is your birthday. We have 10% discount for you");
        }

        // If the destination area is one of the discount areas, apply 10% discount.
        if (AppSystem.isDiscountArea(ride.getDestination()))
        {
            totalDiscount -= 0.1;
            System.out.println("We have 10% discount for rides concerning " + ride.getDestination());
        }

        System.out.println("Price before discount: " + ride.getOffer().getPrice());
        System.out.println("Price after discount: " + ride.getOffer().getPrice() * totalDiscount);
        return (ride.getOffer().getPrice() * totalDiscount);
    }

    //@haridy
    public static void saveRide(Ride ride){
        database.saveRide(ride);
    }

}
