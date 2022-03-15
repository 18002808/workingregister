package com.example.artyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class collectionOfImages extends AppCompatActivity {


    Button addImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_of_images);
        startActivity(new Intent(collectionOfImages.this, newImage.class));
        /*addImage = findViewById(R.id.gotoaddimages);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(collectionOfImages.this, newImage.class));
            }
        });*/
    }
}