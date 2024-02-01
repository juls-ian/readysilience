package com.example.readysilience;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the corresponding DataEvacCenters object
                int realPosition = position % storeSuppliesList.size();
                DataStoresSupplies dataStoresSupplies = storeSuppliesList.get(realPosition);

                // Open Google Maps with the address
                openGoogleMaps(dataStoresSupplies.getStoreName(), dataStoresSupplies.getLatitude(), dataStoresSupplies.getLongitude());
            }
        });

        container.addView(view, 0);
        return view;
    }

    private void openGoogleMaps(String locationName, double latitude, double longitude) {
        // Construct a Google Maps URI with the precise location
        String uri = "https://www.google.com/maps/search/?api=1" +
                "&query=" + Uri.encode(locationName) +
                "&ll=" + latitude + "," + longitude;

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
        context.startActivity(intent);


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

