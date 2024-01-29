package com.example.readysilience;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.example.readysilience.AdapterCenters;
import com.example.readysilience.AdapterFeatured;
import com.example.readysilience.AdapterUpdates;
import com.example.readysilience.DataCenter;
import com.example.readysilience.DataFeatured;
import com.example.readysilience.DataUpdates;
import com.example.readysilience.R;

public class HomeFrag extends Fragment {

    RecyclerView recyclerView;

    ViewPager viewPager;

    ArrayList<DataCenter> dataCenterList = new ArrayList<>();

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //ANNOUNCEMENTS
        ImageSlider imageSlider = view.findViewById(R.id.announcement_slider);
        List<SlideModel> banners = new ArrayList<>();
        banners.add(
                new SlideModel("https://scontent.fmnl24-1.fna.fbcdn.net/v/t39.30808-6/417446475_1089658279057009_2236852168160907753_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=J27eja4OtegAX8E6SpT&_nc_ht=scontent.fmnl24-1.fna&oh=00_AfA31S8X7K8TXY0HU7aDbv-6k84jJv8MdW_OTIVdGRut3A&oe=65B2760C", "Kalinisan",ScaleTypes.FIT));
        banners.add(
                new SlideModel("https://www.deped.gov.ph/wp-content/uploads/2021/08/be2021-web-banner-04.png", "Bayanihan Para sa Paaralan",ScaleTypes.FIT));
        banners.add(
                new SlideModel("https://connect-assets.prosple.com/cdn/ff/9mTtj6yfOvnjiHzh2tPaA5Qv8WTh7BSzaMNqi7CR-HM/1633490570/public/2021-10/banner-department-of-health-philippines-1786x642-2021.png", "RESBAKUNA",ScaleTypes.FIT));
        banners.add(
                new SlideModel("https://ched.gov.ph/wp-content/uploads/CHED-Web-Banner_34th-NSM_2048x966_20230928-1024x483.jpg", "Healthy Philippines",ScaleTypes.FIT));
        banners.add(
                new SlideModel("https://scontent.fmnl24-1.fna.fbcdn.net/v/t39.30808-6/415085256_1089658305723673_3713529331527036240_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=_mrEsvk5cYEAX87kgNi&_nc_ht=scontent.fmnl24-1.fna&oh=00_AfCN-WDzS9H8WVgdzaKHvumJDhHskm74BzCCno20OEV4mA&oe=65B1FAB3", "Kalinisan Day!",ScaleTypes.FIT));

        imageSlider.setImageList(banners, ScaleTypes.FIT);
        imageSlider.setSlideAnimation(AnimationTypes.TOSS);



//        UPDATES
        recyclerView = view.findViewById(R.id.updatesRecyclerView);
        List<DataUpdates> updateDataList = new ArrayList<>();
        updateDataList.add(new DataUpdates("https://t3.ftcdn.net/jpg/03/27/55/60/360_F_327556002_99c7QmZmwocLwF7ywQ68ChZaBry1DbtD.jpg", "Live Update: Bagyong Yolanda towards Samar"));
        updateDataList.add(new DataUpdates("https://media.gettyimages.com/id/1311148884/vector/abstract-globe-background.jpg?s=612x612&w=gi&k=20&c=G5uPfn2VTF3aXCr76pn1T7oWE-aHVQ0rAYMl_MK2OvM=", "Breaking News: Japan Earthquake"));
        recyclerView.setAdapter(new AdapterUpdates(getContext(), updateDataList));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        //NEARBY CENTERS
        viewPager = view.findViewById(R.id.viewPager);

        dataCenterList.add(new DataCenter("https://scontent.fmnl24-1.fna.fbcdn.net/v/t1.6435-9/56629322_2586522344709165_9170986385169973248_n.png?_nc_cat=101&ccb=1-7&_nc_sid=7a1959&_nc_ohc=s8PKBaXt_NcAX83E01N&_nc_ht=scontent.fmnl24-1.fna&oh=00_AfA3Q1QXSC2fcUM-nRn1vk6WzafiwjTKNjJdSNUuCj_miQ&oe=65D46926", "C.P Reyes Hospital"));
        dataCenterList.add(new DataCenter("https://scontent.fmnl24-1.fna.fbcdn.net/v/t39.30808-6/327295511_1377913956284459_2414036593808725653_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=efb6e6&_nc_ohc=MfEe1hagHWcAX8SplWN&_nc_ht=scontent.fmnl24-1.fna&oh=00_AfDcybo4wtun9NnFL_2HqvGOCcx5QyK8gG5pBR9_BFN3bw&oe=65B1055F", "Cabrini Medical Center"));
        dataCenterList.add(new DataCenter("https://th.bing.com/th/id/R.94adcbb2ceaf4d112716a87ee6f7b800?rik=K0dBzhDwP5r3DA&riu=http%3a%2f%2fphotos.wikimapia.org%2fp%2f00%2f00%2f83%2f47%2f75_big.jpg&ehk=qyN0J6a9rauCJVojyHfVoE3cSEex1%2fMFpPFnNyhozKc%3d&risl=&pid=ImgRaw&r=0&sres=1&sresct=1", "DMMC"));
        dataCenterList.add(new DataCenter("https://th.bing.com/th/id/OIP.l9_QzBq2TczSCEKKpNVvzwAAAA?rs=1&pid=ImgDetMain", "Santo Tomas General Hospital"));
        dataCenterList.add(new DataCenter("https://static.seriousmd.com/profile_pictures/clinic_11996_5c32b016-a591-4c18-9ed4-0071477a26ef.jpg", "Community General Hospital"));

        int initialPosition = Integer.MAX_VALUE / 2;
        viewPager.setAdapter(new AdapterCenters(getContext(), dataCenterList));
        viewPager.setPadding(100, 0, 0, 0);
        viewPager.setCurrentItem(initialPosition);

        //FEATURED
            recyclerView = view.findViewById(R.id.featuredRecyclerView);
            List<DataFeatured> featuredDataList = new ArrayList<>();
            featuredDataList.add(new DataFeatured("https://staffskillstraining.co.uk/wp-content/uploads/2019/08/Emergency-Procedures-1024x530.png", "Why disaster preparedness is important"));
            featuredDataList.add(new DataFeatured("https://www.valchoice.com/wp-content/uploads/2020/08/31497925_s_National_Preparedness_month_tiny-e1597791983919.jpg", "Does Preparedness Matter?"));
            featuredDataList.add(new DataFeatured("https://i.ytimg.com/vi/pXkOscAY8zk/maxresdefault.jpg", "7 Reasons Why Disaster Management Is Important"));
            recyclerView.setAdapter(new AdapterFeatured(getContext(), featuredDataList));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}