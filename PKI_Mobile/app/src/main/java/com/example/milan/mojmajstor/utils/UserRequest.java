package com.example.milan.mojmajstor.utils;

public class UserRequest {
    private String description;
    private String craftsman;
    private String date;
    private String status;

    public UserRequest(String description, String craftsman, String date, String status) {
        this.description = description;
        this.craftsman = craftsman;
        this.date = date;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCraftsman() {
        return craftsman;
    }

    public void setCraftsman(String craftsman) {
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
}
