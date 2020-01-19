package com.und3f1n3d.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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

    private View rootView;
    private RecyclerView mainRecycler;
    private RecyclerView.Adapter mainAdapter;
    private FragmentActivity main;

    private static boolean noteEditActive = false;

    //CONSTRUCTORS

    public NotesListFragment() {
        // Required empty public constructor
    }

    // METHODS

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_notes_list, container, false);
        noteEditActive = false;
        mainRecycler = rootView.findViewById(R.id.mainRecycler);
        mainRecycler.setHasFixedSize(true);
        mainRecycler.setLayoutManager(new LinearLayoutManager(main.getBaseContext()));
        mainAdapter = new MainAdapter((pos, v) ->{
            NoteEditFragment.setNoteToEdit(MainActivity.getNotes().get(pos));
            noteEditActive = true;
            main.invalidateOptionsMenu();
            FragmentTransaction transaction = main.getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .addToBackStack("noteEditFragment");
            transaction.replace(R.id.mainLayout, new NoteEditFragment());
            transaction.commit();
        });
        mainRecycler.setAdapter(mainAdapter);


        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        main = getActivity();
    }

    // GETTER

    public static boolean isNoteEditActive() {
        return noteEditActive;
    }
}
