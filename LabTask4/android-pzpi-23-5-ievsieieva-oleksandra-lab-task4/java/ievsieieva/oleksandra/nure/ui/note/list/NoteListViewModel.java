package ievsieieva.oleksandra.nure.ui.note.list;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ievsieieva.oleksandra.nure.MainViewModel;
import ievsieieva.oleksandra.nure.data.model.Note;
import ievsieieva.oleksandra.nure.data.source.NoteDataSource;

public class NoteListViewModel extends ViewModel {
    private String TAG = NoteListViewModel.class.getSimpleName();
    NoteDataSource noteDataSource = NoteDataSource.getInstance();
    MutableLiveData<List<Note>> notes = new MutableLiveData<>();
    String search = "";
    MainViewModel.FilterOrder filterOrder = MainViewModel.FilterOrder.DESC;


    public NoteListViewModel() {
        notes.postValue(noteDataSource.getNotes());
        onSearch("note");
    }

    public void deleteNote(String title) {
        noteDataSource.deleteNote(title);
        notes.postValue(noteDataSource.getNotes());
    }

    public void onViewCreated() {
        notes.postValue(noteDataSource.getNotes());
    }

    public void onSearch(String search) {
        this.search = search;
        List<Note> oldList = noteDataSource.getNotes();
        Map<Note.Type,List<Note>> noteMap = new HashMap<>();
        noteMap.put(Note.Type.LOW, new ArrayList<>());
        noteMap.put(Note.Type.NORMAL, new ArrayList<>());
        noteMap.put(Note.Type.HIGH, new ArrayList<>());
        for (Note n : oldList) {
            noteMap.get(n.getType()).add(n);
        }
        oldList.clear();
        if(MainViewModel.FilterOrder.DESC == filterOrder) {
            oldList.addAll(noteMap.get(Note.Type.HIGH));
            oldList.addAll(noteMap.get(Note.Type.NORMAL));
            oldList.addAll(noteMap.get(Note.Type.LOW));
        } else {
            oldList.addAll(noteMap.get(Note.Type.LOW));
            oldList.addAll(noteMap.get(Note.Type.NORMAL));
            oldList.addAll(noteMap.get(Note.Type.HIGH));
        }
        if (TextUtils.isEmpty(search)) {
            notes.postValue(oldList);
        } else {
            ArrayList<Note> newList = new ArrayList<>();
            for (Note n : oldList) {
                if(n.getDesc().contains(search)) {
                    newList.add(n);
                }
            }
            notes.postValue(newList);
        }
    }

    public void onFilter(MainViewModel.FilterOrder filterOrder) {
        this.filterOrder = filterOrder;
        onSearch(search);
    }


}
