package ievsieieva.oleksandra.nure.data.source;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ievsieieva.oleksandra.nure.R;
import ievsieieva.oleksandra.nure.data.model.Note;

public class NoteDataSource {
    private String TAG = NoteDataSource.class.getSimpleName();
    private ArrayList<Note> list = new ArrayList<>();
    private static NoteDataSource INSTANCE = null;

    private NoteDataSource() {
        list.add(new Note("Note1", "note desc1", Note.Type.HIGH, new Date(), R.drawable.happy));
        list.add(new Note("Note2", "note desc2", Note.Type.LOW, new Date(), R.drawable.happy));
        list.add(new Note("Note3", "note desc3", Note.Type.NORMAL, new Date(), R.drawable.happy));
        list.add(new Note("Note4", "note desc4", Note.Type.HIGH, new Date(), R.drawable.happy));
    }

    public List<Note> getNotes() {
        return list;
    }

    public static NoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NoteDataSource();
        }
        return INSTANCE;
    }

    public Note getNoteByTitle(String title) {
        for (Note note: list) {
            if (note.getTitle().equals(title)) {
                return note;
            }
        }
        return null;
    }

    public void deleteNote(String title) {
        ArrayList<Note> notes = new ArrayList<>();
        for (Note n: list) {
            if (!n.getTitle().equals(title)) {
                notes.add(n);
            }
        }
        list = notes;
    }

    public void save(Note note) {
        deleteNote(note.getTitle());
        list.add(note);
    }
}
