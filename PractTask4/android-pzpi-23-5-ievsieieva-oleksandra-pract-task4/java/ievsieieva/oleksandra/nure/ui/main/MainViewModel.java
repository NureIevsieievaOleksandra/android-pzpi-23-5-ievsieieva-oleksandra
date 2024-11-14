package ievsieieva.oleksandra.nure.ui.main;

import android.provider.ContactsContract;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ievsieieva.oleksandra.nure.data.DataSource;
import ievsieieva.oleksandra.nure.data.model.User;

public class MainViewModel extends ViewModel {
    public MutableLiveData<String> name = new MutableLiveData<>("");
    public MutableLiveData<String> age = new MutableLiveData<>("");
    public MutableLiveData<List<User>> users = new MutableLiveData<>();
    DataSource dataSource = DataSource.getInstance();

    public MainViewModel() {
        name.postValue(DataSource.getInstance().getName());
        age.postValue(DataSource.getInstance().getAge().toString());
        users.postValue(dataSource.getUsers());
    }

    public void save() {
        try {
            dataSource.save(name.getValue(), Integer.parseInt(age.getValue()));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void onAdd() {
        try {
            dataSource.insertUser(new User(null, name.getValue(), Integer.parseInt(age.getValue())));
            users.postValue(dataSource.getUsers());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
