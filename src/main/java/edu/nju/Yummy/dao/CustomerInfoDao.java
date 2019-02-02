package edu.nju.Yummy.dao;

import edu.nju.Yummy.model.Customer;

public interface CustomerInfoDao {

    boolean saveCustomerInfo(Customer customer);

    Customer findCustomerInfoByMail(String customerMail);
}
