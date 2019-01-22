package com.example.milan.mojmajstor.utils;

import com.example.milan.mojmajstor.LoginActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private LoginActivity.UserType userType;
    private double rate;
    private int rateCount;
    private ArrayList<String> comments;
    private String profession;

    public User(String firstName, String lastName, String email, String username, String password, LoginActivity.UserType userType, String profession) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userType =userType;
        this.rate = 0;
        this.rateCount = 0;
        this.comments = new ArrayList<>();
        this.profession = profession;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginActivity.UserType getUserType() {
        return userType;
    }

    public void setUserType(LoginActivity.UserType userType) {
        this.userType = userType;
    }

    public boolean checkPassword(String password){
        return this.password == password;
    }

    public boolean checkUsername(String username){
        return this.username == username;
    }

    public String getFirstAndLastName(){
        return firstName + " " + lastName;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getRateCount() {
        return rateCount;
    }

    public void setRateCount(int rateCount) {
        this.rateCount = rateCount;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public void addComent(String comment){
        comments.add(comment);
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
