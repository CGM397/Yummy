package edu.nju.Yummy.serviceImpl;

import edu.nju.Yummy.dao.RestaurantInfoDao;
import edu.nju.Yummy.model.Restaurant;
import edu.nju.Yummy.service.RestaurantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantInfoServiceImpl implements RestaurantInfoService {

    @Autowired
    private RestaurantInfoDao restaurantInfoDao;

    @Override
    public boolean updateRestaurantInfo(Restaurant restaurant) {
        return restaurantInfoDao.updateRestaurantInfo(restaurant);
    }

    @Override
    public Restaurant findRestaurantInfoById(String restaurantId) {
        return restaurantInfoDao.findRestaurantInfoById(restaurantId);
    }
}
