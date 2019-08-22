package com.example.slispapp;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeUser extends Fragment {

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
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home_user,container,false);



        listView =  view.findViewById(R.id.listview2);
        HomeUser.CustomAdapter customAdapter = new HomeUser.CustomAdapter();

        listView.setAdapter(customAdapter);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.activity_list_item,android.R.id.text1,str);
        // listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position==0){
                    // Intent intent = new Intent(view.getContext(),Alphabets.class);
                    Intent intent = new Intent(view.getContext(),Alphabets.class);
                    intent.putExtra("textView",str[position]);
                    intent.putExtra("imageView",img1[position]);
                    startActivity(intent);
                }
                if (position==1){
                    // Intent intent = new Intent(view.getContext(),.class);
                    Intent intent = new Intent(view.getContext(),Numeric.class);
                    intent.putExtra("textView",str[position]);
                    intent.putExtra("imageView",img1[position]);
                    startActivity(intent);
                }
                if (position==2){
                    // Intent intent = new Intent(view.getContext(),.class);
                    Intent intent = new Intent(view.getContext(),Basics.class);
                    intent.putExtra("textView",str[position]);
                    intent.putExtra("imageView",img1[position]);
                    startActivity(intent);
                }
                if (position==3){
                    // Intent intent = new Intent(view.getContext(),Alphabets.class);
                    Intent intent = new Intent(view.getContext(),Greetings.class);
                    intent.putExtra("textView",str[position]);
                    intent.putExtra("imageView",img1[position]);
                    startActivity(intent);
                }
                if (position==4){
                    // Intent intent = new Intent(view.getContext(),Alphabets.class);
                    Intent intent = new Intent(view.getContext(),Family.class);
                    intent.putExtra("textView",str[position]);
                    intent.putExtra("imageView",img1[position]);
                    startActivity(intent);
                }

                if (position==5){
                    // Intent intent = new Intent(view.getContext(),Alphabets.class);
                    Intent intent = new Intent(view.getContext(),Home.class);
                    intent.putExtra("textView",str[position]);
                    intent.putExtra("imageView",img1[position]);
                    startActivity(intent);
                }

                if (position==6){
                    // Intent intent = new Intent(view.getContext(),Alphabets.class);
                    Intent intent = new Intent(view.getContext(),Food.class);
                    intent.putExtra("textView",str[position]);
                    intent.putExtra("imageView",img1[position]);
                    startActivity(intent);
                }
                if (position==7){
                    // Intent intent = new Intent(view.getContext(),Alphabets.class);
                    Intent intent = new Intent(view.getContext(),School.class);
                    intent.putExtra("textView",str[position]);
                    intent.putExtra("imageView",img1[position]);
                    startActivity(intent);
                }
                if (position==8){
                    // Intent intent = new Intent(view.getContext(),Alphabets.class);
                    Intent intent = new Intent(view.getContext(),Health.class);
                    intent.putExtra("textView",str[position]);
                    intent.putExtra("imageView",img1[position]);
                    startActivity(intent);
                }
            }
        });

        return view;
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

        @SuppressLint({"ViewHolder","InflateParams"})
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
