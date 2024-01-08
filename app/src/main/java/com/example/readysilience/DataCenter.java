package com.example.readysilience;

public class DataCenter {
    private String imageUrl;
    private String text;

    public DataCenter(String imageUrl, String text) {
        this.imageUrl = imageUrl;
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getText() {
        return text;
    }
}

