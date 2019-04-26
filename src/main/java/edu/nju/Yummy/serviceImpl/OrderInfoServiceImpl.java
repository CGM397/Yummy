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

    @Autowired
    private RestaurantInfoDao restaurantInfoDao;

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
        //check the commodityInfo.leftAmount
        ArrayList<CommodityInfo> commodityInfos = commodityInfoDao.showCommodity(restaurantId);
        ArrayList<DiscountInfo> discountInfos = restaurantInfoDao.showDiscountInfo(restaurantId);
        ArrayList<CommodityInfo> commodityInfoStore = new ArrayList<>();
        ArrayList<DiscountInfo> discountInfoStore = new ArrayList<>();
        for(OrderItem one : items) {
            for(CommodityInfo commodityInfo : commodityInfos) {
                if(one.getItemName().equals(commodityInfo.getCommodityName())) {
                    if(one.getAmount() > commodityInfo.getLeftAmount())
                        return false;
                    commodityInfo.setLeftAmount(commodityInfo.getLeftAmount() - one.getAmount());
                    commodityInfoStore.add(commodityInfo);
                    for(DiscountInfo discountInfo : discountInfos) {
                        if(one.getItemName().equals(discountInfo.getCommodityName())) {
                            if(discountInfo.getLeftAmount() >= one.getAmount()){
                                discountInfo.setLeftAmount(discountInfo.getLeftAmount()
                                                            - one.getAmount());
                            }else{
                                discountInfo.setLeftAmount(0);
                            }
                            discountInfoStore.add(discountInfo);
                            break;
                        }
                    }
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

        //update commodityInfo.leftAmount and discountInfo.leftAmount
        for(CommodityInfo one : commodityInfoStore) {
            commodityInfoDao.updateCommodityInfo(one);
        }
        for(DiscountInfo one : discountInfoStore) {
            commodityInfoDao.updateDiscountInfo(one);
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

            //update commodityInfo.leftAmount and discountInfo.leftAmount
            ArrayList<CommodityInfo> commodityInfos = commodityInfoDao.showCommodity(restaurantId);
            ArrayList<DiscountInfo> discountInfos = restaurantInfoDao.showDiscountInfo(restaurantId);
            for(OrderItem one : items) {
                for(CommodityInfo commodityInfo : commodityInfos) {
                    if(one.getItemName().equals(commodityInfo.getCommodityName())) {
                        int currentLeftAmount = commodityInfo.getLeftAmount();
                        int amount = commodityInfo.getAmount();
                        commodityInfo.setLeftAmount(commodityInfo.getLeftAmount() + one.getAmount());
                        commodityInfoDao.updateCommodityInfo(commodityInfo);
                        for(DiscountInfo discountInfo : discountInfos) {
                            if(one.getItemName().equals(discountInfo.getCommodityName())){
                                int tmp1 = currentLeftAmount + one.getAmount();
                                int tmp2 = amount - discountInfo.getDiscountAmount();
                                if(tmp1 < tmp2){
                                    discountInfo.setLeftAmount(0);
                                }else
                                    discountInfo.setLeftAmount(tmp1 - tmp2);
                                commodityInfoDao.updateDiscountInfo(discountInfo);
                                break;
                            }
                        }
                        break;
                    }
                }
            }

            //update orderInfo.orderCondition
            orderInfoDao.updateOrderCondition(orderId,"已取消");
        }
        return res;
    }

    @Override
    public boolean confirmOrderInAdvance(long orderId, Date confirmTime) {
        return orderInfoDao.confirmOrderInAdvance(orderId, confirmTime);
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
        //1% per minutes
        res = totalPrice * (1 - min / 100.0);
        //up to 50%
        if(res < totalPrice / 2.0)
            res = totalPrice / 2.0;
        res = Math.round(res * 100) / 100.0;
        return res;
    }
}
