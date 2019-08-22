package com.example.slispapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class SignListview extends ArrayAdapter<String> {

    private String[] EnglishName;
    private String[] LugandaName;
    private String[] Sign;
    private Activity context;
    Bitmap bitmap;

    public SignListview(Activity context,String[] EnglishName, String[] LugandaName, String[] Sign ) {
        super(context,R.layout.verifysignlayout,EnglishName);
        this.context=context;
        this.EnglishName=EnglishName;
        this.LugandaName=LugandaName;
        this.Sign=Sign;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View r=convertView;
        ViewHolder viewHolder=null;
        if (r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.verifysignlayout,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)r.getTag();
        }

        viewHolder.tvw1.setText(EnglishName[position]);
        viewHolder.tvw2.setText(LugandaName[position]);

        new GetImageFromURL(viewHolder.ivw).execute(Sign[position]);


        return r;
    }
    class ViewHolder{
        TextView tvw1;
        TextView tvw2;
        ImageView ivw;

        ViewHolder(View v){
            tvw1=(TextView)v.findViewById(R.id.englishName);
            tvw2=(TextView)v.findViewById(R.id.lugandaName);
            ivw=(ImageView)v.findViewById(R.id.imageSign);
        }
    }
    public class GetImageFromURL extends AsyncTask<String,Void, Bitmap>{

        ImageView imageView;
        public GetImageFromURL(ImageView imv){
            this.imageView=imv;
        }
        @Override
        protected Bitmap doInBackground(String... url) {
            String urldisplay=url[0];
            bitmap=null;
            try {
                InputStream ist=new java.net.URL(urldisplay).openStream();
                bitmap= BitmapFactory.decodeStream(ist);

            }
            catch (Exception ex){
                ex.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }
}
