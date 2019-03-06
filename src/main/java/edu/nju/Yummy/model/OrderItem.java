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
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue
    private long orderItemId;

    private String itemName;

    private int amount;

    private double subTotal;

    public OrderItem() {
    }

    public OrderItem(String itemName, int amount, double subTotal) {
        this.itemName = itemName;
        this.amount = amount;
        this.subTotal = subTotal;
    }

    public long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
