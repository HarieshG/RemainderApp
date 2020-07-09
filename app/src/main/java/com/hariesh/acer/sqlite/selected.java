package com.hariesh.acer.sqlite;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.allyants.notifyme.NotifyMe;

public class selected extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTheme(R.style.orange);
        if(Build.VERSION.SDK_INT>=21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorAccent));
        }
        setContentView(R.layout.activity_selected);
        Button upbutton=(Button) findViewById(R.id.editbutton);
        Button dontbutton=(Button)findViewById(R.id.donebutton);
        Button delbutton = (Button) findViewById(R.id.deletebutton);
         final TextView headtext=(TextView)findViewById(R.id.statushead);
        final TextView destext=(TextView)findViewById(R.id.desstatus);

        final String headstr=getIntent().getStringExtra("Head");
        final String desstr=getIntent().getStringExtra("Des");
        headtext.setText(getIntent().getStringExtra("Head"));
        destext.setText(getIntent().getStringExtra("Des"));
        final String str=getIntent().getStringExtra("Key");

        final DatabaseHandler db=new DatabaseHandler(this);
        upbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteContact(str);
                try{
                NotifyMe.cancel(getApplicationContext(),"test");}
                catch (Exception e){

                }
                Intent itent = new Intent(selected.this,Addlist.class);
                itent.putExtra("Header",headstr);
                itent.putExtra("Descrip",desstr);
                startActivity(itent);
                finish();
            }
        });
        delbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteContact(str);
                NotifyMe.cancel(getApplicationContext(),"test");
                Intent itent = new Intent(selected.this,MainActivity.class);
                startActivity(itent);
                finish();
            }
        });
        dontbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteContact(str);
                NotifyMe.cancel(getApplicationContext(),"test");
                db.addTodo(new Todo(str,headstr,desstr,1));
                Intent itent = new Intent(selected.this,MainActivity.class);
                startActivity(itent);
                finish();
            }
        });
    }

}
