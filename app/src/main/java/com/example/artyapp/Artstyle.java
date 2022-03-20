package com.example.artyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

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

public class Artstyle extends AppCompatActivity {
    RecyclerView recyclerView;
    imageAdapter myAdapter;
    ArrayList<Image> imageList;
    Image modelImage;
    LinearLayoutManager linearLayoutManager;
    EditText artStyle;
    Button search;
    private String artstyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artstyle);
        recyclerView = findViewById(R.id.recyclerView2);
        linearLayoutManager = new LinearLayoutManager(this);
        artStyle = findViewById(R.id.artStyleSearch);
        search = findViewById(R.id.searchArtStyle);
        //username = user.getUsername();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        imageList = new ArrayList<>();
        myAdapter = new imageAdapter(this, imageList);
        recyclerView.setAdapter(myAdapter);
        //System.out.println(currentUsername);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                artstyle = artStyle.getText().toString();
                getImage();
            }
        });


        //getImage(currentUsername);
        // getImage();


    }

    public void getImage() {

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.0.36/artapp/getArtstyle.php",
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
                params.put("artstyle", artstyle);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

}