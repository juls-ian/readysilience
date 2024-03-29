package com.example.readysilience;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.rd.PageIndicatorView;

import java.util.ArrayList;
import android.view.View;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EvacCenterFrag extends Fragment {

    ViewPager viewPagerMaps, viewPagerCenters;
    PageIndicatorView pageIndicatorView;
    ArrayList<DataEvacMaps> evacMapsList = new ArrayList<>();
    ArrayList<DataEvacCenters> evacCentersList = new ArrayList<>();

    public EvacCenterFrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_evac_center, container, false);

        //STA ANA MAPS
        viewPagerMaps = view.findViewById(R.id.maps_viewpager);
        evacMapsList.add(new DataEvacMaps(R.drawable.map_staana_evac, "Sta. Ana Evacuation Map"));
        evacMapsList.add(new DataEvacMaps(R.drawable.map_flood_exposure, "Sta. Ana's Flood Exposure Map"));
        evacMapsList.add(new DataEvacMaps(R.drawable.map_flood_hazard, "Sta. Ana's Flood Hazard Map"));

        int initialPosition = evacMapsList.size() / 2;
        viewPagerMaps.setAdapter(new AdapterEvacMaps(getContext(), evacMapsList));
        viewPagerMaps.setPadding(50, 0, 50, 0);
//        viewPager.setCurrentItem(initialPosition);

        pageIndicatorView = view.findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setCount(evacMapsList.size());

        viewPagerMaps.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Not needed for this implementation
            }

            @Override
            public void onPageSelected(int position) {
                // Update the selected page in PageIndicatorView
                pageIndicatorView.setSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Not needed for this implementation
            }
        });
        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = new MapFragment();
        fragmentManager.beginTransaction().replace(R.id.map_layout, mapFragment).commit();


        //EVAC CENTERS
        viewPagerCenters = view.findViewById(R.id.evacuation_center_viewpager);

        evacCentersList.add(new DataEvacCenters(R.drawable.evac_center_stomas, "Sto Tomas Evacuation Center", "Sto. Tomas, Batangas",
                "Need Donation", "Equipped", "Equipped", "Equipped",
                "Available",14.10712419186952, 121.13908182353603));

        evacCentersList.add(new DataEvacCenters(R.drawable.evac_center_court, "Sta Ana Basketball Court", "Sto. Tomas, Batangas",
                "Need Donation", "Equipped", "Equipped", "Need Donation",
                "Available", 14.067415392647591, 121.19463666402055));

        evacCentersList.add(new DataEvacCenters(R.drawable.evac_center_school, "Sta Ana Elementary School", "Sto. Tomas, Batangas",
                "Equipped", "Need Donation", "Equipped", "Need Donation",
                "Crowded", 14.066051218977245, 121.19486635995683));

        viewPagerMaps.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disable ScrollView's touch events
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        viewPagerCenters.setAdapter(new AdapterEvacCenter(getContext(), evacCentersList));
        viewPagerCenters.setPadding(50, 0, 50, 0);

        return view;
    }


    }



