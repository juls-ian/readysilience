package com.example.readysilience;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Toast;

public class TCActivity extends AppCompatActivity {

    private CheckBox termsCheckBox;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tc);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        startButton = findViewById(R.id.getstarted_button);
        ImageButton backButton = findViewById(R.id.back_button);
        termsCheckBox = findViewById(R.id.terms_checkbox);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the checkbox is checked before proceeding
                if (termsCheckBox.isChecked()) {
                    Intent intent = new Intent(TCActivity.this, SignUp.class);
                    startActivity(intent);
                } else {
                    // Inform the user to check the terms before proceeding
                    Toast.makeText(TCActivity.this, "Please agree to the terms before proceeding", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Instead of finishing the activity, navigate back to the Login activity
                Intent intent = new Intent(TCActivity.this, Login.class);
                startActivity(intent);
                finish(); // Optional: finish the current activity if needed
            }
        });
    }
}
