package ievsieieva.oleksandra.nure.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.List;

import ievsieieva.oleksandra.nure.R;
import ievsieieva.oleksandra.nure.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {
    GalleryViewModel viewModel;
    FragmentGalleryBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        binding.setViewModel(viewModel);

        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.happy);
        list.add(R.drawable.android);
        list.add(R.drawable.plane);
        list.add(R.drawable.sleep);
        list.add(R.drawable.admin);
        list.add(R.drawable.message);

        binding.gallery.setAdapter(new GalleryAdapter(list,image -> {
            Bundle result = new Bundle();
            result.putInt("image", image);
            getParentFragmentManager().setFragmentResult("image", result);
            NavHostFragment.findNavController(this).popBackStack();
        }));
    }
}
