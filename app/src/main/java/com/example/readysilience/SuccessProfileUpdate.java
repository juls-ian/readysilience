package com.example.readysilience;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class SuccessProfileUpdate extends AppCompatActivity {

    Handler handler = new Handler(Looper.myLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_profile_update);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SuccessProfileUpdate.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }
}
