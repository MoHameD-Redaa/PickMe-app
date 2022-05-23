package com.example.demo;

import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;

public class User {
    private String name;
    private String username;
    private String mobile;
    private String Email;
    private boolean isActive;
    private String password;
    private ArrayList<String> Notifications;
    private LocalDate birthday;

    public User(String name, String username, String mobile, String email, String password) {
        this.name = name;
        this.username = username;
        this.mobile = mobile;
        Email = email;
        this.password = password;
        Notifications = new ArrayList<>();
        isActive = true;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }


    public User() {
        Notifications = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getNotifications() {
        return Notifications;
    }

    public void setNotifications(ArrayList<String> notifications) {
        Notifications = notifications;
    }

    public void addNotification(String notification) {
        Notifications.add(notification);
    }

    public void DeleteAllNotifications() {
        Notifications.clear();
    }

    public void DisplayNotifications(){
        if (Notifications.size() < 1){
            System.out.println("No Notifications");
            return;
        }
        System.out.println("Notifications: -");
        ArrayList<String> mynotifications = Notifications;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

}
