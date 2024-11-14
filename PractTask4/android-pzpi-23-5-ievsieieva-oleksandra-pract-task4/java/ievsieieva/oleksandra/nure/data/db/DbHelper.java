package ievsieieva.oleksandra.nure.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS  user (id INTEGER PRIMARY KEY, name TEXT, age INTEGER)";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "app.db";
    public static final String TABLE_USER = "user";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String AGE = "age";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.getWritableDatabase().execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
