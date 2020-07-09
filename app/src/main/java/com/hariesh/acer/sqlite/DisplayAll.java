package com.hariesh.acer.sqlite;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;





public class DisplayAll extends AppCompatActivity {
    public static String head;
    public static String des;
    public static ListView finallist;
    public static List<Todo> listofdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all);
    }

    @Override
    protected void onStart() {
        super.onStart();


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rview);
        DatabaseHandler db = new DatabaseHandler(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listofdata = new ArrayList<Todo>();

        Cursor cursor = db.getAllList();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Empty list", Toast.LENGTH_LONG).show();
            finish();
        } else {
            do {
                String key = cursor.getString(0);
                String head = cursor.getString(1);
                String des = cursor.getString(2);
                int status = cursor.getInt(3);
                Log.d("key===", key);
                listofdata.add(new Todo(key, head, des, status));
            } while (cursor.moveToNext());
            adapter listr = new adapter(this, listofdata);
            recyclerView.setAdapter(listr);
        }
    }
}

