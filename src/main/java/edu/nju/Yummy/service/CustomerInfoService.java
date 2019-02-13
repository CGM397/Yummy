package edu.nju.Yummy.service;

import edu.nju.Yummy.model.Address;
import edu.nju.Yummy.model.Customer;

import java.util.ArrayList;

public interface CustomerInfoService {

    Customer findCustomerInfoByMail(String customerMail);

    boolean updateCustomerInfo(Customer customer);

    boolean saveDeliveryAddress(Address address);

    ArrayList<Address> showDeliveryAddress(String customerId);

}
