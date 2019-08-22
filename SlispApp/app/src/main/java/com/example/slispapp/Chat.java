package com.example.slispapp;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.kosalgeek.android.json.JsonConverter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Chat extends Fragment {

    public  PostAdapter adapter;
    EditText post;
    ImageButton btnPost;

    StringRequest stringRequest5;

    ArrayList<Post>posts;

    private Random random;

    RecyclerView recyclerView;


    final String TAG = "Chat";
    PostAdapter adapterchat;

    @Override
    public View onCreateView(final LayoutInflater inflater,final ViewGroup container,final Bundle savedInstanceState) {

        final View view = inflater.inflate( R.layout.activity_chat,container,false );

        post= view.findViewById( R.id. post);
        btnPost =view.findViewById( R.id.send );


        recyclerView = (RecyclerView) view.findViewById(R.id.PostData);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


      recyclerView.setHasFixedSize(true);

        String url = "http://"+Data.serverIP+"/SlispApp/SelectPost.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);

                        ArrayList<Post> postList = new JsonConverter<Post>()
                                .toArrayList(response, Post.class);


                        PostAdapter adapter = new PostAdapter(getContext(), postList);

                       recyclerView.setAdapter(adapter);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null){
                            Log.d(TAG, error.toString());
                            Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
        RequestHandler.getInstance(getActivity()).addToRequestQue(stringRequest);






        btnPost.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(post.getText().length()<=0){
                    Toast.makeText( getActivity(),"Please Make a post. \n\nBaako kyo wandiika.",Toast.LENGTH_LONG ).show();
                }
                else {
                    uploadPost();
                    Toast.makeText( getActivity(),"Posted successfully",Toast.LENGTH_LONG ).show();
                    post.setText( "" );
                    updatechat();
                }

            }

        } );


        random = new Random();


        return view;
    }

    public  void getchat(){

        String url = "http://"+Data.serverIP+"/SlispApp/SelectPost.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);

                       ArrayList<Post> postList = new JsonConverter<Post>()
                                .toArrayList(response, Post.class);

            if(adapterchat==null){
            adapterchat = new PostAdapter( getContext(),postList );
        }else{
            adapterchat.posts =  postList;
        }

                        recyclerView.setAdapter(adapterchat);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null){
                            Log.d(TAG, error.toString());
                            Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
        RequestHandler.getInstance(getActivity()).addToRequestQue(stringRequest);

    }


    public  void updatechat(){
        ScheduledThreadPoolExecutor exc = new ScheduledThreadPoolExecutor(1);
        exc.scheduleAtFixedRate( new Runnable() {
            @Override
            public void run() {
                getchat();
            }
        } ,5000,10000,TimeUnit.MILLISECONDS);


    }


    private void uploadPost(){

        stringRequest5 = new StringRequest( Request.Method.POST,"http://"+Data.serverIP+"/SlispApp/PostApi.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Content", post.getText().toString().trim());
                params.put("User_id",  Callback.UserId);
                return params;

            }
        };


        RequestHandler.getInstance(getActivity()).addToRequestQue(stringRequest5 );
    }


}

