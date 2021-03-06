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

    @RequestMapping("/customer-statistics")
    public String customerStatistics(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "customer-statistics";
    }

    @RequestMapping("/customer-shopping")
    public String customerShopping(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "customer-shopping";
    }

    @RequestMapping("/customer-settle")
    public String customerSettle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "customer-settle";
    }

    @RequestMapping("/customer-orderDetail")
    public String customerOrderDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "customer-orderDetail";
    }

    @RequestMapping("/customer-account")
    public String customerAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "customer-account";
    }

    @RequestMapping("/customer-statisticsDetail")
    public String customerStatisticsDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "customer-statisticsDetail";
    }

    @RequestMapping("/customer-staResDetail")
    public String customerStaResDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "customer-staResDetail";
    }

    @RequestMapping("/customer-staResItemDetail")
    public String customerStaResItemDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "customer-staResItemDetail";
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

    @RequestMapping("/restaurant-order")
    public String restaurantOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "restaurant-order";
    }

    @RequestMapping("/restaurant-statistics")
    public String restaurantStatistics(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "restaurant-statistics";
    }

    @RequestMapping("/restaurant-modification")
    public String restaurantModification(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "restaurant-modification";
    }

    @RequestMapping("/restaurant-release")
    public String restaurantRelease(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "restaurant-release";
    }

    @RequestMapping("/restaurant-orderDetail")
    public String restaurantOrderDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "restaurant-orderDetail";
    }

    @RequestMapping("/restaurant-statisticsDetail")
    public String restaurantStatisticsDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "restaurant-statisticsDetail";
    }

    @RequestMapping("/restaurant-staCusDetail")
    public String restaurantStaCusDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "restaurant-staCusDetail";
    }

    @RequestMapping("/restaurant-staCusItemDetail")
    public String restaurantStaCusItemDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "restaurant-staCusItemDetail";
    }

    @RequestMapping("/admin-checkModification")
    public String adminCheckModification(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "admin-checkModification";
    }

    @RequestMapping("/admin-statistics")
    public String adminStatistics(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null) {
            response.sendRedirect("/login");
            return null;
        }
        return "admin-statistics";
    }
}
