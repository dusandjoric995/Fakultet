package com.example.milan.mojmajstor.utils;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {

    public static HashMap<String, String> users;
    public static ArrayList<UserRequest> userRequests;
    public static User currentUser;

    public static void initialize(){
        users = new HashMap<>();
        userRequests = new ArrayList<>();

        users.put("dusan", "dusan");
        users.put("milan", "milan");
        users.put("zoran", "zoran");
        users.put("sladjana", "sladjana");

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

}
