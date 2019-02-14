package edu.nju.Yummy.dao;

import edu.nju.Yummy.model.Address;

import java.util.ArrayList;

public interface UserAddressDao {

    boolean saveAddress(Address address);

    ArrayList<Address> showAddress(String userId);
}
