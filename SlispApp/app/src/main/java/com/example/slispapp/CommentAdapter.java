package com.example.slispapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{
private Context context;
public ArrayList<Comment2> comments;


public CommentAdapter(Context context,ArrayList<Comment2> comments){
        this.context = context;
        this.comments = comments;
        }

@Override
public CommentViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext())
        .inflate(R.layout.commentlayout, parent, false);

        CommentViewHolder commentViewHolder = new CommentViewHolder(view);
        return commentViewHolder;
        }

@Override
public void onBindViewHolder(CommentViewHolder holder,int position) {
        Comment2 comment = comments.get(position);
        holder.tvName.setText(comment.Username);
        holder.tvPost.setText(comment.Content);
        holder.tvDate.setText(comment.CommentTime);


        }

@Override
public int getItemCount() {
        if(comments != null){
        return comments.size();
        }
        return 0;
        }

//ViewHolder class
public static class CommentViewHolder extends RecyclerView.ViewHolder{

    public CardView cvPost;
    public TextView tvDate;
    public TextView tvName;
    public TextView tvPost;
    public ImageView img;
    //=  ItemClickListener2 itemClickListener2;

    public CommentViewHolder(View itemView) {
        super(itemView);
        cvPost = (CardView)itemView.findViewById(R.id.cv);
        tvDate = (TextView)itemView.findViewById(R.id.Date2);
        tvName = (TextView)itemView.findViewById(R.id.user_name3);
        tvPost = (TextView)itemView.findViewById(R.id.comment1);

    }
}


    }