package edu.nju.Yummy.dao;

import edu.nju.Yummy.model.Modification;

import java.util.ArrayList;

public interface ModificationDao {

    boolean addModification(Modification modification);

    boolean deleteModification(String restaurantId);

    boolean updateModification(Modification modification);

    Modification findModificationById(String restaurantId);

    ArrayList<Modification> showModification();
}
