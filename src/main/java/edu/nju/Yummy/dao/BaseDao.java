package edu.nju.Yummy.dao;

import org.hibernate.Session;

public interface BaseDao {

    Session getSession();

    boolean save(Object object);

    boolean update(Object object);

    boolean merge(Object object);
}
