package edu.nju.Yummy.controller;

import edu.nju.Yummy.model.Modification;
import edu.nju.Yummy.service.AdminManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/adminManagement")
public class AdminManagementController {

    @Autowired
    private AdminManagementService adminManagementService;

    @RequestMapping(value = "/showModification", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<Modification> showModification(){
        return adminManagementService.showModification();
    }

    @RequestMapping(value = "/updateModification", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateModification(@RequestBody Modification modification){
        return adminManagementService.updateModification(modification);
    }

    @RequestMapping(value = "/getCustomerNum", method = RequestMethod.POST)
    @ResponseBody
    public int getCustomerNum() {
        return adminManagementService.getCustomerNum();
    }

    @RequestMapping(value = "/getRestaurantNum", method = RequestMethod.POST)
    @ResponseBody
    public int getRestaurantNum() {
        return adminManagementService.getRestaurantNum();
    }
}
