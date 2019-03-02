package edu.nju.Yummy.service;

import edu.nju.Yummy.model.Customer;

public interface CustomerInfoService {

    Customer findCustomerInfoById(String customerId);

    Customer findCustomerInfoByMail(String customerMail);

    boolean updateCustomerInfo(Customer customer);

}
