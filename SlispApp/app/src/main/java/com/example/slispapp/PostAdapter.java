package com.example.slispapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{


    private Context context;
    public ArrayList<Post> posts;

    public PostAdapter(Context context,ArrayList<Post> posts){
        this.context = context;
        this.posts = posts;

    }

    @Override
    public PostViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext())
                .inflate(R.layout.post_layout, parent, false);

        PostViewHolder postViewHolder = new PostViewHolder(view);
        return postViewHolder;
    }


    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {

        final Post post = posts.get(position);
        holder.tvName.setText(post.Username);
        holder.tvPost.setText(post.Content);
        holder.tvDate.setText(post.PostTime);

        holder.setItemClickListener( new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {

            }
        } );



        holder.img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PostCallback.PostId=post.Post_Id;
                PostCallback.Postuser=post.Username;
                PostCallback.Postmessage=post.Content;
                PostCallback.PostTime=post.PostTime;

                Intent in = new Intent( context.getApplicationContext(),PostDetail.class);
                context.startActivity(in);


            }
        } );


    }

    @Override
    public int getItemCount() {
        if(posts != null){
            return posts.size();
        }
        return 0;
    }

    //ViewHolder class
    public static class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public CardView cvPost;
        public TextView tvDate;
        public TextView tvName;
        public TextView tvPost;
        public ImageView img;
       ItemClickListener itemClickListener;

        public PostViewHolder(View itemView) {
            super(itemView);
            cvPost = (CardView)itemView.findViewById(R.id.cardPost);
            tvDate = (TextView)itemView.findViewById(R.id.Date2);
            tvName = (TextView)itemView.findViewById(R.id.user_name2);
            tvPost = (TextView)itemView.findViewById(R.id.PostDetail2);
           img = (ImageView)itemView.findViewById( R.id.comment_image);

        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener= itemClickListener;
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick( getLayoutPosition() );
        }
    }

    public interface ItemClickListener{
        void  onItemClick(int pos);
    }


}
