package com.example.slispapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class About extends AppCompatActivity {
static  String status="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setTitle("About SLISP (Ebikwata Ku SLISP)");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitems,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //select item
        int id = item.getItemId();

        switch (item.getItemId()){
            case R.id.signup:
                Intent intent=new Intent(About.this,SignUp.class);
                startActivity(intent);
                break;

            case R.id.login2:
                Intent intent1=new Intent(About.this,Login.class);
                startActivity(intent1);
                break;

            case R.id.about4:
                Intent intent2=new Intent(About.this,About.class);
                startActivity(intent2);
                break;
            case R.id.searchsign:
                Intent intent3 = new Intent(About.this,SearchSign3.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
