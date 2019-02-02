package edu.nju.Yummy.model;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
@Repository
public class Restaurant implements Serializable {

    @Id
    private String restaurantId;

    private String restaurantPassword;

    private String restaurantName;

    private String restaurantAddress;

    private String restaurantType;

    public Restaurant() {
    }

    public Restaurant(String restaurantId, String restaurantPassword, String restaurantName,
                      String restaurantAddress, String restaurantType) {
        this.restaurantId = restaurantId;
        this.restaurantPassword = restaurantPassword;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantType = restaurantType;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantPassword() {
        return restaurantPassword;
    }

    public void setRestaurantPassword(String restaurantPassword) {
        this.restaurantPassword = restaurantPassword;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
    }
}
