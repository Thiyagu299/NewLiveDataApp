package com.example.android.livedataapp.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "DataLiveDB";
    public static final int DB_VERSION = 1;
    public static final String NAME = "dataLive";
    public static final String ID = "id";
    public static final String FIRST_NAME = "firstname";
    public static final String LAST_NAME = "lastname";
    public static final String AVATAR = "avatar";


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
             db.execSQL("CREATE TABLE IF NOT EXISTS "+ NAME +"("+ ID +" TEXT, "+FIRST_NAME+ " TEXT, "+LAST_NAME+ " TEXT, "+AVATAR+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
