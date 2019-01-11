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

        userRequests.add(new UserRequest("Popravka motora", containsUsername("milan"), "10.10.2018.", "Prihvacen", containsUsername("dusan"), "Novi Beograd", "Bul. Zorana Djondjica 111/1/1", true, 1500));
        userRequests.add(new UserRequest("Popravka vrata", containsUsername("sladjana"), "10.10.2018.", "Prihvacen", containsUsername("zoran"), "Novi Beograd", "Bul. Zorana Djondjica 111/1/1", true, 2500));
        userRequests.add(new UserRequest("Popravka kreveta", containsUsername("jelena"), "10.10.2018.", "Prihvacen", containsUsername("jovana"), "Novi Beograd", "Bul. Zorana Djondjica 111/1/1", true, 3500));
        userRequests.add(new UserRequest("Popravka zida", containsUsername("milan"), "10.10.2018.", "Prihvacen", containsUsername("dusan"), "Novi Beograd", "Bul. Zorana Djondjica 111/1/1", true, 4500));
        userRequests.add(new UserRequest("Popravka terase", containsUsername("sladjana"), "10.10.2018.", "Prihvacen", containsUsername("zoran"), "Novi Beograd", "Bul. Zorana Djondjica 111/1/1", true, 5500));
        userRequests.add(new UserRequest("Popravka kompjutera",containsUsername("jelena"), "10.10.2018.", "Prihvacen", containsUsername("jovana"), "Novi Beograd", "Bul. Zorana Djondjica 111/1/1", true, 6500));
        userRequests.add(new UserRequest("Popravka baterije", containsUsername("milan"), "10.10.2018.", "Prihvacen", containsUsername("dusan"), "Novi Beograd", "Bul. Zorana Djondjica 111/1/1", false, 0));
        userRequests.add(new UserRequest("Popravka enterijera", containsUsername("sladjana"), "10.10.2018.", "Prihvacen", containsUsername("zoran"), "Novi Beograd", "Bul. Zorana Djondjica 111/1/1", false, 0));
        userRequests.add(new UserRequest("Popravka stolice", containsUsername("jelena"), "10.10.2018.", "Prihvacen", containsUsername("jovana"), "Novi Beograd", "Bul. Zorana Djondjica 111/1/1", false, 0));
        userRequests.add(new UserRequest("Popravka odece", containsUsername("milan"), "10.10.2018.", "Prihvacen", containsUsername("dusan"), "Novi Beograd", "Bul. Zorana Djondjica 111/1/1", false, 0));
        userRequests.add(new UserRequest("Popravka obuce", containsUsername("sladjana"), "10.10.2018.", "Prihvacen", containsUsername("zoran"), "Novi Beograd", "Bul. Zorana Djondjica 111/1/1", false, 0));
        userRequests.add(new UserRequest("Popravka skutera", containsUsername("jelena"), "10.10.2018.", "Prihvacen", containsUsername("jovana"), "Novi Beograd", "Bul. Zorana Djondjica 111/1/1", false, 0));
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
