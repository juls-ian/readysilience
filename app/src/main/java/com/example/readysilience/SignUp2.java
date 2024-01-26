package com.example.readysilience;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.InputFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.readysilience.databinding.ActivitySignUp2Binding;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.concurrent.TimeUnit;

public class SignUp2 extends AppCompatActivity {

    ActivitySignUp2Binding binding;
    String firstName, lastName, email, age, sex, houseNumber, purok, phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUp2Binding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_sign_up2);

        final TextInputLayout signupPhone = findViewById(R.id.signupPhone);
        final Button sendOTPbutton = findViewById(R.id.sendOTPbutton);

        signupPhone.getEditText().setFilters(new InputFilter[] {new InputFilter.LengthFilter(10)});

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
                String enteredPhoneNumber = signupPhone.getEditText().getText().toString().trim();

                if (enteredPhoneNumber.isEmpty()) {
                    Toast.makeText(SignUp2.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.d("Firebase", "Entered phone number: " + enteredPhoneNumber);

                checkPhoneNumberAndSendOTP(enteredPhoneNumber, signupPhone);
            }
        });
    }

    private void checkPhoneNumberAndSendOTP(final String enteredPhoneNumber, final TextInputLayout signupPhone) {
        // Check and format the phone number to ensure it follows E.164 format
        final String phoneNumber;
        if (!enteredPhoneNumber.startsWith("+")) {
            // If the country code is not present, add it
            phoneNumber = "+63" + enteredPhoneNumber;
        } else {
            phoneNumber = enteredPhoneNumber;
        }

        // Log the entered and formatted phone numbers
        Log.d("Firebase", "Entered phone number: " + enteredPhoneNumber);
        Log.d("Firebase", "Formatted phone number: " + phoneNumber);

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users");

        Query query = usersRef.orderByChild("phoneNumber").equalTo(phoneNumber);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Phone number already exists, set an error on the TextInputLayout
                    signupPhone.setError("Phone number already used");
                } else {
                    // Phone number is not used, proceed with sending OTP
                    sendOTP(phoneNumber);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error checking phone number", error.toException());
                Toast.makeText(SignUp2.this, "Error checking phone number", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendOTP(final String phoneNumber) {
        final Button sendOTPbutton = findViewById(R.id.sendOTPbutton);

        sendOTPbutton.setVisibility(View.INVISIBLE);

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                SignUp2.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        sendOTPbutton.setVisibility(View.VISIBLE);
                        // Handle verification completed if needed
                        // ...
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        sendOTPbutton.setVisibility(View.VISIBLE);
                        Toast.makeText(SignUp2.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        sendOTPbutton.setVisibility(View.VISIBLE);

                        // Pass user data to the next activity
                        Intent nextIntent = new Intent(getApplicationContext(), SignUp3.class);
                        nextIntent.putExtra("firstName", firstName);
                        nextIntent.putExtra("lastName", lastName);
                        nextIntent.putExtra("email", email);
                        nextIntent.putExtra("age", age);
                        nextIntent.putExtra("sex", sex);
                        nextIntent.putExtra("houseNumber", houseNumber);
                        nextIntent.putExtra("purok", purok);
                        nextIntent.putExtra("phoneNumber", phoneNumber);
                        nextIntent.putExtra("verificationId", verificationId);
                        startActivity(nextIntent);
                    }
                }
        );
    }
}
