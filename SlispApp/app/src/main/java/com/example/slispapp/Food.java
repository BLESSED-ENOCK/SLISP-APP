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

public class Food extends AppCompatActivity {

    GridView gridView;

    int foodImages [] = {R.drawable.bread,R.drawable.porridge, R.drawable.samosa, R.drawable.egg, R.drawable.cook,R.drawable.eat, R.drawable.cooking_oil, R.drawable.fish, R.drawable.pineapple, R.drawable.flour, R.drawable.groundnuts, R.drawable.cassava,R.drawable.cabbage, R.drawable.mango, R.drawable.irish_potato, R.drawable.maize, R.drawable.matooke, R.drawable.meat, R.drawable.peas,R.drawable.posho, R.drawable.rice, R.drawable.water, R.drawable.salt, R.drawable.tea};
    String foodNames [] = {"Bread","Porridge","Samosa","Egg","Cooking","Eating","Cooking Oil", "Fish","Pineapple", "Flour","Ground Nuts","Cassava","Cabbages", "Mango","Irish Potatoes", "Maize","Matooke","Meat","Peas", "Posho","Rice", "Water","Salt", "Tea"};
    String foodNames2 [] = {"\"Omugaati\"", "\"Obugi\"", "\"Sumbusa\"", "\"Eggi\"", "\"Okufumba\"", "\"Okulya\"", "\"Buto\"", "\"Ekyenyanja\"", "\"Ennanansi\"", "\"Obuwunga\"", "\"Ebinyebwa\"", "\"Muwogo\"", "\"Emboga\"", "\"Omuyembe\"", "\"Obumonde obuzungu\"", "\"Kasooli\"", "\"Amatooke\"","\"Ennyama\"", "\"Kawo\"", "\"Akawunga\"", "\"Omucceere\"","\"Amazzi\"", "\"Omunnyo\"", "\"Amajjani\"", "\"Omugaati\"", "\"Omugaati\""};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        getSupportActionBar().setTitle("SLISP: Food Related Words");

        gridView = (GridView) findViewById(R.id.foodgrid);
        FoodAdapter adapter = new FoodAdapter(this,foodImages,foodNames, foodNames2);
        gridView.setAdapter(adapter);
    }


}

class FoodAdapter extends BaseAdapter{

    private int Foodimage2[];
    private String Foodname[];
    private String Foodname2[];
    private Context context;
    private LayoutInflater inflater;

    public FoodAdapter (Context context, int Foodimage2[], String Foodname[], String Foodname2[]){

        this.context= context;
        this.Foodimage2=Foodimage2;
        this.Foodname=Foodname;
        this.Foodname2=Foodname2;
    }
    @Override
    public int getCount() {
        return Foodname.length;
    }

    @Override
    public Object getItem(int position) {
        return Foodname[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View FoodView = convertView;

        if (convertView==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            FoodView = inflater.inflate(R.layout.foodlayout,null);
        }
        ImageView sign = (ImageView)FoodView.findViewById(R.id.foodImage);
        TextView label = (TextView) FoodView.findViewById(R.id.food);
        TextView label2 = (TextView) FoodView.findViewById(R.id.food2);
        sign.setScaleType(ImageView.ScaleType.FIT_CENTER);
        sign.setImageResource(Foodimage2[position]);

        label.setText(Foodname[position]);
        label2.setText(Foodname2[position]);
        return FoodView;
    }
}