package com.example.artyapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class imageAdapter extends RecyclerView.Adapter<imageAdapter.ViewHolder> {
    Context context;
    private ArrayList<imageModel> imageModelArrayList;

    public imageAdapter(Context context, ArrayList<imageModel> courseModelArrayList) {
        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
    }


    @NonNull
    @Override
    public imageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull imageAdapter.ViewHolder holder, int position) {
        imageModel model = imageModelArrayList.get(position);
        holder.imageName.setText(model.getImageName());
        holder.imageDescription.setText("" + model.getImageDescription());
        holder.image.setImageResource(Integer.parseInt(model.getImage()));

    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }


    public class ViewHolder  extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView imageName, imageDescription;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.idIVCourseImage);
            imageName = itemView.findViewById(R.id.idTVCourseName);
            imageDescription = itemView.findViewById(R.id.idTVCourseRating);
        }
    }
}
