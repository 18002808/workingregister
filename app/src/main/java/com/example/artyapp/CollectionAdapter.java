package com.example.artyapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.artyapp.ImageActivity;
import com.example.artyapp.Image;

import java.util.ArrayList;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder>{
    private ArrayList<String> imageArrayList;
    private Context context;
    RequestOptions option;


    public CollectionAdapter(ArrayList<String> imageArrayList, Context context){
        this.context = context;
        this.imageArrayList = imageArrayList;
        option = new RequestOptions().centerCrop();

    }


    @NonNull
    @Override
    public CollectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ImageActivity.class);
                /*
                i.putExtra("imageName",imageArrayList.get(viewHolder.getAdapterPosition().getImageName());
                i.putExtra("imageDescription",imageArrayList.get(viewHolder.getAdapterPosition().getImageDescription());
                //i.putExtra("imageName",imageArrayList.get(viewHolder.getAdapterPosition().getImageName());

                 */
                context.startActivity(i);
            }
        });





        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        /*holder.tv_name.setText(imageArrayList.get(position).getImageName());
        holder.tv_rating.setText(imageArrayList.get(position).getRating());
        holder.tv_studio.setText(imageArrayList.get(position).getDescription());
        holder.tv_category.setText(imageArrayList.get(position).getCategorie());

        Glide.with(context).load(imageArrayList.get(position).getImage_url()).apply(option).into(holder.img_thumbnail);
        Image image  = imageArrayList.get(position);
        Glide.with(context).load(imageArrayList.get(position)).into(holder.imageViewCollection);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,displayImage.class);
                i.putExtra("image",imageArrayList.get(position));
                context.startActivity(i);

            }
        });*/

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name ;
        TextView tv_rating ;
        TextView tv_studio ;
        TextView tv_category;
        ImageView img_thumbnail;
        LinearLayout view_container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            /*
            view_container = itemView.findViewById(R.id.container);
            tv_name = itemView.findViewById(R.id.anime_name);
            tv_category = itemView.findViewById(R.id.categorie);
            tv_rating = itemView.findViewById(R.id.rating);
            tv_studio = itemView.findViewById(R.id.studio);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
            imageCardView = itemView.findViewById(R.id.imageCardView);
            imageViewCollection = itemView.findViewById(R.id.imageViewforCollection);

             */
        }


    }

}
