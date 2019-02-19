package edu.nju.Yummy.serviceImpl;

import edu.nju.Yummy.dao.CustomerInfoDao;
import edu.nju.Yummy.dao.RestaurantInfoDao;
import edu.nju.Yummy.model.Customer;
import edu.nju.Yummy.model.Restaurant;
import edu.nju.Yummy.service.CommonService;
import edu.nju.Yummy.service.RegisterLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RegisterLoginServiceImpl implements RegisterLoginService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CustomerInfoDao customerInfoDao;
    @Autowired
    private RestaurantInfoDao restaurantInfoDao;

    @Override
    public String login(String identity, String account, String password) {
        String res = "";
        if(identity.equals("顾客")){
            Customer customer = customerInfoDao.findCustomerInfoByMail(account);
            if(customer == null || customer.getCustomerPassword() == null)
                res = "wrong_password";
            else if(customer.getCustomerPassword().equals(password)) {
                if(customer.isActive())
                    res = "success";
                else
                    res = "not_active";
            }
            else
                res = "wrong_password";
        }else if(identity.equals("餐厅")){
            Restaurant restaurant = restaurantInfoDao.findRestaurantInfoById(account);
            if(restaurant == null || restaurant.getRestaurantPassword() == null)
                res = "wrong_password";
            else if(restaurant.getRestaurantPassword().equals(password)) {
                res = "success";
            }
            else
                res = "wrong_password";
        }else if(identity.equals("经理")){
            if(account.equals("admin") && password.equals("000000"))
                res = "success";
            else
                res = "wrong_password";
        }
        return res;
    }

    @Override
    public String sendMail(String customerMail) {
        ArrayList<Customer> customers = customerInfoDao.showAllCustomers();
        for(Customer one : customers) {
            if(one.getCustomerMail().equals(customerMail))
                return "duplicate_mail";
        }
        SimpleMailMessage message = new SimpleMailMessage();
        String randomCode = commonService.generateRandomCode(6);
        message.setFrom("980231201@qq.com");
        message.setTo(customerMail);
        message.setSubject("YummySystem : 邮箱验证");
        message.setText("验证码 : " + randomCode);
        mailSender.send(message);
        return randomCode;
    }

    @Override
    public boolean customerRegister(String customerMail, String customerPassword,
                                    String customerName, String phoneNumber) {
        String customerId = "c_" + commonService.generateId(6,"customer");
        Customer customer = new Customer(customerId, customerMail, customerPassword, customerName,
                phoneNumber,0,true);
        return customerInfoDao.saveCustomerInfo(customer);
    }

    @Override
    public String restaurantRegister(String restaurantName, String restaurantPassword) {
        String restaurantId = commonService.generateId(7,"restaurant");
        Restaurant restaurant = new Restaurant(restaurantId, restaurantPassword, restaurantName,"type");
        if(restaurantInfoDao.saveRestaurantInfo(restaurant))
            return restaurantId;
        else
            return "fail";
    }
}
