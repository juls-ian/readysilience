package com.example.readysilience;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;

public class SuccessChangePass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_change_pass);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               startActivity(new Intent (SuccessChangePass.this, Login.class));
               finish();
            }
        }, 3000);
    }
}