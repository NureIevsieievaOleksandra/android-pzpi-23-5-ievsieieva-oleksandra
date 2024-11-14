package ievsieieva.oleksandra.nure.ui.note.details;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NoteDetailsViewModelFactory implements ViewModelProvider.Factory {
    String name;
    public NoteDetailsViewModelFactory(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NoteDetailsViewModel(name);
    }
}
