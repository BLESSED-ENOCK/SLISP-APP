package com.example.slispapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
  private int sign1[];
  private String label1[];
  private String Numbers2[];
  private Context context;
  private LayoutInflater inflater;
    public GridAdapter(Context context,int sign[],String label[],String numbers2[]){

        this.context= context;
        this.sign1=sign;
        this.label1=label;
        this.Numbers2=numbers2;



    }

    @Override
    public int getCount() {
        return label1.length;
    }

    @Override
    public Object getItem(int position) {
        return label1[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridView = convertView;

        if (convertView==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            gridView = inflater.inflate(R.layout.numerics,null);

        }

        ImageView sign = (ImageView) gridView.findViewById(R.id.sign);
        TextView label = (TextView) gridView.findViewById(R.id.label);
        TextView Number2 = (TextView) gridView.findViewById(R.id.number2);
        sign.setScaleType(ImageView.ScaleType.FIT_CENTER);
        sign.setImageResource(sign1[position]);

        label.setText(label1[position]);
        Number2.setText( Numbers2[position] );

        return gridView;
    }
}
