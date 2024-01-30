package com.example.readysilience;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
    private List<WeatherInfoData> weatherInfoDataList;

    public WeatherAdapter(List<WeatherInfoData> weatherInfoDataList) {
        this.weatherInfoDataList = weatherInfoDataList;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        WeatherInfoData weatherInfoData = weatherInfoDataList.get(position);
        holder.cityTextView.setText(weatherInfoData.getCityName());
        holder.countryTextView.setText(weatherInfoData.getCountryName());
        holder.temperatureTextView.setText(String.valueOf(weatherInfoData.getTemperature()));
        holder.descriptionTextView.setText(weatherInfoData.getDescription());
        // Bind other fields as needed
    }

    @Override
    public int getItemCount() {
        return weatherInfoDataList.size();
    }

    public static class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView cityTextView, countryTextView, temperatureTextView, descriptionTextView;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            cityTextView = itemView.findViewById(R.id.cityTextView);
            countryTextView = itemView.findViewById(R.id.countryTextView);
            temperatureTextView = itemView.findViewById(R.id.temperatureTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            // Initialize other TextViews as needed
        }
    }
}

