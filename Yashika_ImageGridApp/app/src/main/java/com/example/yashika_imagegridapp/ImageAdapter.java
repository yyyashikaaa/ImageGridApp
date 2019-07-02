package com.example.yashika_imagegridapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    Context context;
    ArrayList<CustomClass> list;

    ImageAdapter(Context c, ArrayList<CustomClass> list) {
        context = c;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.image, parent, false);
        }
        CustomClass customClass = (CustomClass) getItem(position);
        ImageView imageView = convertView.findViewById(R.id.imageView);
        imageView.setImageBitmap(customClass.getImage());
        return convertView;
    }
}
