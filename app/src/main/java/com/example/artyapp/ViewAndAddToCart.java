package com.example.artyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.HashMap;
import java.util.Map;

public class ViewAndAddToCart extends AppCompatActivity {
    private ImageView imageView;
    String image,imagename,imageId;
    private TextView imageNames, imageDescriptions;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_and_add_to_cart);
        User user = SharedPrefManager.getInstance(this).getUser();
        username = user.getUsername();
        Button button = findViewById(R.id.addToCartBtn);
        image = getIntent().getExtras().getString("image");
        imageId = getIntent().getExtras().getString("imageId");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.0.36/artapp/addtocart.php"
                        , new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(ViewAndAddToCart.this, response, Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewAndAddToCart.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("image", image);
                        params.put("imageId",imageId);
                        params.put("username", username);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(ViewAndAddToCart.this);
                requestQueue.add(request);

            }
        });


        imageView = findViewById(R.id.image_view);

        Glide.with(this).asBitmap().load(image).into(imageView);
    }
}