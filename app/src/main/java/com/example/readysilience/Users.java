package com.example.readysilience;

import java.io.Serializable;

public class Users implements Serializable {

    String firstName, lastName, age, houseNumber, purok, phoneNumber;

    public Users() {
    }

    public Users(String firstName, String lastName, String age, String houseNumber, String purok, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.houseNumber = houseNumber;
        this.purok = purok;
        this.phoneNumber = phoneNumber;
    }

    public Users(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

//    public String getSex() {
//        return sex;
//    }
//
//    public void setSex(String sex) {
//        this.sex = sex;
//    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}