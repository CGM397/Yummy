package edu.nju.Yummy.daoImpl;

import edu.nju.Yummy.dao.BaseDao;
import edu.nju.Yummy.dao.OrderInfoDao;
import edu.nju.Yummy.model.OrderInfo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class OrderInfoDaoImpl implements OrderInfoDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public OrderInfo findOneOrderById(long orderId){
        OrderInfo res = new OrderInfo();
        try(Session session = baseDao.getSession()) {
            Transaction transaction = session.beginTransaction();
            res = session.get(OrderInfo.class, orderId);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    private ArrayList<OrderInfo> showAllOrders(){
        ArrayList<OrderInfo> res = new ArrayList<>();
        try(Session session = baseDao.getSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "from OrderInfo";
            Query query = session.createQuery(hql);
            if(query.list().size() > 0)
                res = (ArrayList<OrderInfo>)query.list();
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ArrayList<OrderInfo> showCustomerOrders(String customerId) {
        ArrayList<OrderInfo> res = new ArrayList<>();
        ArrayList<OrderInfo> store = showAllOrders();
        for(OrderInfo one : store) {
            if(one.getCustomerId().equals(customerId))
                res.add(one);
        }
        return res;
    }

    @Override
    public ArrayList<OrderInfo> showRestaurantOrders(String restaurantId) {
        ArrayList<OrderInfo> res = new ArrayList<>();
        ArrayList<OrderInfo> store = showAllOrders();
        for(OrderInfo one : store) {
            if(one.getRestaurantId().equals(restaurantId))
                res.add(one);
        }
        return res;
    }

    @Override
    public boolean saveOrderInfo(OrderInfo orderInfo) {
        return baseDao.save(orderInfo);
    }
}
