package edu.nju.Yummy.model;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Repository
public class Modification implements Serializable {

    @Id
    @GeneratedValue
    private long modificationId;

    private String modificationDate;

    private String restaurantId;

    private String modifiedName;

    private String modifiedType;

    private String modifiedAddress;

    private boolean checked;

    private boolean approve;

    public Modification() {
    }

    public Modification(String modificationDate, String restaurantId, String modifiedName,
                        String modifiedType, String modifiedAddress,
                        boolean checked, boolean approve) {
        this.modificationDate = modificationDate;
        this.restaurantId = restaurantId;
        this.modifiedName = modifiedName;
        this.modifiedType = modifiedType;
        this.modifiedAddress = modifiedAddress;
        this.checked = checked;
        this.approve = approve;
    }

    public long getModificationId() {
        return modificationId;
    }

    public void setModificationId(long modificationId) {
        this.modificationId = modificationId;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getModifiedName() {
        return modifiedName;
    }

    public void setModifiedName(String modifiedName) {
        this.modifiedName = modifiedName;
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }
}
