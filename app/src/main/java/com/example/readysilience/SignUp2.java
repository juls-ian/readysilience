package com.example.readysilience;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.readysilience.databinding.ActivitySignUp2Binding;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUp2 extends AppCompatActivity {

    ActivitySignUp2Binding binding;
    String firstName, lastName, email, age, sex, houseNumber, purok, phoneNumber, password, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUp2Binding.inflate(getLayoutInflater());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_sign_up2);

        final TextInputLayout signupPhone = findViewById(R.id.signupPhone);
        final Button sendOTPbutton = findViewById(R.id.sendOTPbutton);

        Intent intent = getIntent();
        firstName = intent.getStringExtra("firstName");
        lastName = intent.getStringExtra("lastName");
        email = intent.getStringExtra("email");
        age = intent.getStringExtra("age");
        sex = intent.getStringExtra("sex");
        houseNumber = intent.getStringExtra("houseNumber");
        purok = intent.getStringExtra("purok");

        sendOTPbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (signupPhone.getEditText().getText().toString().trim().isEmpty()) {
                    Toast.makeText(SignUp2.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendOTPbutton.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+63" + signupPhone.getEditText().getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        SignUp2.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                sendOTPbutton.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                sendOTPbutton.setVisibility(View.VISIBLE);
                                Toast.makeText(SignUp2.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                sendOTPbutton.setVisibility((View.VISIBLE));

                                // Pass user data to the next activity
                                Intent nextIntent = new Intent(getApplicationContext(), SignUp3.class);
                                nextIntent.putExtra("firstName", firstName);
                                nextIntent.putExtra("lastName", lastName);
                                nextIntent.putExtra("email", email);
                                nextIntent.putExtra("age", age);
                                nextIntent.putExtra("sex", sex);
                                nextIntent.putExtra("houseNumber", houseNumber);
                                nextIntent.putExtra("purok", purok);
                                nextIntent.putExtra("phoneNumber", signupPhone.getEditText().getText().toString());
                                nextIntent.putExtra("verificationId", verificationId);
                                startActivity(nextIntent);
                            }
                        }
                );
            }
        });
    }
}
