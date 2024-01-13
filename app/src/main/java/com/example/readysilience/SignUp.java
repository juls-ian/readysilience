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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.widget.Toast;
public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding binding;
    String firstName, lastName, age, houseNumber, purok, phoneNumber;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        MaterialAutoCompleteTextView autoCompleteTextView = findViewById(R.id.inputTV);
//        Button next = findViewById(R.id.continue_button);

        binding.continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("SignUpActivity", "Continue Button Clicked");

                firstName = binding.firstName.getEditText().getText().toString();
                lastName = binding.lastName.getEditText().getText().toString();
                age = binding.age.getEditText().getText().toString();
                houseNumber = binding.houseNumber.getEditText().getText().toString();
                purok = binding.purok.getEditText().getText().toString();

                Log.d("SignUpActivity", "Input Values: " + firstName + ", " + lastName + ", " + age + ", " + houseNumber + ", " + purok);

                if (!firstName.isEmpty() && !lastName.isEmpty() && !age.isEmpty() && !houseNumber.isEmpty() && !purok.isEmpty()) {

                    Users users = new Users(firstName, lastName, age, houseNumber, purok, phoneNumber);
//                    db = FirebaseDatabase.getInstance();
//                    reference = db.getReference("Users");

                    Log.d("SignUpActivity", "Starting SignUp2 activity with data: " +
                            "firstName=" + firstName +
                            ", lastName=" + lastName +
                            ", age=" + age +
                            ", houseNumber=" + houseNumber +
                            ", purok=" + purok);


                    Intent intent = new Intent(SignUp.this, SignUp2.class);
                    intent.putExtra("firstName", firstName);
                    intent.putExtra("lastName", lastName);
                    intent.putExtra("age", age);
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