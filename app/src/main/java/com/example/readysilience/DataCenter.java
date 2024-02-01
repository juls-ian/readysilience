package com.example.readysilience;

public class DataCenter {
    private String imageUrl;
    private String centerName;
    private double hospitalLatitude;
    private double hospitalLongitude;

    public DataCenter(String imageUrl, String centerName, double hospitalLatitude, double hospitalLongitude) {
        this.imageUrl = imageUrl;
        this.centerName = centerName;
        this.hospitalLatitude = hospitalLatitude;
        this.hospitalLongitude = hospitalLongitude;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public String getCenterName() {
        return centerName;
    }

    public double getHospitalLatitude() {
        return hospitalLatitude;
    }

    public double getHospitalLongitude() {
        return hospitalLongitude;
    }
}

