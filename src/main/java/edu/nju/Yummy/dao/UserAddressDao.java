package edu.nju.Yummy.dao;

import edu.nju.Yummy.model.Address;

import java.util.ArrayList;

public interface UserAddressDao {

    boolean saveAddress(Address address);

    ArrayList<Address> showAddress(String userId);

    boolean updateAddress(String userId, String oldAddress, String newAddress);

    boolean deleteAddress(String userId, String address);
}
