package com.example.hamza.noteappp.Database;


import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
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
                    .addCallback(roomcallback)
                    .build();

        }
        return instance; }



private static RoomDatabase.Callback roomcallback =new RoomDatabase.Callback(){

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);
        new PopulateDbAsyncTask(instance).execute();
    }
};
   private static class PopulateDbAsyncTask extends AsyncTask<Void ,Void ,Void>{

       private Dao dao;

       public PopulateDbAsyncTask(Databasee database) {
        dao=database.noteDao();}

       @Override
       protected Void doInBackground(Void... voids) {
           dao.insertNote(new Note("Task one","You can add tasks by click +1  "+"\n"+"swipe to delete task","date"));

           return null;
       }
   }}

