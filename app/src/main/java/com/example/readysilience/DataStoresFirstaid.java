package com.example.readysilience;

public class DataStoresFirstaid {
    private int storePic;
    private String storeName;
    private double latitude;
    private double longitude;


    public DataStoresFirstaid(int storePic, String storeName, double latitude, double longitude) {
        this.storePic = storePic;
        this.storeName = storeName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getStorePic() {
        return storePic;
    }

    public String getStoreName() {
        return storeName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}