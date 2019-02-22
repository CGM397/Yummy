package edu.nju.Yummy.model;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table
@Repository
public class CommodityInfo implements Serializable {

    @Id
    @GeneratedValue

    private long commodityInfoId;

    private String restaurantId;

    private String commodityName;

    private String type;

    private double commodityPrice;

    private int amount;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "info_Id")
    private ArrayList<CommodityItem> items;

    private String releaseDate;

    private int duration;

    public CommodityInfo() {
    }

    public CommodityInfo(String restaurantId, String commodityName, String type,
                         double commodityPrice, int amount, ArrayList<CommodityItem> items,
                         String releaseDate, int duration) {
        this.restaurantId = restaurantId;
        this.commodityName = commodityName;
        this.type = type;
        this.commodityPrice = commodityPrice;
        this.amount = amount;
        this.items = items;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

    public long getCommodityInfoId() {
        return commodityInfoId;
    }

    public void setCommodityInfoId(long commodityInfoId) {
        this.commodityInfoId = commodityInfoId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ArrayList<CommodityItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<CommodityItem> items) {
        this.items = items;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
