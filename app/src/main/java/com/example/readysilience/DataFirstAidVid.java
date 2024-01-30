package com.example.readysilience;

public class DataFirstAidVid {

    private int thumbnail;
    private String vidTitle;
    private String vidDuration;
    private int creatorLogo;

    public DataFirstAidVid(int thumbnail, String vidTitle, String vidDuration, int creatorLogo) {
        this.thumbnail = thumbnail;
        this.vidTitle = vidTitle;
        this.vidDuration = vidDuration;
        this.creatorLogo = creatorLogo;
    }

    public int getThumbnail(){
        return thumbnail;
    }

    public String getVidTitle(){
        return vidTitle;
    }

    public String getVidDuration(){
        return vidDuration;
    }

    public int getCreatorLogo(){
        return creatorLogo;
    }
}