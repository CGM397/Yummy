package edu.nju.Yummy.serviceImpl;

import edu.nju.Yummy.dao.OrderInfoDao;
import edu.nju.Yummy.model.OrderInfo;
import edu.nju.Yummy.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Override
    public boolean saveOrderInfo(OrderInfo orderInfo) {
        return orderInfoDao.saveOrderInfo(orderInfo);
    }
}
