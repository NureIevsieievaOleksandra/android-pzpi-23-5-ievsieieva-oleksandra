package ievsieieva.oleksandra.nure.ui.note.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ievsieieva.oleksandra.nure.data.model.Note;
import ievsieieva.oleksandra.nure.data.source.NoteDataSource;

public class NoteDetailsViewModel extends ViewModel {
    Integer id;
    NoteDataSource noteDataSource = NoteDataSource.getInstance();
    MutableLiveData<Note> _note = new MutableLiveData<>();
    public NoteDetailsViewModel(Integer id) {
        this.id = id;
        _note.postValue(noteDataSource.getNoteById(id));
    }

    public LiveData<Note> getNote() {
        return _note;
    }
}
