package edu.nju.Yummy.controller;

import edu.nju.Yummy.service.RegisterLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registerLogin")
public class RegisterLoginController {

    @Autowired
    private RegisterLoginService registerLoginService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public boolean register(@RequestParam String userMail, @RequestParam String userPassword,
                            @RequestParam String userName, @RequestParam String phoneNumber){
        return registerLoginService.register(userMail, userPassword, userName, phoneNumber);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public boolean login(@RequestParam String userMail, @RequestParam String userPassword){
        return registerLoginService.login(userMail, userPassword);
    }
}
