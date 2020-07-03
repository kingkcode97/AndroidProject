package com.example.dangkythanhvien;

import java.io.Serializable;

public class UserModel implements Serializable {
    private String fullName;
    private String userName;
    private String passWord;
    private String birthDay;
    private String sex;
    private String country;

    public UserModel(){}

    public UserModel(String fullName, String userName, String passWord, String birthDay, String sex, String country) {
        this.fullName = fullName;
        this.userName = userName;
        this.passWord = passWord;
        this.birthDay = birthDay;
        this.sex = sex;
        this.country = country;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
