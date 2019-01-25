package edu.nju.Yummy.service;

public interface RegisterLoginService {

    boolean login(String userMail, String userPassword);

    boolean register(String userMail, String userPassword, String userName, String phoneNumber);
}
