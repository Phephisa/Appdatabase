package com.example.app3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    //constatnt varibale - database name
    private static final String DB_NAME = "mydatabase.db";

    //database version
    private static final int DB_VERSION = 1;

    //table name
    private static final String TABLE_NAME = "database";

    //date column
    private static final String DATE_COL = "DATE";

    //time column
    private static final String TIME_COL = "TIME";



    public DBHandler( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATING A QUERY ALONG WITI DATA TYPES
        String query = "CREATE TABLE " + TABLE_NAME + " (" + DATE_COL + "TEXT, " + TIME_COL + "TEXT)";
        db.execSQL(query);
    }

    public void addDateTime(String ctime, String cdate)
    {

        //getting writable database
        SQLiteDatabase db = this.getWritableDatabase();
        //creating varriable for content
        ContentValues values = new ContentValues();

        //passing values intot
        values.put(DATE_COL, cdate);
        values.put(TIME_COL, ctime);

        //passing content
        db.insert(TABLE_NAME, null, values);
        //closing database
        db.close();

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //to handle database changes
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
