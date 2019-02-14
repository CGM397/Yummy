package edu.nju.Yummy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class IndexController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/customer-home")
    public String customerHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "customer-home";
    }

    @RequestMapping("/customer-info")
    public String customerInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "customer-info";
    }

    @RequestMapping("/customer-address")
    public String customerAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "customer-address";
    }

    @RequestMapping("/customer-order")
    public String customerOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "customer-order";
    }

    @RequestMapping("/restaurant-home")
    public String restaurantHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "restaurant-home";
    }

    @RequestMapping("/restaurant-info")
    public String restaurantInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "restaurant-info";
    }
}
