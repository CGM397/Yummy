package edu.nju.Yummy.daoImpl;

import edu.nju.Yummy.dao.AccountInfoDao;
import edu.nju.Yummy.dao.BaseDao;
import edu.nju.Yummy.dao.OrderInfoDao;
import edu.nju.Yummy.model.Account;
import edu.nju.Yummy.model.OrderInfo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public class OrderInfoDaoImpl implements OrderInfoDao {

    @Autowired
    private BaseDao baseDao;

    @Autowired
    private AccountInfoDao accountInfoDao;

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
        ArrayList<OrderInfo> store = new ArrayList<>();
        ArrayList<OrderInfo> res = new ArrayList<>();
        try(Session session = baseDao.getSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "from OrderInfo";
            Query query = session.createQuery(hql);
            if(query.list().size() > 0)
                store = (ArrayList<OrderInfo>)query.list();
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        for(OrderInfo one : store){
            if(one.getOrderCondition().equals("未付款") && isOverdue(one.getOrderTime())) {
                one.setOrderCondition("已取消");
                baseDao.update(one);
            }else if(one.getOrderCondition().equals("送货中") && isArrive(one.getDeliveryTime())) {
                payMoneyToRestaurant(one);
            }
            res.add(one);
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

    @Override
    public boolean updateOrderCondition(long orderId, String orderCondition) {
        boolean res = false;
        try(Session session = baseDao.getSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "update OrderInfo o set o.orderCondition = ?1 where orderId = ?2";
            Query query = session.createQuery(hql);
            query.setParameter(1,orderCondition);
            query.setParameter(2,orderId);
            if(query.executeUpdate() > 0)
                res = true;
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean confirmOrderInAdvance(long orderId, Date confirmTime) {
        OrderInfo one = findOneOrderById(orderId);
        one.setDeliveryTime(confirmTime);
        payMoneyToRestaurant(one);
        return true;
    }

    private boolean isOverdue(Date orderTime){
        Date now = new Date();
        Date twoMinutesBefore = new Date(now.getTime() - 120000);
        return twoMinutesBefore.after(orderTime);
    }

    private boolean isArrive(Date deliveryTime) {
        Date now = new Date();
        return now.after(deliveryTime);
    }

    private void payMoneyToRestaurant(OrderInfo one){
        one.setOrderCondition("已完成");
        baseDao.update(one);
        Account account = accountInfoDao.showUserAccount(one.getRestaurantId()).get(0);
        Account adminAccount = accountInfoDao.showUserAccount("admin").get(0);
        account.setBalance(account.getBalance() + one.getTotalPrice() * 0.9);
        adminAccount.setBalance(adminAccount.getBalance() - one.getTotalPrice() * 0.9);
        accountInfoDao.updateUserAccount(account);
        accountInfoDao.updateUserAccount(adminAccount);
    }
}
