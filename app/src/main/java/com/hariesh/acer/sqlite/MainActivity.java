package com.hariesh.acer.sqlite;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;





public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DatabaseHandler db = new DatabaseHandler(this);
        CardView viewall = (CardView) findViewById(R.id.viewlist);
        CardView addlist = (CardView) findViewById(R.id.addtolist);
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DisplayAll.class);
                startActivity(intent);

            }
        });
        addlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Addlist.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView heda = (TextView) findViewById(R.id.mainhead);
        TextView desa = (TextView) findViewById(R.id.maindes);
        TextView rata = (TextView) findViewById(R.id.rate);
        int c = 0;
        int total = 0;
        int success = 0;
        final DatabaseHandler db = new DatabaseHandler(this);
        try {
            Cursor cursor = db.getAllList();
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "Empty list", Toast.LENGTH_LONG).show();
            } else {
                do {
                    String key = cursor.getString(0);
                    String head = cursor.getString(1);
                    String des = cursor.getString(2);
                    int status = cursor.getInt(3);
                    if (total == 0)
                        heda.setText(head);
                    desa.setText(des);
                    if (status == 1)
                        success++;
                    total++;

                } while (cursor.moveToNext());
            }
            if(total==0)
                rata.setText("Add to list");

          //    Log.d("Rate",String.valueOf(rate));

            else{
                float rate = ((float) success / (float) total) * 100;
                rata.setText("You finished " + String.format("%.2f", rate) + "%");
            }


        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Empty", Toast.LENGTH_LONG).show();
        }

    }
}

