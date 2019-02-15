package edu.nju.Yummy.controller;

import edu.nju.Yummy.model.Restaurant;
import edu.nju.Yummy.service.RestaurantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurantInfo")
public class RestaurantInfoController {

    @Autowired
    private RestaurantInfoService restaurantInfoService;

    @RequestMapping(value = "/updateRestaurantInfo", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateRestaurantInfo(@RequestBody Restaurant restaurant){
        return restaurantInfoService.updateRestaurantInfo(restaurant);
    }

    @RequestMapping(value = "/findRestaurantInfoById", method = RequestMethod.POST)
    @ResponseBody
    public Restaurant findRestaurantInfoById(@RequestParam String restaurantId){
        return restaurantInfoService.findRestaurantInfoById(restaurantId);
    }
}
