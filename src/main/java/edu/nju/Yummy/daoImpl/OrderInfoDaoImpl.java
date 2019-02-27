package edu.nju.Yummy.daoImpl;

import edu.nju.Yummy.dao.BaseDao;
import edu.nju.Yummy.dao.OrderInfoDao;
import edu.nju.Yummy.model.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class OrderInfoDaoImpl implements OrderInfoDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public ArrayList<OrderInfo> showCustomerOrders(String customerId) {
        return null;
    }

    @Override
    public ArrayList<OrderInfo> showRestaurantOrders(String restaurantId) {
        return null;
    }

    @Override
    public boolean saveOrderInfo(OrderInfo orderInfo) {
        return baseDao.save(orderInfo);
    }
}
