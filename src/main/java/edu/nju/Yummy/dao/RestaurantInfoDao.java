package edu.nju.Yummy.dao;

import edu.nju.Yummy.model.Restaurant;

import java.util.ArrayList;

public interface RestaurantInfoDao {

    ArrayList<Restaurant> showAllRestaurants();

    boolean saveRestaurantInfo(Restaurant restaurant);

    boolean updateRestaurantInfo(Restaurant restaurant);

    Restaurant findRestaurantInfoById(String restaurantId);

}
