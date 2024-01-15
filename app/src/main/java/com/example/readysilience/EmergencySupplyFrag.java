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

        supplyPics.addAll(Arrays.asList(R.drawable.supply_battery, R.drawable.supply_blankets, R.drawable.supply_canned_foods,
                R.drawable.supply_firstaidkit, R.drawable.supply_flashlight, R.drawable.supply_radio, R.drawable.supply_water,
                R.drawable.supply_whistle));
        supplyNames.addAll(Arrays.asList("Batteries", "Blankets", "Canned Goods", "First Aid Kit", "Flashlight",
                "Radio", "Bottled Water", "Whistle"));

        AdapterGridSupply adapterGridSupply = new AdapterGridSupply(getContext(), supplyPics, supplyNames);
        gridViewSupplies.setAdapter(adapterGridSupply);

        return view;
    }
}