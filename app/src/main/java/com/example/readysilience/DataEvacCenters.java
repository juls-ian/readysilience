package com.example.readysilience;

public class DataEvacCenters {

    private int evaCenterPic;
    String centerAvailability;
    String centerName;
    String centerLocation;
    String waterSupply;
    String foodSupply;
    String blanketSupply;
    String medicSupply;
    double latitude;
    double longitude;


    public DataEvacCenters(int evaCenterPic, String centerName,
                           String centerLocation, String waterSupply, String foodSupply,
                        String blanketSupply, String medicSupply, String centerAvailability,
                           double latitude, double longitude) {
        this.evaCenterPic = evaCenterPic;
        this.centerAvailability = centerAvailability;
        this.centerName = centerName;
        this.centerLocation = centerLocation;
        this.waterSupply = waterSupply;
        this.foodSupply = foodSupply;
        this.blanketSupply = blanketSupply;
        this.medicSupply = medicSupply;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public int getEvaCenterPic() {
        return evaCenterPic;
    }


    public String getCenterName(){
        return centerName;
    }

    public String getCenterLocation(){
        return centerLocation;
    }

    public String getWaterSupply(){
        return waterSupply;
    }

    public String getFoodSupply(){
        return foodSupply;
    }

    public String getBlanketSupply(){
        return blanketSupply;
    }

    public String getMedicSupply(){
        return medicSupply;
    }

    public String getCenterAvailability(){
        return centerAvailability;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
