package com.example.readysilience;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class VideoActivity extends AppCompatActivity {

    private SimpleExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        PlayerView playerView = findViewById(R.id.playerView);

        // Create a SimpleExoPlayer instance
        player = new SimpleExoPlayer.Builder(this).build();

        // Set media controller to hide/show automatically
        playerView.setUseController(true);

        // Set player to the PlayerView
        playerView.setPlayer(player);

        // Hide the navigation and status bars by default
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );

        String videoUrl = getIntent().getStringExtra("videoUrl");

        // Prepare the media item
        Uri uri = Uri.parse(videoUrl);
        player.addMediaItem(MediaItem.fromUri(uri));

        // Prepare the player
        player.prepare();
        player.setPlayWhenReady(true);  // Auto-start playback
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the player when the activity is destroyed
        player.release();
    }
}
