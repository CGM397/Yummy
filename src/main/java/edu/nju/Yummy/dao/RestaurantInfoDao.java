package edu.nju.Yummy.dao;

import edu.nju.Yummy.model.DiscountInfo;
import edu.nju.Yummy.model.Restaurant;

import java.util.ArrayList;

public interface RestaurantInfoDao {

    ArrayList<Restaurant> showAllRestaurants();

    ArrayList<DiscountInfo> showDiscountInfo(String restaurantId);

    boolean saveRestaurantInfo(Restaurant restaurant);

    boolean updateRestaurantInfo(Restaurant restaurant);

    Restaurant findRestaurantInfoById(String restaurantId);

}
