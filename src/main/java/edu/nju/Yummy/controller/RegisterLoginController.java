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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public boolean register(@RequestParam String customerMail, @RequestParam String customerPassword,
                            @RequestParam String customerName, @RequestParam String phoneNumber){
        return registerLoginService.register(customerMail, customerPassword, customerName, phoneNumber);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public boolean login(@RequestParam String customerMail, @RequestParam String customerPassword){
        return registerLoginService.login(customerMail, customerPassword);
    }
}
