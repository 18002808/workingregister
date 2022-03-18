package com.example.artyapp;

import android.content.Context;
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
    private ArrayList<Image> imageArrayList;

    public portfolioAdapter(Context context, ArrayList<Image> imageArrayList) {
        this.context = context;
        this.imageArrayList = imageArrayList;
    }



    @Override
    public imageViewPortfolioHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //LayoutInflater inflater = LayoutInflater.from(context);
        //View view=inflater.inflate(R.layout.images_list,null);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.portfolio_layout,parent,false);
        return new imageViewPortfolioHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull imageViewPortfolioHolder holder, int position) {
        Image image  = imageArrayList.get(position);
        Glide.with(context)
                .load(image.getImage())
                .into(holder.imageView);
        holder.imageName.setText(image.getImageName());
        holder.imageDescription.setText( image.getImageDescription());


    }

    @Override
    public int getItemCount() {
        return imageArrayList.size();
    }

}

class imageViewPortfolioHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;
    //private ImageView image;
    public TextView imageName, imageDescription;



    public imageViewPortfolioHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.addImageIcon);
        imageName = itemView.findViewById(R.id.textName);
        imageDescription = itemView.findViewById(R.id.textViewDescription);
    }
}
