package edu.nju.Yummy.service;

public interface RegisterLoginService {

    boolean login(String customerMail, String customerPassword);

    /**
     * check the uniqueness of this userMail,
     * then send verification code to this mail address!
     * @param customerMail the user's mail address
     * @return verification code or wrong message
     */
    String sendMail(String customerMail);

    /**
     * save this new user's info!
     * @param customerMail user mail
     * @param customerPassword user password
     * @param customerName user name
     * @param phoneNumber user phone number
     * @return success or not
     */
    boolean register(String customerMail, String customerPassword, String customerName, String phoneNumber);
}
