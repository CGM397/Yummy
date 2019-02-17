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
    public boolean deleteModification(String restaurantId) {
        boolean res = false;
        try(Session session = baseDao.getSession()) {
            Transaction transaction = session.beginTransaction();
            Modification tmp = session.get(Modification.class, restaurantId);
            if(tmp != null){
                session.delete(tmp);
                transaction.commit();
                res = true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean updateModification(Modification modification) {
        return baseDao.update(modification);
    }

    @Override
    public Modification findModificationById(String restaurantId) {
        Modification res = new Modification();
        try(Session session = baseDao.getSession()) {
            Transaction transaction = session.beginTransaction();
            res = session.get(Modification.class, restaurantId);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
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
