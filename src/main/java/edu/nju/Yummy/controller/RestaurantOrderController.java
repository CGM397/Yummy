package edu.nju.Yummy.controller;

import edu.nju.Yummy.model.OrderInfo;
import edu.nju.Yummy.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/restaurantOrder")
public class RestaurantOrderController {

    @Autowired
    private OrderInfoService orderInfoService;

    @RequestMapping(value = "/showRestaurantOrders", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<OrderInfo> showRestaurantOrders(@RequestParam String restaurantId){
        return orderInfoService.showRestaurantOrders(restaurantId);
    }
}
