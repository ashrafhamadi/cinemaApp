package com.example.cinemaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 = "create table userscinema(username text,email text, password text)";
        db.execSQL(qry1);

        String qry2 = "create table tickets(username text,fullname text,movie text,nboftickets int,price int, date text, time text, cinematype text)";
        db.execSQL(qry2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void Register(String username, String email, String password){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("userscinema",null,cv);
        db.close();
    }

    public int login(String username, String password){
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from userscinema where username=? and password=?",str);
        if(c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }

    public void buyTickets(String username, String fullname, String movie, int nboftickets, int price, String date, String time, String cinemaType){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("fullname", fullname);
        cv.put("movie", movie);
        cv.put("nboftickets", nboftickets);
        cv.put("price",price);
        cv.put("date" ,date);
        cv.put("time", time);
        cv.put("cinemaType" , cinemaType);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("tickets", null, cv);
        db.close();
    }

    public ArrayList getOrderData(String username) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("select * from tickets where username = ?",str);
        if(c.moveToFirst()) {
            do{
                arr.add(c.getString(1) + "$" + c.getString(2) + "$" +c.getString( 3)+ "$" +c.getString(4) + "$" +c.getString(5) + "$"+c.getString(6) + "$"+c.getString(7));
            }while(c.moveToNext());
        }
        db.close();
        return arr;
    }
}
