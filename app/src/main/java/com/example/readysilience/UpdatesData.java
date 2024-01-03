package com.example.readysilience;

public class UpdatesData {

    private String newsImage;
    private String title;



    public UpdatesData(String newsImage, String title) {
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


