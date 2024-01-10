package com.example.readysilience;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.text.util.Linkify;

import java.util.ArrayList;
import java.util.List;


public class HotlinesFrag extends Fragment {

    TextView staAnaFB,staAnaPhone;

    TextView stoTomasPhone1, stoTomasPhone2, stoMasWebsite, healthOfficeFB ;
    TextView healthOfficePhone, trafficOfficePhone1, trafficOfficePhone2;

    RecyclerView recyclerViewDirectories;



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


        //FEATURED
        recyclerViewDirectories = view.findViewById(R.id.nationalDirectoryRecycler);
        List<DataNatDirectories> natDirectoriesDataList = new ArrayList<>();
        natDirectoriesDataList.add(new DataNatDirectories("https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Logo_Philippine_Red_Cross.svg/1024px-Logo_Philippine_Red_Cross.svg.png",
                "PH Red Cross", "(02) 527-0000", "(02) 8790-2300"));
        natDirectoriesDataList.add(new DataNatDirectories("https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Philippine_National_Police_seal.svg/1466px-Philippine_National_Police_seal.svg.png",
                "Philippine National Police", "043-778-1610", "16677"));
        natDirectoriesDataList.add(new DataNatDirectories("https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Department_of_Health_%28DOH%29_PHL.svg/2048px-Department_of_Health_%28DOH%29_PHL.svg.png",
                "Department of Health", "2225-2227", "1555"));
        recyclerViewDirectories.setAdapter(new AdapterNatDirectories(getContext(), natDirectoriesDataList));
        recyclerViewDirectories.setLayoutManager(new LinearLayoutManager(getContext()));



        //LINKS
        staAnaFB = view.findViewById(R.id.sta_ana_fb);
        staAnaFB.setMovementMethod(LinkMovementMethod.getInstance());
        stoMasWebsite = view.findViewById(R.id.stomas_website);
        stoMasWebsite.setMovementMethod(LinkMovementMethod.getInstance());
        healthOfficeFB = view.findViewById(R.id.health_office_fb);
        healthOfficeFB.setMovementMethod(LinkMovementMethod.getInstance());




        //PHONE - STA. ANA
        staAnaPhone = view.findViewById(R.id.brgy_phone);
        staAnaPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:09285670575"));
                startActivity(intent);

            }
        });


        //PHONES - STO TOMAS
        stoTomasPhone1 = view.findViewById(R.id.stomas_phone1);
        stoTomasPhone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0437848432"));
                startActivity(intent);

            }
        });

        stoTomasPhone2 = view.findViewById(R.id.stomas_phone2);
        stoTomasPhone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:09338122581"));
                startActivity(intent);

            }
        });

        healthOfficePhone = view.findViewById(R.id.health_office_phone1);
        healthOfficePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0434066539"));
                startActivity(intent);

            }
        });

        trafficOfficePhone1 = view.findViewById(R.id.traffic_mgmt_phone1);
        trafficOfficePhone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0437846544"));
                startActivity(intent);

            }
        });

        trafficOfficePhone2 = view.findViewById(R.id.traffic_mgmt_phone2);
        trafficOfficePhone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:09392938006"));
                startActivity(intent);

            }
        });



        return view;
    }

}