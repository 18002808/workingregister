package com.example.artyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        // hide the default actionbar
        getSupportActionBar().hide();

        // Recieve data

        String name  = getIntent().getExtras().getString("imageName");
        String description = getIntent().getExtras().getString("imageDescription");
        String username = getIntent().getExtras().getString("username") ;
        String image_url = getIntent().getExtras().getString("anime_img") ;


        // ini views

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_name = findViewById(R.id.imageName);
        TextView tv_description = findViewById(R.id.imageDescription);
        ImageView img = findViewById(R.id.artImage);

        // setting values to each view

        tv_name.setText(name);
        tv_description.setText(description);


        collapsingToolbarLayout.setTitle(name);


        RequestOptions requestOptions = new RequestOptions().centerCrop();


        // set image using Glide
        Glide.with(this).load(image_url).apply(requestOptions).into(img);
    }
}