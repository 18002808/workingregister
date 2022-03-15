package com.example.artyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class addImage extends AppCompatActivity {
    RecyclerView recyclerView;
    imageAdapter myAdapter;
    ArrayList<Image> imageList;
    Image modelImage;
    LinearLayoutManager linearLayoutManager;
    private String currentUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addimage);
        User user = SharedPrefManager.getInstance(this).getUser();
        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        currentUsername =user.getUsername();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        imageList = new ArrayList<>();
        myAdapter = new imageAdapter(this,imageList);
        recyclerView.setAdapter(myAdapter);
        //System.out.println(currentUsername);




        //getImage(currentUsername);
        getImage();



    }

    public void getImage(){

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.0.36/artapp/fetchallImages.php",
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
                                    String images = object.getString("image");
                                    String imageName = object.getString("imageName");
                                    String description = object.getString("description");
                                    String username = object.getString("username");

                                    String image = "http://192.168.0.36/artapp/Images/"+images;

                                    modelImage = new Image(id,image,imageName,description,username);
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
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

   /* public void getImage(String currentUsername){

        String url = "http://192.168.0.36/artapp/serverdoc/getimagesforartist.php";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(addImage.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // on below line passing our response to json object.
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    // on below line we are checking if the response is null or not.
                    //if (jsonObject.getString("image") == null) {
                    // displaying a toast message if we get error
                    //Toast.makeText(addImage.this, "Please enter valid id.", Toast.LENGTH_SHORT).show();
                    //} else {
                    // if we get the data then we are setting it in our text views in below line.
                    //nameOfPic.setText(jsonObject.getString("name"));
                    //textView.setText(jsonObject.getString("image"));
                    //String encodedImage = jsonObject.getString("image");
                    //System.out.println(encodedImage);
                    //decodeImage(encodedImage);

                    //byte[] bytes= Base64.decode(encodedImage,Base64.DEFAULT);
                    // Initialize bitmap
                    //Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    // set bitmap on imageView
                    //imageView.setImageBitmap(bitmap);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject image = jsonObject.getJSONObject(int i);

                        //getting product object from json array
                        //JSONObject image = jsonObject.getJSONObject(String.valueOf(i));

                        //adding the product to product list
                        imageList.add(new Image(
                                image.getInt("id"),
                                image.getString("image"),
                                image.getString("imageName"),
                                image.getString("imageDescription"),
                                image.getString("username")

                        ));
                    }

                    //creating adapter object and setting it to recyclerview
                    imageAdapter adapter = new imageAdapter(addImage.this, imageList);
                    recyclerView.setAdapter(adapter);


                    // }
                    // on below line we are displaying
                    // a success toast message.
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
            },
                new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // method to handle errors.
                }



        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }


            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our key and value pair to our parameters.
                params.put("username", currentUsername);

                // at last we are returning our params.
                return params;
            }


        };*/
           // queue.add(request);
        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }





}