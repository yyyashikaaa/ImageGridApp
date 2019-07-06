package com.example.yashika_imagegridapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class FinalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Bitmap bitmap = (Bitmap) bundle.get("ImageBitmap");

        ImageView imageView = findViewById(R.id.selectedImage);
        imageView.setImageBitmap(bitmap);

    }
}