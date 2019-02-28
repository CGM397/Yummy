package edu.nju.Yummy.dao;

import edu.nju.Yummy.model.OrderInfo;

import java.util.ArrayList;

public interface OrderInfoDao {

    OrderInfo findOneOrderById(long orderId);

    ArrayList<OrderInfo> showCustomerOrders(String customerId);

    ArrayList<OrderInfo> showRestaurantOrders(String restaurantId);

    boolean saveOrderInfo(OrderInfo orderInfo);

    boolean updateOrderCondition(long orderId, String orderCondition);
}
