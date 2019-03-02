package edu.nju.Yummy.controller;

import edu.nju.Yummy.model.CommodityInfo;
import edu.nju.Yummy.model.DiscountInfo;
import edu.nju.Yummy.service.RestaurantReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/restaurantRelease")
public class RestaurantReleaseController {

    @Autowired
    private RestaurantReleaseService restaurantReleaseService;

    @RequestMapping(value = "/releaseProduct", method = RequestMethod.POST)
    @ResponseBody
    public boolean releaseProduct(@RequestBody CommodityInfo commodityInfo){
        return restaurantReleaseService.releaseProduct(commodityInfo);
    }

    @RequestMapping(value = "/releaseDiscount", method = RequestMethod.POST)
    @ResponseBody
    public boolean releaseDiscount(@RequestBody DiscountInfo discountInfo){
        return restaurantReleaseService.releaseDiscount(discountInfo);
    }

    @RequestMapping(value = "/showCommodity", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<CommodityInfo> showCommodity(@RequestParam String restaurantId){
        return restaurantReleaseService.showCommodity(restaurantId);
    }

    @RequestMapping(value = "/showCommodityToCome", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<CommodityInfo> showCommodityToCome(@RequestParam String restaurantId){
        return restaurantReleaseService.showCommodityToCome(restaurantId);
    }
}
