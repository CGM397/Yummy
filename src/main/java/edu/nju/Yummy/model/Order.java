package edu.nju.Yummy.model;

import java.util.ArrayList;
import java.util.Date;

public class Order {

    private String orderId;

    private String customerId;

    private String restaurantId;

    private String deliveryAddress;

    private String orderCondition;

    private Date orderTime;

    private double amount;

    private ArrayList<OrderItem> items;
}
