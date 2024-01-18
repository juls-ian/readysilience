package com.example.readysilience;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.readysilience.databinding.ActivitySignUpBinding;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.widget.Toast;
public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding binding;
    String firstName, lastName, email, age, sex, houseNumber, purok, phoneNumber, password, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
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

                Log.d("SignUpActivity", "Input Values: " + firstName + ", " + lastName + ", " + email + ", " + age + ", " + houseNumber + ", " + purok);

                if (!firstName.isEmpty() && !lastName.isEmpty() && !age.isEmpty() && !houseNumber.isEmpty() && !purok.isEmpty()) {

                    Users users = new Users(firstName, lastName, email, age, sex, houseNumber, purok, phoneNumber, password, password2);

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
                } else{

                    Toast.makeText(SignUp.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}