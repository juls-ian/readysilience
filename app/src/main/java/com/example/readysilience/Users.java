package com.example.readysilience;

public class Users {

    String firstName, lastName, sex, age, houseNumber, purok;

    public Users() {
    }

    public Users(String firstName, String lastName, String sex, String houseNumber, String purok) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.houseNumber = houseNumber;
        this.purok = purok;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPurok() {
        return purok;
    }

    public void setPurok(String purok) {
        this.purok = purok;
    }
}
