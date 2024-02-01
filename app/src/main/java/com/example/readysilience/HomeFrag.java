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
    RecyclerView updatesRecyclerView;

    public HomeFrag() {
        // Required empty public constructor

    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

            updatesRecyclerView = view.findViewById(R.id.weather_recycler_view);
            getWeatherInfoForLocation();

        //WEATHER

            recyclerView = view.findViewById(R.id.weather_recycler_view);
            List<DataWeather> weatherDataList = new ArrayList<>();
            weatherDataList.add(new DataWeather("63",
                    "Humidity",
                    "Atmospheric Pressure",
                    "Wind Pressure",
                    "Visibility",
                    R.drawable.weather_sunny
                    ));
            recyclerView.setAdapter(new AdapterWeather(getContext(), weatherDataList));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


            //ANNOUNCEMENTS

            SliderView sliderView = view.findViewById(R.id.announcement_slider);

            AdapterImageSlider adapterImageSlider = new AdapterImageSlider(getContext());
            sliderView.setSliderAdapter(adapterImageSlider);


        //NEARBY CENTERS
        viewPager = view.findViewById(R.id.viewPager);

        dataCenterList.add(new DataCenter("https://www.health-core.org/wp-content/uploads/2017/10/st_cabrini_nabh_accreditation.jpg",
                "St. Frances Cabrini Medical Center",
                14.126479887369038, 121.13823613703165));

            dataCenterList.add(new DataCenter("https://lh5.googleusercontent.com/p/AF1QipNUgJelQ_Ouu4UY4gbXwgwSJdR2uFrMbXy9ROQZ=w480-h300-k-n-rw",
                    "Santo Tomas General Hospital",
                    14.098478505443497, 121.147269723536));

            dataCenterList.add(new DataCenter("https://lh5.googleusercontent.com/p/AF1QipOpq_BLB9TFCcIsqYXD8SEmTwc7W0B76fK4A9cS=w408-h306-k-no",
                    "St. John Diagnostic and Medical Clinic",
                    14.092505553654314, 121.14888583433017));

            dataCenterList.add(new DataCenter("https://jodans.com.ph/projects/148687922512.jpg",
                    "C.P. Reyes Hospital",
                    14.084706256479368, 121.14956283887555));


        int initialPosition = Integer.MAX_VALUE / 2;
        viewPager.setAdapter(new AdapterCenters(getContext(), dataCenterList));
        viewPager.setPadding(50, 0, 50, 0);
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
            featuredDataList.add(new DataFeatured("https://shutgun.ca/wp-content/uploads/2023/02/inspecting-fire-extinguihser.jpg",
                    "Know the Importance of a Fire Extinguisher",
                    "You cannot tell when a fire will happen. Before you know it, the flames have spread, damaging the vicinity and endangering lives. \n" +
                            "\n" +
                            "Fire extinguishers are your first line of defense. But how exactly do these fire protection devices work? Do they work on all kinds of fires? \n" +
                            "\n" +
                            "In this article, you’ll learn the importance of fire extinguishers in the workplace and at home. This article will also discuss its types and advantages and disadvantages. ",
                    "",
                    "What is a Fire Extinguisher?",
                    "A fire extinguisher is a device used for extinguishing small fires or controlling their spread and damage before the firefighters arrive. It usually contains dry or wet chemicals specifically formulated to counter flames.  \n" +
                            "\n" +
                            "Fire extinguishers come in various sizes, but they’re generally portable. They’re usually located in accessible places, so people can quickly grab them in case of emergency. \n" +
                            "\n" +
                            "Does a fire extinguisher expire? Yes, it does.\n" +
                            "\n" +
                            "However, the lifespan of a fire extinguisher is generally long. If you’re wondering how many years is a fire extinguisher good for, know that a fire extinguisher can last between 10 and 12 years if its vessel is properly maintained. ",
                    "https://25174313.fs1.hubspotusercontent-eu1.net/hub/25174313/hubfs/Pics%20for%20blog%20-%20600x400-Jun-19-2023-07-55-01-7730-AM.png?width=674&height=449&name=Pics%20for%20blog%20-%20600x400-Jun-19-2023-07-55-01-7730-AM.png",
                    "Is it important to have a fire extinguisher at home? Yes—fires can happen anywhere, so a fire extinguisher is necessary in homes, shops, and offices, among others. \n" +
                            "\n" +
                            "Place fire extinguishers across your property, including all the floors of your home, your garage, bedrooms, and most importantly, near a heat source such as the kitchen. \n" +
                            "\n" +
                            "If you're running a business, you’ll have to secure a Fire Safety Inspection Certificate.[1] Fire extinguishers are among this certificate’s requirements. \n" +
                            "\n" +
                            "There are also fire extinguishers designed for car owners. It may not seem immediately obvious that you need one, but remember that your car runs on fuel and some of its parts are made of combustible material. ",
                    "Pros of Using a Fire Extinguisher ",
                    "\uD83D\uDC4D Useful During Emergencies \n" +
                            "The importance of a fire extinguisher is evident during emergencies. In the hands of an alert individual, this device can control the size and spread of the fire before firefighters arrive. It can also completely kill small fires.  \n" +
                            "\n" +
                            "\uD83D\uDC4D Relatively Affordable \n" +
                            "Fire extinguishers are relatively inexpensive, costing between ₱500 to ₱1,000. You can buy them online via shopping platforms like Lazada or Shopee.  \n" +
                            "\n" +
                            "\uD83D\uDC4D Portable\n" +
                            "Fire extinguishers are light and portable, which means you can place them wherever necessary. Take them with you if you’re moving to a new home or office. You can even keep a smaller variant in your car. \n" +
                            "\n" +
                            "\uD83D\uDC4D Easy to Use \n" +
                            "Since fire extinguishers are lightweight and portable, anyone can singlehandedly operate them. Some extinguishers even come with instructions to guide those using them for the first time.   ",
                    "",
                    "https://25174313.fs1.hubspotusercontent-eu1.net/hub/25174313/hubfs/Pics%20for%20blog%20-%20600x400%20(2)-Jun-19-2023-08-07-41-1959-AM.png?width=674&height=449&name=Pics%20for%20blog%20-%20600x400%20(2)-Jun-19-2023-08-07-41-1959-AM.png",
                    "The importance of a fire extinguisher is evident during emergencies. In the hands of an alert individual, this device can control the size and spread of the fire before firefighters arrive. It can also completely kill small fires",
                    "Types of Fire Extinguishers",
                    "https://25174313.fs1.hubspotusercontent-eu1.net/hub/25174313/hubfs/Pics%20for%20blog%20-%20600x400%20(1)-Jun-19-2023-07-57-30-2514-AM.png?width=675&height=450&name=Pics%20for%20blog%20-%20600x400%20(1)-Jun-19-2023-07-57-30-2514-AM.png",
                    "\uD83D\uDD25 Water Fire Extinguisher \n" +
                            "Water fire extinguishers use high-pressure water to douse fires. They’re also mixed with certain additives to increase effectiveness. They generally come in two types: water spray and dry water mist. \n" +
                            "\n" +
                            "Water spray extinguishers are suitable for Class A fires or flames that involve solid materials, such as paper, plastic, and textile. However, they may not be suitable for fires that involve oil and electrical equipment. \n" +
                            "\n" +
                            "On the other hand, dry water mist extinguishers can fight almost all kinds of fires. \n" +
                            "\n" +
                            "\uD83D\uDD25 Powder Fire Extinguisher \n" +
                            "Powder fire extinguishers use dry chemical powder to combat fires. The ABC powder extinguisher is one of its most common kinds. \n" +
                            "\n" +
                            "As the name suggests, the ABC powder extinguisher can fight class A, B, and C fires. However, it can also combat electrical fires since its powder does not conduct electricity.  \n" +
                            "\n" +
                            "\uD83D\uDD25 Carbon Dioxide Fire Extinguisher \n" +
                            "You can easily identify this fire extinguisher as it bears the text CO2. Fire extinguishers under this classification are designed to combat Class B and electrical fires. They work by displacing and reducing the amount of oxygen—an element that triggers fires—in the vicinity.[3]\n" +
                            "\n" +
                            "\uD83D\uDD25 Foam Fire Extinguisher \n" +
                            "Foam fire extinguishers produce foam that can blanket flames while keeping the vapor from reignition. They’re made of water with foaming additives.\n" +
                            "\n" +
                            "These fire extinguishers can combat class A and B flames. \n" +
                            "\n" +
                            "\uD83D\uDD25 Wet Chemical Fire Extinguisher\n" +
                            "Wet chemical fire extinguishers usually contain water, potassium acetate, and potassium carbonate. These are suitable for killing class F fires, which possess extremely high temperatures. However, some variations are designed to combat class A flames. ",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""));


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