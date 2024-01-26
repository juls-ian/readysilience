package com.example.readysilience;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.readysilience.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

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
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        floatButton = findViewById(R.id.fab);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            finish();
            return;
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

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Hamburger Color
        toggle.getDrawerArrowDrawable().setColor(ContextCompat.getColor(this, R.color.blue));
        //Actionbar No title


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

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


    //SOS REPORT
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

                // Create a Report object
                Report report = new Report(selectedIncidentType, description);

                // Push the report to the database
                reportsReference.push().setValue(report)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Report sent successfully", Toast.LENGTH_SHORT).show();
                                    Log.d("MainActivity", "Report sent successfully");
                                } else {
                                    Toast.makeText(MainActivity.this, "Failed to send report", Toast.LENGTH_SHORT).show();
                                    Log.e("MainActivity", "Failed to send report", task.getException());
                                }

                                // Dismiss the dialog
                                dialog.dismiss();
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

    public static class Report {
        private String incidentType;
        private String description;

        public Report() {
        }

        public Report(String incidentType, String description) {
            this.incidentType = incidentType;
            this.description = description;
        }

        public String getIncidentType() {
            return incidentType;
        }

        public String getDescription() {
            return description;
        }

        public void setIncidentType(String incidentType) {
            this.incidentType = incidentType;
        }

        public void setDescription(String description) {
            this.description = description;
        }
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
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ChecklistFrag()).commit();
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

    private void logoutMenu(MainActivity mainActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
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



    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
    private void openFragment (Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }


}

