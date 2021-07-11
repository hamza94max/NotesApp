package com.example.hamza.noteappp.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.hamza.noteappp.R;
import com.example.hamza.noteappp.databinding.ActivityAddNoteBinding;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_TITLE = "TITLE";
    private static final String Extra_DATE = "DATE";
    public static final String EXTRA_DESCRIPTION ="CONTENT";

    Intent dataintent = new Intent();
    ActivityAddNoteBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

         binding = DataBindingUtil.setContentView(this,R.layout.activity_add_note);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            binding.title.setText(intent.getStringExtra(EXTRA_TITLE));
            binding.content.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
        }

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (noteisEmpty()) {
                    insertToast();
                    return;
                }
                addData();
                setResult(RESULT_OK, dataintent);

                Toast.makeText(getApplicationContext(), "Note saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

        private Boolean noteisEmpty(){
         return binding.title.getText().toString().trim().isEmpty() || binding.content.getText().toString().trim().isEmpty();
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        private void addData(){
            dataintent.putExtra(EXTRA_TITLE, binding.title.getText().toString());
            dataintent.putExtra(EXTRA_DESCRIPTION,binding.content.getText().toString());
            dataintent.putExtra(Extra_DATE, getdate());
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        private String getdate(){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd ");
            LocalDateTime now = LocalDateTime.now();
            String date = dtf.format(now);
            return date; }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void update_task(View view) {

            if (noteisEmpty()) {
               insertToast();
                return;
            }
            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if (id != -1) {
               addData();
            }
            setResult(RESULT_OK, dataintent);
            finish();
    }

        private void insertToast(){
            Toast.makeText(getApplicationContext(), "Please insert a title and Content", Toast.LENGTH_SHORT).show();
        }
}