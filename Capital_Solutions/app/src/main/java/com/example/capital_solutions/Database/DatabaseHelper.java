package com.example.capital_solutions.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME= "money.db";
    public static final String TABLE_NAME= "Money_table";
    public static final String TABLE_NAME_2= "week1";
    public static final String TABLE_NAME_3 = "week2";
    public static final String TABLE_NAME_4 = "week3";
    public static final String COL_1= "WEEK";
    public static final String COL_2= "FOOD";
    public static final String COL_3= "CLOTHES";
    public static final String COL_4= "OTHER";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 7);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (WEEK DOUBLE , FOOD DOUBLE, CLOTHES DOUBLE, OTHER DOUBLE) ");
        db.execSQL("create table " + TABLE_NAME_2 + " (ID INTEGER , NAME TEXT, SURNAME TEXT, MARKS INTEGER) ");
        db.execSQL("create table " + TABLE_NAME_3 + " (ID INTEGER , NAME TEXT, SURNAME TEXT, MARKS INTEGER) ");
        db.execSQL("create table " + TABLE_NAME_4 + " (ID INTEGER , NAME TEXT, SURNAME TEXT, MARKS INTEGER) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME_2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_3);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_4);
        onCreate(db);
    }

    public boolean insertData(String s, String name, String surname, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, s);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, marks);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;


    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean updatedata (String week, String food, String clothes, String other) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, week);
        contentValues.put(COL_2, food);
        contentValues.put(COL_3, clothes);
        contentValues.put(COL_4, other);
        db.update(TABLE_NAME, contentValues, "WEEK = ?", new String[]{week});
        return true;
    }

    public Integer deleteData (String week){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "WEEK = ?", new String[] {week});
    }

}
