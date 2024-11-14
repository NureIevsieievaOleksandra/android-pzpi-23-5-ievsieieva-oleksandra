package ievsieieva.oleksandra.nure.ui.note.details;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import ievsieieva.oleksandra.nure.R;
import ievsieieva.oleksandra.nure.data.source.NoteDataSource;
import ievsieieva.oleksandra.nure.databinding.FragmentNoteDetailsBinding;

public class NoteDetailsFragment extends Fragment {
    NoteDetailsViewModel viewModel;
    FragmentNoteDetailsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_details, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments() != null) {
            viewModel = new ViewModelProvider(
                    this,
                    new NoteDetailsViewModelFactory(
                            NoteDetailsFragmentArgs
                                    .fromBundle(
                                            getArguments()
                                    )
                                    .getId()))
                    .get(NoteDetailsViewModel.class);
            binding.setViewModel(viewModel);
        }
        int textSize = NoteDataSource.getInstance().getTextSize();
        if (textSize == 0) {
            binding.titleLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
            binding.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            binding.descLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
            binding.desc.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            binding.dateLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
            binding.date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        } else if (textSize == 1) {
            binding.titleLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            binding.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            binding.descLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            binding.desc.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            binding.dateLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            binding.date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        } else {
            binding.titleLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            binding.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            binding.descLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            binding.desc.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            binding.dateLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            binding.date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        }
    }

}
