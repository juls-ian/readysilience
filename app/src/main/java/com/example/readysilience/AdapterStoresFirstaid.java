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

public class AdapterStoresFirstaid extends PagerAdapter {

    Context context;
    ArrayList<DataStoresFirstaid> storesFirstaidsList;



    public AdapterStoresFirstaid(Context context, ArrayList<DataStoresFirstaid> storesFirstaidsList) {
        this.context = context;
        this.storesFirstaidsList = storesFirstaidsList;
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
        if (storesFirstaidsList.size() == 0) {
            return new Object();
        }

        int realPosition = position % storesFirstaidsList.size();
        DataStoresFirstaid storesFirstaid = storesFirstaidsList.get(realPosition);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_stores_firstaid, null);
        ImageView imageView = view.findViewById(R.id.store_image);
        TextView textView = view.findViewById(R.id.store_name);



        Glide.with(context).asBitmap().load(storesFirstaid.getStorePic()).into(imageView);
        textView.setText(storesFirstaid.getStoreName());

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

