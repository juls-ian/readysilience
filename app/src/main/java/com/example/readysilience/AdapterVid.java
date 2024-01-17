package com.example.readysilience;

import static com.example.readysilience.R.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterVid extends PagerAdapter {

    Context context;
    ArrayList<DataFirstAidVid> videosList;



    public AdapterVid(Context context, ArrayList<DataFirstAidVid> videosList) {
        this.context = context;
        this.videosList = videosList;
    }

    @Override
    public int getCount() {
        return videosList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if (videosList.size() == 0) {
            return new Object(); // Handle empty list to prevent IndexOutOfBoundsException
        }

        int realPosition = position % videosList.size();
        DataFirstAidVid dataFirstAidVid = videosList.get(realPosition);

        View view = LayoutInflater.from(context).inflate(layout.layout_firstaid_video, null);
        ImageView imageView = view.findViewById(id.thumbnail);
        TextView textView = view.findViewById(id.vid_title);
        TextView textView1 = view.findViewById(id.vid_duration);
        CircleImageView circleImageView = view.findViewById(R.id.creator_logo);


        Glide.with(context).asBitmap().load(dataFirstAidVid.getThumbnail()).into(imageView);
        Glide.with(context).asBitmap().load(dataFirstAidVid.getCreatorLogo()).into(circleImageView);
        textView.setText(dataFirstAidVid.getVidTitle());
        textView1.setText(dataFirstAidVid.getVidDuration());


        container.addView(view, 0);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public float getPageWidth(int position) {
        return 0.8f;
    }
}
