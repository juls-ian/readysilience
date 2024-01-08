package com.example.readysilience;

public class DataDisasters {
    private int disasterPic;
    private String disasterName;

    private  String bitDesc;

    private  String disasterType;

    private  String proneType;

    private String activityType;

    public DataDisasters(int disasterPic, String disasterName, String bitDesc, String disasterType, String proneType, String
                         activityType) {
        this.disasterPic = disasterPic;
        this.disasterName = disasterName;
        this.bitDesc = bitDesc;
        this.disasterType = disasterType;
        this.proneType = proneType;
        this.activityType = activityType;
    }

    public int getImagePic() {
        return disasterPic;
    }

    public String getDisasterName() {
        return disasterName;
    }

    public String getBitDesc() {
        return bitDesc;
    }

    public String getDisasterType() {
        return disasterType;
    }

    public String getProneType() {
        return proneType;
    }

    public String getActivityType() {
        return activityType;
    }
}

