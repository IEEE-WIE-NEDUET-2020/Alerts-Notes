package com.example.reminderapp;

public class alerts_info {
    String Title, Details;

    public alerts_info(String Title, String Details){
        this.Title = Title;
        this.Details = Details;
    }

    public alerts_info(){

    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
