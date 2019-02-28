package edu.nju.Yummy.controller;

import edu.nju.Yummy.model.DiscountInfo;
import edu.nju.Yummy.model.OrderInfo;
import edu.nju.Yummy.model.Restaurant;
import edu.nju.Yummy.service.OrderInfoService;
import edu.nju.Yummy.service.RestaurantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/customerShopping")
public class CustomerShoppingController {

    @Autowired
    private RestaurantInfoService restaurantInfoService;

    @Autowired
    private OrderInfoService orderInfoService;

    @RequestMapping(value = "/showAllRestaurants", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<Restaurant> showAllRestaurants(){
        return restaurantInfoService.showAllRestaurants();
    }

    @RequestMapping(value = "/showDiscountInfo", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<DiscountInfo> showDiscountInfo(@RequestParam String restaurantId){
        return restaurantInfoService.showDiscountInfo(restaurantId);
    }

    @RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
    @ResponseBody
    public boolean confirmOrder(@RequestBody OrderInfo orderInfo){
        return orderInfoService.saveOrderInfo(orderInfo);
    }

}
