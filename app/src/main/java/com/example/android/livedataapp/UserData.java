package com.example.android.livedataapp;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class UserData {
    public String id, first_name, last_name, avatar;
    public boolean progress;

    @BindingAdapter({"android:avatar"})
    public void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
}
