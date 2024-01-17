package com.example.readysilience;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUp3 extends AppCompatActivity {

    private EditText inputCode1, inputCode2, inputCode3, inputCode4, inputCode5, inputCode6;
    private String verificationId;
    String firstName, lastName, email, age, sex, houseNumber, purok, phoneNumber, password, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3);

        Intent intent = getIntent();
        firstName = intent.getStringExtra("firstName");
        lastName = intent.getStringExtra("lastName");
        email = intent.getStringExtra("email");
        age = intent.getStringExtra("age");
        sex = intent.getStringExtra("sex");
        houseNumber = intent.getStringExtra("houseNumber");
        purok = intent.getStringExtra("purok");
        phoneNumber = intent.getStringExtra("phoneNumber");


        TextView textMobile = findViewById(R.id.text_Mobile);
        textMobile.setText(String.format(
                "+63" + phoneNumber, getIntent().getStringExtra("mobile")
        ));

        inputCode1 = findViewById(R.id.inputCode1);
        inputCode2 = findViewById(R.id.inputCode2);
        inputCode3 = findViewById(R.id.inputCode3);
        inputCode4 = findViewById(R.id.inputCode4);
        inputCode5 = findViewById(R.id.inputCode5);
        inputCode6 = findViewById(R.id.inputCode6);

        setupOTPInputs();

        final Button buttonVerify = findViewById(R.id.button_Verify);

        verificationId = getIntent().getStringExtra("verificationId");

        buttonVerify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("SignUp3", "Verification button clicked");

                if(inputCode1.getText().toString().trim().isEmpty()
                        || inputCode2.getText().toString().trim().isEmpty()
                        || inputCode3.getText().toString().trim().isEmpty()
                        || inputCode4.getText().toString().trim().isEmpty()
                        || inputCode5.getText().toString().trim().isEmpty()
                        || inputCode6.getText().toString().trim().isEmpty()) {
                    Toast.makeText(SignUp3.this, "Please enter a valid OTP code", Toast.LENGTH_SHORT).show();
                    Log.d("SignUp3", "Invalid OTP code entered");
                    return;
                }
                String code =
                        inputCode1.getText().toString() +
                                inputCode2.getText().toString() +
                                inputCode3.getText().toString() +
                                inputCode4.getText().toString() +
                                inputCode5.getText().toString() +
                                inputCode6.getText().toString();

                Log.d("SignUp3", "Entered OTP code: " + code);

                if(verificationId != null) {
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            verificationId,
                            code
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d("SignUp3", "onComplete triggered");
                                    if(task.isSuccessful()) {
                                        Log.d("SignUp3", "Verification successful");
                                        Users users = new Users(firstName, lastName, email, age, sex, houseNumber, purok, phoneNumber, password, password2);

                                        Log.d("SignUpActivity", "Starting SignUp2 activity with data: " +
                                                "firstName=" + firstName +
                                                ", lastName=" + lastName +
                                                ", email=" + email +
                                                ", age=" + age +
                                                ", sex=" + sex +
                                                ", houseNumber=" + houseNumber +
                                                ", purok=" + purok +
                                                ", phoneNumber="+ phoneNumber);

                                        Intent intent = new Intent(SignUp3.this, SignUp4.class);
                                        intent.putExtra("firstName", firstName);
                                        intent.putExtra("lastName", lastName);
                                        intent.putExtra("email", email);
                                        intent.putExtra("age", age);
                                        intent.putExtra("sex", sex);
                                        intent.putExtra("houseNumber", houseNumber);
                                        intent.putExtra("purok", purok);
                                        intent.putExtra("phoneNumber", phoneNumber);


                                        startActivity(intent);
//                                        Intent intent = new Intent(getApplicationContext(), SignUp4.class);
//                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(SignUp3.this,"The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                                        Log.d("SignUp3", "Verification failed: " + task.getException().getMessage());
                                    }
                                }
                            });
                }
            }
        });

        findViewById(R.id.resend_otp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+63" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        SignUp3.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                // Log when verification is automatically completed
                                Log.d("SignUp3", "Auto verification completed with credential: " + phoneAuthCredential.toString());
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                // Log if verification fails
                                Log.e("SignUp3", "Verification failed: " + e.getMessage());
                                Toast.makeText(SignUp3.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newVerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                // Log when the code is sent
                                Log.d("SignUp3", "Verification code sent. Verification ID: " + newVerificationId);
                                verificationId = newVerificationId;
                                Toast.makeText(SignUp3.this, "OTP Sent", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

            }
        });
    }

    private void setupOTPInputs() {
        inputCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s .toString().trim().isEmpty()) {
                    inputCode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s .toString().trim().isEmpty()) {
                    inputCode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputCode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s .toString().trim().isEmpty()) {
                    inputCode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s .toString().trim().isEmpty()) {
                    inputCode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputCode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s .toString().trim().isEmpty()) {
                    inputCode6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}