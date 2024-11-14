package ievsieieva.oleksandra.nure.data.source;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ievsieieva.oleksandra.nure.R;
import ievsieieva.oleksandra.nure.data.db.DbHelper;
import ievsieieva.oleksandra.nure.data.model.Note;

public class NoteDataSource {
    private String TAG = NoteDataSource.class.getSimpleName();
    Context context;
    DbHelper dbHelper;
    SQLiteDatabase db;
    private static NoteDataSource INSTANCE = null;
    SharedPreferences sharedPreferences;

    private NoteDataSource(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
    }

    public void saveTextSize(Integer size) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("size", size);
        editor.apply();
    }

    public Integer getTextSize() {
        return sharedPreferences.getInt("size", 1);
    }

    @SuppressLint("Range")
    public List<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        Cursor cursor = db.query(
                DbHelper.TABLE_NOTES,
                null,
                null,
                null,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            notes.add(
                    new Note(
                            cursor.getInt(cursor.getColumnIndex(DbHelper.ID)),
                            cursor.getString(cursor.getColumnIndex(DbHelper.TITLE)),
                            cursor.getString(cursor.getColumnIndex(DbHelper.DESC)),
                            Note.Type.values()[cursor.getType(cursor.getColumnIndex(DbHelper.TYPE))],
                            new Date(cursor.getLong(cursor.getColumnIndex(DbHelper.DATE))),
                            cursor.getInt(cursor.getColumnIndex(DbHelper.ICON))
                    )
            );
        }
        cursor.close();
        return notes;
    }

    public static NoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new NoteDataSource(context);
        }
        return INSTANCE;
    }

    public static NoteDataSource getInstance() {
        return INSTANCE;
    }

    @SuppressLint("Range")
    public Note getNoteById(Integer id) {
        ArrayList<Note> notes = new ArrayList<>();
        String selection = DbHelper.ID + "= ?";
        String[] selectionArgs = {id.toString()};
        Cursor cursor = db.query(
                DbHelper.TABLE_NOTES,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            notes.add(
                    new Note(
                            cursor.getInt(cursor.getColumnIndex(DbHelper.ID)),
                            cursor.getString(cursor.getColumnIndex(DbHelper.TITLE)),
                            cursor.getString(cursor.getColumnIndex(DbHelper.DESC)),
                            Note.Type.values()[cursor.getType(cursor.getColumnIndex(DbHelper.TYPE))],
                            new Date(cursor.getLong(cursor.getColumnIndex(DbHelper.DATE))),
                            cursor.getInt(cursor.getColumnIndex(DbHelper.ICON))
                    )
            );
        }
        cursor.close();
        return notes.get(0);
    }


    public void deleteNote(Integer id) {
        String selection = DbHelper.ID + "= ?";
        String[] selectionArgs = {id.toString()};
        db.delete(DbHelper.TABLE_NOTES, selection, selectionArgs);
    }

    public void save(Note note) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.TITLE, note.getTitle());
        values.put(DbHelper.DESC, note.getDesc());
        values.put(DbHelper.TYPE, note.getType().ordinal());
        values.put(DbHelper.DATE, note.getDate().getTime());
        values.put(DbHelper.ICON, note.getIcon());

        if (note.getId() == null) {
            db.insert(DbHelper.TABLE_NOTES, null, values);
        } else {
            String selection = DbHelper.ID + "= ?";
            String[] selectionArgs = {note.getId().toString()};
            db.update(
                    DbHelper.TABLE_NOTES,
                    values,
                    selection,
                    selectionArgs
            );
        }

    }


}
