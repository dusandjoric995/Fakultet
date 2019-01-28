package com.example.milan.mojmajstor.utils;

import android.app.Activity;
import android.app.Application;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.milan.mojmajstor.LoginActivity;

import java.util.ArrayList;
import java.util.Date;

public class Data {

    public ArrayList<User> users;
    public ArrayList<RepairRequest> repairRequests;
    public User currentUser;
    private static Data instance = null;
    private static Activity currentActivity;

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(Activity currentActivity) {
        Data.currentActivity = currentActivity;
    }

    public static void initiate(){
        if(instance == null){
            instance = new Data();
        }
    }

    private Data(){
        initialize();
    }

    private void initialize(){
        users = new ArrayList<>();
        repairRequests = new ArrayList<>();

        users.add(new User("Dusan", "Djoric", "dusandjoric995@gmail.com", "Inzenjer elektrotehnike", "dusan", "dusan", LoginActivity.UserType.USER));
        users.add(new User("Milan", "Djoric", "milandjoric992@gmail.com", "Limar", "milan", "milan", LoginActivity.UserType.CRAFTSMAN));
        users.add(new User("Zoran", "Djoric", "zorandjoric958@gmail.com", "Masinac", "zoran", "zoran", LoginActivity.UserType.USER));
        users.add(new User("Sladjana", "Djoric", "sladjanadjoric959@gmail.com", "Vodoinstaler", "sladjana", "sladjana", LoginActivity.UserType.CRAFTSMAN));
        users.add(new User("Jovana", "Cakic", "jovanacakic995@gmail.com", "Diplomirani inzenjer energetike", "jovana", "jovana", LoginActivity.UserType.USER));
        users.add(new User("Jelena", "Petrovic", "jelenapetrovic995@gmail.com", "Stolar", "jelena", "jelena", LoginActivity.UserType.CRAFTSMAN));

        repairRequests.add(new RepairRequest("Popravka motora", containsUsername("milan"), "10.10.2018.", RepairRequest.Status.ACCEPTED, containsUsername("dusan"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", true, 0, 10000, RepairRequest.Severity.MEDIUM));
        repairRequests.add(new RepairRequest("Popravka vrata", containsUsername("sladjana"), "10.10.2018.", RepairRequest.Status.ACCEPTED, containsUsername("zoran"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", true, 0, 10000, RepairRequest.Severity.MEDIUM));
        repairRequests.add(new RepairRequest("Popravka kreveta", containsUsername("jelena"), "10.10.2018.", RepairRequest.Status.ACCEPTED, containsUsername("jovana"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", true, 0, 10000, RepairRequest.Severity.MEDIUM));
        repairRequests.add(new RepairRequest("Popravka zida", containsUsername("milan"), "10.10.2018.", RepairRequest.Status.ACCEPTED, containsUsername("dusan"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", true, 0, 10000, RepairRequest.Severity.LOW));
        repairRequests.add(new RepairRequest("Popravka terase", containsUsername("sladjana"), "10.10.2018.", RepairRequest.Status.ACCEPTED, containsUsername("zoran"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", true, 0, 10000, RepairRequest.Severity.LOW));
        repairRequests.add(new RepairRequest("Popravka kompjutera",containsUsername("jelena"), "10.10.2018.", RepairRequest.Status.ACCEPTED, containsUsername("jovana"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", true, 0, 10000, RepairRequest.Severity.LOW));
        repairRequests.add(new RepairRequest("Popravka baterije", containsUsername("milan"), "10.10.2018.", RepairRequest.Status.ACCEPTED, containsUsername("dusan"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", false, 0, 10000, RepairRequest.Severity.HIGH));
        repairRequests.add(new RepairRequest("Popravka enterijera", containsUsername("sladjana"), "10.10.2018.", RepairRequest.Status.ACCEPTED, containsUsername("zoran"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", false, 0, 10000, RepairRequest.Severity.HIGH));
        repairRequests.add(new RepairRequest("Popravka stolice", containsUsername("jelena"), "10.10.2018.", RepairRequest.Status.ACCEPTED, containsUsername("jovana"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", false, 0, 10000, RepairRequest.Severity.HIGH));
        repairRequests.add(new RepairRequest("Popravka odece", containsUsername("milan"), "10.10.2018.", RepairRequest.Status.REFUSED, containsUsername("dusan"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", false, 0, 10000, RepairRequest.Severity.MEDIUM));
        repairRequests.add(new RepairRequest("Popravka obuce", containsUsername("sladjana"), "10.10.2018.", RepairRequest.Status.REFUSED, containsUsername("zoran"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", false, 0, 10000, RepairRequest.Severity.LOW));
        repairRequests.add(new RepairRequest("Popravka skutera", containsUsername("jelena"), "10.10.2018.", RepairRequest.Status.REFUSED, containsUsername("jovana"), "Novi Beograd", "Bul. Zorana Djindjica 111/1/1", false, 0, 10000, RepairRequest.Severity.HIGH));
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

    public void getCurrentUserRepairRequests(ArrayList<RepairRequest> currentUserRepairRequests, LoginActivity.UserType userType) {
        currentUserRepairRequests.clear();
        switch (userType){
            case USER:{
                for(RepairRequest repairRequest : repairRequests){
                    if(repairRequest.getClient() == currentUser){
                        currentUserRepairRequests.add(repairRequest);
                    }
                }
                break;
            }
            case CRAFTSMAN:{
                for(RepairRequest repairRequest : repairRequests){
                    if(repairRequest.getCraftsman() == currentUser){
                        currentUserRepairRequests.add(repairRequest);
                    }
                }
                break;
            }
        }
    }

    public void removeRepairRequest(RepairRequest repairRequest) {
        repairRequests.remove(repairRequest);
    }

    public void findSelectedCraftsmen(ArrayList<User> selectedCraftsmen, String name, String profession, User currentUser) {
        selectedCraftsmen.clear();
        if(currentUser != null){
            for(User craftsman : users){
                if(craftsman.getUserType() == LoginActivity.UserType.CRAFTSMAN && craftsman.getFirstAndLastName().toUpperCase().contains(name.toUpperCase()) && craftsman.getProfession().toUpperCase().contains(profession.toUpperCase()) && relatedCraftsmanAndClient(craftsman, currentUser)){
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

    private void findFilteredRepairRequests(ArrayList<RepairRequest> filteredRepairRequests, EditText etFilterDescription, EditText etFilterClient, EditText etFilterDistrict, EditText etFilterAddress,
                                            EditText etFilterDate, CheckBox cbSeverityLow, CheckBox cbSeverityMedium, CheckBox cbSeverityHigh,
                                            CheckBox cbStatusOnHold, CheckBox cbStatusOffered, CheckBox cbStatusAccepted, CheckBox cbStatusPaid, CheckBox cbStatusRefused){
        filteredRepairRequests.clear();
        for(RepairRequest repairRequest : repairRequests){
            if(repairRequest.getDescription().toUpperCase().contains(etFilterDescription.getText().toString()) &&
                    repairRequest.getClient().getFirstAndLastName().toUpperCase().contains(etFilterClient.getText().toString()) &&
                    repairRequest.getDistrict().toUpperCase().contains(etFilterDistrict.getText().toString()) &&
                    repairRequest.getAddress().toUpperCase().contains(etFilterAddress.getText().toString()) &&
                    repairRequest.getDate().toUpperCase().contains(etFilterDate.getText().toString()) &&
                    (!cbSeverityLow.isChecked() || repairRequest.getSeverity() == RepairRequest.Severity.LOW) &&
                    (!cbSeverityMedium.isChecked() || repairRequest.getSeverity() == RepairRequest.Severity.MEDIUM) &&
                    (!cbSeverityHigh.isChecked() || repairRequest.getSeverity() == RepairRequest.Severity.HIGH) &&
                    (!cbStatusOnHold.isChecked() || repairRequest.getStatus() == RepairRequest.Status.ON_HOLD) &&
                    (!cbStatusOffered.isChecked() || repairRequest.getStatus() == RepairRequest.Status.OFFERED) &&
                    (!cbStatusAccepted.isChecked() || repairRequest.getStatus() == RepairRequest.Status.ACCEPTED) &&
                    (!cbStatusPaid.isChecked() || repairRequest.getStatus() == RepairRequest.Status.PAID) &&
                    (!cbStatusRefused.isChecked() || repairRequest.getStatus() == RepairRequest.Status.REFUSED)
                    ){
                filteredRepairRequests.add(repairRequest);
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

    public void sortRepairRequests(ArrayList<RepairRequest> repairRequests, int sortingOption) {
        if (repairRequests.size() > 0) {
            switch (sortingOption) {
                case 0: { // Description
                    if (repairRequests.get(0).getDescription().toUpperCase().compareTo(repairRequests.get(repairRequests.size() - 1).getDescription().toUpperCase()) >= 0) {
                        for (int i = 0; i < repairRequests.size() - 1; i++) {
                            for (int j = i; j < repairRequests.size(); j++) {
                                if (repairRequests.get(i).getDescription().toUpperCase().compareTo(repairRequests.get(j).getDescription().toUpperCase()) > 0) {
                                    RepairRequest temp = repairRequests.get(i);
                                    repairRequests.set(i, repairRequests.get(j));
                                    repairRequests.set(j, temp);
                                }
                            }
                        }
                    }
                    else{
                        for (int i = 0; i < repairRequests.size() - 1; i++) {
                            for (int j = i; j < repairRequests.size(); j++) {
                                if (repairRequests.get(i).getDescription().toUpperCase().compareTo(repairRequests.get(j).getDescription().toUpperCase()) < 0) {
                                    RepairRequest temp = repairRequests.get(i);
                                    repairRequests.set(i, repairRequests.get(j));
                                    repairRequests.set(j, temp);
                                }
                            }
                        }
                    }
                    break;
                }
                case 1: { // Date

                    break;
                }
                case 2: { // Severity
                    if (repairRequests.get(0).getSeverity().ordinal() >= repairRequests.get(repairRequests.size() - 1).getSeverity().ordinal()) {
                        for (int i = 0; i < repairRequests.size() - 1; i++) {
                            for (int j = i; j < repairRequests.size(); j++) {
                                if (repairRequests.get(i).getSeverity().ordinal() > repairRequests.get(j).getSeverity().ordinal()) {
                                    RepairRequest temp = repairRequests.get(i);
                                    repairRequests.set(i, repairRequests.get(j));
                                    repairRequests.set(j, temp);
                                }
                            }
                        }
                    }
                    else{
                        for (int i = 0; i < repairRequests.size() - 1; i++) {
                            for (int j = i; j < repairRequests.size(); j++) {
                                if (repairRequests.get(i).getSeverity().ordinal() < repairRequests.get(j).getSeverity().ordinal()) {
                                    RepairRequest temp = repairRequests.get(i);
                                    repairRequests.set(i, repairRequests.get(j));
                                    repairRequests.set(j, temp);
                                }
                            }
                        }
                    }
                    break;
                }
                case 3: { // Status
                    if (repairRequests.get(0).getStatus().ordinal() >= repairRequests.get(repairRequests.size() - 1).getStatus().ordinal()) {
                        for (int i = 0; i < repairRequests.size() - 1; i++) {
                            for (int j = i; j < repairRequests.size(); j++) {
                                if (repairRequests.get(i).getStatus().ordinal() > repairRequests.get(j).getStatus().ordinal()) {
                                    RepairRequest temp = repairRequests.get(i);
                                    repairRequests.set(i, repairRequests.get(j));
                                    repairRequests.set(j, temp);
                                }
                            }
                        }
                    }
                    else{
                        for (int i = 0; i < repairRequests.size() - 1; i++) {
                            for (int j = i; j < repairRequests.size(); j++) {
                                if (repairRequests.get(i).getStatus().ordinal() < repairRequests.get(j).getStatus().ordinal()) {
                                    RepairRequest temp = repairRequests.get(i);
                                    repairRequests.set(i, repairRequests.get(j));
                                    repairRequests.set(j, temp);
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    private boolean dateBeforeDate(String date1, String date2) {
        Date date1d = new Date(date1);
        Date date2d = new Date(date2);
        if(date1d.before(date2d)){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean dateAfterDate(String date1, String date2) {
        Date date1d = new Date(date1);
        Date date2d = new Date(date2);
        if(date1d.after(date2d)){
            return true;
        }
        else{
            return false;
        }
    }
}
