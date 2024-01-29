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

public class AdapterStoresSupplies extends PagerAdapter {

    Context context;
    ArrayList<DataStoresSupplies> storeSuppliesList;



    public AdapterStoresSupplies(Context context, ArrayList<DataStoresSupplies> storeSuppliesList) {
        this.context = context;
        this.storeSuppliesList = storeSuppliesList;
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
        if (storeSuppliesList.size() == 0) {
            return new Object();
        }

        int realPosition = position % storeSuppliesList.size();
        DataStoresSupplies storesSupplies = storeSuppliesList.get(realPosition);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_stores_supplies, null);
        ImageView imageView = view.findViewById(R.id.store_image);
        TextView textView = view.findViewById(R.id.store_name);



        Glide.with(context).asBitmap().load(storesSupplies.getStorePic()).into(imageView);
        textView.setText(storesSupplies.getStoreName());

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

