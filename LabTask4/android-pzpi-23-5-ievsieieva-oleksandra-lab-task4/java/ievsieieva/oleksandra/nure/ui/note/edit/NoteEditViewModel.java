package ievsieieva.oleksandra.nure.ui.note.edit;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.DrawableRes;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import ievsieieva.oleksandra.nure.R;
import ievsieieva.oleksandra.nure.data.model.Note;
import ievsieieva.oleksandra.nure.data.source.NoteDataSource;

public class NoteEditViewModel extends ViewModel {
    private String TAG = NoteEditViewModel.class.getSimpleName();
    String name;
    NoteDataSource noteDataSource = NoteDataSource.getInstance();
    public MutableLiveData<String> title = new MutableLiveData<>("");
    public MutableLiveData<String> desc = new MutableLiveData<>("");
    public MutableLiveData<Boolean> low = new MutableLiveData<>(true);
    public MutableLiveData<Boolean> normal = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> high = new MutableLiveData<>(false);
    public MutableLiveData<Integer> year = new MutableLiveData<>(2024);
    public MutableLiveData<Integer> month = new MutableLiveData<>(11);
    public MutableLiveData<Integer> day = new MutableLiveData<>(1);
    public MutableLiveData<Integer> hours = new MutableLiveData<>(0);
    public MutableLiveData<Integer> mins = new MutableLiveData<>(0);
    MutableLiveData<Boolean> onBack = new MutableLiveData<>(false);
    MutableLiveData<Integer> image = new MutableLiveData<>();

    public LiveData<Integer> getImage() {
        return image;
    }

    public LiveData<Boolean> getOnBack() {
        return onBack;
    }

    public void setImage(@DrawableRes Integer imageRes) {
        image.postValue(imageRes);
    }

    public NoteEditViewModel(String name) {
        if(name != null && !TextUtils.isEmpty(name) && TextUtils.isEmpty(title.getValue())) {
            Log.d(TAG, "NoteEditViewModel: load note...");
            this.name = name;
           loadNote(this.name);
        } else {
            Log.d(TAG, "NoteEditViewModel: constructor: skip");
        }
    }

    public void save() {
       saveOnly();
       onBack.postValue(true);
    }

    private void saveOnly() {
        Note.Type type;
        if (low.getValue()) {
            type = Note.Type.LOW;
        } else if (normal.getValue()) {
            type = Note.Type.NORMAL;
        } else {
            type = Note.Type.HIGH;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(year.getValue(), month.getValue(), day.getValue(), hours.getValue(), mins.getValue());

        noteDataSource.save(new Note(
                title.getValue(),
                desc.getValue(),
                type,
                calendar.getTime(),
                image.getValue()
        ));
    }

    private void loadNote(String name) {
        Note note = noteDataSource.getNoteByTitle(name);
        title.postValue(note.getTitle());
        desc.postValue(note.getDesc());
        low.postValue(note.getType()== Note.Type.LOW);
        normal.postValue(note.getType()== Note.Type.NORMAL);
        high.postValue(note.getType()== Note.Type.HIGH);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(note.getDate());
        year.postValue(calendar.get(Calendar.YEAR));
        month.postValue(calendar.get(Calendar.MONTH));
        day.postValue(calendar.get(Calendar.DAY_OF_MONTH));
        hours.postValue(calendar.get(Calendar.HOUR_OF_DAY));
        mins.postValue(calendar.get(Calendar.MINUTE));
        image.postValue(note.getIcon());
    }
}
