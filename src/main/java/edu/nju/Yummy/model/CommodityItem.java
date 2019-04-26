package edu.nju.Yummy.model;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Repository
public class CommodityItem implements Serializable {

    @Id
    @GeneratedValue
    private long commodityItemId;

    private String restaurantId;

    private String itemName;

    private double itemPrice;

    public CommodityItem() {
    }

    public CommodityItem(String restaurantId, String itemName,
                         double itemPrice) {
        this.restaurantId = restaurantId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public long getCommodityItemId() {
        return commodityItemId;
    }

    public void setCommodityItemId(long commodityItemId) {
        this.commodityItemId = commodityItemId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}
