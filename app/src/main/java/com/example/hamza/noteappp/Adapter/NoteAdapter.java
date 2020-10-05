package com.example.hamza.noteappp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hamza.noteappp.Model.Note;
import com.example.hamza.noteappp.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.Viewholder> {

    private static OnItemClickListener listener;

    public static ArrayList<Note> arrayList = new ArrayList<>();

    public NoteAdapter() {
    }


    public NoteAdapter(ArrayList<Note> models, Context context) {
        this.arrayList = models;
    }

    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.rec_item, parent, false);


        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.Viewholder holder, int position) {
        Note currentNote = arrayList.get(position);

        holder.titlee.setText(currentNote.getTitle());
        holder.contenntt.setText(currentNote.getContent());


    }
    public Note getNoteAt(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public void setNotes(List<Note> notes) {
        this.arrayList = (ArrayList<Note>) notes;
        notifyDataSetChanged();
    }
    static class Viewholder extends RecyclerView.ViewHolder {

        private TextView titlee;
        private TextView contenntt;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            titlee=itemView.findViewById(R.id.title_text);
            contenntt=itemView.findViewById(R.id.description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(arrayList.get(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Note note);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}