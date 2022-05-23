/*
package com.example.demo;

import java.util.*;

public class UserInterface {

    RegistrationClass registration;
    LoginClass loginClass;

    public UserInterface() throws InterruptedException {
        registration = new RegistrationClass();
        loginClass = new LoginClass();
        mainMenu();
    }

    */
/**
     * get user's input
     *//*

    Scanner scan = new Scanner(System.in);

    */
/**
     * Show Administrator screen
     *//*

    public void adminMenu(Admin admin) throws InterruptedException {
        int choice = 0;
        boolean valid = true;

        boolean correctChoice = false;
        while (valid) {

            while (!correctChoice) {
                System.out.println("1- Show all pending drivers.\n"
                        + "2- Suspend driver.\n"
                        + "3- Suspend user account.\n"
                        + "4- Add discount Area\n"
                        + "5- See all rides\n"
                        + "6- logOut");

                choice = scan.nextInt();

                if (choice >= 1 && choice <= 6) {
                    correctChoice = true;
                } else {
                    correctChoice = false;
                }
            }

            switch (choice) {
                case 1: {
                    */
/*
                     The admin user should be able to verify driver registration. So the admin should be able to
                     list all pending driver registrations and verify any pending driver registration.
                     *//*


                    int size = admin.listPendingDrivers();
                    if (size > 0) {
                        System.out.println("\nChoose driver number to verify: ");
                        int idx = scan.nextInt();
                        if (admin.verifyDriver(idx)) {
                            System.out.println("Verified successfully!\n");
                        } else {
                            System.out.println("Driver not found.");
                        }
                    }

                    correctChoice = false;
                    break;
                }

                case 2: {
                    System.out.println("Enter driver username.");

                    String username = scan.next();
                    boolean done = admin.suspendDriver(username);
                    if (done) {
                        System.out.println("diver has been deleted successfully.");
                    } else {
                        System.out.println("driver name isn't correct.");
                        System.out.println("pls try again.");
                    }
                    correctChoice = false;
                    break;

                }

                case 3: {
                    System.out.println("Enter user name.");

                    String name = scan.next();
                    boolean done = admin.suspendClient(name);
                    if (done) {
                        System.out.println("User has been deleted successfully.");
                    } else {
                        System.out.println("User name isn't correct.");
                        System.out.println("please try again.");
                    }
                    correctChoice = false;
                    break;
                }

                case 4: {
                    System.out.println("Enter discount area:");
                    String area = scan.next();
                    admin.addDiscountArea(area);
                    System.out.println("Area is added successfully.");
                    correctChoice = false;
                    break;
                }

                case 5: {

                    if (admin.ListAllRides()) {
                        System.out.println("Choose the ride you want to show it's Events..");
                        int input = scan.nextInt();
                        admin.ListAllEvents(input);
                    }
                    correctChoice = false;
                    break;
                }

                case 6: {
                    valid = false;
                    mainMenu();
                    break;
                }

                default: {
                    valid = true;
                    correctChoice = false;
                }
            }

        }
    }

    public void mainMenu() throws InterruptedException {
        System.out.println("Main Menu");
        System.out.println("1- Login as admin.");
        System.out.println("2- Login as a client.");
        System.out.println("3- Login as a driver.");
        System.out.println("4- Signup as a client.");
        System.out.println("5- Signup as a driver.");
        System.out.println("6- exit.");
        int choice = scan.nextInt();
        if (choice == 2) {

            System.out.println("Enter username: ");
            String username = scan.next();
            System.out.println("Enter password: ");
            String password = scan.next();
            Client client = loginClass.loginAsClient(username, password);
            if(client != null){
                clientMenu(client);
            }
            else {
                mainMenu();
            }

        } else if (choice == 3) {

            System.out.println("Enter username: ");
            String username = scan.next();
            System.out.println("Enter password: ");
            String password = scan.next();
            Driver driver = loginClass.loginAsDriver(username, password);
            if (driver != null){
                driverMenu(driver);
            }
            else {
                mainMenu();
            }
        } else if (choice == 5 || choice == 4) {
            System.out.println("Email: ");
            String email = scan.next();
            System.out.println("Password: ");
            String password = scan.next();
            System.out.println("username: ");
            String username = scan.next();
            System.out.println("Phone number: ");
            String phone = scan.next();
            System.out.println("Real name: ");
            String realName = scan.next();
            if (choice == 4) {
                boolean valid = !AppSystem.isValidClient(username, password);
                if (valid) {
                    System.out.println("Your account is registered successfully");
                    registration.signUpClient(realName, username, phone, email, password);
                } else {
                    System.out.println("Your account is already registered. You can login");
                }
            } else {
                System.out.print("National ID: ");
                String nationalID = scan.next();
                System.out.print("Driver License: ");
                String driverLicense = scan.next();
                boolean valid = !AppSystem.isValidDriver(username, password);
                if (valid) {
                    System.out.println("Your account is registered successfully");
                    registration.signUpDriver(realName, username, phone, email, password, nationalID, driverLicense);
                } else {
                    System.out.println("Your account is already registered. You can login");
                }
            }
            mainMenu();
        } else if (choice == 1) {
            System.out.println("Username: ");
            String username = scan.next();
            System.out.println("Password: ");
            String password = scan.next();
            Admin admin = loginClass.loginAsAdmin(username, password);
            if (admin != null){
                adminMenu(admin);
            }
            else {
                mainMenu();
            }

        }else if (choice == 6) {
            System.exit(0);
        }
        else {
            System.out.println("Incorrect Input");
            mainMenu();
        }
    }


    */
/**
     * Show driver screen
     *//*

    public void driverMenu(Driver driver) throws InterruptedException {
        if (driver.isDriving()) {
            System.out.println("You are now on a ride!");
            driver.getNotifications().clear();
        }
        while (true) {
            System.out.println("Driver Menu");
            System.out.println("1- View notifications");
            System.out.println("2- Add favourite area");
            System.out.println("3- Show users' rate");
            System.out.println("4- Logout");
            int choice = scan.nextInt();
            if (choice == 1) {
                int notifications = driver.getNotifications().size();
                if (notifications == 0) {
                    System.out.println("You have no notifications");
                } else {
                    for (int i = 0; i < notifications; i++) {
                        System.out.print(i + 1 + ")" + driver.getNotifications().get(i));
                    }
                    System.out.print("\nEnter the number of notification if you want to make an offer ");
                    System.out.println("or enter 0 to go back");
                    int idx = scan.nextInt();
                    if (idx == 0) {
                        continue;
                    } else if (idx > notifications) {
                        System.out.println("Wrong number!");
                        continue;
                    } else {
                        System.out.println("Enter the price you want to offer for this ride.");
                        double price = scan.nextDouble();
                        idx--;
                        //Offer offer = new Offer(driver.getAverageRating(), price, driver.getUsername());
                        driver.makeOffer(price,idx);
                        //AppSystem.notifyClient(username, offer);
                        System.out.println("Your offer was sent to the client");

                    }
                }
            } else if (choice == 2) {
                System.out.println("Enter a new favourite area");
                String area = scan.next();
                driver.addFavArea(area);
                System.out.println("A new area was added successfully.");
                System.out.println("You will be notified we there is a pending ride at this area");
            } else if (choice == 3) {
                ArrayList<Ride> rides = driver.getRides();
                if (rides.size() == 0) {
                    System.out.println("No past rides");
                } else {
                    for (int i = 0; i < rides.size(); i++) {
                        System.out.println(rides.get(i).toString());
                    }
                }
            } else if (choice == 4) {
                break;
            }
        }
        mainMenu();
    }

    */
/**
     * Show client screen
     *//*


    public void clientMenu(Client var1) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        if (var1.getOffers() != null && var1.getOffers().size() > 0) {
            System.out.println("notification:-\n" +
                    "You Have new offers please check it");
        }

        while (true) {
            System.out.println("Please Enter the number of your choice...");

            System.out.println( "1- Ride Request\n" +
                    "2- Check offers\n" +
                    "3- Rate Driver\n" +
                    "4- Logout");
            short choice = input.nextShort();
            if (choice == 1) {
                if (var1.getRide() != null && var1.getRide().getOffer() != null) {
                    System.out.println("You Already in Ride!...");

                } else {
                    String Source, Dest;
                    System.out.println("Source: ");
                    Source = input.next();
                    System.out.println("Destination: ");
                    Dest = input.next();

                    int numOfUsers;
                    boolean notValidInput = true;
                    while (notValidInput){
                        System.out.println("Enter number of passengers between 1 : 3\n");
                        numOfUsers = input.nextInt();
                        if(numOfUsers < 4){
                            notValidInput = false;
                            var1.requestRide(Source, Dest, numOfUsers);
                        }
                        else
                            System.out.println("Pls try again...\n");

                    }
                    System.out.println("Waiting Driver...");
                }
            } else if (choice == 2) {
                if (var1.getOffers().size() > 0 && var1.getOffers() != null) {
                    var1.listOffers();
                    System.out.println("Enter the number of Chosen offer:");
                    //System.out.print("Offer: ");
                    int choose_offer = input.nextInt();
                    boolean offerexist = var1.chooseOffer(choose_offer);

                    while (!offerexist) {
                        choose_offer = input.nextInt();
                        offerexist = var1.chooseOffer(choose_offer);
                    }
                    //@mohamedHaridy.
                    var1.getRide().AddEvent(new ACCEPTOFFEREVENT(var1.getUsername(),var1.getRide().getOffer().getDriverName()));
                    System.out.println("Ride start...");
                    Thread.sleep(5000);

                    var1.getRide().AddEvent(new ARRIVETOUSEREVENT(var1.getRide().getOffer().getDriverName(),var1.getUsername()));

                } else {
                    System.out.println("You Have No offers...");
                }
            } else if (choice == 3) {
                if (var1.getRide() == null || var1.getRide().getOffer() == null) {
                    System.out.println("Sorry The Ride not assigned...");
                } else {
                    System.out.println("Please Enter the Rate From 1 to 5 ...");
                    System.out.print("Enter Rate: ");
                    double rate = input.nextDouble();
                    while (rate < 0.0 || rate > 5.0) {
                        System.out.println("Rate must be From 1 to 5, Please Try again...");
                        System.out.print("Enter Rate: ");
                        rate = input.nextDouble();
                    }
                    var1.rateDriver(rate);
                    //@Haridy
                    System.out.println("You are Reach...");
                    var1.getRide().AddEvent(new ARRIVETODESTEVENT(var1.getRide().getOffer().getDriverName(), var1.getUsername()));

                    // @Ziad
                    var1.endRide();
                    var1.DeleteRide();
                }

            } else if (choice == 4) {
                System.out.println("Logged out");
                mainMenu();
            } else {
                System.out.println("Invalid input please Try again...");
            }


        }


        //throw new Error("Unresolved compilation problem: \n");
    }

}
*/
