package edu.nju.Yummy.dao;

import edu.nju.Yummy.model.Modification;

import java.util.ArrayList;

public interface ModificationDao {

    boolean addModification(Modification modification);

    boolean updateModification(Modification modification);

    ArrayList<Modification> findRestaurantModification(String restaurantId);

    ArrayList<Modification> showModification();
}
