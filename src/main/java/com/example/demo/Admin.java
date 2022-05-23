package com.example.demo;

import EventPackage.Event;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class Admin {

    private String username;
    private String password;
    static Database database = Database.getDatabase();
    private Admin curr = null;
    public Admin()
    {
        this.username = "admin";
        this.password = "admin";
    }
    @GetMapping ("/admin")
    public String getUsername()
    {
          curr = AppSystem.curAdmin;
          AppSystem.curAdmin = null;
        return username;
    }

    public String getPassword() {
        return password;
    }

    @GetMapping("/pendingDrivers")
    public String listPendingDrivers()
    {
        database = Database.getDatabase();
        return database.getPendingDrivers().toString();
    }

    @PostMapping("/verifyDriver")
    public boolean verifyDriver(@RequestBody int idx)
    {
        idx--;
        int Size = database.getPendingDrivers().size();
        if(idx > -1 && idx < Size){
            database.addDriver(database.getPendingDrivers().get(idx));
            database.removeDriver(idx);
            return true;
        }
        return false;
    }

    @PostMapping("/suspendDriver")
    public boolean suspendDriver(@RequestBody String username)
    {
        for (int i = 0; i < database.getDrivers().size(); i++)
        {
            if (username.equals(database.getDrivers().get(i).getUsername())) {
                database.getDrivers().get(i).setActive(false);
                return true;
            }
        }
        return false;
    }

    @PostMapping("/suspendClient")
    public boolean suspendClient(@RequestBody String username)
    {
        for (int i = 0; i < database.getClients().size(); i++)
        {
            if (username.equals(database.getClients().get(i).getUsername())) {
                database.getClients().get(i).setActive(false);
                return true;
            }
        }
        return false;
    }

    @PostMapping("/addDiscountArea")
    // Function for adding discounts.
    public void addDiscountArea(@RequestBody String area)
    {
        database.addDiscountArea(area);
    }

    @GetMapping ("/events/{idx}")
    public ArrayList<Event> ListAllEvents(int idx) {
        idx--;
        if (idx >= 0 && idx < database.getAllRides().size())
        {
            ArrayList<Event> ClientEvent = database.getAllRides().get(idx).getEvents();
            return ClientEvent;
        }
        ArrayList<Event> emp = new ArrayList<>();
        return emp;
    }

    @GetMapping("/rides")
    public ArrayList<Ride> ListAllRides() {
        ArrayList<Ride> AllRides = database.getAllRides();
        return AllRides;
    }
    @GetMapping("/{username}/logOut")

    public ResponseEntity<?> logOutAdmin(){
        curr = null;
        return  ResponseEntity.ok("You are logged out now.");

    }

}
