package edu.nju.Yummy.controller;

import edu.nju.Yummy.model.Address;
import edu.nju.Yummy.model.Customer;
import edu.nju.Yummy.service.CustomerInfoService;
import edu.nju.Yummy.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/customerInfo")
public class CustomerInfoController {

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private UserAddressService userAddressService;

    @RequestMapping(value = "/findCustomerInfoByMail", method = RequestMethod.POST)
    @ResponseBody
    public Customer findCustomerInfoByMail(@RequestParam String customerMail){
        return customerInfoService.findCustomerInfoByMail(customerMail);
    }

    @RequestMapping(value = "/updateCustomerInfo", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateCustomerInfo(@RequestBody Customer customer){
        return customerInfoService.updateCustomerInfo(customer);
    }

    @RequestMapping(value = "/saveDeliveryAddress", method = RequestMethod.POST)
    @ResponseBody
    public boolean saveDeliveryAddress(@RequestBody Address address){
        return userAddressService.saveAddress(address);
    }

    @RequestMapping(value = "/showDeliveryAddress", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<Address> showDeliveryAddress(@RequestParam String customerId){
        return userAddressService.showAddress(customerId);
    }
}
