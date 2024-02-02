package com.example.readysilience;

public class WeatherInfoData {

    private String cityName;
    private String countryName;
    private double temperature;
    private String description;

    public WeatherInfoData(String cityName, String countryName, double temperature, String description) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.temperature = temperature;
        this.description = description;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
