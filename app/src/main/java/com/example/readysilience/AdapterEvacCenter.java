package com.example.readysilience;

import static com.example.readysilience.R.*;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.readysilience.DataEvacCenters;
import com.example.readysilience.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.widget.Toast;



import java.util.ArrayList;

public class AdapterEvacCenter extends PagerAdapter {

    Context context;
    ArrayList<DataEvacCenters> evacCentersList;

    private OnItemClickListener onItemClickListener;




    public AdapterEvacCenter(Context context, ArrayList<DataEvacCenters> evacCentersList) {
        this.context = context;
        this.evacCentersList = evacCentersList;

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getCount() {
        return evacCentersList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if (evacCentersList.size() == 0) {
            return new Object();
        }

        int realPosition = position % evacCentersList.size();
        DataEvacCenters dataEvacCenters = evacCentersList.get(realPosition);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_evac_centers, null);
        ImageView imageView = view.findViewById(id.evacuation_center_pic);
        TextView textView = view.findViewById(id.evac_center_name);
        TextView textView1 = view.findViewById(id.evac_location);
        TextView textView2 = view.findViewById(id.needs_water);
        TextView textView3 = view.findViewById(id.needs_can);
        TextView textView4 = view.findViewById(id.needs_blanket);
        TextView textView5 = view.findViewById(id.needs_medic);
        TextView textView6 = view.findViewById(id.center_availability);
        ImageView waterBottleIcon = view.findViewById(id.waterbottle_icon);
        ImageView cannedFoodIcon = view.findViewById(id.can_icon);
        ImageView blanketIcon = view.findViewById(id.blanket_icon);
        ImageView medicIcon = view.findViewById(id.medic_icon);

        ImageButton checkInButton = view.findViewById(R.id.checkIn);

        checkInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser != null) {
                    String userId = currentUser.getUid();

                    DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                    usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                String firstName = dataSnapshot.child("firstName").getValue(String.class);
                                String lastName = dataSnapshot.child("lastName").getValue(String.class);
                                String houseNumber = dataSnapshot.child("houseNumber").getValue(String.class);
                                String purok = dataSnapshot.child("purok").getValue(String.class);
                                String phoneNumber = dataSnapshot.child("phoneNumber").getValue(String.class);
                                String age = dataSnapshot.child("age").getValue(String.class);

                                DataEvacCenters evacCenter = evacCentersList.get(realPosition);
                                String centerName = evacCenter.getCenterName();

                                DatabaseReference evacueesRef = FirebaseDatabase.getInstance().getReference().child("Evacuees").child(userId);

                                evacueesRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            String previousCenterName = dataSnapshot.child("centerName").getValue(String.class);

                                            if (centerName.equals(previousCenterName)) {
                                                showToast("You have already chosen " + centerName + " as your evacuation center");
                                            } else {
                                                dataSnapshot.getRef().child("centerName").setValue(centerName);
                                                showToast("Evacuation center updated to " + centerName);
                                            }
                                        } else {
                                            Evacuee evacuee = new Evacuee(firstName, lastName, houseNumber, purok, phoneNumber, centerName, age);
                                            evacueesRef.setValue(evacuee);

                                            showToast("Safety registered at " + centerName);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        // Handle onCancelled event
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Handle onCancelled event
                        }
                    });
                }
            }
        });

        DatabaseReference evacDatabaseRef = null;

        String centerName = dataEvacCenters.getCenterName();
        switch (centerName) {
            case "Sta Ana Elementary School":
                evacDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Sta Ana Elementary School");
                break;
            case "Sta Ana Basketball Court":
                evacDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Sta Ana Basketball Court");
                break;
            case "Sto Tomas Evacuation Center":
                evacDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Sto Tomas Evacuation Center");
                break;
            default:
                Log.e("AdapterEvacCenter", "Unknown centerName: " + centerName);
                break;
        }

        if (evacDatabaseRef != null) {
            evacDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String waterStatus = dataSnapshot.child("Water").getValue(String.class);
                        String foodStatus = dataSnapshot.child("Foods").getValue(String.class);
                        String garmentStatus = dataSnapshot.child("Garments").getValue(String.class);
                        String medicStatus = dataSnapshot.child("Medicine").getValue(String.class);
                        String availabilityStatus = dataSnapshot.child("Availability").getValue(String.class);

                        setIconBackground(waterStatus, waterBottleIcon, textView2);
                        setIconBackground(foodStatus, cannedFoodIcon, textView3);
                        setIconBackground(garmentStatus, blanketIcon, textView4);
                        setIconBackground(medicStatus, medicIcon, textView5);
                        setBackgroundColorBasedOnAvailability(availabilityStatus, textView6);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle onCancelled event
                }
            });
        } else {
            Log.e("AdapterEvacCenter", "evacDatabaseRef is null for centerName: " + centerName);
        }

        Glide.with(context).asBitmap().load(dataEvacCenters.getEvaCenterPic()).into(imageView);
        textView.setText(dataEvacCenters.getCenterName());
        textView1.setText(dataEvacCenters.getCenterLocation());
        textView2.setText(dataEvacCenters.getWaterSupply());
        textView3.setText(dataEvacCenters.getFoodSupply());
        textView4.setText(dataEvacCenters.getBlanketSupply());
        textView5.setText(dataEvacCenters.getMedicSupply());
        textView6.setText(dataEvacCenters.getCenterAvailability());

        setBackgroundColorBasedOnAvailability(dataEvacCenters.getCenterAvailability(), textView6);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the corresponding DataEvacCenters object
                int realPosition = position % evacCentersList.size();
                DataEvacCenters dataEvacCenters = evacCentersList.get(realPosition);

                // Open Google Maps with the address
                openGoogleMaps(dataEvacCenters.getCenterName(), dataEvacCenters.getLatitude(), dataEvacCenters.getLongitude());
            }
        });

        container.addView(view, 0);
        return view;
    }

    private void openGoogleMaps(String locationName, double latitude, double longitude) {
        // Construct a Google Maps URI with the precise location
        String uri = "https://www.google.com/maps/search/?api=1" +
                "&query=" + Uri.encode(locationName) +
                "&ll=" + latitude + "," + longitude;

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
        context.startActivity(intent);


    }
    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private void setIconBackground(String status, ImageView imageView, TextView textView) {
        if (status != null) {
            if (status.equals("Equipped")) {
                imageView.setBackgroundResource(R.drawable.bg_3needs_ready);
                if (textView != null) {
                    textView.setText("Equipped");
                }
            } else if (status.equals("Need Donation")) {
                imageView.setBackgroundResource(R.drawable.bg_3needs_not);
                if (textView != null) {
                    textView.setText("Need Donation");
                }
            }
        }
    }

    private void setBackgroundColorBasedOnAvailability(String availabilityStatus, TextView centerAvailabilityTextView) {
        int backgroundDrawable;

        switch (availabilityStatus) {
            case "Available":
                backgroundDrawable = R.drawable.bg_evac_available;
                centerAvailabilityTextView.setText("Available");
                break;
            case "Unavailable":
                backgroundDrawable = R.drawable.bg_evac_unavailable;
                centerAvailabilityTextView.setText("Unavailable");
                break;
            case "Crowded":
                backgroundDrawable = R.drawable.bg_evac_crowded;
                centerAvailabilityTextView.setText("Crowded");
                break;
            default:
                backgroundDrawable = R.drawable.bg_evac_unavailable;
                centerAvailabilityTextView.setText("Unknown");
                Log.e("AdapterEvacCenter", "Unknown availabilityStatus: " + availabilityStatus);
                break;
        }

        centerAvailabilityTextView.setBackgroundResource(backgroundDrawable);
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public float getPageWidth(int position) {
        return 0.8f;
    }

    public interface OnItemClickListener {
        void onItemClick(String address);
    }



    private static class Evacuee {
        private String firstName;
        private String lastName;
        private String houseNumber;
        private String purok;
        private String phoneNumber;
        private String centerName;
        private String age;


        public Evacuee() {

        }

        public Evacuee(String firstName, String lastName, String houseNumber, String purok, String phoneNumber, String centerName, String age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.houseNumber = houseNumber;
            this.purok = purok;
            this.phoneNumber = phoneNumber;
            this.centerName = centerName;
            this.age = age;
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

        public String getHouseNumber() {
            return houseNumber;
        }

        public void setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
        }

        public String getPurok() {
            return purok;
        }

        public void setPurok(String purok) {
            this.purok = purok;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getCenterName() {
            return centerName;
        }

        public void setCenterName(String centerName) {
            this.centerName = centerName;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
