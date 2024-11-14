package ievsieieva.oleksandra.nure;

import android.app.Application;

import ievsieieva.oleksandra.nure.data.source.NoteDataSource;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NoteDataSource.getInstance(this);
    }
}
