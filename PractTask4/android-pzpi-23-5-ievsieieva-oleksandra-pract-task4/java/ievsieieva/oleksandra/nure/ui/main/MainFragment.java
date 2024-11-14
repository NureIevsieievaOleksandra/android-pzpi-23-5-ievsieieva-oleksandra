package ievsieieva.oleksandra.nure.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import ievsieieva.oleksandra.nure.R;
import ievsieieva.oleksandra.nure.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {
    MainAdapter adapter;
    MainViewModel viewModel;
    FragmentMainBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setViewModel(viewModel);
        adapter = new MainAdapter();
        binding.list.setAdapter(adapter);

        viewModel.users.observe(getViewLifecycleOwner(), list -> {
            adapter.submitList(list);
        });

        viewModel.name.observe(getViewLifecycleOwner(), n -> {
            viewModel.save();
        });
        viewModel.age.observe(getViewLifecycleOwner(), a -> {
            viewModel.save();
        });

        binding.write.setOnClickListener(v -> {
            writeToFile(binding.config.getText().toString(), getContext());
            binding.config.setText("");
        });

        binding.read.setOnClickListener(v -> {
            binding.result.setText(readFromFile(getContext()));
        });
    }

    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }
}
