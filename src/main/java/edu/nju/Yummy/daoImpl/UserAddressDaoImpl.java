package edu.nju.Yummy.daoImpl;

import edu.nju.Yummy.dao.BaseDao;
import edu.nju.Yummy.dao.UserAddressDao;
import edu.nju.Yummy.model.Address;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserAddressDaoImpl implements UserAddressDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public boolean saveAddress(Address address) {
        return baseDao.save(address);
    }

    @Override
    public ArrayList<Address> showAddress(String userId) {
        ArrayList<Address> res = new ArrayList<>();
        try (Session session = baseDao.getSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "select a from Address a where a.userId = ?1";
            Query query = session.createQuery(hql);
            query.setParameter(1,userId);
            if(query.list().size() > 0)
                res = (ArrayList<Address>) query.list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
