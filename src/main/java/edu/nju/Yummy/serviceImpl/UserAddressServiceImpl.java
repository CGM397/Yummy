package edu.nju.Yummy.serviceImpl;

import edu.nju.Yummy.dao.UserAddressDao;
import edu.nju.Yummy.model.Address;
import edu.nju.Yummy.service.CommonService;
import edu.nju.Yummy.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressDao userAddressDao;

    @Autowired
    private CommonService commonService;

    @Override
    public boolean saveAddress(Address address) {
        ArrayList<Integer> coordinates = commonService.generateCoordinate();
        address.setCoordinateX(coordinates.get(0));
        address.setCoordinateY(coordinates.get(1));
        return userAddressDao.saveAddress(address);
    }

    @Override
    public ArrayList<Address> showAddress(String userId) {
        return userAddressDao.showAddress(userId);
    }
}
