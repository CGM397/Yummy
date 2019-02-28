package edu.nju.Yummy.service;

import edu.nju.Yummy.model.Account;

import java.util.ArrayList;

public interface UserAccountService {

    ArrayList<Account> showUserAccount(String userId);

    boolean saveUserAccount(Account account);

    boolean updateUserAccount(Account account);
}
