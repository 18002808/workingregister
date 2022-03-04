package com.example.artyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class nonArtistBoard extends AppCompatActivity {
    TextView textViewName;

    CardView portfolio, viewCollections, profile, viewCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_artist_board);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, login.class));
        }

        textViewName = findViewById(R.id.textViewName);
        profile = findViewById(R.id.profile);
        viewCollections = findViewById(R.id.collectionsCard);
        portfolio = findViewById(R.id.artistportfolios);
        viewCart = findViewById(R.id.cartCard);
        User user = SharedPrefManager.getInstance(this).getUser();
        textViewName.setText(user.getName());

        findViewById(R.id.logoutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }
        });

        viewCollections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(nonArtistBoard.this, displayCollections.class));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(nonArtistBoard.this, displayProfile.class));
            }
        });

        portfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(nonArtistBoard.this, displayPortfolio.class));
            }
        });

        viewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(nonArtistBoard.this, displayCart.class));
            }
        });
    }
}