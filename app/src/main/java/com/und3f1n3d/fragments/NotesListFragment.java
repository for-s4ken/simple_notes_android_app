package com.und3f1n3d.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.und3f1n3d.R;


public class NotesListFragment extends Fragment {

    private View rootView;
    private RecyclerView mainRcycler;

    public NotesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_notes_list, container, false);
        mainRcycler = rootView.findViewById(R.id.mainRecycler);
        mainRcycler.setHasFixedSize(true);
        return rootView;
    }

}
