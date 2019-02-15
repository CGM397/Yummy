package edu.nju.Yummy.serviceImpl;

import edu.nju.Yummy.dao.CustomerInfoDao;
import edu.nju.Yummy.dao.RestaurantInfoDao;
import edu.nju.Yummy.model.Customer;
import edu.nju.Yummy.model.Restaurant;
import edu.nju.Yummy.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CustomerInfoDao customerInfoDao;

    @Autowired
    private RestaurantInfoDao restaurantInfoDao;

    @Override
    public String generateRandomCode(int length) {
        String res = "";
        if(length < 1)
            return res;
        int min = (int)Math.pow(10, length - 1);
        int max = (int)Math.pow(10, length) - min;
        Random random = new Random();
        res = (random.nextInt(max) + min) + "";
        return res;
    }

    @Override
    public String generateId(int length, String identity) {
        String res;
        int maxId = 0;
        if(identity.equals("customer")) {
            ArrayList<Customer> customers = customerInfoDao.showAllCustomers();
            if(customers.size() > 0) {
                for(Customer one : customers) {
                    int currentId = Integer.parseInt(one.getCustomerId().split("_")[1]);
                    if(maxId < currentId)
                        maxId = currentId;
                }
            }
        }else if(identity.equals("restaurant")) {
            ArrayList<Restaurant> restaurants = restaurantInfoDao.showAllRestaurants();
            if(restaurants.size() > 0) {
                for(Restaurant one : restaurants) {
                    int currentId = Integer.parseInt(one.getRestaurantId());
                    if(maxId < currentId)
                        maxId = currentId;
                }
            }
        }
        maxId = maxId + 1;
        res = maxId + "";
        int idLength = res.length();
        for(int i = 0; i < length - idLength; i++){
            res = "0" + res;
        }
        return res;
    }

    @Override
    public ArrayList<Integer> generateCoordinate() {
        ArrayList<Integer> res = new ArrayList<>();
        Random random = new Random();
        res.add(random.nextInt(300));
        res.add(random.nextInt(300));
        return res;
    }
}
