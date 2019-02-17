package edu.nju.Yummy.model;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
@Repository
public class Modification implements Serializable {

    @Id
    private String restaurantId;

    private String modifiedType;

    private String modifiedAddress;

    private boolean approve;

    public Modification() {
    }

    public Modification(String restaurantId, String modifiedType, String modifiedAddress, boolean approve) {
        this.restaurantId = restaurantId;
        this.modifiedType = modifiedType;
        this.modifiedAddress = modifiedAddress;
        this.approve = approve;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getModifiedType() {
        return modifiedType;
    }

    public void setModifiedType(String modifiedType) {
        this.modifiedType = modifiedType;
    }

    public String getModifiedAddress() {
        return modifiedAddress;
    }

    public void setModifiedAddress(String modifiedAddress) {
        this.modifiedAddress = modifiedAddress;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }
}
