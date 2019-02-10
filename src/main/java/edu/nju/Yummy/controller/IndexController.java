package edu.nju.Yummy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/customer-home")
    public String customerHome(){
        return "customer-home";
    }

    @RequestMapping("/customer-info")
    public String customerInfo(){
        return "customer-info";
    }

    @RequestMapping("/customer-order")
    public String customerOrder(){
        return "customer-order";
    }

    @RequestMapping("/restaurant-home")
    public String restaurantHome(){
        return "restaurant-home";
    }

    @RequestMapping("/restaurant-info")
    public String restaurantInfo(){
        return "restaurant-info";
    }
}
