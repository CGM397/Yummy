package edu.nju.Yummy.serviceImpl;

import edu.nju.Yummy.model.Address;
import edu.nju.Yummy.model.Customer;
import edu.nju.Yummy.service.CustomerInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {
    @Override
    public boolean updateCustomerInfo(Customer customer) {
        return false;
    }

    @Override
    public boolean saveDeliveryAddress(ArrayList<Address> addresses) {
        return false;
    }
}
