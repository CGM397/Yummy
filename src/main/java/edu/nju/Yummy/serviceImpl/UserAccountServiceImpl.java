package edu.nju.Yummy.serviceImpl;

import edu.nju.Yummy.dao.AccountInfoDao;
import edu.nju.Yummy.model.Account;
import edu.nju.Yummy.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private AccountInfoDao accountInfoDao;

    @Override
    public ArrayList<Account> showUserAccount(String userId) {
        return accountInfoDao.showUserAccount(userId);
    }

    @Override
    public boolean saveUserAccount(Account account) {
        return accountInfoDao.saveUserAccount(account);
    }

    @Override
    public boolean updateUserAccount(Account account) {
        return accountInfoDao.updateUserAccount(account);
    }
}
