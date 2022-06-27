package com.example.capital_solutions.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper2 extends SQLiteOpenHelper {

    public static final String DATABASE_NAME= "money.db";
    public static final String TABLE_NAME_2= "Money_table"; //OPPOSITE OF DBHELPER1
    public static final String TABLE_NAME= "week1"; //OPPOSITE OF DBHELPER1
    public static final String TABLE_NAME_3 = "week2";
    public static final String TABLE_NAME_4 = "week3";

    public static final String COL_1= "ID";
    public static final String COL_2= "NAME";
    public static final String COL_3= "SURNAME";
    public static final String COL_4= "MARKS";


    public DatabaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, 7);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    public DatabaseHelper2(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME_2 + " (WEEK DOUBLE , FOOD DOUBLE, CLOTHES DOUBLE, OTHER DOUBLE) ");
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER , NAME TEXT, SURNAME TEXT, MARKS INTEGER) ");
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

    public void insertData() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, "1");
        contentValues.put(COL_2, "blah");
        contentValues.put(COL_3, "blah");
        contentValues.put(COL_4, "blah");

        db.insert(TABLE_NAME, null, contentValues);
        db.insert(TABLE_NAME_3, null, contentValues);
        db.insert(TABLE_NAME_4, null, contentValues);

        contentValues.put(COL_1, "2");
        contentValues.put(COL_2, "blah");
        contentValues.put(COL_3, "blah");
        contentValues.put(COL_4, "blah");



        db.insert(TABLE_NAME, null, contentValues);
        db.insert(TABLE_NAME_3, null, contentValues);
        db.insert(TABLE_NAME_4, null, contentValues);

        contentValues.put(COL_1, "3");
        contentValues.put(COL_2, "blah");
        contentValues.put(COL_3, "blah");
        contentValues.put(COL_4, "blah");


        db.insert(TABLE_NAME, null, contentValues);
        db.insert(TABLE_NAME_3, null, contentValues);
        db.insert(TABLE_NAME_4, null, contentValues);

        contentValues.put(COL_1, "4");
        contentValues.put(COL_2, "blah");
        contentValues.put(COL_3, "blah");
        contentValues.put(COL_4, "blah");

        db.insert(TABLE_NAME, null, contentValues);
        db.insert(TABLE_NAME_3, null, contentValues);
        db.insert(TABLE_NAME_4, null, contentValues);

        contentValues.put(COL_1, "5");
        contentValues.put(COL_2, "blah");
        contentValues.put(COL_3, "blah");
        contentValues.put(COL_4, "blah");

        db.insert(TABLE_NAME, null, contentValues);
        db.insert(TABLE_NAME_3, null, contentValues);
        db.insert(TABLE_NAME_4, null, contentValues);

        contentValues.put(COL_1, "6");
        contentValues.put(COL_2, "blah");
        contentValues.put(COL_3, "blah");
        contentValues.put(COL_4, "blah");

        db.insert(TABLE_NAME, null, contentValues);
        db.insert(TABLE_NAME_3, null, contentValues);
        db.insert(TABLE_NAME_4, null, contentValues);

        contentValues.put(COL_1, "7");
        contentValues.put(COL_2, "blah");
        contentValues.put(COL_3, "blah");
        contentValues.put(COL_4, "blah");

        db.insert(TABLE_NAME, null, contentValues);
        db.insert(TABLE_NAME_3, null, contentValues);
        db.insert(TABLE_NAME_4, null, contentValues);
    }

    public void updateData() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, "1");
        contentValues.put(COL_2, "21");
        contentValues.put(COL_3, "0");
        contentValues.put(COL_4, "29");

        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{"1"});
        contentValues.put(COL_1, "2");
        contentValues.put(COL_2, "27");
        contentValues.put(COL_3, "0");
        contentValues.put(COL_4, "33");

        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{"2"});
        contentValues.put(COL_1, "3");
        contentValues.put(COL_2, "35");
        contentValues.put(COL_3, "20");
        contentValues.put(COL_4, "37");


        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{"3"});

        contentValues.put(COL_1, "4");
        contentValues.put(COL_2, "41");
        contentValues.put(COL_3, "0");
        contentValues.put(COL_4, "18");

        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{"4"});

        contentValues.put(COL_1, "5");
        contentValues.put(COL_2, "23");
        contentValues.put(COL_3, "0");
        contentValues.put(COL_4, "24");

        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{"5"});

        contentValues.put(COL_1, "6");
        contentValues.put(COL_2, "29");
        contentValues.put(COL_3, "15");
        contentValues.put(COL_4, "9");

        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{"6"});

        contentValues.put(COL_1, "7");
        contentValues.put(COL_2, "44");
        contentValues.put(COL_3, "0");
        contentValues.put(COL_4, "20");

        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{"7"});


    }
    public void updateData2() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, "1");
        contentValues.put(COL_2, "29");
        contentValues.put(COL_3, "0");
        contentValues.put(COL_4, "70");

        db.update(TABLE_NAME_3, contentValues, "ID = ?", new String[]{"1"});

        contentValues.put(COL_1, "2");
        contentValues.put(COL_2, "21");
        contentValues.put(COL_3, "0");
        contentValues.put(COL_4, "19");

        db.update(TABLE_NAME_3, contentValues, "ID = ?", new String[]{"2"});

        contentValues.put(COL_1, "3");
        contentValues.put(COL_2, "23");
        contentValues.put(COL_3, "50");
        contentValues.put(COL_4, "26");

        db.update(TABLE_NAME_3, contentValues, "ID = ?", new String[]{"3"});

        contentValues.put(COL_1, "4");
        contentValues.put(COL_2, "17");
        contentValues.put(COL_3, "20");
        contentValues.put(COL_4, "6");

        db.update(TABLE_NAME_3, contentValues, "ID = ?", new String[]{"4"});

        contentValues.put(COL_1, "5");
        contentValues.put(COL_2, "33");
        contentValues.put(COL_3, "0");
        contentValues.put(COL_4, "19");

        db.update(TABLE_NAME_3, contentValues, "ID = ?", new String[]{"5"});

        contentValues.put(COL_1, "6");
        contentValues.put(COL_2, "27");
        contentValues.put(COL_3, "0");
        contentValues.put(COL_4, "0");

        db.update(TABLE_NAME_3, contentValues, "ID = ?", new String[]{"6"});

        contentValues.put(COL_1, "7");
        contentValues.put(COL_2, "10");
        contentValues.put(COL_3, "0");
        contentValues.put(COL_4, "0");

        db.update(TABLE_NAME_3, contentValues, "ID = ?", new String[]{"7"});
    }

    public void updateData3() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, "1");
        contentValues.put(COL_2, "26");
        contentValues.put(COL_3, "10");
        contentValues.put(COL_4, "40");

        db.update(TABLE_NAME_4, contentValues, "ID = ?", new String[]{"1"});

        contentValues.put(COL_1, "2");
        contentValues.put(COL_2, "14");
        contentValues.put(COL_3, "10");
        contentValues.put(COL_4, "31");

        db.update(TABLE_NAME_4, contentValues, "ID = ?", new String[]{"2"});

        contentValues.put(COL_1, "3");
        contentValues.put(COL_2, "20");
        contentValues.put(COL_3, "8");
        contentValues.put(COL_4, "29");

        db.update(TABLE_NAME_4, contentValues, "ID = ?", new String[]{"3"});

        contentValues.put(COL_1, "4");
        contentValues.put(COL_2, "25");
        contentValues.put(COL_3, "0");
        contentValues.put(COL_4, "0");

        db.update(TABLE_NAME_4, contentValues, "ID = ?", new String[]{"4"});

        contentValues.put(COL_1, "5");
        contentValues.put(COL_2, "24");
        contentValues.put(COL_3, "0");
        contentValues.put(COL_4, "0");

        db.update(TABLE_NAME_4, contentValues, "ID = ?", new String[]{"5"});

        contentValues.put(COL_1, "6");
        contentValues.put(COL_2, "16");
        contentValues.put(COL_3, "0");
        contentValues.put(COL_4, "0");

        db.update(TABLE_NAME_4, contentValues, "ID = ?", new String[]{"6"});

        contentValues.put(COL_1, "7");
        contentValues.put(COL_2, "15");
        contentValues.put(COL_3, "0");
        contentValues.put(COL_4, "10");

        db.update(TABLE_NAME_4, contentValues, "ID = ?", new String[]{"7"});

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
    public Cursor getAllData2() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_3, null);
        return res;
    }
    public Cursor getAllData3() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_4, null);
        return res;
    }

    public boolean updatedata (String id, String name, String surname, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, marks);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;
    }

    public Integer deleteData (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID = ?", new String[] {id});
        db.delete(TABLE_NAME_3, "ID = ?", new String[] {id});
        return db.delete(TABLE_NAME_4, "ID = ?", new String[] {id});
    }
}