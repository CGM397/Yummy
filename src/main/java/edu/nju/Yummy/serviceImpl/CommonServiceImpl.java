package edu.nju.Yummy.serviceImpl;

import edu.nju.Yummy.service.CommonService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CommonServiceImpl implements CommonService {
    @Override
    public String generateRandomCode(int length) {
        String res = "";
        if(length < 1)
            return res;
        int min = (int)Math.pow(10, length - 1);
        int max = (int)Math.pow(10, length) - min;
        Random random = new Random();
        res = (random.nextInt(max) + min) + "";
        return res;
    }
}
