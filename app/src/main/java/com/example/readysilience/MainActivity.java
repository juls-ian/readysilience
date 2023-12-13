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
import android.widget.Toast;
import com.example.readysilience.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Vars
    FloatingActionButton floatButton;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ChipNavigationBar chipNavigationBar;
    ActivityMainBinding binding;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        //IDs
//        floatButton = findViewById(R.id.fab);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.nav_view);
//        chipNavigationBar = findViewById(R.id.chip_navbar);
        frameLayout = findViewById(R.id.frame_layout);




        //Drawer Nav
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFrag()).commit();
            navigationView.setCheckedItem(R.id.drawer_home);
        }
        //Hamburger Color
        toggle.getDrawerArrowDrawable().setColor(ContextCompat.getColor(this, R.color.blue));


        //Actionbar No title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                item.setChecked(true);
                Toast.makeText(MainActivity.this, "Upload video is clicked", Toast.LENGTH_SHORT).show();

                switch (id) {
                    case R.id.drawer_home:
                        switchFrag(new HomeFrag());
                        break;

                    case R.id.drawer_firstaid:
                        switchFrag(new FirstAidFrag());
                        break;

                    case R.id.drawer_supplies:
                        switchFrag(new EmergencySupplyFrag());
                        break;

                    case R.id.drawer_checklist:
                        switchFrag(new ChecklistFrag());
                        break;

                    case R.id.drawer_feedback:
                        switchFrag(new FeedbackFrag());
                        break;

                    default:
                        return false;
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        //Chip Navbar Frag Switching

//        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(int id) {
//                Fragment selectedFragment = null;
//
//                switch (id) {
//                    case R.id.home:
//                        selectedFragment = new HomeFrag();
//                        break;
//
//                    case R.id.info_center:
//                        selectedFragment = new InfoCenterFrag();
//                        break;
//
//                    case R.id.evacuation:
//                        selectedFragment = new EvacCenterFrag();
//                        break;
//
//                    case R.id.hotlines:
//                        selectedFragment = new HotlinesFrag();
//                        break;
//                }
//
//                if (selectedFragment != null) {
//                    switchFrag(selectedFragment);
//                }
//            }
//        });




//        floatButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showBottomDialog();
//                Log.d("NavigationDrawer", "Item selected: " );
//            }
//        });

    }


    private void switchFrag(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commitNow();
    }


//    private void showBottomDialog() {
//
//
//        Dialog dialog = new Dialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.bottom_sheet_layout);
//
//        LinearLayout videoLayout = dialog.findViewById(R.id.layoutVideo);
//        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);
//
//        videoLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//                Toast.makeText(MainActivity.this, "Upload video is clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//
//        dialog.show();
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//        dialog.getWindow().setGravity(Gravity.BOTTOM);
//    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.drawer_firstaid:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FirstAidFrag()).commit();
                break;

            case R.id.drawer_checklist:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChecklistFrag()).commit();
                break;

            case R.id.drawer_supplies:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EmergencySupplyFrag()).commit();
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
}

