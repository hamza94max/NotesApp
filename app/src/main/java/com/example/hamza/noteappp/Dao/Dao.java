package com.example.hamza.noteappp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.hamza.noteappp.Model.Note;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    long insertNote(Note noteData);


    @Update
    int updateNote(Note noteData);


    @Query("SELECT * FROM note_data")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM note_data WHERE note_id = :noteId LIMIT 1")
    Note getOneNote(int noteId);

    @Delete
    void deleteNote(Note note);

    @Query ("DELETE FROM note_data")
    void deleteAllNotes();






}
