package com.example.readysilience;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.readysilience.databinding.ActivitySignUp2Binding;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SignUp2 extends AppCompatActivity {

    ActivitySignUp2Binding binding;
    String firstName, lastName, age, houseNumber, purok, phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUp2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String age = intent.getStringExtra("age");
        String houseNumber = intent.getStringExtra("houseNumber");
        String purok = intent.getStringExtra("purok");

        binding.sendOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                Log.d("SignUp2Activity", "sendOtpButton Clicked");

                phoneNumber = binding.signupPhone.getEditText().getText().toString();
                Log.d("SignUp2Activity", "Input Values: " + phoneNumber );

                if (!phoneNumber.isEmpty()){

                    Users users = new Users(firstName, lastName, age, houseNumber, purok, phoneNumber);

                    Log.d("SignUpActivity", "Starting SignUp2 activity with data: " +
                            "firstName=" + firstName +
                            ", lastName=" + lastName +
                            ", age=" + age +
                            ", houseNumber=" + houseNumber +
                            ", purok=" + purok +
                            ", phoneNumber="+ phoneNumber);

                    Intent intent = new Intent(SignUp2.this, SignUp3.class);
                    intent.putExtra("firstName", firstName);
                    intent.putExtra("lastName", lastName);
                    intent.putExtra("age", age);
                    intent.putExtra("houseNumber", houseNumber);
                    intent.putExtra("purok", purok);
                    intent.putExtra("phoneNumber", phoneNumber);


                    startActivity(intent);
                } else{

                    Toast.makeText(SignUp2.this, "Please fill in Phone Number", Toast.LENGTH_SHORT).show();
                }
            }

        });


//        Button sendOTP = findViewById(R.id.send_otp_button);
//
//        sendOTP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Create an Intent to switch to the new activity
//                Intent intent = new Intent(SignUp2.this, SignUp3.class);
//
//                // Start the new activity
//                startActivity(intent);
//            }
//        });


    }
}