package com.example.hamza.noteappp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hamza.noteappp.dialog.Dialog;

public class MainActivity extends AppCompatActivity {

    RecyclerView rec;
    Button plus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        plus=findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog exampleDialog = new Dialog();
                exampleDialog.show(getSupportFragmentManager(), "example dialog");
            }
        });



    }
}