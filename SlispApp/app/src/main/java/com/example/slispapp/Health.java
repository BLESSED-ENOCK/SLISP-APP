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

public class Health extends AppCompatActivity {

    GridView gridView;

    int healthImages [] = {R.drawable.hospital,R.drawable.doctor, R.drawable.nurse, R.drawable.blood, R.drawable.sick,R.drawable.pain,R.drawable.injection,R.drawable.surgery,R.drawable.tablet};
    String healthNames [] = {"Hospital","Doctor","Nurse","Blood","Sick","Pain","Injection","Surgery","Tablet"};
    String healthNames2 [] = {"\"Eddwaliro\"", "\"Dokita\"", "\"Nansi\"", "\"Omusayi\"", "\"Omulwadde\"", "\"Obulumi\"", "\"Empiso\"", "\"Okulongosa\"", "\"Empeke\""};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        getSupportActionBar().setTitle("SLISP: Health Related Words");

        gridView = (GridView) findViewById(R.id.healthgrid);
        HealthAdapter adapter = new HealthAdapter(this,healthImages,healthNames,healthNames2);
        gridView.setAdapter(adapter);
    }


}

class HealthAdapter extends BaseAdapter{

    private int Healthimage2[];
    private String healthname[];
    private String healthname2[];
    private Context context;
    private LayoutInflater inflater;

    public HealthAdapter (Context context, int Healthimage2[], String healthname[], String healthname2[]){

        this.context= context;
        this.Healthimage2=Healthimage2;
        this.healthname=healthname;
        this.healthname2=healthname2;
    }
    @Override
    public int getCount() {
        return healthname.length;
    }

    @Override
    public Object getItem(int position) {
        return healthname[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View healthView = convertView;

        if (convertView==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            healthView = inflater.inflate(R.layout.healthlayout,null);
        }
        ImageView sign = (ImageView) healthView.findViewById(R.id.healthImage);
        TextView label = (TextView) healthView.findViewById(R.id.health);
        TextView label2 = (TextView) healthView.findViewById(R.id.health2);
        sign.setScaleType(ImageView.ScaleType.FIT_CENTER);
        sign.setImageResource(Healthimage2[position]);

        label.setText(healthname[position]);
        label2.setText(healthname2[position]);
        return healthView;
    }
}