package com.example.readysilience;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SuccessSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_sign_up);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent (SuccessSignUp.this, Login.class));
                finish();
            }
        }, 3000);
    }
}