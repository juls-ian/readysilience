package com.example.readysilience;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.readysilience.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private boolean isSignInAttempted = false;
    ActivityMainBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    //Vars
    FloatingActionButton floatButton;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    NavigationView navigationView;
    ChipNavigationBar chipNavigationBar;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FrameLayout frameLayout;
    FloatingActionButton fab;
    private DatabaseReference reportsReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        floatButton = findViewById(R.id.fab);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        Log.d("MainActivity", "onCreate: Current user: " + currentUser);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean(KEY_IS_LOGGED_IN, false);
        Log.d("MainActivity", "onCreate: isLoggedIn: " + isLoggedIn);

        if (!isLoggedIn && currentUser == null) {
            // If the user is not logged in and not previously logged in, finish the activity
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
            return;
        }

        // Save the login state to true when the user is logged in
        if (currentUser != null) {
            saveLoginState(true);
            Log.d("MainActivity", "User is logged in");
        }

        reportsReference = FirebaseDatabase.getInstance().getReference("Users Incident Reports").child(currentUser.getUid());

        //Toolbar + Drawer Nav
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_tools);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Hamburger Color
        toggle.getDrawerArrowDrawable().setColor(ContextCompat.getColor(this, R.color.blue));
        //Actionbar No title

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        //USER PROFILE
        CircleImageView userProfileIcon = findViewById(R.id.userProfileIcon);

        // Load user's profile picture
        loadUserProfileImage(userProfileIcon);

        userProfileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the UserProfile activity when the icon is clicked
                Intent userProfileIntent = new Intent(MainActivity.this, UserProfile.class);
                startActivity(userProfileIntent);
            }
        });

        //Chip Nav
        chipNavigationBar = findViewById(R.id.chip_navbar);
        chipNavigationBar.setItemSelected(R.id.chip_home, true);


        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {

            @Override
            public void onItemSelected(int id) {
                Fragment chipFragment = null;

                switch (id) {
                    case R.id.chip_home:
                        chipFragment = new HomeFrag();
                        break;

                    case R.id.chip_info_center:
                        chipFragment = new InfoCenterFrag();
                        break;

                    case R.id.chip_evacuation:
                        chipFragment = new EvacCenterFrag();
                        break;

                    case R.id.chip_hotlines:
                        chipFragment = new HotlinesFrag();
                        break;
                }

                if (chipFragment != null) {
                    openFragment(chipFragment);
                }
            }
        });

        fragmentManager = getSupportFragmentManager();
        openFragment(new HomeFrag());

        //FAB Dialog Sheet
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
                Log.d("NavigationDrawer", "Item selected: " );
            }
        });
    }

    private void loadUserProfileImage(CircleImageView userProfileIcon) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(currentUser.getUid());
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    UserProfile.UserProfileData userProfileData = dataSnapshot.getValue(UserProfile.UserProfileData.class);
                    if (userProfileData != null) {
                        // Load the profile image from the URL
                        String profileImageUrl = userProfileData.getProfileImageUrl();
                        if (profileImageUrl != null && !profileImageUrl.isEmpty()) {
                            // Use Glide to load the image into the CircleImageView
                            Glide.with(MainActivity.this).load(profileImageUrl).into(userProfileIcon);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle errors
                    Log.e("MainActivity", "Error loading user profile: " + databaseError.getMessage());
                }
            });
        }
    }

    public static class Report {
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String incidentType;
        private String description;
        private long timestamp;

        public Report() {
        }

        public Report(String firstName, String lastName, String phoneNumber, String incidentType, String description, long timestamp) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.incidentType = incidentType;
            this.description = description;
            this.timestamp = timestamp;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getIncidentType() {
            return incidentType;
        }

        public void setIncidentType(String incidentType) {
            this.incidentType = incidentType;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public String getFormattedTimestamp() {
            Date date = new Date(timestamp);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            return sdf.format(date);
        }
    }

    //SOS REPORT
    private void showBottomDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_layout);

        LinearLayout sosLayout = dialog.findViewById(R.id.layout_sos);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);
        Button reportButton = dialog.findViewById(R.id.report_button);

        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Fetch user information from the "Users" database
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(currentUser.getUid());

                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        UserProfile.UserProfileData userProfileData = dataSnapshot.getValue(UserProfile.UserProfileData.class);

                        if (userProfileData != null) {
                            String firstName = userProfileData.getFirstName();
                            String lastName = userProfileData.getLastName();
                            String phoneNumber = userProfileData.getPhoneNumber();

                            // Find the RadioGroup views
                            RadioGroup disastersRadioGroup1 = dialog.findViewById(R.id.disasters_radio_group1);
                            RadioGroup disastersRadioGroup2 = dialog.findViewById(R.id.disasters_radio_group2);

                            int selectedRadioButtonId1 = disastersRadioGroup1.getCheckedRadioButtonId();
                            int selectedRadioButtonId2 = disastersRadioGroup2.getCheckedRadioButtonId();

                            RadioButton selectedRadioButton1 = dialog.findViewById(selectedRadioButtonId1);
                            RadioButton selectedRadioButton2 = dialog.findViewById(selectedRadioButtonId2);

                            String selectedIncidentType = "";
                            if (selectedRadioButton1 != null) {
                                selectedIncidentType = selectedRadioButton1.getText().toString();
                            } else if (selectedRadioButton2 != null) {
                                selectedIncidentType = selectedRadioButton2.getText().toString();
                            } else {
                                Toast.makeText(MainActivity.this, "Please select an incident type", Toast.LENGTH_SHORT).show();
                                Log.e("MainActivity", "No incident type selected");
                                return;
                            }

                            TextInputLayout textInputLayout = dialog.findViewById(R.id.situation_report);
                            TextInputEditText descriptionEditText = textInputLayout.findViewById(R.id.incident_situation_edit_text);
                            String description = descriptionEditText.getText().toString();

                            if (selectedIncidentType.isEmpty() || description.isEmpty()) {
                                Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                                Log.e("MainActivity", "Empty incident type or description");
                                return;
                            }

                            Log.d("MainActivity", "Incident Type: " + selectedIncidentType);
                            Log.d("MainActivity", "Description: " + description);

                            Report report = new Report(firstName, lastName, phoneNumber, selectedIncidentType, description, System.currentTimeMillis());

                            String finalSelectedIncidentType = selectedIncidentType;
                            reportsReference.push().setValue(report)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(MainActivity.this, "Report sent successfully", Toast.LENGTH_SHORT).show();
                                                Log.d("MainActivity", "Report sent successfully");

                                                // Start the SuccessSOS activity
                                                Intent intent = new Intent(MainActivity.this, SuccessSOS.class);
                                                intent.putExtra("incidentType", finalSelectedIncidentType);
                                                intent.putExtra("description", description);
                                                intent.putExtra("timestamp", report.getFormattedTimestamp());
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(MainActivity.this, "Failed to send report", Toast.LENGTH_SHORT).show();
                                                Log.e("MainActivity", "Failed to send report", task.getException());
                                            }

                                            dialog.dismiss();
                                        }
                                    });
                        } else {

                            Toast.makeText(MainActivity.this, "User information not available", Toast.LENGTH_SHORT).show();
                            Log.e("MainActivity", "User information not available");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle errors
                        Log.e("MainActivity", "Error loading user information: " + databaseError.getMessage());
                    }
                });
            }
        });

        sosLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Upload video is clicked", Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    //DRAWER NAV
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemID = item.getItemId();
        item.setChecked(true);

        switch (itemID) {

            case R.id.drawer_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFrag()).commit();
                break;

            case R.id.drawer_firstaid:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new FirstAidFrag()).commit();
                break;

            case R.id.drawer_supplies:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new EmergencySupplyFrag()).commit();
                break;

            case R.id.drawer_checklist:
                Intent checklistIntent = new Intent(MainActivity.this, Checklist.class);
                startActivity(checklistIntent);
                finish();
                break;

            case R.id.drawer_feedback:
                Intent feedbackIntent = new Intent(MainActivity.this, Feedback.class);
                startActivity(feedbackIntent);
                finish();
                break;

            case R.id.drawer_logout:
                Log.d("MainActivity", "Logout menu selected");
                logoutMenu(this);
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("MainActivity", "onResume: Checking authentication status");

        // Check authentication status
        FirebaseUser currentUser = auth.getCurrentUser();
        Log.d("MainActivity", "onResume: Current user: " + currentUser);

        if (currentUser == null && !isSignInAttempted) {
            // The user is not signed in, navigate to the login activity
            Log.d("MainActivity", "onResume: User not signed in, navigating to login activity");
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        } else {
            Log.d("MainActivity", "onResume: User is already signed in");
            // If the user is already signed in, you can proceed with your app logic
        }
    }

    public void logout(View view) {
        logoutMenu(MainActivity.this);
    }

    private void logoutMenu(MainActivity mainActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity, R.style.AlertDialogTheme);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to Logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AuthManager.setLoggedIn(mainActivity, false);
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(mainActivity, Login.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("MainActivity", "No clicked");
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void saveLoginState(boolean isLoggedIn) {
        // Save the login state using SharedPreferences
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        // Check if there are fragments in the back stack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            super.onBackPressed(); // If there are fragments, allow normal back behavior
        } else {
            // If no fragments are in the back stack, show exit confirmation dialog
            showExitConfirmationDialog();
        }
    }

    private void showExitConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit App");
        builder.setMessage("Are you sure you want to exit the app?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Finish all activities in the task and exit the app
                finishAffinity();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Dismiss the dialog and do nothing
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void openFragment (Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }
}
