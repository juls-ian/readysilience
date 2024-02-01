package com.example.readysilience;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.readysilience.ExpandedViews.WeatherExpandedView;

import java.util.List;


// UpdatesAdapter.java
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


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch the expanded view activity
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



        WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            weatherIconIV = itemView.findViewById(R.id.indication_icon);
            temperatureTV = itemView.findViewById(R.id.temperature);
            humidityTV = itemView.findViewById(R.id.humidity);
            atmosphericPressureTV = itemView.findViewById(R.id.atmospheric_pressure);
            windPressureTV = itemView.findViewById(R.id.wind_speed_pressure);
            visibilityTV = itemView.findViewById(R.id.visibility);
        }
    }
}


