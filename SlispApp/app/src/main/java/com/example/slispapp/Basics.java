package com.example.slispapp;

import android.content.Context;
import android.content.Intent;
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

import org.w3c.dom.Text;

public class Basics extends AppCompatActivity {
    GridView gridView;

    int basicsImages [] = {R.drawable.good,R.drawable.bad, R.drawable.what, R.drawable.where, R.drawable.who,R.drawable.why};
    String basicsNames [] = {"Good","Bad","What?","Where?","Who?","Why?"};
    String basicsName2[] = {"\"Kirungi\"", "\"Kibi\"","\"Kiki? / Kki?\"", "\"Wa/ Luda wa?\"", "\"Ani?\"", "\"Lwaaki\""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basics);
        getSupportActionBar().setTitle("SLISP:      Basics Words");

        gridView = (GridView) findViewById(R.id.basicsgrid);
        BasicsAdapter adapter = new BasicsAdapter(this,basicsImages,basicsNames,basicsName2);
        gridView.setAdapter(adapter);

    }

}

class BasicsAdapter extends BaseAdapter{

    private int Basicsimage2[];
    private String Basicsname[];
    private String basicsname[];
    private Context context;
    private LayoutInflater inflater;

    public BasicsAdapter (Context context, int Basicsimage2[], String Basicsname[], String basicsname[]){

        this.context= context;
        this.Basicsimage2=Basicsimage2;
        this.Basicsname=Basicsname;
        this.basicsname=basicsname;
    }

    @Override
    public int getCount() {
        return Basicsname.length;
    }

    @Override
    public Object getItem(int position) {
        return Basicsname[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View BasicsView = convertView;

        if (convertView==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            BasicsView = inflater.inflate(R.layout.basicslayout,null);
        }
        ImageView sign = (ImageView) BasicsView.findViewById(R.id.basicsImage);
        TextView label = (TextView) BasicsView.findViewById(R.id.basics);
        TextView label2 = (TextView) BasicsView.findViewById(R.id.basics2);
        sign.setScaleType(ImageView.ScaleType.FIT_CENTER);
        sign.setImageResource(Basicsimage2[position]);

        label.setText(Basicsname[position]);
        label2.setText(basicsname[position]);
        return BasicsView;
    }
}