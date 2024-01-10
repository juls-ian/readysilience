package com.example.readysilience;

public class DataNatDirectories {

    private String natIcon;
    private String officeName;
    private String phone1;
    private String phone2;

    public DataNatDirectories(String natIcon, String officeName, String phone1, String phone2) {
        this.natIcon = natIcon;
        this.officeName = officeName;
        this.phone1 = phone1;
        this.phone2 = phone2;

    }

    public String getNatIcon(){
        return natIcon;
    }

    public String getOfficeName(){
        return officeName;
    }

    public String getPhone1(){
        return phone1;
    }

    public String getPhone2(){
        return phone2;
    }
}
