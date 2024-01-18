package com.example.readysilience;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

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
                // Create an Intent to switch to the new activity
                Intent intent = new Intent(Login.this, SignUp.class);
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

                        // Now you can get user details from the 'user' object
                        String userId = user.getUid();
                        String userEmailFromAuth = user.getEmail();

                        Log.d("LoginActivity", "User ID: " + userId);
                        Log.d("LoginActivity", "User Email: " + userEmailFromAuth);

                        // Add your logic to navigate to the next activity
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
}
