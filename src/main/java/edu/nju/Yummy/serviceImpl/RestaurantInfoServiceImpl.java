package edu.nju.Yummy.serviceImpl;

import edu.nju.Yummy.model.Restaurant;
import edu.nju.Yummy.service.RestaurantInfoService;
import org.springframework.stereotype.Service;

@Service
public class RestaurantInfoServiceImpl implements RestaurantInfoService {
    @Override
    public boolean updateRestaurantInfo(Restaurant restaurant) {
        return false;
    }
}
