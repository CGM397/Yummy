package edu.nju.Yummy.model;

public class Account {

    private String accountId;

    private String ownerId;

    private double balance;

    public Account() {
    }

    public Account(String accountId, String ownerId, double balance) {
        this.accountId = accountId;
        this.ownerId = ownerId;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
