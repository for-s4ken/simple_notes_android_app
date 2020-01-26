package com.und3f1n3d;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.und3f1n3d.model.Note;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private static ClickListener clickListenerMain;

    public MainAdapter(ClickListener listener) {
        MainAdapter.clickListenerMain = listener;
    }

    // INFLATING LAYOUT

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mainrecycler_item, parent, false);
        return new ViewHolder(v);
    }

    // SHOWING FIRST LINE OF STRING

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = MainActivity.getNotes().get(position);
        holder.date.setText(note.getDateOfLastChange());
        String temp = note.getText();
        if(temp.contains(System.lineSeparator())) {
            holder.header.setText(temp.substring(0, temp.indexOf(System.lineSeparator())));
            if(holder.header.getText().length() > 19){
                temp = temp.substring(0, temp.indexOf(System.lineSeparator())).substring(0, 16) + "...";
                holder.header.setText(temp);
            }
        }else if(temp.length() > 19){
            temp = (note.getText().substring(0, 16) + "...");
            System.out.println(temp);
            holder.header.setText(temp);
        }else{
            holder.header.setText(temp);
        }
    }

    /////////////////////////////////


    @Override
    public int getItemCount() {
        if(MainActivity.getNotes() != null){
            return MainActivity.getNotes().size();
        }else{
            return 0;
        }
    }

    // VIEWHOLDER MODEL

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView header;
        private TextView date;
        private LinearLayout linearLayout;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.header);
            date = itemView.findViewById(R.id.date);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListenerMain.onItemClick(getAdapterPosition(), v);
        }

    }

    // THIS ALLOWS TO USE ONCLICK METHOD FOR VIEWHOLDERS

    @FunctionalInterface
    public interface ClickListener{
        void onItemClick(int pos, View v);
    }

}
