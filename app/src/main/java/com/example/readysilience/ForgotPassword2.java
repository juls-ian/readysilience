package com.example.readysilience;

import androidx.appcompat.app.AppCompatActivity;

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
        setContentView(R.layout.activity_forgot_pass2);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent (ForgotPassword2.this, Login.class));
                finish();
            }
        }, 3000);
    }
}