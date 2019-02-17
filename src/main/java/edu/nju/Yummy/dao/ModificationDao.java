package edu.nju.Yummy.dao;

import edu.nju.Yummy.model.Modification;

import java.util.ArrayList;

public interface ModificationDao {

    ArrayList<Modification> showModification();

    boolean deleteModification(String restaurantId);

    boolean addModification(Modification modification);
}
