package edu.nju.Yummy.daoImpl;

import edu.nju.Yummy.dao.BaseDao;
import edu.nju.Yummy.dao.CustomerInfoDao;
import edu.nju.Yummy.model.Customer;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerInfoDaoImpl implements CustomerInfoDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public boolean saveCustomerInfo(Customer customer) {
        Session session = baseDao.getSession();
        boolean res = false;
        try{
            session.save(customer);
            res = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Customer findCustomerInfoByMail(String customerMail) {
        return null;
    }
}
