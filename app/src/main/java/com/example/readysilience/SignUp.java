package com.example.readysilience;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;

public class SignUp extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        MaterialAutoCompleteTextView autoCompleteTextView = findViewById(R.id.inputTV);

        Button next = findViewById(R.id.continue_button);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to switch to the new activity
                Intent intent = new Intent(SignUp.this, SignUp2.class);

                // Start the new activity
                startActivity(intent);
            }
        });


    }
}