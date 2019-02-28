package edu.nju.Yummy.service;

import edu.nju.Yummy.model.OrderInfo;

import java.util.ArrayList;

public interface OrderInfoService {

    OrderInfo findOneOrderById(long orderId);

    ArrayList<OrderInfo> showCustomerOrders(String customerId);

    ArrayList<OrderInfo> showRestaurantOrders(String restaurantId);

    boolean saveOrderInfo(OrderInfo orderInfo);

}
