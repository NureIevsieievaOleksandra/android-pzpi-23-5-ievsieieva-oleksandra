package ievsieieva.oleksandra.nure.ui.settings;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import androidx.lifecycle.ViewModel;

import ievsieieva.oleksandra.nure.R;
import ievsieieva.oleksandra.nure.data.source.NoteDataSource;

public class SettingsViewModel extends ViewModel {
    private String TAG = SettingsViewModel.class.getSimpleName();
    NoteDataSource noteDataSource = NoteDataSource.getInstance();

    public void onSmall() {
        noteDataSource.saveTextSize(0);
    }

    public void onNormal() {
        noteDataSource.saveTextSize(1);
    }

    public void onLarge() {
        noteDataSource.saveTextSize(2);
    }
}
