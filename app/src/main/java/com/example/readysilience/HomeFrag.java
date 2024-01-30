package com.example.readysilience;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.readysilience.ExpandedViews.AnnouncementExpandedActivity;
import com.smarteist.autoimageslider.SliderView;


import org.json.JSONException;
import org.json.JSONObject;

public class HomeFrag extends Fragment {

    RecyclerView recyclerView;

    ViewPager viewPager;

    ArrayList<DataCenter> dataCenterList = new ArrayList<>();


    public HomeFrag() {
        // Required empty public constructor

    }

    RecyclerView updatesRecyclerView;




        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

            updatesRecyclerView = view.findViewById(R.id.updatesRecyclerView);
            getWeatherInfoForLocation();

            //ANNOUNCEMENTS

            SliderView sliderView = view.findViewById(R.id.announcement_slider);

            AdapterImageSlider adapterImageSlider = new AdapterImageSlider(getContext());
            sliderView.setSliderAdapter(adapterImageSlider);



        //UPDATES
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
            //DATA 1 - Image Resolution: 1429x807
            featuredDataList.add(new DataFeatured("https://pia.gov.ph/uploads/2023/05/da1949e314282c51346a6d94a5937853.jpg",
                    "Typhoon Safety Tips: What to Do Before, During, and After",
                    "Filipinos are known for being resilient in the face of a storm, literally and figuratively. Living in the Philippines—where natural disasters happen quite frequently—compels everyone to be tough and adaptable. \n" +
                    "\n" +
                    "Although Filipino resilience is legendary and admirable, typhoon preparedness can minimize its impact on lives and properties. Knowing what to do before, during, and after a typhoon will protect you, your family, your home, and your other precious assets.\n" +
                    "\n" +
                    "Aside from minding the four Ps of disaster management (predict, plan, prepare, and practice), here are other typhoon safety tips to keep your family out of harm's way.",
                    "What to Do Before a Typhoon: Things to Prepare and Plan",
                    "Prepare an Emergency Go Bag",
                    "A go bag is a simple, low-cost emergency kit that's easy to bring when you need to leave your home, office, or car to evacuate because of a typhoon, fire, earthquake, or any emergency. This is why it's also called a grab-and-go bag, lifeline kit, or emergency survival kit.\n" +
                    "\n" +
                    "The importance of an emergency go bag can't be stressed enough. Having a typhoon emergency kit will help you survive for several days until your food, electricity, and water supplies normalize after a calamity.",
                    "https://25174313.fs1.hubspotusercontent-eu1.net/hub/25174313/hubfs/Survival_pack_Go-to_bag.png?width=1080&height=1080&name=Survival_pack_Go-to_bag.png",
                    "To save on money, you can make your own typhoon preparedness kit. Simply get a backpack with enough space for food, cash, documents, toiletries, and other essentials that should last for two to three days.\n" +
                            "\n" +
                            "Particularly, here are the must-have contents of your emergency go bag, according to the Red Cross:\n" +
                            "\n" +
                            "• Ready-to-eat food (such as canned goods and chocolate bars)\n" +
                            "• Bottled water (Up to 1 gallon per person)\n" +
                            "• Face masks\n" +
                            "• Alcohol\n" +
                            "• Extra clothes, rain gear, and blankets\n" +
                            "• Cellphones and power banks\n" +
                            "• Battery-operated radio\n" +
                            "• Flashlight\n" +
                            "• Whistle\n" +
                            "• First-aid kit (for injuries and maintenance medicines)\n" +
                            "• Important documents\n" +
                            "• Extra cash (in case ATMs become offline)\n" +
                            "\n" +
                            "If you don't have much time to prepare all the items in your emergency go bag list, you can instead shop online for a lifeline kit from the Philippine Red Cross via Shopee or Lazada. It may cost you money (₱3,500 for a go bag + ₱4,000 for an optional first-aid kit backpack), but the convenience and life-saving benefits will definitely be priceless.",
                    "Secure Your Loved Ones and Properties",
                    "Here's a checklist of the things you need to do when there's an upcoming typhoon in the Philippines:\n" +
                            "\n" +
                            "• Monitor weather reports and don't ignore rainfall warning alerts you receive through text.\n" +
                            "• Ensure you have enough food and water supply.\n" +
                            "• Keep the following items handy in case of a power outage: battery-operated radio, flashlights, spare batteries, rechargeable lamps, and candles.\n" +
                            "• Check your home for anything that needs to be fixed or secured, such as a leaky roof and trees that need to be trimmed.\n" +
                            "• Park your car in a higher place (like in a mall's parking space) if you live in a low-lying area.\n" +
                            "• Secure your pets in a safe place.\n" +
                            "• Evacuate immediately and calmly—if there's an order from your local government unit or if you live near a body of water or mountainsides—to avoid flash floods and landslides. Close all windows and switch off your main power supply.\n" +
                            "• Make a plan for staying in touch with your family during a typhoon. Have a designated place for the family to gather in case some members get separated.\n" +
                            "• Keep your phones charged.\n" +
                            "• Prepare a list of emergency hotlines to call",
                    "What to Do During a Typhoon: 10 Things to Remember",
                    "https://25174313.fs1.hubspotusercontent-eu1.net/hub/25174313/hubfs/What%20to%20Do%20During%20a%20Typhoon%20-%20preparedness%20checklist.png?width=674&height=449&name=What%20to%20Do%20During%20a%20Typhoon%20-%20preparedness%20checklist.png",
                    "What should you do during a typhoon? Be alert, have the presence of mind, and don't panic.\n" +
                            "\n" +
                            "To ensure your family’s safety and survival during a typhoon, here are the things to do and not to do. Make sure to include these safety tips in your emergency preparedness checklist:\n" +
                            "\n" +
                            "• Stay at home or in a safe place. If you really have to leave, avoid areas that are prone to flooding or landslides. Watch out also for flying objects and debris.\n" +
                            "• Cancel all travel and outdoor activities. Practice safe driving during a typhoon if you really have to go somewhere.\n" +
                            "• Wear dry and warm clothes.\n" +
                            "• Keep yourself updated on the latest weather news.\n" +
                            "• Don't wade through the flood to avoid leptospirosis and other water-borne diseases, as well as electrocution.\n" +
                            "• Keep children from playing in the rain or swimming in the flood.\n" +
                            "• If you run out of potable water, make your available water supply safe for drinking by boiling it for 3 to 20 minutes.\n" +
                            "• Keep all food and water containers covered and sealed.\n" +
                            "• Keep lit candles and gas lamps out of reach of children and pets.\n" +
                            "• Consult a doctor right away when you or a family member is sick or has symptoms of a disease.",
                    "What to Do After a Typhoon: 5 Things to Watch Out For",
                    "https://25174313.fs1.hubspotusercontent-eu1.net/hub/25174313/hubfs/assets_moneymax/flood-965092_640-e1562232913744.jpg?width=675&height=448&name=flood-965092_640-e1562232913744.jpg",
                    "Your preparedness for a typhoon extends until after the storm. You still have to watch out for any risks or dangers even when the worst is over. \n" +
                            "\n" +
                            "According to the Red Cross, it’s crucial to be self-reliant during the first three days following a disaster. This is when water, electricity, and communication lines are usually non-existent. \n" +
                            "\n" +
                            "Here's a checklist of safety precautions you can refer to during the aftermath of a typhoon in the Philippines:\n" +
                            "\n" +
                            "• Leave the evacuation area only when the authorities declare that it's safe to return home.\n" +
                            "• Assess the risk. If your home was ruined by the typhoon, check first if it's safe and stable before entering. Report any damaged electric posts and cables to the authorities. Watch out for live wires or outlets submerged in water. Also, don't plug in and use appliances and other electrical devices that have been flooded.\n" +
                            "• Remove water in containers, cans, tires, and pots to keep mosquitos from breeding.\n" +
                            "• Inspect your vehicle for any damage caused by flooding.\n" +
                            "• Keep monitoring the news for any new typhoon in the Philippines.",
                    "How to Survive a Typhoon in the Philippines: 5 Safety Tips",
                    "Stay Healthy",
                    "Keep yourself physically healthy to survive a typhoon. If you live in a low-lying and flood-prone area, you need your physical strength to survive the floodwaters, strong winds, and other harsh elements. \n" +
                            "\n" +
                            "The same is true for your mental health. According to the World Health Organization (WHO), mental health problems emerged among those who lost loved ones and properties during Typhoon Yolanda.[3] Being mentally fit can reduce the adverse impact of natural disasters on your mental health, like depression and post-traumatic stress disorders. ",
                    "Make an Emergency and Survival Plan",
                    "What to do during a typhoon? Save your appliances or pack your clothes. Also, where will you park the car in case floodwaters rise? Who should you call and where should you go?  \n" +
                            "\n" +
                            "If you don’t know the answers, sit down and discuss survival measures and disaster awareness with your family. Assign tasks among yourselves so that you won’t waste time. Even younger members of the household can help by collecting flashlights, power banks, and other small emergency equipment. Other adults can take charge of preparing food, drinking water, and other essentials. ",
                    "Get an Insurance Plan",
                    "In times of crisis, having insurance coverage can give you peace of mind. Some of the must-haves include car insurance, health insurance, and life insurance. If you don’t have one yet, you can purchase an insurance policy for as low as ₱200 per month. Also, protect your elderly parents by getting them health cards for senior citizens.  ",
                    "",
                    ""));


            //DATA 2 - Image Resolution:


//            featuredDataList.add(new DataFeatured("https://www.japantimes.co.jp/uploads/imported_images/uploads/2019/07/f-quakepredict-a-20190708.jpg",
//                    "Fire 101: Tips for prevention and survival", "Article 2 Intro", "Detail 2","Sub-subheading1", "Subheading 1 Text" ));


            //DISPLAY CONTENT
            recyclerView.setAdapter(new AdapterFeatured(getContext(), featuredDataList));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }




    private void getWeatherInfoForLocation() {
        String city = "Santo Tomas";
        String country = "Philippines";

        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "," + country + "&appid=43f6f5726e6143864cf4b139376f2029";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<WeatherInfoData> weatherInfoDataList = parseWeatherData(response);

                WeatherAdapter weatherAdapter = new WeatherAdapter(weatherInfoDataList);
                updatesRecyclerView.setAdapter(weatherAdapter);
                updatesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(stringRequest);
    }

    private List<WeatherInfoData> parseWeatherData(String response) {
        List<WeatherInfoData> weatherInfoDataList = new ArrayList<>();

        try {
            JSONObject jsonResponse = new JSONObject(response);

            // Extract necessary information from the JSON response
            String cityName = jsonResponse.optString("name");
            String countryName = jsonResponse.optJSONObject("sys").optString("country");
            double temperature = jsonResponse.optJSONObject("main").optDouble("temp");
            String description = jsonResponse.optJSONArray("weather").optJSONObject(0).optString("description");

            // Create a WeatherInfoData object and add it to the list
            WeatherInfoData weatherInfoData = new WeatherInfoData(cityName, countryName, temperature, description);
            weatherInfoDataList.add(weatherInfoData);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return weatherInfoDataList;
    }


    private void showExpandedView(SlideModel slideModel) {

        Intent intent = new Intent(getContext(), AnnouncementExpandedActivity.class);
        intent.putExtra("bannerImage", slideModel.getImageUrl());
        intent.putExtra("title", slideModel.getTitle());

        startActivity(intent);
    }
}