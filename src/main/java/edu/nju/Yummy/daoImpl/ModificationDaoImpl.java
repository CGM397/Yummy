package edu.nju.Yummy.daoImpl;

import edu.nju.Yummy.dao.ModificationDao;
import edu.nju.Yummy.model.Modification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ModificationDaoImpl implements ModificationDao {
    @Override
    public ArrayList<Modification> showModification() {
        return null;
    }

    @Override
    public boolean deleteModification(String restaurantId) {
        return false;
    }

    @Override
    public boolean addModification(Modification modification) {
        return false;
    }
}
