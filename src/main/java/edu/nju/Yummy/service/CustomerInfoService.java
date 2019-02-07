package edu.nju.Yummy.service;

import edu.nju.Yummy.model.Address;
import edu.nju.Yummy.model.Customer;

import java.util.ArrayList;

public interface CustomerInfoService {

    boolean updateCustomerInfo(Customer customer);

    boolean saveDeliveryAddress(ArrayList<Address> addresses);

}
