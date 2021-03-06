package com.example.milan.mojmajstor.utils;

import android.content.res.Resources;
import android.util.Pair;

import com.example.milan.mojmajstor.R;

import java.io.Serializable;
import java.util.ArrayList;

public class RepairRequest implements Serializable {
    private String description;
    private User craftsman;
    private String date;
    private Status status;
    private int statusColor;
    private User client;
    private String district;
    private String address;
    private boolean creditCard;
    private double paid;
    private double price;
    private boolean listenerSet;
    private Severity severity;
    private Pair<Double, Double> coorditnates;

    public RepairRequest(String description, User craftsman, String date, Status status, User client, String district, String address, boolean creditCard, double paid, double price, Severity severity) {
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
        this.severity = severity;
        setStatusColor(status);
        coorditnates = new Pair<>(Math.random() * 20, Math.random() * 20);
    }

    private void setStatusColor(Status status){
        switch(status){
            case ON_HOLD:{
                statusColor = Data.getCurrentActivity().getResources().getColor(R.color.colorDarkGrey,null);
                break;
            }
            case OFFERED:{
                statusColor = Data.getCurrentActivity().getResources().getColor(R.color.colorBlue,null);
                break;
            }
            case ACCEPTED:{
                statusColor = Data.getCurrentActivity().getResources().getColor(R.color.colorPrimary,null);
                break;
            }
            case PAID:{
                statusColor = Data.getCurrentActivity().getResources().getColor(R.color.colorGold,null);
                break;
            }
            case REFUSED:{
                statusColor = Data.getCurrentActivity().getResources().getColor(R.color.colorAccent,null);
                break;
            }
        }
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        setStatusColor(status);
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

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public int getStatusColor() {
        return statusColor;
    }

    public void setStatusColor(int statusColor) {
        this.statusColor = statusColor;
    }

    public Pair<Double, Double> getCoorditnates() {
        return coorditnates;
    }

    public void setCoorditnates(Pair<Double, Double> coorditnates) {
        this.coorditnates = coorditnates;
    }

    public enum Severity {
        LOW,
        MEDIUM,
        HIGH;

        @Override
        public String toString() {
            switch (this){
                case LOW:{
                    return Data.getCurrentActivity().getResources().getString(R.string.severity_low);
                }
                case MEDIUM:{
                    return Data.getCurrentActivity().getResources().getString(R.string.severity_medium);
                }
                case HIGH:{
                    return Data.getCurrentActivity().getResources().getString(R.string.severity_high);
                }
            }
            return null;
        }
    }

    public enum Status {
        ON_HOLD,
        OFFERED,
        ACCEPTED,
        PAID,
        REFUSED;

        @Override
        public String toString() {
            switch (this){
                case ON_HOLD:{
                    return Data.getCurrentActivity().getResources().getString(R.string.repair_status_on_hold);
                }
                case OFFERED:{
                    return Data.getCurrentActivity().getResources().getString(R.string.repair_status_offered);
                }
                case ACCEPTED:{
                    return Data.getCurrentActivity().getResources().getString(R.string.repair_status_accepted);
                }
                case PAID:{
                    return Data.getCurrentActivity().getResources().getString(R.string.repair_status_paid);
                }
                case REFUSED:{
                    return Data.getCurrentActivity().getResources().getString(R.string.repair_status_refused);
                }
            }
            return null;
        }
    }

}
