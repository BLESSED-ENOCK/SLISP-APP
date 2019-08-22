package com.example.slispapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.CardView;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.util.MalformedJsonException;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.AuthFailureError;
        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonObjectRequest;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;
        import com.google.gson.JsonArray;
        import com.google.gson.JsonObject;
        import com.kosalgeek.android.json.JsonConverter;

        import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
        import org.apache.http.HttpResponse;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.util.EntityUtils;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;

public class PostDetail extends AppCompatActivity {

    public static TextView poster, date, postDetail;

    EditText comment;
    ImageButton btncomment;
    RecyclerView recyclerView;
    final String TAG = "PostDetail";

    StringRequest stringRequest6;
    ArrayList<Comment2> comments;

    CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        getSupportActionBar().setTitle("SLISP: Make Comments");
        setContentView( R.layout.popup_add_post );
        poster = findViewById( R.id.user_name );
        date = findViewById( R.id.Date);
        postDetail = findViewById( R.id.PostDetail3 );



        poster.setText( PostCallback.Postuser );
        date.setText( PostCallback.PostTime );
        postDetail.setText( PostCallback.Postmessage );

      // updateComment();

        comment=findViewById( R.id.post );

        btncomment = findViewById( R.id.send );

        btncomment.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comment.getText().length()<=0){
                    Toast.makeText( getApplicationContext(),"Please Make a Comment. \n\nBaako kyo wandiika.",Toast.LENGTH_LONG ).show();
                }
                else {
                    uploadPost();
                    Toast.makeText( PostDetail.this,"Commented successfully",Toast.LENGTH_LONG ).show();
                    comment.setText( "" );

            }
            }
        } );

        recyclerView = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);


        recyclerView.setHasFixedSize(true);

        String url = "http://"+Data.serverIP+"/SlispApp/SelectComment.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);

                        ArrayList<Comment2> commentList = new JsonConverter<Comment2>()
                                .toArrayList(response, Comment2.class);

                        CommentAdapter adapter = new CommentAdapter(getApplicationContext(), commentList);

                        recyclerView.setAdapter(adapter);


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null){
                            Log.d(TAG, error.toString());
                            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();

                params.put("Post_Id",  PostCallback.PostId);
                params.put("User_id",  com.example.slispapp.Callback.UserId);

                return params;

            }
        };

        ;

        RequestHandler.getInstance(getApplicationContext()).addToRequestQue(stringRequest);

    }

 /*   public  void getPostchat(){

        String url = "http://"+Data.serverIP+"/SlispApp/SelectComment.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);

                        ArrayList<Comment2> commentList = new JsonConverter<Comment2>()
                                .toArrayList(response, Comment2.class);

                        if(commentAdapter==null){
                            commentAdapter = new CommentAdapter(getApplicationContext(),commentList );
                        }else{
                            commentAdapter.comments =  commentList;
                        }

                        recyclerView.setAdapter(commentAdapter);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null){
                            Log.d(TAG, error.toString());
                            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
        RequestHandler.getInstance(PostDetail.this).addToRequestQue(stringRequest);

    }

    public  void updateComment(){
       ScheduledThreadPoolExecutor e = new ScheduledThreadPoolExecutor(1);
        e.scheduleAtFixedRate( new Runnable() {
            @Override
            public void run() {
                getPostchat();
            }
        }
        ,5000,10000,TimeUnit.MILLISECONDS);

    } */

    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory( GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Comment>> call = api.getStatus();

        call.enqueue( new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call,retrofit2.Response<List<Comment>> response) {
                List<Comment> adslist = response.body();

                String username = adslist.get(0).getUsername();
                String commentTime = adslist.get(0).getCommentTime();
                String post = adslist.get(0).getContent();

                poster.setText(username);
                date.setText(commentTime);
                postDetail.setText(post);

            }

            @Override
            public void onFailure(Call<List<Comment>> call,Throwable t) {

                Toast.makeText(PostDetail.this, ""+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        } );
    }

    private void uploadPost(){

        stringRequest6 = new StringRequest( Request.Method.POST,"http://"+Data.serverIP+"/SlispApp/CommentApi.php",
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
                params.put("Content", comment.getText().toString().trim());
                params.put("User_id",  com.example.slispapp.Callback.UserId);
                params.put("Post_Id",  com.example.slispapp.PostCallback.PostId);

                return params;

            }
        };


        RequestHandler.getInstance(getApplicationContext()).addToRequestQue(stringRequest6 );
    }
    
}



