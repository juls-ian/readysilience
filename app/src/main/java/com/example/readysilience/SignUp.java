package com.example.readysilience;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.readysilience.databinding.ActivitySignUpBinding;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.util.Log;

import androidx.annotation.NonNull;

public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding binding;
    String firstName, lastName, email, age, sex, houseNumber, purok, phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("SignUpActivity", "Continue Button Clicked");

                firstName = binding.firstName.getEditText().getText().toString();
                lastName = binding.lastName.getEditText().getText().toString();
                email = binding.email.getEditText().getText().toString();
                age = binding.age.getEditText().getText().toString();
                sex = binding.sex.getEditText().getText().toString();
                houseNumber = binding.houseNumber.getEditText().getText().toString();
                purok = binding.purok.getEditText().getText().toString();

                Log.d("SignUpActivity", "Input Values: " + firstName + ", " + lastName + ", " + email + ", " + age + ", " + houseNumber + ", " + purok + ", " + sex);

                if (!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !age.isEmpty() && !houseNumber.isEmpty() && !purok.isEmpty()) {
                    // Check if the email is already used
                    checkEmailAndProceed();
                } else {
                    Toast.makeText(SignUp.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkEmailAndProceed() {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users");

        Query query = usersRef.orderByChild("email").equalTo(email);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Email already exists, set an error
                    binding.email.setError("Email already used");
                } else {
                    // Email is not used, proceed to SignUp2 activity
                    startSignUp2Activity();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error checking email", error.toException());
                Toast.makeText(SignUp.this, "Error checking email", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startSignUp2Activity() {
        Users users = new Users(firstName, lastName, email, age, sex, houseNumber, purok, phoneNumber);

        Log.d("SignUpActivity", "Starting SignUp2 activity with data: " +
                "firstName=" + firstName +
                ", lastName=" + lastName +
                ", email=" + email +
                ", age=" + age +
                ", sex=" + sex +
                ", houseNumber=" + houseNumber +
                ", purok=" + purok);

        Intent intent = new Intent(SignUp.this, SignUp2.class);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        intent.putExtra("email", email);
        intent.putExtra("age", age);
        intent.putExtra("sex", sex);
        intent.putExtra("houseNumber", houseNumber);
        intent.putExtra("purok", purok);

        startActivity(intent);
    }
}
