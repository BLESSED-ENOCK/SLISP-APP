package com.example.slispapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    ListView listView;

    int img1[] = {R.drawable.broadway,
            R.drawable.numbers,
            R.drawable.basics,
            R.drawable.greetings,
            R.drawable.family,
            R.drawable.home,
            R.drawable.eat_food,
            R.drawable.school,
            R.drawable.health};
    String str[]  = {"Alphabets", "Numbers", "Basics", "Greetings", "Family", "Home", "Food", "School","Health"};
    String str2[]  = {"\"Enyukuta\"", "\"Emiwendo\"", "\"Ebisokelwako\"", "\"Enamusa\"", "\"Abomumaka\"", "\"Ebyewaka\"", "\"Ebyemeere\"", "\"Ebyokussomero\"","\"Ebyobulamu\""};

     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitems,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //select item
        int id = item.getItemId();

        switch (item.getItemId()){
            case R.id.signup:
                Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
               break;

            case R.id.login2:
                Intent intent1=new Intent(MainActivity.this,Login.class);
                startActivity(intent1);
                break;

            case R.id.about4:
                Intent intent2=new Intent(MainActivity.this,About.class);
                startActivity(intent2);
                break;
            case R.id.searchsign:
                Intent intent3 = new Intent(MainActivity.this,SearchSign3.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView =  findViewById(R.id.listview);
        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);


       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.activity_list_item,android.R.id.text1,str);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


               if (position==0){

                   Intent intent = new Intent(getApplicationContext(),Alphabets.class);
                   intent.putExtra("textView",str[position]);
                   intent.putExtra("imageView",img1[position]);
                   startActivity(intent);
                }
                if (position==1){

                    Intent intent = new Intent(getApplicationContext(),Numeric.class);
                    intent.putExtra("textView",str[position]);
                    intent.putExtra("imageView",img1[position]);
                    startActivity(intent);
                }
                if (position==2){

                    Intent intent = new Intent(getApplicationContext(),Basics.class);
                    intent.putExtra("textView",str[position]);
                    intent.putExtra("imageView",img1[position]);
                    startActivity(intent);
                }
                if (position==3){

                    Intent intent = new Intent(getApplicationContext(),Greetings.class);
                    intent.putExtra("textView",str[position]);
                    intent.putExtra("imageView",img1[position]);
                    startActivity(intent);
                }
                if (position==4){

                    Intent intent = new Intent(getApplicationContext(),Family.class);
                    intent.putExtra("textView",str[position]);
                    intent.putExtra("imageView",img1[position]);
                    startActivity(intent);
                }

                if (position==5){

                    Intent intent = new Intent(getApplicationContext(),Home.class);
                    intent.putExtra("textView",str[position]);
                    intent.putExtra("imageView",img1[position]);
                    startActivity(intent);
                }

                if (position==6){

                    Intent intent = new Intent(getApplicationContext(),Food.class);
                    intent.putExtra("textView",str[position]);
                    intent.putExtra("imageView",img1[position]);
                    startActivity(intent);
                }
                if (position==7){

                    Intent intent = new Intent(getApplicationContext(),School.class);
                    intent.putExtra("textView",str[position]);
                    intent.putExtra("imageView",img1[position]);
                    startActivity(intent);
                }
                if (position==8){

                    Intent intent = new Intent(getApplicationContext(),Health.class);
                    intent.putExtra("textView",str[position]);
                    intent.putExtra("imageView",img1[position]);
                    startActivity(intent);
                }
            }
        });

    }

    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return img1.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

           convertView = getLayoutInflater().inflate(R.layout.customlayout,null);

           ImageView img = convertView.findViewById(R.id.imageView);
           TextView txt = convertView.findViewById(R.id.textView);
           TextView txt1 = convertView.findViewById(R.id.name2);

           img.setImageResource(img1[position]);
           txt.setText(str[position]);
           txt1.setText(str2[position]);

            return convertView;
        }
    }
}