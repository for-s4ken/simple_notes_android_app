package com.und3f1n3d.fragments;

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

    private FragmentActivity main;
    private View rootView;
    private RecyclerView mainRecycler;
    private RecyclerView.Adapter mainAdapter;

    public NotesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_notes_list, container, false);
        mainRecycler = rootView.findViewById(R.id.mainRecycler);
        mainRecycler.setHasFixedSize(true);
        mainRecycler.setLayoutManager(new LinearLayoutManager(main.getBaseContext()));
        mainAdapter = new MainAdapter(MainActivity.getNotes(), getFragmentManager());
        mainRecycler.setAdapter(mainAdapter);
        System.out.println(mainAdapter.getItemCount());
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        main = getActivity();
        super.onAttach(context);
    }
}
