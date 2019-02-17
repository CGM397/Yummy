package edu.nju.Yummy.serviceImpl;

import edu.nju.Yummy.dao.ModificationDao;
import edu.nju.Yummy.model.Modification;
import edu.nju.Yummy.service.AdminManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminManagementServiceImpl implements AdminManagementService {

    @Autowired
    private ModificationDao modificationDao;

    @Override
    public ArrayList<Modification> showModification() {
        return modificationDao.showModification();
    }

    @Override
    public boolean updateModification(Modification modification) {
        return modificationDao.updateModification(modification);
    }
}
