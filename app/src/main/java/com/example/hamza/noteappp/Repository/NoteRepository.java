package com.example.hamza.noteappp.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.hamza.noteappp.Dao.Dao;
import com.example.hamza.noteappp.Database.Databasee;
import com.example.hamza.noteappp.Model.Note;

import java.util.List;

public class NoteRepository {

    private Dao dao;
    private LiveData<List<Note>> allnotes;


    public NoteRepository (Application application){
        Databasee database =Databasee.getInstance(application);
        dao =database.noteDao();
        allnotes= dao.getAllNotes();

    }

    public void insert(Note note){
        new InsertNoteAsyncTask(dao).execute(note);

    }
    public void update(Note note){
        new UpdateNoteAsyncTask(dao).execute(note);
    }
    public void delete(Note note){
        new DeleteNoteAsyncTask(dao).execute(note);

    }
    public void deleteAllNotes(){
        new DeleteAllNoteAsyncTask(dao).execute();

    }
    public LiveData<List<Note>>getAllnotes(){
        return allnotes;
    }



    private static class InsertNoteAsyncTask extends AsyncTask <Note,Void ,Void> {

        private Dao dao ;

        private InsertNoteAsyncTask(Dao dao){
            this.dao=dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            dao.insertNote(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask <Note,Void ,Void> {

        private Dao dao ;

        private UpdateNoteAsyncTask(Dao dao){
            this.dao=dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            dao.updateNote(notes[0]);
            return null;
        }
    }
    private static class DeleteNoteAsyncTask extends AsyncTask <Note,Void ,Void> {

        private Dao dao ;

        private DeleteNoteAsyncTask(Dao dao){
            this.dao=dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            dao.deleteNote(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask <Void,Void ,Void> {

        private Dao dao ;

        private DeleteAllNoteAsyncTask(Dao dao){
            this.dao=dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllNotes();
            return null;
        }
    }





}





