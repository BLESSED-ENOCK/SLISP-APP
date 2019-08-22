package com.example.slispapp;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.baoyz.widget.PullRefreshLayout;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ViewUsers extends Fragment {


   TextView b6,b19,  b29 ,b30, b31;

    StringRequest stringRequest2;
        @Override
        public View onCreateView(LayoutInflater inflater,ViewGroup container,
            Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.activity_view_users,container,false);

          final   TableLayout tv=(TableLayout) view.findViewById(R.id.table);

            TableRow tr0=new TableRow(getActivity());
            tr0.setBackgroundColor(Color.LTGRAY);
            tr0.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 20));
            tr0.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            b6=new TextView(getActivity());
            b6.setPadding(0, 20, 5, 20);
            b6.setText("User ID");
            b6.setTextColor( Color.BLUE);
            b6.setTextSize(15);
            tr0.addView(b6);

            b19=new TextView(getActivity());
            b19.setPadding(10, 20, 5, 20);
            b19.setTextSize(15);
            b19.setText("Username");
            b19.setTextColor(Color.BLUE);
            tr0.addView(b19);

            b29=new TextView(getActivity());
            b29.setPadding(15, 20, 5, 20);
            b29.setText("Phone Number");
            b29.setTextColor(Color.BLUE);
            b29.setTextSize(15);
            tr0.addView(b29);

            b30=new TextView(getActivity());
            b30.setPadding(25, 20, 5, 20);
            b30.setText("Role");
            b30.setTextColor(Color.BLUE);
            b30.setTextSize(15);
            tr0.addView(b30);

            b31=new TextView(getActivity());
            b31.setPadding(50, 20, 5, 20);
            b31.setText("DELETE");
            b31.setTextColor(Color.RED);
            b31.setTextSize(15);
            tr0.addView(b31);

            tv.addView(tr0);

            final View vline = new View(getActivity());
            vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 4));
            vline.setBackgroundColor(Color.BLUE);

            tv.addView(vline);

            stringRequest2 = new StringRequest( Request.Method.POST,"http://"+Data.serverIP+"/SlispApp/ViewUsers.php",
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

                                final    JSONObject json_data = jArray.getJSONObject( i );

                                    TextView b = new TextView( getActivity() );

                                    String stime = String.valueOf( json_data.getString( "User_id" ) );
                                    b.setPadding( 20,0,5,0 );
                                    b.setText( stime );
                                    b.setTextColor( Color.RED );
                                    b.setTextSize( 15 );
                                    tr.addView( b );

                                    TextView b1 = new TextView( getActivity() );
                                    b1.setPadding( 15,0,5,0 );
                                    b1.setTextSize( 15 );
                                    String stime1 = json_data.getString( "Username" );
                                    b1.setText( stime1 );
                                    b1.setTextColor( Color.RED );
                                    tr.addView( b1 );

                                    TextView b2 = new TextView( getActivity() );
                                    b2.setPadding( 15,0,5,0 );
                                    String stime2 = String.valueOf( json_data.getString( "Phone_number" ) );
                                    b2.setText( stime2 );
                                    b2.setTextColor( Color.RED );
                                    b2.setTextSize( 15 );
                                    tr.addView( b2 );

                                    TextView b3 = new TextView( getActivity() );
                                    b3.setPadding( 15,0,5,0 );
                                    String stime3 = String.valueOf( json_data.getString( "Role" ) );
                                    b3.setText( stime3 );
                                    b3.setTextColor( Color.RED );
                                    b3.setTextSize( 15 );
                                    tr.addView( b3 );

                                    final Button btn1 =new Button( getActivity());
                                    btn1.setText("X");

                                    btn1.setTextColor( Color.BLACK );
                                    btn1.setTextSize( 15 );
                                    btn1.setBackgroundColor(Color.RED);
                                    btn1.setOnClickListener( new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            try {
                                                deluser( json_data.getString( "User_id" ) );
                                                tr.setVisibility( View.INVISIBLE );
                                                Toast.makeText( getActivity(),"User deleted successfully",Toast.LENGTH_LONG ).show();

                                            }
                                            catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    } );
                                    tr.addView( btn1 );

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
            })

            ;

    RequestHandler.getInstance(getActivity()).addToRequestQue(stringRequest2 );

                return view;
    }

    public void  deluser(String User_ID){
     //Toast.makeText( getActivity(),ID,Toast.LENGTH_LONG ).show();
     final    String User_ID2=User_ID;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://"+Data.serverIP+"/SlispApp/DeleteUser.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        RequestHandler.getInstance(getActivity()).addToRequestQue(stringRequest2 );

                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("User_id", User_ID2);

                return params;
            }
        };
        RequestHandler.getInstance(getActivity()).addToRequestQue(stringRequest );
    }

}
