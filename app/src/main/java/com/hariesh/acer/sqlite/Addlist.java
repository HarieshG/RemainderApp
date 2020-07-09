package com.hariesh.acer.sqlite;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;


import com.allyants.notifyme.NotifyMe;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static com.hariesh.acer.sqlite.DatabaseHandler.checklist;

public class Addlist extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener,DatePickerDialog.OnDateSetListener{

    Calendar now = Calendar.getInstance();
String key;
    TimePickerDialog tpd;

    DatePickerDialog dpd;

    EditText gethead;
    EditText getdes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setTheme(R.style.orange);
        if(Build.VERSION.SDK_INT>=21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorAccent));
        }

        setContentView(R.layout.activity_addlist);

        dpd = DatePickerDialog.newInstance(

                 Addlist.this,

                now.get(Calendar.YEAR),

                now.get(Calendar.MONTH),

                now.get(Calendar.DAY_OF_MONTH)

        );
        tpd =TimePickerDialog.newInstance(

                 Addlist.this,

                now.get(Calendar.HOUR_OF_DAY),

                now.get(Calendar.MINUTE),

                now.get(Calendar.SECOND),

                false

        );

        final DatabaseHandler db = new DatabaseHandler(this);
        Button addlist = (Button) findViewById(R.id.button2);

        gethead = (EditText) findViewById(R.id.head);
        getdes = (EditText) findViewById(R.id.description);
        try{
        gethead.setHint(getIntent().getStringExtra("Header"));
        getdes.setHint(getIntent().getStringExtra("Descrip"));}
      catch (Exception e){
        gethead.setHint("Enter the work");
        getdes.setHint("Enter short description");
      }
        addlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (!checklist.isEmpty() && checklist.contains(gethead.getText().toString())) {

                        Toast.makeText(getApplicationContext(), "Already in list", Toast.LENGTH_LONG).show();
                    } else {
                        dpd.show(getFragmentManager(), "Datepickerdialog");
                        key=UUID.randomUUID().toString();
                        db.addTodo(new Todo(key, gethead.getText().toString(), getdes.getText().toString(),0));
                        Toast.makeText(getApplicationContext(), " added", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    dpd.show(getFragmentManager(), "Datepickerdialog");
                    key=UUID.randomUUID().toString();
                    db.addTodo(new Todo(key, gethead.getText().toString(), getdes.getText().toString(),0));
                }


            }
        });


    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        now.set(Calendar.YEAR,year);

        now.set(Calendar.MONTH,monthOfYear);

        now.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        tpd.show(getFragmentManager(), "Timepickerdialog");
    }

        @Override
        public void onTimeSet (TimePickerDialog view,int hourOfDay, int minute, int second){
        now.set(Calendar.HOUR_OF_DAY, hourOfDay);

        now.set(Calendar.MINUTE, minute);

        now.set(Calendar.SECOND, second);

        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);

        intent.putExtra("test", "I am a String");
try{
        NotifyMe notifyMe = new NotifyMe.Builder(getApplicationContext())
                .title(gethead.getText().toString())

                .content(getdes.getText().toString())

                .color(19, 9, 116, 100)

                .led_color(255, 255, 255, 255)

                .time(now)

                .key("test")

                .addAction(new Intent(), "Dismiss", true, false)

                .addAction(intent, "Done")

                .addAction(intent, "Doo", true, true)

                .large_icon(R.mipmap.ic_launcher_round)

                .build();}catch (Exception e){
            Log.d("Error -> ",key);
                }
    }
}
