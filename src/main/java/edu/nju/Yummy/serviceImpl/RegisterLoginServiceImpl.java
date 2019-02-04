package edu.nju.Yummy.serviceImpl;

import edu.nju.Yummy.dao.CustomerInfoDao;
import edu.nju.Yummy.model.Customer;
import edu.nju.Yummy.service.CommonService;
import edu.nju.Yummy.service.RegisterLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class RegisterLoginServiceImpl implements RegisterLoginService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CustomerInfoDao customerInfoDao;

    @Override
    public boolean login(String identity, String account, String password) {
        boolean res = false;
        if(identity.equals("顾客")){
            Customer customer = customerInfoDao.findCustomerInfoByMail(account);
            if(customer.getCustomerPassword().equals(password))
                res = true;
        }
        return res;
    }

    @Override
    public String sendMail(String customerMail) {
        //check the uniqueness of this mail first

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
        Customer customer = new Customer("id2",customerMail,customerPassword,customerName,
                phoneNumber,1);
        return customerInfoDao.saveCustomerInfo(customer);
    }

    @Override
    public String restaurantRegister(String restaurantName, String restaurantPassword) {
        return null;
    }
}
