package edu.nju.Yummy.daoImpl;

import edu.nju.Yummy.dao.AccountInfoDao;
import edu.nju.Yummy.dao.BaseDao;
import edu.nju.Yummy.model.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountInfoDaoImpl implements AccountInfoDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public ArrayList<Account> showUserAccount(String userId) {
        ArrayList<Account> res = new ArrayList<>();
        try(Session session = baseDao.getSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "from Account where userId = ?1";
            Query query = session.createQuery(hql);
            query.setParameter(1,userId);
            if(query.list() != null && query.list().size() > 0) {
                List<Account> store = query.list();
                res = (ArrayList<Account>) store;
            }
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean saveUserAccount(Account account) {
        return baseDao.save(account);
    }

    @Override
    public boolean updateUserAccount(Account account) {
        return baseDao.update(account);
    }
}
