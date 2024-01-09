package com.example.readysilience;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class InfoCenterFrag extends Fragment {

    RecyclerView recyclerView;



    public InfoCenterFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        return inflater.inflate(R.layout.fragment_info_center, container, false);
        View view = inflater.inflate(R.layout.fragment_info_center, container, false);


        //DISASTERS
        recyclerView = view.findViewById(R.id.disastersRecyclerView);
        List<DataDisasters> disastersDataList = new ArrayList<>();
        disastersDataList.add(new DataDisasters(R.drawable.earthquake, "Earthquake",
                "An earthquake is a sudden shaking of the ground caused by the movement of tectonic plates beneath the Earth's surface.",
                "Natural", "Prone", "Active"));

        disastersDataList.add(new DataDisasters(R.drawable.earthquake, "Typhoon",
                "An earthquake is a sudden shaking of the ground caused by the movement of tectonic plates beneath the Earth's surface.",
                "Natural", "Prone", "Inactive"));

        disastersDataList.add(new DataDisasters(R.drawable.earthquake, "Fire",
                "An earthquake is a sudden shaking of the ground caused by the movement of tectonic plates beneath the Earth's surface.",
                "Man-made", "Prone", "Inactive"));



        recyclerView.setAdapter(new AdapterDisasters(getContext(), disastersDataList));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));




        return view;
    }
}