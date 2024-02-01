package com.example.readysilience;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        disastersDataList.add(new DataDisasters(R.drawable.disaster_earthquake, "Earthquake",
                "An earthquake is a sudden shaking of the ground caused by the movement of tectonic plates beneath the Earth's surface.",
                "Natural", "Prone", "Active"));

        disastersDataList.add(new DataDisasters(R.drawable.disaster_typhoon, "Typhoon",
                "A typhoon is a mature tropical cyclone that develops in the western part of the North Pacific Ocean.",
                "Natural", "Low", "Inactive"));

        disastersDataList.add(new DataDisasters(R.drawable.disaster_fire, "Fire",
                "Fire is often associated with the process of combustion, where a material reacts with oxygen to produce heat and light.",
                "Man-made", "Prone", "Inactive"));

        disastersDataList.add(new DataDisasters(R.drawable.disaster_volcanic_eruption, "Volcanic Eruption",
                "A volcanic eruption occurs when magma, ash, and gases are expelled from a volcano. This explosive event can lead to lava flows, ash clouds, and pyroclastic flows.",
                "Natural", "Prone", "Inactive"));

        disastersDataList.add(new DataDisasters(R.drawable.disaster_flood, "Flood",
                "Floods happen when water overflows onto land, often due to heavy rain or rising rivers. They damage homes and infrastructure, posing risks to life and causing displacement.",
                "Both", "Prone", "Inactive"));

        disastersDataList.add(new DataDisasters(R.drawable.disaster_deforestation, "Deforestation",
                "Deforestation is the large-scale removal of trees from forests, leading to the loss of wooded areas. It disrupts ecosystems, contributes to climate change, and threatens biodiversity.",
                "Man-made", "Low", "Inactive"));





        recyclerView.setAdapter(new AdapterDisasters(getContext(), disastersDataList));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));




        return view;
    }
}