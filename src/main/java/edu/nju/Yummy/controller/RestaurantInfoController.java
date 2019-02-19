package edu.nju.Yummy.controller;

import edu.nju.Yummy.model.Address;
import edu.nju.Yummy.model.Modification;
import edu.nju.Yummy.model.Restaurant;
import edu.nju.Yummy.service.RestaurantInfoService;
import edu.nju.Yummy.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/restaurantInfo")
public class RestaurantInfoController {

    @Autowired
    private RestaurantInfoService restaurantInfoService;

    @Autowired
    private UserAddressService userAddressService;

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

    @RequestMapping(value = "/showRestaurantAddress", method = RequestMethod.POST)
    @ResponseBody
    public Address showRestaurantAddress(@RequestParam String restaurantId){
        Address res = new Address();
        ArrayList<Address> store = userAddressService.showAddress(restaurantId);
        if(store != null && store.size() > 0)
            res = store.get(0);
        return res;
    }

    @RequestMapping(value = "/saveRestaurantAddress", method = RequestMethod.POST)
    @ResponseBody
    public boolean saveRestaurantAddress(@RequestBody Address address){
        return userAddressService.saveAddress(address);
    }

    @RequestMapping(value = "/updateRestaurantAddress", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateRestaurantAddress(@RequestParam String restaurantId,
                                           @RequestParam String oldAddress,
                                           @RequestParam String newAddress){
        return userAddressService.updateAddress(restaurantId, oldAddress, newAddress);
    }

    @RequestMapping(value = "/addModification", method = RequestMethod.POST)
    @ResponseBody
    public boolean addModification(@RequestBody Modification modification){
        return restaurantInfoService.addModification(modification);
    }

    @RequestMapping(value = "/findRestaurantModification", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<Modification> findRestaurantModification(@RequestParam String restaurantId){
        return restaurantInfoService.findRestaurantModification(restaurantId);
    }
}
