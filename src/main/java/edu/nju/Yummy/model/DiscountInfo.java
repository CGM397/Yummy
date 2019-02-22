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
public class DiscountInfo implements Serializable {

    @Id
    @GeneratedValue
    private long discountInfoId;

    private String restaurantId;

    private String commodityName;

    private double discount;

    private int discountAmount;

    private String releaseDate;

    private int duration;

    public DiscountInfo() {
    }

    public DiscountInfo(String restaurantId, String commodityName, double discount,
                        int discountAmount, String releaseDate, int duration) {
        this.restaurantId = restaurantId;
        this.commodityName = commodityName;
        this.discount = discount;
        this.discountAmount = discountAmount;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

    public long getDiscountInfoId() {
        return discountInfoId;
    }

    public void setDiscountInfoId(long discountInfoId) {
        this.discountInfoId = discountInfoId;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
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
