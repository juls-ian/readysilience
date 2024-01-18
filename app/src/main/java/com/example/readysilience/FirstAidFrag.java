package com.example.readysilience;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.readysilience.AdapterGridItems;
import com.example.readysilience.AdapterInjuries;
import com.example.readysilience.AdapterVid;
import com.example.readysilience.DataFirstAidVid;
import com.example.readysilience.DataInjuries;
import com.example.readysilience.R;

import java.util.ArrayList;
import java.util.Arrays;


public class FirstAidFrag extends Fragment {


    ViewPager viewPagerVid;
    RecyclerView recyclerViewInjury;
    GridView gridView;
    ArrayList<DataFirstAidVid> vidList = new ArrayList<>();
    ArrayList<DataInjuries> injuriesList = new ArrayList<>();
    ArrayList<Integer> itemPics;
    ArrayList<String> itemNames;

    public FirstAidFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_first_aid, container, false);

        // VIDEO RESOURCES
        viewPagerVid = view.findViewById(R.id.vids_viewpager);
        vidList.add(new DataFirstAidVid(R.drawable.firstaid_basics, "First Aid Basic", "11:09", R.drawable.logo_trioshealth));
        vidList.add(new DataFirstAidVid(R.drawable.firstaid_sling, "Sling Basics", "3:00", R.drawable.logo_stjohn_ambulance));
        vidList.add(new DataFirstAidVid(R.drawable.firstaid_cuts, "Cuts and Gazes", "1:29", R.drawable.logo_stjohn_ambulance));
        vidList.add(new DataFirstAidVid(R.drawable.firstaid_primarysurvey, "Primary Survey", "4:02", R.drawable.logo_stjohn_ambulance));

        int initialPosition = vidList.size() / 2;
        viewPagerVid.setAdapter(new AdapterVid(getContext(), vidList));
        viewPagerVid.setPadding(50, 0, 50, 0);

        //INJURIES
        recyclerViewInjury = view.findViewById(R.id.injuries_recycler_view);
        injuriesList.add(new DataInjuries(R.drawable.injury_choking, "Choking", R.string.choking_desc, "Medical Emergency"));
        injuriesList.add(new DataInjuries(R.drawable.injury_burn, "Thermal Burn", R.string.burn_desc, "Burn"));
        injuriesList.add(new DataInjuries(R.drawable.injury_bruise, "Bruise", R.string.bruise_desc, "Traumatic"));
        injuriesList.add(new DataInjuries(R.drawable.injury_fracture, "Fracture", R.string.fracture_desc, "Traumatic"));

        recyclerViewInjury.setAdapter(new AdapterInjuries(getContext(), injuriesList));
        recyclerViewInjury.setLayoutManager(new LinearLayoutManager(getContext()));

        //FIRST AID ITEMS

        gridView = view.findViewById(R.id.gridView);
        itemPics = new ArrayList<>();
        itemNames = new ArrayList<>();

        itemPics.addAll(Arrays.asList(R.drawable.aid_adhesive_bandages, R.drawable.aid_antibiotic_ointment, R.drawable.aid_antiseptic_wipe_packets,
                R.drawable.aid_aspirin, R.drawable.aid_cloth_tape, R.drawable.aid_dressings, R.drawable.aid_hydrocortisone,
                R.drawable.aid_instant_cold_compress));
        itemNames.addAll(Arrays.asList("Adhesive Bandages", "Antibiotic", "Antiseptic Wipe", "Aspirin", "Adhesive Cloth Tope",
                "Absorbent Dressings", "Hydrocortisone", "Instant Cold Pack"));

        AdapterGridItems adapterGridItems = new AdapterGridItems(getContext(), itemPics, itemNames);
        gridView.setAdapter(adapterGridItems);

        return view;
    }
}