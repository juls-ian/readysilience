package com.example.readysilience;

import static com.example.readysilience.R.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterEvacCenter extends PagerAdapter {

    Context context;
    ArrayList<DataEvacCenters> evacCentersList;



    public AdapterEvacCenter(Context context, ArrayList<DataEvacCenters> evacCentersList) {
        this.context = context;
        this.evacCentersList = evacCentersList;
    }

    @Override
    public int getCount() {
        return evacCentersList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if (evacCentersList.size() == 0) {
            return new Object(); // Handle empty list to prevent IndexOutOfBoundsException
        }

        int realPosition = position % evacCentersList.size();
        DataEvacCenters dataEvacCenters = evacCentersList.get(realPosition);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_evac_centers, null);
        ImageView imageView = view.findViewById(id.evacuation_center_pic);
        TextView textView = view.findViewById(id.evac_center_name);
        TextView textView1 = view.findViewById(id.evac_location);
        TextView textView2 = view.findViewById(id.needs_water);
        TextView textView3 = view.findViewById(id.needs_can);
        TextView textView4 = view.findViewById(id.needs_blanket);
        TextView textView5 = view.findViewById(id.needs_medic);
        TextView textView6 = view.findViewById(id.center_availability);
        ImageView waterBottleIcon = view.findViewById(id.waterbottle_icon);
        ImageView cannedFoodIcon = view.findViewById(id.can_icon);
        ImageView blanketIcon = view.findViewById(id.blanket_icon);
        ImageView medicIcon = view.findViewById(id.medic_icon);


        Glide.with(context).asBitmap().load(dataEvacCenters.getEvaCenterPic()).into(imageView);
        textView.setText(dataEvacCenters.getCenterName());
        textView1.setText(dataEvacCenters.getCenterLocation());
        textView2.setText(dataEvacCenters.getWaterSupply());
        textView3.setText(dataEvacCenters.getFoodSupply());
        textView4.setText(dataEvacCenters.getBlanketSupply());
        textView5.setText(dataEvacCenters.getMedicSupply());
        textView6.setText(dataEvacCenters.getCenterAvailability());


        setIconBackground(textView2.getText().toString(), waterBottleIcon);
        setIconBackground(textView3.getText().toString(), cannedFoodIcon);
        setIconBackground(textView4.getText().toString(), blanketIcon);
        setIconBackground(textView5.getText().toString(), medicIcon);

        setBackgroundColorBasedOnAvailability(dataEvacCenters.getCenterAvailability(), textView6);



        container.addView(view, 0);
        return view;
    }

    private void setIconBackground(String status, ImageView waterBottleIcon) {
        if (status.equals("Equipped")) {
            waterBottleIcon.setBackgroundResource(R.drawable.bg_3needs_ready);
        } else if (status.equals("Need Donation")) {
            waterBottleIcon.setBackgroundResource(R.drawable.bg_3needs_not);
        }

    }

    private void setBackgroundColorBasedOnAvailability(String availabilityStatus, TextView centerAvailabilityTextView) {
        int backgroundDrawable;
        if ("Available".equals(availabilityStatus)) {
            backgroundDrawable = drawable.bg_evac_available;
        } else {
            backgroundDrawable = drawable.bg_evac_unavailable;
        }
        centerAvailabilityTextView.setBackgroundResource(backgroundDrawable);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public float getPageWidth(int position) {
        return 0.9f;
    }
}
