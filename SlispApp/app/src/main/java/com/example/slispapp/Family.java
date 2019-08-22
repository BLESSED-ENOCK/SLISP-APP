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

public class Family extends AppCompatActivity {
   GridView gridView;

    int familyImages [] = {R.drawable.parents,R.drawable.father, R.drawable.mother, R.drawable.uncle, R.drawable.aunt,R.drawable.brother, R.drawable.sister, R.drawable.friend, R.drawable.he_or_she};
    String familyNames [] = {"Parents","Father","Mother","Uncle","Aunt","Brother","Sister", "Friend","He or She is my friend"};
    String familyNames2[] = {"\"Abazadde\"", "\"Taata\"", "\"Maama\"", "\"Kojja\"", "\"Ssenga\"", "\"Muganda wange omulenzi\"","\"Muganda wange omuwala\"","\"Mukwano\"", "\"Oyo mukwano ngwange\""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        getSupportActionBar().setTitle("SLISP: Friends & Family");

        gridView = (GridView) findViewById(R.id.familygrid);
        FamilyAdapter adapter = new FamilyAdapter(this,familyImages,familyNames,familyNames2);
        gridView.setAdapter(adapter);
    }


}

class FamilyAdapter extends BaseAdapter{

    private int Familyimage2[];
    private String Familyname[];
    private String Familyname2[];
    private Context context;
    private LayoutInflater inflater;

    public FamilyAdapter(Context context,int Familyimage2[],String Familyname[],String Familyname2[]){

        this.context= context;
        this.Familyimage2=Familyimage2;
        this.Familyname=Familyname;
        this.Familyname2=Familyname2;

    }

    @Override
    public int getCount() {
        return Familyname.length;
    }

    @Override
    public Object getItem(int position) {
        return Familyname[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View FamilyView = convertView;

        if (convertView==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            FamilyView = inflater.inflate(R.layout.familylayout,null);
        }
        ImageView sign = (ImageView)FamilyView.findViewById(R.id.familyImage);
        TextView label = (TextView) FamilyView.findViewById(R.id.family);
        TextView label2 = (TextView) FamilyView.findViewById(R.id.family2);

                sign.setScaleType(ImageView.ScaleType.FIT_CENTER);
        sign.setImageResource(Familyimage2[position]);

        label.setText(Familyname[position]);
        label2.setText(Familyname2[position]);
        return FamilyView;
    }
}
