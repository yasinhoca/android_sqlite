package com.example.sqlitedb;

public class Notes {
    private String note_id, note_text, note_date;

    public Notes(String note_id, String note_text) {
        this.note_id = note_id;
        this.note_text = note_text;
    }

    public String getNote_id() {
        return note_id;
    }

    public void setNote_id(String note_id) {
        this.note_id = note_id;
    }

    public String getNote_text() {
        return note_text;
    }

    public void setNote_text(String note_text) {
        this.note_text = note_text;
    }

    public String getNote_date() {
        return note_date;
    }

    public void setNote_date(String note_date) {
        this.note_date = note_date;
    }
}
