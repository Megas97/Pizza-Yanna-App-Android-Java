package com.example.pizzayannaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "PizzaYanna.db";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(username TEXT primary key, email TEXT, phone TEXT, address TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
    }

    public boolean registerUser(String username, String email, String phone, String address, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("address", address);
        contentValues.put("password", password);
        long result = db.insert("users", null, contentValues);
        return result != -1;
    }

    public boolean isUsernameTaken(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ?", new String[] {username});
        return cursor.getCount() > 0;
    }

    public boolean isUserRegistered(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username = ? and password = ?", new String[] {username, password});
        return cursor.getCount() > 0;
    }

    public String getEmail(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String result = null;
        Cursor cursor = db.rawQuery("select email from users where username = ?", new String[] {username});
        if (cursor.moveToFirst()){
            result = cursor.getString(0);
        }
        System.out.println(result);
        return result;
    }

    public String getPhoneNumber(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String result = null;
        Cursor cursor = db.rawQuery("select phone from users where username = ?", new String[] {username});
        if (cursor.moveToFirst()){
            result = cursor.getString(0);
        }
        System.out.println(result);
        return result;
    }

    public String getHomeAddress(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String result = null;
        Cursor cursor = db.rawQuery("select address from users where username = ?", new String[] {username});
        if (cursor.moveToFirst()){
            result = cursor.getString(0);
        }
        return result;
    }

    public boolean updateUsername(String username, String newUsername) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", newUsername);
        long update = db.update("users", contentValues, "username = ?", new String[] {username});
        return update != -1;
    }

    public boolean updatePassword(String username, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", newPassword);
        long update = db.update("users", contentValues, "username = ?", new String[] {username});
        return update != -1;
    }

    public boolean updateEmail(String username, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        long update = db.update("users", contentValues, "username = ?", new String[] {username});
        return update != -1;
    }

    public boolean updatePhoneNumber(String username, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone", phone);
        long update = db.update("users", contentValues, "username = ?", new String[] {username});
        return update != -1;
    }

    public boolean updateHomeAddress(String username, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("address", address);
        long update = db.update("users", contentValues, "username = ?", new String[] {username});
        return update != -1;
    }
}