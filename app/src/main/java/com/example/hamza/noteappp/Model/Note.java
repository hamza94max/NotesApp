package com.example.hamza.noteappp.Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

    @Entity(tableName = "Note_data")
    public class Note {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    private int id;

    @ColumnInfo(name = "note_title")
    String title;

    @ColumnInfo(name = "note_content")
    String content;

    @ColumnInfo(name = "note_date")
    String date;

    public Note() {}

    public Note(String title, String content,String date) {
        this.title = title;
        this.content = content;
        this.date=date;}

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
            return date;
        }

    public void setDate(String date) {
            this.date = date;
        }}





