package edu.nju.Yummy.controller;

import edu.nju.Yummy.model.OrderInfo;
import edu.nju.Yummy.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/customerOrder")
public class CustomerOrderController {

    @Autowired
    private OrderInfoService orderInfoService;

    @RequestMapping(value = "/showCustomerOrders", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<OrderInfo> showCustomerOrders(@RequestParam String customerId){
        return orderInfoService.showCustomerOrders(customerId);
    }

    @RequestMapping(value = "/findOneOrderById", method = RequestMethod.POST)
    @ResponseBody
    public OrderInfo findOneOrderById(@RequestParam long orderId){
        return orderInfoService.findOneOrderById(orderId);
    }
}
