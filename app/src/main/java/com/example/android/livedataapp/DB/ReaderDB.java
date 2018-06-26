package com.example.android.livedataapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.livedataapp.UserData;
import com.example.android.livedataapp.UserModel;

import java.util.ArrayList;

public class ReaderDB {

    public static void insertDatalive(Context context, UserData userData) {

        Cursor c = null;

        DBHelper dbHelper = new DBHelper(context, DBHelper.DB_NAME, null, DBHelper.DB_VERSION);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.ID, userData.id);
        contentValues.put(DBHelper.FIRST_NAME, userData.first_name);
        contentValues.put(DBHelper.LAST_NAME, userData.last_name);
        contentValues.put(DBHelper.AVATAR, userData.avatar);

        try {

            c = sqLiteDatabase.rawQuery("SELECT * FROM " + DBHelper.NAME + " where " + DBHelper.ID + " ='"+ userData.id + "'", null);
            if (c.getCount() == 0) {
                sqLiteDatabase.insert(DBHelper.NAME, null, contentValues);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.close();
            sqLiteDatabase.close();
        }
    }

    public static void getDataLive(Context context, UserModel userModel) {

        ArrayList<UserData> userDataArrayList = new ArrayList<>();
        Cursor c = null;
        DBHelper dbHelper = new DBHelper(context, DBHelper.DB_NAME, null, DBHelper.DB_VERSION);
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        try {
            c = sqLiteDatabase.rawQuery("SELECT * FROM " + DBHelper.NAME, null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    UserData userData = new UserData();
                    userData.id = c.getString(c.getColumnIndex(DBHelper.ID));
                    userData.first_name = c.getString(c.getColumnIndex(DBHelper.FIRST_NAME));
                    userData.last_name = c.getString(c.getColumnIndex(DBHelper.LAST_NAME));
                    userData.avatar = c.getString(c.getColumnIndex(DBHelper.AVATAR));
                    userDataArrayList.add(userData);
                    userModel.userModelMutableLiveData.setValue(userDataArrayList);

                }
                while (c.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.close();
            sqLiteDatabase.close();
        }
    }
}
