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

public class Home extends AppCompatActivity {

    GridView gridView;

    int homeImages [] = {R.drawable.give,R.drawable.bring_to_me, R.drawable.wash_plates, R.drawable.sweep_the_compound, R.drawable.mop_the_house,R.drawable.wash_clothes, R.drawable.bathe, R.drawable.brush_teeth};
    String homeNames [] = {"Give","Bring to me","Wash plates","Sweep the compound","Mop the house","Wash clothes","Bathe", "Brush teeth"};
    String homeNames2 [] ={"\"Wa\"", "\"Ndetera\"", "\"Yoza essoowani\"","\"Yeela olujja\"", "\"Okusimula ennyumba\"", "\"Yoza engoye\"", "\"Okunaaba\"", "\"Okusenya amannyo\""};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("SLISP:      Home Activities");

        gridView = (GridView) findViewById(R.id.homegrid);
        HomeAdapter adapter = new HomeAdapter(this,homeImages,homeNames,homeNames2);
        gridView.setAdapter(adapter);
    }

}

class HomeAdapter extends BaseAdapter{

    private int Homeimage2[];
    private String Homename[];
    private String homename[];
    private Context context;
    private LayoutInflater inflater;

    public HomeAdapter (Context context, int Homeimage2[], String Homename[],String homename[]){

        this.context= context;
        this.Homeimage2=Homeimage2;
        this.Homename=Homename;
        this.homename=homename;
    }




    @Override
    public int getCount() {
        return Homename.length;
    }

    @Override
    public Object getItem(int position) {
        return Homename[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View HomeView = convertView;

        if (convertView==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

           HomeView = inflater.inflate(R.layout.homelayout,null);
        }
        ImageView sign = (ImageView)HomeView.findViewById(R.id.homeImage);
        TextView label = (TextView) HomeView.findViewById(R.id.home);
        TextView label2 = (TextView) HomeView.findViewById(R.id.home2);
        sign.setScaleType(ImageView.ScaleType.FIT_CENTER);


        sign.setImageResource(Homeimage2[position]);
        label.setText(Homename[position]);
        label2.setText(homename[position]);


        return HomeView;
    }
}
