package com.example.hamza.noteappp.Activities;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.hamza.noteappp.Adapter.NoteAdapter;
import com.example.hamza.noteappp.Model.Note;
import com.example.hamza.noteappp.R;
import com.example.hamza.noteappp.databinding.ActivityMainBinding;
import com.example.hamza.noteappp.viewmodel.ViewModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    private ViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);


        binding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), AddNoteActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });



        binding.rec.setHasFixedSize(true);
        final NoteAdapter adapter = new NoteAdapter();
        binding.rec.setAdapter(adapter);
        binding.rec.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));




        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.rec);



        noteViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                //update RecyclerView
                adapter.setNotes(notes);
            }
        });

        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                intent.putExtra(AddNoteActivity.EXTRA_ID, note.getId());
                intent.putExtra(AddNoteActivity.EXTRA_TITLE, note.getTitle());
                intent.putExtra(AddNoteActivity.EXTRA_DESCRIPTION, note.getContent());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        }); }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteAll:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (!isFinishing()){
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle(" Alert")
                                    .setMessage("Do you really want to delete all notes ?")
                                    .setCancelable(true)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // Whatever...

                                            noteViewModel.deleteAllNotes();
                                            Toast.makeText(getApplicationContext(), "All notes are deleted", Toast.LENGTH_SHORT).show();
                                        }
                                    }).show();
                        }
                    }
                });


                return true;

        }

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getdate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd ");
        LocalDateTime now = LocalDateTime.now();
        String date =dtf.format(now);
        return date;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION);
            String date =getdate();
            Note note = new Note(title, description,date);
            noteViewModel.insert(note);
            Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show();

        } else if (requestCode==EDIT_NOTE_REQUEST&&resultCode==RESULT_OK){
            int id = data.getIntExtra(AddNoteActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Note can't be updated , Please click update", Toast.LENGTH_SHORT).show();
                return;
            }
            String title = data.getStringExtra(AddNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION);
            Note note = new Note(title, description,getdate());
            note.setId(id);
            noteViewModel.update(note);
            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }}