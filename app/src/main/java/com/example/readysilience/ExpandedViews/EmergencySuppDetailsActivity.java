package com.example.readysilience.ExpandedViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.readysilience.AdapterCenters;
import com.example.readysilience.AdapterStoresSupplies;
import com.example.readysilience.DataCenter;
import com.example.readysilience.DataStoresSupplies;
import com.example.readysilience.EmergencySupplyFrag;
import com.example.readysilience.Feedback;
import com.example.readysilience.MainActivity;
import com.example.readysilience.R;

import java.util.ArrayList;

public class EmergencySuppDetailsActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_supp_details);

        ImageButton backButton = findViewById(R.id.back_button_es);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        int supplyPic = getIntent().getIntExtra("supplyPic", 0);
        String supplyName = getIntent().getStringExtra("supplyName");
//        String additionalDetails = getIntent().getStringExtra("additionalDetails");

        // Update UI with the information
        ImageView supplyImageView = findViewById(R.id.supply_pic);
        TextView supplyNameTextView = findViewById(R.id.supply_name);
//        TextView detailsTextView = findViewById(R.id.additional_details);

        supplyImageView.setImageResource(supplyPic);
        supplyNameTextView.setText(supplyName);
//        detailsTextView.setText(additionalDetails);

        viewPager = findViewById(R.id.viewpager_stores_supplies);
        ArrayList<DataStoresSupplies> storeSuppliesList = new ArrayList<>();

        storeSuppliesList.add(new DataStoresSupplies(R.drawable.supplies_store4, "Stella's Sari-sari store"));
        storeSuppliesList.add(new DataStoresSupplies(R.drawable.supplies_store2, "MMM Deelicious Bakery"));
        storeSuppliesList.add(new DataStoresSupplies(R.drawable.supplies_store3, "Loida's Sari-sari store"));
        storeSuppliesList.add(new DataStoresSupplies(R.drawable.supplies_store1, "Vapezilla x MGS Variety Store"));

        int initialPosition = Integer.MAX_VALUE / 2;
        AdapterStoresSupplies adapter = new AdapterStoresSupplies(this, storeSuppliesList);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(50, 0, 50, 0);
        viewPager.setCurrentItem(initialPosition);
    }

}
