package edu.nju.Yummy.service;

import edu.nju.Yummy.model.DiscountInfo;
import edu.nju.Yummy.model.Modification;
import edu.nju.Yummy.model.Restaurant;

import java.util.ArrayList;

public interface RestaurantInfoService {

    boolean updateRestaurantInfo(Restaurant restaurant);

    Restaurant findRestaurantInfoById(String restaurantId);

    boolean addModification(Modification modification);

    ArrayList<Modification> findRestaurantModification(String restaurantId);

    ArrayList<Restaurant> showAllRestaurants();

    ArrayList<DiscountInfo> showDiscountInfo(String restaurantId);
}