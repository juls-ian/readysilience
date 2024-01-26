package com.example.readysilience;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;


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
        viewPagerVid = view.findViewById(R.id.vids_viewpager);
        loadVideosForViewPager();

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

    private void loadVideosForViewPager() {
        FirebaseStorage.getInstance().getReference().child("videos").listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        ArrayList<Video> videoList = new ArrayList<>();

                        for (StorageReference storageReference : listResult.getItems()) {
                            Video video = new Video();
                            video.setTitle(storageReference.getName());

                            storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        String url = task.getResult().toString();
                                        video.setUrl(url);
                                        videoList.add(video);
                                    }

                                    // Set up the ViewPager adapter
                                    AdapterVid adapterVid = new AdapterVid(requireContext(), videoList);
                                    if (viewPagerVid != null) {
                                        viewPagerVid.setAdapter(adapterVid);
                                        viewPagerVid.setPadding(50, 0, 50, 0);
                                    }
                                }
                            });
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(requireContext(), "Failed to retrieve videos", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}