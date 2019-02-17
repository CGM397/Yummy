package edu.nju.Yummy.daoImpl;

import edu.nju.Yummy.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository
public class BaseDaoImpl implements BaseDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    @Override
    public boolean save(Object object) {
        boolean res = false;
        try(Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
            res = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean update(Object object) {
        boolean res = false;
        try(Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
            res = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
