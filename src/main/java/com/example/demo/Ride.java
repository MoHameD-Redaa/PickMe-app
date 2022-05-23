package com.example.demo;

import EventPackage.Event;

import java.time.LocalDate;
import java.util.*;

public class Ride {
    private String source;
    private String destination;
    private String clientName;
    private double rate;
    private Offer offer;
    private LocalDate date;
    private int cntPassengers; // How many passengers for the ride.
    private ArrayList<Event>Events;

    public Ride(String source, String destination, int cntPassengers){
        this.source = source;
        this.destination = destination;
        this.cntPassengers = cntPassengers;
        date = LocalDate.now();
        Events=new ArrayList<>();
    }


    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public void setCntPassengers(int cntPassengers) {
        this.cntPassengers = cntPassengers;
    }

    public int getCntPassengers() {
        return cntPassengers;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "source:'" + source + '\'' +
                ", destination:'" + destination + '\'' +
                ", numOfPassengers: " + cntPassengers + '\'' +
                ", clientName:'" + clientName + '\'' +
                ", rate:" + rate +
                '}';
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String userName) {
        clientName = userName;
    }

    //@Haridy
    public void AddEvent(Event e){
        Events.add(e);
    }


    public ArrayList<Event> getEvents() {
        return Events;
    }

}
