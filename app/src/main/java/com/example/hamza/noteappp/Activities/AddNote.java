package com.example.hamza.noteappp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.hamza.noteappp.R;
import com.example.hamza.noteappp.databinding.ActivityAddNoteBinding;

public class AddNote extends AppCompatActivity {
    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_TITLE ="TITLE";
    public static final String EXTRA_DESCRIPTION ="CONTENT";

    Intent data = new Intent();
    ActivityAddNoteBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);


         binding= DataBindingUtil.setContentView(this,R.layout.activity_add_note);




        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            binding.title.setText(intent.getStringExtra(EXTRA_TITLE));
            binding.contentt.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
        } else {
            setTitle("Add Note");
        }

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.title.getText().toString().trim().isEmpty() || binding.contentt.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please insert a title and Content", Toast.LENGTH_SHORT).show();
                    return;
                }

                data.putExtra(EXTRA_TITLE, binding.title.getText().toString());
                data.putExtra(EXTRA_DESCRIPTION, binding.contentt.getText().toString());

                setResult(RESULT_OK, data);
                Toast.makeText(getApplicationContext(), "Task saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
    public void update_task(View view) {

        if (binding.title.getText().toString().trim().isEmpty() || binding.contentt.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please insert a title and Content", Toast.LENGTH_SHORT).show();
            return;
        }
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
            data.putExtra(EXTRA_TITLE, binding.title.getText().toString());
            data.putExtra(EXTRA_DESCRIPTION, binding.contentt.getText().toString());
        }
        setResult(RESULT_OK, data);

        finish();

    }
}