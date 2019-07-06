package com.example.yashika_imagegridapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1001;
    Button btn;
    GridView gridView;
    Bundle bundle;
    ArrayList<CustomClass> list;
    private static final int My_PERMISSION_CODE = 188;
    ImageAdapter imageAdapter;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rl = findViewById(R.id.relativeLayout);
        btn = findViewById(R.id.capture_btn);
        gridView = findViewById(R.id.gridviewimg);
        list = new ArrayList<CustomClass>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, My_PERMISSION_CODE);
                    setImage();
                } else {
                    openCamera();
                    setImage();
                    TextView tvd = new TextView(getApplicationContext());
                    tvd.setText(getDateTime());
                    rl.addView(tvd);
                }
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), FinalActivity.class);
                intent.putExtra("ImageID", list.get(position).getBitmap());
                startActivity(intent);
            }
        });
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private void setImage() {
        imageAdapter = new ImageAdapter(this, list);
        gridView.setAdapter(imageAdapter);
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == My_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_SHORT).show();
                openCamera();
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            bundle=data.getExtras();
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            list.add(new CustomClass(photo));
            imageAdapter.notifyDataSetChanged();
        }
    }
}




