package com.example.readysilience;

public class DataFeatured {

    private String articleImage;
    private String headline;



    public DataFeatured(String articleImage, String headline) {
        this.articleImage = articleImage;
        this.headline = headline;
    }

    public String getArticleImage(){

        return articleImage;
    }

    public String getHeadline() {

        return headline;
    }

}


