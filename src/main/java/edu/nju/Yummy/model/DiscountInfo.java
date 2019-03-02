package edu.nju.Yummy.model;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

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

    private int leftAmount;

    private Date beginDate;

    private Date endDate;

    public DiscountInfo() {
    }

    public DiscountInfo(String restaurantId, String commodityName, double discount,
                        int discountAmount, int leftAmount, Date beginDate, Date endDate) {
        this.restaurantId = restaurantId;
        this.commodityName = commodityName;
        this.discount = discount;
        this.discountAmount = discountAmount;
        this.leftAmount = leftAmount;
        this.beginDate = beginDate;
        this.endDate = endDate;
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

    public int getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(int leftAmount) {
        this.leftAmount = leftAmount;
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
