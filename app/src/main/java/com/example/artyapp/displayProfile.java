package com.example.artyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class displayProfile extends AppCompatActivity {
    TextView textViewUsername, textViewEmail, textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, login.class));
        }


        textViewUsername = findViewById(R.id.textViewUserName);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewName =findViewById(R.id.textViewFullName);

        User user = SharedPrefManager.getInstance(this).getUser();

        textViewUsername.setText(user.getUsername());
        textViewEmail.setText(user.getEmail());
        textViewName.setText(user.getName());




    }
}