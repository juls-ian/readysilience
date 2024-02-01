package com.example.readysilience;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.readysilience.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment {

    SupportMapFragment supportMapFragment;
    MapView mapView;

    public MapFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = view.findViewById(R.id.evacenter_map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {

                LatLng location1 = new LatLng(14.067446613892669, 121.19462593518556); // Sta. Ana Basketball Court
                LatLng location2 = new LatLng(14.107370804876277, 121.13893671320922); // Sto. Tomas Evac Center
                LatLng location3 = new LatLng(14.072573669982877, 121.19574727683766); // Sta. Ana Elementary School

                MarkerOptions markerOptions1 = new MarkerOptions().position(location1).title("Sta. Ana Basketball Court ");
                MarkerOptions markerOptions2 = new MarkerOptions().position(location2).title("City Evacuation Center of Sto. Tomas, Batangas");
                MarkerOptions markerOptions3 = new MarkerOptions().position(location3).title("Sta. Ana Elementary School");

                googleMap.addMarker(markerOptions1);
                googleMap.addMarker(markerOptions2);
                googleMap.addMarker(markerOptions3);

                // Set the initial camera position
                LatLng initialLocation = location1;
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(initialLocation, 13));
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude + "kg" + latLng.longitude);
                        googleMap.clear();
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
                        googleMap.addMarker(markerOptions);
                    }
                });
            }
        });

        mapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Enable ScrollView's touch events
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        return view;
    }

    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}