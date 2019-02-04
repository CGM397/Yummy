package edu.nju.Yummy.daoImpl;

import edu.nju.Yummy.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
}
