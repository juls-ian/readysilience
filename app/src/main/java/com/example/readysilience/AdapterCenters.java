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
import com.example.readysilience.DataCenter;
import com.example.readysilience.R;

import java.util.ArrayList;

public class AdapterCenters extends PagerAdapter {

    Context context;
    ArrayList<DataCenter> dataCenterList;
    private AdapterEvacCenter.OnItemClickListener onItemClickListener;



    public AdapterCenters(Context context, ArrayList<DataCenter> dataCenterList) {
        this.context = context;
        this.dataCenterList = dataCenterList;
    }

    public void setOnItemClickListener(AdapterEvacCenter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getCount() {
//        return Integer.MAX_VALUE;
        return dataCenterList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if (dataCenterList.size() == 0) {
            return new Object(); // Handle empty list to prevent IndexOutOfBoundsException
        }

        int realPosition = position % dataCenterList.size();
        DataCenter dataCenter = dataCenterList.get(realPosition);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_nearbycenter, null);
        ImageView imageView = view.findViewById(R.id.centerImage);
        TextView textView = view.findViewById(R.id.text1);

        Glide.with(context).asBitmap().load(dataCenter.getImageUrl()).into(imageView);
        textView.setText(dataCenter.getCenterName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int realPosition = position % dataCenterList.size();
                DataCenter dataCenters = dataCenterList.get(realPosition);

                // Open Google Maps with the address
                openGoogleMaps(dataCenters.getCenterName(), dataCenters.getHospitalLatitude(), dataCenters.getHospitalLongitude());
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
        return 0.6f;
    }
}
