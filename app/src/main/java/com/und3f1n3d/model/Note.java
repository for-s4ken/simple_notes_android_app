package com.und3f1n3d.model;

import com.und3f1n3d.MainActivity;

import java.util.Calendar;

public class Note {

    // FIELDS

    private String text;
    private String dateOfLastChange;
    private int id;

    // CONSTRUCTOR

    private Note(String text, String dateOfLastChange, int id){
        this.text = text;
        this.dateOfLastChange = dateOfLastChange;
        this.id = id;
    }

    // NOTE REDACTING METHODS

    public void changeText(String text) {
        this.text = text;
        this.dateOfLastChange = Calendar.getInstance().getTime().toString().substring(0, 19);
    }

    public static Note makeNewNote(String text){
        return new Note(text, Calendar.getInstance().getTime().toString().substring(0, 19), MainActivity.getNotes().size());
    }

    // GETTERS

    public String getText() { return text; }

    public String getDateOfLastChange() { return dateOfLastChange; }

    public int getId() { return id; }

    // TO STRING

    @Override
    public String toString() {
        return "Note{" +
                "text='" + text + '\'' +
                ", dateOfLastChange='" + dateOfLastChange + '\'' +
                '}';
    }
}
