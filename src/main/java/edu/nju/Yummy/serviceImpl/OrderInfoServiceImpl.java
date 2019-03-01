package edu.nju.Yummy.serviceImpl;

import edu.nju.Yummy.dao.*;
import edu.nju.Yummy.model.*;
import edu.nju.Yummy.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Autowired
    private CustomerInfoDao customerInfoDao;

    @Autowired
    private AccountInfoDao accountInfoDao;

    @Autowired
    private CommodityInfoDao commodityInfoDao;

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

    @Override
    public boolean payOrder(long orderId) {
        OrderInfo orderInfo = findOneOrderById(orderId);
        double totalPrice = orderInfo.getTotalPrice();
        String customerId = orderInfo.getCustomerId();
        String restaurantId = orderInfo.getRestaurantId();
        List<OrderItem> items = orderInfo.getItems();

        //check the balance
        ArrayList<Account> accountStore = accountInfoDao.showUserAccount(customerId);
        Account account = new Account();
        for(Account one : accountStore) {
            if(one.isInUse()) {
                if(one.getBalance() < totalPrice)
                    return false;
                account = one;
                break;
            }
        }
        //check the commodity amount
        ArrayList<CommodityInfo> commodityInfos = commodityInfoDao.showCommodity(restaurantId);
        ArrayList<CommodityInfo> commodityInfoStore = new ArrayList<>();
        for(OrderItem one : items) {
            for(CommodityInfo commodityInfo : commodityInfos) {
                if(one.getItemName().equals(commodityInfo.getCommodityName())) {
                    if(one.getAmount() > commodityInfo.getAmount())
                        return false;
                    commodityInfo.setAmount(commodityInfo.getAmount() - one.getAmount());
                    commodityInfoStore.add(commodityInfo);
                    break;
                }
            }
        }

        ArrayList<Customer> customerStore = customerInfoDao.showAllCustomers();
        Customer customer = new Customer();
        for(Customer one : customerStore) {
            if(one.getCustomerId().equals(customerId)) {
                customer = one;
                break;
            }
        }

        //update customer account and admin account
        account.setBalance(account.getBalance() - totalPrice);
        accountInfoDao.updateUserAccount(account);
        ArrayList<Account> adminAccountStore = accountInfoDao.showUserAccount("admin");
        Account adminAccount = adminAccountStore.get(0);
        adminAccount.setBalance(adminAccount.getBalance() + totalPrice);
        accountInfoDao.updateUserAccount(adminAccount);

        //update customerInfo: add the vip points and update vip level
        customer.setVipPoints(customer.getVipPoints() + (int) Math.round(totalPrice / 10.0));
        customer.setVipLevel(updateVipLevel(customer.getVipPoints()));
        customerInfoDao.updateCustomerInfo(customer);

        //update commodityInfo.amount
        for(CommodityInfo one : commodityInfoStore){
            commodityInfoDao.updateCommodityInfo(one);
        }

        //update order condition
        orderInfoDao.updateOrderCondition(orderInfo.getOrderId(), "送货中");

        return true;
    }

    @Override
    public double cancelOrder(long orderId) {
        double res = -2;
        OrderInfo orderInfo = findOneOrderById(orderId);
        if(orderInfo.getOrderCondition().equals("未付款")){
            orderInfoDao.updateOrderCondition(orderId,"已取消");
            res = -1;
        }else if(orderInfo.getOrderCondition().equals("送货中")){
            String customerId = orderInfo.getCustomerId();
            String restaurantId = orderInfo.getRestaurantId();
            List<OrderItem> items = orderInfo.getItems();
            double totalPrice = orderInfo.getTotalPrice();
            Date orderTime = orderInfo.getOrderTime();
            double returnMoney = getReturnMoney(totalPrice, orderTime);
            res = returnMoney;
            //update customer vipPoints and vipLevel
            ArrayList<Customer> customerStore = customerInfoDao.showAllCustomers();
            Customer customer = new Customer();
            for(Customer one : customerStore) {
                if(one.getCustomerId().equals(orderInfo.getCustomerId())) {
                    customer = one;
                    break;
                }
            }
            customer.setVipPoints(customer.getVipPoints() - (int) Math.round(totalPrice / 10.0));
            customer.setVipLevel(updateVipLevel(customer.getVipPoints()));
            customerInfoDao.updateCustomerInfo(customer);


            //update customer account and admin account
            ArrayList<Account> accountStore = accountInfoDao.showUserAccount(customerId);
            for(Account one : accountStore) {
                if(one.isInUse()) {
                    one.setBalance(one.getBalance() + returnMoney);
                    accountInfoDao.updateUserAccount(one);
                    break;
                }
            }
            Account adminAccount = accountInfoDao.showUserAccount("admin").get(0);
            adminAccount.setBalance(adminAccount.getBalance() - returnMoney);
            accountInfoDao.updateUserAccount(adminAccount);

            //update commodityInfo.amount
            ArrayList<CommodityInfo> commodityInfos = commodityInfoDao.showCommodity(restaurantId);
            for(OrderItem one : items) {
                for(CommodityInfo commodityInfo : commodityInfos) {
                    if(one.getItemName().equals(commodityInfo.getCommodityName())) {
                        commodityInfo.setAmount(commodityInfo.getAmount() + one.getAmount());
                        commodityInfoDao.updateCommodityInfo(commodityInfo);
                        break;
                    }
                }
            }

            //update orderInfo.orderCondition
            orderInfoDao.updateOrderCondition(orderId,"已取消");
        }
        return res;
    }

    private int updateVipLevel(int vipPoints) {
        int res = vipPoints / 50;
        if(res > 10)
            res = 10;
        return res;
    }

    private double getReturnMoney(double totalPrice, Date orderTime){
        double res = 0;
        Date now = new Date();
        int min = (int)Math.round((now.getTime() - orderTime.getTime()) / 60000.0);
        res = totalPrice * (1 - min / 100.0);
        res = Math.round(res * 100) / 100.0;
        return res;
    }
}
