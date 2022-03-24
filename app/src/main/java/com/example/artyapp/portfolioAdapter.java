package com.example.artyapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class portfolioAdapter extends RecyclerView.Adapter<imageViewPortfolioHolder>{

    private Context context;
    //private ArrayList<Image> imageArrayList;
    private ArrayList<User> userArrayList;

    public portfolioAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        //this.imageArrayList = imageArrayList;
        this.userArrayList = userArrayList;
    }



    @Override
    public imageViewPortfolioHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //LayoutInflater inflater = LayoutInflater.from(context);
        //View view=inflater.inflate(R.layout.images_list,null);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_layout,parent,false);
        return new imageViewPortfolioHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull imageViewPortfolioHolder holder, @SuppressLint("RecyclerView") int position) {
        //Image image  = imageArrayList.get(position);
        User user = userArrayList.get(position);
        holder.fullname.setText(user.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,ArtistPortfolio.class);
                i.putExtra("name", userArrayList.get(position).getName());
                i.putExtra("username",userArrayList.get(position).getUsername());
                context.startActivity(i);
            }
        });
       // Glide.with(context)
        //        .load(image.getImage())
        //        .into(holder.imageView);
       // holder.imageName.setText(image.getImageName());
       // holder.imageDescription.setText( image.getImageDescription());


    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

}

class imageViewPortfolioHolder extends RecyclerView.ViewHolder{
    //public ImageView imageView;
    //private ImageView image;
    //public TextView imageName, imageDescription;
    public TextView fullname;



    public imageViewPortfolioHolder(@NonNull View itemView) {
        super(itemView);
        fullname = itemView.findViewById(R.id.artitstFullName);
        //imageView = itemView.findViewById(R.id.addImageIcon);
        //imageName = itemView.findViewById(R.id.textName);
        //imageDescription = itemView.findViewById(R.id.textViewDescription);
    }
}
