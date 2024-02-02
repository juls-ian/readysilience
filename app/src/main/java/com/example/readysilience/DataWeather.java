package com.example.readysilience;

public class DataWeather {

    private String temperature;
    private String humidity;
    private String atmosphericPressure;
    private String windPressure;
    private String visibility;
    private String weatherDescription;
    private int weatherIcon;

    public DataWeather(String temperature, String humidity, String atmosphericPressure, String windPressure, String visibility, String weatherDescription, int weatherIcon) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.atmosphericPressure = atmosphericPressure;
        this.windPressure = windPressure;
        this.visibility = visibility;
        this.weatherDescription = weatherDescription;
        this.weatherIcon = weatherIcon;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getAtmosphericPressure() {
        return atmosphericPressure;
    }

    public void setAtmosphericPressure(String atmosphericPressure) {
        this.atmosphericPressure = atmosphericPressure;
    }

    public String getWindPressure() {
        return windPressure;
    }

    public void setWindPressure(String windPressure) {
        this.windPressure = windPressure;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public int getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(int weatherIcon) {
        this.weatherIcon = weatherIcon;
    }
}
