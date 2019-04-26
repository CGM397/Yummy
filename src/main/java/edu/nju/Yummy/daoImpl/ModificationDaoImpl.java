package edu.nju.Yummy.daoImpl;

import edu.nju.Yummy.dao.BaseDao;
import edu.nju.Yummy.dao.ModificationDao;
import edu.nju.Yummy.model.Modification;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ModificationDaoImpl implements ModificationDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public boolean addModification(Modification modification) {
        return baseDao.save(modification);
    }

    @Override
    public boolean updateModification(Modification modification) {
        return baseDao.update(modification);
    }

    @Override
    public ArrayList<Modification> findRestaurantModification(String restaurantId) {
        ArrayList<Modification> res = new ArrayList<>();
        ArrayList<Modification> store = showModification();
        if(store != null && store.size() > 0){
            for(Modification modification : store){
                if(modification.getRestaurantId().equals(restaurantId))
                    res.add(modification);
            }
        }
        return res;
    }

    @Override
    public ArrayList<Modification> showModification() {
        ArrayList<Modification> res = new ArrayList<>();
        try(Session session = baseDao.getSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "from Modification";
            Query query = session.createQuery(hql);
            if(query.list().size() > 0)
                res = (ArrayList<Modification>) query.list();
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
