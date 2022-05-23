package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping(value = "/Driver", produces = MediaType.APPLICATION_JSON_VALUE)
public class Driver extends User {

    private String nationalString;
    private String drivingLicense;
    private ArrayList<String> favouriteArea = new ArrayList<String>();
    private ArrayList<Ride> rides = new ArrayList<>();
    private boolean isDriving = false;
    private Driver curr = null;

    // Constructor
    public Driver(String name, String username, String mobile, String email, String password,
                  String nationalString, String drivingLicense) {
        super(name, username, mobile, email, password);
        this.nationalString = nationalString;
        this.drivingLicense = drivingLicense;
        isDriving = false;
        super.setActive(true);
        favouriteArea = new ArrayList<String>();
    }
    public Driver(){
        super();
    }

    public boolean isDriving() {
        return isDriving;
    }

    public void setDriving(boolean isDriving) {
        this.isDriving = isDriving;
    }

    // Copy Constructor.
    public Driver(String name, String username, String mobile, String email, String password,
                  String nationalString, String drivingLicense, ArrayList<String> favouriteArea) {
        super(name, username, mobile, email, password);
        this.nationalString = nationalString;
        this.drivingLicense = drivingLicense;
        this.favouriteArea = favouriteArea;
    }

    public Driver(String nationalString, String drivingLicense, ArrayList<String> favouriteArea) {
        this.nationalString = nationalString;
        this.drivingLicense = drivingLicense;
        this.favouriteArea = favouriteArea;
    }

    public void addRide(Ride ride)
    {
        rides.add(ride);
    }

    @GetMapping("/{username}")
    public String getUsername(@PathVariable String username) {
        System.out.println();
        curr = AppSystem.curDriver;
        AppSystem.curDriver = null;
        System.out.println(curr.getUsername() + "hena");
        return curr.getUsername();
    }

    @GetMapping("/{username}/logOut")
    public ResponseEntity<?> logOutDriver(){
        curr = null;
        return  ResponseEntity.ok("You are logged out now.");

    }



    @GetMapping("/{username}/notifications")
    public String notifications() {
        return curr.getNotifications().toString();
    }

    @GetMapping("/{username}/ratings")
    public String listRatings()
    {
        String ret = "";
        for (int i = 0; i < curr.rides.size(); i++)
        {
            ret += "Client's name: " + curr.rides.get(i).getClientName() + " " + "Rating: " + curr.rides.get(i).getRate() + '\n';
        }
        return ret;
    }


    @Override
    public String toString() {
        return "Driver [nationalString=" + nationalString + ", drivingLicense=" + drivingLicense + ", Name="
                + getName() + ", Username= " + getUsername() + "]";
    }

    @PostMapping("{username}/addFavArea/{FavArea}")
    public boolean addFavArea(@PathVariable String FavArea) {
        curr.favouriteArea.add(FavArea);
        return true;
    }

    public double getAverageRating()
    {
        double avg = 0;
        for (int i = 0; i < rides.size(); i++)
            avg += rides.get(i).getRate();
        avg /= rides.size();
        return avg;
    }
    @GetMapping("/{username}/makeOffer/{price}/{notificationIndx}")
    public void makeOffer(@PathVariable double price, @PathVariable int notificationIndx) {
        String notification = curr.getNotifications().get(notificationIndx);
        String[] words = notification.split(" ");
        String username = words[words.length - 1];
        Offer offer = new Offer(curr.getAverageRating(), price, curr.getUsername());
        AppSystem.notifyClient( username, offer);
    }


    public ArrayList<Ride> getRides() {
        return rides;
    }

    public void setRides(ArrayList<Ride> rides) {
        this.rides = rides;
    }

    public String getNationalString() {
        return nationalString;
    }

    public void setNationalString(String nationalString) {
        this.nationalString = nationalString;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public ArrayList<String> getFavouriteArea() {
        return favouriteArea;
    }

    @GetMapping("/{username}/FavouriteArea")
    public String favouriteArea() {
        return curr.favouriteArea.toString();
    }

    public void setFavouriteArea(ArrayList<String> favouriteArea) {
        this.favouriteArea = favouriteArea;
    }

}
