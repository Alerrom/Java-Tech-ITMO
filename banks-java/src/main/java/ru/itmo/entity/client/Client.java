package ru.itmo.entity.client;

import ru.itmo.entity.account.AbstractAccount;

import java.util.ArrayList;

public class Client {
    private ArrayList<String> notificationHistory;
    private String name;
    private String surname;
    private String address;
    private String passport;
    private boolean notify;

    public Client(String name, String surname, String address, String passport, boolean notify) {

        this.name = name;
        this.surname = surname;
        this.address = address;
        this.passport = passport;
        this.notify = notify;
        this.notificationHistory = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPassport() {
        return this.passport;
    }

    public boolean getNotify() {
        return notify;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }


    public ArrayList<String> getNotificationHistory() {
        return notificationHistory;
    }


    public boolean IsDoubtfulAccount() {
        return this.address.equals("") || this.passport.equals("") || this.passport == null;
    }
}
