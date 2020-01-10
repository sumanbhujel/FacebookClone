package com.example.facebookclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookclone.R;
import com.example.facebookclone.model.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder>{

    List<Post> postList;
    Context c;

    public PostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.show_user, parent, false);
        PostAdapter.MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Post post = postList.get(position);


        holder.textView.setText(post.getStatus());
//        Picasso.with()
//                .load("http://sujitg.com.np/wc/teams/"+
//                        response.body().getFile())
//                .into(imageView);

//        holder.imageView.setImageResource("localhost:7000/images/"+addpost.getImage());


    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image1);
            textView = itemView.findViewById(R.id.tvStatus);
        }
    }


}
