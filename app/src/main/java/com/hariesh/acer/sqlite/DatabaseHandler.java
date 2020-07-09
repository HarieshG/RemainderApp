package com.hariesh.acer.sqlite;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static List<String> checklist=new ArrayList<>();
    public static int version=1;
    public static String dbname="TodoList";
    public static String tablename="Todo";
    public static String Key_id="Id";
    public static String Key_head="Head";
    public static String Key_dis="Description";
    public static String Key_status="Status";



    public DatabaseHandler(Context mainActivity) {
        super(mainActivity,dbname,null,version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE "+tablename+"("+Key_id+ " TEXT," + Key_head + " TEXT,"
                + Key_dis + " TEXT," + Key_status + " INT" + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addTodo(Todo td){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(Key_id,td.getId());
        contentValues.put(Key_head,td.getHead());
        contentValues.put(Key_dis,td.getDescription());
        contentValues.put(Key_status,td.getStatus());
        checklist.add(td.getHead());
        db.insert(tablename,null,contentValues);
        db.close();
    }
    public Cursor getAllList(){
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+tablename, null );
        if (res != null) {
            res.moveToFirst();
        }
       return res;
    }
    public void deleteContact(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tablename, Key_id + " = ?",
                new String[] { String.valueOf(key) });
        db.close();
    }




}
