package com.example.slispapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Numeric extends AppCompatActivity {

    GridView gridView;
    int[] numeric = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine, R.drawable.ten};
    String[] numbers = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
    String numbers2 [] ={"\"Emu\"", "\"Bbiri\"", "\"Satu\"","\"Nya\"", "\"Taano\"", "\"Mukaaga\"", "\"Musanvu\"", "\"Munaana\"", "\"Mwenda\"", "\"Kumi\""};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeric);
        getSupportActionBar().setTitle("SLISP: Numbers 1 to 10");

        gridView = (GridView) findViewById(R.id.gridData);

        GridAdapter adapter = new GridAdapter(Numeric.this, numeric, numbers,numbers2);

        gridView.setAdapter(adapter);

    }


}