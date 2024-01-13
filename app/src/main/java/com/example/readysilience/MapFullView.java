package com.example.readysilience;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MapFullView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mapfull_view);

        ImageView enlargedImageView = findViewById(R.id.enlargedImageView);
        ImageButton closeButton = findViewById(R.id.closeButton);
        TextView mapNameTextView = findViewById(R.id.mapNameInside);

        // Retrieve data from the Intent
        int mapPic = getIntent().getIntExtra("mapPic", 0);
        String mapName = getIntent().getStringExtra("mapName");

        // Set data to views
        enlargedImageView.setImageResource(mapPic);
        mapNameTextView.setText(mapName);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the activity when the close button is clicked
                finish();
            }
        });

    }
}