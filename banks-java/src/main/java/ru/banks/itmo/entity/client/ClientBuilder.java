package ru.banks.itmo.entity.client;

public class ClientBuilder {
    private String name;
    private String surname;
    private String address;
    private String passport;
    private boolean notification;

    public ClientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ClientBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public ClientBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public ClientBuilder setPassport(String passport) {
        this.passport = passport;
        return this;
    }

    public ClientBuilder setNotification(boolean notification) {
        this.notification = notification;
        return this;
    }

    public Client getClient() {
        return new Client(name, surname, address, passport, notification);
    }
}
