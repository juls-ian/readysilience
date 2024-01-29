package com.example.readysilience;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.readysilience.ExpandedViews.EmergencySuppDetailsActivity;

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
        View rootView = inflater.inflate(R.layout.fragment_emergency_supply, container, false);

        gridViewSupplies = rootView.findViewById(R.id.supplies_grid_view);
        supplyPics = new ArrayList<>();
        supplyNames = new ArrayList<>();

        supplyPics.addAll(Arrays.asList(R.drawable.supplies_battery, R.drawable.supplies_blanket, R.drawable.supplies_can,
                R.drawable.supplies_firstaidkit, R.drawable.supplies_flashlight, R.drawable.supplies_radio, R.drawable.supplies_water,
                R.drawable.supplies_whistle));
        supplyNames.addAll(Arrays.asList("Batteries", "Blankets", "Canned Goods", "First Aid Kit", "Flashlight",
                "Radio", "Bottled Water", "Whistle"));

        AdapterGridSupply adapterGridSupply = new AdapterGridSupply(getContext(), supplyPics, supplyNames);
        gridViewSupplies.setAdapter(adapterGridSupply);

        gridViewSupplies.setOnItemClickListener((parent, view, position, id) -> {
            // Handle item click
            showExpandedView(position);
        });

//        return view;
        return rootView;
    }

    private void showExpandedView(int position) {
        // Create an Intent to launch EmergencySuppDetailsActivity
        Intent intent = new Intent(requireContext(), EmergencySuppDetailsActivity.class);

        // Pass the selected item's information to the next activity
        intent.putExtra("supplyPic", supplyPics.get(position));
        intent.putExtra("supplyName", supplyNames.get(position));

        // Start the activity
        startActivity(intent);
    }
}