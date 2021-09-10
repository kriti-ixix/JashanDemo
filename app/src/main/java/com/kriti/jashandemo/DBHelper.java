package com.kriti.jashandemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper
{
    public static final String DATABASE = "school";
    public static final String TABLE = "students";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE, null, 1);
    }

    long addValues(int rn, String n, float m)
    {
        SQLiteDatabase sq = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("rollno", rn);
        cv.put("name", n);
        cv.put("marks", m);
        long result = sq.insert(TABLE, null, cv);
        return result;
    }

    long updateValues(int rn, String n, float m)
    {
        SQLiteDatabase sq = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", n);
        cv.put("marks", m);
        long result = sq.update(TABLE, cv, "rollno = " + rn, null);
        return result;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE + " (rollno integer primary key, name text, marks float)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
