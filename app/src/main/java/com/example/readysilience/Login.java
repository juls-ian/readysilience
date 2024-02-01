package com.example.readysilience;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    TextInputLayout loginEmail, loginPassword;
    Button loginButton;
    Button signupButton;
    Button forgotpassButton;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        if (AuthManager.isLoggedIn(this)) {
            // User is logged in, navigate to the main part of the app
            startActivity(new Intent(this, MainActivity.class));
            finish(); // Finish the current activity to prevent going back to it
            return; // Skip the rest of the code in onCreate
        }

        loginEmail = findViewById(R.id.login_Email);
        loginPassword = findViewById(R.id.login_Pass);
        loginButton = findViewById(R.id.login_button);
        signupButton = findViewById(R.id.sign_up_button);
        forgotpassButton = findViewById(R.id.forgot_pass_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateEmail() | !validatePass()) {
                    // Handle validation failure
                } else {
                    loginUser();
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, TCActivity.class);
                // Start the new activity
                startActivity(intent);
            }
        });

        forgotpassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to switch to the new activity
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                // Start the new activity
                startActivity(intent);
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, fragment);
        transaction.addToBackStack(null); // Add to back stack for navigation
        transaction.commit();
    }

    public Boolean validateEmail() {
        String val = loginEmail.getEditText().getText().toString();
        if (val.isEmpty()) {
            loginEmail.setError("Username cannot be empty");
            return false;
        } else {
            loginEmail.setError(null);
            return true;
        }
    }

    public Boolean validatePass() {
        String val = loginPassword.getEditText().getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Password cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }

    public void loginUser() {
        String userEmail = loginEmail.getEditText().getText().toString().trim();
        String userPass = loginPassword.getEditText().getText().toString().trim();

        Log.d("LoginActivity", "Checking user: " + userEmail);

        auth.signInWithEmailAndPassword(userEmail, userPass)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("LoginActivity", "signInWithEmail:success");
                        FirebaseUser user = auth.getCurrentUser();

                        String userId = user.getUid();
                        String userEmailFromAuth = user.getEmail();

                        AuthManager.setLoggedIn(Login.this, true);

                        Log.d("LoginActivity", "User ID: " + userId);
                        Log.d("LoginActivity", "User Email: " + userEmailFromAuth);

                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("userId", userId);
                        intent.putExtra("userEmail", userEmailFromAuth);
                        startActivity(intent);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("LoginActivity", "signInWithEmail:failure", task.getException());

                        if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                            // User not found
                            loginEmail.setError("User not found");
                        } else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // Invalid password
                            loginPassword.setError("Invalid password");
                        }
                    }
                });
    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finish();
    }
}