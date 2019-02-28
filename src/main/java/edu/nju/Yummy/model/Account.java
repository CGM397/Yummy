package edu.nju.Yummy.model;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
@Repository
public class Account implements Serializable {

    @Id
    @GeneratedValue
    private long accountInfoId;

    private String userId;

    private String accountId;

    private String paymentPassword;

    private double balance;

    private boolean inUse;

    public Account() {
    }

    public Account(String userId, String accountId, String paymentPassword,
                   double balance, boolean inUse) {
        this.userId = userId;
        this.accountId = accountId;
        this.paymentPassword = paymentPassword;
        this.balance = balance;
        this.inUse = inUse;
    }

    public long getAccountInfoId() {
        return accountInfoId;
    }

    public void setAccountInfoId(long accountInfoId) {
        this.accountInfoId = accountInfoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}
