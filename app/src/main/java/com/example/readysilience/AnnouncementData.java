package com.example.readysilience;

public class AnnouncementData {
    private String date;
    private String title;
    private String description;
    private String imageUrl;

    public AnnouncementData() {
    }

    public AnnouncementData(String date, String title, String description, String imageUrl) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
