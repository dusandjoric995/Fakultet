package com.example.milan.mojmajstor.utils;

import com.example.milan.mojmajstor.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {



    public ArrayList<User> users;
    public ArrayList<UserRequest> userRequests;
    public User currentUser;
    private static Data instance = new Data();

    private Data(){
        initialize();
    }

    private void initialize(){
        users = new ArrayList<>();
        userRequests = new ArrayList<>();

        users.add(new User("Dusan", "Djoric", "dusandjoric995@gmail.com", "dusan", "dusan", LoginActivity.UserType.USER));
        users.add(new User("Milan", "Djoric", "milandjoric992@gmail.com", "milan", "milan", LoginActivity.UserType.CRAFTSMAN));
        users.add(new User("Zoran", "Djoric", "zorandjoric958@gmail.com", "zoran", "zoran", LoginActivity.UserType.USER));
        users.add(new User("Sladjana", "Djoric", "sladjanadjoric959@gmail.com", "sladjana", "sladjana", LoginActivity.UserType.CRAFTSMAN));
        users.add(new User("Jovana", "Cakic", "jovanacakic995@gmail.com", "jovana", "jovana", LoginActivity.UserType.USER));
        users.add(new User("Jelena", "Petrovic", "jelenapetrovic995@gmail.com", "jelena", "jelena", LoginActivity.UserType.CRAFTSMAN));

        userRequests.add(new UserRequest("Popravka motora", "Zoran Djoric", "10.10.2018.", "Prihvacen"));
        userRequests.add(new UserRequest("Popravka vrata", "Dusan Djoric", "10.10.2018.", "Prihvacen"));
        userRequests.add(new UserRequest("Popravka kreveta", "Milan Djoric", "10.10.2018.", "Prihvacen"));
        userRequests.add(new UserRequest("Popravka zida", "Sladjana Djoric", "10.10.2018.", "Prihvacen"));
        userRequests.add(new UserRequest("Popravka terase", "Zivka Djoric", "10.10.2018.", "Prihvacen"));
        userRequests.add(new UserRequest("Popravka kompjutera", "Gordana Petkovic", "10.10.2018.", "Prihvacen"));
        userRequests.add(new UserRequest("Popravka baterije", "Jovana Cakic", "10.10.2018.", "Prihvacen"));
        userRequests.add(new UserRequest("Popravka enterijera", "Jelena Petrovic", "10.10.2018.", "Prihvacen"));
        userRequests.add(new UserRequest("Popravka stolice", "Vladimir Antic", "10.10.2018.", "Prihvacen"));
        userRequests.add(new UserRequest("Popravka odece", "Jelena Antic", "10.10.2018.", "Prihvacen"));
        userRequests.add(new UserRequest("Popravka obuce", "Mila Antic", "10.10.2018.", "Prihvacen"));
        userRequests.add(new UserRequest("Popravka skutera", "Zoran Antic", "10.10.2018.", "Prihvacen"));
    }

    public static Data getInstance(){
        return instance;
    }

    public User containsUsername(String username){
        User foundUser = null;
        for(User user : users){
            if(user.getUsername().equals(username)){
                foundUser = user;
                break;
            }
        }
        return foundUser;
    }

    public boolean checkPassword(User user, String password){
        return user.getPassword().equals(password);
    }

}
