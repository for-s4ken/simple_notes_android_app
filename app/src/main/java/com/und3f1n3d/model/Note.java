package com.und3f1n3d.model;

import java.util.Calendar;

public class Note {

    // FIELDS

    private String text;
    private String dateOfLastChange;

    // CONSTRUCTOR

    Note(String text, String dateOfLastChange){
        this.text = text;
        this.dateOfLastChange = dateOfLastChange;
    }

    // NOTE REDACTING METHOD

    private void changeText(String text) {
        this.text = text;
        this.dateOfLastChange = Calendar.getInstance().getTime().toString().substring(0, 19);
    }

    // GETTERS

    public String getText() { return text; }

    public String getDateOfLastChange() { return dateOfLastChange; }

}
