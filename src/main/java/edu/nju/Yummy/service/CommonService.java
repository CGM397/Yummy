package edu.nju.Yummy.service;

import java.util.ArrayList;

public interface CommonService {

    /**
     * generate random code
     * @param length the length of the code
     * @return random code
     */
    String generateRandomCode(int length);

    /**
     * generate customer/restaurant Id
     * @param length id_length
     * @param identity customer/restaurant
     * @return legal_id
     */
    String generateId(int length, String identity);

    /**
     * generate random coordinate x,y in range of[0,300]
     * @return x and y
     */
    ArrayList<Integer> generateCoordinate();
}
