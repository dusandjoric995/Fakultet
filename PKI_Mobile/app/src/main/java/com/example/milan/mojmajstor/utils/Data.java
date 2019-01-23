package com.example.milan.mojmajstor.utils;

import com.example.milan.mojmajstor.LoginActivity;

import java.util.ArrayList;

public class Data {

    public ArrayList<User> users;
    public ArrayList<RepairRequest> repairRequests;
    public User currentUser;
    private static Data instance = new Data();

    private Data(){
        initialize();
    }

    private void initialize(){
        users = new ArrayList<>();
        repairRequests = new ArrayList<>();

        users.add(new User("Dusan", "Djoric", "dusandjoric995@gmail.com", "dusan", "dusan", LoginActivity.UserType.USER, "Inzenjer elektrotehnike"));
        users.add(new User("Milan", "Djoric", "milandjoric992@gmail.com", "milan", "milan", LoginActivity.UserType.CRAFTSMAN, "Limar"));
        users.add(new User("Zoran", "Djoric", "zorandjoric958@gmail.com", "zoran", "zoran", LoginActivity.UserType.USER, "Masinac"));
        users.add(new User("Sladjana", "Djoric", "sladjanadjoric959@gmail.com", "sladjana", "sladjana", LoginActivity.UserType.CRAFTSMAN, "Vodoinstaler"));
        users.add(new User("Jovana", "Cakic", "jovanacakic995@gmail.com", "jovana", "jovana", LoginActivity.UserType.USER, "Diplomirani inzenjer energetike"));
        users.add(new User("Jelena", "Petrovic", "jelenapetrovic995@gmail.com", "jelena", "jelena", LoginActivity.UserType.CRAFTSMAN, "Stolar"));

        repairRequests.add(new RepairRequest("Popravka motora", containsUsername("milan"), "10.10.2018.", "Prihvacen", containsUsername("dusan"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", true, 0, 10000));
        repairRequests.add(new RepairRequest("Popravka vrata", containsUsername("sladjana"), "10.10.2018.", "Prihvacen", containsUsername("zoran"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", true, 0, 10000));
        repairRequests.add(new RepairRequest("Popravka kreveta", containsUsername("jelena"), "10.10.2018.", "Prihvacen", containsUsername("jovana"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", true, 0, 10000));
        repairRequests.add(new RepairRequest("Popravka zida", containsUsername("milan"), "10.10.2018.", "Odbijen", containsUsername("dusan"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", true, 0, 10000));
        repairRequests.add(new RepairRequest("Popravka terase", containsUsername("sladjana"), "10.10.2018.", "Prihvacen", containsUsername("zoran"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", true, 0, 10000));
        repairRequests.add(new RepairRequest("Popravka kompjutera",containsUsername("jelena"), "10.10.2018.", "Prihvacen", containsUsername("jovana"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", true, 0, 10000));
        repairRequests.add(new RepairRequest("Popravka baterije", containsUsername("milan"), "10.10.2018.", "Prihvacen", containsUsername("dusan"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", false, 0, 10000));
        repairRequests.add(new RepairRequest("Popravka enterijera", containsUsername("sladjana"), "10.10.2018.", "Prihvacen", containsUsername("zoran"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", false, 0, 10000));
        repairRequests.add(new RepairRequest("Popravka stolice", containsUsername("jelena"), "10.10.2018.", "Prihvacen", containsUsername("jovana"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", false, 0, 10000));
        repairRequests.add(new RepairRequest("Popravka odece", containsUsername("milan"), "10.10.2018.", "Odbijen", containsUsername("dusan"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", false, 0, 10000));
        repairRequests.add(new RepairRequest("Popravka obuce", containsUsername("sladjana"), "10.10.2018.", "Odbijen", containsUsername("zoran"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", false, 0, 10000));
        repairRequests.add(new RepairRequest("Popravka skutera", containsUsername("jelena"), "10.10.2018.", "Prihvacen", containsUsername("jovana"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", false, 0, 10000));
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

    public ArrayList<RepairRequest> getCurrentUserRepairRequests() {
        ArrayList<RepairRequest> currentUserRepairRequests = new ArrayList<>();
        for(RepairRequest repairRequest : repairRequests){
            if(repairRequest.getClient() == currentUser){
                currentUserRepairRequests.add(repairRequest);
            }
        }
        return currentUserRepairRequests;
    }

    public void removeRepairRequest(RepairRequest repairRequest) {
        repairRequests.remove(repairRequest);
    }

    public void findSelectedCraftsmen(ArrayList<User> selectedCraftsmen, String name, String profession, User user) {
        selectedCraftsmen.clear();
        if(user != null){
            for(User craftsman : users){
                if(craftsman.getUserType() == LoginActivity.UserType.CRAFTSMAN && craftsman.getFirstAndLastName().toUpperCase().contains(name.toUpperCase()) && craftsman.getProfession().toUpperCase().contains(profession.toUpperCase()) && relatedCraftsmanAndClient(craftsman, user)){
                    selectedCraftsmen.add(craftsman);
                }
            }
        }
        else {
            for(User craftsman : users){
                if(craftsman.getUserType() == LoginActivity.UserType.CRAFTSMAN && craftsman.getFirstAndLastName().toUpperCase().contains(name.toUpperCase()) && craftsman.getProfession().toUpperCase().contains(profession.toUpperCase())){
                    selectedCraftsmen.add(craftsman);
                }
            }
        }
    }

    private boolean relatedCraftsmanAndClient(User craftsman, User user) {
        for(RepairRequest repairRequest : repairRequests){
            if(repairRequest.getCraftsman() == craftsman && repairRequest.getClient() == user){
                return true;
            }
        }
        return false;
    }
}
