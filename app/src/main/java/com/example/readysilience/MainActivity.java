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


import android.app.Dialog;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.readysilience.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        floatButton = findViewById(R.id.fab);

        //Toolbar + Drawer Nav
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

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
    private void showBottomDialog() {


        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_layout);

        LinearLayout videoLayout = dialog.findViewById(R.id.layoutVideo);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        videoLayout.setOnClickListener(new View.OnClickListener() {
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
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ChecklistFrag()).commit();
                break;

            case R.id.drawer_feedback:
                Intent feedbackIntent = new Intent(MainActivity.this, Feedback.class);
                startActivity(feedbackIntent);
                finish();
                break;


            case R.id.drawer_logout:
                Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
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

