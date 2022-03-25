package com.example.artyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

public class ArtistPortfolio extends AppCompatActivity {
    String username, name;
    private TextView userName, fullName;
    RecyclerView recyclerView;
    imageAdapter myAdapter;
    ArrayList<Image> imageList;
    Image modelImage;
    LinearLayoutManager linearLayoutManager;
    TextView artistName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_portfolio);
        recyclerView = findViewById(R.id.portfolioRecycler);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        imageList = new ArrayList<>();
        myAdapter = new imageAdapter(this, imageList);
        recyclerView.setAdapter(myAdapter);
        artistName = findViewById(R.id.artistName);

        username = getIntent().getExtras().getString("username");
        name = getIntent().getExtras().getString("name");
        artistName.setText(username);
        getImage();





    }

    public void getImage() {

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.0.36/artapp/getPortfolios.php",
                new Response.Listener<String>() {
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String succes = jsonObject.getString("success");

                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (succes.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    int id = Integer.parseInt(object.getString("id"));
                                    String images = object.getString("image");
                                    String imageName = object.getString("imageName");
                                    String description = object.getString("description");
                                    String username = object.getString("username");

                                    String image = "http://192.168.0.36/artapp/Images/" + images;

                                    modelImage = new Image(id, image, imageName, description, username);
                                    imageList.add(modelImage);
                                    myAdapter.notifyDataSetChanged();

                                }


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Toast.makeText(addImage.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })

        {
            protected Map<String, String> getParams () throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


}