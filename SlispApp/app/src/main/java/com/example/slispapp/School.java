package com.example.slispapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.PrivateKey;

public class School extends AppCompatActivity {

    GridView gridView;

    int schoolImages [] = {R.drawable.school,R.drawable.teacher, R.drawable.student, R.drawable.vocational_program, R.drawable.sewing,R.drawable.sign_language, R.drawable.how_was_school};
    String schoolNames [] = {"School","Teacher","Student","Vocational","Sewing","Sign Language","How was the school?"};
    String schoolNames3 [] = {"\"Esoomero\"", "\"Omusomesa\"", "\"Omuyizi\"", "\"Ebyemikono\"", "\"Okutunga\"", "\"Olulimi lwa sayini\"", "\"Esoomero libadde litya?\""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
        getSupportActionBar().setTitle("SLISP: School Elements");


        gridView = (GridView) findViewById(R.id.schoolgrid);
        SchoolAdapter adapter = new SchoolAdapter(this,schoolImages,schoolNames,schoolNames3);
        gridView.setAdapter(adapter);
    }

}


class SchoolAdapter extends BaseAdapter{

    private int Schoolimage2[];
    private String Schoolname[];
    private String Schoolname3[];
    private Context context;
    private LayoutInflater inflater;

    public SchoolAdapter (Context context, int Schoolimage2[], String Schoolname[], String Schoolname3[]){

        this.context= context;
        this.Schoolimage2=Schoolimage2;
        this.Schoolname=Schoolname;
        this.Schoolname3=Schoolname3;
    }

    @Override
    public int getCount() {
        return Schoolname.length;
    }

    @Override
    public Object getItem(int position) {
        return Schoolname[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View SchoolView = convertView;

        if (convertView==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            SchoolView = inflater.inflate(R.layout.schoollayout,null);
        }
        ImageView sign = (ImageView)SchoolView.findViewById(R.id.schoolImage);
        TextView label = (TextView) SchoolView.findViewById(R.id.school);
        TextView label2 = (TextView) SchoolView.findViewById(R.id.school3);
        sign.setScaleType(ImageView.ScaleType.FIT_CENTER);

        sign.setImageResource(Schoolimage2[position]);
        label.setText(Schoolname[position]);
        label2.setText(Schoolname3[position]);
        return SchoolView;
    }

}