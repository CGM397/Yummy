package edu.nju.Yummy.daoImpl;

import edu.nju.Yummy.dao.BaseDao;
import edu.nju.Yummy.dao.CustomerInfoDao;
import edu.nju.Yummy.model.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerInfoDaoImpl implements CustomerInfoDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public boolean saveCustomerInfo(Customer customer) {
        boolean res = false;
        try (Session session = baseDao.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Customer findCustomerInfoByMail(String customerMail) {
        Customer res = new Customer();
        try (Session session = baseDao.getSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "select c from Customer c where c.customerMail = ?1";
            Query query = session.createQuery(hql);
            query.setParameter(1,customerMail);
            res = (Customer) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
