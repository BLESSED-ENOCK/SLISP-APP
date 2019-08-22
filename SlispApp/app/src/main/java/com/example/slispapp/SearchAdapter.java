package com.example.slispapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.List;

public class SearchAdapter extends BaseAdapter {

    Context ctx;
    int res;
    List<SearchSign2>searchlist;

    public SearchAdapter(Context contx ,int resource ,List<SearchSign2> object) {
        super();
        this.ctx=contx;
        this.res=resource;
        this.searchlist=object;
    }

    @Override
    public int getCount() {
        return searchlist.size();
    }

    @Override
    public Object getItem(int position) {
        return searchlist.get( position );
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate( res,parent,false );
        ImageView img =(ImageView)view.findViewById( R.id.Signview );
        TextView eng=(TextView)view.findViewById( R.id.EnglishName );
        TextView lug=(TextView)view.findViewById( R.id.LugandaName );
        TextView desc=(TextView)view.findViewById( R.id.Description );

        SearchSign2 searchObject =searchlist.get( position );

        eng.setText(  searchObject.getEnglishName());
        lug.setText(  searchObject.getLugandaName());
        desc.setText(  searchObject.getDescription());
 Glide.with(ctx).load("http://"+Data.serverIP+"/SlispApp/"+searchObject.getSignImage()).into(img);


        return view;
    }
}
