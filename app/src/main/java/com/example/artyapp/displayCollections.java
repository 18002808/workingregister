package com.example.artyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class displayCollections extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_collections);
        Button medium =findViewById(R.id.searchByMedium);
        Button artStyle = findViewById(R.id.artstyleBtn);
        Button various = findViewById(R.id.variousBtn);




        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(displayCollections.this, Medium.class));
            }
        });

        artStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(displayCollections.this, Artstyle.class));
            }
        });

        various.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(displayCollections.this, Various.class));
            }
        });

    }
}