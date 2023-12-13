package com.example.readysilience;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SuccessSignUp extends AppCompatActivity {

    Handler handler = new Handler(Looper.myLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_sign_up);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent (SuccessSignUp.this, Login.class));
                finish();
            }
        }, 3000);
    }
}