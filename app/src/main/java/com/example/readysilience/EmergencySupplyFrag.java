package com.example.readysilience;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;


public class EmergencySupplyFrag extends Fragment {

    GridView gridViewSupplies;
    ArrayList<Integer> supplyPics;
    ArrayList<String> supplyNames;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_emergency_supply, container, false);

        gridViewSupplies = view.findViewById(R.id.supplies_grid_view);
        supplyPics = new ArrayList<>();
        supplyNames = new ArrayList<>();

        supplyPics.addAll(Arrays.asList(R.drawable.supplies_battery, R.drawable.supplies_blanket, R.drawable.supplies_can,
                R.drawable.supplies_firstaidkit, R.drawable.supplies_flashlight, R.drawable.supplies_radio, R.drawable.supplies_water,
                R.drawable.supplies_whistle));
        supplyNames.addAll(Arrays.asList("Batteries", "Blankets", "Canned Goods", "First Aid Kit", "Flashlight",
                "Radio", "Bottled Water", "Whistle"));

        AdapterGridSupply adapterGridSupply = new AdapterGridSupply(getContext(), supplyPics, supplyNames);
        gridViewSupplies.setAdapter(adapterGridSupply);

        return view;
    }
}