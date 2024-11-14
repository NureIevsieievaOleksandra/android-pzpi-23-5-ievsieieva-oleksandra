package ievsieieva.oleksandra.nure.ui.note.list;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import ievsieieva.oleksandra.nure.MainActivity;
import ievsieieva.oleksandra.nure.MainViewModel;
import ievsieieva.oleksandra.nure.R;
import ievsieieva.oleksandra.nure.databinding.FragmentNoteListBinding;

public class NoteListFragment extends Fragment {
    private String TAG = NoteListFragment.class.getSimpleName();
    private NoteListViewModel viewModel;
    private FragmentNoteListBinding binding;
    private NoteListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_list, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(NoteListViewModel.class);
        binding.setViewModel(viewModel);
        ((MainActivity) getActivity()).viewModel.isSearchVisible.observe(getViewLifecycleOwner(), v -> {
            binding.search.setVisibility(v ? View.VISIBLE : View.GONE);
            viewModel.onSearch("");
        });
        ((MainActivity) getActivity()).viewModel.filterOrder.observe(getViewLifecycleOwner(), f -> {
            viewModel.onFilter(f);
        });

        NoteListFragment fragment = this;

        adapter = new NoteListAdapter(new OnItemClickCallback() {
            @Override
            public void onItemClick(String title) {
                NavHostFragment
                        .findNavController(fragment)
                        .navigate(
                                NoteListFragmentDirections
                                        .actionNoteListFragmentToNoteDetailsFragment(title)
                        );
            }

            @Override
            public void onDelete(String title) {
                viewModel.deleteNote(title);
            }

            @Override
            public void onEdit(String title) {
                NavHostFragment
                        .findNavController(fragment)
                        .navigate(
                                NoteListFragmentDirections
                                        .actionNoteListFragmentToNoteEditFragment(title)
                        );
            }
        });
        binding.list.setAdapter(adapter);
        binding.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.onSearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        subscribeToViewModel(viewModel);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.onViewCreated();
    }

    private void subscribeToViewModel(NoteListViewModel viewModel) {
        viewModel.notes.observe(getViewLifecycleOwner(),list -> {
            adapter.submitList(list);
        });
    }
}
