package com.example.studentdatasqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    // Database
    public static final String DB_NAME = "Student.db";
    public static final String DB_TABLE = "student_list";

    // Col
    public static final String ID = "ID";
    public static final String ROLLNUM = "RollNumber";
    public static final String NAME = "Name";
    public static final String EMAIL = "Email";
    public static final String PHONE = "Phone";

    // CREATE TABLE
    public static final String CREATE_TABLE = "CREATE TABLE "+ DB_TABLE +"("+
            ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            ROLLNUM+" INTEGER, "+
            NAME+" TEXT, "+
            EMAIL+" TEXT, "+
            PHONE+" TEXT"+")";

    // public variable to get rollnumber.
    public String spinnervalue;


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE);

        onCreate(db);

    }
    // Adding Data in table.
    public boolean insertData(String rollnum, String name, String email, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ROLLNUM, rollnum);
        contentValues.put(NAME, name);
        contentValues.put(EMAIL, email);
        contentValues.put(PHONE, phone);
        Long result = db.insert(DB_TABLE, null, contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }

    }
    // View All Data
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+DB_TABLE,null);

        return res;
    }

    //Spinner List Data.
    public ArrayList<String> getRollNumberLists(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            String selectQuery = "SELECT * FROM " + DB_TABLE;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String sname = cursor.getString(cursor.getColumnIndex("RollNumber"));
                    list.add(sname);
                }
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }

        return list;

    }
    //View data in editTexts.
    public Cursor getRollNumberData(){
        String string = new String(spinnervalue);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+DB_TABLE+" WHERE "+ROLLNUM +" = "+string,null);

        return res;
    }

    //Updating Data in Table.
    public boolean updatedata(String rollnumb, String name, String email, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ROLLNUM,rollnumb);
        contentValues.put(NAME, name);
        contentValues.put(EMAIL, email);
        contentValues.put(PHONE, phone);
        String[] whereArgs = new String[] {String.valueOf(rollnumb)};
        db.update(DB_TABLE, contentValues, "RollNumber = ?", whereArgs);
        return true;
    }
    //Deleting Data.
    public Integer DeleteData(String rollnumber){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs = new String[] {String.valueOf(rollnumber)};
        return db.delete(DB_TABLE, "RollNumber = ?", whereArgs);

    }



}
