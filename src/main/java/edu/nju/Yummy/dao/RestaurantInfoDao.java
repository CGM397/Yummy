package edu.nju.Yummy.dao;

import edu.nju.Yummy.model.Restaurant;

import java.util.ArrayList;

public interface RestaurantInfoDao {

    ArrayList<Restaurant> showAllRestaurants();

    boolean saveRestaurantInfo(Restaurant restaurant);

    Restaurant findRestaurantById(String restaurantId);

}
