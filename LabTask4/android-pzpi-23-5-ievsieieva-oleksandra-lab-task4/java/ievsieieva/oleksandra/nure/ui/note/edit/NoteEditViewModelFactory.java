package ievsieieva.oleksandra.nure.ui.note.edit;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ievsieieva.oleksandra.nure.ui.note.details.NoteDetailsViewModel;

public class NoteEditViewModelFactory implements ViewModelProvider.Factory {
    String name;
    public NoteEditViewModelFactory(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NoteEditViewModel(name);
    }
}
