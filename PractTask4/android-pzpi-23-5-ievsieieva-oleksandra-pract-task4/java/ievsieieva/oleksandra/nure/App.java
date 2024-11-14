package ievsieieva.oleksandra.nure;

import android.app.Application;

import ievsieieva.oleksandra.nure.data.DataSource;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataSource.getInstance(this);
    }
}
