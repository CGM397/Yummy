package edu.nju.Yummy.dao;

import org.hibernate.Session;

public interface BaseDao {

    Session getSession();

    boolean save(Object object);
}
