package com.example.readysilience;

public class DataInjuries {

    private int injuryPic;
    private String injuryName;
    private int injuryDesc;
    private String injuryType;

    public DataInjuries(int injuryPic, String injuryName,  int injuryDesc, String injuryType) {
        this.injuryPic = injuryPic;
        this.injuryName = injuryName;
        this.injuryDesc = injuryDesc;
        this.injuryType = injuryType;
    }

    public int getInjuryPic(){
        return injuryPic;
    }

    public String getInjuryName(){
        return injuryName;
    }

    public int getInjuryDesc(){
        return injuryDesc;
    }
    public String getInjuryType(){
        return injuryType;
    }
}
