package com.example.artyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class artistDashboard extends AppCompatActivity {
    TextView textViewName;
    CardView images, collections, profile, myArt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, login.class));
        }

        textViewName = findViewById(R.id.textViewName);
        images = findViewById(R.id.images);
        collections = findViewById(R.id.collections);
        profile = findViewById(R.id.profile);
        myArt = findViewById(R.id.myart);
        User user = SharedPrefManager.getInstance(this).getUser();
        textViewName.setText(user.getName());

        findViewById(R.id.logoutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }
        });

        images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(artistDashboard.this, addImage.class));
            }
        });

        collections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(artistDashboard.this, collectionOfImages.class));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(artistDashboard.this, displayProfile.class));
            }
        });
        myArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(artistDashboard.this, showArtistPortfolio.class));
            }
        });
    }
}