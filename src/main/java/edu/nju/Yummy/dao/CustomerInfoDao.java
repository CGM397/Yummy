package edu.nju.Yummy.dao;

import edu.nju.Yummy.model.Address;
import edu.nju.Yummy.model.Customer;

import java.util.ArrayList;

public interface CustomerInfoDao {

    boolean saveCustomerInfo(Customer customer);

    boolean updateCustomerInfo(Customer customer);

    boolean saveDeliveryAddress(Address address);

    ArrayList<Address> showDeliveryAddress(String customerId);

    Customer findCustomerInfoByMail(String customerMail);
    
    ArrayList<Customer> showAllCustomers();
}
