package edu.nju.Yummy.service;

import edu.nju.Yummy.model.CommodityInfo;
import edu.nju.Yummy.model.DiscountInfo;

import java.util.ArrayList;

public interface RestaurantReleaseService {

    boolean releaseProduct(CommodityInfo commodityInfo);

    boolean releaseDiscount(DiscountInfo discountInfo);

    ArrayList<CommodityInfo> showCommodity(String restaurantId);
}
