package com.example.readysilience;

public class DataStoresFirstaid {
    private int storePic;
    private String storeName;

    public DataStoresFirstaid(int storePic, String storeName) {
        this.storePic = storePic;
        this.storeName = storeName;
    }

    public int getStorePic() {
        return storePic;
    }

    public String getStoreName() {
        return storeName;
    }
}