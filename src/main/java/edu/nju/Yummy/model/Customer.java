package edu.nju.Yummy.model;

import java.util.ArrayList;

public class Customer {

    private String customerId;

    private String customerMail;

    private String customerPassword;

    private String customerName;

    private String phoneNumber;

    private ArrayList<String> deliveryAddress;

    private int vipLevel;

    public Customer() {
    }

    public Customer(String customerId, String customerMail, String customerPassword,
                    String customerName, String phoneNumber,
                    ArrayList<String> deliveryAddress, int vipLevel) {
        this.customerId = customerId;
        this.customerMail = customerMail;
        this.customerPassword = customerPassword;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.deliveryAddress = deliveryAddress;
        this.vipLevel = vipLevel;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
