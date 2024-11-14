package ievsieieva.oleksandra.nure.ui.note.edit;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ievsieieva.oleksandra.nure.ui.note.details.NoteDetailsViewModel;

public class NoteEditViewModelFactory implements ViewModelProvider.Factory {
    Integer id;
    public NoteEditViewModelFactory(Integer id) {
        this.id = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NoteEditViewModel(id);
    }
}
