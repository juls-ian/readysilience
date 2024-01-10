package com.example.readysilience;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rd.PageIndicatorView;

import java.util.ArrayList;

public class EvacCenterFrag extends Fragment {

    ViewPager viewPager;
    PageIndicatorView pageIndicatorView;
    ArrayList<DataEvacMaps> evacMapsList = new ArrayList<>();

    public EvacCenterFrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_evac_center, container, false);

        viewPager = view.findViewById(R.id.maps_viewpager);

        evacMapsList.add(new DataEvacMaps(R.drawable.map_staana_evac, "Sta. Ana Evacuation Map"));
        evacMapsList.add(new DataEvacMaps(R.drawable.map_staana_evac, "Flood Exposure"));

        int initialPosition = Integer.MAX_VALUE / 2;
        viewPager.setAdapter(new AdapterEvacMaps(getContext(), evacMapsList));
        viewPager.setPadding(50, 0, 50, 0);
        viewPager.setCurrentItem(initialPosition);

        pageIndicatorView = view.findViewById(R.id.pageIndicatorView);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        return view;
    }

}