package com.und3f1n3d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.und3f1n3d.fragments.NoteEditFragment;
import com.und3f1n3d.fragments.NotesListFragment;
import com.und3f1n3d.model.Note;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // FIELDS

    private MenuItem addNote;
    private MenuItem deleteNote;
    private static List<Note> notes;

    // BASIC METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            notes = new ArrayList<>();
            for(int i = 0; i < 10; i++){
                notes.add(Note.makeNewNote("lalka" + i));
            }
            //
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            transaction.replace(R.id.mainLayout, new NotesListFragment());
            transaction.commit();
            //
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        deleteNote = menu.findItem(R.id.deleteNote);
        addNote = menu.findItem(R.id.addNote);
        return true;
    }

    // THIS METHOD CALLS EVERY TIME WHEN USER CLICKS ON VIEWHOLDER (SEE NotesListFragment.java)

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(NotesListFragment.isNoteEditActive()){
            changeMenuMode(false);
        }else{
            changeMenuMode(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    // MAKING AND DELETING A NOTE

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNote:
                //
                FragmentTransaction transaction = getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .addToBackStack("noteEditFragment");
                transaction.replace(R.id.mainLayout, new NoteEditFragment());
                transaction.commit();
                //
                changeMenuMode(false);
                return true;
            case  R.id.deleteNote:
                removeCurrentNote();
                onBackPressed();
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    // OVERRIDING PARENT onBackPressed

    @Override
    public void onBackPressed() {
        int fragmentCount = getSupportFragmentManager().getBackStackEntryCount();
        if(fragmentCount == 0){
            super.onBackPressed();
        }else{
            changeMenuMode(true);
            getSupportFragmentManager().popBackStack();
            NoteEditFragment.setNoteToEdit(null);
        }
    }

    // MANIPULATIONS WITH NOTES LIST

    public static List<Note> getNotes() { return notes; }

    public static void redactNote(int id, String change){
        notes.get(id).changeText(change);
    }

    public static void addNewNote(Note n){
        notes.add(n);
    }

    public  void removeCurrentNote(){
        if(NoteEditFragment.getCurrentNote() != null){
            int temp = NoteEditFragment.getCurrentNote().getId();
            if(notes.size() > 0){
                notes.remove(temp);
                for(int i = temp; i < notes.size(); i++){
                    notes.get(i).setId(i);
                }
            }else{
                notes.remove(temp);
            }
        }else{
            this.onBackPressed();
        }
        System.out.println(notes);
    }

    // CHANGING MENU ITEMS

    public void changeMenuMode(boolean b){
        addNote.setVisible(b);
        deleteNote.setVisible(!b);
    }
}
