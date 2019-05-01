package com.example.notesapp;

public class Data {

    String title;
    String note;
    String notedatee;
    String id;

    public Data() {
    }

    public Data(String title, String note, String notedatee, String id) {
        this.title = title;
        this.note = note;
        this.notedatee = notedatee;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNotedatee() {
        return notedatee;
    }

    public void setNotedatee(String notedatee) {
        this.notedatee = notedatee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
