package com.example.readysilience;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class SuccessFeedbackSent extends AppCompatActivity {

    Handler handler = new Handler(Looper.myLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_sent);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SuccessFeedbackSent.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }
}
