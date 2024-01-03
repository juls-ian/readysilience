package com.example.readysilience;

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

public class AdapterCenters extends PagerAdapter {

    Context context;
    ArrayList<CenterData> centerDataList;



    public AdapterCenters(Context context, ArrayList<CenterData> centerDataList) {
        this.context = context;
        this.centerDataList = centerDataList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if (centerDataList.size() == 0) {
            return new Object(); // Handle empty list to prevent IndexOutOfBoundsException
        }

        int realPosition = position % centerDataList.size();
        CenterData centerData = centerDataList.get(realPosition);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_nearbycenter, null);
        ImageView imageView = view.findViewById(R.id.centerImage);
        TextView textView = view.findViewById(R.id.text1);

        Glide.with(context).asBitmap().load(centerData.getImageUrl()).into(imageView);
        textView.setText(centerData.getText());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public float getPageWidth(int position) {
        return 0.5f;
    }
}
