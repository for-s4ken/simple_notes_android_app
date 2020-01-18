package com.und3f1n3d.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.und3f1n3d.MainActivity;
import com.und3f1n3d.MainAdapter;
import com.und3f1n3d.R;


public class NotesListFragment extends Fragment {

    // FIELDS

    private Context context;
    private View rootView;
    private RecyclerView mainRecycler;
    private RecyclerView.Adapter mainAdapter;
    private MainActivity main;

    //CONSTRUCTORS

    public NotesListFragment() {
        // Required empty public constructor
    }

    public NotesListFragment(MainActivity main){
        this.main = main;
    }

    // METHODS

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_notes_list, container, false);
        mainRecycler = rootView.findViewById(R.id.mainRecycler);
        mainRecycler.setHasFixedSize(true);
        mainRecycler.setLayoutManager(new LinearLayoutManager(context));
        mainAdapter = new MainAdapter(main);
        mainRecycler.setAdapter(mainAdapter);
        mainRecycler.setOnClickListener(l -> {

        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
