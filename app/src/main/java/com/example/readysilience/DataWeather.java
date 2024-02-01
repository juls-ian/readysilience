package com.example.readysilience;

public class DataWeather {

    private String temperature;
    private String humidity;
    private String atmosphericPressure;
    private String windPressure;
    private String visibility;
    private int weatherIcon;


    public DataWeather(String temperature, String humidity, String atmosphericPressure, String windPressure, String visibility,
                       int weatherIcon) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.atmosphericPressure = atmosphericPressure;
        this.windPressure = windPressure;
        this.visibility = visibility;
        this.weatherIcon = weatherIcon;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getAtmosphericPressure() {
        return atmosphericPressure;
    }

    public String getWindPressure() {
        return windPressure;
    }

    public String getVisibility() {
        return visibility;
    }

    public int getWeatherIcon() {
        return weatherIcon;
    }
}


