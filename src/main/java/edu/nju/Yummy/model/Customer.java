package edu.nju.Yummy.model;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
@Repository
public class Customer implements Serializable {

    @Id
    private String customerId;

    private String customerMail;

    private String customerPassword;

    private String customerName;

    private String phoneNumber;

    private int vipLevel;

    private boolean active;

    public Customer() {
    }

    public Customer(String customerId, String customerMail, String customerPassword,
                    String customerName, String phoneNumber, int vipLevel, boolean active) {
        this.customerId = customerId;
        this.customerMail = customerMail;
        this.customerPassword = customerPassword;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.vipLevel = vipLevel;
        this.active = active;
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

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
