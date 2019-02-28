package edu.nju.Yummy.serviceImpl;

import edu.nju.Yummy.dao.OrderInfoDao;
import edu.nju.Yummy.model.OrderInfo;
import edu.nju.Yummy.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Override
    public OrderInfo findOneOrderById(long orderId) {
        return orderInfoDao.findOneOrderById(orderId);
    }

    @Override
    public ArrayList<OrderInfo> showCustomerOrders(String customerId) {
        return orderInfoDao.showCustomerOrders(customerId);
    }

    @Override
    public ArrayList<OrderInfo> showRestaurantOrders(String restaurantId) {
        return orderInfoDao.showRestaurantOrders(restaurantId);
    }

    @Override
    public boolean saveOrderInfo(OrderInfo orderInfo) {
        return orderInfoDao.saveOrderInfo(orderInfo);
    }
}
