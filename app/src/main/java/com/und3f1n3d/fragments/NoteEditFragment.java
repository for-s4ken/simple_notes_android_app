package com.und3f1n3d.fragments;


import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.und3f1n3d.MainActivity;
import com.und3f1n3d.R;
import com.und3f1n3d.model.Note;


public class NoteEditFragment extends Fragment {

    //FIELDS

    private View rootView;
    private FragmentActivity main;
    private static Note noteToEdit;
    private EditText noteEditText;
    private Button saveButton;

    //CONSTRUCTORS

    public NoteEditFragment() {
        // Required empty public constructor
    }

    // METHODS

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_note_edit, container, false);
        saveButton = rootView.findViewById(R.id.saveButton);
        noteEditText = rootView.findViewById(R.id.noteEditText);
        if(noteToEdit != null){
            noteEditText.setText(noteToEdit.getText());
            saveButton.setOnClickListener(l -> {
                MainActivity.redactNote(noteToEdit.getId(), noteEditText.getText().toString());
                noteToEdit = null;
                main.onBackPressed();
            });
        }else{
            saveButton.setOnClickListener(l -> {
                MainActivity.addNewNote(Note.makeNewNote(noteEditText.getText().toString()));
                noteToEdit = null;
                main.onBackPressed();
            });
        }
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        main = getActivity();
    }

    // SETTER

    public static void setNoteToEdit(Note n) { noteToEdit = n; }

}
