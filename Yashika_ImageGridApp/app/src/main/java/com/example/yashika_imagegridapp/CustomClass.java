package com.example.yashika_imagegridapp;

import android.graphics.Bitmap;

public class CustomClass {
    Bitmap image;

    public CustomClass(Bitmap image)
    {
        this.image=image;
    }
    public Bitmap getImage()
    {
        return image;
    }
}
