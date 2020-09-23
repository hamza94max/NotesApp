package com.example.hamza.noteappp.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hamza.noteappp.Dao.Dao;
import com.example.hamza.noteappp.Model.Note;

@Database(entities = Note.class,version = 1 )
public abstract class Databasee extends RoomDatabase {

    private static Databasee instance;
    public abstract Dao noteDao();


    public static synchronized Databasee getInstance(Context context){

        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext()
                    , Databasee.class,"Note_database")
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance; }

}





