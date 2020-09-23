package com.example.hamza.noteappp.Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Note_data")
public class Note {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "note_id")
    private int id ;



    @ColumnInfo(name = "note_title")
    String title ;

    @ColumnInfo(name = "note_content")
    String content ;

    @ColumnInfo(name ="note_Checked")
    private boolean noteChecked;

    @ColumnInfo(name = "note_category")
    private String noteCategory;

    @ColumnInfo(name = "note_created_at")
    private long createdAt;

    public Note() {
    }

    public Note( String title, String content, boolean noteChecked, String noteCategory, long createdAt) {
        this.title=title;
        this.content = content;
        this.noteChecked = noteChecked;
        this.noteCategory = noteCategory;
        this.createdAt = createdAt; }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isNoteChecked() {
        return noteChecked;
    }

    public void setNoteChecked(boolean noteChecked) {
        this.noteChecked = noteChecked;
    }

    public String getNoteCategory() {
        return noteCategory;
    }

    public void setNoteCategory(String noteCategory) {
        this.noteCategory = noteCategory;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}





