package com.example.artyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class displayProfile extends AppCompatActivity {
    TextView textViewUsername, textViewEmail, textViewName;
    Button update, delete;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);

        update = findViewById(R.id.updateProfileBtn);
        delete = findViewById(R.id.deleteAccBtn);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, login.class));
        }


        textViewUsername = findViewById(R.id.textViewUserName);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewName =findViewById(R.id.textViewFullName);

        User user = SharedPrefManager.getInstance(this).getUser();
        username = user.getUsername();

        textViewUsername.setText(user.getUsername());
        textViewEmail.setText(user.getEmail());
        textViewName.setText(user.getName());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(displayProfile.this, UpdateAccount.class));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.0.36/artapp/deleteUser.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {



                                //converting response to json object
                                JSONObject obj = null;
                                try {
                                    obj = new JSONObject(response);
                                    if (!obj.getBoolean("error")) {

                                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();




                                    } else {
                                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                //if no error in response


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("username", username);
                        return params;
                    }
                };

            }
        });




    }
}