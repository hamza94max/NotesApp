package com.example.hamza.noteappp.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.hamza.noteappp.Model.Note;
import com.example.hamza.noteappp.R;

public class Dialog extends AppCompatDialogFragment {


    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_items, null);
        final EditText title = view.findViewById(R.id.title);
        final EditText content = view.findViewById(R.id.content);
        builder.setView(view)
                .setTitle("Login")
                .setNegativeButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Note note =new Note();
                        note.setContent(content.getText().toString());
                        note.setTitle(title.getText().toString());



                    }
                });


        return builder.create();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

}




