package com.example.artyapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class newImage extends AppCompatActivity {

    Button btnUploadingImage;
    ImageView imageView;
    String sImage;
    Bitmap bitmap;
    String encodedImage;
    private EditText nameOfPic, descriptionOfImage;
    private String imageName, imageDescription ,currentUsername;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_image);

        btnUploadingImage = findViewById(R.id.uploadImagebtn);
        imageView = findViewById(R.id.addImageIcon);
        nameOfPic = findViewById(R.id.imagename);
        descriptionOfImage = findViewById(R.id.imageDescription);
        User user = SharedPrefManager.getInstance(this).getUser();
        currentUsername = user.getUsername();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(newImage.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {


                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);


                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {

                            }


                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });


        btnUploadingImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                imageName = nameOfPic.getText().toString();
                imageDescription = descriptionOfImage.getText().toString();


                StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.0.36/artapp/uploadingnew.php"
                        , new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(newImage.this, response, Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(newImage.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("image", encodedImage);
                        params.put("imageName", imageName);
                        params.put("description", imageDescription);
                        params.put("username", currentUsername);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(newImage.this);
                requestQueue.add(request);

            }

        });
    }

        @Override

        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

            if(requestCode == 1 && resultCode == RESULT_OK && data!=null){

                Uri filePath = data.getData();

                try {
                    InputStream inputStream = getContentResolver().openInputStream(filePath);
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    imageView.setImageBitmap(bitmap);

                    imageStore(bitmap);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


            }

            super.onActivityResult(requestCode, resultCode, data);

        }

        private void imageStore(Bitmap bitmap) {

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

            byte[] imageBytes = stream.toByteArray();

            encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);


        }


    }




