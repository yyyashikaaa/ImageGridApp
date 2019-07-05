package com.example.yashika_imagegridapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
public class FinalActivity extends AppCompatActivity {
    ImageView selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        selectedImage = (ImageView) findViewById(R.id.selectedImage);
        ImageView image = (ImageView) selectedImage;
        Intent intent = getIntent();
        selectedImage.setImageResource(intent.getIntExtra("image", 0));
    }
}