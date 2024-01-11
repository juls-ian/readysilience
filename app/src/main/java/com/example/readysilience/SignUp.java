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

public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding signUpBinding;
    String firstName, lastName, sex, houseNumber, purok;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(signUpBinding.getRoot());
        MaterialAutoCompleteTextView autoCompleteTextView = findViewById(R.id.inputTV);

        signUpBinding.continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstName = signUpBinding.firstName.getEditText().getText().toString();
                lastName = signUpBinding.lastName.getEditText().getText().toString();
                sex = signUpBinding.sex.getEditText().getText().toString();
                houseNumber = signUpBinding.houseNumber.getEditText().getText().toString();
                purok = signUpBinding.purok.getEditText().getText().toString();

                if (!firstName.isEmpty() && !lastName.isEmpty() && !sex.isEmpty() && !houseNumber.isEmpty() && !purok.isEmpty()) {
                    Users users = new Users(firstName, lastName, sex, houseNumber, purok);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Users");
                    reference.child(firstName).setValue(users);
                }
            }
        });

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
