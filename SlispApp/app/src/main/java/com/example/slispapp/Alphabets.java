package com.example.slispapp;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Alphabets extends AppCompatActivity {
    GridView gridView;


    int[] Images = { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j,R.drawable.k,R.drawable.l,R.drawable.m,R.drawable.n,R.drawable.o,R.drawable.p,R.drawable.q,R.drawable.r,R.drawable.s,R.drawable.t,R.drawable.u,R.drawable.v,R.drawable.w,R.drawable.x,R.drawable.y,R.drawable.z};
    String[] letters = { "A", "B", "C", "D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabets);
        getSupportActionBar().setTitle("SLISP:      Learn Alphabets");

       gridView = (GridView) findViewById(R.id.gridview);
       ImageAdapter adapter = new ImageAdapter(this,Images,letters );
        gridView.setAdapter(adapter);

    }



}
class ImageAdapter extends BaseAdapter{
    private int alphabet[];
    private String image2[];
    private Context context;
    private LayoutInflater inflater;

    public ImageAdapter (Context context, int alphabet[], String image2[]){

        this.context= context;
        this.alphabet=alphabet;
        this.image2=image2;
    }


    @Override
    public int getCount() {
        return image2.length;
    }

    @Override
    public Object getItem(int position) {
        return image2[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View textView = convertView;

        if (convertView==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            textView = inflater.inflate(R.layout.alphalayout,null);
        }
        ImageView sign = (ImageView) textView.findViewById(R.id.sign);
        TextView label = (TextView) textView.findViewById(R.id.label);
        sign.setScaleType(ImageView.ScaleType.FIT_CENTER);
        sign.setImageResource(alphabet[position]);

        label.setText(image2[position]);
        return textView;
    }

}
