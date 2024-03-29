package com.example.readysilience;

import android.content.Intent;
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
import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class FirstAidFrag extends Fragment {


    ViewPager viewPagerVid;
    RecyclerView recyclerViewInjury;
    GridView gridView;
    ArrayList<DataFirstAidVid> vidList = new ArrayList<>();
    ArrayList<DataInjuries> injuriesList = new ArrayList<>();
    ArrayList<Integer> itemPics;
    ArrayList<String> itemNames;
    ArrayList<String> itemDescs;

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
//        loadVideosForViewPager();

        viewPagerVid = view.findViewById(R.id.vids_viewpager);
        vidList.add(new DataFirstAidVid(R.drawable.firstaid_sling, "Sling Basics", "3:00", R.drawable.logo_stjohn_ambulance));
        vidList.add(new DataFirstAidVid(R.drawable.firstaid_cuts, "Cuts and Grazes", "1:29", R.drawable.logo_stjohn_ambulance));
        vidList.add(new DataFirstAidVid(R.drawable.firstaid_primarysurvey, "Primary Survey", "4:02", R.drawable.logo_stjohn_ambulance));

        int initialPosition = vidList.size() / 2;
        viewPagerVid.setAdapter(new AdapterVid(getContext(), vidList));
        viewPagerVid.setPadding(50, 0, 50, 0);
        AdapterVid adapterVid = new AdapterVid(getContext(), vidList);
        viewPagerVid.setAdapter(adapterVid);
        adapterVid.setOnItemClickListener(new AdapterVid.OnItemClickListener() {
            @Override
            public void onItemClick(DataFirstAidVid videoData) {
                openVidResources(videoData);
            }
        });

        //INJURIES
        recyclerViewInjury = view.findViewById(R.id.injuries_recycler_view);
        injuriesList.add(new DataInjuries(R.drawable.injury_choking, "Choking", R.string.choking_desc, "Medical Emergency"));
        injuriesList.add(new DataInjuries(R.drawable.injury_burn, "Thermal Burn", R.string.burn_desc, "Burn"));
//        injuriesList.add(new DataInjuries(R.drawable.injury_bruise, "Bruise", R.string.bruise_desc, "Traumatic"));
//        injuriesList.add(new DataInjuries(R.drawable.injury_fracture, "Fracture", R.string.fracture_desc, "Traumatic"));

        recyclerViewInjury.setAdapter(new AdapterInjuries(getContext(), injuriesList));
        recyclerViewInjury.setLayoutManager(new LinearLayoutManager(getContext()));

        AdapterInjuries adapterInjuries = new AdapterInjuries(getContext(), injuriesList);
        adapterInjuries.setOnItemClickListener(new AdapterInjuries.OnItemClickListener() {
            @Override
            public void onItemClick(DataInjuries injuriesData) {
                // Handle the item click, e.g., open a new activity
                openNewActivity(injuriesData);
            }
        });
        recyclerViewInjury.setAdapter(adapterInjuries);

        //FIRST AID ITEMS

        gridView = view.findViewById(R.id.gridView);
        itemPics = new ArrayList<>();
        itemNames = new ArrayList<>();
        itemDescs = new ArrayList<>();

        itemPics.addAll(Arrays.asList(R.drawable.aid_adhesive_bandages, R.drawable.aid_antibiotic_ointment, R.drawable.aid_antiseptic_wipe_packets,
                R.drawable.aid_aspirin, R.drawable.aid_cloth_tape, R.drawable.aid_dressings, R.drawable.aid_hydrocortisone,
                R.drawable.aid_instant_cold_compress));
        itemNames.addAll(Arrays.asList("Adhesive Bandages", "Antibiotic", "Antiseptic Wipe", "Aspirin", "Adhesive Cloth Tope",
                "Absorbent Dressings", "Hydrocortisone", "Instant Cold Pack"));
        itemDescs.addAll(Arrays.asList(
                "Adhesive bandages, commonly known as band-aids, are small strips with a sterile pad and adhesive backing. They protect minor cuts and wounds by covering them, preventing dirt and bacteria from entering and aiding in the healing process.",
                "Antibiotic ointments are topical medications applied to wounds or cuts. They contain antibiotics to prevent or treat infections, promoting a cleaner healing process.",
                "Antiseptics are substances used to disinfect and clean wounds, preventing infection. They are applied topically to kill or inhibit the growth of bacteria, viruses, and other microorganisms on the skin's surface.",
                "Aspirin is a medication that belongs to the group of nonsteroidal anti-inflammatory drugs (NSAIDs). It is commonly used to relieve pain, reduce inflammation, and lower fever. Additionally, aspirin has blood-thinning properties, making it useful in preventing heart attacks and strokes.",
                "Adhesive cloth tape is a durable tape with a fabric backing and adhesive. It's often used in medical settings to secure bandages and has various applications in crafting and repairs due to its strength and flexibility.",
                "Absorbent dressings are wound coverings that soak up fluids to promote healing. They have layers, including an absorbent core, and are used for wounds with moderate to heavy exudate.",
                "A topical corticosteroid used to alleviate itching, redness, and inflammation associated with various skin conditions, such as eczema, dermatitis, or insect bites. It works by reducing swelling and suppressing the body's immune response at the site of application, providing relief for skin-related discomfort.",
                "An instant cold pack is a one-time-use device that, when activated, quickly produces cold for immediate pain relief and swelling reduction, commonly used in first aid."));

        AdapterGridItems adapterGridItems = new AdapterGridItems(getContext(), itemPics, itemNames, itemDescs);
        gridView.setAdapter(adapterGridItems);

        return view;
    }

//    private void loadVideosForViewPager() {
//        if (!isAdded()) {
//            // Fragment is not attached, avoid unnecessary operations
//            return;
//        }
//
//        FirebaseStorage.getInstance().getReference().child("videos").listAll()
//                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
//                    @Override
//                    public void onSuccess(ListResult listResult) {
//                        ArrayList<Video> videoList = new ArrayList<>();
//
//                        for (StorageReference storageReference : listResult.getItems()) {
//                            Video video = new Video();
//                            video.setTitle(storageReference.getName());
//
//                            storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Uri> task) {
//                                    if (isAdded() && task.isSuccessful()) {
//                                        String url = task.getResult().toString();
//                                        video.setUrl(url);
//                                        videoList.add(video);
//                                    }
//
//                                    // Set up the ViewPager adapter
//                                    if (isAdded() && viewPagerVid != null) {
//                                        AdapterVid adapterVid = new AdapterVid(requireContext(), videoList);
//                                        viewPagerVid.setAdapter(adapterVid);
//                                        viewPagerVid.setPadding(50, 0, 50, 0);
//                                    }
//                                }
//                            });
//                        }
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        if (isAdded()) {
//                            Toast.makeText(requireContext(), "Failed to retrieve videos", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }

    private void openNewActivity(DataInjuries injuriesData) {
        Intent intent = null;

        // Determine which activity to open based on the item clicked
        switch (injuriesData.getInjuryName()) {
            case "Choking":
                intent = new Intent(getContext(), video_choking.class);
                break;
            case "Thermal Burn":
                intent = new Intent(getContext(), video_thermal_burn.class);
                break;
            // Add more cases for other items
            default:
        }


        // Start the new activity
        startActivity(intent);
    }

    private void openVidResources(DataFirstAidVid videoData) {
        Intent intent = null;

        // Determine which activity to open based on the item clicked
        switch (videoData.getVidTitle()) {
            case "Sling Basics":
                intent = new Intent(getContext(), video_sling.class);
                break;
            case "Cuts and Grazes":
                intent = new Intent(getContext(), video_cuts.class);
                break;
            case "Primary Survey":
                intent = new Intent(getContext(), video_primary_survey.class);
                break;
            // Add more cases for other items
            default:
        }


        // Start the new activity
        startActivity(intent);
    }

}