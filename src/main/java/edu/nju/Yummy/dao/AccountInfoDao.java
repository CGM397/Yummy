package edu.nju.Yummy.dao;

import edu.nju.Yummy.model.Account;

import java.util.ArrayList;

public interface AccountInfoDao {

    ArrayList<Account> showUserAccount(String userId);

    boolean saveUserAccount(Account account);

    boolean updateUserAccount(Account account);
}
