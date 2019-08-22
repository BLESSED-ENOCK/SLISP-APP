package com.example.slispapp;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchSign3 extends AppCompatActivity {
List<SearchSign2> searchSignList= new ArrayList<SearchSign2>();
    SearchAdapter searchAdapter;
    ListView slistview;
    EditText edsearch;
    Button btnsearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_search_sign3);
        getSupportActionBar().setTitle("SLISP:  Search Sign");
        slistview =(ListView) findViewById(R.id.searchlist);
        edsearch = (EditText) findViewById(R.id.signname);
        btnsearch=(Button) findViewById(R.id.submit3);

        btnsearch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edsearch.getText().length()<=0){
                    Toast.makeText(SearchSign3.this, "Enter English or Luganda Name.\n\nWandiika Ekigambo Muluzungu oba Muluganda.", Toast.LENGTH_SHORT).show();
                }
                else{

                    search();

                }

            }
        } );
    }
    public void search(){
        StringRequest    stringRequest = new StringRequest( Request.Method.POST,"http://"+Data.serverIP+"/SlispApp/SearchSign.php",
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONArray jArray = new JSONArray(response);
                            JSONObject json_data ;
                            searchSignList.clear();
                            for (int i = 0; i < jArray.length() ; i++) {

                                json_data = jArray.getJSONObject( i );
                                searchSignList.add(new SearchSign2(json_data.getString("Sign"),json_data.getString("English_Name"),json_data.getString("Luganda_Name"),json_data.getString("Description")));

                            }
                            if( searchAdapter== null) {
                                searchAdapter = new SearchAdapter( getApplicationContext(),R.layout.activity_search_sign2,searchSignList );
                            slistview.setAdapter( searchAdapter );

                            }else{

                                searchAdapter.notifyDataSetChanged();
                                slistview.setAdapter( searchAdapter );
                            }

                        }
                        catch (Exception e) {
                            Toast.makeText(SearchSign3.this, "Sorry. The word you entered does not exist. \n\n Tusonyiweko. Ekigambo kyowandiise tetukirina.", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        } ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("txtSignName", edsearch.getText().toString());
                edsearch.setText("");
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQue(stringRequest);

    }

}
