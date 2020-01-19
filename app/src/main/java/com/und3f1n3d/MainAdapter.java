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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mainrecycler_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = MainActivity.getNotes().get(position);
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
        return MainActivity.getNotes().size();
    }

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

    @FunctionalInterface
    public interface ClickListener{
        void onItemClick(int pos, View v);
    }

}
