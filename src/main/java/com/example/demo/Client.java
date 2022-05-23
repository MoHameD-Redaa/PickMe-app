package com.example.demo;

import EventPackage.ACCEPTOFFEREVENT;
import EventPackage.ARRIVETODESTEVENT;
import EventPackage.ARRIVETOUSEREVENT;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/Client", produces = MediaType.APPLICATION_JSON_VALUE)
public class Client extends User{

    Ride ride;

    ArrayList<Offer> offers;

    Boolean isFirstRide;

    private Client curr =null;

    public Client(){
        super();
    }

    public Client(String name, String username, String mobile, String email, String password) {
        super(name, username, mobile, email, password);
        offers = new ArrayList<Offer>();
        isFirstRide = true;
    }
    @PostMapping("{username}/requestRide")
    public boolean requestRide(@RequestBody Ride ride){
        if (curr == null)
            return false;
        curr.ride = ride;
        curr.ride.setClientName(curr.getUsername());
        AppSystem.notifyDrivers(curr.getUsername(), curr.ride.getSource());
        return true;
    }

    @GetMapping("/{username}")
    public String getUsername(@PathVariable String username) {
        curr = AppSystem.curClient;
        if (curr == null)
            return "NOT EXIST!";
        AppSystem.curClient = null;
        return curr.getUsername();
    }
    @GetMapping("/{username}/logOut")
    public ResponseEntity<?> logOutClient(){
        curr = null;
        return  ResponseEntity.ok("You are logged out now.");

    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    @GetMapping ("{username}/getOffers")
    public ArrayList<Offer> getOffers() {
        return curr.offers;
    }

    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }

    public void addOffer(Offer offer)
    {
        offers.add(offer);
    }

    @PostMapping("{username}/rateDriver/{rate}")
    public Boolean rateDriver(@PathVariable double rate)
    {
        curr.ride.setRate(rate);
        AppSystem.addRide(curr.ride);

        curr.getRide().AddEvent(new ARRIVETODESTEVENT(curr.getRide().getOffer().getDriverName(), curr.getUsername()));

        AppSystem.setDriverStatus(curr.ride.getOffer().driverName, false);
        return true;
    }

    @GetMapping("{username}/notifications")
    public ResponseEntity<ArrayList<String>> showNotifications() {
        if (curr == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"YOU ARE NOT LOGGED IN!");
        return new ResponseEntity<>(curr.getNotification(), HttpStatus.OK);
    }

    public ArrayList<String> getNotification() {
           return super.getNotifications();
    }


    public void endRide()
    {
        AppSystem.checkPrice(this);
    }

    public void listOffers()
    {
        System.out.println("Your offers:\n");
        for (int i = 0; i < offers.size(); i++)
        {
            System.out.print(i + 1 + ") ");
            offers.get(i).printOffer();
        }
    }
    @GetMapping("{username}/chooseOffer/{idx}")
    public boolean chooseOffer(@PathVariable int idx )
    {
        idx--;
        if (idx<0||idx>curr.offers.size()-1){
            System.out.println("Offer Not Found\n");
            return false;
        }
        curr.ride.setOffer(curr.offers.get(idx));
        String username = curr.offers.get(idx).getDriverName();
        AppSystem.setDriverStatus(curr.getUsername(), true);
        curr.getRide().AddEvent(new ARRIVETOUSEREVENT(curr.getRide().getOffer().getDriverName(),curr.getUsername()));
        curr.getRide().AddEvent(new ACCEPTOFFEREVENT(curr.getUsername(), curr.getRide().getOffer().getDriverName()));
        curr.offers.clear();
        return true;
    }

    public Boolean getIsFirstRide() {
        return isFirstRide;
    }

    public void setFirstRide(Boolean firstRide) {
        isFirstRide = firstRide;
    }

    void DeleteRide(){
        AppSystem.saveRide(ride);
        ride = null;
    }
}
