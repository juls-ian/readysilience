package com.example.readysilience;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.readysilience.databinding.ActivitySignUp4Binding;
import com.example.readysilience.databinding.ActivitySignUp2Binding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SignUp4 extends AppCompatActivity {

    ActivitySignUp4Binding binding;
    String firstName, lastName, email, age, sex, houseNumber, purok, phoneNumber, password, password2;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUp4Binding.inflate(getLayoutInflater());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        firstName = intent.getStringExtra("firstName");
        lastName = intent.getStringExtra("lastName");
        email = intent.getStringExtra("email");
        age = intent.getStringExtra("age");
        sex = intent.getStringExtra("sex");
        houseNumber = intent.getStringExtra("houseNumber");
        purok = intent.getStringExtra("purok");
        phoneNumber = intent.getStringExtra("phoneNumber");

        binding.createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("SignUpActivity", "Create Account Button Clicked");

                password = binding.password.getEditText().getText().toString();
                password2 = binding.password2.getEditText().getText().toString();

                Log.d("SignUpActivity", "Input Values: " + firstName + ", " + lastName + ", " + email + ", " + sex + ", " + age + ", " + houseNumber + ", " + purok);

                if (!password.isEmpty() && !password2.isEmpty()) {

                    // Email Authentication
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("SignUp4Activity", "createUserWithEmailAndPassword onComplete");
                                        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                        String displayName = firstName + " " + lastName;

                                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                .setDisplayName(displayName)
                                                .build();

                                        firebaseUser.updateProfile(profileUpdates);

                                        // Database Update
                                        Users users = new Users(firstName, lastName, email, age, sex, houseNumber, purok, phoneNumber, password, password2);
                                        db = FirebaseDatabase.getInstance();
                                        reference = db.getReference("Users");

                                        reference.child(firebaseUser.getUid()).setValue(users)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Log.d("SignUp4Activity", "setValue onComplete");
                                                            Intent successIntent = new Intent(SignUp4.this, SuccessSignUp.class);
                                                            startActivity(successIntent);
                                                        } else {
                                                            Log.e("SignUpActivity", "setValue failed", task.getException());
                                                            Toast.makeText(SignUp4.this, "Failed to update data", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    } else {
                                        Log.e("SignUpActivity", "createUserWithEmailAndPassword failed", task.getException());
                                        Toast.makeText(SignUp4.this, "Failed to create user", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
