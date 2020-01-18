package com.und3f1n3d.model;

import java.util.Calendar;

public class Note {

    // FIELDS

    private String text;
    private String dateOfLastChange;

    // CONSTRUCTOR

    private Note(String text, String dateOfLastChange){
        this.text = text;
        this.dateOfLastChange = dateOfLastChange;
    }

    // NOTE REDACTING METHODS

    public void changeText(String text) {
        this.text = text;
        this.dateOfLastChange = Calendar.getInstance().getTime().toString().substring(0, 19);
    }

    public static Note makeNewNote(String text){
        return new Note(text, Calendar.getInstance().getTime().toString().substring(0, 19));
    }

    // GETTERS

    public String getText() { return text; }

    public String getDateOfLastChange() { return dateOfLastChange; }

    // TO STRING

    @Override
    public String toString() {
        return "Note{" +
                "text='" + text + '\'' +
                ", dateOfLastChange='" + dateOfLastChange + '\'' +
                '}';
    }
}
