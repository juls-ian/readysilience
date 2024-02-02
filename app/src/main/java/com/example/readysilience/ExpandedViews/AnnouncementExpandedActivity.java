package com.example.readysilience.ExpandedViews;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.readysilience.R;

public class AnnouncementExpandedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_expanded);

        // Get intent data
        String date = getIntent().getStringExtra("date");
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String imageUrl = getIntent().getStringExtra("imageUrl");

        // Populate views with intent data
        TextView titleTextView = findViewById(R.id.announcement_title);
        TextView dateTextView = findViewById(R.id.announcement_date);
        TextView detailsTextView = findViewById(R.id.announcement_details);
        ImageView bannerImageView = findViewById(R.id.announcement_banner);
        ImageButton backButton = findViewById(R.id.back_button);

        titleTextView.setText(title);
        dateTextView.setText(date);
        detailsTextView.setText(description);

        // Load image using Glide
        Glide.with(this)
                .load(imageUrl)
                .into(bannerImageView);

        // Set click listener for back button
        backButton.setOnClickListener(v -> onBackPressed());
    }
}
