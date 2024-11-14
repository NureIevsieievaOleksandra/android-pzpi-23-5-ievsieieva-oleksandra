package ievsieieva.oleksandra.nure.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ievsieieva.oleksandra.nure.data.db.DbHelper;
import ievsieieva.oleksandra.nure.data.model.User;

public class DataSource {
    DbHelper dbHelper;
    SQLiteDatabase db;
    Context context;
    private static DataSource INSTANCE = null;
    SharedPreferences sharedPreferences;

    private DataSource(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void save(String name, Integer age) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",name);
        editor.putInt("age", age);
        editor.apply();
    }

    public String getName() {
        return sharedPreferences.getString("name", "");
    }

    public Integer getAge() {
        return sharedPreferences.getInt("age", 0);
    }


    public static DataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DataSource(context);
        }
        return INSTANCE;
    }

    public static DataSource getInstance() {
        return INSTANCE;
    }
    
    @SuppressLint("Range")
    public List<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = db.query(
                DbHelper.TABLE_USER,
                null,
                null,
                null,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            users.add(
                    new User(
                            cursor.getInt(cursor.getColumnIndex(DbHelper.ID)),
                            cursor.getString(cursor.getColumnIndex(DbHelper.NAME)),
                            cursor.getInt(cursor.getColumnIndex(DbHelper.AGE))
                    )
            );
        }
        cursor.close();
        return users;
    }
    
    public void insertUser(User user) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.NAME, user.getName());
        values.put(DbHelper.AGE, user.getAge());

        db.insert(DbHelper.TABLE_USER, null, values);
    }
}


