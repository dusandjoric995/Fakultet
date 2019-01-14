package com.example.milan.mojmajstor.utils;

import java.io.Serializable;
import java.util.ArrayList;

public class RepairRequest implements Serializable {
    private String description;
    private User craftsman;
    private String date;
    private String status;
    private User client;
    private String district;
    private String address;
    private boolean creditCard;
    private double paid;
    private double price;
    private boolean listenerSet;

    public RepairRequest(String description, User craftsman, String date, String status, User client, String district, String address, boolean creditCard, double paid, double price) {
        this.description = description;
        this.craftsman = craftsman;
        this.date = date;
        this.status = status;
        this.client = client;
        this.district = district;
        this.address = address;
        this.creditCard = creditCard;
        this.paid = paid;
        this.price = price;
        this.listenerSet = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCraftsman() {
        return craftsman;
    }

    public void setCraftsman(User craftsman) {
        this.craftsman = craftsman;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isCreditCard() {
        return creditCard;
    }

    public void setCreditCard(boolean creditCard) {
        this.creditCard = creditCard;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isListenerSet() {
        return listenerSet;
    }

    public void setListenerSet(boolean listenerSet) {
        this.listenerSet = listenerSet;
    }
}
