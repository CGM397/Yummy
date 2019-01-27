package edu.nju.Yummy.serviceImpl;

import edu.nju.Yummy.service.RegisterLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class RegisterLoginServiceImpl implements RegisterLoginService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public boolean login(String userMail, String userPassword) {
        return true;
    }

    @Override
    public boolean register(String userMail, String userPassword, String userName, String phoneNumber) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("980231201@qq.com");
        message.setTo("980231201@qq.com");
        message.setSubject("邮箱验证");
        message.setText("验证码");
        mailSender.send(message);
        return true;
    }
}
