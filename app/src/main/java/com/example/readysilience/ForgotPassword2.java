package com.example.readysilience;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

public class ForgotPassword2 extends AppCompatActivity {

    Handler handler = new Handler(Looper.myLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_success_change_pass);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent (ForgotPassword2.this, Login.class));
                finish();
            }
        }, 3000);
    }
}