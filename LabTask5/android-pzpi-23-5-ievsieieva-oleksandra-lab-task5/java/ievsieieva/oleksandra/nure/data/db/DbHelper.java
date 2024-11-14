package ievsieieva.oleksandra.nure.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private String TAG = DbHelper.class.getSimpleName();
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS  note (id INTEGER PRIMARY KEY, title TEXT, desc TEXT, type INTEGER, date INTEGER, icon INTEGER)";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "note.db";
    public static final String TABLE_NOTES = "note";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESC = "desc";
    public static final String TYPE = "type";
    public static final String DATE = "date";
    public static final String ICON = "icon";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        SQLiteDatabase db = this.getWritableDatabase();
        this.getWritableDatabase().execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: init table");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
