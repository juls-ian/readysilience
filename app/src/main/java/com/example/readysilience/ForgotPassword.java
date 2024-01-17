package com.example.readysilience;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

public class ForgotPassword extends AppCompatActivity {

    private TextInputLayout forgotEmail;
    private Button getemailButton;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        forgotEmail = findViewById(R.id.forgotEmail);
        getemailButton = findViewById(R.id.get_email_button);

        getemailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = forgotEmail.getEditText().getText().toString();

                if (email.isEmpty()){
                    Toast.makeText(ForgotPassword.this, "Please provide your email", Toast.LENGTH_SHORT).show();
                } else {
                    forgotEmail();
                }
            }
        });
    }

    private void forgotEmail() {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(ForgotPassword.this, "Check your Email", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotPassword.this, ForgotPassword2.class));
                            finish();
                        } else {
                            Toast.makeText(ForgotPassword.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
