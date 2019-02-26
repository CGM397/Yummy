package edu.nju.Yummy.serviceImpl;

import edu.nju.Yummy.dao.ModificationDao;
import edu.nju.Yummy.dao.RestaurantInfoDao;
import edu.nju.Yummy.model.DiscountInfo;
import edu.nju.Yummy.model.Modification;
import edu.nju.Yummy.model.Restaurant;
import edu.nju.Yummy.service.RestaurantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RestaurantInfoServiceImpl implements RestaurantInfoService {

    @Autowired
    private RestaurantInfoDao restaurantInfoDao;

    @Autowired
    private ModificationDao modificationDao;

    @Override
    public boolean updateRestaurantInfo(Restaurant restaurant) {
        return restaurantInfoDao.updateRestaurantInfo(restaurant);
    }

    @Override
    public Restaurant findRestaurantInfoById(String restaurantId) {
        return restaurantInfoDao.findRestaurantInfoById(restaurantId);
    }

    @Override
    public boolean addModification(Modification modification) {
        return modificationDao.addModification(modification);
    }

    @Override
    public ArrayList<Modification> findRestaurantModification(String restaurantId) {
        return modificationDao.findRestaurantModification(restaurantId);
    }

    @Override
    public ArrayList<Restaurant> showAllRestaurants() {
        return restaurantInfoDao.showAllRestaurants();
    }

    @Override
    public ArrayList<DiscountInfo> showDiscountInfo(String restaurantId) {
        return restaurantInfoDao.showDiscountInfo(restaurantId);
    }

}
