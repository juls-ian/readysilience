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
            return new Object(); // Handle empty list to prevent IndexOutOfBoundsException
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

                                // Check if the user has already checked in
                                evacueesRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            // User has already checked in
                                            String previousCenterName = dataSnapshot.child("centerName").getValue(String.class);

                                            // Check if the selected evacuation center is the same as the previous one
                                            if (centerName.equals(previousCenterName)) {
                                                showToast("You have already chosen " + centerName + " as your evacuation center");
                                            } else {
                                                // Update the evacuation center and show a Toast message
                                                dataSnapshot.getRef().child("centerName").setValue(centerName);
                                                showToast("Evacuation center updated to " + centerName);
                                            }
                                        } else {
                                            // User hasn't checked in yet, proceed to check in
                                            Evacuee evacuee = new Evacuee(firstName, lastName, houseNumber, purok, phoneNumber, centerName, age);
                                            evacueesRef.setValue(evacuee);

                                            // Show a Toast message after data is stored
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

        Glide.with(context).asBitmap().load(dataEvacCenters.getEvaCenterPic()).into(imageView);
        textView.setText(dataEvacCenters.getCenterName());
        textView1.setText(dataEvacCenters.getCenterLocation());
        textView2.setText(dataEvacCenters.getWaterSupply());
        textView3.setText(dataEvacCenters.getFoodSupply());
        textView4.setText(dataEvacCenters.getBlanketSupply());
        textView5.setText(dataEvacCenters.getMedicSupply());
        textView6.setText(dataEvacCenters.getCenterAvailability());


        setIconBackground(textView2.getText().toString(), waterBottleIcon);
        setIconBackground(textView3.getText().toString(), cannedFoodIcon);
        setIconBackground(textView4.getText().toString(), blanketIcon);
        setIconBackground(textView5.getText().toString(), medicIcon);

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

    private void setIconBackground(String status, ImageView waterBottleIcon) {
        if (status.equals("Equipped")) {
            waterBottleIcon.setBackgroundResource(R.drawable.bg_3needs_ready);
        } else if (status.equals("Need Donation")) {
            waterBottleIcon.setBackgroundResource(R.drawable.bg_3needs_not);
        }

    }

    private void setBackgroundColorBasedOnAvailability(String availabilityStatus, TextView centerAvailabilityTextView) {
        int backgroundDrawable;
        if ("Available".equals(availabilityStatus)) {
            backgroundDrawable = drawable.bg_evac_available;
        } else {
            backgroundDrawable = drawable.bg_evac_unavailable;
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
