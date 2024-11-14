package ievsieieva.oleksandra.nure.ui.note.edit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import ievsieieva.oleksandra.nure.R;
import ievsieieva.oleksandra.nure.databinding.FragmentNoteEditBinding;

public class NoteEditFragment extends Fragment {
    String TAG = NoteEditFragment.class.getSimpleName();
    NoteEditViewModel viewModel;
    FragmentNoteEditBinding binding;
    Integer image;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("image", this, (requestKey, result) -> {
            Integer imageRes = result.getInt("image");
            this.image = imageRes;
        });
        getParentFragmentManager().clearFragmentResult("image");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_edit, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Integer id;
        if(getArguments() != null) {
            id = NoteEditFragmentArgs
                    .fromBundle(
                            getArguments()
                    )
                    .getId();
        } else {
            id = -1;
        }
        viewModel = new ViewModelProvider(this,
                new NoteEditViewModelFactory(id)
        )
                .get(NoteEditViewModel.class);
        binding.setViewModel(viewModel);

        subscribeToViewModel(viewModel);

        if (image != null) {
            viewModel.setImage(image);
            this.image = null;
        }

        binding.selectImage.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(
                    NoteEditFragmentDirections.actionNoteEditFragmentToGalleryFragment()
            );
        });
    }

    private void subscribeToViewModel(NoteEditViewModel viewModel) {
        viewModel.onBack.observe(getViewLifecycleOwner(), navigate -> {
            if(navigate) {
                NavHostFragment.findNavController(this).popBackStack();
            }
        });

        viewModel.year.observe(getViewLifecycleOwner(), year -> {
            Log.d(TAG, "subscribeToViewModel: " + year);
        });
    }
}
