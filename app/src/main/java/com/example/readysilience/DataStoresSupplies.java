package com.example.readysilience;

public class DataStoresSupplies {
    private int storePic;
    private String storeName;

    public DataStoresSupplies(int storePic, String storeName) {
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
