package com.example.readysilience.ExpandedViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.readysilience.AdapterStoresFirstaid;
import com.example.readysilience.AdapterStoresSupplies;
import com.example.readysilience.DataStoresFirstaid;
import com.example.readysilience.DataStoresSupplies;
import com.example.readysilience.R;

import java.util.ArrayList;

public class FASuppDetailsActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fasupp_details);

        ImageButton backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView expandedItemImage = findViewById(R.id.fa_image);
        TextView expandedItemName = findViewById(R.id.firstaid_name);
        TextView expandedItemDesc = findViewById(R.id.firstaid_desc);

        Intent intent = getIntent();
        if (intent != null) {
            int itemPic = intent.getIntExtra("item_pic", R.drawable.image_icon); // Use a default image if not provided
            String itemName = intent.getStringExtra("item_name");
            String itemDesc = intent.getStringExtra("item_desc");

            expandedItemImage.setImageResource(itemPic);
            expandedItemName.setText(itemName);
            expandedItemDesc.setText(itemDesc);

        }



        viewPager = findViewById(R.id.viewpager_fa_stores);
        ArrayList<DataStoresFirstaid> storesFirstaidsList = new ArrayList<>();

        storesFirstaidsList.add(new DataStoresFirstaid("https://maps.app.goo.gl/xPvV3bvvwZLeDNov9", "CM Pharmacy"));
        storesFirstaidsList.add(new DataStoresFirstaid("https://maps.app.goo.gl/UCQeD92ppKNqtjTb9", "Generika Drugstore"));
        storesFirstaidsList.add(new DataStoresFirstaid("https://maps.app.goo.gl/UCQeD92ppKNqtjTb9", "Botika Ni Poggs"));


        int initialPosition = Integer.MAX_VALUE / 2;
        AdapterStoresFirstaid adapter = new AdapterStoresFirstaid(this, storesFirstaidsList);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(50, 0, 50, 0);
        viewPager.setCurrentItem(initialPosition);
    }
}