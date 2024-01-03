package com.example.readysilience;

public class DataUpdates {

    private String newsImage;
    private String title;



    public DataUpdates(String newsImage, String title) {
        this.newsImage = newsImage;
        this.title = title;
    }

    public String getNewsImage(){
        return newsImage;
    }

    public String getTitle() {
        return title;
    }

}


