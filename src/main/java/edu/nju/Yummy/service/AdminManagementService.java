package edu.nju.Yummy.service;

import edu.nju.Yummy.model.Modification;

import java.util.ArrayList;

public interface AdminManagementService {

    ArrayList<Modification> showModification();

    boolean updateModification(Modification modification);

    int getCustomerNum();

    int getRestaurantNum();
}
