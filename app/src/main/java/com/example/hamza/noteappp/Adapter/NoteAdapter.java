package com.example.hamza.noteappp.Adapter;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hamza.noteappp.Model.Note;
import com.example.hamza.noteappp.R;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.Viewholder> {

    private static OnItemClickListener listener;

    public static ArrayList<Note> arrayList = new ArrayList<>();

    public NoteAdapter(){}


    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.rec_item, parent, false);


        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Note currentNote = arrayList.get(position);

        holder.titlee.setText(currentNote.getTitle());


        holder.cardView.setCardBackgroundColor(Color.YELLOW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.date.setText(currentNote.getDate());
        }}

    public Note getNoteAt(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public void setNotes(List<Note> notes) {
        arrayList = (ArrayList<Note>) notes;
        notifyDataSetChanged();
    }
    static class Viewholder extends RecyclerView.ViewHolder {

        private final TextView titlee;
        private final TextView date;
        private final CardView cardView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            cardView =itemView.findViewById(R.id.card);
            titlee=itemView.findViewById(R.id.title_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(arrayList.get(position));
                    }
                }
            });

            date=itemView.findViewById(R.id.date);



        }
    }
    public interface OnItemClickListener {
        void onItemClick(Note note);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        NoteAdapter.listener = listener;
    }



/*
    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getdate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd ");
        LocalDateTime now = LocalDateTime.now();
        String date =dtf.format(now);
        return date;
    }
*/

    public int randomColor (){


      List<Integer> colors=new ArrayList<>();
      colors.add(Color.YELLOW);
      colors.add(Color.BLUE);


         /*
        colors.add(R.color.d);
        colors.add(R.color.b);
        colors.add(R.color.a);
        colors.add(R.color.c);
        colors.add(R.color.f);
        */

        Random rand = new Random();
        int Color=rand.nextInt(colors.size());
        return colors.get(Color); }
}

