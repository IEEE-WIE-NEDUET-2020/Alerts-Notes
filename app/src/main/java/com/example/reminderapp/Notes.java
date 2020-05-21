package com.example.reminderapp;

import java.util.Date;

public class Notes {
    int id;
    String title, desc, type;
    Date reminder_date;



    public Notes(int id, String title, String desc, Date reminder_date, String type) {
        this.title = title;
        this.id= id;
        this.desc = desc;
        this.reminder_date = reminder_date;
        this.type =type;
    }

    public Notes() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getReminder_date() {
        return reminder_date;
    }

//    public String getType() { return type;
//    }
//
//    public void setType(String type) { this.type = type;
//    }



    public void setReminder_date(Date reminder_date) {
        this.reminder_date = reminder_date;
    }
}