package com.example.readysilience.ExpandedViews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.readysilience.R;

public class AnnouncementExpandedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_expanded);
        ImageButton backButton = findViewById(R.id.back_button);
        ImageView announcementBanner = findViewById(R.id.announcement_banner);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String imageUrl = getIntent().getStringExtra("imageUrl");
        if (imageUrl != null) {
            Glide.with(this)
                    .load(imageUrl)
                    .into(announcementBanner);
        }
    }
}