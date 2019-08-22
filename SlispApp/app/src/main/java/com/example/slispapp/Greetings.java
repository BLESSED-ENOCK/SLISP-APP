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

public class Greetings extends AppCompatActivity {
   GridView gridView;

    int greetingsImages [] = {R.drawable.hello,R.drawable.welcome, R.drawable.good, R.drawable.bye, R.drawable.how_are_you,R.drawable.i_love_you, R.drawable.evening, R.drawable.night, R.drawable.afternoon, R.drawable.my_name_is};
    String greetingsNames [] = {"Hello","Welcome","Good","Bye","How are you?","I love you","Evening", "Night","Afternoon", "My name is..."};
    String greetingsNames2 [] = {"\"Halo\"","\"Kulika yo\"","\"Kilungi\"","\"Welaba\"","\"Oli otya?\"","\"Nkuyagala\"","\"Akawungezi\"","\"Ekiro\"","\"Entuntu\"","\"Elinya lyange nze...\"",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greetings);
        getSupportActionBar().setTitle("SLISP: Greeting Related Words");

        gridView = (GridView) findViewById(R.id.greetingsgrid);
        GreetingsAdapter adapter = new GreetingsAdapter(this,greetingsImages,greetingsNames,greetingsNames2);
        gridView.setAdapter(adapter);
    }

}

class GreetingsAdapter extends BaseAdapter{

    private int Greetingsimage2[];
    private String Greetingsname[];
    private String Greetingsname2[];
    private Context context;
    private LayoutInflater inflater;

    public GreetingsAdapter (Context context, int Greetingsimage2[], String Greetingsname[], String Greetingsname2[]){

        this.context= context;
        this.Greetingsimage2=Greetingsimage2;
        this.Greetingsname=Greetingsname;
        this.Greetingsname2=Greetingsname2;
    }


    @Override
    public int getCount() {
        return Greetingsname.length;
    }

    @Override
    public Object getItem(int position) {
        return Greetingsname[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View GreetingsView = convertView;

        if (convertView==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            GreetingsView = inflater.inflate(R.layout.greetingslayout,null);
        }
        ImageView sign = (ImageView) GreetingsView.findViewById(R.id.greetingsImage);
        TextView label = (TextView) GreetingsView.findViewById(R.id.greetings);
        TextView label2 = (TextView) GreetingsView.findViewById(R.id.greetings2);
        sign.setScaleType(ImageView.ScaleType.FIT_CENTER);
        sign.setImageResource(Greetingsimage2[position]);

        label.setText(Greetingsname[position]);
        label2.setText(Greetingsname2[position]);

        return GreetingsView;
    }
}