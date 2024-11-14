package ievsieieva.oleksandra.nure.ui.note.details;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NoteDetailsViewModelFactory implements ViewModelProvider.Factory {
    Integer id;
    public NoteDetailsViewModelFactory(Integer id) {
        this.id = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NoteDetailsViewModel(id);
    }
}
