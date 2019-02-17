package edu.nju.Yummy.service;

import edu.nju.Yummy.model.Modification;
import edu.nju.Yummy.model.Restaurant;

public interface RestaurantInfoService {

    boolean updateRestaurantInfo(Restaurant restaurant);

    Restaurant findRestaurantInfoById(String restaurantId);

    boolean addModification(Modification modification);

    boolean deleteModification(String restaurantId);

    Modification findModificationById(String restaurantId);
}
