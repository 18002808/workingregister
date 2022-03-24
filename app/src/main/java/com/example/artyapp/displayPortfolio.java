package com.example.artyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

public class displayPortfolio extends AppCompatActivity {
    RecyclerView recyclerViewportfolio;
    //ArrayList<Image> imageList;
    ArrayList<User> userList;
    portfolioAdapter portfolioAdapter;
    //Image modelImage;
    User userModel;
    LinearLayoutManager linearLayoutManager;
    private String currentUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_portfolio);
        User user = SharedPrefManager.getInstance(this).getUser();
        recyclerViewportfolio = findViewById(R.id.recylerviewArtist);
        linearLayoutManager = new LinearLayoutManager(this);
        currentUsername =user.getUsername();
        recyclerViewportfolio.setLayoutManager(new LinearLayoutManager(this));
        userList = new ArrayList<>();
        portfolioAdapter = new portfolioAdapter(this, userList);
        recyclerViewportfolio.setAdapter(portfolioAdapter);
        //System.out.println(currentUsername);




        //getImage(currentUsername);
        getImage();



    }

    public void getImage(){

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.0.36/artapp/getallusers.php",
                new Response.Listener<String>() {
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String succes = jsonObject.getString("success");

                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if(succes.equals("1")){

                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    int id = Integer.parseInt(object.getString("id"));
                                    String name = object.getString("name");
                                    String username = object.getString("username");
                                    String email = object.getString("email");
                                    String password = object.getString("password");

                                   // String image = "http://192.168.0.36/artapp/Images/"+images;

                                    //modelImage = new Image(id,image,imageName,description,username);
                                    userModel = new User(id,username,email,name);
                                    userList.add(userModel);
                                    portfolioAdapter.notifyDataSetChanged();


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
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }
}