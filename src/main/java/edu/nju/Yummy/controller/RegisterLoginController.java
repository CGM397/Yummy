package edu.nju.Yummy.controller;

import edu.nju.Yummy.service.RegisterLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registerLogin")
public class RegisterLoginController {

    @Autowired
    private RegisterLoginService registerLoginService;

    @RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    @ResponseBody
    public String sendMail(@RequestParam String customerMail){
        return registerLoginService.sendMail(customerMail);
    }

    @RequestMapping(value = "/customerRegister", method = RequestMethod.POST)
    @ResponseBody
    public boolean customerRegister(@RequestParam String customerMail,
                                    @RequestParam String customerPassword,
                                    @RequestParam String customerName,
                                    @RequestParam String phoneNumber){
        return registerLoginService.customerRegister(customerMail, customerPassword,
                customerName, phoneNumber);
    }

    @RequestMapping(value = "/restaurantRegister", method = RequestMethod.POST)
    @ResponseBody
    public String restaurantRegister(@RequestParam String restaurantName,
                                      @RequestParam String restaurantPassword){
        return registerLoginService.restaurantRegister(restaurantName, restaurantPassword);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam String identity, @RequestParam String account,
                         @RequestParam String password){
        return registerLoginService.login(identity, account, password);
    }
}
