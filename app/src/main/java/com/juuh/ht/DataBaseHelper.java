package com.juuh.ht;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//This class manges the database where passwords are saved
//Source: https://www.youtube.com/watch?v=8obgNNlj3Eo

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DataBaseHelper(Context context) {
        super(context, "Login.db", null,
                1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("Create Table users(username TEXT primary key, password TEXT, salt TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String password, String salt) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("salt", salt);
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public boolean deleteData(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        long result = MyDB.delete("users", "username = ?",
                new String[] {username});
        if (result == -1) return false;
        else
            return true;
    }


    public Boolean checkusername (String username) {
        SQLiteDatabase MyDB = this. getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?",
                new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where " +
                "username = ? and password = ?", new String[] {username, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public String getSalt(String username) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select salt from users where username = ?",
                new String[] {username});
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            String salt = cursor.getString(0);
            return salt;
        } else {
            return null;
        }
    }

}
