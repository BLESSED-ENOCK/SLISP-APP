package com.example.slispapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DeleteSigns extends Fragment {


    TextView b6,b19,  b29 ,b30, b33;
    StringRequest stringRequest2;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_delete_signs,container,false);

        final TableLayout tv=(TableLayout) view.findViewById(R.id.tabledeletesigns);

        TableRow tr0=new TableRow(getActivity());
        tr0.setBackgroundColor( Color.LTGRAY);
        tr0.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        b6=new TextView(getActivity());
        b6.setPadding(0, 20, 5, 20);
        b6.setText("Sign ID");
        b6.setTextColor( Color.BLUE);
        b6.setTextSize(15);
        tr0.addView(b6);


        b19=new TextView(getActivity());
        b19.setPadding(10, 20, 5, 20);
        b19.setTextSize(15);
        b19.setText("English");
        b19.setTextColor(Color.BLUE);
        tr0.addView(b19);

        b29=new TextView(getActivity());
        b29.setPadding(15, 20, 5, 20);
        b29.setText("Luganda");
        b29.setTextColor(Color.BLUE);
        b29.setTextSize(15);
        tr0.addView(b29);

        b30=new TextView(getActivity());
        b30.setPadding(25, 20, 5, 20);
        b30.setText("Sign");
        b30.setTextColor(Color.BLUE);
        b30.setTextSize(15);
        tr0.addView(b30);


        b33=new TextView(getActivity());
        b33.setPadding(50, 0, 5, 0);
        b33.setText("DELETE");
        b33.setTextColor(Color.RED);
        b33.setTextSize(15);
        tr0.addView(b33);

        tv.addView(tr0);

        final View vline = new View(getActivity());
        vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 4));
        vline.setBackgroundColor(Color.BLUE);

        tv.addView(vline);

        stringRequest2 = new StringRequest( Request.Method.POST,"http://"+Data.serverIP+"/SlispApp/DeleteOldSignView.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jArray = new JSONArray(response);

                            for(int i=0;i<jArray.length()-1;i++) {

                                final TableRow tr = new TableRow( getActivity() );

                                tr.setLayoutParams( new TableRow.LayoutParams(
                                        TableRow.LayoutParams.FILL_PARENT,
                                        TableRow.LayoutParams.WRAP_CONTENT ) );
                                tr.setId(0);


                                final JSONObject json_data = jArray.getJSONObject( i );

                                Log.i( "log_tag","Sign Id: " + json_data.getString( "Sign_Id" ) +
                                        ", English Name: " + json_data.getString( "English_Name" ) +
                                        ", Luganda Name: " + json_data.getString( "Luganda_Name" ) +
                                        ", Sign: " + json_data.getString( "Sign" ));


                                TextView b = new TextView( getActivity() );

                                String stime = String.valueOf( json_data.getString( "Sign_Id" ) );
                                b.setPadding( 20,0,5,0 );
                                b.setText( stime );
                                b.setTextColor( Color.RED );
                                b.setTextSize( 15 );
                                tr.addView( b );


                                TextView b1 = new TextView( getActivity() );
                                b1.setPadding( 15,0,5,0 );
                                b1.setTextSize( 15 );
                                String stime1 = json_data.getString( "English_Name" );
                                b1.setText( stime1 );
                                b1.setTextColor( Color.RED );
                                tr.addView( b1 );

                                TextView b2 = new TextView( getActivity() );
                                b2.setPadding( 15,0,5,0 );
                                String stime2 = String.valueOf( json_data.getString( "Luganda_Name" ) );
                                b2.setText( stime2 );
                                b2.setTextColor( Color.RED );
                                b2.setTextSize( 15 );
                                tr.addView( b2 );

                                ImageView img = new ImageView( getActivity() );
                                img.setPadding( 15,0,5,0 );
                                Glide.with(getActivity()).load("http://"+Data.serverIP+"/SlispApp/"+json_data.getString("Sign")).into(img);
                                tr.addView(img);

                                final Button btnDelete =new Button( getActivity());
                                btnDelete.setText("X");
                                btnDelete.setTextColor( Color.BLACK );
                                btnDelete.setTextSize( 15 );
                                btnDelete.setBackgroundColor(Color.RED);
                                btnDelete.setOnClickListener( new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        try {
                                            DelOldsign( json_data.getString( "Sign_Id" ) );
                                            tr.setVisibility( View.INVISIBLE );
                                            Toast.makeText( getActivity(),"Sign deleted successfully",Toast.LENGTH_LONG ).show();
                                            Intent refresh  = new Intent(getActivity(),DeleteSigns.class);
                                            startActivity(refresh );
                                        }
                                        catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                } );
                                tr.addView( btnDelete );

                                tv.addView( tr );

                                final View vline1 = new View(getActivity());
                                vline1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 4));
                                vline1.setBackgroundColor(Color.WHITE);

                                tv.addView(vline1);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestHandler.getInstance(getActivity()).addToRequestQue(stringRequest2 );

        return view;
    }

    public void  DelOldsign(String Sign_ID3){

        final    String Sign_ID4=Sign_ID3;
        StringRequest stringRequest3 = new StringRequest(Request.Method.POST,"http://"+Data.serverIP+"/SlispApp/DeleteOldSign.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Sign_Id", Sign_ID4);

                return params;
            }
        };
        RequestHandler.getInstance(getActivity()).addToRequestQue(stringRequest3 );

    }
}
