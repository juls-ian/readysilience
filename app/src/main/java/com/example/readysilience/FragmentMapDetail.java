package com.example.readysilience;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class FragmentMapDetail extends Fragment {

    private static final String ARG_IMAGE_RESOURCE = "imageResource";

    public FragmentMapDetail() {
        // Required empty public constructor
    }


    public static FragmentMapDetail newInstance(int imageResource) {
        FragmentMapDetail fragment = new FragmentMapDetail();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE_RESOURCE, imageResource);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        PhotoView photoView = view.findViewById(R.id.enlargedImageView);

        if (getArguments() != null) {
            int imageResource = getArguments().getInt(ARG_IMAGE_RESOURCE);
            Glide.with(requireContext()).load(imageResource).into(photoView);
        }

        // You can customize the layout and add additional features as needed.
    }
}