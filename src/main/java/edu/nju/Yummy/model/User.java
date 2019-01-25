package edu.nju.Yummy.model;

import java.util.ArrayList;

public class User {

    private String userId;

    private String userMail;

    private String userPassword;

    private String userName;

    private String phoneNumber;

    private ArrayList<String> deliveryAddress;

    private int vipLevel;

    public User() {
    }

    public User(String userId, String userMail, String userPassword, String userName,
                String phoneNumber, ArrayList<String> deliveryAddress, int vipLevel) {
        this.userId = userId;
        this.userMail = userMail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.deliveryAddress = deliveryAddress;
        this.vipLevel = vipLevel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<String> getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(ArrayList<String> deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }
}
