package com.example.readysilience;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TermsFrag extends Fragment {

    public TermsFrag() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_terms, container, false);

        Button acknowledgedButton = view.findViewById(R.id.acknowledged_button);
        acknowledgedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the TCActivity
                Intent intent = new Intent(requireContext(), TCActivity.class);
                startActivity(intent);
            }
        });

        // Set up the back button press handling
        handleBackButtonPress(view);

        return view;
    }

    private void handleBackButtonPress(View view) {
        // Find the fragment container view
        final View fragmentContainer = view.getRootView().findViewById(R.id.tc_layout);

        // Set up the back button press handling
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    // Handle the back button press event
                    // Remove the fragment from the back stack
                    if (getFragmentManager() != null) {
                        getFragmentManager().popBackStack();
                    }
                    return true;
                }
                return false;
            }
        });
    }
}
