package edu.nju.Yummy.serviceImpl;

import edu.nju.Yummy.dao.CustomerInfoDao;
import edu.nju.Yummy.model.Customer;
import edu.nju.Yummy.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {

    @Autowired
    private CustomerInfoDao customerInfoDao;

    @Override
    public Customer findCustomerInfoById(String customerId) {
        ArrayList<Customer> customers = customerInfoDao.showAllCustomers();
        for(Customer one : customers) {
            if(one.getCustomerId().equals(customerId)) {
                return one;
            }
        }
        return new Customer();
    }

    @Override
    public Customer findCustomerInfoByMail(String customerMail) {
        return customerInfoDao.findCustomerInfoByMail(customerMail);
    }

    @Override
    public boolean updateCustomerInfo(Customer customer) {
        return customerInfoDao.updateCustomerInfo(customer);
    }
}
