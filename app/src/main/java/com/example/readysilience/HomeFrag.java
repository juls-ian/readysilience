package com.example.readysilience;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.rd.PageIndicatorView;

public class HomeFrag extends Fragment {


    RecyclerView recyclerView;

    ViewPager viewPager;

    ArrayList<DataCenter> dataCenterList = new ArrayList<>();




    public HomeFrag() {
        // Required empty public constructor

    }


        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //ANNOUNCEMENTS
        ImageSlider imageSlider = view.findViewById(R.id.announcement_slider);
        List<SlideModel> banners = new ArrayList<>();
        banners.add(
                new SlideModel("https://depeddasma.edu.ph/wp-content/uploads/2023/06/Web-Banner.png", "Desc1",ScaleTypes.FIT));
        banners.add(
                new SlideModel("https://www.deped.gov.ph/wp-content/uploads/2021/08/be2021-web-banner-04.png", "Desc2",ScaleTypes.FIT));
        banners.add(
                new SlideModel("https://connect-assets.prosple.com/cdn/ff/9mTtj6yfOvnjiHzh2tPaA5Qv8WTh7BSzaMNqi7CR-HM/1633490570/public/2021-10/banner-department-of-health-philippines-1786x642-2021.png", "Desc3",ScaleTypes.FIT));
        banners.add(
                new SlideModel("https://ched.gov.ph/wp-content/uploads/CHED-Web-Banner_34th-NSM_2048x966_20230928-1024x483.jpg", "Desc4",ScaleTypes.FIT));
        banners.add(
                new SlideModel("https://ecommerce.dti.gov.ph/wp-content/uploads/2023/05/Microsite-Banner-ebayad-1024x410.png", "Desc5",ScaleTypes.FIT));

        imageSlider.setImageList(banners, ScaleTypes.FIT);
        imageSlider.setSlideAnimation(AnimationTypes.TOSS);


        //UPDATES
        recyclerView = view.findViewById(R.id.updatesRecyclerView);
        List<DataUpdates> updateDataList = new ArrayList<>();
        updateDataList.add(new DataUpdates("https://t3.ftcdn.net/jpg/03/27/55/60/360_F_327556002_99c7QmZmwocLwF7ywQ68ChZaBry1DbtD.jpg", "Live Update: Bagyong Yolanda towards Samar"));
        updateDataList.add(new DataUpdates("https://media.gettyimages.com/id/1311148884/vector/abstract-globe-background.jpg?s=612x612&w=gi&k=20&c=G5uPfn2VTF3aXCr76pn1T7oWE-aHVQ0rAYMl_MK2OvM=", "Breaking News: Japan Earthquake"));
        recyclerView.setAdapter(new AdapterUpdates(getContext(), updateDataList));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        //NEARBY CENTERS
        viewPager = view.findViewById(R.id.viewPager);

        dataCenterList.add(new DataCenter("https://thumbs.dreamstime.com/b/hospital-building-modern-parking-lot-59693686.jpg", "Center 1"));
        dataCenterList.add(new DataCenter("https://images.unsplash.com/photo-1519494080410-f9aa76cb4283?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8aG9zcGl0YWwlMjByb29tfGVufDB8fDB8fHww", "Center 2"));
        dataCenterList.add(new DataCenter("https://thumbs.dreamstime.com/b/doctors-hospital-corridor-nurse-pushing-gurney-stretcher-bed-male-senior-female-patient-32154012.jpg", "Center 3"));

        int initialPosition = Integer.MAX_VALUE / 2;
        viewPager.setAdapter(new AdapterCenters(getContext(), dataCenterList));
        viewPager.setPadding(50, 0, 50, 0);
        viewPager.setCurrentItem(initialPosition);



        //FEATURED
            recyclerView = view.findViewById(R.id.featuredRecyclerView);
            List<DataFeatured> featuredDataList = new ArrayList<>();
            featuredDataList.add(new DataFeatured("https://www.japantimes.co.jp/uploads/imported_images/uploads/2019/07/f-quakepredict-a-20190708.jpg", "Seismologists on earthquake preparedness"));
            featuredDataList.add(new DataFeatured("https://images.caxton.co.za/wp-content/uploads/sites/32/2021/07/fire.jpg", "Fire 101: Tips for prevention and survival"));
            recyclerView.setAdapter(new AdapterFeatured(getContext(), featuredDataList));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));





        return view;
    }
}