package com.example.hamza.noteappp.Dao;

import androidx.lifecycle.LiveData;
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
    List<Note> getAllNotes();

    @Query("SELECT * FROM note_data WHERE note_id = :noteId LIMIT 1")
    Note getOneNote(int noteId);

    @Query("SELECT * FROM note_data WHERE note_checked = 1 ORDER BY note_created_at DESC")
    LiveData<List<Note>> getAllMarkedNotes();

    @Query("SELECT * FROM note_data WHERE note_checked = 0 ORDER BY note_created_at DESC")
    LiveData<List<Note>> getAllUnMarkedNotes();

    @Query("SELECT * FROM note_data WHERE note_category LIKE :noteCategory ORDER BY note_created_at DESC")
    LiveData<List<Note>> getAllNotesWithCategory(String noteCategory);

    @Query ("DELETE FROM note_data")
    void deleteAllNotes();






}
