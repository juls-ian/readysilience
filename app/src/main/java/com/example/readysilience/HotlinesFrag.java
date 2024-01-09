package com.example.readysilience;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HotlinesFrag extends Fragment {

    TextView staAnaFB;

    public HotlinesFrag() {
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
        View view = inflater.inflate(R.layout.fragment_hotlines, container, false);

        //LINKS
        staAnaFB = view.findViewById(R.id.sta_ana_fb);
        staAnaFB.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }

}