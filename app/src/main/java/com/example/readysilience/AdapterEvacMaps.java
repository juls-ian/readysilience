package com.example.readysilience;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.readysilience.DataEvacMaps;
import com.example.readysilience.MapFullView;
import com.example.readysilience.R;

import java.util.ArrayList;

public class AdapterEvacMaps extends PagerAdapter {

    Context context;
    ArrayList<DataEvacMaps> evacMapsList;



    public AdapterEvacMaps(Context context, ArrayList<DataEvacMaps> evacMapsList) {
        this.context = context;
        this.evacMapsList = evacMapsList;
    }

    @Override
    public int getCount() {
        return evacMapsList.size();

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        DataEvacMaps evacMapsData = evacMapsList.get(position);

        //DATA RETRIEVAL FOR MAPS VIEWPAGER
        View view = LayoutInflater.from(context).inflate(R.layout.layout_evac_map, null);
        ImageView imageView = view.findViewById(R.id.map_pic);
        TextView textView = view.findViewById(R.id.map_name);


        int mapPic = evacMapsData.getMapPic();
        String mapName = evacMapsData.getmapName();

        Glide.with(context).asBitmap().load(mapPic).into(imageView);
        textView.setText(mapName);

        //RETRIEVAL FOR ENLARGED VIEW
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MapFullView.class);
                intent.putExtra("mapPic", mapPic);
                intent.putExtra("mapName", mapName);
                context.startActivity(intent);
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }




}
