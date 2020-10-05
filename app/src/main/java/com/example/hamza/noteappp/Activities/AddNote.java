package com.example.hamza.noteappp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hamza.noteappp.R;

public class AddNote extends AppCompatActivity {
    public static final String EXTRA_TITLE ="TITLE";
    public static final String EXTRA_DESCRIPTION ="CONTENT";


    private EditText title;
    private EditText content;
    private Button add;
    Intent data = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        title=findViewById(R.id.title);
        content=findViewById(R.id.contentt);










        add =findViewById(R.id.add_btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText().toString().trim().isEmpty() || content.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please insert a title and description", Toast.LENGTH_SHORT).show();
                    return;
                }

                data.putExtra(EXTRA_TITLE, title.getText().toString());
                data.putExtra(EXTRA_DESCRIPTION, content.getText().toString());

                setResult(RESULT_OK, data);
                Toast.makeText(getApplicationContext(), "Note saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });





    }
}