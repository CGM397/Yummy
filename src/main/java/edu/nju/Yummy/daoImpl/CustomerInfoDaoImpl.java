package edu.nju.Yummy.daoImpl;

import edu.nju.Yummy.dao.BaseDao;
import edu.nju.Yummy.dao.CustomerInfoDao;
import edu.nju.Yummy.model.Address;
import edu.nju.Yummy.model.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class CustomerInfoDaoImpl implements CustomerInfoDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public boolean saveCustomerInfo(Customer customer) {
        return baseDao.save(customer);
    }

    @Override
    public boolean updateCustomerInfo(Customer customer) {
        return baseDao.update(customer);
    }

    @Override
    public boolean saveDeliveryAddress(ArrayList<Address> addresses) {
        for(Address address : addresses){
            if(!baseDao.merge(address))
                return false;
        }
        return true;
    }

    @Override
    public Customer findCustomerInfoByMail(String customerMail) {
        Customer res = new Customer();
        try (Session session = baseDao.getSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "select c from Customer c where c.customerMail = ?1";
            Query query = session.createQuery(hql);
            query.setParameter(1,customerMail);
            if(query.list().size() > 0)
                res = (Customer) query.list().get(0);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ArrayList<Customer> showAllCustomers() {
        ArrayList<Customer> res = new ArrayList<>();
        try(Session session = baseDao.getSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "from Customer";
            Query query = session.createQuery(hql);
            if(query.list().size() > 0)
                res = (ArrayList<Customer>) query.list();
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
