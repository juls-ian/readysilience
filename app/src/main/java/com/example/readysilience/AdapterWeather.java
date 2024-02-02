package com.example.readysilience;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.readysilience.ExpandedViews.WeatherExpandedView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdapterWeather extends RecyclerView.Adapter<AdapterWeather.WeatherViewHolder> {

    private Context context;
    private List<DataWeather> weatherDataList;

    public AdapterWeather(Context context, List<DataWeather> weatherDataList) {
        this.context = context;
        this.weatherDataList = weatherDataList;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_weather, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        DataWeather weatherData = weatherDataList.get(position);

        Glide.with(holder.itemView)
                .load(weatherData.getWeatherIcon())
                .placeholder(R.drawable.image_icon)
                .into(holder.weatherIconIV);

        holder.temperatureTV.setText(weatherData.getTemperature());
        holder.humidityTV.setText(weatherData.getHumidity());
        holder.atmosphericPressureTV.setText(weatherData.getAtmosphericPressure());
        holder.windPressureTV.setText(weatherData.getWindPressure());
        holder.visibilityTV.setText(weatherData.getVisibility());
        holder.cloudStatusTV.setText(weatherData.getWeatherDescription());

        getWeatherInfoForLocation(holder);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WeatherExpandedView.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return weatherDataList.size();
    }

    static class WeatherViewHolder extends RecyclerView.ViewHolder {

        ImageView weatherIconIV;
        TextView temperatureTV;
        TextView humidityTV;
        TextView atmosphericPressureTV;
        TextView windPressureTV;
        TextView visibilityTV;
        TextView cloudStatusTV;

        WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            weatherIconIV = itemView.findViewById(R.id.indication_icon);
            temperatureTV = itemView.findViewById(R.id.temperature);
            humidityTV = itemView.findViewById(R.id.humidity);
            atmosphericPressureTV = itemView.findViewById(R.id.atmospheric_pressure);
            windPressureTV = itemView.findViewById(R.id.wind_speed_pressure);
            visibilityTV = itemView.findViewById(R.id.visibility);
            cloudStatusTV = itemView.findViewById(R.id.cloud_status);
        }
    }

    private void getWeatherInfoForLocation(WeatherViewHolder holder) {

        String apiKey = "7e655fe8fed65a9dec0485267561ae8a";
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=14.58&lon=121.01&appid=" + apiKey;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<DataWeather> weatherInfoDataList = parseWeatherData(response);

                if (!weatherInfoDataList.isEmpty()) {
                    DataWeather weatherInfoData = weatherInfoDataList.get(0);
                    holder.temperatureTV.setText(weatherInfoData.getTemperature());
                    holder.humidityTV.setText(weatherInfoData.getHumidity());
                    holder.atmosphericPressureTV.setText(weatherInfoData.getAtmosphericPressure());
                    holder.windPressureTV.setText(weatherInfoData.getWindPressure());
                    holder.visibilityTV.setText(weatherInfoData.getVisibility());
                    holder.cloudStatusTV.setText(weatherInfoData.getWeatherDescription());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    private List<DataWeather> parseWeatherData(String response) {
        List<DataWeather> weatherInfoDataList = new ArrayList<>();

        try {
            JSONObject jsonResponse = new JSONObject(response);

            double temperatureKelvin = jsonResponse.optJSONObject("main").optDouble("temp");
            double temperatureCelsius = convertKelvinToCelsius(temperatureKelvin);
            String temperature = String.format("%.2f°C", temperatureCelsius);

            String humidity = jsonResponse.optJSONObject("main").optDouble("humidity") + "%";
            String atmosphericPressure = jsonResponse.optJSONObject("main").optDouble("pressure") + " hPa";
            String windSpeed = jsonResponse.optJSONObject("wind").optDouble("speed") + " m/s";

            double visibilityMeters = jsonResponse.optDouble("visibility");
            double visibilityKilometers = convertMetersToKilometers(visibilityMeters);
            String visibility = String.format("%.2f km", visibilityKilometers);

            JSONArray weatherArray = jsonResponse.optJSONArray("weather");
            String weatherDescription = weatherArray.optJSONObject(0).optString("description");
            String weatherIcon = weatherArray.optJSONObject(0).optString("icon");

            int weatherIconResourceId = mapWeatherConditionToIcon(weatherIcon);
            Log.d("AdapterWeather", "Weather Icon Resource ID: " + weatherIconResourceId);

            DataWeather weatherInfoData = new DataWeather(temperature, humidity, atmosphericPressure, windSpeed, visibility, weatherDescription, weatherIconResourceId);
            weatherInfoDataList.add(weatherInfoData);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return weatherInfoDataList;
    }

    private double convertMetersToKilometers(double meters) {
        return meters / 1000.0;
    }

    private double convertKelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    private int mapWeatherConditionToIcon(String weatherIcon) {
        String iconName = "weather_icon_" + weatherIcon;
        int iconResourceId = context.getResources().getIdentifier(iconName, "drawable", context.getPackageName());

        if (iconResourceId != 0) {
            Log.d("AdapterWeather", "Found resource for icon: " + iconName + ", Resource ID: " + iconResourceId);
            return iconResourceId;
        } else {
            Log.d("AdapterWeather", "Resource not found for icon: " + iconName);
            return R.drawable.weather_sunny;
        }
    }
}
