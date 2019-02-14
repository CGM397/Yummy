package edu.nju.Yummy.service;

import edu.nju.Yummy.model.Address;

import java.util.ArrayList;

public interface UserAddressService {

    boolean saveAddress(Address address);

    ArrayList<Address> showAddress(String userId);
}
