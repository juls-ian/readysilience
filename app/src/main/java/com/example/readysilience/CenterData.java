package com.example.readysilience;

public class CenterData {
    private String imageUrl;
    private String text;

    public CenterData(String imageUrl, String text) {
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

