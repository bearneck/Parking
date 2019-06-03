package com.bearneck.parking.Bean;

public class Picture {
    private String userName;
    private String realName;
    private String phone;
    private String shifoushenhe;
    private String shiruType;
    private String shiruTime;
    private String shichuTime;
    private String yuyueTime;
    private int id;

    public Picture(String userName, String realName, String phone, String shifoushenhe, String shiruType, String shiruTime, String shichuTime, String yuyueTime, int id) {
        this.userName = userName;
        this.realName = realName;
        this.phone = phone;
        this.shifoushenhe = shifoushenhe;
        this.shiruType = shiruType;
        this.shiruTime = shiruTime;
        this.shichuTime = shichuTime;
        this.yuyueTime = yuyueTime;
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShifoushenhe() {
        return shifoushenhe;
    }

    public void setShifoushenhe(String shifoushenhe) {
        this.shifoushenhe = shifoushenhe;
    }

    public String getShiruType() {
        return shiruType;
    }

    public void setShiruType(String shiruType) {
        this.shiruType = shiruType;
    }

    public String getShiruTime() {
        return shiruTime;
    }

    public void setShiruTime(String shiruTime) {
        this.shiruTime = shiruTime;
    }

    public String getShichuTime() {
        return shichuTime;
    }

    public void setShichuTime(String shichuTime) {
        this.shichuTime = shichuTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYuyueTime() {
        return yuyueTime;
    }

    public void setYuyueTime(String yuyueTime) {
        this.yuyueTime = yuyueTime;
    }
}
