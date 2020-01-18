package com.und3f1n3d;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.und3f1n3d.fragments.NoteEditFragment;
import com.und3f1n3d.model.Note;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Note> notes;
    private FragmentManager manager;

    public MainAdapter(List notes, FragmentManager manager) {
        this.notes = notes;
        this.manager = manager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mainrecycler_item, parent, false);
        ViewHolder holder = new ViewHolder(v);
        holder.linearLayout.setOnClickListener(s -> {
            FragmentTransaction transaction = manager
                    .beginTransaction()
                    .addToBackStack("notesListFragment")
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            transaction.replace(R.id.mainLayout, new NoteEditFragment());
            transaction.commit();
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.date.setText(note.getDateOfLastChange());
        if(note.getText().length() > 19){
            String temp = (note.getText().substring(0, 16) + "...");
            holder.header.setText(temp);
        }else{
            holder.header.setText(note.getText());
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView header;
        private TextView date;
        private LinearLayout linearLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.header);
            date = itemView.findViewById(R.id.date);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

}
