package com.und3f1n3d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.und3f1n3d.fragments.NoteEditFragment;
import com.und3f1n3d.fragments.NotesListFragment;
import com.und3f1n3d.model.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MenuItem addNote;
    private MenuItem deleteNote;
    private Menu mainMenu;
    private static List<Note> notes;
    private  Activity main;

    private static int idOfCurrentNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notes = new ArrayList<>();
        main = this;
        for(int i = 0; i < 10; i++){
            notes.add(Note.makeNewNote("312e1910e1uem812e128eu1290hf1f" + i));
        }
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.mainLayout, new NotesListFragment(this));
        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        mainMenu = menu;
        deleteNote = menu.findItem(R.id.deleteNote);
        addNote = menu.findItem(R.id.addNote);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNote:
                FragmentTransaction transaction = getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("notesListFragment")
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction.replace(R.id.mainLayout, new NoteEditFragment(this));
                transaction.commit();
                return true;
            case  R.id.deleteNote:
                removeNote();
                onBackPressed();
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        int fragmentCount = getSupportFragmentManager().getBackStackEntryCount();
        if(fragmentCount == 0){
            super.onBackPressed();
        }else{
            changeMenuMode(true);
            getSupportFragmentManager().popBackStack();
        }
    }


    public static List<Note> getNotes() { return notes; }

    public static void redactNote(int id, String change){
        notes.get(id).changeText(change);
    }

    public static void removeNote(){
        notes.remove(idOfCurrentNote);
    }

    public static void addNewNote(Note n){
        notes.add(n);
    }

    public void changeMenuMode(boolean b){
        addNote.setVisible(b);
        deleteNote.setVisible(!b);
    }

    public static void setIdOfCurrentNote(int t){
        idOfCurrentNote = t;
    }
}
