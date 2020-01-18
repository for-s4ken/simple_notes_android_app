package com.und3f1n3d;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.und3f1n3d.fragments.NoteEditFragment;
import com.und3f1n3d.model.Note;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Note> notes;
    private MainActivity main;

    public MainAdapter(MainActivity main) {
        this.notes = MainActivity.getNotes();
        this.main = main;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mainrecycler_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.date.setText(note.getDateOfLastChange());
        holder.linearLayout.setOnClickListener(l -> {
            NoteEditFragment.setNoteToEdit(notes.get(position));
            FragmentTransaction transaction = main.getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("notesListFragment")
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            transaction.replace(R.id.mainLayout, new NoteEditFragment(main));
            transaction.commit();
            main.changeMenuMode(false);
        });
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

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView header;
        private TextView date;
        private LinearLayout linearLayout;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.header);
            date = itemView.findViewById(R.id.date);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
