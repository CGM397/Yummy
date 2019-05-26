package edu.nju.Yummy.model;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    private int leftAmount;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<CommodityItem> items;

    private Date beginDate;

    private Date endDate;

    public CommodityInfo() {
    }

    public CommodityInfo(String restaurantId, String commodityName, String type,
                         double commodityPrice, int amount, int leftAmount,
                         List<CommodityItem> items, Date beginDate, Date endDate) {
        this.restaurantId = restaurantId;
        this.commodityName = commodityName;
        this.type = type;
        this.commodityPrice = commodityPrice;
        this.amount = amount;
        this.leftAmount = leftAmount;
        this.items = items;
        this.beginDate = beginDate;
        this.endDate = endDate;
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

    public int getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(int leftAmount) {
        this.leftAmount = leftAmount;
    }

    public List<CommodityItem> getItems() {
        return items;
    }

    public void setItems(List<CommodityItem> items) {
        this.items = items;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
