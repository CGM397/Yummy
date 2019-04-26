package edu.nju.Yummy.dao;

import edu.nju.Yummy.model.Customer;

import java.util.ArrayList;

public interface CustomerInfoDao {

    boolean saveCustomerInfo(Customer customer);

    boolean updateCustomerInfo(Customer customer);

    Customer findCustomerInfoByMail(String customerMail);
    
    ArrayList<Customer> showAllCustomers();
}
