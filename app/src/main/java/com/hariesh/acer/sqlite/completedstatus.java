package com.hariesh.acer.sqlite;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.allyants.notifyme.NotifyMe;

public class completedstatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.green);
        if(Build.VERSION.SDK_INT>=21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.green));
        }
        setContentView(R.layout.activity_completedstatus);
        Button delbutton = (Button) findViewById(R.id.cdeletebutton);
        final TextView headtext=(TextView)findViewById(R.id.cstatushead);
        final TextView destext=(TextView)findViewById(R.id.cdesstatus);
        final String cancel=getIntent().getStringExtra("Head");
        headtext.setText(cancel);
        destext.setText(getIntent().getStringExtra("Des"));
        final String str=getIntent().getStringExtra("Key");
        final DatabaseHandler db=new DatabaseHandler(this);
        delbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteContact(str);
                NotifyMe.cancel(completedstatus.this,cancel);
                Intent itent = new Intent(completedstatus.this,MainActivity.class);
                startActivity(itent);
                finish();
            }
        });

    }
}
