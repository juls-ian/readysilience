package com.example.readysilience;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

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

        View view = LayoutInflater.from(context).inflate(R.layout.layout_evac_map, null);
        ImageView imageView = view.findViewById(R.id.map_pic);
        TextView textView = view.findViewById(R.id.map_name);


        int mapPic = evacMapsData.getMapPic();
        String mapName = evacMapsData.getmapName();

        Glide.with(context).asBitmap().load(mapPic).into(imageView);
        textView.setText(mapName);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event for the item at the specified position
                // You can perform any action here, for example, show a Toast
                Toast.makeText(context, "Clicked item " + position, Toast.LENGTH_SHORT).show();
                openImageDetailFragment(mapPic);
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    private void openImageDetailFragment(int imageResource) {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentMapDetail fragment = FragmentMapDetail.newInstance(imageResource);
        fragmentManager.beginTransaction()
                .replace(android.R.id.content, fragment)
                .addToBackStack(null)
                .commit();
    }


}
