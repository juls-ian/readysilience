package com.example.readysilience;

import android.Manifest;
import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.widget.AutoCompleteTextView;

import android.net.Uri;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseUser currentUser;

    private EditText nameEditText, ageEditText, emailEditText, numberEditText, houseNumEditText, purokEditText;
    private AutoCompleteTextView sexAutoCompleteTextView;
    private UserProfileData userProfileData;
    private CircleImageView userIcon;
    private Uri selectedImageUri;
    private static final int REQUEST_PERMISSION_CODE = 123;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        // Initialize UI elements
        nameEditText = findViewById(R.id.tf_name);
        ageEditText = findViewById(R.id.tf_age);
        emailEditText = findViewById(R.id.tf_email);
        numberEditText = findViewById(R.id.tf_number);
        sexAutoCompleteTextView = findViewById(R.id.tf_sex);
        houseNumEditText = findViewById(R.id.tf_housenum);
        purokEditText = findViewById(R.id.tf_purok);

        sexAutoCompleteTextView.setEnabled(false);
        emailEditText.setEnabled(false);
        numberEditText.setEnabled(false);

        userIcon = findViewById(R.id.user_icon);
        ImageButton cameraButton = findViewById(R.id.camera);

        // Set an OnClickListener for the cameraButton to handle image picking
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call finish to close the current activity and go back to the previous activity
                finish();
            }
        });

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserProfile();
            }
        });

        // Load user profile data
        loadUserProfile();
    }

    private void loadUserProfile() {
        if (currentUser != null) {
            DatabaseReference userRef = databaseReference.child(currentUser.getUid());
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    UserProfileData userProfileData = dataSnapshot.getValue(UserProfileData.class);
                    if (userProfileData != null) {
                        // Only update if the data is not already populated
                        Log.d("UserProfile", "User Profile Data: " + userProfileData.toString());
                        if (isProfileDataEmpty()) {
                            populateUI(userProfileData);
                        }
                    } else {
                        Log.w("UserProfile", "User profile data is null");
                        // Handle the case where userProfileData is null
                        // For example, you might want to set default values in your UI.
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("UserProfile", "Error loading user profile: " + databaseError.getMessage());
                    // Handle errors
                    Toast.makeText(UserProfile.this, "Failed to load profile data", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean isProfileDataEmpty() {
        return nameEditText.getText().toString().isEmpty() &&
                ageEditText.getText().toString().isEmpty() &&
                emailEditText.getText().toString().isEmpty() &&
                numberEditText.getText().toString().isEmpty() &&
                sexAutoCompleteTextView.getText().toString().isEmpty() &&
                houseNumEditText.getText().toString().isEmpty() &&
                purokEditText.getText().toString().isEmpty();
    }
    private void populateUI(UserProfileData userProfileData) {
        Log.d("UserProfile", "UserProfileData from database: " + userProfileData);

        // Set the retrieved data to the UI elements
        String firstName = userProfileData.getFirstName();
        String lastName = userProfileData.getLastName();
        String fullName = firstName + " " + lastName;
        nameEditText.setText(fullName);
        ageEditText.setText(userProfileData.getAge());
        emailEditText.setText(userProfileData.getEmail());
        numberEditText.setText(userProfileData.getPhoneNumber());
        sexAutoCompleteTextView.setText(userProfileData.getSex());
        houseNumEditText.setText(userProfileData.getHouseNumber());
        purokEditText.setText(userProfileData.getPurok());

        // Load the profile image from the URL
        String profileImageUrl = userProfileData.getProfileImageUrl();
        if (profileImageUrl != null && !profileImageUrl.isEmpty()) {
            // Use your preferred image loading library or method to load the image
            // Here, I'm assuming you are using Glide:
            Glide.with(this).load(profileImageUrl).into(userIcon);
        }

        // Update the profile image
        updateProfileImage(profileImageUrl);
    }

    // New method to update the profile image using Glide
    private void updateProfileImage(String profileImageUrl) {
        if (profileImageUrl != null && !profileImageUrl.isEmpty()) {
            Glide.with(this).load(profileImageUrl).into(userIcon);
        }
    }

    private void openImagePicker() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Request permission
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
                return;
            }
        }

        // Proceed with image picker if permission is granted
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, open image picker
                openImagePicker();
            } else {
                // Permission denied, show a message or take appropriate action
                Toast.makeText(this, "Permission denied. Unable to open image picker.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Add this method to handle the result from the image picker
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();

            // Clear Glide cache and load the new image into the CircleImageView
            Glide.with(this).clear(userIcon);
            Glide.with(this).load(selectedImageUri).into(userIcon);
        }
    }

    private void saveUserProfile() {
        try {
            Log.d("UserProfile", "saveUserProfile method called");
            if (currentUser != null) {
                String firstName = extractFirstName(nameEditText.getText().toString());
                String lastName = extractLastName(nameEditText.getText().toString());
                String email = emailEditText.getText().toString();
                String phoneNumber = numberEditText.getText().toString();
                String sex = sexAutoCompleteTextView.getText().toString();
                String age = ageEditText.getText().toString();
                String houseNum = houseNumEditText.getText().toString();
                String purok = purokEditText.getText().toString();

                userProfileData = new UserProfileData(firstName, lastName, age, email, phoneNumber, sex, houseNum, purok);

                if (selectedImageUri != null) {
                    uploadImage(selectedImageUri);
                } else {
                    // If no new image is selected, directly update the user profile data in the database
                    updateUserProfileInDatabase();
                }
            }
        } catch (Exception e) {
            Log.e("UserProfile", "Exception during saveUserProfile: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void uploadImage(Uri imageUri) {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference profileImageRef = storageRef.child("profile_images/" + currentUser.getUid() + ".jpg");

        profileImageRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    // Image uploaded successfully
                    // Now, get the download URL
                    profileImageRef.getDownloadUrl().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            if (downloadUri != null) {
                                // Update the profile image URL in the user profile data
                                userProfileData.setProfileImageUrl(downloadUri.toString());
                                // Update the user profile data in the database
                                updateUserProfileInDatabase();
                            } else {
                                // Handle the case where the download URL is null
                                Toast.makeText(UserProfile.this, "Failed to get download URL", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Handle the case where getting the download URL fails
                            Toast.makeText(UserProfile.this, "Failed to get download URL", Toast.LENGTH_SHORT).show();
                        }
                    });
                })
                .addOnFailureListener(e -> {
                    // Handle failed image upload
                    Toast.makeText(UserProfile.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                });
    }

    private void updateUserProfileInDatabase() {
        // Save user profile data to the database
        databaseReference.child(currentUser.getUid()).setValue(userProfileData)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Display a toast indicating successful profile update
                        Toast.makeText(UserProfile.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                        // Load the updated user profile data
                        loadUserProfile();
                    } else {
                        // Display a toast indicating failed profile update
                        Toast.makeText(UserProfile.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                        Log.e("UserProfile", "Failed to update profile: " + task.getException().getMessage());
                    }
                });
    }

    // Helper method to extract first name
    private String extractFirstName(String fullName) {
        // Assume the first word is the first name
        return fullName.split(" ")[0];
    }

    // Helper method to extract last name
    private String extractLastName(String fullName) {
        // Assume the last word is the last name
        String[] nameParts = fullName.split(" ");
        return nameParts.length > 1 ? nameParts[nameParts.length - 1] : "";
    }

    public static class UserProfileData {
        private String firstName;
        private String lastName;
        private String age;
        private String email;
        private String phoneNumber;
        private String sex;
        private String houseNumber;
        private String purok;
        private String profileImageUrl;

        public UserProfileData() {
            // Default constructor required for Firebase
        }

        public UserProfileData(String firstName, String lastName, String age, String email, String phoneNumber, String sex, String houseNumber, String purok) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.sex = sex;
            this.houseNumber = houseNumber;
            this.purok = purok;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getAge() {
            return age;
        }
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getHouseNumber() {
            return houseNumber;
        }

        public String getPurok() {
            return purok;

        }
        public String getProfileImageUrl() {
            return profileImageUrl;
        }

        public void setProfileImageUrl(String profileImageUrl) {
            this.profileImageUrl = profileImageUrl;
        }
    }

}
