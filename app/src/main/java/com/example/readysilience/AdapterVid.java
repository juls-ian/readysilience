package com.example.readysilience;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.bumptech.glide.Glide;
import com.example.readysilience.Video;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class AdapterVid extends PagerAdapter {
    private final Context context;
    private final ArrayList<Video> videoList;

    public AdapterVid(Context context, ArrayList<Video> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    @Override
    public int getCount() {
        return videoList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_list_item, container, false);

        ImageView imageView = view.findViewById(R.id.list_item_image);
        Glide.with(context).load(videoList.get(position).getUrl()).into(imageView);

        imageView.setOnClickListener(v -> {
            // Start VideoActivity when the image is clicked
            Intent intent = new Intent(context, VideoActivity.class);
            intent.putExtra("videoUrl", videoList.get(position).getUrl());
            context.startActivity(intent);
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
